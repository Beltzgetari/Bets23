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
import java.util.Vector;

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
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class CopyEventGUI extends JFrame {

	private static final long serialVersionUID = 1L;

	private JComboBox<Event> eventsComboBox = new JComboBox<Event>();
	DefaultComboBoxModel<Event> eventsmodel = new DefaultComboBoxModel<Event>();

	private JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelQueries = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private final JButton DeleteEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CopyEvent")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton btnBakc = new JButton(ResourceBundle.getBundle("Etiquetas").getString("WalletBack")); //$NON-NLS-1$ //$NON-NLS-2$
	
	private JLabel jLabelEventDate2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar2 = new JCalendar();
	private Calendar calendarAct2 = null;
	private Calendar calendarAnt2 = null;

	
	private Vector<Date> datesWithEventsCurrentMonth2 = new Vector<Date>();

	private JLabel labelerror = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Noizaukeratubeharda"));;


	public CopyEventGUI() {
		DeleteEvent.setBackground(new Color(0, 0, 0));
		DeleteEvent.setForeground(new Color(255, 255, 255));
		DeleteEvent.setEnabled(false);
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CopyEvent"));
		eventsComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
					DeleteEvent.setEnabled(true);
				
			}
		});
		
		
		eventsComboBox.setBounds(new Rectangle(40, 240, 250, 20));
		jLabelEvents.setBounds(new Rectangle(40, 212, 277, 20));

		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));
		jLabelMsg.setBounds(new Rectangle(175, 20, 305, 20));
		jLabelMsg.setForeground(Color.red);
		// jLabelMsg.setSize(new Dimension(305, 20));
		labelerror.setVisible(false);
		jLabelQueries.setBounds(new Rectangle(222, 182, 305, 20));
		jLabelQueries.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelQueries, null);
		this.getContentPane().add(jLabelEvents, null);
		this.getContentPane().add(eventsComboBox, null);

		this.getContentPane().add(jCalendar1, null);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
		
		
		datesWithEventsCurrentMonth2=facade.getEventsMonth(jCalendar2.getDate());
		paintDaysWithEvents(jCalendar2,datesWithEventsCurrentMonth2);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		DeleteEvent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(eventsComboBox.getSelectedItem()== null || jCalendar2.getCalendar()== null) {
					jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error1"));
				}else {
					Calendar calendar = jCalendar2.getCalendar();
					
					int month = calendar.get(Calendar.MONTH);
					int today=calendar.get(Calendar.DAY_OF_MONTH);
					int year=calendar.get(Calendar.YEAR);
					boolean b= MainGUI.getBusinessLogic().copyEvent((Event)eventsComboBox.getSelectedItem() ,  UtilDate.newDate(year,month,today));
				}
			}
		});
		DeleteEvent.setBounds(330, 263, 150, 39);
		
		getContentPane().add(DeleteEvent);
		btnBakc.setForeground(new Color(0, 0, 0));
		btnBakc.setBackground(new Color(255, 255, 255));
		btnBakc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CopyEventGUI.this.setVisible(false);
				AdminGUI admin = new AdminGUI();
				admin.setVisible(true);
			}
		});
		btnBakc.setBounds(152, 263, 150, 39);
		
		getContentPane().add(btnBakc);
		

		jCalendar2.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar2.setBounds(330, 50, 225, 150);
		getContentPane().add(jCalendar2);
		
		labelerror.setBounds(327, 226, 228, 13);
		getContentPane().add(labelerror);

		
		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				DeleteEvent.setEnabled(false);
				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						BLFacade facade=MainGUI.getBusinessLogic();
						DeleteEvent.setEnabled(false);
						Vector<domain.Event> events=facade.getEvents(jCalendar1.getDate());
						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						eventsmodel.removeAllElements();
						eventsmodel.addAll(events);
						eventsComboBox.setModel(eventsmodel);
					} catch (Exception e1) {
						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});
		
		this.jCalendar2.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{
				
				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarAnt2 = (Calendar) propertychangeevent.getOldValue();
					calendarAct2 = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat2 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay2=UtilDate.trim(new Date(jCalendar2.getCalendar().getTime().getTime()));

					 
					
					int monthAnt2 = calendarAnt2.get(Calendar.MONTH);
					int monthAct2 = calendarAct2.get(Calendar.MONTH);
					
					if (monthAct2!=monthAnt2) {
						if (monthAct2==monthAnt2+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct2.set(Calendar.MONTH, monthAnt2+1);
							calendarAct2.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar2.setCalendar(calendarAct2);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth2=facade.getEventsMonth(jCalendar2.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar2,datesWithEventsCurrentMonth2);
				}
			}
		});
		if (jCalendar1.getCalendar().equals(jCalendar2.getCalendar())) {
			labelerror.setVisible(true);
		}
	}

	
public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed to cyan.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

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
