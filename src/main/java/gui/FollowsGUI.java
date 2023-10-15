package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.Color;

public class FollowsGUI extends JFrame {

	private JPanel contentPane;
	private JLabel labelUnfollows;
	private JLabel labelFollows;
	private JComboBox comboBoxFollows;
	private final DefaultComboBoxModel followsmodel = new DefaultComboBoxModel();
	private JComboBox comboBoxUnfollows;
	private final DefaultComboBoxModel unfollowsmodel = new DefaultComboBoxModel();
	private JButton btnFollow = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnFollow"));
	private JButton btnUnfollow = new JButton(ResourceBundle.getBundle("Etiquetas").getString("btnUnfollow"));
	private JButton btnBack;
	private JTextField kantitatemax;
	private JButton maximoaBttn;
	private JLabel labelkop;
	private JLabel labeldirua;
	private JLabel labelerror;
	private float max;
	private ArrayList<User> listajarraituak = new ArrayList<User>();
	private ArrayList<User> listajarraitzaileak = new ArrayList<User>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FollowsGUI frame = new FollowsGUI();
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
	public FollowsGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
		btnFollow.setEnabled(false);
		btnUnfollow.setEnabled(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		labelUnfollows = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Unfollows"));
		labelUnfollows.setBounds(33, 60, 117, 13);
		contentPane.add(labelUnfollows);

		labelFollows = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Follows")); //$NON-NLS-1$ //$NON-NLS-2$
		labelFollows.setBounds(239, 60, 117, 13);
		contentPane.add(labelFollows);
		User us = (User) MainGUI.getUser();
		max = us.getBetmax();
		comboBoxFollows = new JComboBox();
		comboBoxFollows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnFollow.setEnabled(true);
			}
		});
		followsmodel.addAll(MainGUI.getBusinessLogic().getUnfollows(us));
		comboBoxFollows.setModel(followsmodel);
		comboBoxFollows.setBounds(33, 83, 174, 21);
		contentPane.add(comboBoxFollows);

		comboBoxUnfollows = new JComboBox();
		comboBoxUnfollows.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnUnfollow.setEnabled(true);
			}
		});
		unfollowsmodel.addAll(MainGUI.getBusinessLogic().getJarraituak(us));
		comboBoxUnfollows.setModel(unfollowsmodel);
		comboBoxUnfollows.setBounds(239, 86, 187, 21);
		contentPane.add(comboBoxUnfollows);

		btnFollow.setForeground(new Color(255, 255, 255));
		btnFollow.setBackground(new Color(0, 0, 0));
		btnFollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (comboBoxFollows.getSelectedItem() == null) {
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Aukeratunorjarraitunahiduzun"));
				} else {

					if (max > 0) {
						if (!listajarraituak.contains((User) comboBoxFollows.getSelectedItem())) {
							MainGUI.getBusinessLogic().addFollow((User) comboBoxFollows.getSelectedItem(), us);
							listajarraituak.add((User) comboBoxFollows.getSelectedItem());
							unfollowsmodel.addAll(listajarraituak);
							followsmodel.removeElement((User) comboBoxFollows.getSelectedItem());
							btnFollow.setEnabled(false);
							
						} else {
							labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Yaseguido"));
						}
					} else {
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Máximo"));
					}

				}
			}
		});
		btnFollow.setBounds(32, 129, 174, 36);
		contentPane.add(btnFollow);

		btnUnfollow.setForeground(new Color(255, 255, 255));
		btnUnfollow.setBackground(new Color(0, 0, 0));
		btnUnfollow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBoxUnfollows.getSelectedItem() == null) {
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Aukeratunorjarraitunahiduzun"));
				} else {
					if (!listajarraitzaileak.contains((User) comboBoxUnfollows.getSelectedItem())) {
						MainGUI.getBusinessLogic().removeFollow((User) comboBoxUnfollows.getSelectedItem(), us);
						listajarraitzaileak.add((User) comboBoxUnfollows.getSelectedItem());
						followsmodel.addAll(listajarraitzaileak);
						unfollowsmodel.removeElement((User) comboBoxUnfollows.getSelectedItem());
						btnUnfollow.setEnabled(false);
					} else {
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Yanoseguido"));
					}
				}
			}
		});
		btnUnfollow.setBounds(239, 129, 184, 36);
		contentPane.add(btnUnfollow);

		btnBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEventBack")); //$NON-NLS-1$ //$NON-NLS-2$
		btnBack.setForeground(new Color(0, 0, 0));
		btnBack.setBackground(new Color(255, 255, 255));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGUI menu = new MenuGUI();
				menu.setVisible(true);
				FollowsGUI.this.setVisible(false);
			}
		});
		btnBack.setBounds(163, 187, 135, 36);
		contentPane.add(btnBack);

		labelkop = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("maxKop"));
		labelkop.setBounds(10, 22, 140, 13);
		contentPane.add(labelkop);

		kantitatemax = new JTextField();
		kantitatemax.setBounds(330, 19, 96, 19);
		contentPane.add(kantitatemax);
		kantitatemax.setColumns(10);

		maximoaBttn = new JButton(ResourceBundle.getBundle("Etiquetas").getString("max"));
		maximoaBttn.setBackground(new Color(255, 255, 255));
		maximoaBttn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				labelerror.setText("");
				if (kantitatemax.getText().length() == 0) {
					labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("Máximo"));
				} else {
					try {
						if (Float.parseFloat(kantitatemax.getText()) < 0) {
							labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
						} else {
							MainGUI.getBusinessLogic().changemax((User) MainGUI.getUser(),
									Float.parseFloat(kantitatemax.getText()));
							max = Float.parseFloat(kantitatemax.getText());
							labeldirua.setText(kantitatemax.getText());
						}

					} catch (NumberFormatException h) {
						labelerror.setText(ResourceBundle.getBundle("Etiquetas").getString("ZenbakiIzan"));
					}
				}
			}
		});
		maximoaBttn.setBounds(183, 18, 140, 21);
		contentPane.add(maximoaBttn);
		labeldirua = new JLabel();
		labeldirua.setText(String.valueOf(us.getBetmax()));
		labeldirua.setBounds(144, 22, 45, 13);
		contentPane.add(labeldirua);

		labelerror = new JLabel();
		labelerror.setBounds(97, 233, 242, 13);
		contentPane.add(labelerror);
	}
}
