package view.User;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import controller.User.ChangePasswordController;

public class ChangePasswordView extends JFrame {

	private JPanel contentPane;
	private JTextField newPassAgainText;
	private JTextField newPassText;
	private JTextField oldPassText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChangePasswordView frame = new ChangePasswordView();
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
	public ChangePasswordView() {
		ActionListener action = new ChangePasswordController(this);
		setTitle("Đổi mật khẩu");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 776, 537);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel oldPasslabel = new JLabel("Nhập mật khẩu cũ:");
		oldPasslabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		oldPasslabel.setBounds(91, 77, 185, 35);
		contentPane.add(oldPasslabel);
		
		JLabel newPasslabel = new JLabel("Nhập mật khẩu mới:");
		newPasslabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		newPasslabel.setBounds(91, 152, 185, 35);
		contentPane.add(newPasslabel);
		
		JLabel newPassAgainlabel = new JLabel("Xác nhận mật khẩu mới:");
		newPassAgainlabel.setHorizontalAlignment(SwingConstants.LEFT);
		newPassAgainlabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		newPassAgainlabel.setBounds(91, 228, 185, 35);
		contentPane.add(newPassAgainlabel);
		
		JButton changeButton = new JButton("Đổi mật khẩu");
		changeButton.setForeground(new Color(255, 255, 255));
		changeButton.addActionListener(action);
		changeButton.setBackground(new Color(0, 204, 0));
		changeButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		changeButton.setBounds(180, 373, 165, 77);
		contentPane.add(changeButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBounds(420, 373, 165, 77);
		contentPane.add(logoutButton);
		
		newPassAgainText = new JTextField();
		newPassAgainText.setColumns(10);
		newPassAgainText.setBounds(286, 220, 343, 45);
		contentPane.add(newPassAgainText);
		
		newPassText = new JTextField();
		newPassText.setColumns(10);
		newPassText.setBounds(286, 145, 343, 45);
		contentPane.add(newPassText);
		
		oldPassText = new JTextField();
		oldPassText.setColumns(10);
		oldPassText.setBounds(286, 70, 343, 45);
		contentPane.add(oldPassText);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "\u0110\u1ED5i m\u1EADt kh\u1EA9u", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(48, 29, 671, 292);
		contentPane.add(panel);
		
	}
	public JPanel getContentPane() {
		return contentPane;
	}
	public JTextField getNewPassAgainText() {
		return newPassAgainText;
	}
	public JTextField getNewPassText() {
		return newPassText;
	}
	public JTextField getOldPassText() {
		return oldPassText;
	}
}
