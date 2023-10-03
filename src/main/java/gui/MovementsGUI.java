package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.Movement;
import domain.User;

import javax.swing.JLabel;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MovementsGUI extends JFrame {

	private JPanel contentPane;
	private JList list;
	private JLabel jLabelMovements;
	private DefaultListModel<Movement> model = new DefaultListModel<Movement>();
	private JLabel labelError;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MovementsGUI frame = new MovementsGUI();
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
	public MovementsGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Movements"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		jLabelMovements = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Movements"));
		jLabelMovements.setBounds(39, 10, 103, 13);
		contentPane.add(jLabelMovements);

		list = new JList();
		User us = (User) MainGUI.getUser();
		// System.out.println(us.getMovements().get(0).getDesc());
		if (us != null) {
			for (Movement m : us.getMovements()) {
				if (m.getDesc().equals("DiruaApustuarentzako")) {
					m.setDesc(ResourceBundle.getBundle("Etiquetas").getString("MoneyForBet"));
				} else if (m.getDesc().equals("ApustuaIrabazi")) {
					m.setDesc(ResourceBundle.getBundle("Etiquetas").getString("WinABet"));
				} else if (m.getDesc().equals("DiruaKarteran")) {
					m.setDesc(ResourceBundle.getBundle("Etiquetas").getString("MoneyInWallet"));
				} else {
					m.setDesc(ResourceBundle.getBundle("Etiquetas").getString("MoneyOutWallet"));
				}
//				Movement mov = MainGUI.getBusinessLogic().getMovement(m);
//				System.out.println(mov.getDesc());
				model.addElement(m);
			}
		} else {
			labelError = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterErrorWallet"));
		}
		list.setModel(model);
		list.setBounds(72, 33, 308, 162);
		contentPane.add(list);

		JButton backbtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack")); //$NON-NLS-1$ //$NON-NLS-2$
		backbtton.setBackground(Color.white);
		backbtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGUI menu = new MenuGUI();
				menu.setVisible(true);
				MovementsGUI.this.setVisible(false);
			}
		});
		backbtton.setBounds(162, 205, 124, 35);
		contentPane.add(backbtton);

		labelError = new JLabel();
		labelError.setBounds(152, 10, 134, 13);
		contentPane.add(labelError);

	}
}
