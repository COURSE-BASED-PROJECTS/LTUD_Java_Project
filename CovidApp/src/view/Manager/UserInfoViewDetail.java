package view.Manager;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Manager.DetailInfoController;
import model.User;
import model.managed.Managed_Status;
import model.managed.Managed_User;
import model.managed.Managed_Zone;

public class UserInfoViewDetail extends JFrame {

	private JPanel contentPane;
	private JTable tableManaged;
	private JTable tableRelated;
	private String [] columnNamesManaged = new String [] {"STT","CMND",
             "Trạng thái cũ", "Trạng thái mới", "Thời gian"};
	private String [] columnNamesRelated = new String [] {
            "CMND", "Họ tên","Năm sinh","Trạng Thái", "Địa chỉ"};
	
	private JLabel idText;
	private JLabel addrText;
	private JLabel nameText;
	private JLabel yearText;
	private JLabel statusText;
	private JLabel zoneText;
	private JLabel relativeText;
	private String id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoViewDetail frame = new UserInfoViewDetail("0191818716");
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
	public UserInfoViewDetail(String id) {
		this.id = id;
		ActionListener action = new DetailInfoController(this);
		setTitle("Thông tin");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1178, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel Info = new JPanel();
		Info.setBorder(new TitledBorder(null, "Th\u00F4ng tin ca\u0301 nh\u00E2n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		Info.setBounds(10, 10, 1144, 155);
		contentPane.add(Info);
		Info.setLayout(null);
		
		JLabel idLabel = new JLabel("CMND/CCCD:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setBounds(21, 36, 97, 35);
		Info.add(idLabel);
		
		idText = new JLabel("");
		idText.setHorizontalAlignment(SwingConstants.LEFT);
		idText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idText.setBounds(125, 36, 125, 35);
		
		Info.add(idText);
		
		JLabel nameLabel = new JLabel("Họ tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(260, 36, 64, 35);
		Info.add(nameLabel);
		
		nameText = new JLabel("");
		nameText.setHorizontalAlignment(SwingConstants.LEFT);
		nameText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameText.setBounds(334, 36, 180, 35);
		
		Info.add(nameText);
		
		JLabel yearLabel = new JLabel("Năm sinh:");
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLabel.setBounds(524, 36, 95, 35);
		Info.add(yearLabel);
		
		yearText = new JLabel("2001");
		yearText.setHorizontalAlignment(SwingConstants.LEFT);
		yearText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearText.setBounds(615, 36, 92, 35);
		
		Info.add(yearText);
		
		JLabel addrLabel = new JLabel("Địa chỉ:");
		addrLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addrLabel.setBounds(21, 99, 64, 35);
		Info.add(addrLabel);
		
		addrText = new JLabel("");
		addrText.setHorizontalAlignment(SwingConstants.LEFT);
		addrText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addrText.setBounds(125, 99, 389, 35);
		
		Info.add(addrText);
		
		JLabel statusLabel = new JLabel("Trạng thái:");
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(524, 99, 95, 35);
		Info.add(statusLabel);
		
		statusText = new JLabel("");
		statusText.setHorizontalAlignment(SwingConstants.LEFT);
		statusText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusText.setBounds(615, 99, 92, 35);
		
		Info.add(statusText);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(717, 36, 2, 98);
		Info.add(separator_1);
		
		JLabel lockdownLabel = new JLabel("Nơi điều trị:");
		lockdownLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lockdownLabel.setBounds(729, 36, 79, 35);
		Info.add(lockdownLabel);
		
		zoneText = new JLabel("");
		zoneText.setHorizontalAlignment(SwingConstants.LEFT);
		zoneText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		zoneText.setBounds(822, 36, 301, 35);
		
		Info.add(zoneText);
		
		JLabel relateUser = new JLabel("Người liên quan:");
		relateUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relateUser.setBounds(729, 81, 108, 35);
		Info.add(relateUser);
		
		relativeText = new JLabel("");
		relativeText.setHorizontalAlignment(SwingConstants.LEFT);
		relativeText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relativeText.setBounds(847, 81, 166, 35);
		
		Info.add(relativeText);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Li\u0323ch s\u01B0\u0309", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 175, 1144, 373);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel managedHistory = new JPanel();
		managedHistory.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), 
				"Li\u0323ch s\u01B0\u0309 tr\u1EA1ng th\u00E1i", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		managedHistory.setBounds(10, 21, 1124, 132);
		panel.add(managedHistory);
		managedHistory.setLayout(null);
		
		JScrollPane scrollPaneManaged = new JScrollPane();
		scrollPaneManaged.setBounds(10, 22, 1104, 100);
		managedHistory.add(scrollPaneManaged);
		
		tableManaged = new JTable();
		tableManaged.getTableHeader().setReorderingAllowed(false);
		tableManaged.setDefaultEditor(Object.class,null);
		tableManaged.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneManaged.setViewportView(tableManaged);
		//tableManaged.setModel(initialRow(columnNamesManaged));
		tableManaged.setModel(Managed_Status.showHistoryStatus(id, initialRow(columnNamesManaged)));
		
		JPanel managedConsume = new JPanel();
		managedConsume.setLayout(null);
		managedConsume.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh s\u00E1ch ng\u01B0\u1EDDi li\u00EAn quan", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		managedConsume.setBounds(10, 163, 1124, 150);
		panel.add(managedConsume);
		
		JScrollPane scrollPaneConsume = new JScrollPane();
		scrollPaneConsume.setBounds(10, 22, 1104, 118);
		managedConsume.add(scrollPaneConsume);
		
		tableRelated = new JTable();
		tableRelated.getTableHeader().setReorderingAllowed(false);
		tableRelated.setDefaultEditor(Object.class,null);
		tableRelated.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneConsume.setViewportView(tableRelated);
		//tableRelated.setModel(initialRow(columnNamesRelated));
		tableRelated.setModel(Managed_User.showListUserRelative(id, initialRow(columnNamesRelated)));

		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setBounds(429, 323, 292, 40);
		panel.add(logoutButton);
		
		showInfo();
	}
	
	private void showInfo() {
		User user = Managed_User.findById(id);
		this.getIdText().setText(user.getId());
		this.getNameText().setText(user.getName());
		this.getAddrText().setText(user.getAddress().toString());
		this.getYearText().setText(user.getYearOfBirth()+"");
		this.getStatusText().setText(user.getStatus().getF());
		this.getZoneText().setText(Managed_Zone.getZoneNameFromId(user.getPlaceOfTreatment().getId()));
		if (user.getRelative().getId().length() == 0) {
			this.getRelativeText().setText("");
		} else {
			this.getRelativeText().setText(Managed_User.findById(user.getRelative().getId()).getName());	
		}
	}

	private DefaultTableModel initialRow(String columnNames[]) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JTable getTableManaged() {
		return tableManaged;
	}

	public JTable getTableRelated() {
		return tableRelated;
	}

	public String[] getColumnNamesManaged() {
		return columnNamesManaged;
	}

	public String[] getColumnNamesRelated() {
		return columnNamesRelated;
	}

	public JLabel getIdText() {
		return idText;
	}

	public JLabel getAddrText() {
		return addrText;
	}

	public JLabel getNameText() {
		return nameText;
	}

	public JLabel getYearText() {
		return yearText;
	}

	public JLabel getStatusText() {
		return statusText;
	}

	public JLabel getZoneText() {
		return zoneText;
	}

	public JLabel getRelativeText() {
		return relativeText;
	}

	public String getId() {
		return id;
	}
	

}
