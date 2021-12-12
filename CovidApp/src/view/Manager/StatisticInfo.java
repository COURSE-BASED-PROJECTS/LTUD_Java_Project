package view.Manager;

import java.awt.BorderLayout;
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

public class StatisticInfo extends JFrame {

	private JPanel contentPane;
	private String[] columnNames = new String[] { "Thời gian", "Số Người F0", "Số Người F1", "Số Người F2",
			"Số chuyển trạng thái", "Số người khỏi bệnh", "Số gói NYP tiêu thụ", "Số tiền dư nợ" };

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
		outButton.addActionListener(action);
		outButton.setFont(new Font("Tahoma", Font.PLAIN, 12));
		outButton.setBackground(Color.RED);
		outButton.setBounds(1052, 734, 125, 35);
		contentPane.add(outButton);
	}

	private DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();

		for (int i = 0; i < columnNames.length; i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}

		return defaultTableModel;
	}

	public static JFreeChart createChart(String type) {
		JFreeChart chart = null;
		if (type.equals("Status")) {
			chart = ChartFactory.createBarChart("BIỂU ĐỒ TRẠNG THÁI", "Thời gian", "Số người", createDataset(type),
					PlotOrientation.VERTICAL, true, true, true);
		} else if (type.equals("Package")) {
			chart = ChartFactory.createBarChart("BIỂU ĐỒ TIÊU THỤ GÓI NYP", "", "Số lượng",
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
			dataset.addValue(100, "F0", "2021-12-11");
			dataset.addValue(50, "F1", "2021-12-11");
			dataset.addValue(100, "F2", "2021-12-11");
			dataset.addValue(50, "F3", "2021-12-11");
			dataset.addValue(50, "Khỏi bệnh", "2021-12-11");

			dataset.addValue(200, "F0", "2021-12-12");
			dataset.addValue(250, "F1", "2021-12-12");
			dataset.addValue(300, "F2", "2021-12-12");
			dataset.addValue(400, "F3", "2021-12-12");
			dataset.addValue(50, "Khỏi bệnh", "2021-12-12");
			
		} else if (type.equals("Package")) {
			dataset.addValue(10, "NYP01", "");
			dataset.addValue(20, "NYP02", "");
			dataset.addValue(15, "NYP03", "");
			dataset.addValue(4, "NYP04", "");

		} else if (type.equals("Infor")) {
			dataset.addValue(100, "Chuyển trạng thái", "");
			dataset.addValue(20, "Khỏi bệnh", "");
			dataset.addValue(50, "F0", "");
		}

		return dataset;
	}
	private static PieDataset createPieDataset() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        dataset.setValue("Đã thanh toán", new Double(34.0));
        dataset.setValue("Chưa thanh toán", new Double(66.0));
        return dataset;
    }
}
