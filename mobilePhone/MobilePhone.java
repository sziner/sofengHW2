package mobilePhone;

import java.util.ArrayList;
import java.util.Scanner;

//import java.util.*;
public class MobilePhone {

	private ArrayList<Applicable> appList = new ArrayList<Applicable>();
	private ArrayList<Contact> contactList = new ArrayList<Contact>();
	Scanner s = new Scanner(System.in);

	public MobilePhone() {
		CalendarApp cal = new CalendarApp(contactList);
		appList.ensureCapacity(5);
		appList.add(0, new OperatingSystem());
		appList.add(1, new PhoneBookApp(contactList, cal));
		appList.add(2, new SmsApp(contactList));
		appList.add(3, cal);
		appList.add(4, new MediaPlayerApp());
	}

	private class OperatingSystem implements Applicable {

		@Override
		public void apply(Scanner s) {
			int op;
			System.out.println("WELCOME TO " + this);
			while (true) {
				System.out.println("\nChoose application to run:");
				for(int i = 1; i <appList.size(); ++i) {
					System.out.println(i + ". " + appList.get(i));
				}
				System.out.println(appList.size() + ". EXIT");
				if (!s.hasNextInt()) {
					System.out.println("Illegal input");
					s.next();
					continue;
				}
				op = s.nextInt();
				if (op < 1 || op > appList.size()) {
					System.out.println("Invalid input\n");
					continue;
				} else if (op == appList.size()) {
					System.out.println("Goodbye!");
					return;
				} else {
					appList.get(op).apply(s);
				}
			}
		}

		public String toString() {
			return "MOBILE PHONE";
		}

		@Override
		public void printApp() {
			System.out.println("Installed applications:");
			for (Applicable app : appList) {
				System.out.println(app);
				app.printApp();
			}
		}
	}

	public void startUp() {
		appList.get(0).apply(s);
	}
}
