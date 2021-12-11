package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Vector;

import model.Address;
import model.F;
import model.User;
import model.Zone;
import model.managed.Managed_User;

public class ModifyDataUser {
	public static Managed_User findAll() {
		Managed_User mu = new Managed_User();
		try {
			Connection con = DatabaseConnect.openConnection();
			String sql = "Select * From NGUOIDUNG";
			ResultSet rs = DatabaseConnect.getResultSet(con, sql);
			int numberColumn = rs.getMetaData().getColumnCount();

			User user = null;
			Vector<String> row = null;

			while (rs.next()) {
				row = new Vector<String>();

				for (int i = 1; i <= numberColumn; i++)
					row.addElement(rs.getString(i));
				String id = row.get(0).trim();
				String name = row.get(1).trim();
				int yearOfBirth = Integer.valueOf(row.get(2));
				F status = null;
				String f = row.get(3).trim();
				if (f.equals("F0")) {
					status = F.F0;
				} else if (f.equals("F1")) {
					status = F.F1;
				} if (f.equals("F2")) {
					status = F.F2;
				}
				User relative = new User(row.get(4).trim());
				Address address = new Address(row.get(5).trim(),row.get(6).trim(),row.get(7).trim());
				Zone placeOfTreatment = new Zone(row.get(8).trim());
//				user = new User(name, id, yearOfBirth, address, status, placeOfTreatment, relative);
				mu.addUser(user);
			}
		} catch (SQLException e1) {
			System.out.println("Lỗi trong khi load dữ liệu từ bảng NGUOIDUNG");
			e1.printStackTrace();
		}
		return mu;
	}
}
