package view.User;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import controller.User.PayDebitController;
import model.AccountCurrent;
import model.managed.Managed_Account;
import model.managed.Managed_Payment;
import model.managed.Managed_User;

public class PayDebitView extends JFrame {

	private JPanel contentPane;
	private JTextField paymentMinimumText;
	private JLabel debitCurrentText;
	private JLabel balanceCurrentText;

	public JLabel getBalanceCurrentText() {
		return balanceCurrentText;
	}
	public JTextField getPaymentMinimumText() {
		return paymentMinimumText;
	}
	public JLabel getDebitCurrentText() {
		return debitCurrentText;
	}
	public void setBalanceCurrentText(String curr) {
		balanceCurrentText.setText(curr);;
	}
	public void setDebitCurrentText(String curr) {
		balanceCurrentText.setText(curr);;
	}
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
		setIconImage(new ImageIcon("icons/main.png").getImage());
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
		balanceLabel.setBounds(128, 148, 159, 35);
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
		paymentMinimumText.setFont(new Font("Tahoma", Font.BOLD, 12));
		paymentMinimumText.setColumns(10);
		paymentMinimumText.setBounds(261, 228, 281, 51);
		contentPane.add(paymentMinimumText);
		
		debitCurrentText = new JLabel("Debit");
		debitCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText.setFont(new Font("Tahoma", Font.BOLD, 15));
		debitCurrentText.setBounds(204, 77, 123, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel("Balance");
		balanceCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		balanceCurrentText.setFont(new Font("Tahoma", Font.BOLD, 15));
		balanceCurrentText.setBounds(292, 148, 143, 35);
		contentPane.add(balanceCurrentText);
		
		((AbstractDocument)paymentMinimumText.getDocument()).setDocumentFilter(new DocumentFilter(){
	        Pattern regEx = Pattern.compile("\\d*");

	        @Override
	        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {          
	            Matcher matcher = regEx.matcher(text);
	            if(!matcher.matches()){
	                return;
	            }
	            super.replace(fb, offset, length, text, attrs);
	        }
	    });
		
		Managed_User.showPaymentUser(debitCurrentText, balanceCurrentText, Managed_Account.setAccount(AccountCurrent.getUsernameCurrent()));
	}
}