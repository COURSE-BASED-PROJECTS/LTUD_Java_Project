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
	private JLabel minDebitText;
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
	public JLabel getMinDebitText() {
		return minDebitText;
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
	public PayDebitView() {
		ActionListener action = new PayDebitController(this);
		setTitle("Thanh toán");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 776, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel debitCurrentLabel = new JLabel("Số tiền nợ:");
		debitCurrentLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentLabel.setBounds(141, 74, 95, 35);
		contentPane.add(debitCurrentLabel);
		
		JLabel balanceLabel = new JLabel("Số tiền trong tài khoản:");
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceLabel.setBounds(141, 145, 159, 35);
		contentPane.add(balanceLabel);
		
		JLabel paymentMinimumLabel = new JLabel("Mức thanh toán:");
		paymentMinimumLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentMinimumLabel.setBounds(141, 232, 121, 35);
		contentPane.add(paymentMinimumLabel);
		
		JButton paymentButton = new JButton("Thanh toán");
		paymentButton.setForeground(new Color(255, 255, 255));
		paymentButton.addActionListener(action);
		paymentButton.setBackground(new Color(0, 204, 0));
		paymentButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		paymentButton.setBounds(164, 360, 165, 77);
		contentPane.add(paymentButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBounds(400, 360, 165, 77);
		contentPane.add(logoutButton);
		
		paymentMinimumText = new JTextField();
		paymentMinimumText.setFont(new Font("Tahoma", Font.BOLD, 12));
		paymentMinimumText.setColumns(10);
		paymentMinimumText.setBounds(310, 225, 276, 51);
		contentPane.add(paymentMinimumText);
		
		debitCurrentText = new JLabel("Debit");
		debitCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText.setFont(new Font("Tahoma", Font.BOLD, 15));
		debitCurrentText.setBounds(310, 74, 123, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel("Balance");
		balanceCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		balanceCurrentText.setFont(new Font("Tahoma", Font.BOLD, 15));
		balanceCurrentText.setBounds(310, 145, 143, 35);
		contentPane.add(balanceCurrentText);
		
		JLabel minDebtLabel = new JLabel("Hạn mức thanh toán tối thiểu:");
		minDebtLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		minDebtLabel.setBounds(141, 300, 220, 35);
		contentPane.add(minDebtLabel);
		
		minDebitText = new JLabel("minDebit");
		minDebitText.setHorizontalAlignment(SwingConstants.LEFT);
		minDebitText.setFont(new Font("Tahoma", Font.BOLD, 15));
		minDebitText.setBounds(373, 300, 123, 35);
		contentPane.add(minDebitText);
		
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
		
		Managed_User.showPaymentUser(debitCurrentText, balanceCurrentText, minDebitText, Managed_Account.setAccount(AccountCurrent.getUsernameCurrent()));
		

	}
}