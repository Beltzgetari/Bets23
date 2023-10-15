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

public class makeWinnerDAWTest {
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
    private Quote loser;

    @Before
    public void initialize() {

        questnulo = null;
        quotenulo= null;
        qwinner = new Quote("winner", 2);
        qnowinner = new Quote("nowinner", 2);
        loser =  new Quote("loser", 2);
        questwinner = new Question("proba", 1, new Event() );
        da = new DataAccess();
        da.ezabatu();
        qwinner.makeWinner();
        eproba = new Event("proba", new Date());
        qEzdago = new Quote("noquestion", 2);
        questiongaizki = new Question("proba2", 1, new Event() );
    }
	
	@Test
	public void Test1() {
		System.out.println("Question nulua da eta orduan IF 1.1 doa");
		try{
			da.makeWinner(questnulo, qwinner);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test2() {
		try{
			System.out.println(" Quote nulua da eta orduan IF 1.2 doa");
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
			 //  da.createQuestion(eproba, questiongaizki.getQuestion(), questiongaizki.getBetMinimum());
	          //  da.createQuote(questiongaizki, qwinner.getQuote(), qwinner.getMulti());
	          //  da.createQuote(questwinner, qEzdago.getQuote(), qEzdago.getQuoteNumber());
	            da.makeWinner(questiongaizki, qEzdago);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test5() { 
		try {
			questwinner = da.createQuestion(questwinner.getEvent(), questwinner.getQuestion(), questwinner.getBetMinimum());
			da.createQuote(questwinner, qwinner.getQuote(), qwinner.getMulti());
			da.createQuote(questwinner,loser.getQuote(), loser.getMulti() );
			qwinner = da.getQuote(qwinner);
			assertFalse(da.makeWinner(questwinner, qwinner));
		}catch( Exception e) {
			fail();
		}
		
	}
	@Test
	public void Test6() { 
		try {
			questwinner = da.createQuestion(questwinner.getEvent(), questwinner.getQuestion(), questwinner.getBetMinimum());
			da.createQuote(questwinner, qnowinner.getQuote(), qnowinner.getMulti());
			qnowinner = da.getQuote(qnowinner);
			assertTrue(da.makeWinner(questwinner, qnowinner));
		}catch( Exception e) {
			fail();
		}
		
	}
}
