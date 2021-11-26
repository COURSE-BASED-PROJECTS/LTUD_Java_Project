package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.managed.Managed_Package;
import utils.DatabaseConnect;
import view.LoginView;
import view.ManagerPackages;
import view.ManagerUsers;
import view.ManagerView;
import view.StatisticInfo;

public class ManagerController implements ActionListener {
	public ManagerView view;

	public ManagerController(ManagerView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
			view.setVisible(false);
		}
		if (cm.equals("Đăng xuất")) {
			LoginView lv = new LoginView();
			lv.setVisible(true);
		} else if (cm.equals("Quản lí người dùng")) {
			ManagerUsers mu = new ManagerUsers();
			mu.setVisible(true);
			
		} else if (cm.equals("Quản lí nhu yếu phẩm")) {
			ManagerPackages mp = new ManagerPackages();
			mp.setVisible(true);
		} else if (cm.equals("Thống kê thông tin")) {
			StatisticInfo si = new StatisticInfo();
			si.setVisible(true);
		}
		
	}

}
