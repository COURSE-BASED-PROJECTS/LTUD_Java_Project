package view.Manager;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Manager.ManagerPackagesController;
import model.managed.Managed_Package;

public class ManagerPackages extends JFrame {

	private JPanel contentPane;
	private JPanel listPackage;
	private JScrollPane scrollPanePackage;
	private JTable tableListPackage;
	// Declare columns of the table Package
	private String[] columnNames = new String[] { "Mã NYP", "Tên gói", "Thời gian giới hạn", "Mức giới hạn",
			"Giá tiền" };
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
	
	private final ButtonGroup Sort = new ButtonGroup();
	private final ButtonGroup Wanranty = new ButtonGroup();
	private final ButtonGroup Limitation = new ButtonGroup();
	private final ButtonGroup Price = new ButtonGroup();
	
	private JTextField idText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerPackages frame = new ManagerPackages();
					frame.setVisible(true);
					frame.setResizable(false);

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
		ActionListener action = new ManagerPackagesController(this);
		setTitle("Quản lí gói nhu yếu phẩm");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 10, 1039, 720);
		setResizable(false);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		listPackage = new JPanel();
		listPackage.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Danh sa\u0301ch ca\u0301c go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		listPackage.setBounds(10, 10, 1008, 146);
		contentPane.add(listPackage);
		listPackage.setLayout(null);

		scrollPanePackage = new JScrollPane();
		scrollPanePackage.setBounds(10, 22, 979, 114);
		listPackage.add(scrollPanePackage);

		tableListPackage = new JTable();
		tableListPackage.getTableHeader().setReorderingAllowed(false);
		tableListPackage.setDefaultEditor(Object.class,null);
		tableListPackage.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableListPackage.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((ManagerPackagesController) action).displayData();
			}
		});
		scrollPanePackage.setViewportView(tableListPackage);
		tableListPackage.setModel(initialRow());
		tableListPackage.setModel(Managed_Package.showPackages(initialRow(),false,false));
		
		packageForm = new JPanel();
		packageForm.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4ng tin go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING, TitledBorder.TOP,
				null, new Color(0, 0, 0)));
		packageForm.setBounds(10, 166, 1008, 348);
		contentPane.add(packageForm);
		packageForm.setLayout(null);

		JLabel namePackageLabel = new JLabel("Tên gói:");
		namePackageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		namePackageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		namePackageLabel.setBounds(312, 34, 81, 35);
		packageForm.add(namePackageLabel);

		namePackageText = new JTextField();
		namePackageText.setEnabled(false);
		namePackageText.setBounds(402, 33, 240, 35);
		packageForm.add(namePackageText);
		namePackageText.setColumns(10);

		JLabel limitLabel = new JLabel("Mức giới hạn:");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		limitLabel.setBounds(717, 34, 95, 35);
		packageForm.add(limitLabel);

		limitText = new JTextField();
		limitText.setEnabled(false);
		limitText.setColumns(10);
		limitText.setBounds(821, 33, 95, 35);
		packageForm.add(limitText);

		timeLabel = new JLabel("Thời gian giới hạn:");
		timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		timeLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		timeLabel.setBounds(20, 110, 126, 35);
		packageForm.add(timeLabel);

		timeText = new JTextField();
		timeText.setEnabled(false);
		timeText.setColumns(10);
		timeText.setBounds(150, 110, 190, 35);
		packageForm.add(timeText);

		utilities = new JPanel();
		utilities.setBorder(
				new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(10, 200, 495, 117);
		packageForm.add(utilities);
		utilities.setLayout(null);

		addButton = new JButton("Thêm");
		addButton.setForeground(new Color(255, 255, 255));
		
//		addButton.setIcon(new ImageIcon("icons/icons8-add-64.png"));
		
		addButton.addActionListener(action);
		addButton.setBackground(new Color(0, 153, 255));
		addButton.setBounds(10, 24, 95, 35);
		utilities.add(addButton);
		addButton.setFont(new Font("Segoe UI", Font.BOLD, 15));

		delButton = new JButton("Xóa");
		delButton.setForeground(new Color(255, 255, 255));
		
//		delButton.setIcon(new ImageIcon("icons/icons8-delete-64.png"));
		
		delButton.addActionListener(action);
		delButton.setBackground(new Color(255, 0, 51));
		delButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		delButton.setBounds(135, 24, 95, 35);
		utilities.add(delButton);

		updateButton = new JButton("Sửa");
		updateButton.setForeground(new Color(255, 255, 255));
		
//		updateButton.setIcon(new ImageIcon("icons/icons8-edit-64.png"));
		
		updateButton.addActionListener(action);
		updateButton.setBackground(new Color(255, 204, 51));
		updateButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		updateButton.setBounds(266, 24, 95, 35);
		utilities.add(updateButton);

		clearButton = new JButton("Đặt lại");
		clearButton.setForeground(new Color(255, 255, 255));
		clearButton.setBackground(new Color(153, 153, 153));
		clearButton.addActionListener(action);
		clearButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		clearButton.setBounds(390, 24, 95, 35);
		utilities.add(clearButton);

		saveButton = new JButton("Lưu");
		saveButton.setForeground(new Color(255, 255, 255));
		saveButton.addActionListener(action);
		saveButton.setBackground(new Color(0, 204, 0));
		saveButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		saveButton.setBounds(176, 72, 143, 35);
		utilities.add(saveButton);

		lblnGia = new JLabel("Đơn giá:");
		lblnGia.setHorizontalAlignment(SwingConstants.CENTER);
		lblnGia.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblnGia.setBounds(460, 110, 68, 35);
		packageForm.add(lblnGia);

		priceText = new JTextField();
		priceText.setEnabled(false);
		priceText.setColumns(10);
		priceText.setBounds(538, 110, 166, 35);
		packageForm.add(priceText);

		separator_2 = new JSeparator();
		separator_2.setBounds(48, 178, 432, 2);
		packageForm.add(separator_2);

		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(548, 198, 18, 110);
		packageForm.add(separator_3);

		searchPackageLabel = new JLabel("Tên gói:");
		searchPackageLabel.setHorizontalAlignment(SwingConstants.CENTER);
		searchPackageLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchPackageLabel.setBounds(578, 240, 95, 35);
		packageForm.add(searchPackageLabel);

		searchPackageText = new JTextField();
		searchPackageText.setColumns(10);
		searchPackageText.setBounds(659, 240, 166, 35);
		packageForm.add(searchPackageText);

		searchButton = new JButton("Tìm kiếm");
		searchButton.setForeground(new Color(255, 255, 255));
		searchButton.addActionListener(action);
		searchButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		searchButton.setBackground(new Color(0, 204, 255));
		searchButton.setBounds(851, 235, 105, 42);
		packageForm.add(searchButton);

		JLabel lblPackage = new JLabel("Mã gói:");
		lblPackage.setHorizontalAlignment(SwingConstants.CENTER);
		lblPackage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblPackage.setBounds(24, 34, 67, 35);
		packageForm.add(lblPackage);

		idText = new JTextField();
		idText.setEnabled(false);
		idText.setColumns(10);
		idText.setBounds(100, 33, 95, 35);
		packageForm.add(idText);

		manage = new JPanel();
		manage.setBorder(
				new TitledBorder(null, "Qua\u0309n li\u0301", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		manage.setBounds(10, 524, 1008, 153);
		contentPane.add(manage);
		manage.setLayout(null);

		sortAsc = new JButton("Sắp xếp tăng");
		sortAsc.addActionListener(action);
		sortAsc.setFont(new Font("Tahoma", Font.BOLD, 12));
		sortAsc.setBounds(180, 31, 143, 35);
		manage.add(sortAsc);

		sortDes = new JButton("Sắp xếp giảm");
		sortDes.addActionListener(action);
		sortDes.setFont(new Font("Tahoma", Font.BOLD, 12));
		sortDes.setBounds(180, 90, 143, 35);
		manage.add(sortDes);

		JRadioButton radioNamePackage = new JRadioButton("Tên gói");
		radioNamePackage.setActionCommand("Tên gói");
		Sort.add(radioNamePackage);
		radioNamePackage.setHorizontalAlignment(SwingConstants.CENTER);
		radioNamePackage.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioNamePackage.setBounds(24, 35, 103, 27);
		manage.add(radioNamePackage);

		JRadioButton radioTime = new JRadioButton("Thời gian");
		radioTime.setActionCommand("Thời gian");
		Sort.add(radioTime);
		radioTime.setHorizontalAlignment(SwingConstants.LEFT);
		radioTime.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioTime.setBounds(43, 64, 103, 27);
		manage.add(radioTime);

		JRadioButton radioPrice = new JRadioButton("Giá");
		radioPrice.setActionCommand("Giá");
		Sort.add(radioPrice);
		radioPrice.setHorizontalAlignment(SwingConstants.LEFT);
		radioPrice.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioPrice.setBounds(43, 94, 103, 27);
		manage.add(radioPrice);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(352, 31, 18, 110);
		manage.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(814, 31, 18, 110);
		manage.add(separator_1);

		JButton outButton = new JButton("Thoát");
		outButton.setForeground(new Color(255, 255, 255));
		outButton.addActionListener(action);
		outButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		outButton.setBackground(new Color(255, 0, 51));
		outButton.setBounds(856, 64, 120, 60);
		manage.add(outButton);

		JRadioButton radioWaranty = new JRadioButton("Còn thời hạn");
		radioWaranty.setActionCommand("Còn thời hạn");
		Wanranty.add(radioWaranty);
		radioWaranty.setHorizontalAlignment(SwingConstants.LEFT);
		radioWaranty.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioWaranty.setBounds(372, 35, 103, 27);
		manage.add(radioWaranty);

		JRadioButton radioTimeOut = new JRadioButton("Hết thời hạn");
		radioTimeOut.setActionCommand("Hết thời hạn");
		Wanranty.add(radioTimeOut);
		radioTimeOut.setHorizontalAlignment(SwingConstants.LEFT);
		radioTimeOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioTimeOut.setBounds(519, 35, 103, 27);
		manage.add(radioTimeOut);

		JRadioButton radioPriceGreater = new JRadioButton("Giá > 200.000");
		radioPriceGreater.setActionCommand("Giá > 200.000");
		Price.add(radioPriceGreater);
		radioPriceGreater.setHorizontalAlignment(SwingConstants.LEFT);
		radioPriceGreater.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioPriceGreater.setBounds(372, 94, 103, 27);
		manage.add(radioPriceGreater);
		
		JRadioButton radioPriceLess = new JRadioButton("Giá <= 200.000");
		radioPriceLess.setActionCommand("Giá <= 200.000");
		Price.add(radioPriceLess);
		radioPriceLess.setHorizontalAlignment(SwingConstants.LEFT);
		radioPriceLess.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioPriceLess.setBounds(519, 94, 122, 27);
		manage.add(radioPriceLess);
		
		JRadioButton radioLimitGreater = new JRadioButton("Mức giới hạn > 10");
		radioLimitGreater.setActionCommand("Mức giới hạn > 10");
		Limitation.add(radioLimitGreater);
		radioLimitGreater.setHorizontalAlignment(SwingConstants.LEFT);
		radioLimitGreater.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioLimitGreater.setBounds(372, 64, 135, 27);
		manage.add(radioLimitGreater);

		JRadioButton radioLimitLess = new JRadioButton("Mức giới hạn <= 10");
		radioLimitLess.setActionCommand("Mức giới hạn <= 10");
		Limitation.add(radioLimitLess);
		radioLimitLess.setHorizontalAlignment(SwingConstants.LEFT);
		radioLimitLess.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radioLimitLess.setBounds(519, 64, 135, 27);
		manage.add(radioLimitLess);

		JButton filterButton = new JButton("Lọc");
		filterButton.setForeground(new Color(255, 255, 255));
		filterButton.addActionListener(action);
		filterButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		filterButton.setBackground(new Color(0, 204, 255));
		filterButton.setBounds(678, 50, 105, 50);
		manage.add(filterButton);
	}

	public DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();

		for (int i = 0; i < columnNames.length; i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}

		return defaultTableModel;
	}

	public JTextField getIdText() {
		return idText;
	}

	public JTextField getNamePackageText() {
		return namePackageText;
	}

	public void setNamePackageText(JTextField namePackageText) {
		this.namePackageText = namePackageText;
	}

	public JTextField getLimitText() {
		return limitText;
	}

	public void setLimitText(JTextField limitText) {
		this.limitText = limitText;
	}

	public JTextField getTimeText() {
		return timeText;
	}

	public void setTimeText(JTextField timeText) {
		this.timeText = timeText;
	}

	public JTextField getPriceText() {
		return priceText;
	}

	public void setPriceText(JTextField priceText) {
		this.priceText = priceText;
	}

	public JTextField getSearchPackageText() {
		return searchPackageText;
	}

	public void setSearchPackageText(JTextField searchPackageText) {
		this.searchPackageText = searchPackageText;
	}

	public JTable getTableListPackage() {
		return tableListPackage;
	}

	public ButtonGroup getSort() {
		return Sort;
	}

	public ButtonGroup getWanranty() {
		return Wanranty;
	}

	public ButtonGroup getLimitation() {
		return Limitation;
	}

	public ButtonGroup getPrice() {
		return Price;
	}

	public void loadData() {
		tableListPackage.setModel(Managed_Package.showPackages(initialRow(),true,false));
	}
}