package mobilePhone;

import java.util.*;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors

public class PhoneBookApp implements Applicable {
	private ArrayList<Contact> list;
	private CalendarApp cal;

	public PhoneBookApp(ArrayList<Contact> list, CalendarApp cal) {
		this.list = list;
		this.cal = cal;
	}
	
	public void add(String name, int phonenumber) throws Exception {
		Contact c = new Contact(name, phonenumber);
		int index = Collections.binarySearch(list, c);
		if (index >= 0) {
			throw new Exception("Cannot add duplicate contact name");
		} else {
			list.add(-(index+1), c);
		}
	}

	public void removeContact(String name) throws Exception {
		Contact c = new Contact(name);
		int index = Collections.binarySearch(list, c);
		if (index >= 0) {
			cal.clearContactMeetings(name);
			list.remove(index);
		}
	}

	public void printPhoneBook() {
		if (list.isEmpty()) {
			System.out.println("Phonebook is empty");
		}
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public void search(String name) {
		int i = list.indexOf(name);
		if (i > -1) {
			System.out.println("Contact found: " + list.get(i));
		} else {
			System.out.println("Contact not found\n");
		}
	}

	public void sortByName() {
		list.sort(null);
	}

	public void sortByNumber() {
		list.sort((a, b) ->   b.getPhonenumber() - a.getPhonenumber());
	}

	public void reverse() {
		ArrayList<Contact> revLinkedList = new ArrayList<Contact>();
		for (int i = list.size() - 1; i >= 0; i--) {
			revLinkedList.add(list.get(i));
		}
		list.clear();
		list = revLinkedList;
	}

	public void exportToFile(String filename) {
		try {
			FileWriter file = new FileWriter(filename);
			for (int i = 0; i < list.size(); i++) {
				file.write(list.get(i).toString() + "\n");
			}
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFromFile(String path) {
		try {
			File file = new File(path);
			Scanner fileScan = new Scanner(file);
			while (fileScan.hasNext()) {
				String name = fileScan.next();
				int phonenumber = fileScan.nextInt();
				if(list.indexOf(name) != -1)
				{
					System.out.println("The name: " + name + " already exists.");
					continue;
				}
				list.add(new Contact(name, phonenumber));
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	}

	@Override
	public void printApp() {
		System.out.println("PHONEBOOK:");
		printPhoneBook();
	}

	@Override
	public String toString() {
		return "PHONE BOOK";
	}

	@Override
	public void apply(Scanner s) {
		int op;
		while(true)
		{
			try {
				System.out.println("\nPHONEBOOK\nPlease select an operation:\n"
						+ "1. Add contact\n"
						+ "2. Remove contact (by name)\n"
						+ "3. Print phonebook\n"
						+ "4. Find contact (by name)\n"
						+ "5. Sort phonebook by names\n"
						+ "6. Sort phonebook by phone numbers\n"
						+ "7. Reverse phonebook's order\n"
						+ "8. Save phonebook to text file\n"
						+ "9. Load contacts from text file\n"
						+ "10. Back\n");
				if(!s.hasNextInt())
				{
					System.out.println("Invalid input\n");
					continue;
				}
				op = s.nextInt();
				if (op==1) {
					System.out.println("enter a name:");
					String name = s.next();
					System.out.println("enter a phone number:");
					int phonenumber = s.nextInt();
					add(name, phonenumber);
					continue;
				}
				else if (op==2) {
					System.out.println("enter contact name to delete");
					String name;
					name = s.next();
					removeContact(name);
					continue;
				}
				else if (op==3) {
					System.out.println("Phonebook contents:");
					printPhoneBook();
					continue;
				}
				else if (op==4) {
					System.out.println("enter contact name to find");
					String name;
					name = s.next();
					search(name);
					continue;
				}
				else if (op==5) {
					sortByName();
					System.out.println("Phonebook sorted by name");
					continue;
				}
				else if (op==6) {
					sortByNumber();
					System.out.println("Phonebook sorted by numbers");
					continue;
				}
				else if (op==7) {
					reverse();
					System.out.println("Phonebook reversed");
					continue;
				}
				else if (op==8) {
					System.out.println("Enter a name for the text file:");
					String filename;
					filename = s.next();
					exportToFile(filename);
					System.out.println("Phonebook saved succesfully");
					continue;
				}
				else if (op==9) {
					System.out.println("Enter a Text file path:");
					String path;
					path = s.next();
					readFromFile(path);
					System.out.println("Entries loaded succesfully");
					continue;
				}
				else if (op==10) {
					System.out.println("Exiting...");
					return;
				}
				System.out.println("Invalid input\n");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}//while
	}
}// end Phonebook
