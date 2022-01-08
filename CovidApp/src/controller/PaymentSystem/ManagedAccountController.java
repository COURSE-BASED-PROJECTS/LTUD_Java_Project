package controller.PaymentSystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.TableModel;

import PaymentSystem.ManagedAccountView;
import PaymentSystem.PaymentSystemView;
import model.managed.Managed_Payment;
import model.managed.Managed_PaymentSystem;

public class ManagedAccountController implements ActionListener {
	private String previousCm = null;
	public ManagedAccountView view;

	public ManagedAccountController(ManagedAccountView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String cm = e.getActionCommand();
		if (cm.equals("Thoát")) {
			
			this.view.dispose();
			PaymentSystemView ps = new PaymentSystemView();
			ps.setVisible(true);
			
		}else if (cm.equals("Lưu")) {
			this.previousCm = cm;
			int i = this.view.getTableManaged().getSelectedRow();

			if (i == -1) {
				JOptionPane.showMessageDialog(view, "Chưa chọn đối tượng để cập nhật");
			} else {
				saveAction(previousCm);
				clearForm();
				JOptionPane.showMessageDialog(view, "Cập nhật thành công!");
				this.view.loadData();
			}	
			
		}
	}
	private void enabledForm() {
		this.view.getBalanceText().setEditable(true);
	}
	private void disabledForm() {
		this.view.getUsernameText().setEditable(false);
	}
	private void clearForm() {
		this.view.getUsernameText().setText(null);
		this.view.getBalanceText().setText(null);
	}
	
	private void saveAction(String previousCm) {
		String username = this.view.getUsernameText().getText().trim();
		String balance = this.view.getBalanceText().getText().trim();

		String bln = validateBalance(balance);

		if (bln != null) {
				Managed_PaymentSystem.updateBalance(Double.parseDouble(balance), username);				
				Managed_Payment.setNewBalance(Double.parseDouble(balance), username);
		}
		else {
			JOptionPane.showMessageDialog(view, "Cập nhật không thành công!");
			return;
		}
	}
	
	public String validateBalance(String balance) {
		if (Double.parseDouble(balance) < 0.0) {
			JOptionPane.showMessageDialog(view, "Số dư không được bé hơn 0");
			return null;
		}
		return balance;
	}
	
	public void displayData() {
		disabledForm();
		enabledForm();

		int i = this.view.getTableManaged().getSelectedRow();
		TableModel model = this.view.getTableManaged().getModel();

		this.view.getUsernameText().setText(model.getValueAt(i, 0).toString().trim());
		this.view.getBalanceText().setText(model.getValueAt(i, 3).toString().trim());
	}
}