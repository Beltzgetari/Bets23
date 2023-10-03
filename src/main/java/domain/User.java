package domain;

import java.util.Date;
import java.util.Vector;

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
	private Vector<Movement> movements = new Vector<Movement>();
	private Vector<Bet> bets;
	@XmlIDREF @OneToMany(fetch=FetchType.EAGER)
	private Vector<User> jarraituak;
	@XmlIDREF @OneToMany(fetch=FetchType.EAGER)
	private Vector<User> jarraitzaileak;
	private float betmax;
	@OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	private Statistics statistics;
	public float getBetmax() {
		return betmax;
	}
	public void setBetmax(float betmax) {
		this.betmax = betmax;
	}
	public Vector<User> getJarraituak() {
	
		return jarraituak;
	}
	public void setJarraituak(Vector<User> jarraituak) {
		this.jarraituak = jarraituak;
	}
	public Vector<User> getJarraitzaileak() {
		return jarraitzaileak;
	}
	public void setJarraitzaileak(Vector<User> jarraitzaileak) {
		this.jarraitzaileak = jarraitzaileak;
	}

	
	public User(String username, String password, String izena, int age) {
		super(username, password);
		this.izena= izena;
		this.age= age;
		this.movements= new Vector<Movement>();
		this.bets= new Vector<Bet>();
		this.wallet=0;
		this.betmax=0;
		this.jarraituak= new Vector<User>();
		this.jarraitzaileak= new Vector<User>();
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
	public Vector<Bet> getBets() {
		return bets;
	}
	public void setBets(Vector<Bet> bets) {
		this.bets = bets;
	}
	public Vector<Movement> getMovements() {
		return movements;
	}
	public void setMovements(Vector<Movement> movements) {
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
}

