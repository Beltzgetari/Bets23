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


public class CreateQuoteGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private ArrayList<Date> datesWithEventsCurrentMonth = new ArrayList<Date>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton createquotebtton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuoteButon")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JButton backbutton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BackQuote")); //$NON-NLS-1$ //$NON-NLS-2$
	private final JTextField MultiplierField = new JTextField();


	private JLabel labelQuotename;
	private final JLabel LabelError = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$

	private JLabel multiLabel_1= new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MultiplierLabel"));
	private JTextField QuoteField = new JTextField();
	

	
	
	
	public CreateQuoteGUI()
	{
		
		MultiplierField.setBounds(478, 387, 96, 19);
		MultiplierField.setColumns(10);
		MultiplierField.setVisible(true);
		QuoteField.setVisible(true);
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("CreateQuoteButon"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 210, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

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
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						ArrayList<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {
						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				QuoteField.setVisible(true);
				MultiplierField.setVisible(true);
			}
		});
		scrollPaneQueries.setBounds(new Rectangle(40, 234, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				ArrayList<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		createquotebtton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (QuoteField.getText().length()==0 || MultiplierField.getText().length()==0) {
						LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("RegisterGUI_error1"));
					}else {
						System.out.println((tableModelQueries.getValueAt(tableQueries.getSelectedRow(), 0)).toString());
						int q = Integer.parseInt((tableModelQueries.getValueAt(tableQueries.getSelectedRow(), 0)).toString());
						System.out.println(q);
						Question question = MainGUI.getBusinessLogic().getQuestion(q);
						boolean b= MainGUI.getBusinessLogic().createQuote(question, QuoteField.getText(), Double.parseDouble(MultiplierField.getText()));
						if (b) {
							LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("Sartu"));
						}else {
							LabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuoteAlreadyExist"));
							
						}
					}
				} catch (NumberFormatException  f) {
					LabelError.setText(f.getMessage());
				}
			}
		});
		createquotebtton.setForeground(new Color(255, 255, 255));
		createquotebtton.setBackground(new Color(0, 0, 0));
		createquotebtton.setBounds(190, 371, 140, 51);
		
		getContentPane().add(createquotebtton);
		backbutton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AdminGUI admin=new AdminGUI();
				admin.setVisible(true);
				CreateQuoteGUI.this.setVisible(false);
			}
		});
		backbutton.setBackground(new Color(255, 255, 255));
		backbutton.setBounds(40, 371, 140, 51);
		
		getContentPane().add(backbutton);
		
		labelQuotename = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("QuoteName"));
		labelQuotename.setBounds(340, 360, 184, 13);
		getContentPane().add(labelQuotename);
		
		getContentPane().add(QuoteField);
		QuoteField.setColumns(10);
		
		getContentPane().add(MultiplierField);
		LabelError.setHorizontalAlignment(SwingConstants.CENTER);
		LabelError.setBounds(10, 432, 666, 13);
		
		getContentPane().add(LabelError);
		
		
		multiLabel_1.setBounds(348, 390, 98, 13);
		getContentPane().add(multiLabel_1);
		
		QuoteField.setBounds(478, 357, 96, 19);
		getContentPane().add(QuoteField);
		QuoteField.setColumns(10);

	}
}