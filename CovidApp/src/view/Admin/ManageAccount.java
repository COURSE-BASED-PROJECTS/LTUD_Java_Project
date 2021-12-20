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
		setBounds(100, 100, 996, 631);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ta\u0300i khoa\u0309n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 962, 452);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPaneAccount = new JScrollPane();
		scrollPaneAccount.setBounds(10, 23, 942, 419);
		panel.add(scrollPaneAccount);
		
		AccountTable = new JTable();
		scrollPaneAccount.setViewportView(AccountTable);
		AccountTable.setModel(initialRow());
		AccountTable.setModel(Managed_Account.showAccountTable(initialRow()));
		
		JButton outButton = new JButton("Thoát");
		outButton.addActionListener(action);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(778, 500, 142, 50);
		contentPane.add(outButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(20, 474, 692, 110);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton lockButton = new JButton("Khóa tài khoản");
		lockButton.addActionListener(action);
		lockButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lockButton.setBackground(Color.ORANGE);
		lockButton.setBounds(45, 34, 131, 38);
		panel_1.add(lockButton);
		
		JButton activeButton = new JButton("Kích hoạt tài khoản");
		activeButton.addActionListener(action);
		activeButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		activeButton.setBackground(Color.GREEN);
		activeButton.setBounds(251, 34, 137, 38);
		panel_1.add(activeButton);
		
		JButton activityButton = new JButton("Xem lịch sử hoạt động");
		activityButton.addActionListener(action);
		activityButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		activityButton.setBackground(Color.CYAN);
		activityButton.setBounds(467, 34, 168, 38);
		panel_1.add(activityButton);
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
    public void loadData() {
		AccountTable.setModel(Managed_Account.showAccountTable(initialRow()));
	}
}
