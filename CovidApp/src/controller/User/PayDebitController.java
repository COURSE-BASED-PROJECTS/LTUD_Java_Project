package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import model.AccountCurrent;
import model.Payment_History;
import model.managed.Managed_Account;
import model.managed.Managed_Package;
import model.managed.Managed_Payment;
import model.managed.Managed_User;
import view.PayDebitView;
import view.UserView;


public class PayDebitController implements ActionListener {
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
			
			if(Double.parseDouble(view.getDebitCurrentText().getText())==0) {
				JOptionPane.showMessageDialog(view, "Không tồn tại khoản dư phải đóng", "Lỗi", JOptionPane.WARNING_MESSAGE, null);
			}
			else if(Double.parseDouble(view.getBalanceCurrentText().getText())< Double.parseDouble(view.getPaymentMinimumText().getText())) {
				JOptionPane.showMessageDialog(view, "Số dư không đủ", "Lỗi", JOptionPane.WARNING_MESSAGE, null);
			}
			else if(Double.parseDouble(view.getPaymentMinimumText().getText())<Double.parseDouble(view.getDebitCurrentText().getText())*0.1) {
				JOptionPane.showMessageDialog(view, "Vui lòng thanh toán ít nhất 10% dư nợ", "Lỗi", JOptionPane.WARNING_MESSAGE, null);
			}
			else {
				if(Managed_Payment.insertPayment(paymentHistory) && Managed_Payment.updateBalance(Double.parseDouble(view.getDebitCurrentText().getText()), 
					Double.parseDouble(view.getBalanceCurrentText().getText()), Double.parseDouble(view.getPaymentMinimumText().getText()))) {
						JOptionPane.showConfirmDialog(view, "Đã thanh toán thành công", "Thanh toán thành công", JOptionPane.CLOSED_OPTION, 1);
						Managed_User.showPaymentUser(view.getDebitCurrentText(), view.getBalanceCurrentText(), Managed_Account.setAccount(AccountCurrent.getUsernameCurrent()));
						view.getPaymentMinimumText().setText("");
				}
				else {
					JOptionPane.showConfirmDialog(view, "Thanh toán không thành công", "Thanh toán không thành công", JOptionPane.CLOSED_OPTION, 0);
				}
			}
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
