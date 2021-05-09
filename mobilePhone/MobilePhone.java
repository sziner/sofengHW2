package mobilePhone;
import java.util.*;
public class MobilePhone {
	public MobilePhone() {
		list = new LinkedList<ContactData>();
		pb=new PhoneBook(list);
	}
	private LinkedList<ContactData> list;
	private PhoneBook pb;
	private Sms sms;
	private Calendar cal = new Calendar();
	private MediaPlayer mp = new MediaPlayer();
}
