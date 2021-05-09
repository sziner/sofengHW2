package mobilePhone;

import java.util.Date;

public abstract class CalendarEntry {
	public CalendarEntry(Date date, int duration){
		this.date=date;
		this.duration=duration;
	}
	Date date;
	int duration;
}
