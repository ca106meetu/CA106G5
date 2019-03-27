package android.com.meetU.stick.model;

import java.io.Serializable;

public class StickVO implements Serializable{
	
	private static final long serialVersionUID = 2980603815848328178L;
	private String stick_ID;
	private String stick_name;
	
	public String getStick_ID() {
		return stick_ID;
	}
	public void setStick_ID(String stick_ID) {
		this.stick_ID = stick_ID;
	}
	public String getStick_name() {
		return stick_name;
	}
	public void setStick_name(String stick_name) {
		this.stick_name = stick_name;
	}
}
