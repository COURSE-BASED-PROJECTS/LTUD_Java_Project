package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Manager.ManagerPackages;
import view.Manager.ManagerView;

public class ManagerPackagesController implements ActionListener {
	public ManagerPackages view;

	public ManagerPackagesController(ManagerPackages view) {
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
			ManagerView mv = new ManagerView();
			mv.setVisible(true);
		} else if (cm.equals("Thêm")) {
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
