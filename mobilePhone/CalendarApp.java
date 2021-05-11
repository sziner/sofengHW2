package mobilePhone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Scanner;

public class CalendarApp implements Applicable {

	private class Month implements Comparable<Date> {
		Date month;
		ArrayList<Day> days;

		/*
		 * public Month(Date date) { this.month = new Date(date.getYear(),
		 * date.getMonth(), 1); this.days = new ArrayList<Day>(); }
		 */

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
		if (i >= 0) {
			Month month = months.get(i);
			int j = Collections.binarySearch(month.days, date);
			if (j >= 0) {
				Day day = month.days.get(j);
				int k = Collections.binarySearch(day.eventsList, event);
				if (k >= 0) {
					day.eventsList.add(k, event);
				} else {
					day.eventsList.add(-(k + 1), event);
				}
			} else {
				Day day = new Day(date.getDate(), event);
				month.days.add(-(j + 1), day);
			}
		} else {
			Month month = new Month(date, event);
			months.add(-(i + 1), month);
		}
	}

	public void removeContactMeetings(Contact contact) throws Exception {
		for (MeetingCalendarEvent meeting : contact.getMeetings()) {
			removeCalendarEvent(meeting);
		}
	}

	public void removeCalendarEvent(AbstractCalendarEvent event) throws Exception {// TODO unfinished
		if (event instanceof MeetingCalendarEvent) {
			MeetingCalendarEvent meeting = (MeetingCalendarEvent) event;
			ArrayList<MeetingCalendarEvent> meetings = meeting.contact.getMeetings();
			int index = Collections.binarySearch(meetings, event);
			if (index >= 0) {
				meetings.remove(index);
			} else {
				throw new Exception("Meeting does not exist");
			}
		}
		Date date = event.getEventDate();
		int i = Collections.binarySearch(months, date);
		if (i < 0) {
			throw new Exception("Event not found");
		}
		Month month = months.get(i);
		int j = Collections.binarySearch(month.days, date);
		if (j < 0) {
			throw new Exception("Event not found");
		}
		Day day = month.days.get(j);
		int k = Collections.binarySearch(day.eventsList, event);
		if (k < 0) {
			throw new Exception("Event not found");
		}
		day.eventsList.remove(k);
		if (day.eventsList.isEmpty()) {
			month.days.remove(j);
			if (month.days.isEmpty()) {
				months.remove(i);
			}
		}
	}

	public void printDay(Date date) {
		int i = Collections.binarySearch(months, date);
		if (i < 0) {
			System.out.println("No events for " + date.getDate() + "/" + (date.getMonth() + 1) + "/"
					+ (date.getYear() + 1900) + ".");
			return;
		}
		Month month = months.get(i);
		int j = Collections.binarySearch(month.days, date);
		if (j < 0) {
			System.out.println("No events for " + date.getDate() + "/" + (date.getMonth() + 1) + "/"
					+ (date.getYear() + 1900) + ".");
			return;
		}
		Day day = month.days.get(j);
		System.out.println(
				"Events for " + date.getDate() + "/" + (date.getMonth() + 1) + "/" + (date.getYear() + 1900) + ":");
		for (AbstractCalendarEvent event : day.eventsList) {
			System.out.println("- " + event);
		}
		System.out.println();
	}

	public void printContactMeetings(String name) throws Exception {
		int index = list.indexOf(name);
		if (index >= 0) {
			Contact contact = list.get(index);
			ArrayList<MeetingCalendarEvent> meetings = contact.getMeetings();
			if (meetings.isEmpty()) {
				System.out.println("No meetings for " + contact.getName());
			} else {
				System.out.println("Meetings for " + contact.getName() + ":");
				for (AbstractCalendarEvent event : meetings) {
					System.out.println("- " + event);
				}
			}
			System.out.println();
		} else {
			throw new Exception("Contact does not exist");
		}
	}

	public void removeOverlaps() {
		// TODO
	}

	public void printAllEvents() {
		for (Month m : months) {
			for (Day d : m.days) {
				System.out.println(d.day + "/" + (m.month.getMonth() + 1) + "/" + (m.month.getYear() + 1900) + ":");
				for (AbstractCalendarEvent event : d.eventsList) {
					System.out.println(event);
				}
				System.out.println();
			}
		}
		System.out.println();
	}

	@Override
	public void run(Scanner s) {
		// TODO

	}

}
