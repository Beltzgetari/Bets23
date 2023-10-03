package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class StatisticsGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnBack;
	private JLabel lblNewLabel_7= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AmountOfBets"));;
	private JLabel lblNewLabel_6 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("VictoryRatio"));;
	private JLabel lblNewLabel_5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WinMoney"));
	private JComboBox comboBoxFollows = new JComboBox();
	private DefaultComboBoxModel<User> model = new DefaultComboBoxModel<User>();
	private JLabel lblNewLabel_4 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FollowedUser"));
	private JLabel lblNewLabel_3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("You"));
	private JLabel lblNewLabel_2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("VictoryRatio"));
	private JLabel lblNewLabel_1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AmountOfBets"));
	private JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WinMoney"));
	private JLabel lblwinyou = new JLabel();
	private JLabel lblwinfollow = new JLabel();
	private JLabel lblratiofollow = new JLabel();
	private JLabel lblratioyou= new JLabel();
	private JLabel lblbetsyou = new JLabel();
	private JLabel lblbetsfollow= new JLabel();
	private JButton bttnRanking =new JButton("Ranking");
	User us = (User)MainGUI.getUser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsGUI frame = new StatisticsGUI();
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
	public StatisticsGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Statistics"));
		us = (User)MainGUI.getUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 452, 317);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		lblNewLabel.setBounds(20, 43, 132, 21);
		contentPane.add(lblNewLabel);
		
		
		lblNewLabel_1.setBounds(20, 74, 132, 17);
		contentPane.add(lblNewLabel_1);
		
		
		lblNewLabel_2.setBounds(20, 59, 151, 20);
		contentPane.add(lblNewLabel_2);
		
		
		lblNewLabel_3.setBounds(10, 20, 45, 13);
		contentPane.add(lblNewLabel_3);
		

		lblNewLabel_4.setBounds(10, 112, 142, 13);
		contentPane.add(lblNewLabel_4);
		comboBoxFollows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				us = (User) MainGUI.getUser();
				User usFollow = (User) comboBoxFollows.getSelectedItem();
				lblwinfollow.setText(((Float)usFollow.getStatistics().getWinMoney()).toString());
				lblratiofollow.setText(((Float)usFollow.getStatistics().getVictoryRatio()).toString() + " %");
				lblbetsfollow.setText(((Integer)usFollow.getStatistics().getAmountOfBets()).toString());
			}
		});
		
	
		comboBoxFollows.setBounds(162, 108, 108, 21);
		contentPane.add(comboBoxFollows);

		model.addAll(MainGUI.getBusinessLogic().getJarraituak(us));
		comboBoxFollows.setModel(model);
		
		lblNewLabel_5.setBounds(20, 135, 132, 13);
		contentPane.add(lblNewLabel_5);
		
		lblNewLabel_6.setBounds(20, 150, 173, 13);
		contentPane.add(lblNewLabel_6);
		
	
		lblNewLabel_7.setBounds(20, 166, 173, 13);
		contentPane.add(lblNewLabel_7);
		
		btnBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack"));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsGUI.this.setVisible(false);
				MenuGUI menu = new MenuGUI();
				menu.setVisible(true);
			}
		});
		bttnRanking.setForeground(new Color(255, 255, 255));
		bttnRanking.setBackground(new Color(0, 0, 0));
		btnBack.setBounds(65, 217, 128, 36);
		contentPane.add(btnBack);
		
		bttnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				StatisticsGUI.this.setVisible(false);
				RankingGUI ranking = new RankingGUI();
				ranking.setVisible(true);
			}
		});
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(255, 255, 255));
		bttnRanking.setBounds(265, 217, 128, 36);
		contentPane.add(bttnRanking);
		
		lblwinyou.setBounds(181, 47, 159, 13);
		contentPane.add(lblwinyou);
		lblwinyou.setText(((Float)us.getStatistics().getWinMoney()).toString());
		
		lblwinfollow.setBounds(181, 135, 159, 13);
		contentPane.add(lblwinfollow);
		
		lblratiofollow.setBounds(181, 150, 132, 13);
		contentPane.add(lblratiofollow);
		
		lblratioyou.setBounds(181, 63, 100, 13);
		contentPane.add(lblratioyou);
		lblratioyou.setText(((Float)us.getStatistics().getVictoryRatio()).toString() + " %");
		
		lblbetsyou.setBounds(181, 76, 111, 13);
		contentPane.add(lblbetsyou);
		lblbetsyou.setText(((Integer)us.getStatistics().getAmountOfBets()).toString());
		
		lblbetsfollow.setBounds(181, 166, 108, 13);
		contentPane.add(lblbetsfollow);
	}
}
