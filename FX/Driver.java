package application;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.*;

import java.io.*;


public class Driver extends Application{

	private static MediaRentalManager mediaRentalManager = new MediaRentalManager();

	Scene MainScene,CustomerScene,MediaScene,RentScene,AddcustScene,DeletecustScene,UpdatecustScene,SearchcustScene
	,AddmediaScene,DeletemediaScene,UpdatemediaScene,SearchmediaScene,printmediascene;

	Button Return = new Button("Return to main page");
	Button Returnn = new Button("Return to main page");
	Button Returnnn = new Button("Return to main page");
	
	static String name,medianame;

	public static void main(String[] args) throws FileNotFoundException
	{


		File fout = new File("out.txt");
		PrintWriter out = new PrintWriter(fout);


		// Adding a Customer
		mediaRentalManager.addCustomer("Mamoun", "Mamoun Address", "UNLIMITED","1","05959937656");
		mediaRentalManager.addCustomer("NAFE", "Nafe address", "LIMITED","2","0597785625");


		// Adding a Movie
		mediaRentalManager.addMovie("SpiderMan", 1,"HR","ABC");
		mediaRentalManager.addMovie("El3yalkebret", 4, "HR","ABCD");

		// Adding an Album
		mediaRentalManager.addAlbum("Silawy", 2, "Silawy", "malaki,wenk","A21");
		mediaRentalManager.addAlbum("AmrDiab", 2, "AmrDiab", "shukran,2odamMerayetha","k98");
		mediaRentalManager.addAlbum("Bigsam", 3, "Bigsam", "bethon,mabethoon","l10");

		//Adding a game
		mediaRentalManager.addGame("League", 2, 100,"o2");
		mediaRentalManager.addGame("Dota",1,200,"p1");
		mediaRentalManager.addGame("CS", 1, 21,"m20");


		//Adding to cart
		mediaRentalManager.addToCart("NAFE", "SpiderMan");
		mediaRentalManager.addToCart("NAFE", "El3yalkebret");
		mediaRentalManager.addToCart("NAFE", "Silawy");
		mediaRentalManager.addToCart("Mamoun", "Silawy");
		mediaRentalManager.addToCart("Mamoun", "SpiderMan");
		mediaRentalManager.addToCart("Mamoun", "AmrDiab");




		String mediaInfo = mediaRentalManager.getAllMediaInfo();
		out.println("Media Inventory Initial State : " + mediaInfo);

		out.println("------------------------------------------------------------------------");//Decoration
		String Info = mediaRentalManager.getAllCustomersInfo();
		out.println("Customer State before Processing-1- : "+Info);

		out.println("------------------------------------------------------------------------");//Decoration

		String processingMessage = mediaRentalManager.processRequests();
		out.println("Processing Message: "+ processingMessage);

		out.println("------------------------------------------------------------------------");//Decoration

		Info = mediaRentalManager.getAllCustomersInfo();
		out.println("Customer State after Processing-2- : "+ Info);

		out.println("------------------------------------------------------------------------");//Decoration

		mediaInfo = mediaRentalManager.getAllMediaInfo();
		out.println("Media Inventory Final State : " + mediaInfo);

		out.println("------------------------------------------------------------------------");//Decoration

		mediaRentalManager.removeFromCart("A", "M2");
		Info = mediaRentalManager.getAllCustomersInfo();
		out.println("Customer State after Processing-3- : "+ Info);

		out.println("------------------------------------------------------------------------");//Decoration

		mediaRentalManager.returnMedia("Mamoun","SpiderMan");
		mediaRentalManager.returnMedia("Nafe","Silawy");
		mediaRentalManager.returnMedia("LAILA","El3yalkebret");
		mediaInfo = mediaRentalManager.getAllMediaInfo();
		out.println("Media Inventory Final State : " + mediaInfo);

		out.println("------------------------------------------------------------------------");//Decoration

		Info = mediaRentalManager.getAllCustomersInfo();
		out.println("Customer State after Processing-4- : "+ Info);

		out.println("------------------------------------------------------------------------");//Decoration

		ArrayList<String> search = mediaRentalManager.searchMedia("SpiderMan", "HR", "Silawy", "shukran");
		out.println("Searched media : \n" + search);



		String s = mediaRentalManager.searchCustomer("2");
		out.println(s);

		out.close();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		//------------------------------------------------------------------------ Image
		FileInputStream input = new FileInputStream("C:\\Users\\Public\\Pictures\\MediaRental.jpg");
		ImageView imageview = new ImageView(new Image(input));
		imageview.setFitHeight(200);
		imageview.setFitWidth(200);
		//----------------------------------------------------------Panes
		GridPane pane = new GridPane();
		pane.setVgap(5);
		pane.setHgap(10);
		//----------------------------------------------------------Nodes
		Button button1 = new Button("Customer");
		Button button2 = new Button("Media");
		Button button3 = new Button("Rent");
		Text text = new Text("Media Rental System");

		button1.setScaleX(1.75);
		button1.setScaleY(1.75);
		button2.setScaleX(1.75);
		button2.setScaleY(1.75);
		button3.setScaleX(1.75);
		button3.setScaleY(1.75);
		text.setScaleX(2);
		text.setScaleY(2);


		pane.add(button1,40,40);
		pane.add(button2, 40, 50);
		pane.add(button3, 40, 60);
		pane.add(text, 60, 70);
		pane.add(imageview, 60, 40);

		//------------------------------------------first scene buttons
		button1.setOnAction(e -> 
		{
			primaryStage.setScene(CustomerScene);
		});

		button2.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);
		});

		button3.setOnAction(e -> 
		{
			primaryStage.setScene(RentScene);
		});


		//-----------------------------------------------}Customer scene 
		Button Addcust = new Button("Add new Customer");
		Button Deletecust = new Button("Delete Customer");
		Button Updatecust = new Button("Update Information about Customer");
		Button Searchcust = new Button("Search a Customer by id");

		VBox panee = new VBox();
		panee.setAlignment(Pos.CENTER);
		panee.setSpacing(30);
		panee.getChildren().addAll(Addcust,Deletecust,Updatecust,Searchcust,Returnn);
		CustomerScene = new Scene(panee,1200,900);

		Addcust.setOnAction(e -> 
		{
			primaryStage.setScene(AddcustScene);
		});

		Deletecust.setOnAction(e -> 
		{
			primaryStage.setScene(DeletecustScene);
		});

		Updatecust.setOnAction(e -> 
		{
			primaryStage.setScene(UpdatecustScene);
		});

		Searchcust.setOnAction(e -> 
		{
			primaryStage.setScene(SearchcustScene);
		});

		Return.setOnAction(e -> 
		{
			primaryStage.setScene(MainScene);
		});
		//---------------------------------------------------Add cust scene

		TextField custID = new TextField();
		TextField custName = new TextField();
		TextField custAddress = new TextField();
		TextField custMobile = new TextField();

		custName.setDisable(true);
		custAddress.setDisable(true);
		custMobile.setDisable(true);


		Label custIDLabel = new Label("Customer ID:");
		Label custNameLabel = new Label("Customer Name:");
		Label custAddressLabel = new Label("Customer Address:");
		Label custMobileLabel = new Label("Customer Mobile:");
		Button add = new Button("Add");
		Button back = new Button("Back");

		GridPane addcustpane = new GridPane();
		addcustpane.setVgap(5);
		addcustpane.setHgap(10);

		addcustpane.add(custID, 30, 20);
		addcustpane.add(custName, 30, 30);
		addcustpane.add(custAddress, 30, 40);
		addcustpane.add(custMobile, 30, 50);
		addcustpane.add(custIDLabel, 20, 20);
		addcustpane.add(custNameLabel, 20, 30);
		addcustpane.add(custAddressLabel, 20, 40);
		addcustpane.add(custMobileLabel, 20, 50);
		addcustpane.add(add, 20, 60);
		addcustpane.add(back, 30, 60);

		AddcustScene = new Scene(addcustpane,1200,900);

		add.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size();i++)
			{
				if(mediaRentalManager.customerList.get(i).getID().equals(custID.getText()))
				{
					//mediaRentalManager.addCustomer(null, null, null,null, null);

				}
				else
				{
					mediaRentalManager.addCustomer(custName.getText().trim(), custAddress.getText().trim(), "LIMITED"
							, custID.getText().trim(), custMobile.getText().trim());
				}
			}
		});

		back.setOnAction(e -> 
		{
			primaryStage.setScene(CustomerScene);
		});


		custID.setOnKeyTyped(e -> 
		{
			custName.setDisable(false);
		});

		custName.setOnKeyTyped(e -> 
		{
			custAddress.setDisable(false);
		});

		custAddress.setOnKeyTyped(e -> 
		{
			custMobile.setDisable(false);
		});


		//-------------------------------------------------------------------------Delete cust scene

		GridPane deletecustpane = new GridPane();
		deletecustpane.setVgap(5);
		deletecustpane.setHgap(10);

		Button find = new Button("find");
		Button delete = new Button("Delete");
		TextField custIDd = new TextField();
		TextField custNamee = new TextField();
		TextField custAddresss = new TextField();
		TextField custMobilee = new TextField();


		custNamee.setDisable(true);
		custAddresss.setDisable(true);
		custMobilee.setDisable(true);

		Label custIDLabell = new Label("Customer ID:");
		Label custNameLabell = new Label("Customer Name:");
		Label custAddressLabell = new Label("Customer Address:");
		Label custMobileLabell = new Label("Customer Mobile:");
		Button backk = new Button("Back");

		deletecustpane.add(custIDd, 30, 20);
		deletecustpane.add(custNamee, 30, 30);
		deletecustpane.add(custAddresss, 30, 40);
		deletecustpane.add(custMobilee, 30, 50);
		deletecustpane.add(custIDLabell, 20, 20);
		deletecustpane.add(custNameLabell, 20, 30);
		deletecustpane.add(custAddressLabell, 20, 40);
		deletecustpane.add(custMobileLabell, 20, 50);
		deletecustpane.add(delete, 40, 60);
		deletecustpane.add(backk, 30, 60);
		deletecustpane.add(find, 20, 60);


		delete.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size() ;i++)
				if(mediaRentalManager.customerList.get(i).getID().equals(custIDd.getText()))
				{
					mediaRentalManager.DeleteCustomer(custIDd.getText());
				}
		});

		backk.setOnAction(e -> 
		{
			primaryStage.setScene(CustomerScene);
		});

		find.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size() ;i++)
				if(mediaRentalManager.customerList.get(i).getID().equals(custID.getText()))
				{
					custNamee.setText(mediaRentalManager.customerList.get(i).getName());  
					custAddresss.setText(mediaRentalManager.customerList.get(i).getAddress());
					custMobilee.setText(mediaRentalManager.customerList.get(i).getMobileNumber());	
				}
				else
				{
					custNamee.setText("No Info");  
					custAddresss.setText("No Info");
					custMobilee.setText("No Info");
				}
		});

		custIDd.setOnKeyTyped(e -> 
		{
			custNamee.setDisable(false);
		});

		custNamee.setOnKeyTyped(e -> 
		{
			custAddresss.setDisable(false);
		});

		custAddresss.setOnKeyTyped(e -> 
		{
			custMobilee.setDisable(false);
		});


		DeletecustScene = new Scene(deletecustpane,1200,900);

		//------------------------------Update cust scene

		GridPane updatecustpane = new GridPane();
		updatecustpane.setVgap(5);
		updatecustpane.setHgap(10);

		Button findd = new Button("find");
		TextField custIDdd = new TextField();
		TextField custNameee = new TextField();
		TextField custAddressss = new TextField();
		TextField custMobileee = new TextField();

		Label custIDLabelll = new Label("Customer ID:");
		Label custNameLabelll = new Label("Customer Name:");
		Label custAddressLabelll = new Label("Customer Address:");
		Label custMobileLabelll = new Label("Customer Mobile:");
		Button backkk = new Button("Back");
		Button Update = new Button("Update");

		updatecustpane.add(custIDdd, 30, 20);
		updatecustpane.add(custNameee, 30, 30);
		updatecustpane.add(custAddressss, 30, 40);
		updatecustpane.add(custMobileee, 30, 50);
		updatecustpane.add(custIDLabelll, 20, 20);
		updatecustpane.add(custNameLabelll, 20, 30);
		updatecustpane.add(custAddressLabelll, 20, 40);
		updatecustpane.add(custMobileLabelll, 20, 50);
		updatecustpane.add(backkk, 30, 60);
		updatecustpane.add(Update, 40, 60);
		updatecustpane.add(findd, 20, 60);



		custNameee.setDisable(true);
		custAddressss.setDisable(true);
		custMobileee.setDisable(true);


		findd.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size() ;i++)
				if(mediaRentalManager.customerList.get(i).getID().equals(custID.getText()))
				{
					custNameee.setDisable(false);
					custAddressss.setDisable(false);
					custMobileee.setDisable(false);

					custNameee.setText(mediaRentalManager.customerList.get(i).getName());  
					custAddressss.setText(mediaRentalManager.customerList.get(i).getAddress());
					custMobileee.setText(mediaRentalManager.customerList.get(i).getMobileNumber());
				}
				else
				{
					custNameee.setText("No Info");  
					custAddressss.setText("No Info");
					custMobileee.setText("No Info");
				}
		});

		backkk.setOnAction(e -> 
		{
			primaryStage.setScene(CustomerScene);
		});

		Update.setOnAction(e ->
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size() ;i++)
				if(mediaRentalManager.customerList.get(i).getID().equals(custID.getText()))
				{
					mediaRentalManager.customerList.get(i).setName(custNameee.getText());
					mediaRentalManager.customerList.get(i).setAddress(custAddressss.getText());
					mediaRentalManager.customerList.get(i).setMobileNumber(custMobileee.getText());
				}
		});

		UpdatecustScene = new Scene(updatecustpane,1200,900);



		//-------------------search cust scene



		GridPane searchcustpane = new GridPane();
		searchcustpane.setVgap(5);
		searchcustpane.setHgap(10);

		Button search = new Button("find");
		TextField custIDddd = new TextField();
		TextField custNameeee = new TextField();
		TextField custAddresssss = new TextField();
		TextField custMobileeee = new TextField();




		Label custIDLabellll = new Label("Customer ID:");
		Label custNameLabellll = new Label("Customer Name:");
		Label custAddressLabellll = new Label("Customer Address:");
		Label custMobileLabellll = new Label("Customer Mobile:");
		Button backkkk = new Button("Back");


		searchcustpane.add(custIDddd, 30, 20);
		searchcustpane.add(custNameeee, 30, 30);
		searchcustpane.add(custAddresssss, 30, 40);
		searchcustpane.add(custMobileeee, 30, 50);
		searchcustpane.add(custIDLabellll, 20, 20);
		searchcustpane.add(custNameLabellll, 20, 30);
		searchcustpane.add(custAddressLabellll, 20, 40);
		searchcustpane.add(custMobileLabellll, 20, 50);
		searchcustpane.add(backkkk, 30, 60);
		searchcustpane.add(search, 20, 60);

		custNameeee.setDisable(true);
		custAddresssss.setDisable(true);
		custMobileeee.setDisable(true);

		search.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.customerList.size() ;i++)
				if(mediaRentalManager.customerList.get(i).getID().equals(custID.getText()))
				{
					custNameeee.setDisable(false);
					custAddresssss.setDisable(false);
					custMobileeee.setDisable(false);

					custNameeee.setText(mediaRentalManager.customerList.get(i).getName());  
					custAddresssss.setText(mediaRentalManager.customerList.get(i).getAddress());
					custMobileeee.setText(mediaRentalManager.customerList.get(i).getMobileNumber());
				}
				else
				{
					custNameeee.setText("No Info");  
					custAddresssss.setText("No Info");
					custMobileeee.setText("No Info");
				}
		});

		backkkk.setOnAction(e -> 
		{
			primaryStage.setScene(CustomerScene);
		});


		SearchcustScene = new Scene(searchcustpane,1200,900);


		//------------------------------------Media scene

		Button Addmedia = new Button("Add new Media");
		Button Deletemedia = new Button("Delete Media");
		Button Updatemedia = new Button("Update Information about Mediar");
		Button Searchmedia = new Button("Search a Media by code");
		Button printmedia = new Button("Print All Media information");

		VBox paneee = new VBox();
		paneee.setAlignment(Pos.CENTER);
		paneee.setSpacing(30);
		paneee.getChildren().addAll(Addmedia,Deletemedia,Updatemedia,Searchmedia,printmedia,Return);
		MediaScene = new Scene(paneee,1200,900);

		Addmedia.setOnAction(e -> 
		{
			primaryStage.setScene(AddmediaScene);
		});

		Deletemedia.setOnAction(e -> 
		{
			primaryStage.setScene(DeletemediaScene);
		});

		Updatemedia.setOnAction(e -> 
		{
			primaryStage.setScene(UpdatemediaScene);
		});

		Searchmedia.setOnAction(e -> 
		{
			primaryStage.setScene(SearchmediaScene);
		});


		Return.setOnAction(e -> 
		{
			primaryStage.setScene(MainScene);
		});

		//------------------add media scene

		TextField mediacode = new TextField();
		TextField mediacopies = new TextField();
		TextField mediatile = new TextField();
		TextField gameweight = new TextField();
		TextField movierating = new TextField();
		TextField AlbumArtist = new TextField();
		TextField Albumsongs = new TextField();

		Label mediacodeLabel = new Label("Media code:");
		Label mediacopiesLabel = new Label("Media Copies Available:");
		Label mediatileLabel = new Label("Media title:");
		Label gameweightLabel = new Label("Game weight:");
		Label movieratingLabel = new Label("Movie rating:");
		Label AlbumArtistLabel = new Label("Album Artist:");
		Label AlbumsongsLabel = new Label("Album songs:");
		Label MovieRatingNoteLabel = new Label("Note : (DR,HR,AC)");

		Button Mediadd = new Button("Add");
		Button Mediaback = new Button("Back");

		ComboBox<String> mediacombo = new ComboBox<>();
		mediacombo.getItems().addAll("Game","Movie","Album");
		mediacombo.setPromptText("Choose type of media");


		GridPane addmediapane = new GridPane();
		addmediapane.setVgap(5);
		addmediapane.setHgap(10);

		mediacopies.setDisable(true);
		gameweight.setDisable(true);
		mediatile.setDisable(true);
		gameweight.setDisable(true);
		movierating.setDisable(true);
		AlbumArtist.setDisable(true);
		Albumsongs.setDisable(true);

		addmediapane.add(mediacode, 30, 20);
		addmediapane.add(mediacopies, 30, 30);
		addmediapane.add(mediatile, 30, 40);

		addmediapane.add(mediacombo, 40, 40);

		addmediapane.add(gameweight, 30, 50);
		addmediapane.add(movierating, 30, 60);
		addmediapane.add(AlbumArtist, 30, 70);
		addmediapane.add(Albumsongs, 30, 80);

		addmediapane.add(mediacodeLabel, 20, 20);
		addmediapane.add(mediacopiesLabel, 20, 30);
		addmediapane.add(mediatileLabel, 20, 40);
		addmediapane.add(gameweightLabel, 20, 50);
		addmediapane.add(movieratingLabel, 20, 60);
		addmediapane.add(AlbumArtistLabel, 20, 70);
		addmediapane.add(AlbumsongsLabel, 20, 80);

		addmediapane.add(Mediadd, 20, 100);
		addmediapane.add(Mediaback, 30, 100);

		addmediapane.add(MovieRatingNoteLabel, 45, 60);

		AddmediaScene = new Scene(addmediapane,1200,900);

		//disabling 
		mediacode.setOnKeyTyped(e -> 
		{
			mediacopies.setDisable(false);
		});

		mediacopies.setOnKeyTyped(e -> 
		{
			mediatile.setDisable(false);
		});

		mediatile.setOnKeyTyped(e -> 
		{
			gameweight.setDisable(false);
		});

		gameweight.setOnKeyTyped(e -> 
		{
			movierating.setDisable(false);
		});

		movierating.setOnKeyTyped(e -> 
		{
			AlbumArtist.setDisable(false);
		});

		AlbumArtist.setOnKeyTyped(e -> 
		{
			Albumsongs.setDisable(false);
		});

		//actions
		Mediadd.setOnAction(e -> 
		{
			for(int i =0;i< mediaRentalManager.mediaList.size();i++)

				if(mediaRentalManager.mediaList.get(i).getCode().equals(mediacode.getText().trim()))
				{
					//don't add
				}
				else
				{
					if(mediacombo.getValue().equals("Game"))
					{
						if(mediatile.getText().length() > 0 && mediacopies.getText().length() > 0
								&& gameweight.getText().length() > 0 && mediacode.getText().length() > 0)
						{
							mediaRentalManager.addGame(mediatile.getText().trim(), Integer.parseInt(mediacopies.getText().trim())
									, Double.parseDouble(gameweight.getText().trim()), mediacode.getText().trim());
						}
					}
					else if(mediacombo.getValue().equals("Movie"))
					{
						if(mediatile.getText().length() > 0 && mediacopies.getText().length() > 0
								&& movierating.getText().length() > 0 && mediacode.getText().length() > 0)
						{
							mediaRentalManager.addMovie(mediatile.getText().trim(), Integer.parseInt(mediacopies.getText().trim())
									, movierating.getText().trim(), mediacode.getText().trim());
						}
					}

					else if(mediacombo.getValue().equals("Album"))			
					{
						if(mediatile.getText().length() > 0 && mediacopies.getText().length() > 0
								&& AlbumArtist.getText().length() > 0 && mediacode.getText().length() > 0
								&& Albumsongs.getText().length() > 0)
						{
							mediaRentalManager.addAlbum(mediatile.getText().trim(), Integer.parseInt(mediacopies.getText().trim())
									, AlbumArtist.getText().trim(), Albumsongs.getText(), mediacode.getText().trim());
						}
					}
				}
		});

		Mediaback.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);
		});

		//---------------------------delete media scene

		TextField mediacodee = new TextField();
		TextField mediacopiess = new TextField();
		TextField mediatilee = new TextField();
		TextField gameweightt = new TextField();
		TextField movieratingg = new TextField();
		TextField AlbumArtistt = new TextField();
		TextField Albumsongss = new TextField();

		Label mediacodeLabell = new Label("Media code:");
		Label mediacopiesLabell = new Label("Media Copies Available:");
		Label mediatileLabell = new Label("Media title:");
		Label gameweightLabell = new Label("Game weight:");
		Label movieratingLabell = new Label("Movie rating:");
		Label AlbumArtistLabell = new Label("Album Artist:");
		Label AlbumsongsLabell = new Label("Album songs:");
		Label MovieRatingNoteLabell = new Label("Note : (DR,HR,AC)");

		Button MediaDelete = new Button("Delete");
		Button Mediabackk = new Button("Back");
		Button deletemediafind = new Button("Find");

		ComboBox<String> mediacomboo = new ComboBox<>();
		mediacomboo.getItems().addAll("Game","Movie","Album");
		mediacomboo.setPromptText("Choose type of media");


		GridPane deletemediapane = new GridPane();
		deletemediapane.setVgap(5);
		deletemediapane.setHgap(10);

		deletemediapane.add(mediacodee, 30, 20);
		deletemediapane.add(mediacopiess, 30, 30);
		deletemediapane.add(mediatilee, 30, 40);

		deletemediapane.add(mediacomboo, 40, 40);

		deletemediapane.add(gameweightt, 30, 50);
		deletemediapane.add(movieratingg, 30, 60);
		deletemediapane.add(AlbumArtistt, 30, 70);
		deletemediapane.add(Albumsongss, 30, 80);

		deletemediapane.add(mediacodeLabell, 20, 20);
		deletemediapane.add(mediacopiesLabell, 20, 30);
		deletemediapane.add(mediatileLabell, 20, 40);
		deletemediapane.add(gameweightLabell, 20, 50);
		deletemediapane.add(movieratingLabell, 20, 60);
		deletemediapane.add(AlbumArtistLabell, 20, 70);
		deletemediapane.add(AlbumsongsLabell, 20, 80);

		deletemediapane.add(MediaDelete, 20, 100);
		deletemediapane.add(Mediabackk, 30, 100);
		deletemediapane.add(deletemediafind, 40, 100);


		deletemediapane.add(MovieRatingNoteLabell, 45, 60);

		DeletemediaScene = new Scene(deletemediapane,1200,900);


		MediaDelete.setOnAction(e -> 
		{
			mediaRentalManager.DeleteMedia(mediacodee.getText());
		});

		Mediabackk.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);
		});

		deletemediafind.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.mediaList.size() ;i++)
				if(mediaRentalManager.mediaList.get(i).getCode().equals(mediacodee.getText()))
				{
					mediatilee.setText(mediaRentalManager.mediaList.get(i).getTitle());  
					mediacopiess.setText(Integer.toString(mediaRentalManager.mediaList.get(i).getCopiesAvailable()));
				}
				else
				{
					mediatilee.setText("no info");  
					mediacopiess.setText("no info");
				}
		});



		//-----------------------update  media scene

		TextField mediacodeee = new TextField();
		TextField mediacopiesss = new TextField();
		TextField mediatileee = new TextField();
		TextField gameweighttt = new TextField();
		TextField movieratinggg = new TextField();
		TextField AlbumArtisttt = new TextField();
		TextField Albumsongsss = new TextField();

		Label mediacodeLabelll = new Label("Media code:");
		Label mediacopiesLabelll = new Label("Media Copies Available:");
		Label mediatileLabelll = new Label("Media title:");
		Label gameweightLabelll = new Label("Game weight:");
		Label movieratingLabelll = new Label("Movie rating:");
		Label AlbumArtistLabelll = new Label("Album Artist:");
		Label AlbumsongsLabelll = new Label("Album songs:");
		Label MovieRatingNoteLabelll = new Label("Note : (DR,HR,AC)");

		Button MediaUpdate = new Button("Update");
		Button Mediabackkk = new Button("Back");
		Button deletemediafindd = new Button("Find");

		ComboBox<String> mediacombooo = new ComboBox<>();
		mediacombooo.getItems().addAll("Game","Movie","Album");
		mediacombooo.setPromptText("Choose type of media");


		GridPane updateemediapane = new GridPane();
		updateemediapane.setVgap(5);
		updateemediapane.setHgap(10);

		updateemediapane.add(mediacodeee, 30, 20);
		updateemediapane.add(mediacopiesss, 30, 30);
		updateemediapane.add(mediatileee, 30, 40);

		updateemediapane.add(mediacombooo, 40, 40);

		updateemediapane.add(gameweighttt, 30, 50);
		updateemediapane.add(movieratinggg, 30, 60);
		updateemediapane.add(AlbumArtisttt, 30, 70);
		updateemediapane.add(Albumsongsss, 30, 80);

		updateemediapane.add(mediacodeLabelll, 20, 20);
		updateemediapane.add(mediacopiesLabelll, 20, 30);
		updateemediapane.add(mediatileLabelll, 20, 40);
		updateemediapane.add(gameweightLabelll, 20, 50);
		updateemediapane.add(movieratingLabelll, 20, 60);
		updateemediapane.add(AlbumArtistLabelll, 20, 70);
		updateemediapane.add(AlbumsongsLabelll, 20, 80);

		updateemediapane.add(MediaUpdate, 20, 100);
		updateemediapane.add(Mediabackkk, 30, 100);
		updateemediapane.add(deletemediafindd, 40, 100);

		updateemediapane.add(MovieRatingNoteLabelll, 45, 60);

		UpdatemediaScene = new Scene(updateemediapane,1200,900);


		Mediabackkk.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);
		});

		deletemediafind.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.mediaList.size() ;i++)
				if(mediaRentalManager.mediaList.get(i).getCode().equals(mediacodee.getText()))
				{
					mediatilee.setText(mediaRentalManager.mediaList.get(i).getTitle());  
					mediacopiess.setText(Integer.toString(mediaRentalManager.mediaList.get(i).getCopiesAvailable()));
				}
				else
				{
					mediatilee.setText("no info");  
					mediacopiess.setText("no info");
				}
		});

		MediaUpdate.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.mediaList.size() ;i++)
				if(mediaRentalManager.mediaList.get(i).getCode().equals(mediacodee.getText()))
				{
					mediaRentalManager.mediaList.get(i).setCopiesAvailable(Integer.parseInt(mediacopiesss.getText()));
					mediaRentalManager.mediaList.get(i).setTitle(mediatileee.getText());
				}
		});

		//----------------------------search media by code scene

		TextField mediacodeeee = new TextField();
		TextField mediacopiessss = new TextField();
		TextField mediatileeee = new TextField();
		TextField gameweightttt = new TextField();
		TextField movieratingggg = new TextField();
		TextField AlbumArtistttt = new TextField();
		TextField Albumsongssss = new TextField();

		Label mediacodeLabellll = new Label("Media code:");
		Label mediacopiesLabellll = new Label("Media Copies Available:");
		Label mediatileLabellll = new Label("Media title:");
		Label gameweightLabellll = new Label("Game weight:");
		Label movieratingLabellll = new Label("Movie rating:");
		Label AlbumArtistLabellll = new Label("Album Artist:");
		Label AlbumsongsLabellll = new Label("Album songs:");
		Label MovieRatingNoteLabellll = new Label("Note : (DR,HR,AC)");

		Button Mediabackkkk = new Button("Back");
		Button searchmediafind = new Button("Find");

		ComboBox<String> mediacomboooo = new ComboBox<>();
		mediacomboooo.getItems().addAll("Game","Movie","Album");
		mediacomboooo.setPromptText("Choose type of media");


		GridPane searchmediapane = new GridPane();
		searchmediapane.setVgap(5);
		searchmediapane.setHgap(10);

		searchmediapane.add(mediacodeeee, 30, 20);
		searchmediapane.add(mediacopiessss, 30, 30);
		searchmediapane.add(mediatileeee, 30, 40);

		searchmediapane.add(mediacomboooo, 40, 40);

		searchmediapane.add(gameweightttt, 30, 50);
		searchmediapane.add(movieratingggg, 30, 60);
		searchmediapane.add(AlbumArtistttt, 30, 70);
		searchmediapane.add(Albumsongssss, 30, 80);

		searchmediapane.add(mediacodeLabellll, 20, 20);
		searchmediapane.add(mediacopiesLabellll, 20, 30);
		searchmediapane.add(mediatileLabellll, 20, 40);
		searchmediapane.add(gameweightLabellll, 20, 50);
		searchmediapane.add(movieratingLabellll, 20, 60);
		searchmediapane.add(AlbumArtistLabellll, 20, 70);
		searchmediapane.add(AlbumsongsLabellll, 20, 80);


		searchmediapane.add(Mediabackkkk, 30, 100);
		searchmediapane.add(searchmediafind, 40, 100);

		updateemediapane.add(MovieRatingNoteLabellll, 45, 60);

		SearchmediaScene = new Scene(searchmediapane,1200,900);


		Mediabackkkk.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);
		});

		searchmediafind.setOnAction(e -> 
		{
			for(int i = 0 ; i<mediaRentalManager.mediaList.size() ;i++)
				if(mediaRentalManager.mediaList.get(i).getCode().equals(mediacodeeee.getText()))
				{
					mediatileeee.setText(mediaRentalManager.mediaList.get(i).getTitle());  
					mediacopiessss.setText(Integer.toString(mediaRentalManager.mediaList.get(i).getCopiesAvailable()));
				}
				else
				{
					mediatileeee.setText("no info");  
					mediacopiessss.setText("no info");
				}
		});


		//--------------------------Print media info scene
		BorderPane border = new BorderPane();
		TextArea PrintedMedia = new TextArea();
		Button exit = new Button("exit");

		border.setTop(PrintedMedia);
		border.setCenter(exit);
		border.setAlignment(exit, Pos.CENTER);

		printmediascene = new Scene(border,1200,900);

		printmedia.setOnAction(e -> 
		{
			primaryStage.setScene(printmediascene);
			for(int i = 0;i<mediaRentalManager.mediaList.size();i++)
			{
				PrintedMedia.setText(mediaRentalManager.mediaList.get(i).toString()+"\n");
			}
		});

		exit.setOnAction(e -> 
		{
			primaryStage.setScene(MediaScene);

		});


		//----------------------------------- Rent scene
		GridPane gp8 = new GridPane();
		gp8.setVgap(10);
		gp8.setHgap(10);

		TextArea Custtx = new TextArea();
		TextArea Mediatx = new TextArea();

		TextField Custtxfield = new TextField();
		TextField Mediatxfield = new TextField();
		TextField DateRentedLabel = new TextField();

		Label CusttLabel = new Label("Customer ID:");
		Label MediaaLabel = new Label("Media Code:");
		Label DateRented = new Label("Rented Date:");

		Button AddtoCart = new Button("Add To cart");
		Button ProcessCart = new Button("Process Cart");
		Button exittt = new Button("Back");

		HBox hbox = new HBox();
		hbox.getChildren().addAll(AddtoCart,ProcessCart,exittt);
		hbox.setSpacing(10);

		gp8.add(CusttLabel, 20, 10);
		gp8.add(Custtxfield, 25, 10);
		gp8.add(Custtx, 25, 11);
		gp8.add(MediaaLabel, 20, 15);
		gp8.add(Mediatxfield, 25, 15);
		gp8.add(Mediatx, 25, 16);
		gp8.add(DateRented, 20, 18);
		gp8.add(DateRentedLabel, 25, 18);
		gp8.add(hbox, 25, 20);

		RentScene = new Scene(gp8,1900,900);


		CusttLabel.setOnKeyReleased(e -> {
			
			for(int i = 0 ; i< mediaRentalManager.customerList.size() ; i++)
			{
				Custtxfield.setText(mediaRentalManager.customerList.get(i).getInterestedMediaList().toString());
			}
		});
		
		AddtoCart.setOnAction(e -> {
			for(int i =0;i<mediaRentalManager.customerList.size();i++ )
			{
				if(Custtxfield.getText().equals(mediaRentalManager.customerList.get(i).getID()))
				{
					name = mediaRentalManager.customerList.get(i).getName();
				}
			}
			for(int i =0;i<mediaRentalManager.mediaList.size();i++)
			{
				if(Mediatxfield.getText().equals(mediaRentalManager.mediaList.get(i).getCode()))
				{
					medianame = mediaRentalManager.mediaList.get(i).getTitle();
				}
			}
			mediaRentalManager.addToCart(name, medianame);
			DateRentedLabel.setText(new Date().toString());
		});

		ProcessCart.setOnAction(e -> {
			mediaRentalManager.processRequests();
			DateRentedLabel.setText(new Date().toString());
		});

		exittt.setOnAction(e -> {
			primaryStage.setScene(MainScene);
		});

		
		//---------------return button action

		Return.setOnAction(e -> 
		{
			primaryStage.setScene(MainScene);
		});

		Returnn.setOnAction(e -> 
		{
			primaryStage.setScene(MainScene);
		});

		Returnnn.setOnAction(e -> 
		{
			primaryStage.setScene(MainScene);
		});

		//---------------------------------------------Stage and scene
		primaryStage.setMaximized(true);
		MainScene = new Scene(pane,500,500);
		primaryStage.setTitle("Rental Media System");
		primaryStage.setScene(MainScene);
		primaryStage.show();
	}
}
