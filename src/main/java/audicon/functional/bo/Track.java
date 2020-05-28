package audicon.functional.bo;

public class Track {
	private String title;
	private String artist;
	private String length;
	private String conversionDate;
	
 
	public Track(String title, String artist, String length, String date) {
		super();
		this.title = title;
		this.artist = artist;
		this.length = length;
		this.conversionDate = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(String conversionDate) {
		this.conversionDate = conversionDate;
	}
	
	
}
