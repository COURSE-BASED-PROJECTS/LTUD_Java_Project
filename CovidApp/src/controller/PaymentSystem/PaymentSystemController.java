package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import PaymentSystem.CreateUserPaymentView;
import PaymentSystem.TransactionsView;
import PaymentSystem.ManagedAccountView;
import PaymentSystem.PaymentSystemView;
import view.LoginView;

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
			CreateUserPaymentView cup = new CreateUserPaymentView();
			cup.setVisible(true);
			
		}else if (cm.equals("Quản lý tài khoản")) {
			this.view.dispose();
			ManagedAccountView ma = new ManagedAccountView();
			ma.setVisible(true);
			
		}else if (cm.equals("Lịch sử thanh toán")) {
			this.view.dispose();
			TransactionsView dv = new TransactionsView();
			dv.setVisible(true);
		}
	}
}