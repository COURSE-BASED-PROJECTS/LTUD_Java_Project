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
import javax.swing.ButtonGroup;

public class ManagerZones extends JFrame {

	private JPanel contentPane;
	private JPanel listZone;
	private JScrollPane scrollPaneZone;
	private JTable tableListZone;
	
	// Declare columns of the table Users
    private String [] columnNames = new String [] {
            "Tên", "Sức chứa", "Số lượng hiện tại"};
    private JPanel zoneForm;
    private JTextField namePackageText;
    private JTextField limitText;
    private JLabel slotLabel;
    private JTextField slotText;
    private JPanel utilities;
    private JButton addButton;
    private JButton delButton;
    private JButton updateButton;
    private JButton clearButton;
    private JButton saveButton;
    private JSeparator separator_2;
    private JSeparator separator_3;
    private final ButtonGroup Wanranty = new ButtonGroup();
    private final ButtonGroup Limitation = new ButtonGroup();
    private final ButtonGroup Price = new ButtonGroup();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerZones frame = new ManagerZones();
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
	public ManagerZones() {
		setTitle("Quản lí địa điểm điều trị/cách li");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1097, 705);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listZone = new JPanel();
		listZone.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sa\u0301ch \u0111i\u0323a \u0111i\u00EA\u0309m \u0111i\u00EA\u0300u tri\u0323/ca\u0301ch li", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listZone.setBounds(10, 10, 1063, 253);
		contentPane.add(listZone);
		listZone.setLayout(null);
		
		scrollPaneZone = new JScrollPane();
		scrollPaneZone.setBounds(10, 22, 1043, 221);
		listZone.add(scrollPaneZone);
		
		tableListZone = new JTable();
		scrollPaneZone.setViewportView(tableListZone);
		tableListZone.setModel(initialRow());
		
		zoneForm = new JPanel();
		zoneForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin go\u0301i \u0111i\u0323a \u0111i\u00EA\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		zoneForm.setBounds(10, 273, 1063, 385);
		contentPane.add(zoneForm);
		zoneForm.setLayout(null);
		
		JLabel nameZoneLabel = new JLabel("Tên:");
		nameZoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameZoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		nameZoneLabel.setBounds(10, 34, 95, 35);
		zoneForm.add(nameZoneLabel);
		
		namePackageText = new JTextField();
		namePackageText.setBounds(89, 28, 166, 51);
		zoneForm.add(namePackageText);
		namePackageText.setColumns(10);
		
		JLabel limitLabel = new JLabel("Số lượng hiện tại");
		limitLabel.setHorizontalAlignment(SwingConstants.CENTER);
		limitLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		limitLabel.setBounds(316, 34, 114, 35);
		zoneForm.add(limitLabel);
		
		limitText = new JTextField();
		limitText.setColumns(10);
		limitText.setBounds(436, 28, 281, 51);
		zoneForm.add(limitText);
		
		slotLabel = new JLabel("Sức chứa");
		slotLabel.setHorizontalAlignment(SwingConstants.LEFT);
		slotLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		slotLabel.setBounds(22, 150, 95, 35);
		zoneForm.add(slotLabel);
		
		slotText = new JTextField();
		slotText.setColumns(10);
		slotText.setBounds(89, 144, 166, 51);
		zoneForm.add(slotText);
		
		utilities = new JPanel();
		utilities.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(10, 251, 495, 117);
		zoneForm.add(utilities);
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
		saveButton.setBounds(175, 72, 143, 35);
		utilities.add(saveButton);
		
		separator_2 = new JSeparator();
		separator_2.setBounds(48, 232, 432, 2);
		zoneForm.add(separator_2);
		
		separator_3 = new JSeparator();
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(550, 258, 18, 110);
		zoneForm.add(separator_3);
		
		JButton outButton = new JButton("Thoát");
		outButton.setBounds(578, 278, 143, 70);
		zoneForm.add(outButton);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
