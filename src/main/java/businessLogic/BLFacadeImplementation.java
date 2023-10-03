package businessLogic;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quote;
import domain.User;
import domain.UserAbstract;
import domain.UserAdmin;
import domain.Bet;
import domain.Event;
import domain.Movement;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;
import gui.MainGUI;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;
	//UserAbstract user;
	@WebMethod
	public UserAbstract getUser(UserAbstract user) {
		UserAbstract u= null;
		if (user!= null) {
			dbManager.open(false);
			u = dbManager.getUserByName(user.getUsername());
			dbManager.close();
		}
		return u;
	}
	
	@WebMethod
	public void setUser(UserAbstract user) {
		dbManager.open(false);
		if(user instanceof User)
			dbManager.setUser((User) user);
		dbManager.close();
	}

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    @WebMethod 
    public boolean isLogin(String log, String pass) {
    	dbManager.open(false);
    	boolean emaitza= dbManager.isLogin(log, pass);
    	dbManager.close();
    	return emaitza;
    }
    @WebMethod
    public boolean register( String username, String password, String izena, int age) {
    	dbManager.open(false);
    	boolean b=dbManager.register(username, password, izena, age);
    	dbManager.close();
    	return b;
    }
    
    @WebMethod
    public boolean createQuote(Question q, String quote, double multi) {
    	dbManager.open(false);
    	boolean b=dbManager.createQuote(q, quote, multi);
    	dbManager.close();
    	return b;
    }
    @WebMethod
    public UserAbstract getUserByName(String username) {
        dbManager.open(false);
        UserAbstract us = dbManager.getUserByName(username);
        dbManager.close();
        return us;
    }
    
    @WebMethod
    public Question getQuestion(int qn) {
    	dbManager.open(false);
        Question q = dbManager.getQuestion(qn);
        dbManager.close();
        return q;
    }
    
    @WebMethod 
    public boolean createEvent(String description, Date eventdate) {
        dbManager.open(false);
        boolean b= dbManager.createEvent(description, eventdate);
        dbManager.close();
        return b;
    }

	@WebMethod
	public boolean putMoney(float money, User us, String desc) {
		dbManager.open(false);
		boolean b = dbManager.putMoney(money, us, desc);
		dbManager.close();
		return b;
	}
	@WebMethod
	public boolean takeMoney(float money, User us, String desc) {
		dbManager.open(false);
		boolean b=dbManager.takeMoney(money, us, desc);
		dbManager.close();
		return b;
	}
	@WebMethod
	public boolean makeWinner(Question q, Quote qq) {
		dbManager.open(false);
		boolean b=dbManager.makeWinner(q,qq);
		dbManager.close();
		return b;
	}

	@WebMethod
	public boolean addBet(float money, User us, Vector<Quote> q) {
		dbManager.open(false);
		boolean b=dbManager.addBet(money, us, q);
		dbManager.close();
		return b;
	}
	@WebMethod 
	public boolean deleteEvent(Event ev) {
		dbManager.open(false);
		boolean b=dbManager.deleteEvent(ev);
		dbManager.close();
		return b;
	}
	@WebMethod 
	public boolean addMovement(User us, Movement move) {
		dbManager.open(false);
		boolean b=dbManager.addMovement(us, move);
		dbManager.close();
		return b;
	}
	@WebMethod 
	public boolean copyEvent(Event ev, Date date) {
		dbManager.open(false);
		boolean b=dbManager.copyEvent(ev, date);
		dbManager.close();
		return b;
	}

	@WebMethod public Vector<UserAbstract> getUsers(boolean isAdmin){
		dbManager.open(false);
		Vector<UserAbstract> users=dbManager.getUsers(isAdmin);
		dbManager.close();
		return users;
	}

	@WebMethod public void changemax(User us, float kant) {
			dbManager.open(false);
			dbManager.changemax(us, kant);
			dbManager.close();
	}
	@WebMethod public void addFollow(User jarraitu, User jarraitzaile) {
		dbManager.open(false);
		dbManager.addFollow(jarraitu, jarraitzaile);
		dbManager.close();
	}
	@WebMethod public void removeFollow(User jarraitu, User jarraitzaile) {
		dbManager.open(false);
		dbManager.removeFollow(jarraitu, jarraitzaile);
		dbManager.close();
	}
	@WebMethod public Vector<User> getUnfollows(User us){
		dbManager.open(false);
		Vector<User> nofollowing = dbManager.getUnfollows(us);
		dbManager.close();
		return nofollowing;
	}
	@WebMethod public Vector<User> getJarraitzaileak(User us){
		dbManager.open(false);
		Vector<User> jarraitzailes= dbManager.getJarraitzaileak(us);
		dbManager.close();
		return jarraitzailes;
	}
	@WebMethod public Vector<User> getJarraituak(User us){
		dbManager.open(false);
		Vector<User> jarraitus = dbManager.getJarraituak(us);
		dbManager.close();
		return jarraitus;
	}
	
	@WebMethod public void addResult(Question q, Quote qq) {
		boolean irabazle = true;
		this.makeWinner(q, qq);
		dbManager.open(false);
		Quote dbq = dbManager.getQuote(qq);
		Question quest = dbManager.getQuestion(q.getQuestionNumber());
		dbManager.close();
		for (Bet b : dbq.getBets()) {
			User u = (User) this.getUserByName(b.getUser().getIzena());
			System.out.println(b.getQuotes().get(0).getQuote());
			for (Quote q1 : b.getQuotes()) {
				if (q1.isWinner() != 1) {
					irabazle = false;
					//System.out.println(1);
				}
			}
			
			float moneywin = b.getKop();
			if (irabazle) {
				for (Quote q1 : b.getQuotes()) {
					moneywin *= q1.getMulti();
					//System.out.println(2);
				}
				this.putMoney(moneywin, u, "ApustuaIrabazi");
			}
			
			dbManager.open(false);
			dbManager.updateStatistics(u, moneywin, irabazle);
			dbManager.close();
		}
		
		for (Quote qq1: quest.getQuotes()) {
			dbManager.open(false);
			Quote qq2 = dbManager.getQuote(qq1);
			dbManager.close();
			if(qq2.getQuoteNumber() != qq.getQuoteNumber()) {
				for (Bet b1: qq2.getBets()) {
					User us = (User) this.getUserByName(b1.getUser().getIzena());
					dbManager.open(false);
					dbManager.updateStatistics(us, 0, false);
					dbManager.close();
				}
			}
		}
	}
	
	@WebMethod public List<User> getRanking(){
		dbManager.open(false);
		List<User> users = dbManager.getRanking();
		dbManager.close();
		return users;
	}
}

