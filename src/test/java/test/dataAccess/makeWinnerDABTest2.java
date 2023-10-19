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

public class makeWinnerDABTest2 {
    private ArrayList<User> unfollows ;
    private DataAccess da;
    private Quote quotenulo;
    private Question questnulo;
    private Quote qwinner;
    private Question questwinner;
    private Quote qnowinner;
    private Event eproba;
    private Quote qEzdago;
    private Question questiongaizki;


    @Before
    public void initialize() {
    	
        questnulo = null;
        quotenulo= null;
        qwinner = new Quote("winner", 2);
        qnowinner = new Quote("nowinner", 2);
     
        da = new DataAccess();
        da.ezabatu();
        qwinner.makeWinner();
       
        questwinner = new Question("proba", 1, eproba );
        qEzdago = new Quote("noquestion", 2);
        questiongaizki = new Question("proba2", 1, eproba );
    }
	
	@Test
	public void Test1() {
		try{
			System.out.println("Questnulo");
			da.makeWinner(questnulo, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test2() {
		try{
			System.out.println("quotenulo");
			da.makeWinner(questwinner, quotenulo);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test3() {
		try{
			System.out.println("no en la base");
			//Ez daude datu basean
			da.makeWinner(questwinner, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test4() {
		try{
			System.out.println("quote no en pregunta");
			//Quote-a ez dago Question-n (Egin behar
			Date data= new Date();
			 eproba = new Event("proba", data);
		        da.createEvent(eproba.getDescription(), eproba.getEventDate());
		        eproba.setEventNumber(360);
			  da.createQuestion(eproba, questiongaizki.getQuestion(), questiongaizki.getBetMinimum());
			  questiongaizki.setQuestionNumber(362);
	           da.createQuote(questiongaizki, qwinner.getQuote(), qwinner.getMulti());
	          da.createQuote(questwinner, qEzdago.getQuote(), qEzdago.getQuoteNumber());
	            da.makeWinner(questiongaizki, qEzdago);
		
		
		}catch (QuestionAlreadyExist e) {
			fail();
		}catch(Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test5() { 
		try {
			Date date5 = new Date();
			Event e5 = new Event("5", date5);
			da.createEvent(e5.getDescription(), date5);
			e5.setEventNumber(368);
			System.out.println("bn ganar");
			questwinner = da.createQuestion(e5, questwinner.getQuestion(), questwinner.getBetMinimum());
			da.createQuote(questwinner, qwinner.getQuote(), qwinner.getMulti());
			qwinner = da.getQuote(qwinner);
			questwinner = da.getQuestion(64);
			boolean resp = da.makeWinner(questwinner, qwinner);
			assertFalse(resp);
		}catch(QuestionAlreadyExist e) {
			fail();
		}catch( Exception e) {
			fail();
		} 
		
	}
	@Test
	public void Test6() { 
		try {
			System.out.println("bien perder");
			questwinner = da.createQuestion(questwinner.getEvent(), questwinner.getQuestion(), questwinner.getBetMinimum());
			da.createQuote(questwinner, qnowinner.getQuote(), qnowinner.getMulti());
			qnowinner = da.getQuote(qnowinner);
			assertTrue(da.makeWinner(questwinner, qnowinner));
		}catch( Exception e) {
			fail();
		}
		
	}
}
