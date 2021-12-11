package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.RowIdLifetime;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.lang.model.type.NullType;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableModel;

import model.Account;
import model.Address;
import model.F;
import model.User;
import model.Zone;
import utils.DatabaseConnect;

public class Managed_User {
	private ArrayList<User> listUser;

	public Managed_User(ArrayList<User> listUser) {
		this.listUser = listUser;
	}

	public Managed_User() {
		listUser = new ArrayList<User>();
	}
	
	public static void showPaymentUser(JLabel debitCurrentText, JLabel balanceCurrentText, Account account) {
		debitCurrentText.setText(account.getDebit()==null?"0":account.getDebit());
		balanceCurrentText.setText(account.getBalance()==null?"0":account.getBalance());
	}
	
	public static DefaultTableModel showListUser(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				String addr = "";
				for (int i = 1; i <= numberColumn; i++) {
						row.addElement(addr);
				}
				
				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return tableModel;
	}

	public static User setUser(String id) {
		
		User user = new User(id);
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE NGUOIDUNG.CMND='"+id+"'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		
		user.setName(row.get(1));
		user.setYearOfBirth(Integer.valueOf(row.get(2)));
		user.setStatus(row.get(3).contains("F2")?F.F2:(row.get(3).contains("F1")?F.F1:F.F0));
		user.setAddress(new Address(row.get(5), row.get(6), row.get(7)));
		user.setPlaceOfTreatment(Managed_User.LockDownPlace(row.get(8)));
		user.setRelative(searchNameRelativeById(row.get(4)));
		return user;
	}
	
	public static String searchNameRelativeById(String id) {
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG WHERE NGUOIDUNG.CMND='"+id+"'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		
		return row.get(1);
		
	}
	
	public static Zone LockDownPlace(String idLockDown) {
		Zone zone = new Zone(idLockDown); 
		
		Vector<String> row = new Vector<String>();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From KHUCACHLY WHERE KHUCACHLY.MAKCL='"+idLockDown+"'";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();
			
			while (rs.next()) {
				for (int i = 1; i <= numberColumn; i++) {
					row.addElement(rs.getString(i));
				}
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng KHUCACHLY");
			e1.printStackTrace();
		}
		
		zone.setName(row.get(1));
		zone.setCapacity(Integer.valueOf(row.get(2)));
		zone.setEmptySlot(Integer.valueOf(row.get(3)));
		
		return zone;
		
	}
	
	public void viewDetailUser(User user) {

	}

	public User searchById(String id) {
		return null;
	}

	public User searchByName(String name) {
		return null;
	}

	public void sortBy(String type) {

	}

	public void addUser(User u) {
		this.listUser.add(u);
	}

	public void updateUser(User user) {

	}

	public void delUser(User user) {

	}

	public void updateZone(User user, Zone newZone) {

	}

	public ArrayList<User> getListUser() {
		return listUser;
	}

	public void setListUser(ArrayList<User> listUser) {
		this.listUser = listUser;
	}
	

}
