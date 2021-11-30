package controller.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.managed.Managed_Package;
import view.BuyPackageView;
import view.UserView;

public class BuyPackageController implements ActionListener {
	public BuyPackageView view;

	public BuyPackageController(BuyPackageView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
		//System.out.println(cm);
		if (cm.equals("Thoát")) {
			UserView uv = new UserView();
			uv.setVisible(true);
		} else if (cm.equals("Sắp xếp giá tăng")) {
			view.getTableListPackage().setModel(Managed_Package.showPackages(view.getDefaultTableModel(),true,true));
		} else if (cm.equals("Sắp xếp giá giảm")) {
			view.getTableListPackage().setModel(Managed_Package.showPackages(view.getDefaultTableModel(),false,true));
		} else if (cm.equals("Lọc")) {
			if(view.getRadio().isSelected()) {
				view.getTableListPackage().setModel(Managed_Package.filterPackages(view.getDefaultTableModel(),1));
			}
			else if(view.getRadio_1().isSelected()) {
				view.getTableListPackage().setModel(Managed_Package.filterPackages(view.getDefaultTableModel(),2));
			}
			else if(view.getRadio_2().isSelected()){
				view.getTableListPackage().setModel(Managed_Package.filterPackages(view.getDefaultTableModel(),3));
			}
			else {
				JOptionPane.showMessageDialog(view, "Please choose 1 criteria to filter", "Invalid chooser", JOptionPane.WARNING_MESSAGE, null);
			}
		}  else if (cm.equals("Tìm kiếm")) {
			if(!view.getSearchPackageText().getText().equals("")) {
				view.getTableListPackage().setModel(Managed_Package.searchPackage(view.getDefaultTableModel(),view.getSearchPackageText().getText()));
				view.getSearchPackageText().setText(null);
			}
			else {
				JOptionPane.showMessageDialog(view, "Please enter name of the package", "Invalid", JOptionPane.WARNING_MESSAGE, null);
			}
			
		}
		
	}

}
