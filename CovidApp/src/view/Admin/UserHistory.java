package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.UserHistoryController;
import model.managed.Managed_History;

public class UserHistory extends JFrame {

	private JPanel contentPane;
	private JTable tbBuyPackageHistory;
	private JTable tbPaymentHistory;
	private String[] columnsNameBuyPkHis = new String[] {"Mã hóa đơn", "Loại hàng", "Số lượng", "Thời gian" };
	private String[] columnsNamePaymentHis = new String[] { "Mã giao dịch", "Dư nợ", "Số tiền trả", "Thời gian" };
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserHistory frame = new UserHistory("0213489216");
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
	public UserHistory(String id) {
		this.id = id;
		ActionListener action = new UserHistoryController(this);
		setTitle("Lịch sử hoạt động");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 885, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel buyPackageHistory = new JPanel();
		buyPackageHistory.setLayout(null);
		buyPackageHistory.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Li\u0323ch s\u01B0\u0309 mua h\u00E0ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		buyPackageHistory.setBounds(10, 20, 857, 123);
		contentPane.add(buyPackageHistory);

		JScrollPane sPBuyPackageHistory = new JScrollPane();
		sPBuyPackageHistory.setBounds(10, 22, 834, 91);
		buyPackageHistory.add(sPBuyPackageHistory);

		tbBuyPackageHistory = new JTable();
		tbBuyPackageHistory.getTableHeader().setReorderingAllowed(false);
		tbBuyPackageHistory.setDefaultEditor(Object.class,null);
		tbBuyPackageHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sPBuyPackageHistory.setViewportView(tbBuyPackageHistory);
		tbBuyPackageHistory.setModel(Managed_History.showBuyPackageHistory(id, initialRow(columnsNameBuyPkHis)));

		
		JPanel paymentHistory = new JPanel();
		paymentHistory.setLayout(null);
		paymentHistory.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Li\u0323ch s\u01B0\u0309 thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		paymentHistory.setBounds(10, 182, 857, 123);
		contentPane.add(paymentHistory);

		JScrollPane sPPaymentHistory = new JScrollPane();
		sPPaymentHistory.setBounds(10, 22, 834, 91);
		paymentHistory.add(sPPaymentHistory);
		
		tbPaymentHistory = new JTable();
		tbPaymentHistory.getTableHeader().setReorderingAllowed(false);
		tbPaymentHistory.setDefaultEditor(Object.class,null);
		tbPaymentHistory.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		sPPaymentHistory.setViewportView(tbPaymentHistory);
		tbPaymentHistory.setModel(Managed_History.showPaymentHistory(id, initialRow(columnsNamePaymentHis)));


		JButton logoutButton = new JButton("Thoát");
		logoutButton.addActionListener(action);
		logoutButton.setBounds(278, 356, 292, 35);
		contentPane.add(logoutButton);
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBackground(new Color(255, 0, 51));

	}

	private DefaultTableModel initialRow(String columnNames[]) {
		DefaultTableModel defaultTableModel = new DefaultTableModel();

		for (int i = 0; i < columnNames.length; i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		return defaultTableModel;
	}
}
