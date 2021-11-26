package controller.Admin;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AdminView;
import view.ManagerZones;

public class ManagerZonesController implements ActionListener {
	public ManagerZones view;

	public ManagerZonesController(ManagerZones view) {
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
