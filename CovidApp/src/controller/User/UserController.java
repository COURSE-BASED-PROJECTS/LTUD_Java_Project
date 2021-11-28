package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.User.BuyPackageView;
import view.User.PayDebitView;
import view.User.UserInfoView;
import view.User.UserView;

public class UserController implements ActionListener {
	public UserView view;

	public UserController(UserView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
//			view.setVisible(false);
			view.dispose();
		}
		if (cm.equals("Đăng xuất")) {
			LoginView lv = new LoginView();
			lv.setVisible(true);
		} else if (cm.equals("Thông tin cá nhân")) {
			UserInfoView uiv = new UserInfoView();
			uiv.setVisible(true);
			
		} else if (cm.equals("Mua nhu yếu phẩm")) {
			BuyPackageView bpv = new BuyPackageView();
			bpv.setVisible(true);
		} else if (cm.equals("Thanh toán")) {
			PayDebitView pdv = new PayDebitView();
			pdv.setVisible(true);
		}
		
	}

}
