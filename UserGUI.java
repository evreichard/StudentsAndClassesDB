import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class UserGUI implements ActionListener{
	// DB Accessor
	AccessCourseData DBClassAccessor = new AccessCourseData();
	AccessStudentData DBStudentAccessor;
	
	JTextArea reportTextArea;
	
	JTextField classIDField;
	JTextField classNameField;
	JTextField classCreditField;
	JTextField classRequirementsField;
	
	JComboBox majorList;
	JComboBox minorList;
	
	JButton jbGenReport;
	JButton jbUpdateAddClass;
	JButton jbDeleteClass;
	
	String myUsername;
	String myMajor;
	String myMinor;
	
	public UserGUI(String username, String major, String minor){
		myUsername = username;
		myMajor = major;
		myMinor = minor;
		
		DBStudentAccessor = new AccessStudentData(myUsername);
		
		// Get Major from Student DB
	
		// JFrame Initialization
		JFrame myUserGUI = new JFrame("User Panel");
		myUserGUI.setLayout(new GridLayout());
		myUserGUI.setSize(720, 500);
		myUserGUI.setLocation(200, 200);
		myUserGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// JPanels
		JPanel leftP = new JPanel(new GridLayout(2,0));
		JPanel rightP = new JPanel();
		JPanel leftTopP = new JPanel();
		JPanel leftBottomP = new JPanel();
		
		JPanel classIDPanel = new JPanel(new GridLayout(0,2));
		JPanel classNamePanel = new JPanel(new GridLayout(0,2));
		JPanel classCreditPanel = new JPanel(new GridLayout(0,2));
		JPanel classRequirementPanel = new JPanel(new GridLayout(0,2));
		JPanel classButtonPanel = new JPanel();
		
		// Buttons
		jbGenReport = new JButton("Generate Report");
		jbUpdateAddClass = new JButton("Update / Add Class");
		jbDeleteClass = new JButton("Delete Class");
		
		// TextArea w/ Scroll
		reportTextArea = new JTextArea(28, 30);
		reportTextArea.setEditable(false);
		JScrollPane myScrollReport = new JScrollPane(reportTextArea);
		
		// JComboBox
		String[] tempMajors = {"Select Major"};
		String[] tempMinors = {"Select Minor"};
		String[] tempClassID = {"Select Class"};
		
		majorList = new JComboBox(tempMajors);
		minorList = new JComboBox(tempMinors);
		majorList.setPreferredSize(new Dimension(320, 30));
		minorList.setPreferredSize(new Dimension(320, 30));
		
		majorList.setEnabled(false);
		minorList.setEnabled(false);
		
		// JLabels
		JLabel classIDLabel = new JLabel("ID:");
		JLabel classNameLabel = new JLabel("Name:");
		JLabel classCreditLabel = new JLabel("Credit Value:");
		JLabel classRequirementsLabel = new JLabel("Requirements:");
		
		// JTextFields
		classIDField = new JTextField(15);
		classNameField = new JTextField(15);
		classCreditField = new JTextField(15);
		classRequirementsField = new JTextField(15);
		
		// Populate before ActionListener
		populateComboBoxes();
		
		// Action Listener
		jbGenReport.addActionListener(this);
		jbUpdateAddClass.addActionListener(this);
		jbDeleteClass.addActionListener(this);
		majorList.addActionListener(this);
		minorList.addActionListener(this);
		
		// Adding the stuff

		leftTopP.add(majorList);
		leftTopP.add(minorList);		
		leftTopP.add(jbGenReport);
		
		// leftTopP.add(classList);
		//leftTopP.add(Box.createRigidArea(new Dimension(0,200)));
		classIDPanel.add(classIDLabel);
		classIDPanel.add(classIDField);
		
		classNamePanel.add(classNameLabel);
		classNamePanel.add(classNameField);
		
		classCreditPanel.add(classCreditLabel);
		classCreditPanel.add(classCreditField);
		
		classRequirementPanel.add(classRequirementsLabel);
		classRequirementPanel.add(classRequirementsField);
		
		classButtonPanel.add(jbUpdateAddClass);
		classButtonPanel.add(jbDeleteClass);
		
		leftBottomP.add(classIDPanel);
		leftBottomP.add(classNamePanel);
		leftBottomP.add(classCreditPanel);
		leftBottomP.add(classRequirementPanel);
		leftBottomP.add(classButtonPanel);
		
		leftP.add(leftTopP);
		leftP.add(leftBottomP);
		rightP.add(myScrollReport);
		
		myUserGUI.add(leftP);
		myUserGUI.add(rightP);
		
		myUserGUI.setVisible(true);
	}
	
	public void populateComboBoxes(){
		ArrayList<String> tempMajAL;
		ArrayList<String> tempMinAL;
		
		classIDField.setText("");
		classNameField.setText("");
		classCreditField.setText("");
		classRequirementsField.setText("");
		
		majorList.removeAllItems();
		majorList.addItem(myMajor);
		
		minorList.removeAllItems();
		minorList.addItem(myMinor);
	}
	
	public static void main(String[] args){
		new UserGUI("exr7549", "ISTE", "NULL");
	}
	
	public void actionPerformed(ActionEvent ae){
		// Generate User Report
		if(ae.getSource() == jbGenReport){
			ArrayList<String> usersClasses = DBStudentAccessor.getClassIDs();
			ArrayList<ArrayList> usersMajorRequirements = DBClassAccessor.getMajorRequirements(myMajor);
			ArrayList<String> usersMinorRequirements = DBClassAccessor.getMinorRequirements(myMinor);
			
			ArrayList<ArrayList> classesMajStillNeeded = new ArrayList<ArrayList>();
			ArrayList<String> classesMinStillNeeded = new ArrayList<String>();
			
			for(ArrayList<String> tempAL : usersMajorRequirements){
				if(usersClasses.contains(tempAL.get(0))){
					
				}else{
					ArrayList<String> tempAL2 = new ArrayList<String>();
					tempAL2.add(tempAL.get(0));
					tempAL2.add(tempAL.get(1));
					
					classesMajStillNeeded.add(tempAL2);
				}
			}
			
			for(String tempString : classesMinStillNeeded){
				if(usersClasses.contains(tempString)){
					
				}else{
					classesMinStillNeeded.add(tempString);
				}
			}
			
			// classesMinStillNeeded (String array of Minor classes still needed)
			// classesMajStillNeeded (ArrayList array of Major classes still needed)
			
			for(ArrayList<String> tempAL : classesMajStillNeeded){
				System.out.println(tempAL.get(0) + "\t(" + tempAL.get(1) + ")");
			}
			
			// ---------------------------------------------------------------------------
			
			String majorReportString = "---------------------------------------------------------------------------";
				majorReportString = majorReportString + "\nMAJOR: " + majorList.getSelectedItem().toString() + "\n";
				majorReportString = majorReportString + "---------------------------------------------------------------------------";
				int count = 1;
				int previousVal = 0;
				
				for(ArrayList<String> tempAL : classesMajStillNeeded){
					try{
						if(Integer.parseInt(tempAL.get(1)) > previousVal){	
							// Output line
							majorReportString = majorReportString + "\n-----------------------------Semester " + tempAL.get(1) + " -----------------------------";
							previousVal = Integer.parseInt(tempAL.get(1));
							
							if(count % 2 == 0){
								count--;
							}
						}
					}catch(NumberFormatException nfe){
						// If no semesters, this will be thrown - ignore
					}
					
					if(count % 2 == 0){
						majorReportString = majorReportString + "\t" + tempAL.get(0) + "\t(" + tempAL.get(1) + ")";
					}else{
						majorReportString = majorReportString + "\n" + tempAL.get(0) + "\t(" + tempAL.get(1) + ")";
					}
					count++;
					
				}
				


				if(!((minorList.getSelectedItem().toString()).equals("Select Minor"))){
					majorReportString = majorReportString + "\n---------------------------------------------------------------------------";
					majorReportString = majorReportString + "\nMINOR: " + minorList.getSelectedItem().toString();
					majorReportString = majorReportString + "\n---------------------------------------------------------------------------";
					
					// Append to reportTextArea
					
					count = 1;
					
					for(String tempS : classesMinStillNeeded){
						if(count % 2 == 0){
							majorReportString = majorReportString + "\t" + tempS + "\t";
						}else{
							majorReportString = majorReportString + "\n" + tempS + "\t";
						}
						count++;
					}
				}
				
				reportTextArea.setText(majorReportString);
		}
	}
}