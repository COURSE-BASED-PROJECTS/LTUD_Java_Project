package view.Manager;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableModel;

import controller.Manager.ManagerUsersController;
import model.managed.Managed_Address;
import model.managed.Managed_Package;
import model.managed.Managed_User;
import model.managed.Managed_Zone;
import utils.ModifyDataUser;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.Calendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ManagerUsers extends JFrame {

	private JPanel contentPane;
	private JPanel listUser;
	private JScrollPane scrollPaneUser;
	private JTable tableListUser;
	private DefaultTableModel tableModel;
	private Managed_User mu;

	// Declare columns of the table Users
	private String[] columnNames = new String[] { "CMND/CCCD", "Họ Tên", "Năm sinh", "Trạng Thái",
			"Người liên quan", "Địa chỉ", "Nơi cách li/Điều trị" };
	private JPanel userForm;
	private JTextField idText;
	private JTextField nameText;
	private JLabel addressLabel;
	private JComboBox city;
	private JComboBox town;
	private JComboBox district;
	private JComboBox status;
	private JComboBox treatment;
	private JLabel relateLabel;
	private JTextField relativeText;
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
	private JTextField yearText;
	private final ButtonGroup sortGroup = new ButtonGroup();

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
		ActionListener action = new ManagerUsersController(this);
		setTitle("Quản lí người dùng");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 815);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listUser = new JPanel();
		listUser.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sa\u0301ch ng\u01B0\u01A1\u0300i bi\u0323 qua\u0309n li\u0301", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		listUser.setBounds(10, 10, 1063, 146);
		contentPane.add(listUser);
		listUser.setLayout(null);

		scrollPaneUser = new JScrollPane();
		scrollPaneUser.setBounds(10, 22, 1043, 114);
		listUser.add(scrollPaneUser);

		tableListUser = new JTable();
		tableListUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((ManagerUsersController) action).displayData();
			}
		});
		scrollPaneUser.setViewportView(tableListUser);
		//tableListUser.setModel(initialRow());
		tableListUser.setModel(Managed_User.showListUser(initialRow()));

		userForm = new JPanel();
		userForm.setBorder(new TitledBorder(null, "Th\u00F4ng tin ng\u01B0\u01A1\u0300i bi\u0323 qua\u0309n li\u0301",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		userForm.setBounds(10, 166, 1063, 451);
		contentPane.add(userForm);
		userForm.setLayout(null);

		JLabel idLabel = new JLabel("CMND/CCCD:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setBounds(10, 34, 90, 35);
		userForm.add(idLabel);

		idText = new JTextField();
		idText.setEditable(false);
		idText.setBounds(110, 34, 152, 35);
		userForm.add(idText);
		idText.setColumns(10);

		JLabel nameLabel = new JLabel("Họ và tên:");
		nameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameLabel.setBounds(343, 34, 95, 35);
		userForm.add(nameLabel);

		nameText = new JTextField();
		nameText.setEditable(false);
		nameText.setColumns(10);
		nameText.setBounds(436, 34, 281, 35);
		userForm.add(nameText);

		JLabel yearLabel = new JLabel("Năm sinh:");
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		yearLabel.setBounds(782, 34, 95, 35);
		userForm.add(yearLabel);

		addressLabel = new JLabel("Địa chỉ:");
		addressLabel.setHorizontalAlignment(SwingConstants.CENTER);
		addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		addressLabel.setBounds(10, 150, 95, 35);
		userForm.add(addressLabel);

		city = new JComboBox();
		city.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadDistrict();
				loadTown();
			}
		});
		city.setEnabled(false);
		city.setFont(new Font("Tahoma", Font.PLAIN, 15));
		city.setToolTipText("");
		city.setModel(new DefaultComboBoxModel(Managed_Address.getListProvice()));
		city.setName("");
		city.setBounds(110, 150, 170, 35);
		userForm.add(city);

		district = new JComboBox();
		district.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				loadTown();
			}
		});
		district.setEnabled(false);
		district.setModel(new DefaultComboBoxModel(Managed_Address.getListDistrict()));
		district.setToolTipText("");
		district.setName("");
		district.setFont(new Font("Tahoma", Font.PLAIN, 15));
		district.setBounds(290, 150, 170, 35);
		userForm.add(district);

		town = new JComboBox();
		town.setEnabled(false);
		town.setModel(new DefaultComboBoxModel(Managed_Address.getListWard()));
		town.setToolTipText("");
		town.setName("");
		town.setFont(new Font("Tahoma", Font.PLAIN, 15));
		town.setBounds(470, 150, 170, 35);
		userForm.add(town);

		JLabel statusLabel = new JLabel("Trạng thái:");
		statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
		statusLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statusLabel.setBounds(782, 150, 95, 35);
		userForm.add(statusLabel);

		status = new JComboBox();
		status.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if (status.getSelectedItem().toString().trim().equals("F0")) {
					relativeText.setText("");
					relativeText.setEnabled(false);
				} else {
					relativeText.setEnabled(true);
				}
			}
		});
		status.setEnabled(false);
		status.setModel(new DefaultComboBoxModel(new String[] { "Status", "F0", "F1", "F2", "F3" }));
		status.setToolTipText("");
		status.setName("");
		status.setFont(new Font("Tahoma", Font.PLAIN, 15));
		status.setBounds(887, 150, 95, 35);
		userForm.add(status);

		JLabel lblNiiuTri = new JLabel("Nơi điều trị:");
		lblNiiuTri.setHorizontalAlignment(SwingConstants.CENTER);
		lblNiiuTri.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNiiuTri.setBounds(10, 254, 95, 35);
		userForm.add(lblNiiuTri);

		treatment = new JComboBox();
		treatment.setEnabled(false);
		treatment.setModel(new DefaultComboBoxModel(Managed_Zone.getListZone()));
		treatment.setToolTipText("");
		treatment.setName("");
		treatment.setFont(new Font("Tahoma", Font.PLAIN, 15));
		treatment.setBounds(110, 254, 250, 35);
		userForm.add(treatment);

		relateLabel = new JLabel("Người liên quan:");
		relateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		relateLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		relateLabel.setBounds(633, 254, 113, 35);
		userForm.add(relateLabel);

		relativeText = new JTextField();
		relativeText.setEditable(false);
		relativeText.setColumns(10);
		relativeText.setBounds(756, 254, 166, 35);
		userForm.add(relativeText);

		utilities = new JPanel();
		utilities.setBorder(
				new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(276, 324, 495, 117);
		userForm.add(utilities);
		utilities.setLayout(null);

		addButton = new JButton("Thêm");
		addButton.addActionListener(action);
		addButton.setBackground(Color.CYAN);
		addButton.setBounds(10, 24, 95, 35);
		utilities.add(addButton);
		addButton.setFont(new Font("Tahoma", Font.PLAIN, 12));

		delButton = new JButton("Xóa");
		delButton.addActionListener(action);
		delButton.setBackground(Color.RED);
		delButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		delButton.setBounds(135, 24, 95, 35);
		utilities.add(delButton);

		updateButton = new JButton("Sửa");
		updateButton.addActionListener(action);
		updateButton.setBackground(Color.ORANGE);
		updateButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		updateButton.setBounds(266, 24, 95, 35);
		utilities.add(updateButton);

		clearButton = new JButton("Đặt lại");
		clearButton.addActionListener(action);
		clearButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		clearButton.setBounds(390, 24, 95, 35);
		utilities.add(clearButton);

		saveButton = new JButton("Lưu");
		saveButton.addActionListener(action);
		saveButton.setBackground(Color.GREEN);
		saveButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		saveButton.setBounds(103, 72, 143, 35);
		utilities.add(saveButton);

		detailButton = new JButton("Xem chi tiết");
		detailButton.addActionListener(action);
		detailButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		detailButton.setBounds(256, 72, 143, 35);
		utilities.add(detailButton);

		yearText = new JTextField();
		yearText.setEditable(false);
		yearText.setColumns(10);
		yearText.setBounds(887, 34, 119, 35);
		userForm.add(yearText);

		manage = new JPanel();
		manage.setBorder(
				new TitledBorder(null, "Qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		manage.setBounds(10, 627, 1063, 153);
		contentPane.add(manage);
		manage.setLayout(null);

		sortAsc = new JButton("Sắp xếp tăng");
		sortAsc.addActionListener(action);
		sortAsc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortAsc.setBounds(180, 31, 143, 35);
		manage.add(sortAsc);

		sortDes = new JButton("Sắp xếp giảm");
		sortDes.addActionListener(action);
		sortDes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortDes.setBounds(180, 92, 143, 35);
		manage.add(sortDes);

		JRadioButton radioId = new JRadioButton("CMND/CCCD");
		sortGroup.add(radioId);
		radioId.setActionCommand("CMND");
		radioId.setHorizontalAlignment(SwingConstants.CENTER);
		radioId.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioId.setBounds(42, 26, 93, 23);
		manage.add(radioId);

		JRadioButton radioName = new JRadioButton("Họ tên");
		sortGroup.add(radioName);
		radioName.setActionCommand("Họ tên");
		radioName.setHorizontalAlignment(SwingConstants.CENTER);
		radioName.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioName.setBounds(42, 51, 61, 23);
		manage.add(radioName);

		JRadioButton radioYear = new JRadioButton("Năm sinh");
		sortGroup.add(radioYear);
		radioYear.setActionCommand("Năm sinh");
		radioYear.setHorizontalAlignment(SwingConstants.LEFT);
		radioYear.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioYear.setBounds(42, 80, 75, 23);
		manage.add(radioYear);

		JRadioButton radioStatus = new JRadioButton("Trạng thái");
		sortGroup.add(radioStatus);
		radioStatus.setActionCommand("Trạng thái");
		radioStatus.setHorizontalAlignment(SwingConstants.LEFT);
		radioStatus.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioStatus.setBounds(42, 109, 79, 23);
		manage.add(radioStatus);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(333, 26, 18, 110);
		manage.add(separator);

		JLabel searchIdLabel = new JLabel("CMND/CCCD:");
		searchIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchIdLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchIdLabel.setBounds(345, 38, 90, 19);
		manage.add(searchIdLabel);

		searchIdText = new JTextField();
		searchIdText.setColumns(10);
		searchIdText.setBounds(445, 31, 152, 35);
		manage.add(searchIdText);

		JLabel searchNameLabel = new JLabel("Họ và tên:");
		searchNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchNameLabel.setBounds(361, 99, 70, 19);
		manage.add(searchNameLabel);

		searchNameText = new JTextField();
		searchNameText.setColumns(10);
		searchNameText.setBounds(445, 93, 281, 35);
		manage.add(searchNameText);

		JButton searchButton = new JButton("Tìm kiếm");

		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.addActionListener(action);
		searchButton.setBackground(Color.CYAN);
		searchButton.setBounds(618, 22, 105, 52);
		manage.add(searchButton);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(764, 31, 18, 110);
		manage.add(separator_1);

		JButton outButton = new JButton("Thoát");
		outButton.addActionListener(action);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(new Color(255, 0, 0));
		outButton.setBounds(878, 51, 143, 61);
		manage.add(outButton);
	}

	public DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();

		for (int i = 0; i < columnNames.length; i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}

		return defaultTableModel;
	}

	public JPanel getContentPane() {
		return contentPane;
	}

	public JPanel getListUser() {
		return listUser;
	}

	public JScrollPane getScrollPaneUser() {
		return scrollPaneUser;
	}

	public JTable getTableListUser() {
		return tableListUser;
	}

	public DefaultTableModel getTableModel() {
		return tableModel;
	}

	public Managed_User getMu() {
		return mu;
	}

	public String[] getColumnNames() {
		return columnNames;
	}

	public JPanel getUserForm() {
		return userForm;
	}

	public JTextField getIdText() {
		return idText;
	}

	public JTextField getNameText() {
		return nameText;
	}

	public JLabel getAddressLabel() {
		return addressLabel;
	}

	public JComboBox getCity() {
		return city;
	}

	public JComboBox getTown() {
		return town;
	}

	public JComboBox getDistrict() {
		return district;
	}

	public JLabel getRelateLabel() {
		return relateLabel;
	}

	public JTextField getRelativeText() {
		return relativeText;
	}

	public JPanel getUtilities() {
		return utilities;
	}

	public JButton getAddButton() {
		return addButton;
	}

	public JButton getDelButton() {
		return delButton;
	}

	public JButton getUpdateButton() {
		return updateButton;
	}

	public JButton getClearButton() {
		return clearButton;
	}

	public JButton getSaveButton() {
		return saveButton;
	}

	public JButton getDetailButton() {
		return detailButton;
	}

	public JPanel getManage() {
		return manage;
	}

	public JButton getSortAsc() {
		return sortAsc;
	}

	public JButton getSortDes() {
		return sortDes;
	}

	public JTextField getSearchIdText() {
		return searchIdText;
	}

	public JTextField getSearchNameText() {
		return searchNameText;
	}

	public JTextField getYearText() {
		return yearText;
	}

	public JComboBox getStatus() {
		return status;
	}

	public JComboBox getTreatment() {
		return treatment;
	}

	public ButtonGroup getSortGroup() {
		return sortGroup;
	}

	public void loadData() {
		tableListUser.setModel(Managed_User.showListUser(initialRow()));
	}

	public void loadDistrict() {
		//System.out.println("loadDistrict:");
		//System.out.println(this.getCity().getSelectedItem().toString().trim());
		district.setModel(new DefaultComboBoxModel(
				Managed_Address.getListDistrict(this.getCity().getSelectedItem().toString().trim())));
	}

	public void loadTown() {
		//System.out.println("loadTown:");
		//System.out.println(this.getCity().getSelectedItem().toString().trim());
		//System.out.println(this.getDistrict().getSelectedItem().toString().trim());
		town.setModel(new DefaultComboBoxModel(
				Managed_Address.getListWard(this.getCity().getSelectedItem().toString().trim(),this.getDistrict().getSelectedItem().toString().trim())));
	}

}