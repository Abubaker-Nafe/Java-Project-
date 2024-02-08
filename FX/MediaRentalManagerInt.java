package application;

import java.util.ArrayList;

//methods will be implemented in other classes

public interface MediaRentalManagerInt {
	

  void addCustomer(String name, String address, String plan,String ID,String MobileNumber); 	

//  Adds the specified customer to the database. The address is a physical address (not e-mail). The plan options available
//  are: LIMITED and UNLIMITED. LIMITED defines a default maximum of two media that can be rented.
//  Parameters: name - ,address -,plan -
  

  void addMovie(String title, int copiesAvailable, String rating,String code);
  
//  Adds the specified movie to the database. The possible values for rating are "DR", "HR", "AC".
//  Parameters:title - ,copiesAvailable -,rating -
  
  
  void addGame(String title, int copiesAvailable,double weight,String code);
  
//  Adds the specified game to the database.
//  Parameters:title - ,copiesAvailable -,weight -
  
  void addAlbum(String title, int copiesAvailable, String artist, String songs,String code);

//  Adds the specified album to the database. The songs String includes a list of the title of songs in the album (song
//  		titles are separated by commas).
//  		Parameters: title - ,copiesAvailable -,artist -,songs -
  
  void setLimitedPlanLimit(int value);

//  This set the number of media associated with the LIMITED plan.
//  Parameters: value -
  
  
  String getAllCustomersInfo();//Method

//Returns information about the customers in the database. The information is presented sorted by customer name.
  
  
  String getAllMediaInfo();//Method

//  Returns information about all the media (movies, albums, and games) that are part of the database. The information
//  is presented sorted by media title
  

  boolean addToCart(String customerName, String mediaTitle);

//  Adds the specified media title to the cart associated with a customer.
//  Parameters:customerName - ,mediaTitle -
//  Returns: false if the mediaTitle is already part of the cart (it will not be added)

 

  boolean removeFromCart(String customerName, String mediaTitle);

//  Removes the specified media title from the customer's cart.
//  Parameters:customerName - ,mediaTitle -
//  Returns: false if removal failed for any reason (e.g., customerName not found)


  String processRequests();
  
//  Processes the requests cart of each customer. The customers will be processed in alphabetical order. For each customer,
//  the requests cart will be checked and media will be added to the rented cart, if the plan associated with the customer
//  allows it, and if there is a copy of the media available. For UNLIMITED plans the media will be added to the rented
//  cart always, as long as there are copies associated with the media available. For LIMITED plans, the number of entries
//  moved from the requests cart to the rented cart will depend on the number of currently rented media, and whether copies
//  associated with the media are available.
//For each media that is rented, the following message will be generated:
//	"Sending [mediaTitle] to [customerName]"

  boolean returnMedia(String customerName, String mediaTitle);

//  This is how a customer returns a rented media. This method will remove the item from the rented cart and adjust any
//  other values that are necessary (e.g., copiesAvailable)
//  Parameters:customerName - ,mediaTitle -
 

  ArrayList<String> searchMedia(String title, String rating, String artist, String songs);
  
//  Returns a SORTED ArrayList with media titles that satisfy the provided parameter values. If null is specified for a
//  parameter, then that parameter should be ignore in the search. Providing null for all parameters will return all media
//  titles.
//  Parameters:
//  title - ,rating - ,artist -, songs -


}