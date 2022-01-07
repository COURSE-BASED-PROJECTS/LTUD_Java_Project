package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import javax.swing.JOptionPane;

import PaymentSystem.CreateUserPaymentView;
import PaymentSystem.DetailView;
import PaymentSystem.PaymentSystemView;
import model.AccountCurrent;
import model.Payment_History;
import model.managed.Managed_Account;
import model.managed.Managed_Payment;
import model.managed.Managed_User;
import view.LoginView;
import view.User.PayDebitView;

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
			
		}else if (cm.equals("Lịch sử thanh toán")) {
			this.view.dispose();
			DetailView dv = new DetailView();
			dv.setVisible(true);
		}
	}
}