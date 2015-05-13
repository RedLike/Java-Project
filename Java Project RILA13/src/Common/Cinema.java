package Common;

public class Cinema {

	public Integer id;
	public String City;
	public String Name;
	public String address;
	
	
	public Cinema(String city, String name, String address) {
		super();
		this.id = null;
		this.City = city;
		this.Name = name;
		this.address = address;
	}
	
	public Cinema(int id, String city, String name, String address) {
		this(city, name, address);
		this.id = id;
	}


	public String getCity() {
		return City;
	}
	
	public void setCity(String city) {
		City = city;
	}


	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}


	public int getId() {
		return id;
	}
	
	
	
	
}
