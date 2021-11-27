package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.User.BuyPackageController;
import model.managed.Managed_Package;

public class BuyPackageView extends JFrame {

	private JPanel contentPane;
	private JPanel listPackage;
	private JScrollPane scrollPanePackage;
	private JTable tableListPackage;
	
	// Declare columns of the table Users
    private String [] columnNames = new String [] {"Mã NYP",
            "Tên gói", "Thời gian giới hạn", "Mức giới hạn","Giá tiền"};
    private JPanel manage;
    private JButton sortAsc;
    private JButton sortDes;
    private JTextField searchPackageText;
    private JButton searchButton;
    private final ButtonGroup Price = new ButtonGroup();
    private JButton buyButton;
    private JButton logoutButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BuyPackageView frame = new BuyPackageView();
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
	public BuyPackageView() {
		ActionListener action = new BuyPackageController(this);
		setTitle("Mua nhu yếu phẩm");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 540);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listPackage = new JPanel();
		listPackage.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sa\u0301ch ca\u0301c go\u0301i nhu y\u00EA\u0301u ph\u00E2\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listPackage.setBounds(10, 10, 1063, 320);
		contentPane.add(listPackage);
		listPackage.setLayout(null);
		
		scrollPanePackage = new JScrollPane();
		scrollPanePackage.setBounds(10, 22, 1043, 288);
		listPackage.add(scrollPanePackage);
		
		tableListPackage = new JTable();
		scrollPanePackage.setViewportView(tableListPackage);
		tableListPackage.setModel(initialRow());
		tableListPackage.setModel(Managed_Package.showPackages(initialRow()));
		
		manage = new JPanel();
		manage.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		manage.setBounds(10, 340, 1063, 153);
		contentPane.add(manage);
		manage.setLayout(null);
		
		sortAsc = new JButton("Sắp xếp giá tăng");
		sortAsc.addActionListener(action);
		sortAsc.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortAsc.setBounds(10, 31, 143, 35);
		manage.add(sortAsc);
		
		sortDes = new JButton("Sắp xếp giá giảm");
		sortDes.addActionListener(action);
		sortDes.setFont(new Font("Tahoma", Font.PLAIN, 12));
		sortDes.setBounds(10, 90, 143, 35);
		manage.add(sortDes);
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(163, 31, 18, 110);
		manage.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(814, 31, 18, 110);
		manage.add(separator_1);
		
		JRadioButton radio_3 = new JRadioButton("Giá < 250.000");
		Price.add(radio_3);
		radio_3.setHorizontalAlignment(SwingConstants.LEFT);
		radio_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_3.setBounds(175, 94, 103, 27);
		manage.add(radio_3);
		
		JButton filterButton = new JButton("Lọc");
		filterButton.addActionListener(action);
		filterButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		filterButton.setBackground(Color.CYAN);
		filterButton.setBounds(345, 51, 105, 52);
		manage.add(filterButton);
		
		buyButton = new JButton("Mua");
		buyButton.addActionListener(action);
		buyButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		buyButton.setBackground(Color.GREEN);
		buyButton.setBounds(866, 45, 143, 35);
		manage.add(buyButton);
		
		logoutButton = new JButton("Thoát");
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		logoutButton.setBackground(Color.RED);
		logoutButton.setBounds(866, 90, 143, 35);
		manage.add(logoutButton);
		
		JRadioButton radio_2 = new JRadioButton("Giá 250.000 - 500.000");
		Price.add(radio_2);
		radio_2.setHorizontalAlignment(SwingConstants.LEFT);
		radio_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_2.setBounds(175, 64, 151, 27);
		manage.add(radio_2);
		
		JRadioButton radio_1 = new JRadioButton("Giá > 500.000");
		Price.add(radio_1);
		radio_1.setHorizontalAlignment(SwingConstants.LEFT);
		radio_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		radio_1.setBounds(175, 35, 103, 27);
		manage.add(radio_1);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(471, 31, 18, 110);
		manage.add(separator_1_1);
		
		JLabel searchPackageLabel_1 = new JLabel("Tên gói");
		searchPackageLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		searchPackageLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		searchPackageLabel_1.setBounds(475, 51, 95, 35);
		manage.add(searchPackageLabel_1);
		
		searchPackageText = new JTextField();
		searchPackageText.setBounds(564, 38, 166, 51);
		manage.add(searchPackageText);
		searchPackageText.setColumns(10);
		
		searchButton = new JButton("Tìm kiếm");
		searchButton.addActionListener(action);
		searchButton.setBounds(574, 106, 143, 35);
		manage.add(searchButton);
		searchButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		searchButton.setBackground(Color.CYAN);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
