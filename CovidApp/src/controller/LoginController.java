package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import view.LoginView;

public class LoginController implements ActionListener{
	public LoginView view;
	public LoginController(LoginView view) {
		this.view = view;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		
		if (cm.equals("Login")) {
			String username = view.getAccount().getUserName();
			String password = view.getAccount().getUserName();
			
			if (username.equals("admin") && password.equals("admin")) {
				JOptionPane.showMessageDialog(view, "Đăng nhập thành công");
			} else {
				JOptionPane.showMessageDialog(view, "Đăng nhập thất bại");
			}
		}
		
	}

}
