package dataAccess;

//hello
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.ArrayList;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {
		
			String Lokala = "Lokala";
            String Bisitaria = "Bisitaria";
            String Local ="Local";
            
		   UserAdmin admin = new UserAdmin("admin", "admin");
		   User a= new User("a", "a", "a", 18);
		  User b= new User("b", "b", "b", 18);
		   db.persist(admin);
		   db.persist(a);
		   db.persist(b);
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event( "AtlÃ©tico-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event( "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event( "AlavÃ©s-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event( "EspaÃ±ol-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event( "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event( "Girona-LeganÃ©s", UtilDate.newDate(year,month,17));
			Event ev9=new Event( "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event( "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event( "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event( "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event( "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event( "AlavÃ©s-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event( "EspaÃ±ol-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event( "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event("MÃ¡laga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event("Girona-LeganÃ©s", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event("Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event("Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			
			Quote qq11;
			Quote qq12;
			Quote qq21;
			Quote qq22;
			Quote qq31;
			Quote qq32;
			Quote qq41;
			Quote qq42;
			Quote qq51;
			Quote qq52;
			Quote qq61;
			Quote qq62;
		
			
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("Â¿QuiÃ©n ganarÃ¡ el partido?",1);
				q2=ev1.addQuestion("Â¿QuiÃ©n meterÃ¡ el primer gol?",2);
				q3=ev11.addQuestion("Â¿QuiÃ©n ganarÃ¡ el partido?",1);
				q4=ev11.addQuestion("Â¿CuÃ¡ntos goles se marcarÃ¡n?",2);
				q5=ev17.addQuestion("Â¿QuiÃ©n ganarÃ¡ el partido?",1);
				q6=ev17.addQuestion("Â¿HabrÃ¡ goles en la primera parte?",2);
				
				qq11= q1.addQuote(Local, 1.5);
				qq12= q1.addQuote("Visitante", 2);
				qq21=q2.addQuote(Local, 2.5);
				qq22= q2.addQuote("Visitante", 1);
				qq31= q3.addQuote(Local, 3);
				qq32=q3.addQuote("Visitante", 1);
				qq41= q4.addQuote("1", 1.5);
				qq42=q4.addQuote("2", 2);
				qq51=q5.addQuote(Local, 1.5);
				qq52=q5.addQuote("Visitante", 2);
				qq61=q6.addQuote("Si", 1.5);
				qq62=q6.addQuote("No", 2);
				
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
				
				qq11= q1.addQuote(Local, 1.5);
				qq12= q1.addQuote("Visitor", 2);
				qq21=q2.addQuote(Local, 2.5);
				qq22= q2.addQuote("Visitor", 1);
				qq31= q3.addQuote(Local, 3);
				qq32=q3.addQuote("Visitor", 1);
				qq41= q4.addQuote("1", 1.5);
				qq42=q4.addQuote("2", 2);
				qq51=q5.addQuote(Local, 1.5);
				qq52=q5.addQuote("Visitor", 2);
				qq61=q6.addQuote("Yes", 1.5);
				qq62=q6.addQuote("No", 2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
				qq11=q1.addQuote(Lokala, 1.5);
				qq12=q1.addQuote(Bisitaria, 2);
				qq21=q2.addQuote("Lokalak", 2.5);
				qq22=q2.addQuote("Bisitariak", 1);
				qq31=q3.addQuote(Lokala, 3);
				qq32=q3.addQuote(Bisitaria, 1);
				qq41=q4.addQuote("1", 1.5);
				qq42=q4.addQuote("2", 2);
				qq51=q5.addQuote(Lokala, 1.5);
				qq52=q5.addQuote(Bisitaria, 2);
				qq61=q6.addQuote("Bai", 1.5);
				qq62=q6.addQuote("Ez", 2);
				
			}
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);
			
			db.persist(qq11);
			db.persist(qq12);
			db.persist(qq21);
			db.persist(qq22);
			db.persist(qq31);
			db.persist(qq32);
			db.persist(qq41);
			db.persist(qq42);
			db.persist(qq51);
			db.persist(qq52);
			db.persist(qq61);
			db.persist(qq62);
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
	}
	
	public boolean createQuote(Question question, String quote, double multi) {
		Question quest = db.find(Question.class, question);
		Quote q= db.find(Quote.class, quote);
		if (q == null) {
			db.getTransaction().begin();
			quest.addQuote(quote, multi);
			db.persist(quest);
			db.persist(new Quote(quote, multi));
			db.getTransaction().commit();
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public ArrayList<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		ArrayList<Event> res = new ArrayList<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public ArrayList<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		ArrayList<Date> res = new ArrayList<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public boolean isLogin(String log, String pass) {
		UserAbstract us=db.find(UserAbstract.class, log);
		 if (us==null) {
			 return false;
		 }else {
			 if (pass.equals(us.getPassword())) {
				 return true;
			 }else {
				 return false;
			 }
		 }
	}
	public boolean register( String username, String password, String izena, int age) {
		UserAbstract us= db.find(UserAbstract.class, username);
		if (us== null) {
			db.getTransaction().begin();
			System.out.println(username);
			db.persist((UserAbstract)new User(username, password, izena, age));
			//db.persist((UserAbstract)new UserAdmin(username, password));
			db.getTransaction().commit();
			System.out.println("Erregistratu da");
			return true;
		}else {
			return false;
		}
	}

	public UserAbstract getUserByName(String username) {
        return db.find(UserAbstract.class, username);
	}
	public User getUserUsername(String username) {
		 return db.find(User.class, username);
	}
	
	public Question getQuestion(int questionNumber) {
		return db.find(Question.class, questionNumber);
	}
	
	public boolean createEvent(String description, Date eventdate) {
        Event e= new Event(description, eventdate);
        TypedQuery<Event> query = db.createQuery("SELECT DISTINCT e FROM Event e", Event.class);
        List<Event> evs= query.getResultList();
        if (!evs.contains(e)) {
            Event e1= new Event(description, eventdate);
            db.getTransaction().begin();
            db.persist(e1);
            db.getTransaction().commit();
            return true;
        }else {
            return false;
        }
    }
	public boolean putMoney(float money, User us, String desc) {
		User us1 = db.find(User.class, us);
		if (us1 == null) {
			return false;
		}else {
			db.getTransaction().begin();
			us1.setWallet(us1.getWallet()+ money);
			db.getTransaction().commit();
			Movement move= new Movement(desc, money);
			this.addMovement(us1, move);
			return true;
		}
	}
	
	public boolean takeMoney(float money, User us, String desc) {
		User us1= db.find(User.class, us);
		
		if (us1== null) {
			return false;
		}else {
		
			float wallet = us1.getWallet();
			if (wallet < money) {
				System.out.println("Eztago wallet-ean") ;
				return false;
				}else {
					db.getTransaction().begin();
					us1.setWallet(wallet-money);
					Movement move= new Movement(desc, money);
					db.getTransaction().commit();
					this.addMovement(us1, move);
				}
			return true;
		}
	}
	
	protected void Galtzaileak(Question galdera, Quote q1) {
		for (Quote qq : galdera.getQuotes()) {
			Quote qq2 = db.find(Quote.class, qq);
			if (!qq2.getQuoteNumber().equals(q1.getQuoteNumber()))
				qq2.makeLoser();
		}
	}
	
	public boolean makeWinner(Question quest, Quote q) {
		System.out.println("hola");
		Quote q1 = db.find(Quote.class, q);
		Question quest2 = db.find(Question.class, quest);
		if (quest2 == null || q1 == null) {
			if (quest2 == null) {
				System.out.println("no llega quest ");
			}
			if (q1 == null) {
				System.out.println("no llega quote");
			}
			return false;
		} else {
			System.out.println("llega aqui");
			if (q1.isWinner() == 1) {
				return false;
			} else {
				db.getTransaction().begin();
				q1.makeWinner();
				// System.out.println("makeWinner true");
				Galtzaileak(quest2, q1);
				// System.out.println("is Winner: " + q1.isWinner());
				db.getTransaction().commit();
				return true;
			}
		}
	}
	
	public boolean addBet(float money, User us, ArrayList<Quote> quotes) {
		Bet bet= new Bet(money, us);
		bet.setQuotes(quotes);
		Bet bet1= db.find(Bet.class, bet);
		if (bet1==null) {
			db.getTransaction().begin();
			User us1= db.find(User.class, us);
			Bet bet2 = new Bet(money, us1);
			bet2.setQuotes(quotes);
			for (Quote quote : quotes) {
				Quote q1= db.find(Quote.class, quote);
				q1.getBets().add(bet2);
			}
			db.persist(bet2);
			us1.getBets().add(bet2);
			us1.getStatistics().setAmountOfBets(us.getStatistics().getAmountOfBets()+1);
			db.getTransaction().commit();
			this.takeMoney(money, us, "DiruaApustuarentzako" );
			return true;
		} else {
			return false;
		}
	}
	
	public boolean deleteEvent(Event v) {
		Event ev =db.find(Event.class, v);
		if (ev==null) {
			return false;
		} else {
			db.getTransaction().begin();
			ev.removeBets();
			db.remove(ev);
			db.getTransaction().commit();
			return true;
		}
	}
	
	public boolean addMovement(User us, Movement move) {
		Movement move1= db.find(Movement.class, move);
		if (move1 !=null) {
			return false;
		} else {
			db.getTransaction().begin();
			User us1 =db.find(User.class, us);
			us1.getMovements().add(move);
			db.persist(move);
			db.getTransaction().commit();
			return true;
		}
	}
	
	public boolean copyEvent(Event ev, Date date) {
		Event evdb = db.find(Event.class, ev);
		if (evdb == null) {
			return false;
		} else {
			this.createEvent(evdb.getDescription(), date);
			TypedQuery<Event> query1 = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1 AND ev.description=?2", Event.class);
			query1.setParameter(1, date);
			query1.setParameter(2, evdb.getDescription());
			for (Question q: evdb.getQuestions()) {
				try {
				Question q2 = this.createQuestion(query1.getSingleResult(), q.getQuestion(), q.getBetMinimum());
				Question qdb = db.find(Question.class, q2);
				for (Quote qq : q.getQuotes()) {
					this.createQuote(qdb, qq.getQuote(), qq.getMulti());
				}
				
				} catch (QuestionAlreadyExist e) {
					e.printStackTrace();
				}
			}
			return true;
		}
	}
	
	
	public ArrayList<UserAbstract> getUsers(boolean isAdmin){
		ArrayList<UserAbstract> users = new ArrayList<UserAbstract>();
		if (isAdmin){
			TypedQuery<UserAdmin> query1 = db.createQuery("SELECT DISTINCT us FROM UserAdmin us", UserAdmin.class);
		
			users.addAll(query1.getResultList());
		}else {
			TypedQuery<User> query1 = db.createQuery("SELECT DISTINCT us FROM User us", User.class);
			 users.addAll(query1.getResultList());
		}
		return users;
	}
	

	public void changemax(User us, float kant) {
		User us1= db.find(User.class, us);
		db.getTransaction().begin();
		us1.setBetmax(kant);
		db.getTransaction().commit();
	}
	
	public void addFollow(User jarraitua, User jarraitzaile) {
		System.out.println(jarraitzaile.getIzena() + "sigue a " + jarraitua.getIzena());
		User us1= db.find(User.class, jarraitua);
		User us2= db.find(User.class, jarraitzaile);
		db.getTransaction().begin();
		us1.addJarraitzaile(us2);;
		System.out.println("hollaaaa");
		us2.addJarraitu(us1);
		db.getTransaction().commit();
	}
	
	public void removeFollow(User jarraitua, User jarraitzaile) {
		User us1 = db.find(User.class, jarraitua);
		User us2 = db.find(User.class, jarraitzaile);
		db.getTransaction().begin();
		Iterator<User> j1 = us1.getJarraitzaileak().iterator();
		while (j1.hasNext()) {
			User u = j1.next();
			if (u.equals(us2)) {
				j1.remove();
			}
		}
		Iterator<User> j2= us2.getJarraituak().iterator();
		while (j2.hasNext()) {
			User u2 = j2.next();
			if (u2.equals(us1)) {
				j2.remove();
			}
		}
		db.getTransaction().commit();
	}
	
	public ArrayList<User> getUnfollows(User us){
		User us1 = db.find(User.class, us);
		ArrayList<User> nofollowing = new ArrayList<User>();
		TypedQuery<User> guztiak = db.createQuery("SELECT DISTINCT us FROM User us", User.class);
		if (!guztiak.getResultList().isEmpty()) {
			for (User ez : guztiak.getResultList()) {
				if (!us1.getJarraituak().contains(ez) && !us1.equals(ez)) {
					nofollowing.add(ez);
				}
			}
		}
			return nofollowing;
	}
	
	public ArrayList<User> getJarraitzaileak(User us) {
		User us2 = db.find(User.class, us);
		System.out.println(us2.getJarraitzaileak().size());
		return us2.getJarraitzaileak();
	}
	
	public ArrayList<User> getJarraituak(User us){
		User us2= db.find(User.class, us);
		System.out.println(us2.getJarraituak().size());
		return us2.getJarraituak();
	}
	
	public Quote getQuote(Quote q) {
		return db.find(Quote.class, q);
	}
	
	public void setUser(User u) {
		db.getTransaction().begin();
		User us = db.merge(u);
		db.refresh(us);
		db.getTransaction().commit();
	}
	public List<User> getRanking(){
		TypedQuery<User> query1 = db.createQuery("SELECT DISTINCT us FROM User us", User.class);
		List<User> list = query1.getResultList();
		list.sort(new ComparatorUsers());
		Collections.reverse(list);
		return list;
	}
	public void updateStatistics(User u, float moneywin, boolean irabazi) {
		db.getTransaction().begin();
		User us= db.find(User.class,u);
		us.getStatistics().updateStatistics(moneywin, irabazi);
		db.getTransaction().commit();
		
	}
	public void ezabatu () {
		db.getTransaction().begin();
		Query query1 = db.createQuery("DELETE FROM User");
        query1.executeUpdate();
        db.getTransaction().commit();
	}
	
}
