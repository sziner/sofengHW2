package mobilePhone;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TestMobilePhone {
	public static void main(String args[]) {
		final String PATH = "mobilePhone\\testInput.txt";//external input file. Can be found in the zip file
		File file= new File(PATH);
		try {
			Scanner fileScan = new Scanner(file);
			MobilePhone phone = new MobilePhone(fileScan);
			phone.powerUp();
			fileScan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
