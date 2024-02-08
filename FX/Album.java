package application;

import java.util.ArrayList;
import java.util.List;

public class Album extends Media implements Comparable<Album>{

	private String artist;
	private List<String> songs = new ArrayList<>();

	//Getters and Setters
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable) {
		this.copiesAvailable = copiesAvailable;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public List<String> getSongs() {
		return songs;
	}

	public void setSongs(List<String> songs) {
		this.songs = songs;
	}

	@Override
	public int compareTo(Album o) {

		return o.title.compareTo(title);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Album [artist=" + artist + ", songs=" + songs + "]";
	}

}