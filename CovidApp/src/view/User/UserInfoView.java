package view.User;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.User.UserInfoController;
import model.AccountCurrent;
import model.User;
import model.managed.Managed_Account;
import model.managed.Managed_Order;
import model.managed.Managed_Payment;
import model.managed.Managed_Status;
import model.managed.Managed_User;

public class UserInfoView extends JFrame {

	private JPanel contentPane;
	private JTable tableManaged;
	private JTable tableConsume;
	private JTable tableDebit;
	private String [] columnNamesManaged = new String [] {"STT","CMND",
             "Trạng thái cũ", "Trạng thái mới", "Thời gian"};
	private String [] columnNamesComsume = new String [] {
            "Mã HD", "CMND", "Loại hàng","Số lượng", "Số tiền","Thời gian"};
	private String [] columnNamesDebit = new String [] {
            "Mã GD", "CMND", "Dư nợ","Số tiền trả","Thời gian"};
	private JLabel idText;
	private JLabel addrText;
	private JLabel debitLabel;
	private JLabel nameText;
	private JLabel idText_1;
	private JLabel statusText;
	private JLabel addrText_1;
	private JLabel nameText_1;
	private JLabel debitText;
	
	private static User user = null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoView frame = new UserInfoView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UserInfoView() {
		user = Managed_User.setUser(AccountCurrent.getUsernameCurrent());

		
		ActionListener action = new UserInfoController(this);
		setTitle("Thông tin");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1178, 731);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Info = new JPanel();
		Info.setBorder(new TitledBorder(null, "Th\u00F4ng tin ca\u0301 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Info.setBounds(10, 10, 1144, 203);
		contentPane.add(Info);
		Info.setLayout(null);
		
		JLabel idLabel = new JLabel("CMND:");
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setBounds(10, 36, 95, 35);
		Info.add(idLabel);
		
		idText = new JLabel("User_ID");
		idText.setHorizontalAlignment(SwingConstants.LEFT);
		idText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idText.setBounds(93, 36, 126, 35);
		
		Info.add(idText);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(212, 36, 95, 35);
		Info.add(nameLabel);
		
		nameText = new JLabel("User_Name");
		nameText.setHorizontalAlignment(SwingConstants.LEFT);
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameText.setBounds(295, 36, 209, 35);
		
		Info.add(nameText);
		
		JLabel yearLabel = new JLabel("Năm sinh:");
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLabel.setBounds(524, 36, 95, 35);
		Info.add(yearLabel);
		
		idText_1 = new JLabel("YearOfBirth");
		idText_1.setHorizontalAlignment(SwingConstants.LEFT);
		idText_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idText_1.setBounds(618, 36, 66, 35);
		
		Info.add(idText_1);
		
		JLabel addrLabel = new JLabel("Địa chỉ:");
		addrLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addrLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addrLabel.setBounds(10, 99, 95, 35);
		Info.add(addrLabel);
		
		addrText = new JLabel("User_Address");
		addrText.setHorizontalAlignment(SwingConstants.LEFT);
		addrText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addrText.setBounds(93, 99, 425, 35);
		
		Info.add(addrText);
		
		JLabel statusLabel = new JLabel("Trạng thái:");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(524, 99, 95, 35);
		Info.add(statusLabel);
		
		statusText = new JLabel("F");
		statusText.setHorizontalAlignment(SwingConstants.LEFT);
		statusText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusText.setBounds(618, 99, 66, 35);
		
		Info.add(statusText);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(694, 36, 18, 158);
		Info.add(separator_1);
		
		debitLabel = new JLabel("Dư nợ:");
		debitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		debitLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitLabel.setBounds(10, 159, 95, 35);
		Info.add(debitLabel);
		
		debitText = new JLabel("User_Debit");
		debitText.setHorizontalAlignment(SwingConstants.LEFT);
		debitText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitText.setBounds(93, 159, 126, 35);
		Info.add(debitText);
		
		JLabel lockdownLabel = new JLabel("Nơi điều trị:");
		lockdownLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lockdownLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lockdownLabel.setBounds(705, 36, 95, 35);
		Info.add(lockdownLabel);
		
		addrText_1 = new JLabel("User_Zone");
		addrText_1.setHorizontalAlignment(SwingConstants.LEFT);
		addrText_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addrText_1.setBounds(810, 36, 324, 35);
		
		Info.add(addrText_1);
		
		JLabel relateUser = new JLabel("Người liên quan:");
		relateUser.setHorizontalAlignment(SwingConstants.CENTER);
		relateUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relateUser.setBounds(705, 99, 126, 35);
		Info.add(relateUser);
		
		nameText_1 = new JLabel("User_Relative");
		nameText_1.setHorizontalAlignment(SwingConstants.LEFT);
		nameText_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameText_1.setBounds(827, 99, 216, 35);
		
		Info.add(nameText_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Li\u0323ch s\u01B0\u0309", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 223, 1144, 471);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel managedHistory = new JPanel();
		managedHistory.setBorder(new TitledBorder(null, "Li\u0323ch s\u01B0\u0309 qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		managedHistory.setBounds(10, 21, 1124, 119);
		panel.add(managedHistory);
		managedHistory.setLayout(null);
		
		JScrollPane scrollPaneManaged = new JScrollPane();
		scrollPaneManaged.setBounds(10, 22, 1104, 91);
		managedHistory.add(scrollPaneManaged);
		
		tableManaged = new JTable();
		scrollPaneManaged.setViewportView(tableManaged);
		tableManaged.setModel(initialRow(columnNamesManaged));
		tableManaged.setModel(Managed_Status.showHistoryStatus(user.getId(), initialRow(columnNamesManaged)));
		
		JPanel managedConsume = new JPanel();
		managedConsume.setLayout(null);
		managedConsume.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Li\u0323ch s\u01B0\u0309 ti\u00EAu thu\u0323 go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		managedConsume.setBounds(10, 150, 1124, 119);
		panel.add(managedConsume);
		
		JScrollPane scrollPaneConsume = new JScrollPane();
		scrollPaneConsume.setBounds(10, 22, 1104, 89);
		managedConsume.add(scrollPaneConsume);
		
		tableConsume = new JTable();
		scrollPaneConsume.setViewportView(tableConsume);
		tableConsume.setModel(initialRow(columnNamesComsume));
		tableConsume.setModel(Managed_Order.showHistoryOrder(user.getId(), initialRow(columnNamesComsume)));
		
		JPanel managedDebit = new JPanel();
		managedDebit.setLayout(null);
		managedDebit.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Li\u0323ch s\u01B0\u0309 thanh toa\u0301n d\u01B0 n\u01A1\u0323", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		managedDebit.setBounds(10, 279, 1124, 119);
		panel.add(managedDebit);
		
		JScrollPane scrollPaneDebit = new JScrollPane();
		scrollPaneDebit.setBounds(10, 22, 1104, 89);
		managedDebit.add(scrollPaneDebit);
		
		tableDebit = new JTable();
		scrollPaneDebit.setViewportView(tableDebit);
		tableDebit.setModel(initialRow(columnNamesDebit));
		tableDebit.setModel(Managed_Payment.showHistoryPayment(user.getId(), initialRow(columnNamesDebit)));
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setBounds(425, 408, 292, 40);
		panel.add(logoutButton);
		
		showInfo();
	}
	
	private DefaultTableModel initialRow(String columnNames[]) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
	private void showInfo() {
		String id = user.getId();
		String name = user.getName();
		String dob = String.valueOf(user.getYearOfBirth());
		String adrr = user.getAddress().toString();
		String status = user.getStatus().toString();
		String zone = user.getPlaceOfTreatment().getName();
		String relative = user.getRelativesString();
		
		idText.setText(id.equals("") ?"không có dữ liệu":id);
		nameText.setText(name.equals("") ? "không có dữ liệu":name);
		idText_1.setText(dob.equals("") ? "không có dữ liệu":dob);
		addrText.setText(adrr.equals("") ? "không có dữ liệu":adrr);
		statusText.setText(status.equals("") ? "không có dữ liệu":status);
		
		debitText.setText(Managed_Account.getDebt(id));
		addrText_1.setText(zone.equals("") ?"không có dữ liệu":zone);
		nameText_1.setText(relative.equals("") ? "không có dữ liệu":relative);
		
	}
}
