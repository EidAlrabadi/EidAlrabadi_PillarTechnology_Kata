package BabySitterMethods;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class TimeAnalyzer {
	
	Scanner in = new Scanner(System.in);
	
	
	private static String userStartTime;  // User input is converted for calculations this keeps track of them
	private static String userEndTime;  
	
	
	public static String getUserStartTime() {
		return userStartTime;
	}




	public void setUserStartTime(String userStartTime) {
		this.userStartTime = userStartTime;
	}




	public static String getUserEndTime() {
		return userEndTime;
	}




	public void setUserEndTime(String userEndTime) {
		this.userEndTime = userEndTime;
	}


	/** This is a method that parses a user input string, and returns a boolean value
	 * to determine if the input is of proper format on the 12 hour clock. The format 
	 * must look like the following #:## PM or ##:## AM where # stands for accepted values on
	 * the 12 hour clock. The input MUST include "AM" or "PM" exact casing.
	 * 
	 *
	 * 
	 * 
	 * 
	 * @return true if the format is legal, or false if the format is illegal.
	 */

		String exactStart = "17:00";   //This is the start time 5 PM converted to military
		String exactEnd = "04:00";     // This is the end time 4 AM converted to military
		public String properTimeInputs(String time) {
		LocalTime convertedFormat;
		try {
		convertedFormat = LocalTime.parse(time, DateTimeFormatter.ofPattern("h:m a")); //Java.time format for the forced user input of the program
		}catch(DateTimeParseException e) {
			System.out.println("Input format is incorrect. Please try again");         //Returns false if the format is incorrect.
			return null;
		}
		
		String convertedFormatString = convertedFormat.toString();
		
		if(convertedFormatString.equals(exactStart) || convertedFormatString.equals(exactEnd)) {
			return convertedFormatString;
		}
		int leftOfColonDigit = Integer.parseInt(convertedFormatString.substring(0,2)); //Gets the value to the left of colon and transforms to int.
	    int fourAM = 4; // 4 AM
	    int fivePM = 16;  // 5 PM
		
		if(leftOfColonDigit >= fourAM && leftOfColonDigit <= fivePM) { //If Greater than 4:00 PM with exception of 4:00 PM or
																	   // less than 16 which is 5 PM military it is non working errors so false.
			System.out.println("These are non working hours, please input working hours");
			return null;
		}
		
		
		return convertedFormatString;   //If all test checks pass then return true.
			}

		
		
		
		/**
		 * 
		 * @param startTime (Starting time of the shift) after TimeAnalyzer is called on the string
		 * @param endTime   (End time of the shift) after TimeAnalyzer is called on the string
		 * @return a boolean that makes sure the times are logical. End times before start time or outside of 
		 * work hours.
		 */
			
		
		public boolean timeChecker(String startTime, String endTime) {
			String parsingStartTime = startTime.substring(0,2) + startTime.substring(3,5); //parses the string to retrieve the time value excluding :
			String parsingEndTime =  endTime.substring(0,2) + endTime.substring(3,5);     // input "16:53" becomes "1653" for later calculations
			
			char firstCharacterStartTime = parsingStartTime.charAt(0);
			char firstCharacterEndTime = parsingEndTime.charAt(0);

			int startTimeInteger = Integer.parseInt(parsingStartTime); //Converting string to int for arithmetic
			int endTimeInteger = Integer.parseInt(parsingEndTime);
		
			if(firstCharacterStartTime!= '0' && firstCharacterEndTime == '0') {
				return true;
				
				
			}else if(firstCharacterStartTime == '0' && firstCharacterEndTime != '0') {
				System.out.println("Error! START time cannot be after end time!");
				return false;
			}
			
			else if(startTimeInteger - endTimeInteger <= 0) {
				return true;                    }
			
			
			
			System.out.println("Error! END time cannot be before start time");
			return false;
			
			
		}
		
		/**
		 * This method grabs the start time from a user using a scanner, if the time is in the incorrect
		 * format it will continually ask until the proper format is achieved.
		 * 
		 * @return a proper input string.
		 */
		
		public String getStartTimeFromUser() {
			System.out.print("Enter the start time in 12 hour format followed by a space and 'PM' OR 'AM': ");
			String userTimeInput = in.nextLine();
			String time = properTimeInputs(userTimeInput);
			while(time == null) {
				System.out.print("Enter the start time in 12 hour format followed by a space and 'PM' OR 'AM': ");
				userTimeInput = in.nextLine();
				time = properTimeInputs(userTimeInput);
			}
			setUserStartTime(userTimeInput);
			return time;
		}
		
		/**
		 * Same exact method as getStartTimeFromuser() the only difference is the System.out.println()
		 * This one specifically says end to avoid confusion with the user, and gets the end time.
		 * 
		 * @return a proper input string.
		 */
		
		public String getEndTimeFromUser() {
			System.out.print("Enter the end time in 12 hour format followed by a space and 'PM' OR 'AM': ");
			String userTimeInput = in.nextLine();
			String time = properTimeInputs(userTimeInput);
			while(time == null) {
				System.out.print("Enter the end time in 12 hour format followed by a space and 'PM' OR 'AM': ");
				userTimeInput = in.nextLine();
				time = properTimeInputs(userTimeInput);
			}
			setUserEndTime(userTimeInput);
			return time;
		}
		
		
		/**
		 * This method assumes the times given are in proper format (outside of non working hours), and
		 * returns a boolean to check if the end time is prior to the start time. if this mistake is present
		 * it will continually ask again until a proper input is given
		 * 
		 * @return boolean determing if the times are valid
		 */
		
		public ArrayList<String> validateStartAndEndTime(String startTime, String endTime) {
			ArrayList<String> times = new ArrayList<String>();
			while(!timeChecker(startTime,endTime)) {
				startTime = getStartTimeFromUser();
				endTime = getEndTimeFromUser();
			}
			times.add(startTime);
			times.add(endTime);
			
			return times;
		}
		
		
		
		
}
