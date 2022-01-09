package view.Admin;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.attribute.AttributeSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.DocumentFilter.FilterBypass;

import controller.Admin.ManagerZonesController;
import model.managed.Managed_Zone;

@SuppressWarnings("serial")
public class ManagerZones extends JFrame {

	private JPanel contentPane;
	private JPanel listZone;
	private JScrollPane scrollPaneZone;
	private JTable tableListZone;
	private DefaultTableModel tableModel;
	private Managed_Zone mz;
	
	// Declare columns of the table Users
    private String [] columnNames = new String [] {
    		"Mã KCL","Tên", "Sức chứa", "Số lượng tiếp nhận hiện tại"};
    private JPanel zoneForm;
    private JTextField idText;
    private JTextField nameZoneText;
    private JTextField limitText;
    private JTextField slotText;
    private JLabel slotLabel;
    private JPanel utilities;
    private JButton addButton;
    private JButton updateButton;
    private JButton delButton;
    private JButton clearButton;
    private JButton saveButton;
    private JSeparator separator_Exit;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerZones frame = new ManagerZones();
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
	public ManagerZones() {
		ActionListener action = new ManagerZonesController(this);
		setTitle("Quản lí địa điểm điều trị/cách li");
		setIconImage(new ImageIcon("icons/main.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 893, 705);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		listZone = new JPanel();
		listZone.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Danh sa\u0301ch \u0111i\u0323a \u0111i\u00EA\u0309m \u0111i\u00EA\u0300u tri\u0323/ca\u0301ch li", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		listZone.setBounds(10, 10, 859, 253);
		contentPane.add(listZone);
		listZone.setLayout(null);
		
		scrollPaneZone = new JScrollPane();
		scrollPaneZone.setBounds(10, 20, 839, 223);
		listZone.add(scrollPaneZone);
		
		tableListZone = new JTable();
		tableListZone.getTableHeader().setReorderingAllowed(false);
		tableListZone.setDefaultEditor(Object.class,null);
		tableListZone.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tableListZone.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				((ManagerZonesController) action).displayData();
			}
		});
		scrollPaneZone.setViewportView(tableListZone);
		tableListZone.setModel(Managed_Zone.showZoneTable(initialRow()));
		
		zoneForm = new JPanel();
		zoneForm.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Th\u00F4ng tin go\u0301i \u0111i\u0323a \u0111i\u00EA\u0309m", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		zoneForm.setBounds(10, 273, 859, 385);
		contentPane.add(zoneForm);
		zoneForm.setLayout(null);
		
		JLabel idLabel = new JLabel("Mã KCL:");
		idLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		idLabel.setHorizontalAlignment(SwingConstants.LEFT);
		idLabel.setBounds(60, 55, 90, 35);
		zoneForm.add(idLabel);

		idText = new JTextField();
		idText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		idText.setHorizontalAlignment(SwingConstants.LEFT);
		idText.setEditable(false);
		idText.setBounds(138, 50, 124, 45);
		zoneForm.add(idText);
		idText.setColumns(10);
		
		JLabel nameZoneLabel = new JLabel("Tên KCL:");
		nameZoneLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		nameZoneLabel.setHorizontalAlignment(SwingConstants.LEFT);
		nameZoneLabel.setBounds(343, 55, 78, 35);
		zoneForm.add(nameZoneLabel);
		
		nameZoneText = new JTextField();
		nameZoneText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		nameZoneText.setHorizontalAlignment(SwingConstants.LEFT);
		nameZoneText.setEditable(false);
		nameZoneText.setBounds(431, 50, 350, 45);
		nameZoneText.setColumns(10);
		zoneForm.add(nameZoneText);
		
		JLabel limitLabel = new JLabel("Số lượng tiếp nhận hiện tại:");
		limitLabel.setHorizontalAlignment(SwingConstants.LEFT);
		limitLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		limitLabel.setBounds(343, 135, 200, 35);
		zoneForm.add(limitLabel);
		
		limitText = new JTextField();
		limitText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		limitText.setHorizontalAlignment(SwingConstants.LEFT);
		limitText.setEditable(false);
		limitText.setColumns(10);
		limitText.setBounds(138, 130, 124, 45);
		zoneForm.add(limitText);
		
		slotLabel = new JLabel("Sức chứa:");
		slotLabel.setHorizontalAlignment(SwingConstants.LEFT);
		slotLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		slotLabel.setBounds(60, 136, 90, 35);
		zoneForm.add(slotLabel);
		
		slotText = new JTextField();
		slotText.setFont(new Font("Tahoma", Font.PLAIN, 12));
		slotText.setHorizontalAlignment(SwingConstants.LEFT);
		slotText.setEditable(false);
		slotText.setColumns(10);
		slotText.setBounds(541, 129, 124, 45);
		zoneForm.add(slotText);
		
		utilities = new JPanel();
		utilities.setBorder(new TitledBorder(null, "Ti\u0301nh n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		utilities.setBounds(29, 233, 573, 129);
		utilities.setLayout(null);
		zoneForm.add(utilities);

		addButton = new JButton("Thêm");
		addButton.addActionListener(action);
		addButton.setForeground(new Color(255, 255, 255));
		addButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		addButton.setBackground(new Color(0, 153, 255));
		addButton.setBounds(50, 24, 95, 35);
		utilities.add(addButton);
		
		updateButton = new JButton("Sửa");
		updateButton.setForeground(new Color(255, 255, 255));
		updateButton.addActionListener(action);
		updateButton.setBackground(new Color(255, 204, 51));
		updateButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		updateButton.setBounds(298, 24, 95, 35);
		utilities.add(updateButton);
		
		delButton = new JButton("Xóa");
		delButton.setForeground(new Color(255, 255, 255));		
		delButton.addActionListener(action);
		delButton.setBackground(new Color(255, 0, 51));
		delButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		delButton.setBounds(170, 24, 95, 35);
		utilities.add(delButton);
		
		clearButton = new JButton("Đặt lại");
		clearButton.setForeground(new Color(255, 255, 255));
		clearButton.setBackground(new Color(153, 153, 153));
		clearButton.addActionListener(action);
		clearButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		clearButton.setBounds(427, 24, 95, 35);
		utilities.add(clearButton);
		
		saveButton = new JButton("Lưu");
		saveButton.setForeground(new Color(255, 255, 255));
		saveButton.addActionListener(action);
		saveButton.setBackground(new Color(0, 204, 0));
		saveButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
		saveButton.setBounds(174, 84, 219, 35);
		utilities.add(saveButton);
		
		separator_Exit = new JSeparator();
		separator_Exit.setOrientation(SwingConstants.VERTICAL);
		separator_Exit.setBounds(624, 240, 18, 110);
		zoneForm.add(separator_Exit);
		
		JButton outButton = new JButton("Thoát");
		outButton.setForeground(new Color(255, 255, 255));
		outButton.addActionListener(action);
		outButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
		outButton.setBackground(new Color(255, 0, 51));
		outButton.setBounds(652, 260, 143, 70);
		zoneForm.add(outButton);
	}
	
	public DefaultTableModel initialRow() {
		DefaultTableModel defaultTableModel = new DefaultTableModel();
		
		for(int i=0;i<columnNames.length;i++) {
			defaultTableModel.addColumn(columnNames[i]);
		}
		
		return defaultTableModel;
	}
	public JPanel getContentPane(){
		return contentPane;
	}
	public JPanel getListZone() {
		return listZone;
	}
	public JScrollPane getScrollPaneZone() {
		return scrollPaneZone;
	}
	public JTable getTableListZone() {
		return tableListZone;
	}
	public DefaultTableModel getTableModel() {
		return tableModel;
	}
	public Managed_Zone getManagedZone() {
		return mz;
	}
    public JPanel getZoneForm() {
    	return zoneForm;
    }
	public JTextField getIdText() {
		return idText;
	}
    public JTextField getNameZoneText() {
    	return nameZoneText;
    }
    public JTextField getLimitText() {
    	return limitText;
    }
    public JLabel getSlotLabel() {
    	return slotLabel;
    }
    public JTextField getSlotText() {
    	return slotText;
    }
    public JPanel getUtilities() {
    	return utilities;
    }
    public JButton getAddButton() {
    	return addButton;
    }
    public JButton getUpdateButton() {
    	return updateButton;
    }
    public JButton getDelButton() {
    	return delButton;
    }
    public JButton getClearButton() {
    	return clearButton;
    }
    public JButton getSaveButton() {
    	return saveButton;
    }
    public void loadData() {
    	tableListZone.setModel(Managed_Zone.showZoneTable(initialRow()));
	}
}
