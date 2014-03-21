package CarRental;

public class ThirdSearchContainer {
	
	String vehicleId;
	String numberSales;
	String avgPrice;
	String totalTickets;

	public ThirdSearchContainer(String vehicleId, String numberSales, String avgPrice, String totalTickets) {
		this.vehicleId = vehicleId;
		this.numberSales = numberSales;
		this.avgPrice = avgPrice;
		this.totalTickets = totalTickets;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getNumberSales() {
		return numberSales;
	}

	public void setNumberSales(String numberSales) {
		this.numberSales = numberSales;
	}

	public String getAvgPrice() {
		return avgPrice;
	}

	public void setAvgPrice(String avgPrice) {
		this.avgPrice = avgPrice;
	}

	public String getTotalTickets() {
		return totalTickets;
	}

	public void setTotalTickets(String totalTickets) {
		this.totalTickets = totalTickets;
	}
}
