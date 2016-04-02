package wdsr.exercise3.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Record {
	private String title;
	private String artist;
	private Genre genre;
	
	private Record() {
		// empty - needed for JAXB
	}

	public Record(String title, String artist, Genre genre) {
		this.title = title;
		this.artist = artist;
		this.genre = genre;
	}

	@XmlElement(required=true, nillable = false)
	public String getTitle() {
		return title;
	}

	@XmlElement(required=true, nillable = false)
	public String getArtist() {
		return artist;
	}

	@XmlElement(required=true, nillable = false)
	public Genre getGenre() {
		return genre;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

	@Override
	public String toString() {
		return "Record [title=" + title + ", artist=" + artist + ", genre=" + genre + "]";
	}
}
