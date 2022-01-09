package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.TransactionsView;
import PaymentSystem.PaymentSystemView;

public class TransactionsController implements ActionListener {
	public TransactionsView view;

	public TransactionsController(TransactionsView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm.equals("Thoát")) {
			
			this.view.dispose();
			PaymentSystemView ps = new PaymentSystemView();
			ps.setVisible(true);
		} 
	}
}