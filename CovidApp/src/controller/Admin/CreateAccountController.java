package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
			Account acc = new Account();
			acc.setUserName(view.getUsernameText().getText());
			acc.setPassword(view.getPassText().getText());

			if (view.getRole() == 0)
				acc.setRole(Role.MANAGER);
			else
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