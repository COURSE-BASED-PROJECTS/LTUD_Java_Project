package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.sql.Timestamp;

import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;

import model.AccountCurrent;
import model.Payment_History;
import model.managed.Managed_Account;
import model.managed.Managed_Package;
import model.managed.Managed_Payment;
import model.managed.Managed_User;
import view.User.PayDebitView;
import view.User.UserView;

public class PayDebitController implements ActionListener {
	static BufferedWriter writer;
	public PayDebitView view;

	public PayDebitController(PayDebitView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();

		if (cm.equals("Thoát")) {

			UserView uv = new UserView();
			uv.setVisible(true);
			view.dispose();

		} else if (cm.equals("Thanh toán")) {

			Payment_History paymentHistory = new Payment_History();
			paymentHistory.setCMND(AccountCurrent.getUsernameCurrent());
			paymentHistory.setDebit(Double.parseDouble(view.getDebitCurrentText().getText()));
			paymentHistory.setMinDebt(Double.parseDouble(view.getPaymentMinimumText().getText()));

			if (Double.parseDouble(view.getDebitCurrentText().getText()) == 0) {
				JOptionPane.showMessageDialog(view, "Không tồn tại khoản dư phải đóng", "Lỗi",
						JOptionPane.WARNING_MESSAGE, null);
			} else if (Double.parseDouble(view.getBalanceCurrentText().getText()) < Double
					.parseDouble(view.getPaymentMinimumText().getText())) {
				JOptionPane.showMessageDialog(view, "Số dư không đủ", "Lỗi", JOptionPane.WARNING_MESSAGE, null);
			} else if (Double.parseDouble(view.getPaymentMinimumText().getText()) < Double
					.parseDouble(view.getDebitCurrentText().getText()) * 0.1) {
				JOptionPane.showMessageDialog(view, "Vui lòng thanh toán ít nhất 10% dư nợ", "Lỗi",
						JOptionPane.WARNING_MESSAGE, null);
			} else {
				try {
					Double debt = paymentHistory.getDebit();
					Double min_debt = paymentHistory.getMinDebt();

					String code = paymentHistory.randomCodeHistory();
					paymentHistory.setMaGD(code);
					Timestamp tsmp = new Timestamp(System.currentTimeMillis());
					paymentHistory.setTime(tsmp);

					Socket sock = new Socket("localhost", 1314);
					writer = new BufferedWriter(new OutputStreamWriter(sock.getOutputStream()));

					writer.write(AccountCurrent.getUsernameCurrent());
					writer.write("\n");
					writer.write(code);
					writer.write("\n");
					writer.write(paymentHistory.getCMND());
					writer.write("\n");
					writer.write(String.valueOf(debt));
					writer.write("\n");
					writer.write(String.valueOf(min_debt));
					writer.write("\n");
					writer.write(tsmp.toString());
					writer.write("\n");
					writer.flush();

					ClientThread clientThread = new ClientThread(view, paymentHistory, sock);
					clientThread.start();

				} catch (IOException ex) {
					JOptionPane.showMessageDialog(null, "Cannot connect to server\nPlease check the server IP again!",
							"Error", 1);
					view.dispose();

				}
			}
		}
	}
}

class ClientThread extends Thread implements Runnable {
	PayDebitView pdview;
	Socket socket;
	Payment_History paymentHistory;

	public ClientThread(PayDebitView view, Payment_History payment, Socket sock) {
		this.pdview = view;
		this.socket = sock;
		this.paymentHistory = payment;
	}

	public void run() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			while ((reader.readLine()) != null) {

				if (Managed_Payment.insertPayment(paymentHistory)
						&& Managed_Payment.updateBalance(Double.parseDouble(pdview.getDebitCurrentText().getText()),
								Double.parseDouble(pdview.getBalanceCurrentText().getText()),
								Double.parseDouble(pdview.getPaymentMinimumText().getText()))) {

					JOptionPane.showConfirmDialog(pdview, "Đã thanh toán thành công", "Thanh toán thành công",
							JOptionPane.CLOSED_OPTION, 1);

					Managed_User.showPaymentUser(pdview.getDebitCurrentText(), pdview.getBalanceCurrentText(),
							Managed_Account.setAccount(AccountCurrent.getUsernameCurrent()));

					pdview.getPaymentMinimumText().setText("");
				} else {
					JOptionPane.showConfirmDialog(pdview, "Thanh toán không thành công", "Thanh toán không thành công",
							JOptionPane.CLOSED_OPTION, 0);
				}
			}
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Disconnected!", "Error", 1);
			pdview.dispose();
		}
	}
}
