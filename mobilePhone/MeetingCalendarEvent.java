package mobilePhone;

import java.util.Date;

public class MeetingCalendarEvent extends AbstractCalendarEvent{

	Contact contact;

	public MeetingCalendarEvent(Date date, int duration, Contact contact) {
		super(date, duration);
		this.contact = contact;
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

	public boolean equals(MeetingCalendarEvent o) {
		return (super.equals(o) && (this.contact.equals(o.contact)));
	}

	@Override
	public int compareTo(AbstractCalendarEvent o) {
		int res = super.compareTo(o);
		if (res != 0) {
			return res;
		} else {
			if (o instanceof SimpleCalendarEvent) {
				return -1;
			} else {
				MeetingCalendarEvent oMeeting = (MeetingCalendarEvent) o;
				return this.contact.compareTo(oMeeting.contact);
			}
		}
	}
}
