package CarRental;

import java.sql.Date;

public class FirstSearchContainer {
	
	String name;
	String licence_no;
	String addr;
	Date birthday;
	String dClass;
	String description;
	Date expDate;
	
	public FirstSearchContainer(String name, String licence_no, String addr, Date birthday, String dClass, String description, Date expDate) {
		this.name = name;
		this.licence_no = licence_no;
		this.addr = addr;
		this.birthday = birthday;
		this.dClass = dClass;
		this.description = description;
		this.expDate = expDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLicence_no() {
		return licence_no;
	}

	public void setLicence_no(String licence_no) {
		this.licence_no = licence_no;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getdClass() {
		return dClass;
	}

	public void setdClass(String dClass) {
		this.dClass = dClass;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getExpDate() {
		return expDate;
	}

	public void setExpDate(Date expDate) {
		this.expDate = expDate;
	}
	
	
}
