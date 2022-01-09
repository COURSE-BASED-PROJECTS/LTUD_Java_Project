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
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import controller.PaymentSystem.DetailController;
import model.managed.Managed_PaymentSystem;

public class DetailView extends JFrame {

	private JPanel contentPane;
	private JTable tableManaged;
	private JTable tableConsume;
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
		setTitle("Lịch sử thanh toán");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 776, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(11, 10, 741, 480);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPaneConsume = new JScrollPane();
		scrollPaneConsume.setBounds(10, 22, 721, 354);
		panel.add(scrollPaneConsume);
		
		tableConsume = new JTable();
		tableConsume.getTableHeader().setReorderingAllowed(false);
		tableConsume.setDefaultEditor(Object.class,null);
		tableConsume.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPaneConsume.setViewportView(tableConsume);
		tableConsume.setModel(initialRow(columnNamesTrans));
		tableConsume.setModel(Managed_PaymentSystem.showTransactionTable(initialRow(columnNamesTrans)));
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		logoutButton.setBackground(Color.RED);
		logoutButton.setBounds(216, 409, 292, 50);
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
