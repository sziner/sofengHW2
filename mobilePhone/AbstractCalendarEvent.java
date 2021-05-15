package mobilePhone;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class AbstractCalendarEvent implements Comparable<Object> {

	
	public AbstractCalendarEvent(Date date, int duration){
		this.date = date;
		this.duration = duration;
		this.df = new SimpleDateFormat();
	}
	protected Date date;
	protected int duration;
	protected SimpleDateFormat df; 

	public Date getDate() {
		return date;
	}
	
	public int getDuration() {
		return duration;
	}
	
	@Override
	public String toString() {
		return df.format(date) + " (" + duration + "m) ";
	}
	
	public boolean equals(AbstractCalendarEvent o) {
		return ((this.date.equals(o.date)) && (this.duration == o.duration));
	}
	
	@Override
	public int compareTo(Object o) {
		if (o instanceof AbstractCalendarEvent) {
			AbstractCalendarEvent e = (AbstractCalendarEvent) o;
			int dateDif = this.date.compareTo(e.getDate());
			return ((dateDif != 0) ? dateDif : (this.duration - e.duration));
		} else {
			return this.date.compareTo((Date) o);
		}
	} 
}
