package test;

import javax.swing.UIManager;

import view.LoginView;

public class mainTest {
	public static void main(String[] args) {
		try {
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginView();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
