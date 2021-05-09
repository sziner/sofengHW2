package mobilePhone;

import java.util.*;
import java.io.FileWriter; // Import the FileWriter class
import java.io.IOException; // Import the IOException class to handle errors
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors

public class PhoneBook implements App {
	public PhoneBook(LinkedList<? extends Contact> list) {
		this.list = list;
	}
	private LinkedList<?extends Contact> list;

	private static class SortByName implements Comparator<Contact> {
		// Used for sorting phone book by name
		public int compare(Contact a, Contact b) {
			return a.getName().compareTo(b.getName());
		}
	}

	private static class SortByNameAndNumber implements Comparator<Contact> {
		// Used for sorting phone book by name and number
		public int compare(Contact a, Contact b) {
			int comp = a.getName().compareTo(b.getName());
			if (comp == 0) {
				return b.getPhonenumber() - a.getPhonenumber();
			} else {
				return comp;
			}
		}
	}

	private static class SortByNumber implements Comparator<Contact> {
		// Used for sorting phone book by number
		public int compare(Contact a, Contact b) {
			return b.getPhonenumber() - a.getPhonenumber();
		}
	}

	private int find_index(String name) {
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public void add(String name, int phonenumber) {
		Contact c = new Contact(name, phonenumber);
		list.add(c);
	}

	public void delete(String name) {
		int i = find_index(name);
		if (i > -1) {
			list.remove(i);
		}
	}

	public void printPhoneBook() {
		if (list.isEmpty()) {
			System.out.println("Phonebook is empty");
		}
		System.out.println("Phonebook contains:");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

	public void search(String name) {
		int i = find_index(name);
		if (i > -1) {
			System.out.println("Contact found: " + list.get(i));
		} else {
			System.out.println("Contact not found\n");
		}
	}

	public void sortByName() {
		list.sort(new SortByName());
	}

	public void sortByNumber() {
		list.sort(new SortByNumber());
	}

	public void removeDuplicates() {
		if (list.size() < 2) {
			return;
		}
		list.sort(new SortByNameAndNumber());
		ListIterator<Contact> it = list.listIterator(0);
		Contact first = it.next();
		while (it.hasNext()) {
			Contact cur = it.next();
			if (first.equals(cur)) {
				it.remove();
			} else {
				first = cur;
			}
		}
	}

	public void reverse() {
		LinkedList<Contact> revLinkedList = new LinkedList<Contact>();
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
				this.add(name, phonenumber);
			}
			fileScan.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Scanner s =new Scanner(System.in);
		int op;
		while(true)
		{
			System.out.println("\nPlease select an operation:\n"
					+ "1.  Add contact\n"
					+ "2.  Remove contact (by name)\n"
					+ "3.  Print phonebook\n"
					+ "4.  Find contact (by name)\n"
					+ "5.  Sort phonebook by names\n"
					+ "6.  Sort phonebook by phone numbers\n"
					+ "7.  Remove duplicates\n"
					+ "8.  Reverse phonebook's order\n"
					+ "9.  Save phonebook to text file\n"
					+ "10. Load contacts from text file\n"
					+ "11. Exit\n");
			if(!s.hasNextInt())
			{
				System.out.println("Invalid input\n");
				continue;
			}
			op=s.nextInt();
			if(op==1)
			{
				System.out.println("enter a name:");
				String name;
				name = s.next();
				System.out.println("enter a phone number:");
				int phonenumber;
	 			phonenumber=s.nextInt();
				add(name, phonenumber);
				continue;
			}
			if(op==2)
			{
				System.out.println("enter contact name to delete");
				String name;
				name = s.next();
				delete(name);
				continue;
			}
			if(op==3)
			{
				System.out.println("Phonebook contents:");
				printPhoneBook();
				continue;
			}
			if(op==4)
			{
				System.out.println("enter contact name to find");
				String name;
				name = s.next();
				search(name);
				continue;
			}
			if(op==5)
			{
				sortByName();
				System.out.println("Phonebook sorted by name");
				continue;
			}
			if(op==6)
			{
				sortByNumber();
				System.out.println("Phonebook sorted by numbers");
				continue;
			}
			if(op==7)
			{
				removeDuplicates();
				System.out.println("Duplicates removed");
				continue;
			}
			if(op==8)
			{
				reverse();
				System.out.println("Phonebook reversed");
				continue;
			}
			if(op==9)
			{
				System.out.println("Enter a name for the text file:");
	 			String filename;
	 			filename = s.next();
				exportToFile(filename);
				System.out.println("Phonebook saved succesfully");
				continue;
			}
			if(op==10)
			{
				System.out.println("Enter a Text file path:");
				String path;
				path = s.next();
				readFromFile(path);
				System.out.println("Entries loaded succesfully");
				continue;
			}
			if(op==11)
			{
				System.out.println("Exiting...");
				break;
			}
			System.out.println("Invalid input\n");
		}//while
		s.close();
		return;	
	}
}// end Phonebook
