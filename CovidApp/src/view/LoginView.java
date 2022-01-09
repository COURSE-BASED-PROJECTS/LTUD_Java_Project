package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import controller.LoginController;
import model.Account;
import javax.swing.SwingConstants;

public class LoginView extends JFrame {

	private JPanel contentPane;
	private Account account;
	private static JTextField tf_username;
	private JPasswordField passwordField;
	static final Logger logger = LogManager.getLogger(LoginView.class);
	
	public static String get_username() {
		return tf_username.getText();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		this.account = new Account();
		ActionListener action = new LoginController(this);
		setTitle("Đăng nhập");	
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 776, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin = new JLabel("Đăng nhập");
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setForeground(Color.BLACK);
		lblLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		lblLogin.setBounds(140, 64, 140, 37);
		contentPane.add(lblLogin);
		
		JLabel lblUsername = new JLabel("Tài khoản");
		lblUsername.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblUsername.setBounds(37, 145, 84, 37);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Mật khẩu");
		lblPassword.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblPassword.setBounds(37, 239, 84, 37);
		contentPane.add(lblPassword);
		
		tf_username = new JTextField();
		tf_username.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		tf_username.setBounds(37, 192, 348, 37);
		contentPane.add(tf_username);
		tf_username.setColumns(10);
		
		JButton btnLogin = new JButton("Đăng nhập");
		btnLogin.setBackground(new Color(102, 0, 255));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.addActionListener(action);
		btnLogin.setFont(new Font("Segoe UI", Font.BOLD, 25));
		btnLogin.setBounds(37, 374, 348, 43);
		contentPane.add(btnLogin);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		passwordField.setBounds(38, 286, 347, 37);
		contentPane.add(passwordField);
		

		BufferedImage myPicture;
		try {
			myPicture = ImageIO.read(new File("icons/5K-1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			picLabel.setBounds(430, 0, 332, 500);
			getContentPane().add(picLabel);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Account getAccount() {
		account.setUserName(tf_username.getText());
		account.setPassword(String.valueOf(passwordField.getPassword()));
		return account;
	}
}
