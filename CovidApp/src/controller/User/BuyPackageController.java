package controller.User;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import model.AccountCurrent;
import model.Order_History;
import model.managed.Managed_Account;
import model.managed.Managed_Order;
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
			view.dispose();
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
			
		}else if (cm.equals("Mua")) {
			int quantity =0; 
			
			try {
				quantity = Integer.parseInt(JOptionPane.showInputDialog(view,"Nhập số lượng cần mua"));
				if(Integer.parseInt((String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),3))<quantity) {
					JOptionPane.showMessageDialog(view, "Vui lòng nhập số lượng nhỏ hơn hoặc bằng giới hạn", "Số lượng không đủ", JOptionPane.CLOSED_OPTION, null);
				}
				else {
					Order_History orderHistory  = new Order_History();
					orderHistory.setMaHD(Order_History.randomCodeHistory());
					orderHistory.setCMND(AccountCurrent.getUsernameCurrent());
					orderHistory.setQuantity(quantity);
					orderHistory.setType((String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),0));
					
					if(Managed_Order.insertBuyPackageHistory(orderHistory) && 
							Managed_Order.updateQuantityPackage((String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),0)
									, (String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),3)
									,String.valueOf(quantity))
							&& Managed_Order.updateDebitAccount(String.valueOf(quantity),
									(String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),4))) {
						JOptionPane.showConfirmDialog(view, "Đã mua thành công", "Mua hàng thành công", JOptionPane.CLOSED_OPTION, 1);
					}
					else {
						JOptionPane.showConfirmDialog(view, "Mua không thành công", "Mua hàng không thành công", JOptionPane.CLOSED_OPTION, 0);
					}
				}
				
			} catch (Exception e1) {
				if(view.getTableListPackage().getSelectedRow()==-1)
					JOptionPane.showMessageDialog(view, "Vui lòng chọn món hàng cần mua", "Chưa chọn nhu yếu phẩm", JOptionPane.CLOSED_OPTION, null);
				else
					try {
						if(quantity>Integer.valueOf(Managed_Order.getQuantity((String) view.getTableListPackage().getModel().getValueAt(view.getTableListPackage().getSelectedRow(),0)))) {
							JOptionPane.showMessageDialog(view, "Nhu yếu phẩm đã hết hạn", "Hết hạn", JOptionPane.CLOSED_OPTION, null);
						}
						else {
							JOptionPane.showMessageDialog(view, "Vui lòng nhập số lượng theo đúng định dạng", "Lỗi định dạng", JOptionPane.CLOSED_OPTION, null);
						}
					} catch (NumberFormatException e2) {
						e2.printStackTrace();
					} catch (HeadlessException e2) {
						e2.printStackTrace();
					} catch (SQLException e2) {
						e2.printStackTrace();
					}
					
			}
			view.getTableListPackage().setModel(Managed_Package.showPackages(view.getDefaultTableModel(),false,true));
		}
		
	}
	

}
