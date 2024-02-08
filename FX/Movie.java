package application;

//Class for the Movie


public class Movie extends Media implements Comparable<Movie>
{

	private String rating;
	
// Setters and Getters 
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

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		if(rating == "DR" || rating == "HR" || rating == "AC")
		{
		this.rating = rating;
		}
		else
		{
			System.out.println("Rating should only be one of those (DR), (HR) , (AC)");
		}
	}
	//Implementing the Comparable method

	@Override
	public int compareTo(Movie o) {
		
		return o.title.compareTo(title);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Movie [rating=" + rating + "]";
	}
}