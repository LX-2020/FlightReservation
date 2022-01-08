import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * SeatsList class has an arraylist for holding seats object
 * create new seats arraylist or read from existed file
 * @author lexinma
 *
 */
public class SeatsList 
{
	private ArrayList<Seat> seatslist;	
	
	/**
	 * constructor for new arraylist
	 */
	SeatsList()
	{		
		this.seatslist = new ArrayList<Seat>();
		
		//create first class seats
		for (int i = 1; i < 3; i++)
		{
			for (char p = 'A'; p < 'E'; p++)
			{
				Seat s = new Seat(" ", "first", i, p, " ");
				seatslist.add(s);								
			}
		}
		//create economy class seats
		for (int i = 10; i < 30; i++)
		{
			for (char p = 'A'; p < 'G'; p++)
			{
				Seat s = new Seat(" ", "economy", i, p, " ");
				seatslist.add(s);				
			}
		}
		
	}
	
	/**
	 * constructor for existed seat arraylist
	 * @param filepath
	 */
	SeatsList(String filepath)
	{
		this.seatslist = new ArrayList<Seat>();
		File file = new File(filepath);
		try 
		{			
			Scanner sc = new Scanner(file);
			while(sc.hasNextLine())
			{
				String seatstr = sc.nextLine();
				String[] tokens = seatstr.split(",");				
				String pclass = tokens[0];
				int psn = Integer.parseInt(tokens[1]);
				char psp = tokens[2].charAt(0);							
				String pn = tokens[3];				
				String pgn = tokens[4];
				
				Seat s = new Seat(pn,pclass,psn,psp,pgn);
				seatslist.add(s);
				
			}
			sc.close();
		} catch (FileNotFoundException e) {				
			e.printStackTrace();}
	}
	
	/**
	 * @return seatslist
	 */
	public ArrayList<Seat> getlist()
	{
		return this.seatslist;
	}
	
}