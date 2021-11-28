package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.User.PayDebitView;
import view.User.UserView;

public class PayDebitController implements ActionListener {
	public PayDebitView view;

	public PayDebitController(PayDebitView view) {
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
			UserView uv = new UserView();
			uv.setVisible(true);
		} else if (cm.equals("")) {
			
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
