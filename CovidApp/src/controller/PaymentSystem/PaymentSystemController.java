package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.PaymentSystemView;
import view.LoginView;
import view.Admin.CreateAccount;

public class PaymentSystemController implements ActionListener {
	public PaymentSystemView view;

	public PaymentSystemController(PaymentSystemView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		if (cm.equals("Thoát")) {
			
			this.view.dispose();
			
			LoginView lv = new LoginView();
			lv.setVisible(true);
			
		} else if (cm.equals("Thêm người được quản lí")) {
			CreateAccount ca = new CreateAccount();
			ca.setVisible(true);
			
		}else if (cm.equals("...")) {
			//Bảng tài khoản + bảng giao dịch của DB_Payment
		}
	}
}