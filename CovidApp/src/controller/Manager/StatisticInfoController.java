package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.Manager.ManagerView;
import view.Manager.StatisticInfo;

public class StatisticInfoController implements ActionListener {
	public StatisticInfo view;

	public StatisticInfoController(StatisticInfo view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
			//view.setVisible(false);
			view.dispose();
		}
		if (cm.equals("Thoát")) {
			ManagerView mv = new ManagerView();
			mv.setVisible(true);
		} 		
	}

}
