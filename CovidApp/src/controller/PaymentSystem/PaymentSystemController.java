package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.PaymentSystemView;
import view.PayDebitView;
import view.UserView;

public class PaymentSystemController implements ActionListener {
	public PaymentSystemView view;

	public PaymentSystemController(PaymentSystemView view) {
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
