package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class MenuGUI extends JFrame {

	private JPanel contentPane;
	private JButton ReturnBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ReturnBtton"));
	private JButton WalletBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBtton"));
	private JButton BetBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BetBtton"));
	JButton SeeMovementsBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeMovementsBtton")); //$NON-NLS-1$ //$NON-NLS-2$
	private JButton chatBtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ChatBtton"));;
	private final JButton Followsbtton = new JButton(); // $NON-NLS-1$ //$NON-NLS-2$
	private JButton statisticsBttn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Statistics"));
	private JButton findQuestionsbtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestion"));

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGUI frame = new MenuGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MenuGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Menu"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(WalletBtton);
		contentPane.setLayout(null);
		contentPane.add(ReturnBtton);
		setContentPane(contentPane);
		WalletBtton.setBounds(266, 81, 131, 38);
		BetBtton.setForeground(new Color(255, 255, 255));
		BetBtton.setBackground(new Color(0, 0, 0));
		BetBtton.setBounds(29, 81, 131, 38);
		contentPane.add(BetBtton);
		WalletBtton.setBackground(Color.BLACK);
		WalletBtton.setForeground(Color.WHITE);
		ReturnBtton.setBackground(Color.white);
		SeeMovementsBtton.setForeground(Color.WHITE);
		SeeMovementsBtton.setBackground(Color.BLACK);
		ReturnBtton.setBounds(29, 202, 131, 38);
		SeeMovementsBtton.setBounds(29, 140, 131, 38);
		contentPane.add(SeeMovementsBtton);
		findQuestionsbtton.setBounds(266, 140, 131, 38);
		contentPane.add(findQuestionsbtton);
		chatBtton.setBounds(266, 24, 131, 38);
		contentPane.add(chatBtton);
		Followsbtton.setText(ResourceBundle.getBundle("Etiquetas").getString("Follow")); //$NON-NLS-1$ //$NON-NLS-2$
		Followsbtton.setForeground(new Color(255, 255, 255));
		Followsbtton.setBackground(new Color(0, 0, 0));

		chatBtton.setBackground(new Color(0, 0, 0));
		chatBtton.setForeground(new Color(255, 255, 255));
		chatBtton.setText(ResourceBundle.getBundle("Etiquetas").getString("ChatBtton"));

		findQuestionsbtton.setForeground(Color.WHITE);
		findQuestionsbtton.setBackground(Color.BLACK);
		Followsbtton.setBounds(29, 24, 131, 38);
		statisticsBttn.setForeground(new Color(255, 255, 255));
		statisticsBttn.setBackground(new Color(0, 0, 0));
		statisticsBttn.setBounds(266, 202, 131, 38);
		contentPane.add(statisticsBttn);
		contentPane.add(Followsbtton);
		ReturnBtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main = new MainGUI();
				main.setVisible(true);
				MenuGUI.this.setVisible(false);
			}
		});
		findQuestionsbtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindQuestionsGUI findquestions = new FindQuestionsGUI();
				findquestions.setVisible(true);
				MenuGUI.this.setVisible(false);

			}
		});
		if (MainGUI.getUser() == null) {
			chatBtton.setEnabled(false);
			WalletBtton.setEnabled(false);
			SeeMovementsBtton.setEnabled(false);
			Followsbtton.setEnabled(false);
			statisticsBttn.setEnabled(false);
			BetBtton.setEnabled(false);
			ReturnBtton.setText(ResourceBundle.getBundle("Etiquetas").getString("WalletBack"));
		} else {

			BetBtton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BetGUI bet = new BetGUI();
					bet.setVisible(true);
					MenuGUI.this.setVisible(false);

				}
			});

			WalletBtton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					WalletGUI wallet = new WalletGUI();
					wallet.setVisible(true);
					MenuGUI.this.setVisible(false);

				}
			});

			SeeMovementsBtton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MovementsGUI movements = new MovementsGUI();
					movements.setVisible(true);
					MenuGUI.this.setVisible(false);
				}
			});

			
			// $NON-NLS-1$ //$NON-NLS-2$
			

			Followsbtton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FollowsGUI follow = new FollowsGUI();
					follow.setVisible(true);
					MenuGUI.this.setVisible(false);
				}
			});

			statisticsBttn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					StatisticsGUI stats = new StatisticsGUI();
					stats.setVisible(true);
					MenuGUI.this.setVisible(false);
				}
			});

		}
	}
}