package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.*;
import domain.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

public class BetGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries"));
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events"));
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;

	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JTable tableQuotes = new JTable();

	private String[] columnNamesEvents = new String[] { ResourceBundle.getBundle("Etiquetas").getString("EventN"),
			ResourceBundle.getBundle("Etiquetas").getString("Event"),

	};
	private String[] columnNamesQueries = new String[] { ResourceBundle.getBundle("Etiquetas").getString("QueryN"),
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton betbtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BetBtton")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton backbutton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BackQuote")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField BetField = new JTextField();

	private JLabel labelMoney;
	private final JLabel labelQuote = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quotes")); //$NON-NLS-1$ //$NON-NLS-2$

	private JComboBox eventsComboBox = new JComboBox();
	private final DefaultComboBoxModel eventsmodel = new DefaultComboBoxModel();
	private JComboBox questionsComboBox = new JComboBox();
	private final DefaultComboBoxModel questionsmodel = new DefaultComboBoxModel();
	private JComboBox quotesComboBox = new JComboBox();
	private final DefaultComboBoxModel quotesmodel = new DefaultComboBoxModel();

	private JLabel LabelError;

	private JButton btnAddBet = new JButton("Add Bet");;

	public BetGUI() {
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("BetBtton"));
		BetField.setBounds(464, 297, 96, 19);
		BetField.setColumns(10);
		BetField.setVisible(true);
		LabelError = new JLabel();
		LabelError.setBounds(86, 245, 455, 13);
		getContentPane().add(LabelError);
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {
	
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		betbtton.setEnabled(false);
		btnAddBet.setEnabled(false);
		Vector<Quote> quotes = new Vector<Quote>();
		BLFacade facade = MainGUI.getBusinessLogic();
		backbutton.setBackground(new Color(255, 255, 255));
		backbutton.setBounds(120, 353, 140, 51);
		getContentPane().add(backbutton);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuGUI menu = new MenuGUI();
				menu.setVisible(true);
				BetGUI.this.setVisible(false);

			}
		});
	/*	if (facade.getUser() == null) {
			jLabelEventDate.setVisible(false);
			jLabelQueries.setVisible(false);
			jLabelEvents.setVisible(false);
			jCalendar1.setVisible(false);
			jLabelEventDate.setVisible(false);
			jLabelQueries.setVisible(false);
			jLabelEvents.setVisible(false);
		} else { */

			
			JLabel lblError = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			lblError.setHorizontalAlignment(SwingConstants.CENTER);
			lblError.setBounds(295, 372, 366, 13);
			getContentPane().add(lblError);
			
			jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
			jLabelQueries.setBounds(295, 82, 259, 16);
			jLabelEvents.setBounds(295, 19, 259, 16);

			this.getContentPane().add(jLabelEventDate, null);
			this.getContentPane().add(jLabelQueries);
			this.getContentPane().add(jLabelEvents);

			jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

			datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
			CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

			// Code for JCalendar
			this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
				public void propertyChange(PropertyChangeEvent propertychangeevent) {
					btnAddBet.setEnabled(false);
					eventsmodel.removeAllElements();
					if (propertychangeevent.getPropertyName().equals("locale")) {
						jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
					} else if (propertychangeevent.getPropertyName().equals("calendar")) {
						calendarAnt = (Calendar) propertychangeevent.getOldValue();
						calendarAct = (Calendar) propertychangeevent.getNewValue();
						DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
						Date firstDay = UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

						int monthAnt = calendarAnt.get(Calendar.MONTH);
						int monthAct = calendarAct.get(Calendar.MONTH);

						if (monthAct != monthAnt) {
							if (monthAct == monthAnt + 2) {
								// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2
								// de marzo (se toma como equivalente a 30 de febrero)
								// Con este código se dejará como 1 de febrero en el JCalendar
								calendarAct.set(Calendar.MONTH, monthAnt + 1);
								calendarAct.set(Calendar.DAY_OF_MONTH, 1);
							}

							jCalendar1.setCalendar(calendarAct);

							BLFacade facade = MainGUI.getBusinessLogic();

							datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
						}

						CreateQuestionGUI.paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

						try {

							Vector<domain.Event> events = facade.getEvents(jCalendar1.getDate());
							if (events.isEmpty())
								jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents") + ": "
										+ dateformat1.format(calendarAct.getTime()));
							else
								jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
										+ dateformat1.format(calendarAct.getTime()));
							eventsmodel.removeAllElements();
							eventsmodel.addAll(events);
							eventsComboBox.setModel(eventsmodel);
						} catch (Exception e1) {
					
						}

					}
				}
			});

			this.getContentPane().add(jCalendar1, null);

			betbtton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (quotes.isEmpty()) {
						LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("NoneAdded"));
					}else {
					try {
						float moneybet = Float.parseFloat(BetField.getText());
						if (BetField.getText().length() == 0) {
							LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("PonDinero"));
						} else if (moneybet < 0) {
							LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("Negativo"));
						}else {
						
							Question questionselected = (Question) questionsComboBox.getSelectedItem();
							if (questionselected.getBetMinimum() > moneybet) {
								LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorMin"));
							} else {
								User us1 = (User) MainGUI.getUser();
								if (us1.getWallet() < moneybet) {
									LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNotMoney"));
								} else {
									facade.addBet(moneybet, us1, quotes);
									System.out.println(MainGUI.getBusinessLogic().getJarraitzaileak(us1).size());
									Vector<User> syso = MainGUI.getBusinessLogic().getJarraitzaileak(us1);
									System.out.println( syso.size());
									for (User userra : MainGUI.getBusinessLogic().getJarraitzaileak(us1)) {
										System.out.println(userra.getIzena());
										float max = userra.getBetmax();
										if(max <= userra.getWallet() || moneybet <= userra.getWallet()) {
											if (moneybet > max)
												facade.addBet(max, userra, quotes);
											else
												facade.addBet(moneybet, userra, quotes);
										}
										
									}
									us1.setWallet(us1.getWallet() - moneybet);
									MainGUI.setUser(us1);
									MenuGUI menu1 = new MenuGUI();
									menu1.setVisible(true);
									BetGUI.this.setVisible(false);
								}
							}
						}
					} catch (NumberFormatException f) {
						LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorWalletypeMissmatch"));
					}
					}
				}
			});
			betbtton.setForeground(new Color(255, 255, 255));
			betbtton.setBackground(new Color(0, 0, 0));
			betbtton.setBounds(40, 281, 140, 51);

			getContentPane().add(betbtton);
			getContentPane().add(BetField);
			
			labelMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Money"));
			labelMoney.setBounds(381, 300, 45, 13);
			getContentPane().add(labelMoney);
			labelQuote.setBounds(295, 157, 45, 13);

			getContentPane().add(labelQuote);
			btnAddBet.setForeground(new Color(255, 255, 255));
			btnAddBet.setBackground(new Color(0, 0, 0));
			eventsComboBox = new JComboBox();
			eventsComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					questionsmodel.removeAllElements();
					Event selectedevent = (Event) eventsComboBox.getSelectedItem();
					try {
						questionsmodel.addAll(selectedevent.getQuestions());
						questionsComboBox.setModel(questionsmodel);
					} catch (NullPointerException ex) {
						System.out.println(ex.getMessage());
					}
				}
			});
			eventsComboBox.setBounds(294, 45, 367, 21);
			getContentPane().add(eventsComboBox);

			questionsComboBox = new JComboBox();
			questionsComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					quotesmodel.removeAllElements();
					Question questionselected = (Question) questionsComboBox.getSelectedItem();
					try {
						quotesmodel.addAll(questionselected.getQuotes());
						quotesComboBox.setModel(quotesmodel);
					} catch (NullPointerException ex) {
						System.out.println(ex.getMessage());
					}
				}
			});
			questionsComboBox.setBounds(295, 119, 366, 21);
			getContentPane().add(questionsComboBox);

			quotesComboBox = new JComboBox();
			quotesComboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					betbtton.setEnabled(true);
					btnAddBet.setEnabled(true);
				}
			});
			quotesComboBox.setBounds(295, 195, 366, 21);
			getContentPane().add(quotesComboBox);
			
			btnAddBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Quote quoteselected = (Quote) quotesComboBox.getSelectedItem();
					lblError.setText("");
					if(quotes.contains(quoteselected))
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("BetError"));
					else if(quoteselected.isWinner() != 0) {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("CuotaResuelta"));
					} else
						quotes.add(quoteselected);
				}
			});
			btnAddBet.setBounds(200, 281, 140, 51);
			getContentPane().add(btnAddBet);
		}
	//}
}