package view.Manager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import controller.Manager.StatisticInfoController;
import model.managed.Managed_Account;
import model.managed.Managed_Package;
import model.managed.Managed_Payment;
import model.managed.Managed_Status;

public class StatisticInfo extends JFrame {

	private JPanel contentPane;
	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticInfo frame = new StatisticInfo();
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
	public StatisticInfo() {
		ActionListener action = new StatisticInfoController(this);
		setTitle("Thống kê thông tin");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLocationRelativeTo(null);
		setBounds(100, 100, 1205, 810);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelStatus = new JPanel();
		panelStatus.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4\u0301ng k\u00EA tr\u1EA1ng th\u00E1i", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelStatus.setBounds(10, 10, 575, 350);
		contentPane.add(panelStatus);
		panelStatus.setLayout(new BorderLayout(0, 0));

		ChartPanel chartPanelStatus = new ChartPanel(createChart("Status"));
		chartPanelStatus.setPreferredSize(new java.awt.Dimension(560, 367));
		panelStatus.add(chartPanelStatus, BorderLayout.CENTER);
		panelStatus.validate();

		JPanel panelPackage = new JPanel();
		panelPackage.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4\u0301ng k\u00EA ti\u00EAu th\u1EE5 g\u00F3i nhu y\u1EBFu ph\u1EA9m", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelPackage.setBounds(610, 10, 575, 350);
		contentPane.add(panelPackage);
		panelPackage.setLayout(new BorderLayout(0, 0));

		ChartPanel chartPanelPackage = new ChartPanel(createChart("Package"));
		chartPanelPackage.setPreferredSize(new java.awt.Dimension(560, 367));
		panelPackage.add(chartPanelPackage, BorderLayout.CENTER);
		chartPanelPackage.validate();

		JPanel panelInfor = new JPanel();
		panelInfor.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4\u0301ng k\u00EA th\u00F4ng tin", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelInfor.setBounds(610, 375, 575, 350);
		contentPane.add(panelInfor);
		panelInfor.setLayout(new BorderLayout(0, 0));
		
		ChartPanel chartPanelInfor = new ChartPanel(createChart("Infor"));
		chartPanelInfor.setPreferredSize(new java.awt.Dimension(560, 367));
		panelInfor.add(chartPanelInfor, BorderLayout.CENTER);
		chartPanelInfor.validate();
		
		JPanel panelDebt = new JPanel();
		panelDebt.setBorder(new TitledBorder(
				new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)),
				"Th\u00F4\u0301ng k\u00EA d\u01B0 n\u1EE3", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(0, 0, 0)));
		panelDebt.setBounds(10, 375, 575, 350);
		contentPane.add(panelDebt);
		panelDebt.setLayout(new BorderLayout(0, 0));
		
		ChartPanel chartPanelDebt = new ChartPanel(createChart("Debt"));
		chartPanelDebt.setPreferredSize(new java.awt.Dimension(560, 367));
		panelDebt.add(chartPanelDebt, BorderLayout.CENTER);
		chartPanelDebt.validate();

		JButton outButton = new JButton("Thoát");
		outButton.setForeground(new Color(255, 255, 255));
		outButton.addActionListener(action);
		outButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		outButton.setBackground(new Color(255, 0, 51));
		outButton.setBounds(525, 735, 153, 35);
		contentPane.add(outButton);
	}

	public static JFreeChart createChart(String type) {
		JFreeChart chart = null;
		if (type.equals("Status")) {
			chart = ChartFactory.createBarChart("BIỂU ĐỒ TRẠNG THÁI", "Thời gian", "Số người", createDataset(type),
					PlotOrientation.VERTICAL, true, true, true);
		} else if (type.equals("Package")) {
			chart = ChartFactory.createBarChart("BIỂU ĐỒ TIÊU THỤ GÓI NHU YẾU PHẨM", "", "Số lượng",
					createDataset(type), PlotOrientation.VERTICAL, true, true, true);
		} else if (type.equals("Infor")) {
			chart = ChartFactory.createBarChart("BIỂU ĐỒ THÔNG TIN", "", "Số lượng",
					createDataset(type), PlotOrientation.VERTICAL, true, true, true);
		} else if (type.equals("Debt")) {
			chart = ChartFactory.createPieChart("CƠ CẤU DƯ NỢ", createPieDataset(), true, true, true);
		}
		return chart;
	}

	private static CategoryDataset createDataset(String type) {
		final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		if (type.equals("Status")) {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd/MM");
			 SimpleDateFormat formatterSql = new SimpleDateFormat("yyyy-MM-dd"); 
		        for (int i = 6; i >= 0; i--) {
		        	Calendar cal = Calendar.getInstance();
		        	cal.add(Calendar.DATE, -i);
		            Date date = cal.getTime();    
		            String fromdate = formatter.format(date);
		            String dateSql = formatterSql.format(date);
		            int numF0 = Managed_Status.getNumberOf("F0", dateSql);
		            int numF1 = Managed_Status.getNumberOf("F1", dateSql);
		            int numF2 = Managed_Status.getNumberOf("F2", dateSql);
		            int numF3 = Managed_Status.getNumberOf("F3", dateSql);
		            int numCured = Managed_Status.getNumberOf("Khỏi bệnh", dateSql);
		            
		            dataset.addValue(numF0, "F0", fromdate);
					dataset.addValue(numF1, "F1", fromdate);
					dataset.addValue(numF2, "F2", fromdate);
					dataset.addValue(numF3, "F3", fromdate);
					dataset.addValue(numCured, "Khỏi bệnh", fromdate);
				}				
		} else if (type.equals("Package")) {
			Vector<String> listId = Managed_Package.getListId();
			for (String id : listId) {
				int amount = Managed_Package.getAmountOfConsumption(id);
				dataset.addValue(amount, id, "");
			}

		} else if (type.equals("Infor")) {
			int numberOfStatusTransitions = Managed_Status.getNumberOfStatusTransitions();
			int numberOfCured = Managed_Status.getNumberOf("Khỏi bệnh");
			int numberOfF0 = Managed_Status.getNumberOf("F0");
			dataset.addValue(numberOfStatusTransitions, "Chuyển trạng thái", "");
			dataset.addValue(numberOfCured, "Khỏi bệnh", "");
			dataset.addValue(numberOfF0, "F0", "");
		}

		return dataset;
	}
	private static PieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        Double debt = Managed_Account.getTotalDebt();
        Double paid = Managed_Payment.getTotalPaid();
       
        dataset.setValue("Chưa thanh toán", debt);
        dataset.setValue("Đã thanh toán", paid);
        
        return dataset;
    }
}
