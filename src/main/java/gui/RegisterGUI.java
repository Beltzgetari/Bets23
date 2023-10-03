package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import domain.User;
import domain.UserAbstract;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JPasswordField;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField NameField;
	private JTextField AgeField;
	private JTextField EmailField;
	private JTextField UserNameField;
	private JPasswordField passwordField;
	private JPasswordField PassConfField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
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
	
	JButton SignInButton;
	JButton BackButton;
	JLabel NameLabel;
	JLabel AgeLabel;
	JLabel EmailLabel;
	JLabel UserNameLabel;
	JLabel PasswordLabel;
	JLabel PassConfLabel;
	JButton Login;
	
	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_sign"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 679, 517);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		SignInButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_sign"));
		SignInButton.setBounds(285, 419, 140, 48);
		SignInButton.setForeground(Color.WHITE);
		SignInButton.setBackground(new Color(0, 0, 0));
		
		
		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(150, 369, 357, 13);
		contentPane.add(lblError);
		
		
		
		
		
		SignInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(NameField.getText().length() == 0 || AgeField.getText().length() == 0 || EmailField.getText().length() == 0 || UserNameField.getText().length() == 0 || passwordField.getPassword().length == 0 || PassConfField.getPassword().length == 0) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error1"));
				} else {
					try {
						int age = Integer.parseInt(AgeField.getText());
						if(age < 18)
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error2"));
						else if(!arrToString(passwordField.getPassword()).equals(arrToString(PassConfField.getPassword())))
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error3"));
						else {
							boolean b = MainGUI.getBusinessLogic().register(UserNameField.getText(), arrToString(passwordField.getPassword()), NameField.getText(), age);
							if (!b) {
								User us=(User) MainGUI.getBusinessLogic().getUserByName(UserNameField.getText());
								//MainGUI.getBusinessLogic().setUser(us);
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error4"));
							} else {
								UserAbstract us = MainGUI.getBusinessLogic().getUserByName(UserNameField.getText());
								MainGUI.setUser(us);
								MenuGUI m = new MenuGUI();
								m.setVisible(true);
								RegisterGUI.this.setVisible(false);
							}
						}
					} catch (NumberFormatException execption) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error5"));
					}
				}
			}
		});

		
		contentPane.setLayout(null);
		contentPane.add(SignInButton);
		
		BackButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_back"));
		BackButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main=new MainGUI();
				main.setVisible(true);
				RegisterGUI.this.setVisible(false);
			}
		});
		BackButton.setBackground(new Color(255, 255, 255));
		BackButton.setBounds(466, 419, 140, 48);
		contentPane.add(BackButton);
		
		NameField = new JTextField();
		NameField.setBounds(150, 24, 478, 38);
		contentPane.add(NameField);
		NameField.setColumns(10);
		
		NameLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_name"));
		NameLabel.setBounds(10, 24, 130, 38);
		contentPane.add(NameLabel);
		
		AgeField = new JTextField();
		AgeField.setColumns(10);
		AgeField.setBounds(150, 73, 478, 38);
		contentPane.add(AgeField);
		
		AgeLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_age"));
		AgeLabel.setBounds(10, 73, 130, 38);
		contentPane.add(AgeLabel);
		
		EmailField = new JTextField();
		EmailField.setColumns(10);
		EmailField.setBounds(150, 131, 478, 38);
		contentPane.add(EmailField);
		
		UserNameField = new JTextField();
		UserNameField.setColumns(10);
		UserNameField.setBounds(150, 186, 478, 38);
		contentPane.add(UserNameField);
		
		EmailLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_email"));
		EmailLabel.setBounds(10, 130, 130, 38);
		contentPane.add(EmailLabel);
		
		UserNameLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_username"));
		UserNameLabel.setBounds(10, 185, 130, 38);
		contentPane.add(UserNameLabel);
		
		PasswordLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_password"));
		PasswordLabel.setBounds(10, 243, 130, 38);
		contentPane.add(PasswordLabel);
		
		PassConfLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_confPassword"));
		PassConfLabel.setBounds(10, 302, 130, 38);
		contentPane.add(PassConfLabel);
		
		passwordField = new JPasswordField();
		passwordField.setToolTipText("");
		passwordField.setBounds(150, 244, 478, 38);
		contentPane.add(passwordField);
		
		PassConfField = new JPasswordField();
		PassConfField.setBounds(150, 303, 478, 38);
		contentPane.add(PassConfField);
		
		Login = new JButton(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_login"));
		Login.setBackground(new Color(255, 255, 255));
		Login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				LoginGUI login=new LoginGUI();
				login.setVisible(true);
				RegisterGUI.this.setVisible(false);
			}
		});
		Login.setBounds(105, 419, 140, 48);
		contentPane.add(Login);
		
	}
	
	private void redibujar2() {
		PasswordLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_password"));
		PassConfLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_password"));
		UserNameLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_username"));
		NameLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_name"));
		AgeLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_age"));
		EmailLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_email"));
		Login.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_login"));
		BackButton.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_back"));
		SignInButton.setText(ResourceBundle.getBundle("Etiquetas").getString("LoginGUI_sign"));
	}
}

