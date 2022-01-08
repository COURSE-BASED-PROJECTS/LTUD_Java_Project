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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.Admin.ManagerHistoryController;
import model.managed.Managed_History;

public class ManagerHistory extends JFrame {

	private JPanel contentPane;
	private JTable tableHistory;
	private String[] columnsName = new String[] { "STT", "Hành động", "Bảng", "Mã đối tượng", "Thời gian" };
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerHistory frame = new ManagerHistory("QuanLyA");
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
	public ManagerHistory(String id) {
		this.id = id;
		ActionListener action = new ManagerHistoryController(this);
		setTitle("Lịch sử");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 885, 463);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel managedHistory = new JPanel();
		managedHistory.setLayout(null);
		managedHistory.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Li\u0323ch s\u01B0\u0309 ho\u1EA1t \u0111\u1ED9ng", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		managedHistory.setBounds(10, 20, 857, 308);
		contentPane.add(managedHistory);

		JScrollPane scrollPaneHistory = new JScrollPane();
		scrollPaneHistory.setBounds(10, 22, 834, 263);
		managedHistory.add(scrollPaneHistory);
		
		tableHistory = new JTable();
		scrollPaneHistory.setViewportView(tableHistory);
		tableHistory.setModel(Managed_History.showManagerHistory(id, initialRow()));

		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.addActionListener(action);
		logoutButton.setBounds(278, 356, 292, 35);
		contentPane.add(logoutButton);

	}

	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		for (int i = 0; i < columnsName.length; i++) {
			defaultTableModel.addColumn(columnsName[i]);
		}

		return defaultTableModel;
	}
}
