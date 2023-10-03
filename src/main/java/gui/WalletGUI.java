package gui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Movement;
import domain.User;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class WalletGUI extends JFrame {

	private JPanel contentPane;
	private JTextField PutTakeField;
	private JButton jBttonBack;
	private JButton jBttonTake = new JButton(ResourceBundle.getBundle("Etiquetas").getString("TakeMoney"));;
	private JButton jBttonPut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutMoney"));;
	private JLabel jLabelMoney;
	private JLabel jLabelAmounttoPut;
	private JLabel jLabelAmountMoney;
	private JLabel labelerror = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterErrorWallet"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WalletGUI frame = new WalletGUI();
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
	public WalletGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("WalletBtton"));
		User us1 = (User) MainGUI.getUser();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		jBttonBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack"));
		jBttonBack.setBounds(168, 205, 119, 48);
		jBttonBack.setBackground(new Color(255, 255, 255));
		jBttonBack.setForeground(new Color(0, 0, 0));
		jBttonBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WalletGUI.this.setVisible(false);
				MenuGUI menu = new MenuGUI();
				menu.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(jBttonBack);
		labelerror= new JLabel();
		labelerror.setBounds(20, 162, 395, 13);
		contentPane.add(labelerror);
		setContentPane(contentPane);

		/*if (us1 == null) {

			jBttonTake.setVisible(false);
			jBttonPut.setVisible(false);
			labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterErrorWallet"));
			jLabelMoney = new JLabel("0.0");
		} else {  */
			
			jLabelAmountMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchMoney"));
			jLabelAmountMoney.setBounds(20, 93, 198, 13);
			contentPane.add(jLabelAmountMoney);

			jLabelAmounttoPut = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money"));
			jLabelAmounttoPut.setBounds(133, 126, 70, 13);
			contentPane.add(jLabelAmounttoPut);

			jLabelMoney = new JLabel(String.valueOf(us1.getWallet()));

			jLabelMoney.setBounds(228, 86, 153, 27);
			contentPane.add(jLabelMoney);

			PutTakeField = new JTextField();
			PutTakeField.setBounds(228, 123, 153, 19);
			contentPane.add(PutTakeField);
			PutTakeField.setColumns(10);

			

			jBttonTake.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String putake = PutTakeField.getText();

					if (putake.length() == 0) {
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterErrorWalletDiruKantitatea"));
					} else {
						try {
							float money = Float.parseFloat(putake);
							if(money < 0) {
								labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Nega"));
							} else {
								User us = (User) MainGUI.getUser();
								boolean b = MainGUI.getBusinessLogic().takeMoney(money, us,
										ResourceBundle.getBundle("Etiquetas").getString("MoneyOutWallet"));
								if (b) {
									us.setWallet(us.getWallet() - Float.parseFloat(putake));
									jLabelMoney.setText(String.valueOf(us.getWallet()));
								}
								if(money <= us.getWallet()) {
									System.out.println("Movement created");
									Movement move = new Movement("DiruaKarteratik", money);
									us.getMovements().add(move);
									MainGUI.setUser(us);
								} else {
									labelerror.setText("Not enough money in the wallet");
								}
							}
						} catch (NumberFormatException l) {
							labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorWalletypeMissmatch"));
						}
					}

				}
			});
			jBttonTake.setBounds(296, 205, 130, 48);
			jBttonTake.setBackground(new Color(0, 0, 0));
			jBttonTake.setForeground(new Color(255, 255, 255));
			contentPane.add(jBttonTake);

			jBttonPut = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutMoney"));
			jBttonPut.setBackground(new Color(0, 0, 0));
			jBttonPut.setForeground(new Color(255, 255, 255));
			jBttonPut.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String putake = PutTakeField.getText();

					if (putake.length() == 0) {
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterErrorWalletDiruKantitatea"));
					} else {
						try {
							float money = Float.parseFloat(putake);
							if (money < 0 ) {
								labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Nega"));
							}else {
								User us = (User) MainGUI.getUser();
								boolean b = MainGUI.getBusinessLogic().putMoney(Float.parseFloat(putake), us, "DiruaKarteran");
								if (b) {
									us.setWallet(us.getWallet() + Float.parseFloat(putake));
									jLabelMoney.setText(String.valueOf(us.getWallet()));
								}
								Movement move = new Movement("DiruaKarteran", money);
								us.getMovements().add(move);
								MainGUI.setUser(us);
							}
						} catch (NumberFormatException l) {
							labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorWalletypeMissmatch"));
						}
					}

				}
			});
			jBttonPut.setBounds(10, 205, 145, 48);
			contentPane.add(jBttonPut);
			contentPane.add(labelerror);
		}
	//}
}

			