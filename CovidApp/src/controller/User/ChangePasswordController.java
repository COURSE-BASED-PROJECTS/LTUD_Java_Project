package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.AccountCurrent;
import model.managed.Managed_Account;
import utils.Password;
import view.User.ChangePasswordView;
import view.User.UserView;

public class ChangePasswordController implements ActionListener {
	public ChangePasswordView view;

	public ChangePasswordController(ChangePasswordView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();

		if (cm.equals("Thoát")) {
			this.view.dispose();
			UserView uv = new UserView();
			uv.setVisible(true);
		} else if (cm.equals("Đổi mật khẩu")) {
			changePassAction();
		}

	}

	private void changePassAction() {
		String username = AccountCurrent.getUsernameCurrent();
		
		String oldPass = Password.encrypt(this.view.getOldPassText().getText().toString().trim());
		if (!Managed_Account.isAccount(username, oldPass)) {
			JOptionPane.showMessageDialog(view, "Sai mật khẩu cũ");
			return;
		}
		String newPass = Password.encrypt(this.view.getNewPassText().getText().toString().trim());
		String newAgainPass = Password.encrypt(this.view.getNewPassText().getText().toString().trim());
		if (oldPass.equals(newPass)) {
			JOptionPane.showMessageDialog(view, "Mật khẩu mới không được trùng mật khẩu cũ");
			return;
		}
		if (newPass.equals(newAgainPass)) {
			Managed_Account.changePassword(username, newPass);
			JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công");
			this.view.dispose();
			UserView uv = new UserView();
			uv.setVisible(true);
		} else {
			JOptionPane.showMessageDialog(view, "Nhập lại mật khẩu mới không đúng");
		}
		
	}

}
