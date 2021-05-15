package mobilePhone;

import java.util.Date;

public class MeetingCalendarEvent extends AbstractCalendarEvent{

	Contact contact;

	public MeetingCalendarEvent(Date date, int duration, Contact contact) {
		super(date, duration);
		this.contact = contact;
	}
	
	public Contact getContact() {
		return contact;
	}

	@Override
	public boolean equals(AbstractCalendarEvent o) {
		if (o == null) {
			return false;
		}
	    if (this.getClass() != o.getClass()) {
			return false;
		}
		MeetingCalendarEvent event = (MeetingCalendarEvent) o;
	    return this.equals(event);
	}


	@Override
	public String toString() {
		return super.toString() + ": meeting with " + contact;
	}

	public boolean equals(MeetingCalendarEvent o) {
		return (super.equals(o) && (this.contact.equals(o.contact)));
	}

	public boolean equals(String name) {
		return this.contact.equals(name);
	}

	@Override
	public int compareTo(Object o) {
		int res = super.compareTo(o);
		if (res != 0 || o instanceof Date) {
			return res;
		} else if (o instanceof SimpleCalendarEvent) {
			return -1;
		} else {
			MeetingCalendarEvent oMeeting = (MeetingCalendarEvent) o;
			return this.contact.compareTo(oMeeting.contact);
		}
	}
}
