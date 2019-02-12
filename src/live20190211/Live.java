package live20190211;

import java.io.Serializable;
import java.sql.Timestamp;

public class Live implements Serializable {
	
private static final long serialVersionUID = -6748584585155653661L;

private String host_id;
private String live_name;
private Integer live_acc;
private byte[] live_pic;
private Timestamp live_date;
private Integer live_status;
//@@@
public Live() {
	
}
public String getHost_id() {
	return host_id;
}
public void setHost_id(String host_id) {
	this.host_id = host_id;
}
public String getLive_name() {
	return live_name;
}
public void setLive_name(String live_name) {
	this.live_name = live_name;
}
public Integer getLive_acc() {
	return live_acc;
}
public void setLive_acc(Integer live_acc) {
	this.live_acc = live_acc;
}
public byte[] getLive_pic() {
	return live_pic;
}
public void setLive_pic(byte[] live_pic) {
	this.live_pic = live_pic;
}
public Timestamp getLive_date() {
	return live_date;
}
public void setLive_date(Timestamp live_date) {
	this.live_date = live_date;
}
public Integer getLive_status() {
	return live_status;
}
public void setLive_status(Integer live_status) {
	this.live_status = live_status;
}

}

