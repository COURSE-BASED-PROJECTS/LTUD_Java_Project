package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.managed.Managed_Account;
import model.managed.Managed_Zone;
import view.Admin.AdminView;
import view.Admin.ManageAccount;

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
			clearForm();
			this.view.loadData();
			
		} else if (cm.equals("Xem lịch sử hoạt động")) {
			
			
		}
	}
	
	private void lockAction() {		
		String id = this.view.getTextField().getText();
		if (id.length() == 0) {
			JOptionPane.showMessageDialog(view, "Chưa nhập mã tài khoản");
		}
		String ops[] = { "Có", "Không", "Thoát" };
		int op = JOptionPane.showOptionDialog(view, "Bạn có chắc muốn khoá", "Xác nhận khoá", JOptionPane.NO_OPTION,
				JOptionPane.PLAIN_MESSAGE, null, ops, "Không");
		if (op == 0) {
			Managed_Account.lockAcc(id);
		}
	}
	
	private void clearForm() {
		this.view.getTextField().setText(null);
	}
}