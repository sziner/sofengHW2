package mobilePhone;

import java.util.ArrayList;
import java.util.Scanner;

//import java.util.*;
public class MobilePhone {

	private ArrayList<Applicable> appList;
	private ArrayList<Contact> contactList;
	Scanner s;

	public MobilePhone(Scanner s) {
		this.s = s;
		appList = new ArrayList<Applicable>();
		contactList = new ArrayList<Contact>();
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
				System.out.println(appList.size() + ". PRINT ALL");
				System.out.println((appList.size()+1) + ". EXIT");
				if (!s.hasNextInt()) {
					System.out.println("Illegal input");
					s.next();
					continue;
				}
				op = s.nextInt();
				if (op < 1 || op > appList.size()+1) {
					System.out.println("Invalid input\n");
					continue;
				} else if (op == appList.size()) {
					printAllApps();
					return;
				} else if (op == appList.size() +1) {
					System.out.println("Goodbye!");
					return;
				} else {
					appList.get(op).apply(s);
				}
			}
		}

		public String toString() {
			return "OPERATING-SYSTEM";
		}

		@Override
		public void printApp() {
			System.out.println("Installed applications:");
			for (Applicable app : appList) {
				System.out.println("- " + app);
			}
		}
	}

	public void powerUp() {
		appList.get(0).apply(s);
	}

	private void printAllApps() {
		for (Applicable app : appList) {
			System.out.println(app + ":");
			app.printApp();
			System.out.println();
		}
	}
}
