package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class UserAdmin extends UserAbstract {
	public UserAdmin(String username, String password) {
		super(username, password );
	}
	
	public UserAdmin() {
		super();
	}
}
