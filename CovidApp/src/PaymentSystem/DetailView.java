package PaymentSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.PaymentSystem.DetailController;
import model.managed.Managed_PaymentSystem;

public class DetailView extends JFrame {

	private JPanel contentPane;
	private JTable tableManaged;
	private JTable tableConsume;
	private String [] columnNamesAccount = new String [] {"Tài khoản", "Mật khẩu", "Phân quyền","Số dư","Dư nợ", "Trạng thái"};
	private String [] columnNamesTrans = new String [] {
            "Mã GD", "CMND", "Dư nợ","Số tiền trả","Thời gian"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DetailView frame = new DetailView();
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
	public DetailView() {
		ActionListener action = new DetailController(this);
		setTitle("Thông tin giao dịch");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 867, 602);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(11, 10, 842, 555);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel AccountTable = new JPanel();
		AccountTable.setBorder(new TitledBorder(null, "Ta\u0300i khoa\u0309n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		AccountTable.setBounds(10, 21, 822, 254);
		panel.add(AccountTable);
		AccountTable.setLayout(null);
		
		JScrollPane scrollPaneManaged = new JScrollPane();
		scrollPaneManaged.setBounds(10, 22, 802, 222);
		AccountTable.add(scrollPaneManaged);
		
		tableManaged = new JTable();
		scrollPaneManaged.setViewportView(tableManaged);
		tableManaged.setModel(initialRow(columnNamesAccount));
		tableManaged.setModel(Managed_PaymentSystem.showAccountTable(initialRow(columnNamesAccount)));
		
		JPanel TransTable = new JPanel();
		TransTable.setLayout(null);
		TransTable.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Li\u0323ch s\u01B0\u0309 giao di\u0323ch", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		TransTable.setBounds(7, 285, 825, 196);
		panel.add(TransTable);
		
		JScrollPane scrollPaneConsume = new JScrollPane();
		scrollPaneConsume.setBounds(10, 22, 805, 164);
		TransTable.add(scrollPaneConsume);
		
		tableConsume = new JTable();
		scrollPaneConsume.setViewportView(tableConsume);
		tableConsume.setModel(initialRow(columnNamesTrans));
		tableConsume.setModel(Managed_PaymentSystem.showTransactionTable(initialRow(columnNamesTrans)));
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		logoutButton.setBackground(Color.RED);
		logoutButton.setBounds(271, 491, 292, 35);
		panel.add(logoutButton);
		
	}
	
	private DefaultTableModel initialRow(String columnNames[]) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
}
