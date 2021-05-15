package mobilePhone;

public class MusicMedia extends Media {

	public MusicMedia(String label, float length) {
		super(label, length);
	}

	@Override
	public void play() {
		System.out.println("playing song " + label + " for " + length + "m.");
	}
	
}
