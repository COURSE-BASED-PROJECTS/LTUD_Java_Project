package PaymentSystem;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.PaymentSystem.PaymentSystemController;
import controller.User.PayDebitController;
import model.AccountCurrent;
import model.Payment_History;
import model.managed.Managed_Account;
import model.managed.Managed_PaymentSystem;
import model.managed.Managed_User;
import utils.DatabaseConnect;

import javax.swing.border.TitledBorder;
import javax.swing.text.BadLocationException;

@SuppressWarnings("serial")
public class PaymentSystemView extends JFrame {

	private JPanel contentPane;
	private JLabel debitCurrentText;
	private JLabel balanceCurrentText;
	private String balance = Managed_PaymentSystem.getBalance("Admin_Payment");
	public JPanel getContentPane() {
		return contentPane;
	}
	public JLabel DebitCurrentText() {
		return debitCurrentText;
	}
	public JLabel getBalanceCurrentText() {
		return balanceCurrentText;
	}
	public String getBalance() {
		return balance;
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
		setBounds(100, 100, 724, 326);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idAdmin = new JLabel("ID:");
		idAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		idAdmin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idAdmin.setBounds(118, 77, 95, 35);
		contentPane.add(idAdmin);
		
		JLabel balanceLabel = new JLabel("Số dư:");
		balanceLabel.setHorizontalAlignment(SwingConstants.LEFT);
		balanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceLabel.setBounds(369, 77, 165, 35);
		contentPane.add(balanceLabel);
		
		JButton paymentButton = new JButton("Thêm người được quản lí");
		paymentButton.addActionListener(action);
		paymentButton.setBackground(Color.GREEN);
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentButton.setBounds(136, 137, 212, 77);
		contentPane.add(paymentButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.addActionListener(action);
		logoutButton.setBackground(Color.RED);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logoutButton.setBounds(369, 137, 165, 77);
		contentPane.add(logoutButton);
		
		debitCurrentText = new JLabel("QuanTriThanhToan");
		debitCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		debitCurrentText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		debitCurrentText.setBounds(182, 77, 130, 35);
		contentPane.add(debitCurrentText);
		
		balanceCurrentText = new JLabel(balance);
		balanceCurrentText.setHorizontalAlignment(SwingConstants.LEFT);
		balanceCurrentText.setFont(new Font("Tahoma", Font.PLAIN, 15));
		balanceCurrentText.setBounds(422, 77, 130, 35);
		contentPane.add(balanceCurrentText);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Qu\u1EA3n l\u00ED thanh to\u00E1n", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(94, 54, 503, 187);
		contentPane.add(panel);
	}
}