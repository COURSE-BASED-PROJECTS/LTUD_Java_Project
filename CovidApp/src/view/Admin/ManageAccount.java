package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.ManageAccountController;
import model.managed.Managed_Account;
import model.managed.Managed_Zone;

public class ManageAccount extends JFrame {

	private Managed_Account ma;
	private JPanel contentPane;
	private JTable AccountTable;
	private JTextField textField;
	private String [] columnNames = new String [] {
            "Tài khoản", "Mật khẩu", "Phân quyền","Số dư","Dư nợ", "Trạng thái"};
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManageAccount frame = new ManageAccount();
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
	public ManageAccount() {
		ActionListener action = new ManageAccountController(this);
		setTitle("Thông tin tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1097, 686);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ta\u0300i khoa\u0309n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 1063, 452);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPaneAccount = new JScrollPane();
		scrollPaneAccount.setBounds(10, 23, 1043, 419);
		panel.add(scrollPaneAccount);
		
		AccountTable = new JTable();
		scrollPaneAccount.setViewportView(AccountTable);
		AccountTable.setModel(initialRow());
		AccountTable.setModel(Managed_Account.showAccountTable(initialRow()));
		
		JButton outButton = new JButton("Thoát");
		outButton.addActionListener(action);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(883, 549, 142, 50);
		contentPane.add(outButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(21, 472, 692, 167);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton lockButton = new JButton("Khóa tài khoản");
		lockButton.addActionListener(action);
		lockButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lockButton.setBackground(Color.ORANGE);
		lockButton.setBounds(437, 33, 131, 38);
		panel_1.add(lockButton);
		
		JButton activityButton = new JButton("Xem lịch sử hoạt động");
		activityButton.addActionListener(action);
		activityButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		activityButton.setBackground(Color.CYAN);
		activityButton.setBounds(420, 98, 168, 38);
		panel_1.add(activityButton);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(340, 21, 18, 125);
		panel_1.add(separator_1_1);
		
		JLabel lblNewLabel = new JLabel("Tên tài khoản:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblNewLabel.setBounds(58, 74, 83, 31);
		panel_1.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 12));
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setColumns(10);
		textField.setBounds(151, 74, 145, 31);
		panel_1.add(textField);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(855, 529, 18, 110);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(869, 517, 173, 2);
		contentPane.add(separator_2);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
	public Managed_Account getManagedAccount() {
		return ma;
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTable getAccountTable() {
		return AccountTable;
	}
	public JTextField getTextField() {
		return textField;
	}
    public void loadData() {
		AccountTable.setModel(Managed_Account.showAccountTable(initialRow()));
	}
}
