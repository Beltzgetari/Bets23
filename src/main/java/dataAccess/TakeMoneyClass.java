package dataAccess;

import domain.User;

public class TakeMoneyClass {
	private float money;
	private User us;
	private String desc;
	
	public float getMoney() {
		return money;
	}

	public void setMoney(float money) {
		this.money = money;
	}

	public User getUs() {
		return us;
	}

	public void setUs(User us) {
		this.us = us;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public TakeMoneyClass(float money, User us, String desc) {
		this.money = money;
		this.us = us;
		this.desc = desc;
	}
}
