package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Manager.ManagerUsers;
import view.Manager.UserInfoViewDetail;

public class DetailInfoController implements ActionListener {
	public UserInfoViewDetail view;

	public DetailInfoController(UserInfoViewDetail view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm.equals("Thoát")) {
			this.view.dispose();
			ManagerUsers mu = new ManagerUsers();
			mu.setVisible(true);
		} 
		
	}

}
