package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Manager.StatisticInfoController;

public class StatisticInfo extends JFrame {

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
					StatisticInfo frame = new StatisticInfo();
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
	public StatisticInfo() {
		ActionListener action = new StatisticInfoController(this);
		setTitle("Thống kê thông tin");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1218, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Th\u00F4\u0301ng k\u00EA", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 10, 1184, 497);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPaneInfo = new JScrollPane();
		scrollPaneInfo.setBounds(10, 23, 1164, 464);
		panel.add(scrollPaneInfo);
		
		InfoTable = new JTable();
		scrollPaneInfo.setViewportView(InfoTable);
		InfoTable.setModel(initialRow());
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setOrientation(SwingConstants.VERTICAL);
		separator_1.setBounds(957, 533, 18, 110);
		contentPane.add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(957, 517, 237, 6);
		contentPane.add(separator_2);
		
		JButton outButton = new JButton("Thoát");
		outButton.addActionListener(action);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(1000, 551, 143, 70);
		contentPane.add(outButton);
	}
	
	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
