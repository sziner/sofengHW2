package mobilePhone;

import java.util.ArrayList;
import java.util.ListIterator;

public class Contact implements Comparable<Contact>  {

	private String name;
 	private int phonenumber;	
 	private ArrayList<String> chat;
 	private ArrayList<MeetingCalendarEvent> meetings;

	/* regular class methods */
 
 	public Contact(String name, int phonenumber) {
		this.name = name;
		this.phonenumber = phonenumber;
	}
	
	public String getName() {
		return this.name;
	}
	public int getPhonenumber() {
		return this.phonenumber;
	}

	public String toString() {
		String str = this.name + " " + this.phonenumber;
		return str;
	}

	public void print() {
		System.out.println(this.toString());
	}
	
	public boolean equals(Contact other) {
		return this.name.equals(other.name);
	}

	public boolean equals(String name) {
		return this.name.equals(name);
	}

	/* Chat methods */ 

	public ArrayList<String> getChat() {
		return chat;
	}		
	
	public void addToChat(String str) {
		chat.add(str);	
	}
	
	public void deleteChat() {
		chat.clear();	
	}


	/*  Calendar methods */ 

	public void addMeeting(MeetingCalendarEvent entry) {
		int i = java.util.Collections.binarySearch(meetings, entry);//insert at: -(i+1)
		if (i >= 0) {
			meetings.add(i, entry);
		} else {
			meetings.add(-(i+1), entry);
		}
	}

	public void removeMeeting(MeetingCalendarEvent entry) {
		meetings.remove(entry);
	}

	public void printAgenda() {
		ListIterator<MeetingCalendarEvent> it = meetings.listIterator();
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}

	public boolean contains(String str) {
		ListIterator<String> it = chat.listIterator();
		while (it.hasNext()) {
			if (it.next().contains(str)) {
				return true;
			}
		}
		return false;
	}

	public ArrayList<MeetingCalendarEvent> getMeetings() {
		return meetings;
	}

	public int compareTo(Contact o) {
		return this.getName().compareTo(o.getName());
	}

	public int compareTo(String str) {
		return this.getName().compareTo(str);
	}
}
