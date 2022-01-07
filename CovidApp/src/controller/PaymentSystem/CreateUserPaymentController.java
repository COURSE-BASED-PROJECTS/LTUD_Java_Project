package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import PaymentSystem.CreateUserPaymentView;
import PaymentSystem.PaymentSystemView;
import model.Account;
import model.AccountCurrent;
import model.Role;
import model.managed.Managed_Account;
import utils.Password;
import view.Admin.CreateAccount;
import view.User.UserView;

public class CreateUserPaymentController implements ActionListener {
	public CreateUserPaymentView view;

	public CreateUserPaymentController(CreateUserPaymentView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();

		if (cm.equals("Thoát")) {
			view.dispose();

			PaymentSystemView ps = new PaymentSystemView();
			ps.setVisible(true);
			
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
			PaymentSystemView ps = new PaymentSystemView();
			ps.setVisible(true);
		}
	}
}