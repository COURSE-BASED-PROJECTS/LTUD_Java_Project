package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Admin.AdminView;
import view.Admin.ManageAccount;

public class ManageAccountController implements ActionListener {
	public ManageAccount view;

	public ManageAccountController(ManageAccount view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
			view.setVisible(false);
		}
		if (cm.equals("Thoát")) {
			AdminView av = new AdminView();
			av.setVisible(true);
		} else if (cm.equals("")) {
			
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
