package test.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.User;

public class removeFollowDABTest {
	private ArrayList<User> unfollows ;
	private DataAccess da;
	private User jarraitzailenulo;
	private User jarraitunull;
	private User Xabi;
	private User Mikel;
	private User mikelezdatubasean;
	private User xabiezdatubasean;
	private User xabikezjarraitumikel;
	private User mikelekezjarraitzailexabi ; 
	
	@Before
	public void initialize() {
		
		jarraitunull = null;
		jarraitzailenulo= null;
		Mikel = new User("Beltzgetari", "OnePieceUnaMierda", "Mikel", 19 );
		
		Xabi = new User("MundukoErregie", "OnePieceUnaMierda", "Xabi", 20);
		Xabi.addJarraitu(Mikel);
		Mikel.addJarraitzaile(Xabi);
		da = new DataAccess();
		da.ezabatu();
		mikelezdatubasean = new User("Mikel", "OnePieceUnaMierda", "Mikel", 19 );
		xabiezdatubasean = new User("Xabi", "OnePieceUnaMierda", "Xabi", 20);
		xabikezjarraitumikel = new User("Xabi-Mikel", "OnePieceUnaMierda", "Xabi-Mikel", 40);
		mikelekezjarraitzailexabi = new User("Mikel-Xabi", "OnePieceUnaMierda", "Mikel-Xabi", 40);
		da.register(xabikezjarraitumikel);
		da.register(mikelekezjarraitzailexabi);
		da.register(Xabi);
		da.register(Mikel);
	}
	
	@Test
	public void Test1() {
		try{
			da.removeFollow(jarraitunull, Xabi);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test2() {
		try{
			da.removeFollow(Mikel, jarraitzailenulo);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test3() {
		try{
			da.removeFollow(Mikel, xabiezdatubasean);
			
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test4() {
		try{
			//Quote-a ez dago Question-n (Egin behar)
			da.removeFollow(mikelezdatubasean, Xabi);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test5() { 
		try{
			//Quote-a ez dago Question-n (Egin behar)
			da.removeFollow(Mikel, xabikezjarraitumikel);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
		
	}
	@Test
	public void Test6() { 
		try{
			//Quote-a ez dago Question-n (Egin behar)
			da.removeFollow(mikelekezjarraitzailexabi, Xabi);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
		
	}
	@Test
	public void Test7() {
		int sizelista1  = Xabi.getJarraituak().size();
		int sizelista2 = Mikel.getJarraitzaileak().size();
		da.removeFollow(Mikel, Xabi);
		Xabi = da.getUserUsername(Xabi.getUsername());
		Mikel = da.getUserUsername(Mikel.getUsername());
		int sizeberria1 = Xabi.getJarraituak().size();
		int sizeberria2 = Mikel.getJarraitzaileak().size();
		assertEquals(sizelista1, sizeberria1+1);
		assertEquals(sizelista2, sizeberria2+1);
	}
}
