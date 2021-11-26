package view;

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
		
		manageUser = new JButton("Quản lí người dùng");
		manageUser.addActionListener(action);
		manageUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manageUser.setBounds(87, 56, 165, 77);
		feature.add(manageUser);
		
		managePackage = new JButton("Quản lí nhu yếu phẩm");
		managePackage.addActionListener(action);
		managePackage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		managePackage.setBounds(361, 56, 165, 77);
		feature.add(managePackage);
		
		statisticInfo = new JButton("Thống kê thông tin");
		statisticInfo.addActionListener(action);
		statisticInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statisticInfo.setBounds(87, 173, 439, 77);
		feature.add(statisticInfo);
		
		logout = new JButton("Đăng xuất");
		logout.addActionListener(action);
		logout.setBackground(Color.RED);
		logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logout.setBounds(238, 357, 165, 77);
		contentPane.add(logout);
	}
}
