import java.sql.*;
import java.util.ArrayList;

public class AccessCourseData{
	static Connection connection;
	static Statement statement;
	
	public AccessCourseData(){
		
	}
	
	/**
	 * executeQuery
	 * 
	 * This method is for opening and executing the query passed along to it. 
	 * This method does not close the Connection that it opens, it must be closed 
	 * via the methods that called it. This is because we return the ResultSet. 
	 *
	 * @param 	String 		query
	 * @return 	ResultSet 	rs
	 **/
	private ResultSet executeQuery(String query){
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = "jdbc:odbc:Courses";
            
			connection = DriverManager.getConnection(database,"",""); 
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			// We return the ResultSet so the individual methods deal with the information.
			// Don't forget! We need to close the connection once we're done in the method we return to.
			return rs;
			
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error!");
        }
		
		return null;
	}
	
	/**
	 * getMinorRequirements
	 *
	 * This method accepts a minor as a parameter, and returns an ArrayList 
	 * of String of all class requirements. 
	 *
	 * 'ClassID'
	 *
	 * @param 	String 				minor
	 * @return 	ArrayList<String> 	minorRequirements
	 **/
	public ArrayList<String> getMinorRequirements(String minor){
		String minReqQuery = "SELECT ClassID FROM MinorRequirements WHERE MinorID='" + minor + "'";
		ResultSet rs = executeQuery(minReqQuery);
		
		ArrayList<String> minorRequirements = new ArrayList<String>();
		
		try{
			while(rs.next()){
				// Still need to split via CSV
				String ClassID = rs.getString(1);
				minorRequirements.add(ClassID);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return minorRequirements;
	}
	
	/**
	 * getClassRequirements
	 *
	 * This method accepts a classID as a parameter and returns an ArrayList 
	 * of Strings of all classes that are a pre-requisite. 
	 *
	 * 'Requirements'
	 *
	 * @param 	String 				classID
	 * @return 	ArrayList<String> 	classReq
	 **/
	public ArrayList<String> getClassRequirements(String classID){
		String classReqQuery = "SELECT Requirements FROM Classes WHERE ClassID='" + classID + "'";
		ResultSet rs = executeQuery(classReqQuery);
		
		ArrayList<String> classReq = new ArrayList<String>();
		
		try{
			while(rs.next()){
				// Still need to split via CSV
				String reqTemp = rs.getString(1);
				classReq.add(reqTemp);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return classReq;
	}
	
	public Integer getClassTotalCredits(ArrayList<String> classAL){
		int totalVal = 0;
		for(String myClass : classAL){
			int tempVal = getClassCredit(myClass);
			totalVal += tempVal;
		}
		
		return totalVal;
	}
	
	public Integer getClassCredit(String myClass){
		String classCreditQuery = "SELECT CreditValue from Classes where ClassID='" + myClass + "'";
		ResultSet rs = executeQuery(classCreditQuery);
		
		int tempCred = 0;
		
		try{
			tempCred = rs.getInt(1);
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}

		return tempCred;
	}
	
	/**
	 * getMajorRequirements
	 *
	 * This method accepts a major as a parameter and returns an ArrayList of
	 * ArrayLists of said majors requirements with each "sub" ArrayList holding 
	 * a ClassID and it's respective Semester.
	 *
	 * 'ClassID', 'Semester'
	 *
	 * @param 	String 					major
	 * @return 	ArrayList<ArrayList> 	majorRequirements
	 **/
	public ArrayList<ArrayList> getMajorRequirements(String major){
		String majReqQuery = "SELECT ClassID, Semester FROM MajorRequirements WHERE MajorID='" + major + "'";
		ResultSet rs = executeQuery(majReqQuery);
		
		ArrayList<ArrayList> majorRequirements = new ArrayList<ArrayList>();
		
		try{
			while(rs.next()){
				ArrayList<String> tempAL = new ArrayList<String>();
				String ClassID = rs.getString(1);
				String Semester = rs.getString(2);
				
				tempAL.add(ClassID);
				tempAL.add(Semester);
				
				majorRequirements.add(tempAL);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}

		return majorRequirements;
	}
	
	/**
	 * getMajors
	 *
	 * This method returns an ArrayList of ArrayLists of all majors with
	 * each "sub" ArrayList holding a MajorID, and it's respective MajorName 
	 * and CreditsNeeded.
	 *
	 * 'MajorID', 'MajorName', 'CreditsNeeded'
	 *
	 * @return 	ArrayList<ArrayList> 	majors
	 **/
	public ArrayList<ArrayList> getMajors(){
		String majorQuery = "SELECT * FROM Majors";
		ResultSet rs = executeQuery(majorQuery);
		
		ArrayList<ArrayList> majors = new ArrayList<ArrayList>();
		
		try{
			while(rs.next()){
				ArrayList<String> tempAL = new ArrayList<String>();
				String MajorID = rs.getString(1);
				String MajorName = rs.getString(2);
				String CreditsNeeded = rs.getInt(3) + "";
				
				tempAL.add(MajorID);
				tempAL.add(MajorName);
				tempAL.add(CreditsNeeded);
				
				majors.add(tempAL);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return majors;
	}
	
	/**
	 * getMinors
	 *
	 * This method returns an ArrayList of ArrayLists of all minors with
	 * each "sub" ArrayList holding a MinorID, and it's respective MinorName 
	 * and CreditsNeeded.
	 *
	 * 'MinorID', 'MinorName', 'CreditsNeeded'
	 *
	 * @return 	ArrayList<ArrayList> 	minors
	 **/
	public ArrayList<ArrayList> getMinors(){
		String minorQuery = "SELECT * FROM Minors";
		ResultSet rs = executeQuery(minorQuery);
		
		ArrayList<ArrayList> minors = new ArrayList<ArrayList>();
		
		try{
			while(rs.next()){
				ArrayList<String> tempAL = new ArrayList<String>();
				String MinorID = rs.getString(1);
				String MinorName = rs.getString(2);
				String CreditsNeeded = rs.getInt(3) + "";
				
				tempAL.add(MinorID);
				tempAL.add(MinorName);
				tempAL.add(CreditsNeeded);
				
				minors.add(tempAL);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return minors;
	}
	
	/**
	 * getClasses
	 *
	 * This method returns an ArrayList of ArrayLists of all classes with
	 * each "sub" ArrayList holding a ClassID, and it's respective ClassName,
	 * CreditsValue, and Requirements.
	 *
	 * 'ClassID', 'ClassName', 'CreditValue', 'Requirements'
	 *
	 * @return 	ArrayList<ArrayList> 	classes
	 **/
	public ArrayList<ArrayList> getClasses(){
		String classesQuery = "SELECT * FROM Classes";
		ResultSet rs = executeQuery(classesQuery);
		
		ArrayList<ArrayList> classes = new ArrayList<ArrayList>();
		
		try{
			while(rs.next()){
				ArrayList<String> tempAL = new ArrayList<String>();
				String ClassID = rs.getString(1);
				String ClassName = rs.getString(2);
				String CreditValue = rs.getInt(3) + "";
				String Requirements = rs.getString(4);
				
				tempAL.add(ClassID);
				tempAL.add(ClassName);
				tempAL.add(CreditValue);
				tempAL.add(Requirements);
				
				classes.add(tempAL);
			}
			
			connection.close();
		}catch(SQLException sqle){
			sqle.printStackTrace();
			System.out.println("Error!");
		}
		
		return classes;
	}
}