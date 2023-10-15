package test.dataAccess;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.User;
import exceptions.QuestionAlreadyExist;

public class makeWinnerDABTest {
	private ArrayList<User> unfollows ;
	private DataAccess da;
	private Quote quotenulo;
	private Question questnulo;
	private Quote qwinner;
	private Question questwinner;
	private Quote qnowinner;
	private Event eproba;
	
	@Before
	public void initialize() {
		
		questnulo = null;
		quotenulo= null;
		qwinner = new Quote("winner", 2);
		qnowinner = new Quote("nowinner", 2);
		questwinner = new Question("proba", 1, new Event() );
		da = new DataAccess();
		qwinner.makeWinner();
		eproba = new Event("proba", new Date());
	}
	
	@Test
	public void Test1() {
		try{
			da.makeWinner(questnulo, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test2() {
		try{
			da.makeWinner(questwinner, quotenulo);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test3() {
		try{
			//Ez daude datu basean
			da.makeWinner(questwinner, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test4() {
		try{
			//Quote-a ez dago Question-n (Egin behar)
			da.makeWinner(questwinner, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test5() { 
		//try {
			da.createEvent(eproba.getDescription(), eproba.getEventDate());
			eproba.setEventNumber(61);
			questwinner.setQuestionNumber(64);
			qwinner.setQuoteNumber(88);
			qwinner.makeWinner();
			//da.createQuestion(eproba, questwinner.getQuestion(), questwinner.getBetMinimum());
		//	da.createQuote(questwinner,qwinner.getQuote() , qwinner.getMulti());
			assertFalse(da.makeWinner(questwinner, qwinner));
		/*} catch( QuestionAlreadyExist e) {
			fail("existe");
		}*/
	
		
	}
	@Test
	public void Test6() { 
		da.createEvent(eproba.getDescription(), eproba.getEventDate());
		eproba.setEventNumber(61);
		questwinner.setQuestionNumber(64);
		qnowinner.setQuoteNumber(99);
		//da.createQuote(questwinner,qnowinner.getQuote() , qnowinner.getMulti());
		assertTrue(da.makeWinner(questwinner, qnowinner));
		
	}
}
