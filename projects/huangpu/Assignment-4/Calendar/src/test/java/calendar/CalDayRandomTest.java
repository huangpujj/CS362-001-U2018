package calendar;


import org.junit.Test;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Random;
import java.util.GregorianCalendar;


import calendar.Appt;
import calendar.CalDay;
import java.util.*;




/**
 * Random Test Generator  for CalDay class.
 */

public class CalDayRandomTest {
	
    /**
     * Generate Random Tests that tests CalDay Class.
     */
	private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 7.5 seconds */
	private static final int NUM_TESTS=100;
	
	 @Test
	  public void radnomtest()  throws Throwable  {
		 
		 long startTime = Calendar.getInstance().getTimeInMillis();
 		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;

		 
		 
		 try {
	 	 	for( int recursion = 0; elapsed < TestTimeout; recursion++) {
		  	
	 	 		long randomseed =System.currentTimeMillis(); 
	 			 Random random = new Random(randomseed);
	 			 
		  		
		  		
			 	int startHour=ValuesGenerator.getRandomIntBetween(random, -11, 30);
		 		int startMinute=ValuesGenerator.getRandomIntBetween(random, -60, 100);
			 	int startDay=ValuesGenerator.getRandomIntBetween(random, -100, 100);
			 	int startMonth=ValuesGenerator.getRandomIntBetween(random, -13, 13);
			 	int startYear=ValuesGenerator.getRandomIntBetween(random, -2018, 2018);
			 	String title="Birthday Party";
			 	String description="This is my birthday party.";
			 	String emailAddress="xyz@gmail.com";
			 	
			 	Appt appt = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
			 	startHour=ValuesGenerator.getRandomIntBetween(random, -11, 30);
		 		startMinute=ValuesGenerator.getRandomIntBetween(random, -60, 100);
			 	Appt appt0 = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
			 	startHour=ValuesGenerator.getRandomIntBetween(random, -11, 30);
		 		startMinute=ValuesGenerator.getRandomIntBetween(random, -60, 100);
			 	Appt appt1 = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
			 	
			 	appt.setValid();
			 	appt0.setValid();
		  		appt1.setValid();
		  		
			 	
			 	Calendar cal = Calendar.getInstance();
			 	GregorianCalendar gcal = new GregorianCalendar(startYear,startMonth,startDay);
			 	CalDay new_cal = new CalDay(gcal);
			 	new_cal.addAppt(appt);
			 	new_cal.addAppt(appt1);
			 	new_cal.addAppt(appt0);
			 	
			 	
			 	//show elapsed time??
				elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			/*   	if((iteration%10000)==0 && iteration!=0 )
					System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);*/
		 		}
		 }catch (NullPointerException e) {
			 
		 } 
	 	 
	 }


	
}
