package test.dataAccess;


import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quote;

public class makeWinnerDABMTest { 
	 
	 DataAccess da = Mockito.mock(DataAccess.class);
	 BLFacadeImplementation blf = new BLFacadeImplementation(da);
	 
	 
	 @Test
	 public void Test1() {
		Question quest = new Question();
		Quote q = new Quote();
		
		when(da.makeWinner(quest, q)).thenReturn(true);
		try {
			
			blf.makeWinner(quest, q);
			assertTrue(true);
		}catch(Exception e) {
			fail();
		}
		 
	 }
	 
	 @Test
	 public void Test2(){
		 Question quest = new Question();
			Quote q = new Quote();
			
			when(da.makeWinner(quest, q)).thenReturn(false);
			try {		
				blf.makeWinner(quest, q);
				assertTrue(true);
			}catch(Exception e) {
				fail();	
			}
	 }
}
