package domain;

import java.util.Objects;
import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlIDREF;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Bet {
	@XmlID @GeneratedValue @Id @XmlJavaTypeAdapter(IntegerAdapter.class)
	private Integer number;
	private float kop;
	@XmlIDREF
	private User user;
	@XmlIDREF @OneToMany(fetch=FetchType.EAGER)
	private Vector<Quote> quotes;
	private boolean betUpdated;
	

	public Bet(float kop, User user) {
		this.kop=kop;
		this.user=user;
		this.quotes= new Vector<Quote>();
		betUpdated = false;
	}
	
	public Bet() {
		super();
	}
	
	public boolean getBetUpdated() {
		return this.betUpdated;
	}
	
	public void setBetUpdated(boolean b) {
		this.betUpdated = b;
	}
	
	public float getKop() {
		return kop;
	}
	public void setKop(float kop) {
		this.kop = kop;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void remove() {
		user.removeBet(this);
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bet other = (Bet) obj;
		return number == other.number;
	}
	public Vector<Quote> getQuotes() {
		return quotes;
	}
	public void setQuotes(Vector<Quote> quotes) {
		this.quotes = quotes;
	}
}
