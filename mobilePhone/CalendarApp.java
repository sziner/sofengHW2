package mobilePhone;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.ListIterator;
import java.util.Scanner;

public class CalendarApp implements Applicable {

	ArrayList<Contact> contactList;
	ArrayList<AbstractCalendarEvent> eventList = new ArrayList<AbstractCalendarEvent>();

	public CalendarApp(ArrayList<Contact> list) {
		this.contactList = list;
	}

	public void addCalendarEvent(AbstractCalendarEvent event) throws Exception {
		int index = Collections.binarySearch(eventList, event);
		if (index < 0) {
			eventList.add(-(index+1), event);
		} else {
			eventList.add(index, event);
		}
	}

	public void clearContactMeetings(String name) throws Exception {
		eventList.removeIf(
			event -> (
				(event instanceof MeetingCalendarEvent)
				&& ((MeetingCalendarEvent) event).equals(name)));
	}

	public boolean removeCalendarEvent(AbstractCalendarEvent event) throws Exception {
		int index = Collections.binarySearch(eventList, event);
		if (index >= 0) {
			eventList.remove(index);
			return true;
		}
		return false;
	}


	public void printDay(int d) {
		Date day = new Date(0, 0, d);
		if (eventList.isEmpty()) {
			System.out.println("No events for " + day + ".");
			return;
		}
		ListIterator<AbstractCalendarEvent> it = eventList.listIterator();
		AbstractCalendarEvent e;
		do {
			if (!(it.hasNext())) {
				System.out.println("No events for " + day + ".");
				return;
			}
			e = it.next();
		} while (e.getDate().before(day));

		day.setDate(d+1);
		if (!(e.getDate().before(day))) {
			System.out.println("No events for " + day + ".");
			return;
		}
		System.out.println(e);

		while(it.hasNext()) {
			e = it.next();
			if (!(e.getDate().before(day))) {
				return;
			}
			System.out.println(e);
		}
	}

	public void printContactMeetings(String name) throws Exception {
		Contact c = new Contact(name);
		int index = Collections.binarySearch(contactList, c);
		if (index < 0) {
			throw new Exception("Contact does not exist");
		}
		System.out.println("Meetings for " + name + ":");
		for (AbstractCalendarEvent ev : eventList) {
			if (ev instanceof MeetingCalendarEvent && ((MeetingCalendarEvent) ev).contact.equals(name)) {
				System.out.println("- " + ev);
			}
		}
	}

	public void removeOverlaps() {
		if (eventList.isEmpty()) {
			return;
		}
		ListIterator<AbstractCalendarEvent> it = eventList.listIterator(0);
		Calendar cal = Calendar.getInstance();
		AbstractCalendarEvent e1 = it.next();
		do {
			cal.setTime(e1.getDate());
			cal.add(Calendar.MINUTE, e1.duration);
			Date d1 = cal.getTime();
			while (true) {
				if (!(it.hasNext())) {
					return;
				}
				AbstractCalendarEvent e2 = it.next();
				if (d1.after(e2.getDate())) {
					it.remove();
				} else {
					e1 = e2;
					break;
				}
			}
		} while(it.hasNext());
	}

	public void printAllEvents() {
		if (eventList.isEmpty()) {
			System.out.println("No events.");
		} else {
			for (AbstractCalendarEvent ev : eventList) {
				System.out.println(ev);	
			}
		}
	}

	@Override
	public void apply(Scanner s) {
		int op;
		while(true)
		{
			try {
				System.out.println("\nCALENDAR\nPlease select an operation:\n"
						+ "1.  Add event\n"
						+ "2.  Remove event\n"
						+ "3.  Print day's events\n"
						+ "4.  Print contact's meetings\n"
						+ "5.  Remove overlaps\n"
						+ "6.  Print all events\n"
						+ "7.  Back\n");
				if(!s.hasNextInt())
				{
					System.out.println("Invalid input\n");
					s.next();
					continue;
				}
				op = s.nextInt();
				if (op==1) {
					System.out.println("enter day of month (1-30):");
					int d = s.nextInt();
					if(d<1 || d>30)
					{
					while(true){
					System.out.println("enter day of month (1-30):");
					d = s.nextInt();
					if(d>=1 && d<=30)
						continue;
					else
						System.out.println("You can't choose this day");
					}
					}
					System.out.println("enter hour of day (0-23):");
					int h = s.nextInt();
					if(h<0 || h>23) {
					while(true){
					System.out.println("enter hour of day (0-23):");
					h = s.nextInt();
					if(h>=0 && h<=23)
						continue;
					else
						System.out.println("You can't choose this hour");
					}
					}
					System.out.println("enter minutes (0-59):");
					int m = s.nextInt();
					if(m<0 && m>59) {
					while(true){
					System.out.println("enter minutes (0-59):");
					m = s.nextInt();
					if(m>=0 && m<=59)
						continue;
					else
						System.out.println("You can choose only (0-59) minutes");
					}
					}
					Date date = new Date(0, 0, d, h, m);
					System.out.println("enter duration in minutes (1-60):");
					int duration = s.nextInt();
					if(duration<1 && duration>60) {
					while(true){
					System.out.println("enter duration in minutes (1-60):");
					duration = s.nextInt();
					if(duration>=1 && duration<=60)
						continue;
					else
						System.out.println("You can choose only (1-60) minutes");
					}
					}
					l1: while (true) {
						System.out.println("Choose event type:\n1.Meeting\n2.Simple event");
						int type = s.nextInt();
						AbstractCalendarEvent ev;
						switch (type) {
							case 1:
							System.out.println("enter contact's name:");
							s.nextLine();
							String name = s.nextLine();
							Contact c = new Contact(name);
							int index = Collections.binarySearch(contactList, c);							if (index < 0) {
								System.out.println("Contact does not exist");
								continue;
							}
							c = contactList.get(index);
							ev = new MeetingCalendarEvent(date, duration, c);
							addCalendarEvent(ev);
							break l1;

							case 2:
							System.out.println("enter event's description:");
							s.nextLine();
							String des = s.nextLine();
							ev = new SimpleCalendarEvent(date, duration, des);
							addCalendarEvent(ev);
							break l1;

							default:
							System.out.println("invalid input.");
						}
					}
					continue;
				}
				else if (op==2) {
					System.out.println("enter day of month (1-30):");
					int d = s.nextInt();
					System.out.println("enter hour of day (0-23):");
					int h = s.nextInt();
					System.out.println("enter minutes (0-59):");
					int m = s.nextInt();
					Date date = new Date(0, 0, d,h,m);
					System.out.println("enter duration:");
					int duration = s.nextInt();
					l2: while (true) {
						System.out.println("Choose event type:\n1.Meeting\n2.Simple event");
						int type = s.nextInt();
						AbstractCalendarEvent ev;
						switch (type) {
							case 1:
							System.out.println("enter contact's name:");
							s.nextLine();
							String name = s.nextLine();
							Contact c = new Contact(name);
							int index = Collections.binarySearch(contactList, c);							if (index < 0) {
								System.out.println("Contact does not exist");
								continue;
							}
							c = contactList.get(index);
							ev = new MeetingCalendarEvent(date, duration, c);
							removeCalendarEvent(ev);
							break l2;

							case 2:
							System.out.println("enter event's description:");
							s.nextLine();
							String des = s.nextLine();
							ev = new SimpleCalendarEvent(date, duration, des);
							removeCalendarEvent(ev);
							break l2;

							default:
							System.out.println("invalid input.");
						}
					}
					continue;
				}
				else if (op==3) {
					System.out.println("Enter day to print (1-30):");
					int d = s.nextInt();
					printDay(d);
					continue;
				}
				else if (op==4) {
					System.out.println("enter contact name");
					s.nextLine();
					String name = s.nextLine();
					printContactMeetings(name);
					continue;
				}
				else if (op==5) {
					removeOverlaps();
					System.out.println("Overlaps removed");
					continue;
				}
				else if (op==6) {
					printAllEvents();
					continue;
				} else if (op==7) {
					System.out.println("Exiting " + this + "...");
					return;
				}
				System.out.println("Invalid input\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//while
	}

	@Override
	public void printApp() {
		printAllEvents();
	}

	@Override
	public String toString() {
		return "CALENDAR";
	}

}
