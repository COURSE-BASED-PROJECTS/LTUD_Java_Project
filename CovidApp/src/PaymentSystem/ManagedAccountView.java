package PaymentSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.ManagerZonesController;
import controller.PaymentSystem.ManagedAccountController;
import model.managed.Managed_PaymentSystem;
import model.managed.Managed_Zone;

public class ManagedAccountView extends JFrame {

	private JPanel contentPane;
	private JTable tableManaged;
    private JTextField usernameText;
    private JTextField balanceText;
	private String [] columnNamesAccount = new String [] {"Tài khoản", "Mật khẩu", "Phân quyền","Số dư","Dư nợ", "Trạng thái"};

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagedAccountView frame = new ManagedAccountView();
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
	public ManagedAccountView() {
		ActionListener action = new ManagedAccountController(this);
		setTitle("Thông tin tài khoản");
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
		AccountTable.setBounds(10, 21, 822, 288);
		panel.add(AccountTable);
		AccountTable.setLayout(null);
		
		JScrollPane scrollPaneManaged = new JScrollPane();
		scrollPaneManaged.setBounds(10, 22, 802, 256);
		AccountTable.add(scrollPaneManaged);
		
		tableManaged = new JTable();
		tableManaged.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((ManagedAccountController) action).displayData();
			}
		});
		scrollPaneManaged.setViewportView(tableManaged);
		tableManaged.setModel(Managed_PaymentSystem.showAccountTable(initialRow(columnNamesAccount)));
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), 
				"C\u1EADp nh\u1EADt s\u1ED1 d\u01B0", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		feature.setBounds(10, 319, 822, 136);
		panel.add(feature);
		feature.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Tên tài khoản:");
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameLabel.setHorizontalAlignment(SwingConstants.LEFT);
		usernameLabel.setBounds(26, 56, 110, 35);
		feature.add(usernameLabel);

		usernameText = new JTextField();
		usernameText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		usernameText.setHorizontalAlignment(SwingConstants.LEFT);
		usernameText.setEditable(false);
		usernameText.setBounds(132, 50, 171, 50);
		feature.add(usernameText);
		usernameText.setColumns(10);
		
		JLabel balanceLabel = new JLabel("Số dư:");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setBounds(345, 56, 58, 35);
		feature.add(balanceLabel);

		balanceText = new JTextField();
		balanceText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		balanceText.setHorizontalAlignment(SwingConstants.LEFT);
		balanceText.setEditable(false);
		balanceText.setBounds(413, 50, 171, 50);
		feature.add(balanceText);
		balanceText.setColumns(10);
		
		JButton saveButton = new JButton("Lưu");
		saveButton.setBounds(673, 47, 110, 50);
		saveButton.addActionListener(action);
		feature.add(saveButton);
		saveButton.setForeground(Color.WHITE);
		saveButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		saveButton.setBackground(new Color(0, 204, 0));
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		logoutButton.setBackground(Color.RED);
		logoutButton.setBounds(283, 477, 292, 50);
		panel.add(logoutButton);
		
	}
	
	private DefaultTableModel initialRow(String columnNames[]) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
	
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTable getTableManaged() {
		return tableManaged;
	}
    public JTextField getUsernameText() {
    	return usernameText;
    }
    public JTextField getBalanceText() {
    	return balanceText;
    }
    public void loadData() {
    	tableManaged.setModel(Managed_PaymentSystem.showAccountTable(initialRow(columnNamesAccount)));
	}
}
