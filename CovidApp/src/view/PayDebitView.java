package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

public class PayDebitView extends JFrame {

	private JPanel contentPane;
	private JTextField paymentMinimumText;

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
		paymentButton.setBackground(Color.GREEN);
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentButton.setBounds(162, 325, 165, 77);
		contentPane.add(paymentButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setBackground(Color.RED);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logoutButton.setBounds(398, 325, 165, 77);
		contentPane.add(logoutButton);
		
		paymentMinimumText = new JTextField();
		paymentMinimumText.setColumns(10);
		paymentMinimumText.setBounds(261, 228, 281, 51);
		contentPane.add(paymentMinimumText);
		
		JLabel debitCurrentText = new JLabel("200.000");
		debitCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentText.setBounds(204, 77, 95, 35);
		contentPane.add(debitCurrentText);
		
		JLabel debitCurrentText_1 = new JLabel("1.000.000");
		debitCurrentText_1.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentText_1.setBounds(292, 148, 95, 35);
		contentPane.add(debitCurrentText_1);
	}
}
