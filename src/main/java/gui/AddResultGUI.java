package gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;
import domain.*;
import domain.Quote;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import javax.swing.SwingConstants;

public class AddResultGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<Event> eventsComboBox = new JComboBox<Event>();
	DefaultComboBoxModel<Event> eventsmodel = new DefaultComboBoxModel<Event>();

	private JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();

	private ArrayList<Date> datesWithEventsCurrentMonth = new ArrayList<Date>();
	private final JButton jbtnAdd = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Add")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton jbtnBack = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack")); //$NON-NLS-1$ //$NON-NLS-2$

	private JComboBox jComboBoxQuotes = new JComboBox();
	private DefaultComboBoxModel<Quote> modelquotes = new DefaultComboBoxModel<Quote>();
	private JComboBox jComboBoxQuestions = new JComboBox();
	DefaultComboBoxModel<Question> modelquestions = new DefaultComboBoxModel<Question>();
	private JLabel lblQuotes;

	private JLabel lblQueries;

	public AddResultGUI() {
		jbtnAdd.setForeground(new Color(255, 255, 255));
		jbtnAdd.setBackground(new Color(0, 0, 0));
		jbtnAdd.setEnabled(false);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AddResult"));
		eventsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelquestions.removeAllElements();
				Event event = (Event) eventsComboBox.getSelectedItem();
				try {
					modelquestions.addAll(event.getQuestions());
					jComboBoxQuestions.setModel(modelquestions);
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		eventsComboBox.setBounds(new Rectangle(275, 47, 250, 20));
		jLabelEvents.setBounds(new Rectangle(290, 17, 277, 20));

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jLabelMsg.setBounds(new Rectangle(290, 0, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(10, 0, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jLabelEvents, null);
		this.getContentPane().add(eventsComboBox, null);

		this.getContentPane().add(jCalendar1, null);

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth = facade.getEventsMonth(jCalendar1.getDate());
		paintDaysWithEvents(jCalendar1, datesWithEventsCurrentMonth);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		jbtnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Quote qq = (Quote) jComboBoxQuotes.getSelectedItem();
				Question q = (Question) jComboBoxQuestions.getSelectedItem();
				MainGUI.getBusinessLogic().addResult(q, qq);
			}
		});
		jbtnAdd.setBounds(321, 255, 121, 46);

		getContentPane().add(jbtnAdd);
		jbtnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI admin = new AdminGUI();
				admin.setVisible(true);
				AddResultGUI.this.setVisible(false);
			}
		});
		jbtnBack.setBounds(142, 255, 115, 46);
		jbtnBack.setBackground(Color.white);
		getContentPane().add(jbtnBack);

		lblQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Questions"));
		lblQueries.setBounds(290, 82, 85, 31);
		getContentPane().add(lblQueries);

		lblQuotes = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quotes"));
		lblQuotes.setBounds(290, 146, 67, 33);
		getContentPane().add(lblQuotes);

		jComboBoxQuotes = new JComboBox();
		jComboBoxQuotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jbtnAdd.setEnabled(true);
			}
		});
		jComboBoxQuotes.setBounds(275, 189, 250, 21);
		getContentPane().add(jComboBoxQuotes);

		jComboBoxQuestions = new JComboBox();
		jComboBoxQuestions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modelquotes.removeAllElements();
				Question question = (Question) jComboBoxQuestions.getSelectedItem();
				try {
					modelquotes.addAll(question.getQuotes());
					jComboBoxQuotes.setModel(modelquotes);
				} catch (NullPointerException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		jComboBoxQuestions.setBounds(275, 123, 250, 21);
		getContentPane().add(jComboBoxQuestions);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
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
						BLFacade facade = MainGUI.getBusinessLogic();

						ArrayList<domain.Event> events = facade.getEvents(jCalendar1.getDate());
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
	}

	public static void paintDaysWithEvents(JCalendar jCalendar, ArrayList<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day
		// is changed to cyan.

		Calendar calendar = jCalendar.getCalendar();

		int month = calendar.get(Calendar.MONTH);
		int today = calendar.get(Calendar.DAY_OF_MONTH);
		int year = calendar.get(Calendar.YEAR);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;

		for (Date d : datesWithEventsCurrentMonth) {
			calendar.setTime(d);
			System.out.println(d);

			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
		}

		calendar.set(Calendar.DAY_OF_MONTH, today);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);

	}
}
