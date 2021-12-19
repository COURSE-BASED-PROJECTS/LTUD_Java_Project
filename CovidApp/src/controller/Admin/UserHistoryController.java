package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Admin.ManageAccount;
import view.Admin.UserHistory;

public class UserHistoryController implements ActionListener {
	public UserHistory view;

	public UserHistoryController(UserHistory view) {
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