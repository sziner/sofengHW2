package mobilePhone;

import java.util.ArrayList;

public class Contact implements Comparable<Contact>  {

	private String name;
 	private int phonenumber;	
 	private ArrayList<String> chat;

	/* regular class methods */
 
	public Contact(String name) {
		this.name = name;
		this.phonenumber = 0;
		this.chat = new ArrayList<String>();
	}
	
	public Contact(String name, int phonenumber) {
		this.name = name;
		this.phonenumber = phonenumber;
		this.chat = new ArrayList<String>();
	}
	
	public String getName() {
		return this.name;
	}
	public int getPhonenumber() {
		return this.phonenumber;
	}

	public String toString() {
		String str = this.name + " - " + this.phonenumber;
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

	public void printChat() {
		if(chat.isEmpty()) {
			System.out.println("No messages");
		} else {
			for (String msg : chat) {
				System.out.println(msg);
			}
		}
	}

	public boolean contains(String str) {
		for ( String s : chat) {
			if (s.contains(str)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int compareTo(Contact o) {
		return this.getName().compareTo(o.getName());
	}
}
