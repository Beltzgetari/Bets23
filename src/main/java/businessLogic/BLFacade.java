package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Quote;
import domain.User;
import domain.UserAbstract;
import domain.UserAdmin;
import domain.Event;
import domain.Movement;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod public boolean isLogin(String log, String pass);
	@WebMethod public boolean register( String username, String password, String izena, int age);
	@WebMethod public UserAbstract getUserByName(String username);
	@WebMethod public boolean createQuote(Question q, String quote, double multi);
	@WebMethod public Question getQuestion(int qn);
	@WebMethod public boolean createEvent(String description, Date eventdate);
	@WebMethod public boolean putMoney(float money, User us, String desc);
	@WebMethod public boolean takeMoney(float money, User us, String desc);
	@WebMethod public boolean makeWinner(Question q, Quote qq);
	@WebMethod public boolean addBet(float money, User us, Vector<Quote> q);
	@WebMethod public boolean deleteEvent(Event ev);
	@WebMethod public UserAbstract getUser(UserAbstract user);
	@WebMethod public void setUser(UserAbstract user);
	@WebMethod public boolean addMovement(User us, Movement move);
	@WebMethod public boolean copyEvent(Event ev, Date date);
	@WebMethod public Vector<UserAbstract> getUsers(boolean isAdmin);
	@WebMethod public void changemax(User us, float kant);
	@WebMethod public void addFollow(User jarraitu, User jarraitzaile);
	@WebMethod public void removeFollow(User jarraitu, User jarraitzaile);
	@WebMethod public Vector<User> getUnfollows(User us);
	@WebMethod public Vector<User> getJarraitzaileak(User us);
	@WebMethod public Vector<User> getJarraituak(User us);
	@WebMethod public void addResult(Question q, Quote qq);
	@WebMethod public List<User> getRanking();
}