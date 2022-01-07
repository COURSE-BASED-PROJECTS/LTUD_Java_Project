package PaymentSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 676, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idAdmin = new JLabel("ID:");
		idAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		idAdmin.setBounds(90, 87, 37, 35);
		contentPane.add(idAdmin);
		
		JLabel balanceLabel = new JLabel("Số dư:");
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		balanceLabel.setBounds(353, 87, 62, 35);
		contentPane.add(balanceLabel);
		
		JButton paymentButton = new JButton("Thêm người được quản lí");
		paymentButton.setForeground(new Color(0, 0, 0));
		paymentButton.addActionListener(action);
		paymentButton.setBackground(new Color(255, 204, 51));
		paymentButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		paymentButton.setBounds(54, 174, 223, 77);
		contentPane.add(paymentButton);
		
		JButton btnLchSThanh = new JButton("Lịch sử thanh toán");
		btnLchSThanh.addActionListener(action);
		btnLchSThanh.setFont(new Font("Segoe UI", Font.BOLD, 15));
		btnLchSThanh.setBackground(new Color(0, 204, 51));
		btnLchSThanh.setBounds(369, 174, 223, 77);
		contentPane.add(btnLchSThanh);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBounds(243, 308, 165, 77);
		contentPane.add(logoutButton);
		
		debitCurrentText = new JLabel("Admin_Payment");
		debitCurrentText.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		debitCurrentText.setBounds(137, 88, 140, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel(Managed_PaymentSystem.getBalance("Admin_Payment"));
		balanceCurrentText.setFont(new Font("Segoe UI", Font.BOLD, 16));
		balanceCurrentText.setBounds(425, 87, 165, 35);
		contentPane.add(balanceCurrentText);
		
		JLabel lblSystem = new JLabel("HỆ THỐNG QUẢN LÝ THANH TOÁN");
		lblSystem.setBounds(51, 5, 522, 27);
		contentPane.add(lblSystem);
		lblSystem.setForeground(new Color(0, 102, 51));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
