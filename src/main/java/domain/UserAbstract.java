package domain;

import java.util.Vector;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlSeeAlso;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso ({User.class, UserAdmin.class})
public abstract class UserAbstract {
	@XmlID @Id
	private String username;
	private String password;




	public UserAbstract(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public UserAbstract() {
		super();
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return  username ;
	}
}