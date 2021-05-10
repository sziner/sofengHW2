package mobilePhone;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractCalendarEvent implements Comparable<AbstractCalendarEvent> {

	
	public AbstractCalendarEvent(Date date, int duration){
		this.date = date;
		this.duration = duration;
		this.df = new SimpleDateFormat();
	}
	protected Date date;
	protected int duration;
	protected SimpleDateFormat df; 

	public Date getEventDate() {
		return date;
	}
	
	public int getDuration() {
		return duration;
	}
	
	@Override
	public String toString() {
		return df.format(date) + " duration: " + duration;
	}
	
	public boolean equals(AbstractCalendarEvent o) {
		return ((this.date.equals(o.date)) && (this.duration == o.duration));
	}
	
	@Override
	public int compareTo(AbstractCalendarEvent o) {
		int dateDif = this.date.compareTo(o.date);
		return ((dateDif != 0) ? dateDif : -(this.duration - o.duration));
	}
}
