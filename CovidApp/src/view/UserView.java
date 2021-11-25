package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSplitPane;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class UserView extends JFrame {

	private JPanel contentPane;
	private JButton InfoButton;
	private JButton buyPackage;
	private JButton paymentButton;
	private JButton logout;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserView frame = new UserView();
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
	public UserView() {
		setTitle("Người được quản lí");
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
		
		InfoButton = new JButton("Thông tin cá nhân");
		InfoButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		InfoButton.setBounds(87, 56, 165, 77);
		feature.add(InfoButton);
		
		buyPackage = new JButton("Mua nhu yếu phẩm");
		buyPackage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		buyPackage.setBounds(361, 56, 165, 77);
		feature.add(buyPackage);
		
		paymentButton = new JButton("Thanh toán");
		paymentButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		paymentButton.setBounds(87, 173, 439, 77);
		feature.add(paymentButton);
		
		logout = new JButton("Đăng xuất");
		logout.setBackground(Color.RED);
		logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logout.setBounds(238, 357, 165, 77);
		contentPane.add(logout);
	}
}
