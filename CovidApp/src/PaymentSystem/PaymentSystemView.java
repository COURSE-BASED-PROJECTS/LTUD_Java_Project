package PaymentSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.PaymentSystem.PaymentSystemController;
import model.managed.Managed_PaymentSystem;
import utils.ServerThread;

import java.awt.event.ActionEvent;

public class PaymentSystemView extends JFrame {

	private JPanel contentPane;

	private static JLabel debitCurrentText;
	private static JLabel balanceCurrentText;
	public JLabel getDebitCurrentText() {
		return debitCurrentText;
	}
	public JLabel getBalanceCurrentText() {
		return balanceCurrentText;
	}
	public static void setDebitCurrentText(String debit) {
		debitCurrentText.setText(debit);
	}
	public static void setBalanceCurrentText(String balance) {
		balanceCurrentText.setText(balance);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PaymentSystemView frame = new PaymentSystemView();
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
	public PaymentSystemView() {
		ActionListener action = new PaymentSystemController(this);
		setTitle("Thanh toán");
		setIconImage(new ImageIcon("icons/icons8_payment_history_64px_1.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 776, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), 
				"Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		feature.setBounds(10, 134, 742, 245);
		contentPane.add(feature);
		feature.setLayout(null);
		
		JLabel idAdmin = new JLabel("ID:");
		idAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		idAdmin.setBounds(96, 79, 37, 35);
		contentPane.add(idAdmin);
		
		JLabel balanceLabel = new JLabel("Số dư:");
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		balanceLabel.setBounds(385, 79, 62, 35);
		contentPane.add(balanceLabel);
		
		JButton CreateAccButton = new JButton("Thêm người được quản lí");
		CreateAccButton.setForeground(new Color(255, 255, 255));
		CreateAccButton.addActionListener(action);
		CreateAccButton.setBackground(new Color(255, 204, 50));
		CreateAccButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		CreateAccButton.setBounds(246, 141, 230, 60);
		feature.add(CreateAccButton);
		
		JButton ManagedAccButton = new JButton("Quản lý tài khoản");
		ManagedAccButton.addActionListener(action);
		ManagedAccButton.setForeground(new Color(255, 255, 255));
		ManagedAccButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		ManagedAccButton.setBackground(new Color(0, 153, 255));
		ManagedAccButton.setBounds(76, 67, 223, 60);
		feature.add(ManagedAccButton);
		
		JButton btnLchSThanh = new JButton("Lịch sử thanh toán");
		btnLchSThanh.setForeground(new Color(255, 255, 255));
		btnLchSThanh.addActionListener(action);
		btnLchSThanh.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnLchSThanh.setBackground(new Color(0, 204, 51));
		btnLchSThanh.setBounds(427, 67, 223, 60);
		feature.add(btnLchSThanh);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBounds(296, 400, 165, 77);
		contentPane.add(logoutButton);
		
		debitCurrentText = new JLabel("Admin_Payment");
		debitCurrentText.setFont(new Font("Segoe UI Black", Font.PLAIN, 18));
		debitCurrentText.setBounds(143, 80, 160, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel(Managed_PaymentSystem.getBalance("Admin_Payment"));
		balanceCurrentText.setFont(new Font("Segoe UI", Font.BOLD, 18));
		balanceCurrentText.setBounds(457, 79, 165, 35);
		contentPane.add(balanceCurrentText);
		
		JLabel lblSystem = new JLabel("HỆ THỐNG QUẢN LÝ THANH TOÁN");
		lblSystem.setBounds(74, 15, 574, 27);
		contentPane.add(lblSystem);
		lblSystem.setForeground(new Color(0, 102, 51));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
