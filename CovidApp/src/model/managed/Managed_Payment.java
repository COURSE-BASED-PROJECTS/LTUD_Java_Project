package model.managed;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import utils.DatabaseConnect;

public class Managed_Payment extends Managed_General{
	public static DefaultTableModel showListHistory(DefaultTableModel tableModel) {
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From LSTHANHTOAN";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			while (rs.next()) {
				Vector<String> row = new Vector<String>();
				
				for (int i = 1; i <= numberColumn; i++) {
						row.addElement(rs.getString(i));
				}
				
				tableModel.addRow(row);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng LSTHANHTOAN");
			e1.printStackTrace();
		}
		return tableModel;
	}
}
