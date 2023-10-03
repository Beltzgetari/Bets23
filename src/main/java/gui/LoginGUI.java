package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;
import javax.swing.SwingConstants;
import domain.User;
import domain.UserAbstract;
import domain.UserAdmin;

import java.awt.Color;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JLabel passwlabel;
	private JLabel usernamelabel;
	private JLabel lblError;
	private JButton signbutton;
	private JButton loginbutton;
	private JButton backbutton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private static String arrToString(char[] a)
    {
        String string = new String(a);
        return string;
    }

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_login"));
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		loginbutton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_login"));
		loginbutton.setForeground(new Color(255, 255, 255));
		loginbutton.setBackground(new Color(0, 0, 0));
		
		textField = new JTextField();
		textField.setBounds(176, 39, 174, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		usernamelabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_username"));
		usernamelabel.setHorizontalAlignment(SwingConstants.CENTER);
		usernamelabel.setBounds(10, 42, 146, 13);
		contentPane.add(usernamelabel);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(176, 102, 174, 19);
		contentPane.add(passwordField);
		
		passwlabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_password"));
		passwlabel.setHorizontalAlignment(SwingConstants.CENTER);
		passwlabel.setBounds(10, 105, 146, 13);
		contentPane.add(passwlabel);
		
		signbutton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_sign"));
		signbutton.setForeground(new Color(255, 255, 255));
		signbutton.setBackground(new Color(0, 0, 0));
		signbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterGUI r = new RegisterGUI();
				r.setVisible(true);
				LoginGUI.this.setVisible(false);
			}
		});
		signbutton.setBounds(10, 204, 135, 21);
		contentPane.add(signbutton);
		
		lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(45, 181, 357, 13);
		contentPane.add(lblError);
		
		loginbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(passwordField.getPassword().length == 0 || textField.getText().length() == 0) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_error"));
				} else {
					if (MainGUI.getBusinessLogic().isLogin(textField.getText(), arrToString(passwordField.getPassword()))){
						UserAbstract us = MainGUI.getBusinessLogic().getUserByName(textField.getText());
						MainGUI.setUser(us);
						if(us instanceof UserAdmin) {
							AdminGUI a = new AdminGUI();
							a.setVisible(true);
						} else {
							MenuGUI m = new MenuGUI();
							m.setVisible(true);
						}
						LoginGUI.this.setVisible(false);
					}else {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_error2"));
					}
				}
			}
			
		});
		loginbutton.setBounds(155, 204, 132, 21);
		contentPane.add(loginbutton);
		
		backbutton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_back"));
		backbutton.setBackground(new Color(255, 255, 255));
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI m = new MainGUI();
				m.setVisible(true);
				LoginGUI.this.setVisible(false);
			}
		});
		backbutton.setBounds(297, 204, 129, 21);
		contentPane.add(backbutton);
	}
	
	private void redibujar2() {
		passwlabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_password"));
		usernamelabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_username"));
		lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_error"));
		signbutton.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_sign"));
		loginbutton.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_login"));
		backbutton.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_back"));
	}
}