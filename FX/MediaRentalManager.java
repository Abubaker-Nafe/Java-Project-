package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

//you will be implementing the methods in this class

public class MediaRentalManager implements MediaRentalManagerInt {

	protected int limitedPlanLimit = 2;//limited plan

	public ArrayList<Customer> customerList = new ArrayList<>();// ArrayList for adding a customer information

	protected ArrayList<Media> mediaList = new ArrayList<>();// ArrayList for adding Media (Movie + Album + Game) information





	public void addCustomer(String name, String address, String plan,String ID,String MobileNumber)//Adding a customer
	{
		// Validate input parameter
		if ((Objects.nonNull(name) && name.length() > 0 )&& (Objects.nonNull(address) && address.length() > 0)
				&& Objects.nonNull(plan) && plan.length() > 0 && isValidPlan(plan) && Objects.nonNull(ID) && ID.length() > 0 
				&& Objects.nonNull(MobileNumber) && MobileNumber.length() > 0) {
			// Create a Customer and add it to the the Customer List
			Customer cust = new Customer();
			cust.setName(name);
			cust.setAddress(address);
			cust.setPlan(plan.toUpperCase());
			cust.setID(ID);
			cust.setMobileNumber(MobileNumber);
			customerList.add(cust);
		} else {
			throw new RuntimeException("Invalid Customer creation Parameters are provided");
		}
	}


	private boolean isValidPlan(String plan) //plan validation
	{
		return plan.length() > 0
				&& (plan.equalsIgnoreCase("LIMITED") || plan.equalsIgnoreCase("UNLIMITED"));
	}


	@Override
	public void addMovie(String title, int copiesAvailable, String rating,String code)//Adding a movie
	{
		// Validate input parameter
		if (title.length() > 0 && rating.length() > 0 && isValidRating(rating)) {
			// Construct a movie entry and add it to the Media List
			Movie movie = new Movie();
			movie.setTitle(title);
			movie.setCopiesAvailable(copiesAvailable);
			movie.setRating(rating);
			movie.setCode(code);
			mediaList.add(movie);
		} else {
			throw new RuntimeException("Invalid Media creation Parameters are provided");
		}
	}


	private boolean isValidRating(String rating) {
		if (rating == "DR" || rating == "HR" || rating == "AC")
		{
			return true;
		}
		return false;
	}


	@Override
	public void addAlbum(String title, int copiesAvailable, String artist, String songs,String code) //Adding an Album
	{
		if (title.length() > 0 && artist.length() > 0 && songs.length() > 0) {
			// Construct an Album entry and add it to the Media List
			Album album = new Album();
			album.setTitle(title);
			album.setArtist(artist);
			album.setCopiesAvailable(copiesAvailable);
			if (songs.contains(",")) {
				String[] songArr = songs.split(",");
				album.getSongs().addAll(Arrays.asList(songArr));
			}
			album.setCode(code);
			mediaList.add(album);
		} else {
			throw new RuntimeException("Invalid Album creation Parameters are provided");
		}
	}


	@Override
	public void addGame(String title, int copiesAvailable,double weight,String code)//Adding a game
	{
		if (Objects.nonNull(title) && title.length() > 0) {
			Game game = new Game();
			game.setTitle(title);
			game.setCopiesAvailable(copiesAvailable);
			game.setWeight(weight);
			game.setCode(code);
			mediaList.add(game);
		}
		else
		{
			throw new RuntimeException("Invalid game creation title isn't provided");

		}
	}
	@Override

	public void setLimitedPlanLimit(int value) 
	{
		this.limitedPlanLimit = value;
	}


	@Override
	public String getAllCustomersInfo() //Returns sorted customer info
	{
		customerList.sort((Customer s1, Customer s2) -> s1.getName().compareTo(s2.getName()));
		String customerInfo = "\n";
		for (Customer customer : customerList) 
		{
			String info = "Name=" + customer.getName() + ", Plan=" + customer.getPlan()
			+ ",rentedMediaList=" + customer.getRentedMediaList() + ", interestedMediaList=" + customer.getInterestedMediaList();
			customerInfo += info + "\n";
		}
		return customerInfo;
	}

	//	public void SortCustomers(ArrayList<Media> mediaList)//to sort the customer Arraylist
	//	{
	//		Collections.sort(mediaList);
	//	}



	@Override
	public String getAllMediaInfo() //Returns sorted Media info
	{
		mediaList.sort((Media s1, Media s2) -> s1.getTitle().compareTo(s2.getTitle()));

		String mediaInfo = "\n";
		for (Media media : mediaList) 
		{

			String info = "Media-Title=" + media.title +"  " + ",CopiesAvailable=" + media.getCopiesAvailable() + ",Code =" + media.getCode(); 
			mediaInfo += info + "\n";
		}
		return mediaInfo;
	}

	@Override
	public boolean addToCart(String customerName, String mediaTitle)//Adds media to the customer's cart
	{

		Customer selectedCustomer = null;
		// Find the customer from the customer list by name
		for (Customer customer : customerList) {
			if (customer.getName().equalsIgnoreCase(customerName)) {
				selectedCustomer = customer;
				break;
			}
		}

		boolean isMediaAdded = false;
		List<String> requestCart = selectedCustomer.getInterestedMediaList();

		// If the provided media title is not already added to the customers request cart then add it, otherwise don't add
		if (requestCart.isEmpty() || !requestCart.contains(mediaTitle)) {
			requestCart.add(mediaTitle);
			isMediaAdded = true;
		}
		return isMediaAdded;
	}


	@Override
	public boolean removeFromCart(String customerName, String mediaTitle)//removes media from cart for a customer
	{
		Customer selectedCustomer = null;
		// Find the customer from the customer list by name
		for (Customer customer : customerList) {
			if (customer.getName().equalsIgnoreCase(customerName)) {
				selectedCustomer = customer;
				break;
			}
		}

		// If Customer is Not Found, then return false
		if (selectedCustomer == null) {
			return false;
		}

		// If media is found in the customer's requested list then remove it and return true else return false
		List<String> requestMedia = selectedCustomer.getInterestedMediaList();
		return requestMedia.remove(mediaTitle);
	}


	@Override
	public String processRequests()//Processes the requests cart of each customer/sorting/Sending [mediaTitle] to [customerName]
	{
		String processingMessage = "\n";

		// Sort the customer by Name
		customerList.sort((Customer s1, Customer s2) -> s1.getName().compareTo(s2.getName()));//Arraylist.sort(Comparator<?super String>c) method for sorting                         

		// For each customer process the request cart
		for (Customer customer : customerList) {
			String plan = customer.getPlan();
			List<String> rentedMedia = customer.getRentedMediaList();
			List<String> requestCart = customer.getInterestedMediaList();
			List<String> Requestprocess = new ArrayList<>();

			if (!requestCart.isEmpty()) {
				// For each requested media
				for (String mediaRequest : requestCart) {
					// Check if the plan is LIMITED and then check the currently rented media size, if more than limited plan then don't add
					if (plan.equalsIgnoreCase("LIMITED")) {
						if (rentedMedia.size() >= limitedPlanLimit)//LIMITED plan = 2(if not changed by user)
						{
							break;
						}
					}

					if (!rentedMedia.contains(mediaRequest)) {
						//check if the title is available for renting
						boolean hasCopy = hasCopiesAvailable(mediaRequest);
						// If media is available then add it to the customer's rented cart and clear his request cart
						if (hasCopy) {
							Requestprocess.add(mediaRequest);
							rentedMedia.add(mediaRequest);
							processingMessage += "Sending " + mediaRequest + " to " + customer.getName() + "\n";
						}
					}
				}
			}

			// Clear request cart
			for (String fulfilledMessage : Requestprocess) {
				requestCart.remove(fulfilledMessage);
			}
		}

		return processingMessage;
	}


	private boolean hasCopiesAvailable(String mediaTitle)
	{
		boolean hasCopy = false;
		Iterator<Media> mediaIterator = mediaList.iterator();//using iterator to loop through the arraylist by using the hasNext() method
		while (mediaIterator.hasNext()) {
			Media media = mediaIterator.next();
			if (media.getTitle().equalsIgnoreCase(mediaTitle)) {
				if (media.getCopiesAvailable() > 0) {
					hasCopy = true;
					media.setCopiesAvailable(media.getCopiesAvailable() - 1);
					break;
				}
			}
		}
		return hasCopy;
	}


	@Override
	public boolean returnMedia(String customerName, String mediaTitle)//removes the item from the rented cart and adjust other values
	{
		for (Customer customer : customerList) {
			if (customer.getName().equalsIgnoreCase(customerName)) {
				List<String> rentedList = customer.getRentedMediaList();
				if (rentedList.contains(mediaTitle)) {
					// Remove the media from customer's rented list
					rentedList.remove(mediaTitle);
					// Update the Media Inventory
					adjustMediaCopies(mediaTitle);
					return true;
				}
			}
		}
		return false;
	}


	private void adjustMediaCopies(String mediaTitle) //When a media is returned, this method updates the media creator
	{
		Iterator<Media> mediaIterator = mediaList.iterator();//using iterator to loop through the arraylist 
		Media media = null;
		while ((mediaIterator.hasNext())) {
			media = mediaIterator.next();
			if (media.getTitle().equalsIgnoreCase(mediaTitle)) {
				media.setCopiesAvailable(media.getCopiesAvailable() + 1);
				mediaIterator.remove();
				break;
			}
		}
		if (Objects.nonNull(media)) {
			mediaList.add(media);
		}
	}


	public ArrayList<String> searchMedia(String title, String rating, String artist, String songs) 
	{

		ArrayList<String> aftersearch = new ArrayList<String>();
		ArrayList<Movie> movie = new ArrayList<Movie>();
		ArrayList<Game> game = new ArrayList<Game>();
		ArrayList<Album> album = new ArrayList<Album>();
		for (int i = 0; i < mediaList.size(); i++)
		{
			if (mediaList.get(i) instanceof Movie)
				movie.add((Movie) mediaList.get(i));
			if (mediaList.get(i) instanceof Game)
				game.add((Game) mediaList.get(i));
			if (mediaList.get(i) instanceof Album)
				album.add((Album) mediaList.get(i));
		}
		if (rating != "") {
			for (int i = 0; i < movie.size(); i++) {
				if (movie.get(i).getRating().equals(rating) && movie.get(i).getTitle().contains(title))
					aftersearch.add(movie.get(i).toString());
			}
			Collections.sort(aftersearch);
			return aftersearch;
		}
		else if (artist != "" || songs != "") {
			System.out.println();
			for (int i = 0; i < album.size(); i++)
				if (album.get(i).getArtist().indexOf(artist) > -1 && album.get(i).getTitle().contains(title) && album.get(i).getSongs().contains(songs))
					aftersearch.add(album.get(i).toString());
		} 
		else if (title != "" && rating == "" && artist == "" && songs == "") {
			for (int i = 0; i < mediaList.size(); i++) {
				if (mediaList.get(i).getTitle().equals(title))
					aftersearch.add(mediaList.get(i).toString());
			}
		}
		if (title == "" && rating == "" && artist == "" && songs == "") {
			System.out.println("cannot search");
			return null;
		}
		return aftersearch;	
	}
	
	public String searchCustomer(String ID)
	{
		for(int i =0;i<customerList.size();i++)

			if(customerList.get(i).getID().equals(ID))
				return customerList.get(i).toString();
		return null;
	}

	public void DeleteMedia(String code)
	{
		for(int i=0;i<mediaList.size();i++)
		{
			if(mediaList.get(i).getCode().equals(code))
			{
				mediaList.remove(i);
			}
		}
	}
	
	public String SearchMedia(String code)
	{
		for(int i=0;i<mediaList.size();i++)
		{
			if(mediaList.get(i).getCode().equals(code))
			{
				return mediaList.get(i).toString();
			}
		}
		return null;	
	}
	
	public void DeleteCustomer(String ID)
	{
		for(int i=0;i<customerList.size();i++)
		{
			if(customerList.get(i).getID().equals(ID))
			{
				customerList.remove(i);
			}
		}
	}
}
