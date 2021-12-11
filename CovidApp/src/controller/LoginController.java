package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.managed.Managed_Account;
import utils.DatabaseConnect;
import utils.Password;
import view.LoginView;
import view.Admin.AdminView;
import view.Manager.ManagerView;
import view.User.UserView;

public class LoginController implements ActionListener {
	public LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm.equals("Login")) {
			String username = this.view.getAccount().getUserName();
			String password = Password.encrypt(this.view.getAccount().getPassword());
			boolean found = Managed_Account.isAccount(username, password);
			if (found) {
				// System.out.println(role);
//				view.setVisible(false);
				view.dispose();
				String role = Managed_Account.getRole(username);
				switch (role) {
				case "QUANLY":
					ManagerView mv = new ManagerView();
					mv.setVisible(true);
					break;
				case "QUANTRI":
					AdminView av = new AdminView();
					av.setVisible(true);
					break;
				case "NGUOIDUNG":
					UserView uv = new UserView();
					uv.setVisible(true);
					break;
				default:
					break;
				}
			} else {
				JOptionPane.showMessageDialog(view, "Đăng nhập thất bại");
			}

		}
		
	}
}
