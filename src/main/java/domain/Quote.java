package domain;

import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Quote {
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@XmlID @GeneratedValue @Id
	private Integer quoteNumber;
	private String quote;
	private double multi;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private ArrayList<Bet> bets = new ArrayList<Bet>();
	//isWinner = 1 --> irabazlea da, isWinner = 2 --> galtzailea da, isWinner = 0 --> oraindik emaitzik gabe
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer isWinner;

	
	public Quote(String quote, double multi) {
		this.quote = quote;
		this.multi = multi;
		this.isWinner = 0;
		bets = new ArrayList<Bet>();
	}
	
	public Quote() {
		super();
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}

	public double getMulti() {
		return multi;
	}

	public void setMulti(double multi) {
		this.multi = multi;
	}

	public ArrayList<Bet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Bet> bets) {
		this.bets = bets;
	}
	public void makeWinner() {
		isWinner = 1;
	}
	public int isWinner() {
		return isWinner;
	}

	public String toString() {
        return quote + ", x" + multi;
    }
	public void removeBets() {
		for (Bet b: this.getBets()) {
			b.remove();
		}
	}
	public void makeLoser() {
		isWinner= 2;
	}
	
	public Integer getQuoteNumber() {
		return quoteNumber;
	}
	public void setQuoteNumber(Integer numb) {
		this.quoteNumber = numb;
	}
}
