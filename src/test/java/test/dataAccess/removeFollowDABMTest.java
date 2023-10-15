package test.dataAccess;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quote;
import domain.User;

public class removeFollowDABMTest {
	 DataAccess da = Mockito.mock(DataAccess.class);
	 BLFacadeImplementation blf = new BLFacadeImplementation(da);
	 
	 
	 @Test
	 public void Test1() {
		
		User us1 = new User();
		User us2 = new User();
		
		
		try {
			blf.removeFollow(us1, us2);
			Mockito.verify(da, Mockito.times(1)).removeFollow(Mockito.any(User.class),Mockito.any(User.class));
		}catch(Exception e) {
			fail();
		}
		 
	 }
	 
	 @Test
	 public void Test2(){
		 User jarraitu = null;
		 User jarraitzaile = null;
		try {		
			blf.removeFollow(jarraitu, jarraitzaile);
				
		}catch(Exception e) {
			fail();	
		
		}
	 }
}
