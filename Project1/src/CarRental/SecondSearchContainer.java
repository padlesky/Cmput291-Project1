package CarRental;

public class SecondSearchContainer {
	
	Integer ticket_no;
	String violator_no;
	String vehicle_id;
	String office_no;
	String vtype;
	String vdate;
	String place;
	String descriptions;
	
	public SecondSearchContainer(Integer ticket_no, String violator_no, String vehicle_id, String office_no, 
							String vtype, String vdate, String place, String descriptions) {
		this.ticket_no = ticket_no;
		this.violator_no = violator_no;
		this.vehicle_id = vehicle_id;
		this.office_no = office_no;
		this.vtype = vtype;
		this.vdate = vdate;
		this.place = place;
		this.descriptions = descriptions;
	}

	public Integer getTicket_no() {
		return ticket_no;
	}

	public void setTicket_no(Integer ticket_no) {
		this.ticket_no = ticket_no;
	}

	public String getViolator_no() {
		return violator_no;
	}

	public void setViolator_no(String violator_no) {
		this.violator_no = violator_no;
	}

	public String getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(String vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getOffice_no() {
		return office_no;
	}

	public void setOffice_no(String office_no) {
		this.office_no = office_no;
	}

	public String getVtype() {
		return vtype;
	}

	public void setVtype(String vtype) {
		this.vtype = vtype;
	}

	public String getVdate() {
		return vdate;
	}

	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(String descriptions) {
		this.descriptions = descriptions;
	}
	
	
}
