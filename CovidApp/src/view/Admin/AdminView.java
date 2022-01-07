package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Admin.AdminController;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class AdminView extends JFrame {

	private JPanel contentPane;
	private JButton createButton;
	private JButton manageAccount;
	private JButton manageLockdown;
	private JButton logout;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminView frame = new AdminView();
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
	public AdminView() {
		ActionListener action = new AdminController(this);
		setTitle("Admin");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 466);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		feature.setBounds(10, 37, 615, 312);
		contentPane.add(feature);
		feature.setLayout(null);
		
		createButton = new JButton("Tạo tài khoản");
		createButton.addActionListener(action);
		createButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		createButton.setBounds(83, 55, 165, 77);
		feature.add(createButton);
		
		manageAccount = new JButton("Quản lí tài khoản");
		manageAccount.addActionListener(action);
		manageAccount.setFont(new Font("Segoe UI", Font.BOLD, 15));
		manageAccount.setBounds(357, 55, 165, 77);
		feature.add(manageAccount);
		
		manageLockdown = new JButton("Quản lí nơi điều trị/cách li");
		manageLockdown.addActionListener(action);
		manageLockdown.setFont(new Font("Segoe UI", Font.BOLD, 15));
		manageLockdown.setBounds(83, 172, 439, 77);
		feature.add(manageLockdown);
		
		logout = new JButton("Đăng xuất");
		logout.setForeground(Color.WHITE);
		logout.addActionListener(action);
		logout.setBackground(new Color(255, 0, 51));
		logout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logout.setBounds(240, 359, 165, 50);
		contentPane.add(logout);
		
		JLabel lblSystem = new JLabel("HỆ THỐNG QUẢN LÝ COVID-19");
		lblSystem.setBounds(51, 5, 522, 27);
		contentPane.add(lblSystem);
		lblSystem.setForeground(new Color(0, 102, 51));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
