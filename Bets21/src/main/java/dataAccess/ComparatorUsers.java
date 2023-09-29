package dataAccess;

import java.util.Comparator;

import domain.User;

public class ComparatorUsers implements Comparator<User>{

	public int compare(User o1, User o2) {
		// TODO Auto-generated method stub
		return ((Float)o1.getStatistics().getWinMoney()).compareTo((Float)o2.getStatistics().getWinMoney());
	}

}
