import java.util.ArrayList;

public class AccessTester{
	public static void main(String [] args){
		AccessCourseData testerCData = new AccessCourseData();
		AccessStudentData testerSData = new AccessStudentData("user");
		
		// Tests getMinorRequirements
		System.out.println("\ngetMinorRequirements:");
		ArrayList<String> tempAL = testerCData.getMinorRequirements("NSSA");
		
		for(String tempS : tempAL){
			System.out.println(tempS);
		}

		// Tests getClassRequirements
		System.out.println("\n\ngetClassRequirements:");

		ArrayList<String> tempAL2 = testerCData.getClassRequirements("CSCI-320");
		for(String tempS : tempAL2){
			System.out.println(tempS);
		}
		
		// Tests getMajorRequirements
		System.out.println("\n\ngetMajorRequirements:");
		System.out.println("ClassID\t\tSemester");

		ArrayList<ArrayList> tempAL3 = testerCData.getMajorRequirements("ISTE");
		for(ArrayList tempSubAL : tempAL3){
			// This is a fixed size, tempSubAL will be of size 2 (0-1)
			System.out.println(tempSubAL.get(0) + "\t" + tempSubAL.get(1));
		}
		
		// Tests getMajors
		System.out.println("\n\ngetMajors:");
		System.out.printf("\n%-7s  %-36s  %-14s\n", "MajorID", "MajorName", "Credits Needed");

		ArrayList<ArrayList> tempAL4 = testerCData.getMajors();
		for(ArrayList tempSubAL : tempAL4){
			// This is a fixed size, tempSubAL will be of size 3 (0-2)
			System.out.printf("\n%-7s  %-36s  %-14s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2));
		}
		
		// Tests getMinors
		System.out.println("\n\ngetMinors:");
		System.out.printf("\n%-7s  %-50s  %-14s\n", "MinorID", "MinorName", "Credits Needed");

		ArrayList<ArrayList> tempAL5 = testerCData.getMinors();
		for(ArrayList tempSubAL : tempAL5){
			// This is a fixed size, tempSubAL will be of size 3 (0-2)
			System.out.printf("\n%-8s  %-50s  %-14s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2));
		}
		
		// Tests getClasses
		System.out.println("\n\ngetClasses:");
		System.out.printf("\n%-8s  %-60s  %-14s  %-20s\n", "ClassID", "ClassName", "Credits Value", "Requirements");

		ArrayList<ArrayList> tempAL6 = testerCData.getClasses();
		for(ArrayList tempSubAL : tempAL6){
			// This is a fixed size, tempSubAL will be of size 4 (0-3)
			System.out.printf("\n%-7s  %-60s  %-14s  %-20s", tempSubAL.get(0), tempSubAL.get(1), tempSubAL.get(2), tempSubAL.get(3));
		}
		
		// Tests getClassCredit
		
		// Tests getClassTotalCredits
	}
}