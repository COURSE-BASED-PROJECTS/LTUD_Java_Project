package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

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
		if (cm.equals("Login")) {
			String username = view.getAccount().getUserName();
			String password = view.getAccount().getPassword();
			//System.out.println("TK: " + username);
			//System.out.println("MK: " + password);
			// Kiểm tra tài khoản
			try {
				Connection con = DatabaseConnect.openConnection();
				String sql = "Select * From TAIKHOAN";
				ResultSet rs = DatabaseConnect.getResultSet(con, sql);
				int numberColumn = rs.getMetaData().getColumnCount();

				Vector<String> row = null;

				while (rs.next()) {
					row = new Vector<String>();

					for (int i = 1; i <= numberColumn; i++)
						row.addElement(rs.getString(i));

					if (row.get(0).trim().equals(username) && row.get(1).trim().equals(password)) {
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
				//System.out.println(role);
				view.setVisible(false);
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
