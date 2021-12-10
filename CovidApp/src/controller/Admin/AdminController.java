package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.Admin.AdminView;
import view.Admin.CreateAccount;
import view.Admin.ManageAccount;
import view.Admin.ManagerZones;

public class AdminController implements ActionListener {
	public AdminView view;

	public AdminController(AdminView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm != null) {
			view.dispose();
		}
		if (cm.equals("Đăng xuất")) {
			LoginView lv = new LoginView();
			lv.setVisible(true);
			
		} else if (cm.equals("Tạo tài khoản")) {
			CreateAccount ca = new CreateAccount();
			ca.setVisible(true);
			
		} else if (cm.equals("Quản lí tài khoản")) {
			ManageAccount ma = new ManageAccount();
			ma.setVisible(true);
			
		} else if (cm.equals("Quản lí nơi điều trị/cách li")) {
			ManagerZones mz = new ManagerZones();
			mz.setVisible(true);
		}
		
	}

}
