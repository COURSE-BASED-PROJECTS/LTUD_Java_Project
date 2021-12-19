package test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import PaymentSystem.PaymentSystemView;
import model.Account;
import model.Role;
import model.managed.Managed_Account;
import utils.ServerThread;
import view.LoginView;

public class mainTest {
	public static void main(String[] args) {
		try {
//		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			if (Managed_Account.isEmpty()) {
				String usernameAdmin = JOptionPane.showInputDialog("Tạo tên đăng nhập admin:");
				String passwordAdmin = JOptionPane.showInputDialog("Mật khẩu:");
				//System.out.println(usernameAdmin);
				//System.out.println(passwordAdmin);
				Account acc = new Account(usernameAdmin, passwordAdmin, Role.ADMIN_COVID);
				Managed_Account.addAccount(acc);
			}
			LoginView lv = new LoginView();
			lv.setVisible(true);
			
			//ServerThread.startServer();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
