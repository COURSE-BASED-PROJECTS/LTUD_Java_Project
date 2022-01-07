package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import PaymentSystem.PaymentSystemView;
import controller.LoginController;
import model.Account;
import model.Role;
import model.managed.Managed_Account;
import view.Admin.AdminView;
import view.Admin.CreateAccount;

public class CreateAccountController implements ActionListener {
	public CreateAccount view;

	public CreateAccountController(CreateAccount view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();

		if (cm.equals("Thoát")) {
			view.dispose();

			AdminView av = new AdminView();
			av.setVisible(true);
			
		} else if (cm.equals("Tạo tài khoản")) {
			addAccountAction();
		}		
	}
	
	private void clearForm() {
		this.view.getUsernameText().setText(null);
		this.view.getPassText().setText(null);
	}
	
	private void addAccountAction() {
		Account acc = new Account();
		acc.setUserName(view.getUsernameText().getText());
		acc.setPassword(view.getPassText().getText());

		if (view.getRole() == 0)
			acc.setRole(Role.MANAGER);
		else
			acc.setRole(Role.USER);
		
		String username = acc.getUserName();
		
		if(Managed_Account.isExist(username)) {
			JOptionPane.showMessageDialog(view, "Tên tài khoản đã tồn tại!");
			clearForm();

			return;
		}
		else {
			Managed_Account.addAccount(acc);
			clearForm();
			JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công");
			
			this.view.dispose();
			AdminView av = new AdminView();
			av.setVisible(true);
		}
	}
}