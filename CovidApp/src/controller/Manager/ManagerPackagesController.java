package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ButtonModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.TableModel;

import model.Package;
import model.managed.Managed_History;
import model.managed.Managed_Package;
import model.managed.Managed_Zone;
import utils.DatabaseConnect;
import view.Manager.ManagerPackages;
import view.Manager.ManagerView;

public class ManagerPackagesController implements ActionListener {
	public ManagerPackages view;

	public ManagerPackagesController(ManagerPackages view) {
		this.view = view;
	}

	private String previousCm = null;

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		System.out.println(cm);

		if (cm.equals("Thoát")) {
			view.dispose();
			ManagerView mv = new ManagerView();
			mv.setVisible(true);

		} else if (cm.equals("Thêm")) {
			this.previousCm = cm;
			clearForm();
			this.view.getIdText().setText(findNextId());
			enabledForm();

		} else if (cm.equals("Đặt lại")) {
			clearForm();
			this.view.loadData();

		} else if (cm.equals("Xóa")) {
			delAction();
			this.view.loadData();

		} else if (cm.equals("Sửa")) {
			this.previousCm = cm;
			int i = this.view.getTableListPackage().getSelectedRow();
			// System.out.println(i);
			if (i == -1) {
				JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để sửa");
			} else {
				enabledForm();
			}

		} else if (cm.equals("Lưu")) {
			// System.out.println(previousCm);
			saveAction(previousCm);
			clearForm();
			this.view.loadData();

		} else if (cm.equals("Tìm kiếm")) {
			searchAction();

		} else if (cm.equals("Sắp xếp tăng")) {
			sortAction("ASC");

		} else if (cm.equals("Sắp xếp giảm")) {
			sortAction("DESC");

		} else if (cm.equals("Lọc")) {
			filterAction();
		}

	}

	private void sortAction(String type) {
		String col = this.view.getSort().getSelection().getActionCommand();
		// System.out.println(col);
		if (col.equals("Tên gói")) {
			this.view.getTableListPackage().setModel(Managed_Package.sortBy(" TENNYP ", type, this.view.initialRow()));
		} else if (col.equals("Giá")) {
			this.view.getTableListPackage().setModel(Managed_Package.sortBy(" GIATIEN ", type, this.view.initialRow()));
		} else if (col.equals("Thời gian")) {
			this.view.getTableListPackage().setModel(Managed_Package.sortBy(" HSD ", type, this.view.initialRow()));
		} else {
			JOptionPane.showMessageDialog(view, "Chưa chọn thuộc tính sắp xếp");
		}

	}

	private void filterAction() {
		ButtonModel wanranty = this.view.getWanranty().getSelection();
		ButtonModel limition = this.view.getLimitation().getSelection();
		ButtonModel price = this.view.getPrice().getSelection();
		String sql = "SELECT * FROM NHUYEUPHAM WHERE ";
		String currDay = "2021-12-03";
		if (wanranty != null) {
			if (wanranty.getActionCommand().equals("Còn thời hạn")) {
				sql += "HSD > '" + currDay + "' AND ";
			} else {
				sql += "HSD < '" + currDay + "' AND ";
			}
		}
		if (price != null) {
			if (price.getActionCommand().equals("Giá > 200.000")) {
				sql += "GIATIEN > 200000 AND ";
			} else {
				sql += "GIATIEN <= 200000 AND ";
			}
		}
		if (limition != null) {
			if (limition.getActionCommand().equals("Mức giới hạn > 10")) {
				sql += "GIOIHANSL > 10 AND ";
			} else {
				sql += "GIOIHANSL <= 10 AND ";
			}
		}
		sql = sql.substring(0, sql.length() - 4);
		this.view.getTableListPackage()
		.setModel(Managed_Package.filterPackage(this.view.initialRow(), sql));
	}

	private void searchAction() {
		String keyword = this.view.getSearchPackageText().getText().trim();
		if (keyword.length() == 0) {
			JOptionPane.showMessageDialog(view, "Cần nhập tên nhu yếu phẩm trước khi tìm kiếm");
		} else {
			this.view.getTableListPackage()
					.setModel(Managed_Package.showPackageByName(this.view.initialRow(), keyword));
		}
	}

	private void delAction() {
		int i = this.view.getTableListPackage().getSelectedRow();

		if (i == -1) {
			JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để xóa");
		}
		String ops[] = { "Có", "Không", "Thoát" };
		int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn xóa", "Xác nhận xóa", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, ops, "Không");

		if (op == 0) {
			TableModel model = this.view.getTableListPackage().getModel();
			String id = model.getValueAt(i, 0).toString().trim();
			Managed_History.addManagerHistory("Xóa", "NHUYEUPHAM", id);
			Managed_Package.delPackage(id);
		}
	}

	private String findNextId() {
		Connection con = DatabaseConnect.openConnection();
		String id = "NYP";
		int code = 0;
		try {
			ResultSet rs = DatabaseConnect.getResultSet(con, "select count(*) from NHUYEUPHAM");
			while (rs.next()) {
				code = rs.getInt(1) + 1;
			}
			// System.out.println(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (code < 10) {
			id += "0" + code;
		} else {
			id += code;
		}
		return id;
	}

	private void saveAction(String previousCm) {
		String id = this.view.getIdText().getText().trim();
		String name = this.view.getNamePackageText().getText().trim();
		String limit = this.view.getLimitText().getText().trim();
		String timeLimit = this.view.getTimeText().getText().trim();
		String price = this.view.getPriceText().getText().trim();
		Package pk = validatedData(id, name, limit, timeLimit, price);

		if (pk != null) {
			Managed_History.addManagerHistory(previousCm, "NHUYEUPHAM", pk.getId());
			if (previousCm.equals("Thêm")) {
				Managed_Package.addPackage(pk);
			} else {
				Managed_Package.modifyPackage(pk);
			}
		}
	}

	private Package validatedData(String id, String name, String limit, String timeLimit, String price) {
		Date tl = null;
		Double p = 0d;
		int lm = 0;
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(view, "Chưa nhập tên NYP");
			return null;
		}
		try {
			tl = new SimpleDateFormat("yyyy-MM-dd").parse(timeLimit);
		} catch (ParseException e) {
			JOptionPane.showMessageDialog(view, "Sai định dạng ngày tháng");
			return null;
		}
		try {
			p = Double.parseDouble(price);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Sai định dạng giá");
			return null;
		}
		try {
			lm = Integer.parseInt(limit);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Sai định dạng mức giới hạn");
			return null;
		}

		if (lm <= 0) {
			JOptionPane.showMessageDialog(view, "Nhập lại số lượng giới hạn dương");
			return null;
		}
		return new Package(id, name, tl, p, lm);
	}

	private void enabledForm() {
		this.view.getNamePackageText().setEnabled(true);
		this.view.getLimitText().setEnabled(true);
		this.view.getTimeText().setEnabled(true);
		this.view.getPriceText().setEnabled(true);
	}

	private void disabledForm() {
		this.view.getNamePackageText().setEnabled(false);
		this.view.getLimitText().setEnabled(false);
		this.view.getTimeText().setEnabled(false);
		this.view.getPriceText().setEnabled(false);
	}

	private void clearForm() {
		// this.view.getIdText().setText("");
		this.view.getNamePackageText().setText("");
		this.view.getLimitText().setText("");
		this.view.getTimeText().setText("");
		this.view.getPriceText().setText("");
		this.view.getSort().clearSelection();
		this.view.getWanranty().clearSelection();
		this.view.getLimitation().clearSelection();
		this.view.getPrice().clearSelection();
	}

	public void displayData() {
		disabledForm();

		int i = this.view.getTableListPackage().getSelectedRow();

		TableModel model = this.view.getTableListPackage().getModel();
		this.view.getIdText().setText(model.getValueAt(i, 0).toString());
		this.view.getNamePackageText().setText(model.getValueAt(i, 1).toString());
		this.view.getLimitText().setText(model.getValueAt(i, 3).toString());
		this.view.getTimeText().setText(model.getValueAt(i, 2).toString());
		this.view.getPriceText().setText(model.getValueAt(i, 4).toString());
	}

}
