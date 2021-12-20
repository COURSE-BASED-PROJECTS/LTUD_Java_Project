package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import model.managed.Managed_Account;
import model.managed.Managed_Zone;
import view.Admin.AdminView;
import view.Admin.ManageAccount;
import view.Admin.ManagerHistory;
import view.Admin.UserHistory;

public class ManageAccountController implements ActionListener {
	public ManageAccount view;

	public ManageAccountController(ManageAccount view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		
		if (cm.equals("Thoát")) {
			view.dispose();
			
			AdminView av = new AdminView();
			av.setVisible(true);
			
		} else if (cm.equals("Khóa tài khoản")) {
			lockAction();
			this.view.loadData();
			
		}  else if (cm.equals("Kích hoạt tài khoản")) {
			activeAction();
			this.view.loadData();
			
		}else if (cm.equals("Xem lịch sử hoạt động")) {
			viewHistoryAction();			
		}
	}
	
	private void viewHistoryAction() {
		int i = this.view.getAccountTable().getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(view, "Chưa chọn tài khoản để xem lịch sử hoạt động");
		} else {
			this.view.dispose();
			TableModel model = this.view.getAccountTable().getModel();
			String id = model.getValueAt(i, 0).toString().trim();
			String role = model.getValueAt(i, 2).toString().trim();
			if (role.equals("QUANLY")) {
				ManagerHistory mh = new ManagerHistory(id);
				mh.setVisible(true);
			} else if (role.equals("NGUOIDUNG")) {
				UserHistory uh = new UserHistory(id);
				uh.setVisible(true);
			}
		}
		
	}

	private void lockAction() {		
		int i = this.view.getAccountTable().getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(view, "Chưa chọn tài khoản để khoá");
			return;
		}
		TableModel model = this.view.getAccountTable().getModel();
		String id = model.getValueAt(i, 0).toString().trim();
		
		String ops[] = { "Có", "Không", "Thoát" };
		int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn khoá", "Xác nhận khoá", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, ops, "Không");
		if (op == 0) {
			Managed_Account.lockAcc(id);
		}
	}
	
	private void activeAction() {		
		int i = this.view.getAccountTable().getSelectedRow();
		if (i == -1) {
			JOptionPane.showMessageDialog(view, "Chưa chọn tài khoản để kích hoạt");
			return;
		}
		TableModel model = this.view.getAccountTable().getModel();
		String id = model.getValueAt(i, 0).toString().trim();
		
		String ops[] = { "Có", "Không", "Thoát" };
		int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn kích hoạt?", "Xác nhận kích hoạt", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, ops, "Không");
		if (op == 0) {
			Managed_Account.activeAcc(id);
		}
	}
}