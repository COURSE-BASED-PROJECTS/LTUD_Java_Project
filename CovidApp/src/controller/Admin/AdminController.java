package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AdminView;
import view.CreateAccount;
import view.LoginView;
import view.ManageAccount;
import view.ManagerPackages;
import view.ManagerUsers;
import view.ManagerZones;
import view.StatisticInfo;

public class AdminController implements ActionListener {
	public AdminView view;

	public AdminController(AdminView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
//			view.setVisible(false);
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
