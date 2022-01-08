import java.io.Serializable;

/**
 * Seat class
 * get, set passenger name, group name, class, seat number and seat preference
 * @author lexinma
 *
 */
public class Seat implements Serializable
{
	private String pname;
	private String fclass;
	private int seatNum;
	private char seatPre;
	private String groupname;
	
	/**
	 * constructor
	 * @param pname
	 * @param fclass
	 * @param seatNum
	 * @param seatPre
	 * @param groupname
	 */
	Seat(String pname, String fclass, int seatNum, char seatPre, String groupname)
	{
		this.pname = pname;
		this.fclass = fclass;
		this.seatNum = seatNum;
		this.seatPre = seatPre;
		this.groupname = groupname;
	}
	
	/**
	 * set passenger name
	 * @param name
	 */
	public void setpname(String name)
	{
		this.pname = name;
	}
	
	/**
	 * set group name
	 * @param gname
	 */
	public void setgroupname(String gname)
	{
		this.groupname = gname;
	}
	
	/**
	 * @return passenger name
	 */
	public String getpname()
	{
		return this.pname;
	}

	/**
	 * @return class of service
	 */
	public String getfclass()
	{
		return this.fclass;
	}
	
	/**
	 * @return seat number
	 */
	public int getseatNum()
	{
		return this.seatNum;
	}
	
	/**	 
	 * @return seat preference
	 */
	public char getseatPre()
	{
		return this.seatPre;
	}
	
	/**
	 * @return group name
	 */
	public String getgroupname()
	{
		return this.groupname;
	}
	
	public String toString()
	{
		return  this.fclass + " " + this.seatNum + " " + this.seatPre + " " + this.pname + " " + this.groupname;
	}
}
