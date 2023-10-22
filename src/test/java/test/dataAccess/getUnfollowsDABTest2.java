package test.dataAccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
import domain.UserAbstract;
public class getUnfollowsDABTest2 {
	private ArrayList<User> unfollows ;
	private DataAccess da;
	private User Mikel2;
	private User nulo;
	private User Xabi;
	private User mikelezdatubasean;
	private User mikel;
	
	@Before
	public void initialize() {
		Mikel2 = new User("Beltzgetari2", "OnePieceUnaMierdax2", "Mikel2", 19 );
		
		
		da = new DataAccess();
		da.ezabatu();
		mikel = da.getUserUsername("Beltzgetari");
		nulo = null;
		mikelezdatubasean = da.getUserUsername("Mikel-Xabi");
		Xabi = new User("MundukoErregie", "OnePieceUnaMierda", "Xabi", 20);
		
		
		da.register(Xabi);
		da.register(Mikel2);
	}
	
	@Test
	public void Test1() {
		try{
			da.getUnfollows(nulo);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	@Test
	public void Test2() {
		try{
			da.getUnfollows(mikelezdatubasean);
		
		
		}catch (Exception e) {
			assertTrue(true);
		}
	}
	/**
	 * 
	 * @Test
	public void Test3() { 
		ArrayList<User> xabibakarrik = new ArrayList<User>();
		
		xabibakarrik.add(Xabi);
	ArrayList<User> listaemaitza = da.getUnfollows(Mikel2);
		for ( int  a =0; a <listaemaitza.size(); a++) {
			System.out.println(	listaemaitza.size());
			
			assertTrue(xabibakarrik.get(a).getUsername().equals(listaemaitza.get(a).getUsername()));
		}
	}
	 */
	
	
}
