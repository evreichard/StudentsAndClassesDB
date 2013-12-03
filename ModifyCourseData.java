import java.sql.*;

public class ModifyCourseData{
	static Connection connection;
	static PreparedStatement prepStatement;
	
	/**
	 * Default Constructor
	 **/
	public ModifyCourseData(){
		
	}
	
	public boolean updateQuery(String query){
		try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String database = "jdbc:odbc:Courses";
            
			connection = DriverManager.getConnection(database,"",""); 
			prepStatement = connection.prepareStatement(query);
			prepStatement.executeUpdate();
			connection.close();
			
			return true;
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("Error!");
			return false;
        }
		
	}
	
	// This assumes that requirements have been verified
	public void updateClassesTable(String oldClassID, String newClassID, String className, String creditValue, String requirements){
		String updateClassesQuery = "UPDATE Classes SET ClassID='" + newClassID + "', ClassName='" + className + "', CreditValue='" + creditValue + "', Requirements='" + requirements + "' WHERE classID='" + oldClassID + "'";
		
		
		if(updateQuery(updateClassesQuery)){
			// Woo successful
			System.out.println("Success!");
		}else{
			// Booo failure
			System.out.println("FAILURE! YOU STINKY!");
		}
		
	}
}