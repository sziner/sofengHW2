package mobilePhone;

public abstract class Media {
	protected String label;
	protected float length;

	public Media(String label, float length) {
		this.label = label;
		this.length = length;
	}

	public String getLabel() {
		return label;
	}
	
	public float getLength() {
		return length;
	}
	
	public void play() {

	}

}

