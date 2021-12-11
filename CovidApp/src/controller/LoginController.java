package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import model.AccountCurrent;
import model.managed.Managed_Account;
import utils.DatabaseConnect;
import view.AdminView;
import view.LoginView;
import view.ManagerView;
import view.UserView;

public class LoginController implements ActionListener {
	public LoginView view;

	public LoginController(LoginView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		boolean found = false;
		String role = null;
		if (cm.equals("Đăng nhập")) {
			String username = view.getAccount().getUserName();
			String password = view.getAccount().getPassword();
			//System.out.println("TK: " + username);
			//System.out.println("MK: " + password);
			// Kiểm tra tài khoản
			try {
				Connection con = DatabaseConnect.openConnection();
				String sql = "Select * From TAIKHOAN WHERE TAIKHOAN="+username;
				ResultSet rs = DatabaseConnect.getResultSet(con, sql);
				int numberColumn = rs.getMetaData().getColumnCount();
				if(numberColumn == 0) {
					JOptionPane.showMessageDialog(view, "lỗi", "Không có tài khoản trong hệ thống", JOptionPane.ERROR_MESSAGE, null);
				}
				
				
				Vector<String> row = null;

				while (rs.next()) {
					row = new Vector<String>();

					for (int i = 1; i <= numberColumn; i++)
						row.addElement(rs.getString(i));
					
					if(row.get(0) == null) {
						JOptionPane.showMessageDialog(view, "lỗi", "Vui lòng nhập tài khoản", JOptionPane.ERROR_MESSAGE, null);
					}
					else if(row.get(1) == null) {
						found = false;
						role = row.get(2).trim();
						
						JPasswordField pf = new JPasswordField();
						int okCxl = JOptionPane.showConfirmDialog(view, pf, "Vui lòng đổi mật khẩu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
						
						String password_new="";
						if (okCxl == JOptionPane.OK_OPTION) {
						  password_new = new String(pf.getPassword());
						}
						
						Managed_Account.changePassword(username,password_new);
						break;
					}
					else if(row.get(0).trim().equals(username) && row.get(1).trim().equals(password)) {
						found = true;
						role = row.get(2).trim();
						break;
					}
				}
			} catch (SQLException e1) {
				System.out.println("Lỗi đăng nhập");
				e1.printStackTrace();
			}
			if (found) {
				view.dispose();
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
					AccountCurrent.setUsernameCurrent(view.get_username());
					uv.setVisible(true);
					break;
				default:
					break;
				}
			} else {
//				JOptionPane.showMessageDialog(view, "Đăng nhập thất bại");
			}

		}
	}

}
