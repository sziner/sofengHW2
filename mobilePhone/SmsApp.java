package mobilePhone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SmsApp implements Applicable {
	
	private ArrayList<Contact> contactList;
	
	public SmsApp(ArrayList<Contact> contactList) {
		this.contactList = contactList;
	}

	public void addMessageToContact(String name, String str) throws Exception {
		Contact c = new Contact(name);
		int index = Collections.binarySearch(contactList, c);
		if (index < 0) {
			throw new Exception("Contact does not exist");
		}
		contactList.get(index).addToChat(str);
	}
	
	public void clearContactChat(String name) {
		Contact c = new Contact(name);
		int index = Collections.binarySearch(contactList, c);		
		if (index >= 0) {
			contactList.get(index).deleteChat();
		}
	}

	public void printContactChat(String name) {
		Contact c = new Contact(name);
		int index = Collections.binarySearch(contactList, c);		if (index >= 0) {
			System.out.println("Chat of " + name + ":");
			contactList.get(index).printChat();
		}
	}

	public void searchChat(String str) {
		int count = 0;
		String res = "";
		for (Contact c : contactList) {
			if (c.contains(str)) {
				res.concat(c.getName() + "\n");
				++count;
			}
		}
		if (count > 0) {
			System.out.println(count + " contacts found:\n" + res);
		} else {
			System.out.println("No contacts found.\n");
		}
		System.out.println();
	}

	public void printAllChats() {
		if (contactList.isEmpty()) {
			System.out.println("No contacts.");
			System.out.println();
		} else {
			for (Contact c : contactList) {
				System.out.println(c.getName() + ":");
				c.printChat();
				System.out.println();
			}
		}
	}

	@Override
	public void printApp() {
		printAllChats();
	}

	@Override
	public String toString() {
		return "SMS";
	}

	@Override
	public void apply(Scanner s) {
		int op;
		while(true)
		{
			try {
				System.out.println("\nSMS\nPlease select an operation:\n"
						+ "1. Add to contact's chat\n"
						+ "2. clear contact's chat\n"
						+ "3. Print contact's chat\n"
						+ "4. Search in contacts' chats\n"
						+ "5. Print all chats\n"
						+ "6. Exit\n");
				if(!s.hasNextInt())
				{
					System.out.println("Invalid input\n");
					continue;
				}
				op = s.nextInt();
				if (op==1) {
					System.out.println("enter contact's name:");
					s.nextLine();
					String name = s.nextLine();
					System.out.println("enter message:");
					String msg = s.nextLine();
					addMessageToContact(name, msg);
					continue;
				}
				else if (op==2) {
					System.out.println("enter contact name to clear chat");
					s.nextLine();
					String name = s.nextLine();
					clearContactChat(name);
					continue;
				}
				else if (op==3) {
					System.out.println("enter contact name to print his chat");
					s.nextLine();
					String name = s.nextLine();
					printContactChat(name);
					System.out.println();
					continue;
				}
				else if (op==4) {
					System.out.println("enter phrase to search");
					s.nextLine();
					String str = s.nextLine();
					searchChat(str);
					continue;
				}
				else if (op==5) {
					System.out.println("Chats:");
					printAllChats();
					System.out.println();
					continue;
				}
				else if (op==6) {
					System.out.println("Exiting " + this + "...");
					return;
				}
				System.out.println("Invalid input\n");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}//while
	}
	
}
