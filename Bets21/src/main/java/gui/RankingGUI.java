package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class RankingGUI extends JFrame {

	private JPanel contentPane;
	private JLabel positionchange= new JLabel();;
	private JLabel yourposition = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourPosition"));;
	private JLabel RankingLabel= new JLabel("Ranking:");;
	private JButton BackBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack"));
	private  DefaultTableModel model = new DefaultTableModel();
	private JScrollPane scrollPane;
	private final JTable table = new JTable();
	private String[] columnNames = new String[] { ResourceBundle.getBundle("Etiquetas").getString("Puesto"),
												  ResourceBundle.getBundle("Etiquetas").getString("User"),
												  ResourceBundle.getBundle("Etiquetas").getString("MoneyWonRanking") };

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RankingGUI frame = new RankingGUI();
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
	public RankingGUI() {
		this.setTitle("Ranking");
		User us = (User) MainGUI.getUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		positionchange.setBounds(93, 10, 45, 13);
		contentPane.add(positionchange);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 33, 332, 174);
		contentPane.add(scrollPane);
		
		table.setModel(model);
		//table.getColumnModel().getColumn(0).setPreferredWidth(20);
			
		scrollPane.setViewportView(table);
		
		model.setDataVector(null, columnNames);
		model.setColumnCount(3);
		
		int i = 1;
		for(User u: MainGUI.getBusinessLogic().getRanking()) {
			if(u.getUsername().equals(MainGUI.getUser().getUsername()))
				positionchange.setText(((Integer)i).toString());
			Vector<Object> row = new Vector<Object>();
			System.out.println(i);
			row.add(i);
			row.add(u);
			row.add(u.getStatistics().getWinMoney());
			model.addRow(row);
			i++;
		}

		yourposition.setBounds(10, 10, 107, 13);
		contentPane.add(yourposition);
		
		RankingLabel.setBounds(10, 33, 146, 13);
		contentPane.add(RankingLabel);
		
		BackBtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RankingGUI.this.setVisible(false);
				StatisticsGUI statistics = new StatisticsGUI();
				statistics.setVisible(true);
			}
		});
		BackBtton.setForeground(new Color(255, 255, 255));
		BackBtton.setBackground(new Color(0, 0, 0));
		BackBtton.setBounds(166, 217, 129, 36);
		contentPane.add(BackBtton);
	}
}
