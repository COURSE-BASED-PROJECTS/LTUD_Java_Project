package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class ManageAccount extends JFrame {

	private JPanel contentPane;
	private JTable InfoTable;
	private String [] columnNames = new String [] {
            "Thời gian", "Số Người F0", "Số Người F1", "Số Người F2","Số chuyển trạng thái",
            "Số người khỏi bệnh","Số gói nyp tiêu thụ","Số tiền dư nợ"};
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
		setTitle("Thông tin tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 1218, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ta\u0300i khoa\u0309n", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 10, 1184, 497);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPaneAccount = new JScrollPane();
		scrollPaneAccount.setBounds(10, 23, 1164, 464);
		panel.add(scrollPaneAccount);
		
		InfoTable = new JTable();
		scrollPaneAccount.setViewportView(InfoTable);
		InfoTable.setModel(initialRow());
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(957, 533, 18, 110);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(957, 517, 237, 6);
		contentPane.add(separator_2);
		
		JButton outButton = new JButton("Thoát");
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(1000, 551, 143, 70);
		contentPane.add(outButton);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(239, 517, 482, 110);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JButton lockButton = new JButton("Khóa tài khoản");
		lockButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lockButton.setBackground(Color.ORANGE);
		lockButton.setBounds(65, 30, 145, 52);
		panel_1.add(lockButton);
		
		JButton activityButton = new JButton("Xem lịch sử hoạt động");
		activityButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		activityButton.setBackground(Color.CYAN);
		activityButton.setBounds(265, 30, 151, 52);
		panel_1.add(activityButton);
		
		JSeparator separator_1_1 = new JSeparator();
		separator_1_1.setOrientation(SwingConstants.VERTICAL);
		separator_1_1.setBounds(237, 24, 18, 76);
		panel_1.add(separator_1_1);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
