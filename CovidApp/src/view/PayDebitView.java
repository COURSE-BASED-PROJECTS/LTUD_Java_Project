package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.User.PayDebitController;
import model.AccountCurrent;
import model.managed.Managed_Account;
import model.managed.Managed_User;

public class PayDebitView extends JFrame {

	private JPanel contentPane;
	public JTextField getPaymentMinimumText() {
		return paymentMinimumText;
	}

	public JLabel getDebitCurrentText() {
		return debitCurrentText;
	}

	private JTextField paymentMinimumText;
	private JLabel debitCurrentText;
	private JLabel balanceCurrentText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PayDebitView frame = new PayDebitView();
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
	public PayDebitView() {
		ActionListener action = new PayDebitController(this);
		setTitle("Thanh toán");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 724, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel debitCurrentLabel = new JLabel("Số tiền nợ:");
		debitCurrentLabel.setHorizontalAlignment(SwingConstants.CENTER);
		debitCurrentLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentLabel.setBounds(118, 77, 95, 35);
		contentPane.add(debitCurrentLabel);
		
		JLabel balanceLabel = new JLabel("Số tiền trong tài khoản:");
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceLabel.setBounds(128, 148, 154, 35);
		contentPane.add(balanceLabel);
		
		JLabel paymentMinimumLabel = new JLabel("Mức thanh toán:");
		paymentMinimumLabel.setHorizontalAlignment(SwingConstants.LEFT);
		paymentMinimumLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentMinimumLabel.setBounds(130, 234, 121, 35);
		contentPane.add(paymentMinimumLabel);
		
		JButton paymentButton = new JButton("Thanh toán");
		paymentButton.addActionListener(action);
		paymentButton.setBackground(Color.GREEN);
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentButton.setBounds(162, 325, 165, 77);
		contentPane.add(paymentButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.addActionListener(action);
		logoutButton.setBackground(Color.RED);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logoutButton.setBounds(398, 325, 165, 77);
		contentPane.add(logoutButton);
		
		paymentMinimumText = new JTextField();
		paymentMinimumText.setColumns(10);
		paymentMinimumText.setBounds(261, 228, 281, 51);
		contentPane.add(paymentMinimumText);
		
		debitCurrentText = new JLabel("200.000");
		debitCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentText.setBounds(204, 77, 95, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel("1.000.000");
		balanceCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		balanceCurrentText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceCurrentText.setBounds(292, 148, 95, 35);
		contentPane.add(balanceCurrentText);
		
		Managed_User.showPaymentUser(debitCurrentText, balanceCurrentText, Managed_Account.setAccount(AccountCurrent.getUsernameCurrent()));
	}
}
