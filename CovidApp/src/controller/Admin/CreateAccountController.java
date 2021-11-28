package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		//System.out.println(cm);
		if (cm != null) {
			view.setVisible(false);
		}
		if (cm.equals("Thoát")) {
			AdminView av = new AdminView();
			av.setVisible(true);
		} else if (cm.equals("Tạo tài khoản")) {
			
		}		
	}

}
