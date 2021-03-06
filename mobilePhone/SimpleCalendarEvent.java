package mobilePhone;

import java.util.Date;

public class SimpleCalendarEvent extends AbstractCalendarEvent {
	String description;
	
	public SimpleCalendarEvent(Date date, int duration, String description) {
		super(date, duration);
		this.description = description;
	}
	
	public String getDescription() {
		return description;
	}

	@Override
	public boolean equals(AbstractCalendarEvent o) {
		if (o == null) {
			return false;
		}
		if (this.getClass() != o.getClass()) {
			return false;
		}

		SimpleCalendarEvent event = (SimpleCalendarEvent) o;
		return this.equals(event);
	}

	public boolean equals(SimpleCalendarEvent o) {
		return (super.equals((AbstractCalendarEvent) o) && (this.description.equals(o.description)));
	}

	@Override
	public int compareTo(Object o) {
		int res = super.compareTo(o);
		if (res != 0 || o instanceof Date) {
			return res;
		} else if (o instanceof MeetingCalendarEvent) {
			return 1;
		} else {
			SimpleCalendarEvent oSimple = (SimpleCalendarEvent) o;
			return this.description.compareTo(oSimple.description);
		}
	}

	@Override
	public String toString() {
		return super.toString() + ": " + description;
	}
}
