package mobilePhone;

public class VideoMedia extends Media {

	public VideoMedia(String label, float length) {
		super(label, length);
	}

	@Override
	public void play() {
		System.out.println("playing video " + label + " for " + length + "m.");
	}
	
}
