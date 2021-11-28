package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Manager.ManagerUsers;
import view.Manager.ManagerView;

public class ManagerUsersController implements ActionListener {
	public ManagerUsers view;

	public ManagerUsersController(ManagerUsers view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm != null) {
//			view.setVisible(false);
			view.dispose();
		}
		if (cm.equals("Thoát")) {
			ManagerView mv = new ManagerView();
			mv.setVisible(true);
		} else if (cm.equals("Thêm")) {
			System.out.println(view.getIdText());
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
