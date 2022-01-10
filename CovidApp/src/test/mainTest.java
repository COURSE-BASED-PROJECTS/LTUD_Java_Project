package test;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Account;
import model.Role;
import model.managed.Managed_Account;
import utils.ServerThread;
import view.LoginView;

public class mainTest {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			if (Managed_Account.isEmpty()) {
				String usernameAdmin = JOptionPane.showInputDialog("Tạo tên đăng nhập admin:");
				String passwordAdmin = JOptionPane.showInputDialog("Mật khẩu:");

				Account acc = new Account(usernameAdmin, passwordAdmin, Role.ADMIN_COVID);
				Managed_Account.addAccount(acc);
			}
			LoginView lv = new LoginView();
			lv.setVisible(true);
			
			ServerThread.startServer();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
//TEST
// TÀI KHOẢN BỊ LOCK: 060699033017
// TÀI KHOẢN ĐÃ KÍCH HOẠT: 0312614186
// TÀI KHOẢN ADMIN: Admin_Covid
// TÀI KHOẢN ADMIN PAYMENT: Admin_Payment