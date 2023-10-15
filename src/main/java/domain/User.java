package domain;

import java.util.Date;
import java.util.Objects;
import java.util.ArrayList;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;
@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class User extends UserAbstract {
	


	private String izena;
	private int age;
	private float wallet;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private ArrayList<Movement> movements = new ArrayList<Movement>();
	private ArrayList<Bet> bets;
	@XmlIDREF @OneToMany(fetch=FetchType.EAGER)
	private ArrayList<User> jarraituak;
	@XmlIDREF @OneToMany(fetch=FetchType.EAGER)
	private ArrayList<User> jarraitzaileak;
	private float betmax;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Statistics statistics;
	public float getBetmax() {
		return betmax;
	}
	public void setBetmax(float betmax) {
		this.betmax = betmax;
	}
	public ArrayList<User> getJarraituak() {
	
		return jarraituak;
	}
	public void setJarraituak(ArrayList<User> jarraituak) {
		this.jarraituak = jarraituak;
	}
	public ArrayList<User> getJarraitzaileak() {
		return jarraitzaileak;
	}
	public void setJarraitzaileak(ArrayList<User> jarraitzaileak) {
		this.jarraitzaileak = jarraitzaileak;
	}

	
	public User(String username, String password, String izena, int age) {
		super(username, password);
		this.izena= izena;
		this.age= age;
		this.movements= new ArrayList<Movement>();
		this.bets= new ArrayList<Bet>();
		this.wallet=0;
		this.betmax=0;
		this.jarraituak= new ArrayList<User>();
		this.jarraitzaileak= new ArrayList<User>();
		this.statistics = new Statistics();
	}
	public User() {
		super();
	}
	public float getWallet() {
		return wallet;
	}
	public void setWallet(float wallet) {
		this.wallet = wallet;
	}
	public ArrayList<Bet> getBets() {
		return bets;
	}
	public void setBets(ArrayList<Bet> bets) {
		this.bets = bets;
	}
	public ArrayList<Movement> getMovements() {
		return movements;
	}
	public void setMovements(ArrayList<Movement> movements) {
		this.movements = movements;
	}
	public String getIzena() {
		return izena;
	}
	public void setIzena(String izena) {
		this.izena = izena;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public void removeBet(Bet b) {
		bets.remove(b);
	}
	public void addJarraitu(User u) {
		this.jarraituak.add(u);
	}
	public void addJarraitzaile(User u) {
		this.jarraitzaileak.add(u);
	}
	public void setStatistics(Statistics s) {
	this.statistics= s;
	}
	public Statistics getStatistics() {
		return this.statistics;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ Objects.hash(age, betmax, bets, izena, jarraituak, jarraitzaileak, movements, statistics, wallet);
		return result;
	}
	
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return  super.equals(obj);
	}
	
	
}

