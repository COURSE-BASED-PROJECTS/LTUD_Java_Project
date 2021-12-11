package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import model.managed.Managed_Account;
import model.managed.Managed_User;
import utils.Password;
import view.User.UserInfoView;
import model.managed.Managed_User;

public class ChangePasswordController implements ActionListener {
	public ChangePasswordView view;

	public ChangePasswordController(ChangePasswordView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		System.out.println(cm);

		if (cm.equals("Thoát")) {
			this.view.dispose();
			UserInfoView uiv = new UserInfoView();
			uiv.setVisible(true);
		} else if (cm.equals("Đổi mật khẩu")) {
			changePassAction();
		}

	}

	private void changePassAction() {
		// Cần lấy được username từ User đổi mật khẩu
		String username = "a"; //****
		
		String oldPass = Password.encrypt(this.view.getOldPassText().getText().toString().trim());
		if (!Managed_Account.isAccount(username, oldPass)) {
			JOptionPane.showMessageDialog(view, "Sai mật khẩu cũ");
			return;
		}
		String newPass = Password.encrypt(this.view.getNewPassText().getText().toString().trim());
		String newAgainPass = Password.encrypt(this.view.getNewPassText().getText().toString().trim());
		
		if (newPass.equals(newAgainPass)) {
			Managed_Account.changePassword(username, newPass);
			JOptionPane.showMessageDialog(view, "Đổi mật khẩu thành công");
			this.view.getOldPassText().setText("");
			this.view.getNewPassText().setText("");
			this.view.getNewPassAgainText().setText("");
		} else {
			JOptionPane.showMessageDialog(view, "Nhập lại mật khẩu mới không đúng");
		}
		
	}

}
