package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JSeparator;

public class ManagerUsers extends JFrame {

	private JPanel contentPane;
	private JPanel listUser;
	private JScrollPane scrollPaneUser;
	private JTable tableListUser;
	
	// Declare columns of the table Users
    private String [] columnNames = new String [] {
            "CMND", "Họ Tên", "Năm sinh", "Địa chỉ", "Trạng Thái",
            "Nơi cách li/Điều trị","Người liên quan"};
    private JPanel userForm;
    private JTextField idText;
    private JTextField nameText;
    private JSpinner yearText;
    private JLabel addressLabel;
    private JComboBox town;
    private JComboBox city;
    private JComboBox district;
    private JLabel relateLabel;
    private JTextField textField;
    private JPanel utilities;
    private JButton addButton;
    private JButton delButton;
    private JButton updateButton;
    private JButton clearButton;
    private JButton saveButton;
    private JButton detailButton;
    private JPanel manage;
    private JButton sortAsc;
    private JButton sortDes;
    private JTextField searchIdText;
    private JTextField searchNameText;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerUsers frame = new ManagerUsers();
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
	public ManagerUsers() {
		setTitle("Quản lí người dùng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 815);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listUser = new JPanel();
		listUser.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sa\u0301ch ng\u01B0\u01A1\u0300i bi\u0323 qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listUser.setBounds(10, 10, 1063, 146);
		contentPane.add(listUser);
		listUser.setLayout(null);
		
		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(10, 22, 1043, 114);
		listUser.add(scrollPaneUser);
		
		tableListUser = new JTable();
		scrollPaneUser.setViewportView(tableListUser);
		tableListUser.setModel(initialRow());
		
		userForm = new JPanel();
		userForm.setBorder(new TitledBorder(null, "Th\u00F4ng tin ng\u01B0\u01A1\u0300i bi\u0323 qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userForm.setBounds(10, 166, 1063, 451);
		contentPane.add(userForm);
		userForm.setLayout(null);
		
		JLabel idLabel = new JLabel("CMND:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(10, 34, 95, 35);
		userForm.add(idLabel);
		
		idText = new JTextField();
		idText.setBounds(89, 28, 166, 51);
		userForm.add(idText);
		idText.setColumns(10);
		
		JLabel nameLabel = new JLabel("Họ và tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(343, 34, 95, 35);
		userForm.add(nameLabel);
		
		nameText = new JTextField();
		nameText.setColumns(10);
		nameText.setBounds(436, 28, 281, 51);
		userForm.add(nameText);
		
		JLabel yearLabel = new JLabel("Năm sinh:");
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLabel.setBounds(782, 34, 95, 35);
		userForm.add(yearLabel);
		
		yearText = new JSpinner();
		yearText.setModel(new SpinnerNumberModel(2000, 1920, 2021, 1));
		yearText.setBounds(874, 30, 102, 47);
		userForm.add(yearText);
		
		addressLabel = new JLabel("Địa chỉ:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addressLabel.setBounds(10, 150, 95, 35);
		userForm.add(addressLabel);
		
		town = new JComboBox();
		town.setFont(new Font("Tahoma", Font.PLAIN, 15));
		town.setToolTipText("");
		town.setModel(new DefaultComboBoxModel(new String[] {"Xã"}));
		town.setName("");
		town.setBounds(99, 142, 107, 51);
		userForm.add(town);
		
		district = new JComboBox();
		district.setModel(new DefaultComboBoxModel(new String[] {"Huyện"}));
		district.setToolTipText("");
		district.setName("");
		district.setFont(new Font("Tahoma", Font.PLAIN, 15));
		district.setBounds(247, 142, 107, 51);
		userForm.add(district);
		
		city = new JComboBox();
		city.setModel(new DefaultComboBoxModel(new String[] {"Tỉnh"}));
		city.setToolTipText("");
		city.setName("");
		city.setFont(new Font("Tahoma", Font.PLAIN, 15));
		city.setBounds(393, 142, 107, 51);
		userForm.add(city);
		
		JLabel statusLabel = new JLabel("Trạng thái:");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(622, 150, 95, 35);
		userForm.add(statusLabel);
		
		JComboBox status = new JComboBox();
		status.setModel(new DefaultComboBoxModel(new String[] {"F"}));
		status.setToolTipText("");
		status.setName("");
		status.setFont(new Font("Tahoma", Font.PLAIN, 15));
		status.setBounds(741, 142, 107, 51);
		userForm.add(status);
		
		JLabel lblNiiuTri = new JLabel("Nơi điều trị:");
		lblNiiuTri.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiiuTri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNiiuTri.setBounds(10, 254, 95, 35);
		userForm.add(lblNiiuTri);
		
		JComboBox treatment = new JComboBox();
		treatment.setModel(new DefaultComboBoxModel(new String[] {"Tiền Giang"}));
		treatment.setToolTipText("");
		treatment.setName("");
		treatment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		treatment.setBounds(99, 246, 107, 51);
		userForm.add(treatment);
		
		relateLabel = new JLabel("Người liên quan:");
		relateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		relateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relateLabel.setBounds(633, 254, 113, 35);
		userForm.add(relateLabel);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(742, 246, 166, 51);
		userForm.add(textField);
		
		utilities = new JPanel();
		utilities.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(10, 323, 495, 117);
		userForm.add(utilities);
		utilities.setLayout(null);
		
		addButton = new JButton("Thêm");
		addButton.setBackground(Color.CYAN);
		addButton.setBounds(10, 24, 95, 35);
		utilities.add(addButton);
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		
		delButton = new JButton("Xóa");
		delButton.setBackground(Color.RED);
		delButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delButton.setBounds(135, 24, 95, 35);
		utilities.add(delButton);
		
		updateButton = new JButton("Sửa");
		updateButton.setBackground(Color.ORANGE);
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		updateButton.setBounds(266, 24, 95, 35);
		utilities.add(updateButton);
		
		clearButton = new JButton("Đặt lại");
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clearButton.setBounds(390, 24, 95, 35);
		utilities.add(clearButton);
		
		saveButton = new JButton("Lưu");
		saveButton.setBackground(Color.GREEN);
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		saveButton.setBounds(103, 72, 143, 35);
		utilities.add(saveButton);
		
		detailButton = new JButton("Xem chi tiết");
		detailButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detailButton.setBounds(256, 72, 143, 35);
		utilities.add(detailButton);
		
		manage = new JPanel();
		manage.setBorder(new TitledBorder(null, "Qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		manage.setBounds(10, 627, 1063, 153);
		contentPane.add(manage);
		manage.setLayout(null);
		
		sortAsc = new JButton("Sắp xếp tăng");
		sortAsc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortAsc.setBounds(180, 31, 143, 35);
		manage.add(sortAsc);
		
		sortDes = new JButton("Sắp xếp giảm");
		sortDes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortDes.setBounds(180, 76, 143, 35);
		manage.add(sortDes);
		
		JRadioButton radioId = new JRadioButton("CMND");
		radioId.setHorizontalAlignment(SwingConstants.CENTER);
		radioId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioId.setBounds(22, 22, 103, 27);
		manage.add(radioId);
		
		JRadioButton radioName = new JRadioButton("Họ tên");
		radioName.setHorizontalAlignment(SwingConstants.CENTER);
		radioName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioName.setBounds(22, 51, 103, 27);
		manage.add(radioName);
		
		JRadioButton radioYear = new JRadioButton("Năm sinh");
		radioYear.setHorizontalAlignment(SwingConstants.LEFT);
		radioYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioYear.setBounds(44, 80, 103, 27);
		manage.add(radioYear);
		
		JRadioButton radioStatus = new JRadioButton("Trạng thái");
		radioStatus.setHorizontalAlignment(SwingConstants.LEFT);
		radioStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioStatus.setBounds(44, 109, 103, 27);
		manage.add(radioStatus);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(352, 31, 18, 110);
		manage.add(separator);
		
		JLabel searchIdLabel = new JLabel("CMND:");
		searchIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchIdLabel.setBounds(366, 17, 95, 35);
		manage.add(searchIdLabel);
		
		searchIdText = new JTextField();
		searchIdText.setColumns(10);
		searchIdText.setBounds(445, 15, 166, 51);
		manage.add(searchIdText);
		
		JLabel searchNameLabel = new JLabel("Họ và tên:");
		searchNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchNameLabel.setBounds(352, 91, 95, 35);
		manage.add(searchNameLabel);
		
		searchNameText = new JTextField();
		searchNameText.setColumns(10);
		searchNameText.setBounds(445, 85, 281, 51);
		manage.add(searchNameText);
		
		JButton searchButton = new JButton("Tìm kiếm");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setBackground(Color.CYAN);
		searchButton.setBounds(621, 14, 105, 52);
		manage.add(searchButton);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(764, 31, 18, 110);
		manage.add(separator_1);
		
		JButton outButton = new JButton("Thoát");
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(802, 50, 143, 61);
		manage.add(outButton);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
