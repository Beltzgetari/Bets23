package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Event;
import domain.User;
import domain.UserAbstract;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

	
    private static BLFacade appFacadeInterface;
	private static UserAbstract user;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	
	public static UserAbstract getUser() {
		UserAbstract u = appFacadeInterface.getUser(user);
		return u;
	}
	
	public static void setUser(UserAbstract u) {
		appFacadeInterface.setUser(u);
		user = appFacadeInterface.getUser(u);
	}
	
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jbttonskip;
	private JButton jbttonlogin;
	private JButton jbttonsign;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getPanel());
			jContentPane.add(getJbttonskip());
			jContentPane.add(getJbttonlogin());
			jContentPane.add(getJbttonsign());
		}
		return jContentPane;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(284, 5, 98, 21);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(99, 4, 79, 21);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(191, 5, 87, 21);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 190, 481, 63);
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	//private void redibujar() {
	//jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
	//JLabel jButtonQueryQueries = null;
	//	jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
	//JLabel jButtonCreateQuery = null;
	//	jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
	//	this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	//}
	
	private JButton getJbttonskip() {
		if (jbttonskip == null) {
			jbttonskip = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton.text"));
			jbttonskip.setBackground(new Color(255, 255, 255));
			jbttonskip.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MainGUI.this.setUser(null);
					MenuGUI menu=new MenuGUI();
					menu.setVisible(true);
					MainGUI.this.setVisible(false);
					
				}
			});
			jbttonskip.setBounds(174, 59, 129, 21);
		}
		return jbttonskip;
	}
	private JButton getJbttonlogin() {
		if (jbttonlogin == null) {
			jbttonlogin = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jbttonlogin.setBackground(new Color(255, 255, 255));
			jbttonlogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					LoginGUI login=new LoginGUI();
					login.setVisible(true);
					MainGUI.this.setVisible(false);
				}
			});
			jbttonlogin.setBounds(173, 102, 130, 21);
		}
		return jbttonlogin;
	}
	private JButton getJbttonsign() {
		if (jbttonsign == null) {
			jbttonsign = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnNewButton_2.text")); //$NON-NLS-1$ //$NON-NLS-2$
			jbttonsign.setBackground(new Color(255, 255, 255));
			jbttonsign.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					RegisterGUI register=new RegisterGUI();
					register.setVisible(true);
					MainGUI.this.setVisible(false);
				}
			});
			jbttonsign.setBounds(172, 151, 130, 21);
		}
		return jbttonsign;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jbttonlogin.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI_login"));
		jbttonskip.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI_skip"));
		jbttonsign.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI_sign"));
		//jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
} // @jve:decl-index=0:visual-constraint="0,0"

