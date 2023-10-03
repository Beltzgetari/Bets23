package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class AdminGUI extends JFrame {

	private JPanel contentPane;
	private JButton jbtnCreateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEventButton"));
	private JButton jbtnCreateQuestion = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestionButton"));
	private JButton jbtnCreateQuote = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuoteButton"));
	private JButton jbtnBack= new JButton(ResourceBundle.getBundle("Etiquetas").getString("ReturnBtton"));
	private final JButton ChatBtton = new JButton();
	private final JButton bttonCopy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CopyEvent"));; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-1$ //$NON-NLS-2$

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI frame = new AdminGUI();
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
	public AdminGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Administration"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		jbtnCreateEvent.setForeground(Color.WHITE);
		jbtnCreateEvent.setBackground(Color.BLACK);
		
		
		jbtnCreateEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateEventGUI createevent=new CreateEventGUI();
                createevent.setVisible(true);
                AdminGUI.this.setVisible(false);
			}
		});
		jbtnCreateEvent.setBounds(74, 79, 132, 37);
		contentPane.add(jbtnCreateEvent);
		jbtnCreateQuote.setForeground(Color.WHITE);
		jbtnCreateQuote.setBackground(Color.BLACK);
		
	
		jbtnCreateQuote.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateQuoteGUI createquote=new CreateQuoteGUI();
                createquote.setVisible(true);
                AdminGUI.this.setVisible(false);
			}
		});
		jbtnCreateQuote.setBounds(253, 79, 132, 37);
		contentPane.add(jbtnCreateQuote);
		jbtnCreateQuestion.setForeground(Color.WHITE);
		jbtnCreateQuestion.setBackground(Color.BLACK);
		jbtnCreateQuestion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreateQuestionGUI createquestion = new CreateQuestionGUI(MainGUI.getBusinessLogic().getEvents(null));
				createquestion.setVisible(true);
				AdminGUI.this.setVisible(false);
			}
		});
		
		jbtnCreateQuestion.setBounds(74, 139, 132, 37);
		contentPane.add(jbtnCreateQuestion);
		jbtnBack.setBackground(new Color(255, 255, 255));
		jbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainGUI main=new MainGUI();
                main.setVisible(true);
                AdminGUI.this.setVisible(false);
			}
		});
		
		jbtnBack.setBounds(74, 192, 132, 37);
		contentPane.add(jbtnBack);
		
		JButton jBttonResult = new JButton(ResourceBundle.getBundle("Etiquetas").getString("AddResult")); //$NON-NLS-1$ //$NON-NLS-2$
		jBttonResult.setForeground(Color.WHITE);
		jBttonResult.setBackground(Color.BLACK);
		jBttonResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddResultGUI addresult= new AddResultGUI();
				addresult.setVisible(true);
				AdminGUI.this.setVisible(false);
			}
		});
		jBttonResult.setBounds(253, 139, 132, 37);
		contentPane.add(jBttonResult);
		
		JButton jBttonDeleteEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteEvent")); //$NON-NLS-1$ //$NON-NLS-2$
		jBttonDeleteEvent.setForeground(Color.WHITE);
		jBttonDeleteEvent.setBackground(Color.BLACK);
		jBttonDeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteEventGUI deletevent= new DeleteEventGUI();
				deletevent.setVisible(true);
				AdminGUI.this.setVisible(false);
			}
		});
		jBttonDeleteEvent.setBounds(253, 192, 132, 37);
		contentPane.add(jBttonDeleteEvent);
		
		
		contentPane.add(ChatBtton);
		bttonCopy.setForeground(new Color(255, 255, 255));
		bttonCopy.setBackground(new Color(0, 0, 0));
		bttonCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopyEventGUI copy = new CopyEventGUI();
				copy.setVisible(true);
				AdminGUI.this.setVisible(false);
			}
		});
		bttonCopy.setBounds(253, 22, 132, 37);
		
		
		contentPane.add(bttonCopy);
	}
}
