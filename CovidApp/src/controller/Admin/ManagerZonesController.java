package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import model.Address;
import model.F;
import model.User;
import model.Zone;
import model.managed.Managed_Account;
import model.managed.Managed_User;
import model.managed.Managed_Zone;
import view.Admin.AdminView;
import view.Admin.ManagerZones;

public class ManagerZonesController implements ActionListener {
	private String previousCm = null;
	public ManagerZones view;

	public ManagerZonesController(ManagerZones view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		System.out.println(cm);
		if (cm.equals("Thoát")) {
			view.dispose();

			AdminView av = new AdminView();
			av.setVisible(true);
			
		} else if (cm.equals("Thêm")) {
			this.previousCm = cm;
			this.view.getIdText().setEditable(true);
			enabledForm();
			clearForm();
			
		} else if (cm.equals("Xóa")) {
			this.previousCm = cm;
			int i = this.view.getTableListZone().getSelectedRow();

			if (i == -1) {
				JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để xoá");
			} else {
				delAction();
				clearForm();
				this.view.loadData();
			}
			
		} else if (cm.equals("Sửa")) {
			this.previousCm = cm;
			int i = this.view.getTableListZone().getSelectedRow();

			if (i == -1) {
				JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để sửa");
			} else {
				enabledForm();
			}
			
		} else if (cm.equals("Đặt lại")) {
			clearForm();
			this.view.loadData();
			
		} else if (cm.equals("Lưu")) {
			saveAction(previousCm);
			clearForm();
			this.view.loadData();
		}
	}
	private void enabledForm() {
		this.view.getNameZoneText().setEditable(true);
		this.view.getLimitText().setEditable(true);
		this.view.getSlotText().setEditable(true);
	}
	private void disabledForm() {
		this.view.getIdText().setEditable(false);
		this.view.getNameZoneText().setEditable(false);
		this.view.getLimitText().setEditable(false);
		this.view.getSlotText().setEditable(false);
	}
	private void clearForm() {
		this.view.getIdText().setText(null);
		this.view.getNameZoneText().setText(null);
		this.view.getLimitText().setText(null);
		this.view.getSlotText().setText(null);
	}
	
	private void saveAction(String previousCm) {
		String id = this.view.getIdText().getText().trim();
		String name = this.view.getNameZoneText().getText().trim();
		String limit = this.view.getLimitText().getText().trim();
		String received = this.view.getSlotText().getText().trim();

		Zone zone = validateZone(id, name, limit, received);

		if (zone != null) {
			if (previousCm.equals("Thêm")) {
				Managed_Zone.addZone(zone);
				JOptionPane.showMessageDialog(view, "Thêm Khu cách ly mới thành công!");

			} else {
				int i = this.view.getTableListZone().getSelectedRow();
				TableModel model = this.view.getTableListZone().getModel();
				String idModify = model.getValueAt(i, 0).toString().trim();
				Managed_Zone.updateZone(zone, idModify);

				JOptionPane.showMessageDialog(view, "Cập nhật thành công!");

			}
		}
	}
	public Zone validateZone(String name, String id, String limit, String received) {
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(view, "Mã KCL không được trống");
			return null;
		}
		if (name.length() == 0) {
			JOptionPane.showMessageDialog(view, "Chưa nhập tên khu cách ly");
			return null;
		}
		int capacity = -1;
		try {
			capacity = Integer.parseInt(limit);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Sức chứa phải là số nguyên dương (>0)");
			return null;
		}
		if (capacity <= 0) {
			JOptionPane.showMessageDialog(view, "Sức chứa không hợp lệ");
			return null;
		}
		
		int receivedSlot = -1;
		try {
			receivedSlot = Integer.parseInt(received);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(view, "Số lượng đã tiếp nhận phải là số nguyên dương (>0)");
			return null;
		}
		if (receivedSlot < 0) {
			JOptionPane.showMessageDialog(view, "Số lượng đã tiếp nhận không hợp lệ");
			return null;
		}
		if (receivedSlot > capacity) {
			JOptionPane.showMessageDialog(view, "Số lượng đã tiếp nhận không vượt quá sức chứa");
			return null;
		}
		
		return new Zone(name, id, capacity, receivedSlot);
	}
	//DELETE ZONE
	private void delAction() {
		String id = this.view.getIdText().getText().toString().trim();
		
		if(Managed_Zone.isEmpty(id)) {
			String ops[] = { "Có", "Không", "Thoát" };
			int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn xóa?", "Xác nhận xóa", JOptionPane.NO_OPTION,
					JOptionPane.PLAIN_MESSAGE, null, ops, "Không");
			if (op == 0) {
				Managed_Zone.delZone(id);
			}

			JOptionPane.showMessageDialog(view, "Xoá KCL thành công");
		}
		else {
			JOptionPane.showMessageDialog(view, "Không thể xoá KHU CÁCH LY đang tiếp nhận người");
		}
	}	

	public void displayData() {
		disabledForm();
		int i = this.view.getTableListZone().getSelectedRow();
		TableModel model = this.view.getTableListZone().getModel();

		this.view.getIdText().setText(model.getValueAt(i, 0).toString().trim());
		this.view.getNameZoneText().setText(model.getValueAt(i, 1).toString().trim());
		this.view.getLimitText().setText(model.getValueAt(i, 2).toString().trim());
		this.view.getSlotText().setText(model.getValueAt(i, 3).toString().trim());
	}
}