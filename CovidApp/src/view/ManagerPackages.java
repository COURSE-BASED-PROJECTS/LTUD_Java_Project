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

public class ManagerPackages extends JFrame {

	private JPanel contentPane;
	private JPanel listPackage;
	private JScrollPane scrollPanePackage;
	private JTable tableListUser;
	
	// Declare columns of the table Users
    private String [] columnNames = new String [] {
            "Tên gói", "Mức giới hạn", "Thời gian giới hạn", "Đơn giá"};
    private JPanel packageForm;
    private JTextField namePackageText;
    private JTextField limitText;
    private JLabel timeLabel;
    private JTextField timeText;
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
    private JLabel lblnGia;
    private JTextField priceText;
    private JSeparator separator_2;
    private JSeparator separator_3;
    private JLabel searchPackageLabel;
    private JTextField searchPackageText;
    private JButton searchButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPackages frame = new ManagerPackages();
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
	public ManagerPackages() {
		setTitle("Quản lí gói nhu yếu phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 815);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPackage = new JPanel();
		listPackage.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sa\u0301ch ca\u0301c go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listPackage.setBounds(10, 10, 1063, 146);
		contentPane.add(listPackage);
		listPackage.setLayout(null);
		
		scrollPanePackage = new JScrollPane();
		scrollPanePackage.setBounds(10, 22, 1043, 114);
		listPackage.add(scrollPanePackage);
		
		tableListUser = new JTable();
		scrollPanePackage.setViewportView(tableListUser);
		tableListUser.setModel(initialRow());
		
		packageForm = new JPanel();
		packageForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		packageForm.setBounds(10, 166, 1063, 385);
		contentPane.add(packageForm);
		packageForm.setLayout(null);
		
		JLabel namePackageLabel = new JLabel("Tên gói:");
		namePackageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		namePackageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		namePackageLabel.setBounds(10, 34, 95, 35);
		packageForm.add(namePackageLabel);
		
		namePackageText = new JTextField();
		namePackageText.setBounds(89, 28, 166, 51);
		packageForm.add(namePackageText);
		namePackageText.setColumns(10);
		
		JLabel limitLabel = new JLabel("Mức giới hạn");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		limitLabel.setBounds(343, 34, 95, 35);
		packageForm.add(limitLabel);
		
		limitText = new JTextField();
		limitText.setColumns(10);
		limitText.setBounds(436, 28, 281, 51);
		packageForm.add(limitText);
		
		timeLabel = new JLabel("Thời gian");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timeLabel.setBounds(10, 150, 95, 35);
		packageForm.add(timeLabel);
		
		timeText = new JTextField();
		timeText.setColumns(10);
		timeText.setBounds(89, 144, 166, 51);
		packageForm.add(timeText);
		
		utilities = new JPanel();
		utilities.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(10, 251, 495, 117);
		packageForm.add(utilities);
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
		
		lblnGia = new JLabel("Đơn giá");
		lblnGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblnGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnGia.setBounds(343, 150, 95, 35);
		packageForm.add(lblnGia);
		
		priceText = new JTextField();
		priceText.setColumns(10);
		priceText.setBounds(439, 144, 166, 51);
		packageForm.add(priceText);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(48, 232, 432, 2);
		packageForm.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(550, 258, 18, 110);
		packageForm.add(separator_3);
		
		searchPackageLabel = new JLabel("Tên gói");
		searchPackageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchPackageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchPackageLabel.setBounds(578, 275, 95, 35);
		packageForm.add(searchPackageLabel);
		
		searchPackageText = new JTextField();
		searchPackageText.setColumns(10);
		searchPackageText.setBounds(659, 273, 166, 51);
		packageForm.add(searchPackageText);
		
		searchButton = new JButton("Tìm kiếm");
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setBackground(Color.CYAN);
		searchButton.setBounds(851, 272, 105, 52);
		packageForm.add(searchButton);
		
		manage = new JPanel();
		manage.setBorder(new TitledBorder(null, "Qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		manage.setBounds(10, 561, 1063, 153);
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
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(764, 31, 18, 110);
		manage.add(separator_1);
		
		JButton outButton = new JButton("Thoát");
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(803, 51, 143, 70);
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
