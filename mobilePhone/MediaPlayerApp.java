package mobilePhone;

import java.util.ArrayList;
import java.util.Scanner;

public class MediaPlayerApp implements Applicable {
	ArrayList<Media> mediaList = new ArrayList<Media>();

	public void addMedia(Media m) {
		mediaList.add(m);
	}

	public void playMedia(String name) {
		for (Media m : mediaList) {
			if (m.getLabel().equals(name)) {
				m.play();
				return;
			}
		}
		System.out.println("Media not found.");
	}

	public void playAllMedia() {
		if (mediaList.isEmpty()) {
			System.out.println("No media files to play.");
		} else {
			for (Media m : mediaList) {
				m.play();
			}
		}
	}

	@Override
	public void printApp() {
		playAllMedia();
	}

	@Override
	public String toString() {
		return "MEDIA-PLAYER";
	}

	@Override
	public void apply(Scanner s) {
		int op;
		while (true) {
			try {
				System.out.println(
					"\nMEDIA-PLAYER\n"
					+ "Please select an operation:\n"
					+ "1. Add media\n"
					+ "2. play a media file\n"
					+ "3. play all media files\n"
					+ "4. Exit\n");
				/*if (!s.hasNextInt()) {
					System.out.println("Invalid input\n");
					continue;
				}*/
				op = s.nextInt();
				if (op == 1) {
					System.out.println("enter media label:");
					String label = s.next();
					System.out.println("enter length:");
					float length = s.nextFloat();
					l1: while (true) {
						System.out.println("Choose media type:\n1.Music\n2.Video");
						int type = s.nextInt();
						switch (type) {
							case 1:
							addMedia(new MusicMedia(label, length));
							break l1;

							case 2:
							addMedia(new VideoMedia(label, length));
							break l1;

							default:
							System.out.println("invalid input.");
						}
					}
					continue;
				} else if (op == 2) {
					System.out.println("Enter media label to play");
					String label = s.next();
					playMedia(label);
					continue;
				} else if (op == 3) {
					if (mediaList.isEmpty()) {
						System.out.println("No media files to play.");
					} else {
						for (Media m : mediaList) {
							m.play();
						}
					}
					continue;
				} else if (op == 4) {
					System.out.println("Exiting...");
					return;
				} else {
					System.out.println("Invalid input\n");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} // while
	}

}
