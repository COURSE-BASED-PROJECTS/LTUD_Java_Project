package test;

import javax.swing.UIManager;

import model.managed.Managed_User;
import utils.ModifyDataUser;
import view.LoginView;

public class mainTest {
	public static void main(String[] args) {
		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			LoginView lv = new LoginView();
			lv.setVisible(true);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
