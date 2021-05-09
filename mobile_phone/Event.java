package mobile_phone;

import java.util.Date;

public class Event extends CalendarEntry {
	public Event(Date date, int duration, String description) {
		super(date,duration);
		this.description = description;
	}
	String description;
}
