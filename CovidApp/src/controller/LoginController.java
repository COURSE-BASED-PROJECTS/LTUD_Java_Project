package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.AccountCurrent;
import model.managed.Managed_Account;
import utils.Password;
import utils.ServerThread;
import view.LoginView;
import view.Admin.AdminView;
import view.Manager.ManagerView;
import view.User.ChangePasswordView;
import view.User.UserView;

public class LoginController implements ActionListener {
	public LoginView view;
	static String username;
	String password;

	public static String getUsername() {
		return username;
	}
	
	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm.equals("Đăng nhập")) {
			
			username = this.view.getAccount().getUserName();
			password = Password.encrypt(this.view.getAccount().getPassword());
			
			boolean found = Managed_Account.isAccount(username, password);
			if (found) {
				view.dispose();
				String role = Managed_Account.getRole(username);
				String pass = Managed_Account.getPassword(username);
				AccountCurrent.setUsernameCurrent(username);
				
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
					if (pass.equals(Password.encrypt(username))) {
						ChangePasswordView cpv = new ChangePasswordView();
						cpv.setVisible(true);
						break;
					}
					UserView uv = new UserView();
					uv.setVisible(true);
					break;
				default:
					break;
				}
			} else if (username.equals("Admin_Payment")) {
				this.view.dispose();
//				if(ServerThread.serversocket.isClosed()) {
//					ServerThread.startServer();
//				}
				ServerThread.view.setVisible(true);
				
			}else {
				JOptionPane.showMessageDialog(view, "Tài khoản không tồn tại hoặc đã bị khóa");
			}

		}
		
	}
}
