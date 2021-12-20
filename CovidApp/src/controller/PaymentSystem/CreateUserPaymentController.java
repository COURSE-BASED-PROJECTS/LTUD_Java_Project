package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.CreateUserPaymentView;
import PaymentSystem.PaymentSystemView;
import model.Account;
import model.Role;
import model.managed.Managed_Account;
import view.Admin.CreateAccount;

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
			Account acc = new Account();
			acc.setUserName(view.getUsernameText().getText());
			acc.setPassword(view.getPassText().getText());

			acc.setRole(Role.USER);
			
			Managed_Account.addAccount(acc);
			
			clearForm();
		}		
	}
	
	private void clearForm() {
		this.view.getUsernameText().setText(null);
		this.view.getPassText().setText(null);
	}
}