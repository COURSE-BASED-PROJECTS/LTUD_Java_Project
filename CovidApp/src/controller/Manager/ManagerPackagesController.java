package controller.Manager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import model.Package;
import model.managed.Managed_Package;
import utils.DatabaseConnect;
import view.LoginView;
import view.ManagerPackages;
import view.ManagerUsers;
import view.ManagerView;
import view.StatisticInfo;

public class ManagerPackagesController implements ActionListener {
	public ManagerPackages view;

	public ManagerPackagesController(ManagerPackages view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm != null) {
			view.setVisible(false);
		}
		if (cm.equals("Thoát")) {
			ManagerView mv = new ManagerView();
			mv.setVisible(true);
		} else if (cm.equals("Thêm")) {
			
		} else if (cm.equals("")) {
			
		} else if (cm.equals("")) {
			
		}
		
	}

}
