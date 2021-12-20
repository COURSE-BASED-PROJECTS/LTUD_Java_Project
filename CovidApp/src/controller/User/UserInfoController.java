package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.User.UserInfoView;
import view.User.UserView;

public class UserInfoController implements ActionListener {
	public UserInfoView view;

	public UserInfoController(UserInfoView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm != null) {
			view.dispose();
		}
		if (cm.equals("Thoát")) {
			UserView uv = new UserView();
			uv.setVisible(true);
		} else if (cm.equals("")) {
			
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}
	
}
