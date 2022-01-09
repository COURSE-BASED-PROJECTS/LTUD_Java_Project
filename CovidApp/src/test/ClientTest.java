package test;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import model.Account;
import model.Role;
import model.managed.Managed_Account;
import view.LoginView;

public class ClientTest {
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
						
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
