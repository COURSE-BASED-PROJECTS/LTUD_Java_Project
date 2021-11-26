package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import controller.LoginController;
import model.Account;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private Account account;
	private JTextField tf_username;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		this.account = new Account();
		ActionListener action = new LoginController(this);
		setTitle("Login");	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 342);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsername.setBounds(55, 71, 84, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassword.setBounds(55, 140, 84, 37);
		contentPane.add(lblPassword);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tf_username.setBounds(196, 71, 313, 37);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(action);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnLogin.setBounds(253, 215, 99, 43);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		passwordField.setBounds(197, 140, 312, 37);
		contentPane.add(passwordField);
	}

	public Account getAccount() {
		account.setUserName(tf_username.getText().trim());
		account.setPassword(String.valueOf(passwordField.getPassword()).trim());
		return account;
	}
}
