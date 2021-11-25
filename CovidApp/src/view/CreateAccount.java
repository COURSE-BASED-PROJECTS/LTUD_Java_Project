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

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	public CreateAccount() {
		setTitle("Tạo tài khoản");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 724, 481);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Tài khoản:");
		usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernameLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		usernameLabel.setBounds(128, 77, 95, 35);
		contentPane.add(usernameLabel);
		
		JLabel passLabel = new JLabel("Mật khẩu:");
		passLabel.setHorizontalAlignment(SwingConstants.CENTER);
		passLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		passLabel.setBounds(128, 148, 95, 35);
		contentPane.add(passLabel);
		
		JLabel ClassLabel = new JLabel("Phân hệ:");
		ClassLabel.setHorizontalAlignment(SwingConstants.CENTER);
		ClassLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ClassLabel.setBounds(128, 234, 95, 35);
		contentPane.add(ClassLabel);
		
		JButton createButton = new JButton("Tạo tài khoản");
		createButton.setBackground(Color.GREEN);
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createButton.setBounds(111, 325, 165, 77);
		contentPane.add(createButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setBackground(Color.RED);
		logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logoutButton.setBounds(398, 325, 165, 77);
		contentPane.add(logoutButton);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(223, 71, 281, 51);
		contentPane.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(223, 148, 281, 51);
		contentPane.add(textField_1);
		
		JComboBox district = new JComboBox();
		district.setModel(new DefaultComboBoxModel(new String[] {"Quản lí", "Người bị quản lí"}));
		district.setToolTipText("");
		district.setName("");
		district.setFont(new Font("Tahoma", Font.PLAIN, 15));
		district.setBounds(223, 226, 132, 51);
		contentPane.add(district);
	}
}
