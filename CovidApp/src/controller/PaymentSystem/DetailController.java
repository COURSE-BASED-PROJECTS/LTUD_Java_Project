package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.DetailView;
import PaymentSystem.PaymentSystemView;

public class DetailController implements ActionListener {
	public DetailView view;

	public DetailController(DetailView view) {
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