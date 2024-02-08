package application;

//Class for the Game

public class Game extends Media implements Comparable<Game>{
	private double weight;

	//Setters and Getters
	
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
	
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getCopiesAvailable() {
		return copiesAvailable;
	}

	public void setCopiesAvailable(int copiesAvailable)
	{
		this.copiesAvailable = copiesAvailable;
	}
	
	//Implementing the CompareTo method
	@Override
	public int compareTo(Game o) {
		return (int) (o.weight - weight);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Game [weight=" + weight + "]";
	} 

}
