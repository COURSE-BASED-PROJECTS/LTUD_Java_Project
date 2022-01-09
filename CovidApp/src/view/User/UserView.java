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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import controller.User.UserController;

public class UserView extends JFrame {

	private JPanel contentPane;
	private JButton InfoButton;
	private JButton buyPackage;
	private JButton paymentButton;
	private JButton logout;
	private JButton changePassButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
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
	public UserView() {
		ActionListener action = new UserController(this);
		setTitle("Người được quản lí");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 507);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		feature.setBounds(10, 57, 622, 297);
		contentPane.add(feature);
		feature.setLayout(null);
		
		InfoButton = new JButton("Thông tin cá nhân");
		InfoButton.addActionListener(action);
		InfoButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		InfoButton.setBounds(80, 50, 180, 70);
		feature.add(InfoButton);
		
		buyPackage = new JButton("Mua nhu yếu phẩm");
		buyPackage.addActionListener(action);
		buyPackage.setFont(new Font("Segoe UI", Font.BOLD, 15));
		buyPackage.setBounds(350, 50, 180, 70);
		feature.add(buyPackage);
		
		paymentButton = new JButton("Thanh toán");
		paymentButton.addActionListener(action);
		paymentButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		paymentButton.setBounds(80, 170, 180, 70);
		feature.add(paymentButton);
		
		changePassButton = new JButton("Đổi mật khẩu");
		changePassButton.addActionListener(action);
		changePassButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		changePassButton.setBounds(350, 170, 180, 70);
		feature.add(changePassButton);
		
		logout = new JButton("Đăng xuất");
		logout.setForeground(new Color(255, 255, 255));
		logout.addActionListener(action);
		logout.setBackground(new Color(255, 0, 51));
		logout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logout.setBounds(233, 385, 165, 50);
		contentPane.add(logout);
		
		JLabel lblSystem = new JLabel("HỆ THỐNG QUẢN LÝ COVID-19");
		lblSystem.setBounds(87, 5, 451, 27);
		contentPane.add(lblSystem);
		lblSystem.setForeground(new Color(0, 102, 51));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
