package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.Admin.AdminController;

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 507);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		feature.setBounds(10, 10, 622, 313);
		contentPane.add(feature);
		feature.setLayout(null);
		
		createButton = new JButton("Tạo tài khoản");
		createButton.addActionListener(action);
		createButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		createButton.setBounds(87, 56, 165, 77);
		feature.add(createButton);
		
		manageAccount = new JButton("Quản lí tài khoản");
		manageAccount.addActionListener(action);
		manageAccount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manageAccount.setBounds(361, 56, 165, 77);
		feature.add(manageAccount);
		
		manageLockdown = new JButton("Quản lí nơi điều trị/cách li");
		manageLockdown.addActionListener(action);
		manageLockdown.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manageLockdown.setBounds(87, 173, 439, 77);
		feature.add(manageLockdown);
		
		logout = new JButton("Đăng xuất");
		logout.addActionListener(action);
		logout.setBackground(Color.RED);
		logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logout.setBounds(238, 357, 165, 77);
		contentPane.add(logout);
	}
}
