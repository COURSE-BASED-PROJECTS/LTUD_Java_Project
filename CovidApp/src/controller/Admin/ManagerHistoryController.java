package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.managed.Managed_Account;
import view.Admin.AdminView;
import view.Admin.ManageAccount;
import view.Admin.ManagerHistory;

public class ManagerHistoryController implements ActionListener {
	public ManagerHistory view;

	public ManagerHistoryController(ManagerHistory view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		
		if (cm.equals("Thoát")) {
			view.dispose();
			
			ManageAccount ma = new ManageAccount();
			ma.setVisible(true);
		} 
	}
}