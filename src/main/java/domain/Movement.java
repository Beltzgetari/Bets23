package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@XmlAccessorType(XmlAccessType.FIELD)
public class Movement {
	@XmlID @XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id @GeneratedValue
	private Integer movenumb;
	private String desc;
	private float amount;
	
	public Movement(String desc, float amount) {
		this.desc= desc;
		this.amount= amount;
	}
	public Movement() {
		super();
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return  desc + ", " + amount;
	}
	
}
