package view.Manager;

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

import controller.Manager.ManagerController;

public class ManagerView extends JFrame {

	private JPanel contentPane;
	private JButton manageUser;
	private JButton managePackage;
	private JButton statisticInfo;
	private JButton logout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerView frame = new ManagerView();
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
	public ManagerView() {
		ActionListener action = new ManagerController(this);
		setTitle("Manager");
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
		feature.setBounds(10, 67, 622, 313);
		contentPane.add(feature);
		feature.setLayout(null);
		
		manageUser = new JButton("Quản lí người dùng");
		manageUser.addActionListener(action);
		manageUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
		manageUser.setBounds(87, 56, 200, 80);
		feature.add(manageUser);
		
		managePackage = new JButton("Quản lí nhu yếu phẩm");
		managePackage.addActionListener(action);
		managePackage.setFont(new Font("Segoe UI", Font.BOLD, 15));
		managePackage.setBounds(361, 56, 200, 80);
		feature.add(managePackage);
		
		statisticInfo = new JButton("Thống kê thông tin");
		statisticInfo.addActionListener(action);
		statisticInfo.setFont(new Font("Segoe UI", Font.BOLD, 15));
		statisticInfo.setBounds(87, 173, 474, 80);
		feature.add(statisticInfo);
		
		logout = new JButton("Đăng xuất");
		logout.setForeground(new Color(255, 255, 255));
		logout.addActionListener(action);
		logout.setBackground(new Color(255, 0, 51));
		logout.setFont(new Font("Segoe UI", Font.BOLD, 18));
		logout.setBounds(238, 399, 165, 50);
		contentPane.add(logout);
		
		JLabel lblSystem = new JLabel("HỆ THỐNG QUẢN LÝ COVID-19");
		lblSystem.setBounds(51, 5, 522, 27);
		contentPane.add(lblSystem);
		lblSystem.setForeground(new Color(0, 102, 51));
		lblSystem.setHorizontalAlignment(SwingConstants.CENTER);
		lblSystem.setFont(new Font("Segoe UI", Font.BOLD, 20));
	}
}
