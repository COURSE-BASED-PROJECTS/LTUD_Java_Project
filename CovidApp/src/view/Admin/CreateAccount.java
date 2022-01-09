package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import controller.Admin.CreateAccountController;

public class CreateAccount extends JFrame {

	private JPanel contentPane;
	private JLabel usernameLabel;
	private JLabel passLabel;
	private JTextField usernameText;
	private JTextField passText;
	private JComboBox district;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreateAccount frame = new CreateAccount();
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
	@SuppressWarnings("unchecked")
	public CreateAccount() {
		ActionListener action = new CreateAccountController(this);
		setTitle("Tạo tài khoản");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 724, 439);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		usernameLabel = new JLabel("Tài khoản:");
		usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		usernameLabel.setBounds(128, 64, 95, 35);
		contentPane.add(usernameLabel);
		
		passLabel = new JLabel("Mật khẩu:");
		passLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		passLabel.setBounds(128, 145, 95, 35);
		contentPane.add(passLabel);
		
		JLabel ClassLabel = new JLabel("Phân hệ:");
		ClassLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		ClassLabel.setBounds(128, 226, 95, 35);
		contentPane.add(ClassLabel);
		
		JButton createButton = new JButton("Tạo tài khoản");
		createButton.setForeground(new Color(255, 255, 255));
		createButton.addActionListener(action);
		createButton.setBackground(new Color(0, 204, 51));
		createButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		createButton.setBounds(128, 325, 165, 50);
		contentPane.add(createButton);
		
		JButton logoutButton = new JButton("Thoát");
		logoutButton.setForeground(new Color(255, 255, 255));
		logoutButton.addActionListener(action);
		logoutButton.setBackground(new Color(255, 0, 51));
		logoutButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logoutButton.setBounds(398, 325, 165, 50);
		contentPane.add(logoutButton);
		
		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBounds(223, 62, 281, 40);
		contentPane.add(usernameText);
		
		passText = new JTextField();
		passText.setColumns(10);
		passText.setBounds(223, 142, 281, 40);
		contentPane.add(passText);
		
		district = new JComboBox();
		district.setModel(new DefaultComboBoxModel(new String[] {"Quản lí", "Người được quản lí"}));
		district.setToolTipText("");
		district.setName("");
		district.setFont(new Font("Segoe UI", Font.PLAIN, 15));
		district.setBounds(223, 222, 170, 40);
		contentPane.add(district);
	}
	
	public JLabel getUsernameLabel() {
		return usernameLabel;
	}
	public JLabel getPassLabel() {
		return passLabel;
	}
	public JTextField getUsernameText() {
		return usernameText;
    }
	public JTextField getPassText() {
		return passText;
    }
	public int getRole() {
		return district.getSelectedIndex();
	}
}