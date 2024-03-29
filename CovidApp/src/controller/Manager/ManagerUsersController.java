package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import model.Address;
import model.F;
import model.User;
import model.Zone;
import model.managed.Managed_Address;
import model.managed.Managed_History;
import model.managed.Managed_User;
import model.managed.Managed_Zone;
import view.Manager.ManagerUsers;
import view.Manager.ManagerView;
import view.Manager.UserInfoViewDetail;

public class ManagerUsersController implements ActionListener {
	public ManagerUsers view;

	private String previousCm = null;
	private String oldZone = "";

	public ManagerUsersController(ManagerUsers view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		// System.out.println(cm);
		if (cm.equals("Thoát")) {
			view.dispose();
			ManagerView mv = new ManagerView();
			mv.setVisible(true);

		} else if (cm.equals("Thêm")) {
			this.previousCm = cm;
			enabledForm();
			clearForm();

		} else if (cm.equals("Sửa")) {
			this.previousCm = cm;
			modifyAction();

		} /*
			 * else if (cm.equals("Xóa")) { //delAction(); loadData();
			 * 
			 * }
			 */else if (cm.equals("Đặt lại")) {
			String ops[] = { "Có", "Không", "Thoát" };
			int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn đặt lại?", "Xác nhận đặt lại",
					JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE, null, ops, "Không");
			// System.out.println(op);
			if (op == 0) {
				clearForm();
				loadData();
			}
		} else if (cm.equals("Lưu")) {
			saveAction(previousCm);
			loadData();

		} else if (cm.equals("Sắp xếp tăng")) {
			sortAction(" ASC");

		} else if (cm.equals("Sắp xếp giảm")) {
			sortAction(" DESC");

		} else if (cm.equals("Tìm kiếm")) {
			searchAction();

		} else if (cm.equals("Xem chi tiết")) {

			int i = this.view.getTableListUser().getSelectedRow();

			if (i == -1) {
				JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để xem");
			} else {

				this.view.dispose();

				String id = this.view.getTableListUser().getModel().getValueAt(i, 0).toString().trim();
				UserInfoViewDetail uivd = new UserInfoViewDetail(id);
				uivd.setVisible(true);
			}
		}

	}

	private void modifyAction() {
		int i = this.view.getTableListUser().getSelectedRow();
		// System.out.println(i);
		if (i == -1) {
			JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để sửa");
		} else {
			TableModel model = this.view.getTableListUser().getModel();
			if (model.getValueAt(i, 6) != null) {
				this.oldZone = model.getValueAt(i, 6).toString().trim();
			} else {
				this.oldZone = "";
			}
			enabledForm();
			this.view.getIdText().setEditable(false);
		}

	}

	private void searchAction() {
		String id = this.view.getSearchIdText().getText().trim();
		String name = this.view.getSearchNameText().getText().trim();
		// System.out.println(keyword);
		if (id.length() == 0 && name.length() == 0) {
			JOptionPane.showMessageDialog(view, "Cần nhập thông tin tìm kiếm");
		} else {
			this.view.getTableListUser().setModel(Managed_User.showUserByNameAndId(this.view.initialRow(), id, name));
		}

	}

	private void sortAction(String type) {
		String col = this.view.getSortGroup().getSelection().getActionCommand();
		// System.out.println(col);
		if (col.equals("CMND")) {
			this.view.getTableListUser().setModel(Managed_User.sortBy("CMND", type, this.view.initialRow()));
		} else if (col.equals("Họ tên")) {
			this.view.getTableListUser().setModel(Managed_User.sortByName(type, this.view.initialRow()));
		} else if (col.equals("Năm sinh")) {
			this.view.getTableListUser().setModel(Managed_User.sortBy("NAMSINH", type, this.view.initialRow()));
		} else if (col.equals("Trạng thái")) {
			this.view.getTableListUser().setModel(Managed_User.sortBy("TRANGTHAI", type, this.view.initialRow()));
		} else {
			JOptionPane.showMessageDialog(view, "Chưa chọn thuộc tính sắp xếp");
		}

	}

	/*
	 * private void delAction() { int i =
	 * this.view.getTableListUser().getSelectedRow(); // System.out.println(i); if
	 * (i == -1) { JOptionPane.showMessageDialog(view,
	 * "Chưa chọn đối tượng để xóa"); } String id =
	 * this.view.getIdText().getText().toString().trim(); String ops[] = { "Có",
	 * "Không", "Thoát" }; int op = JOptionPane.showOptionDialog(view,
	 * "Bạn có chắc muốn xóa", "Xác nhận xóa", JOptionPane.NO_OPTION,
	 * JOptionPane.PLAIN_MESSAGE, null, ops, "Không"); // System.out.println(op); if
	 * (op == 0) { TableModel model = this.view.getTableListUser().getModel();
	 * Managed_History.addManagerHistory("Xóa", "NGUOIDUNG", model.getValueAt(i,
	 * 0).toString().trim()); Managed_User.delUser(id); }
	 * 
	 * }
	 */

	private void saveAction(String previousCm) {
		String id = this.view.getIdText().getText().trim();
		String name = this.view.getNameText().getText().trim();
		String year = this.view.getYearText().getText().trim();
		String relative = this.view.getRelative().getSelectedItem().toString().trim();
		String ward = this.view.getTown().getSelectedItem().toString().trim();
		String district = this.view.getDistrict().getSelectedItem().toString().trim();
		String city = this.view.getCity().getSelectedItem().toString().trim();
		String f = this.view.getStatus().getSelectedItem().toString().trim();
		String zoneName = this.view.getTreatment().getSelectedItem().toString().trim();
		
		User user = validateUser(name, id, year, ward, district, city, f, zoneName, relative, oldZone);
		// System.out.println(user.toString());
		if (user != null) {
			Managed_History.addManagerHistory(previousCm, "NGUOIDUNG", user.getId());
			if (previousCm.equals("Thêm")) {
				Managed_User.addUser(user);
				if (!user.getStatus().getF().equals("Khỏi bệnh")) {
					int receivedSlot = Managed_Zone.getReceivedSlot(user.getPlaceOfTreatment().getId()) + 1;
					Managed_Zone.updateReceivedSlot(user.getPlaceOfTreatment().getId(), receivedSlot);
				}
				JOptionPane.showMessageDialog(view, "Thêm thành công");
			} else {
				String newZone = user.getPlaceOfTreatment().getId();
				if (!oldZone.equals(newZone) && !oldZone.equals("") && !newZone.equals("")) {
					int newReceivedSlot = Managed_Zone.getReceivedSlot(newZone) + 1;
					int oldReceivedSlot = Managed_Zone.getReceivedSlot(oldZone) - 1;
					Managed_Zone.updateReceivedSlot(newZone, newReceivedSlot);
					Managed_Zone.updateReceivedSlot(oldZone, oldReceivedSlot);

				}

				if (oldZone.equals("") && !newZone.equals("")) {
					int newReceivedSlot = Managed_Zone.getReceivedSlot(newZone) + 1;
					Managed_Zone.updateReceivedSlot(newZone, newReceivedSlot);
				}

				if (!oldZone.equals("") && newZone.equals("")) {
					int oldReceivedSlot = Managed_Zone.getReceivedSlot(oldZone) - 1;
					Managed_Zone.updateReceivedSlot(oldZone, oldReceivedSlot);
				}

				Managed_User.modifyUser(user);
				JOptionPane.showMessageDialog(view, "Sửa thành công");
			}
			clearForm();
			disabledForm();
		} 
	}

	public User validateUser(String name, String id, String year, String ward, String district, String city, String f,
			String zoneName, String relative, String oldZoneId) {
		if (id.length() < 9 || id.length() > 12 || id.length() == 11) {
			JOptionPane.showMessageDialog(view, "CMND/CCCD là dãy 9/10/12 chữ số ");
			return null;
		}

		try {
			long temp = Long.parseLong(id);
//			System.out.println(temp);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "CMND/CCCD phải là dãy số");

			return null;
		}
		if (Managed_User.findById(id) != null && previousCm.equals("Thêm")) {
			JOptionPane.showMessageDialog(view, "CMND/CCCD đã tồn tại");
			return null;
		}

		if (name.length() == 0) {
			JOptionPane.showMessageDialog(view, "Chưa nhập tên người bị quản lí");
			return null;
		}
		int yob = -1;
		try {
			yob = Integer.parseInt(year);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Năm sinh không hợp lệ");
			return null;
		}

		if (yob <= Calendar.getInstance().get(Calendar.YEAR) - 200 || yob > Calendar.getInstance().get(Calendar.YEAR)) {
			JOptionPane.showMessageDialog(view, "Năm sinh không hợp lệ");
			return null;
		}
		if (ward.equals("Xã/Phường") || district.equals("Quận/Huyện") || city.equals("Tỉnh/Thành phố")) {
			JOptionPane.showMessageDialog(view, "Chưa chọn địa chỉ");
			return null;
		}
		Address addr = new Address(ward, district, city);
		if (f.equals("Status")) {
			JOptionPane.showMessageDialog(view, "Chưa chọn trạng thái");
			return null;
		}
		F status = null;
		if (f.equals("F0")) {
			status = F.F0;
		} else if (f.equals("F1")) {
			status = F.F1;
		} else if (f.equals("F2")) {
			status = F.F2;
		} else if (f.equals("F3")) {
			status = F.F3;
		} else if (f.equals("Khỏi bệnh")) {
			status = F.CURED;
		}
		if (zoneName.equals("Nơi điều trị/cách ly") && !f.equals("Khỏi bệnh")) {
			JOptionPane.showMessageDialog(view, "Chưa chọn nơi điều trị/cách ly!");
			return null;
		} else if (Managed_Zone.isFull(zoneName) && !Managed_Zone.getIdFromZoneName(zoneName).equals(oldZoneId) && !f.equals("Khỏi bệnh")) {
			JOptionPane.showMessageDialog(view, "Khu điều trị/cách ly đã đầy!");
			return null;
		}

		Zone zone = new Zone(Managed_Zone.getIdFromZoneName(zoneName));

		User relativeUser = new User("");
		if (!f.equals("F0") && !f.equals("Khỏi bệnh")) {
			long relativeId = 0;
			try {
				relativeId = Long.parseLong(relative);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(view, "Chọn số CMND/CCCD ở ô người liên quan");
				return null;
			}
			relativeUser = new User(relative);
		}
		return new User(name, id, yob, addr, status, zone, relativeUser);
	}

	private void enabledForm() {
		this.view.getIdText().setEditable(true);
		this.view.getNameText().setEditable(true);

		this.view.getYearText().setEditable(true);

		this.view.getTown().setEnabled(true);
		this.view.getDistrict().setEnabled(true);
		this.view.getCity().setEnabled(true);
		this.view.getStatus().setEnabled(true);

		String status = this.view.getStatus().getSelectedItem().toString().trim();
		if (status.equals("F0") || status.equals("Khỏi bệnh")) {
			this.view.getRelative().setEnabled(false);
		} else {
			this.view.getRelative().setEnabled(true);
		}
		if (status.equals("Khỏi bệnh")) {
			this.view.getTreatment().setEnabled(false);
		} else {
			this.view.getTreatment().setEnabled(true);
		}
	}

	private void disabledForm() {
		this.view.getIdText().setEditable(false);
		this.view.getNameText().setEditable(false);
		this.view.getYearText().setEditable(false);

		this.view.getTown().setEnabled(false);
		this.view.getDistrict().setEnabled(false);
		this.view.getCity().setEnabled(false);
		this.view.getStatus().setEnabled(false);
		this.view.getTreatment().setEnabled(false);
		this.view.getRelative().setEnabled(false);
	}

	private void clearForm() {
		this.view.getIdText().setText("");
		this.view.getNameText().setText("");
		this.view.getYearText().setText("");
		this.view.getRelative().setSelectedItem("");

		this.view.getCity().setSelectedIndex(0);
		this.view.getDistrict().setSelectedIndex(0);
		this.view.getTown().setSelectedIndex(0);
		this.view.getTreatment().setSelectedIndex(0);
		this.view.getStatus().setSelectedIndex(0);

		this.view.getSortGroup().clearSelection();
		this.view.getSearchIdText().setText("");
		this.view.getSearchNameText().setText("");
	}

	public void displayData() {
		int i = this.view.getTableListUser().getSelectedRow();
		// System.out.println(i);
		TableModel model = this.view.getTableListUser().getModel();

		this.view.getIdText().setText(model.getValueAt(i, 0).toString().trim());
		this.view.getNameText().setText(model.getValueAt(i, 1).toString().trim());
		this.view.getYearText().setText(model.getValueAt(i, 2).toString().trim());
		String status = model.getValueAt(i, 3).toString().trim();
		switch (status) {
		case "F0":
			this.view.getStatus().setSelectedIndex(1);
			break;
		case "F1":
			this.view.getStatus().setSelectedIndex(2);
			break;
		case "F2":
			this.view.getStatus().setSelectedIndex(3);
			break;
		case "F3":
			this.view.getStatus().setSelectedIndex(4);
			break;
		case "Khỏi bệnh":
			this.view.getStatus().setSelectedIndex(5);
			break;
		default:
			this.view.getStatus().setSelectedIndex(0);
			break;
		}
		if (model.getValueAt(i, 4) != null) {
			this.view.getRelative().addItem(model.getValueAt(i, 4).toString().trim());
			this.view.getRelative().setSelectedItem(model.getValueAt(i, 4).toString().trim());
		} else {
			this.view.getRelative().setSelectedItem("");
		}

		String[] addr = model.getValueAt(i, 5).toString().split(",");
		this.view.getCity().setSelectedItem(addr[2].trim());
		this.view.getDistrict().setSelectedItem(addr[1].trim());
		this.view.getTown().setSelectedItem(addr[0].trim());

		if (model.getValueAt(i, 6) != null) {
			String zoneName = Managed_Zone.getZoneNameFromId(model.getValueAt(i, 6).toString().trim());
			this.view.getTreatment().setSelectedItem(zoneName);
		} else {
			this.view.getTreatment().setSelectedItem("Nơi điều trị/cách ly");
		}
		disabledForm();

	}

	public void loadData() {
		this.view.getTableListUser().setModel(Managed_User.showListUser(this.view.initialRow()));
	}

	@SuppressWarnings("unchecked")
	public void loadDistrict() {
		// System.out.println("loadDistrict:");
		// System.out.println(this.getCity().getSelectedItem().toString().trim());
		this.view.getDistrict().setModel(new DefaultComboBoxModel(
				Managed_Address.getListDistrict(this.view.getCity().getSelectedItem().toString().trim())));
	}

	@SuppressWarnings("unchecked")
	public void loadTown() {
		// System.out.println("loadTown:");
		// System.out.println(this.getCity().getSelectedItem().toString().trim());
		// System.out.println(this.getDistrict().getSelectedItem().toString().trim());
		this.view.getTown()
				.setModel(new DefaultComboBoxModel(
						Managed_Address.getListWard(this.view.getCity().getSelectedItem().toString().trim(),
								this.view.getDistrict().getSelectedItem().toString().trim())));
	}

	@SuppressWarnings("unchecked")
	public void loadRelative(String st) {
		String status = "";
		if (st.equals("F1")) {
			status = "F0";
		} else if (st.equals("F2")) {
			status = "F1";
		} else if (st.equals("F3")) {
			status = "F2";
		}
		this.view.getRelative().setModel(new DefaultComboBoxModel(Managed_User.getListRelative(status)));

	}

}
