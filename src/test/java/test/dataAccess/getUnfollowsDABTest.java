package test.dataAccess;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Before;
import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.User;
public class getUnfollowsDABTest {
	private ArrayList<User> unfollows ;
	private DataAccess da;
	private User Mikel;
	private User nulo;
	private User Xabi;
	
	@Before
	public void initialize() {
		Mikel = new User("Beltzgetari", "OnePieceUnaMierda", "Mikel", 19 );
		Xabi = new User("MundukoErregie", "OnePieceUnaMierda", "Xabi", 20);
		nulo = null;
		ConfigXML c=ConfigXML.getInstance();
		unfollows= new ArrayList<User>();
		da = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		da.register(Xabi.getUsername(), Xabi.getPassword(), Xabi.getIzena(), Xabi.getAge());
		da.register(Mikel.getUsername(), Mikel.getPassword(), Mikel.getIzena(), Mikel.getAge());
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
		Vector<User> xabibakarrik = new Vector<User>();
		xabibakarrik.add(Xabi);
	Vector<User> listaemaitza = da.getUnfollows(Mikel);
		for ( int  a =0; a <listaemaitza.size(); a++) {
			assertTrue(xabibakarrik.get(a).equals(listaemaitza.get(a)));
		}
	}
	
}
