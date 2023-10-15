package test.dataAccess;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.User;

public class getUnfollowsDABMTest {
	 DataAccess da = Mockito.mock(DataAccess.class);
	 BLFacadeImplementation blf = new BLFacadeImplementation(da);
	 
	 
	 @Test
	 public void Test1() {
		User us1 = new User();
		Vector<User> unfollows= new Vector<User>()
;	
		
		when(da.getUnfollows(us1)).thenReturn(unfollows);
	
		try {
			
			blf.getUnfollows(us1);
			assertTrue(true);
		}catch(Exception e) {
			fail();
		}
		 
	 }
	 
	 @Test
	 public void Test2(){
		 User us1 = null;
		try {		
			blf.getUnfollows(us1);
				
		}catch(Exception e) {
			fail();	
			assertTrue(true);
		}
	 }

}
