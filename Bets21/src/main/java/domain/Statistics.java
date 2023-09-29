package domain;

import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Statistics {
	@Id @GeneratedValue @XmlID	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer id;
	private float winMoney;
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer amountOfBets;
	private float victoryRatio;
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private  Integer winAmount;
	
	public Statistics() {
		super();
		this.winMoney= 0;
		this.amountOfBets= 0;
		this.victoryRatio= 0;
		this.winAmount= 0;
		
	}

	public Float getWinMoney() {
		return winMoney;
	}

	public void setWinMoney(Float winMoney) {
		this.winMoney = winMoney;
	}

	public int getAmountOfBets() {
		return amountOfBets;
	}

	public void setAmountOfBets(Integer amountOfBets) {
		this.amountOfBets = amountOfBets;
		System.out.println("apostu kopurua eguneratu da");
	}

	public float getVictoryRatio() {
		return victoryRatio;
	}

	public void setVictoryRatio(Integer winAm, Integer amountOfB) {
		if(amountOfB != 0)
			this.victoryRatio = (winAm.floatValue()/amountOfB.floatValue()) * 100;
		else
			this.victoryRatio = 0.0f;
	}

	public int getWinAmount() {
		return winAmount;
	}

	public void setWinAmount(Integer winAmount) {
		this.winAmount = winAmount;
	}
	
	public void updateStatistics(float moneyWin, boolean irabazi) {
		if(irabazi) {
			this.winMoney += moneyWin;
			this.winAmount++;
		}
		this.setVictoryRatio(this.winAmount, this.amountOfBets);
	}
	
}
