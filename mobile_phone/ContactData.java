package mobile_phone;

import java.util.LinkedList;

public class ContactData extends Contact implements Chat, Agenda {	
 private String chat;
 private LinkedList<CalendarEntry> agenda;
 
 public ContactData(String name, int phonenumber) {
	 super(name, phonenumber);
 }
 @Override
public String getChat() {
	return chat;
}
@Override
public void setChat(String chat) {
	this.chat = chat;
}
@Override
public String getName() {
	 return this.name;
 }
 @Override
public int getPhonenumber() {
	 return this.phonenumber;
 }
 @Override
public String toString() {
	String str = this.name + " " + this.phonenumber;
	return str;
}
@Override
public void print() {
	 System.out.println(this.toString());
 }
 @Override
public boolean equals(Contact other) {
	 return this.name.equals(other.name) && this.phonenumber==other.phonenumber;
 }
@Override
public void setChat() {
	
}
}
