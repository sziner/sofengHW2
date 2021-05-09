package mobile_phone;

public class Contact {
	public Contact(String name, int phonenumber) {
		this.name = name;
		this.phonenumber = phonenumber;
	}

	private String name;
	private int phonenumber;

	public String getName() {
		return name;
	}
	public int getPhonenumber() {
		return phonenumber;
	}
}
