package mobilePhone;
import java.util.*;
public class MobilePhone {
	public MobilePhone() {
		list = new ArrayList<Contact>();
		sms = new SmsApp(list);
		cal = new CalendarApp(list);
		pb = new PhoneBookApp(list, sms, cal);
		mp = new MediaPlayerApp();
	}
	private ArrayList<Contact> list;
	private PhoneBookApp pb;
	private SmsApp sms;
	private CalendarApp cal;
	private MediaPlayerApp mp;
	public void run() {
		Scanner s = new Scanner(System.in);
		int op;
		System.out.println("Welcome to MobilePhone!");
		while(true)
		{
			System.out.println("Please choose an app:\n"
				+ "1. Phone book\n"
				+ "2. Sms\n"
				+ "3. Calendar\n"
				+ "4. Media player\n"
				+ "5. Exit\n");

			if(!s.hasNextInt())//maybe wrong
			{
				System.out.println("Invalid input\n");
				continue;
			}
			op=s.nextInt();
			if(op==1)
			{
				pb.run(s);
				continue;
			}
			if(op==2)
			{
				sms.run(s);
				continue;
			}
			if(op==3)
			{
				cal.run(s);
				continue;
			}
			if(op==4)
			{
				mp.run(s);
				continue;
			}
			if(op==5)
			{
				System.out.println("Exiting...");
				break;
			}
		}
	}
}
