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

public class ManagerView extends JFrame {

	private JPanel contentPane;

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
		setTitle("Manager");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 656, 507);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel feature = new JPanel();
		feature.setBorder(new TitledBorder(null, "Features", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		feature.setBounds(10, 10, 622, 313);
		contentPane.add(feature);
		feature.setLayout(null);
		
		JButton manageUser = new JButton("Manage Users");
		manageUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		manageUser.setBounds(87, 56, 165, 77);
		feature.add(manageUser);
		
		JButton managePackage = new JButton("Manage Packages");
		managePackage.setFont(new Font("Tahoma", Font.PLAIN, 15));
		managePackage.setBounds(361, 56, 165, 77);
		feature.add(managePackage);
		
		JButton statisticInfo = new JButton("Information Statistics");
		statisticInfo.setFont(new Font("Tahoma", Font.PLAIN, 15));
		statisticInfo.setBounds(87, 173, 439, 77);
		feature.add(statisticInfo);
		
		JButton logout = new JButton("Log out");
		logout.setBackground(Color.RED);
		logout.setFont(new Font("Tahoma", Font.PLAIN, 15));
		logout.setBounds(238, 357, 165, 77);
		contentPane.add(logout);
	}
}
