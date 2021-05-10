package mobilePhone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class CalendarApp implements Applicable {

	private class Month implements Comparable<Date> {
		Date month;
		ArrayList<Day> days;
		
		/*public Month(Date date) {
			this.month = new Date(date.getYear(), date.getMonth(), 1);
			this.days = new ArrayList<Day>();
		}*/

		public Month(Date date, AbstractCalendarEvent event) {
			this.month = new Date(date.getYear(), date.getMonth(), 1);
			this.days = new ArrayList<Day>();
			this.days.add(new Day(date.getDate(), event));
		}

		@Override
		public int compareTo(Date o) {
			return this.month.compareTo(new Date(o.getYear(), o.getMonth(), 1));
		}
	}


	private class Day implements Comparable<Date> {
		int day;
		ArrayList<AbstractCalendarEvent> eventsList;

		public Day(int day, AbstractCalendarEvent event) {
			this.day = day;
			this.eventsList = new ArrayList<AbstractCalendarEvent>();
			this.eventsList.add(event);
		}

		@Override
		public int compareTo(Date date) {
			return (this.day - date.getDate());
		}
	}
	

	ArrayList<Contact> list;
	ArrayList<Month> months;

	public CalendarApp(ArrayList<Contact> list) {
		this.list = list;
		months = new ArrayList<Month>();
	}

	public void addCalendarEvent(AbstractCalendarEvent event) throws Exception {
		if (event instanceof MeetingCalendarEvent) {
			MeetingCalendarEvent meeting = (MeetingCalendarEvent) event;
			Contact c = meeting.contact;
			if (list.contains(c)) {
				c.addMeeting(meeting);
			} else {
				throw new Exception("Contact does not exist");
			}
		}
		Date date = event.getEventDate();
		int i = Collections.binarySearch(months, date);
		if (i>=0) {
			Month month = months.get(i);
			int j = Collections.binarySearch(month.days, date);
			if (j >= 0) {
				Day day = month.days.get(j);
				int k = Collections.binarySearch(day.eventsList, event);
				if (k >= 0) {
					day.eventsList.add(k, event);
				} else {
					day.eventsList.add(-(k+1), event);
				}
			} else {
				Day day = new Day(date.getDate(), event);
				month.days.add(-(j+1), day);
			}
		} else {
			Month month = new Month(date, event);
			months.add(-(i+1), month);
		}
	}


	public void removeContactMeetings(Contact c) {

	}

	public void removeCalendarEvent(AbstractCalendarEvent event) {
		
	}

	public void printDay(Date date) {
		//TODO
	}
	
	public void printContactMeetings(String name) {
		//TODO
	}

	public void removeOverlaps() {
		//TODO
	}

	public void printAllEvents() {
		//TODO
	}

	@Override
	public void run(Scanner s) {
		// TODO
		
	}
	
	
}
