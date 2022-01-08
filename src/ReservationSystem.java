import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * main class
 * Display menu, get input from users, and implement reservation functions.
 * @author lexinma
 *
 */
public class ReservationSystem 
{	
	public static void main (String[] args)
	{				
		//command-line arguments for particular flight
		String filepath = args[0] + ".txt";
				
		ArrayList<Seat> seats = null;
		
		File file  = new File(filepath);	
		//get required flight reservation status
		if (!file.exists())
		{
			SeatsList seatslist = new SeatsList();
			seats = seatslist.getlist();
			System.out.println("new file created");			
		}
		else
		{		
			SeatsList seatslist = new SeatsList(filepath);
			seats = seatslist.getlist();			
			System.out.println("file exists");	
		}
		
		Scanner input = new Scanner(System.in);
		
		char select; 		
		boolean repeat = true;	
		
		
		//display menu, implements reservation functions
		do
		{
		//user interface
		System.out.println("*----------------------------------------------------------*\n");
		System.out.println(" Add [P]assenger, Add [G]roup, [C]ancel Reservations ");
		System.out.println("------------------------------------------------------------\n");
		System.out.println("Print Seating [A]vailability Chart, Print [M]anifest, [Q]uit");
		System.out.println("*-----------------------------------------------------------*");
		
		select = Character.toUpperCase(input.next().charAt(0));
		
		switch(select)
		{
		case 'P': //reserves seat for an individual passenger
			System.out.println("Enter the passenger's name(Firstn Lastn):");
			input.nextLine();
			String pname = input.nextLine().toUpperCase();
			
			System.out.println("Enter the service class(First/Economy):");
			String sclass = input.next().toLowerCase();
			
			System.out.println("Enter the seat preference([W]indow/[A]ile/[M]iddle):");
			char sp = Character.toUpperCase(input.next().charAt(0));
			
			//check seats list
			for (Seat s : seats)
			{
				//find matched class and empty seats
				if (sclass.equals("first") && s.getfclass().equals(sclass) && s.getpname().equals(" "))
				{	
					//find matched seat preference
					if (sp == 'W' && (s.getseatPre() == 'A' || s.getseatPre() == 'D'))
					{
						s.setpname(pname);
						System.out.println("Reserved the seat:");
						System.out.println(s.toString());
						break;
					}
					else if (sp == 'A' && (s.getseatPre() == 'B' || s.getseatPre() == 'C'))
					{
						s.setpname(pname);
						System.out.println("Reserved the seat:");
						System.out.println(s.toString());
						break;
					}
				}
				//find matched class and empty seats
				else if (sclass.equals("economy") && s.getfclass().equals(sclass) && s.getpname().equals(" "))
				{
					if (sp == 'W' && (s.getseatPre() == 'A' || s.getseatPre() == 'F'))
					{
						s.setpname(pname);
						System.out.println("Reserved the seat:");
						System.out.println(s.toString());
						break;
					}
					else if (sp == 'A' && (s.getseatPre() == 'C' || s.getseatPre() == 'D'))
					{
						s.setpname(pname);
						System.out.println("Reserved the seat:");
						System.out.println(s.toString());
						break;
					}
					else if (sp == 'M' && (s.getseatPre() == 'M' || s.getseatPre() == 'M'))
					{
						s.setpname(pname);
						System.out.println("Reserved the seat:");
						System.out.println(s.toString());
						break;
					}
					
				}
				
			}		
		break;
		
		case 'G': //reserves seat for a group
			
			System.out.println("Enter the group's name:");
			input.nextLine();
			String gname = input.nextLine();
			
			System.out.println("Enter the passenger's names(Firstn Lastn):");
			String namestring = input.nextLine();
			String[] names = namestring.split(",");
			
			System.out.println("Enter the service class(First/Economy):");
			String gclass = input.nextLine().toLowerCase();
			
			int startpos = 0;
			boolean has_consecutive_seats = false;
			
			//check seats list
			for (int k = 0; k < seats.size(); k++)
			{	
				//skip seats in unmatched class
				if (!seats.get(k).getfclass().equals(gclass))
						continue;
					
				startpos = k;
									
				//check whether there are enough consecutive empty seats for the group
				boolean has_consecutive_seats_from_k = true;
				for (int i = 0; i < names.length; i++)
				{					
					if ( ((k + i) == seats.size()) || (!seats.get(k + i).getpname().equals(" "))) 
					{
						System.out.println("not available");
						has_consecutive_seats_from_k = false;
						break;
					}						
				}
				//found consecutive seats	
				if(has_consecutive_seats_from_k) 
				{
					has_consecutive_seats = true;
					break;
				}								
				
			}
			
			//reserves the seats
			for (int j = 0 ; j < names.length; j++)
			{
				seats.get(startpos + j).setpname(names[j]);
				seats.get(startpos + j).setgroupname(gname);
				System.out.println(seats.get(startpos + j).toString());	
											
			}
			
			System.out.println("");
		break;
		
		case 'C': //cancel reservation
			System.out.println("Cancellation for [I]ndividual or [G]roup?");
			char cs = Character.toUpperCase(input.next().charAt(0));
			if (cs == 'I')
			{
				System.out.println("Enter the passenger's name(Firstn Lastn):");
				input.nextLine();
				String cpn = input.nextLine();
				
				for (Seat s : seats)
				{							
					if (s.getpname().equalsIgnoreCase(cpn) && s.getgroupname().equals(" "))						
					{
						s.setpname(" ");
						System.out.println("Reservation canceled for " + cpn);
					}
				}
			}
			else if (cs == 'G')
			{
				System.out.println("Enter the group's name:");
				input.nextLine();
				String cgn = input.nextLine();
				
				for (Seat s : seats)
				{				
					if (s.getgroupname().equalsIgnoreCase(cgn))						
					{
						s.setpname(" ");
						s.setgroupname(" ");
					}					
				}
				System.out.println("Reservation canceled for group " + cgn);
			}
		break;
		
		case 'A': //list available seats in required class
			System.out.println("Enter the service class(First/Economy):");
			input.nextLine();
			String aclass = input.nextLine();
			
			for (Seat s : seats)
			{								
				if (s.getfclass().equals(aclass) && s.getpname().equals(" "))
				{
					System.out.println(s.toString());
				}
			}
		break;
		
		case 'M': //list reserved seats in required class
			System.out.println("Enter the service class(First/Economy):");
			input.nextLine();
			String mclass = input.nextLine();
			
			for (Seat s : seats)
			{			
				if (s.getfclass().equals(mclass) && !(s.getpname().equals(" ") ))
				{
					System.out.println(s.toString());
					System.out.println(s.getpname());
				}
			}			
		break;
		
		case 'Q': //quit the reservation system and update the flight status
			System.out.println("Good Bye");
			
			File outputfile = new File(filepath);	
			try 
			{
				FileWriter writer = new FileWriter(outputfile);
				for (Seat s : seats)
				{
					for(int i = 0; i < s.toString().length(); i++)
					{
						writer.write(s.toString().charAt(i));
					}
					System.getProperty("line.separator");
					writer.write(System.getProperty("line.separator"));
				}			
				writer.flush();
				writer.close();
			} catch (IOException e) 
			{e.printStackTrace();}			
			repeat = false;	
			break;					
					
		}
		
	}while(repeat);	//do-while loop ends
		
	input.close();
} //main
}
