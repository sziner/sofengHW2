package mobilePhone;

import java.util.Date;

public class Meeting extends CalendarEntry{

	public Meeting(Date date, int duration, Contact contact) {
		super(date, duration);
		this.contact = contact;
	}
	Contact contact;
}
