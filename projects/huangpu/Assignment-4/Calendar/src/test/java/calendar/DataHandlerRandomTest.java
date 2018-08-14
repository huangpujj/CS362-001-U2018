package calendar;

import java.util.Calendar;
import java.util.Random;

import org.junit.Test;


import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import calendar.Appt;
import calendar.CalDay;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;

import org.junit.Assert.*;




/**
 * Random Test Generator  for DataHandler class.
 */

public class DataHandlerRandomTest {
	
    /**
     * Generate Random Tests that tests DataHandler Class.
     */
	
	  private static final long TestTimeout = 60 * 500 * 1; /* Timeout at 7.5 seconds */
	  private static final int NUM_TESTS=100;
	  
	  @Before
	     public void setUp() {
	         File testfile = new File("calendar_test.xml");
	         testfile.delete();
	     }

	     //clean up file after test
	     @After
	     public void tearDown() {
	         File testfile = new File("calendar_test.xml");
	         testfile.delete();
	     }

	  
	  
	/* @Test
	  public void radnomtest()  throws Throwable  {
		 long startTime = Calendar.getInstance().getTimeInMillis();
 		 long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;
 		 
 		 try {
 			 for(int recursion = 0; elapsed < TestTimeout; recursion++ ) {
 				 
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
			 	
			 	
 				 
 			 }
 			 
 			elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
			 if(iteration!=0)
				 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);
 
 		 }catch (NullPointerException e) {
			 
		 } 
			
		 
		 
		 
	 }*/

	 
		 @Test
		  public void randomtest()  throws Throwable  {
			  long startTime = Calendar.getInstance().getTimeInMillis();
			  long elapsed = Calendar.getInstance().getTimeInMillis() - startTime;


			  for (int iteration = 0; elapsed < TestTimeout; iteration++) {
				  try{

					 long randomseed =System.currentTimeMillis(); //10
	 				 Random random = new Random(randomseed);

					 //random autosave setting
					 boolean autosave= (Math.random() < 0.5);
					 
					// System.out.println("the autosave set was :" + autosaveSet);
					 
					 //start days
	 				int start_Day=ValuesGenerator.getRandomIntBetween(random, -1, 33);
				 	int start_Month=ValuesGenerator.getRandomIntBetween(random, -13, 13);
				 	int start_Year=ValuesGenerator.getRandomIntBetween(random, 2018, 2019);

					 //end days
	 				 int end_Day=ValuesGenerator.getRandomIntBetween(random, -1, 33);
	 				 int end_Month=ValuesGenerator.getRandomIntBetween(random, -13, 13);
	 				 int end_Year=ValuesGenerator.getRandomIntBetween(random, 2019, 2020);

					 //random number of appointments
					 int num_Appts=ValuesGenerator.getRandomIntBetween(random, 0, 10);

					 //setup the random firstday and lastday
					 DataHandler data0 = new DataHandler("calendar_test.xml", autosave);
					 GregorianCalendar first_day = new GregorianCalendar(start_Year, start_Month, start_Day);
					 GregorianCalendar last_day = new GregorianCalendar(end_Year, end_Month, end_Day);

					 //one in ten data handlers are not valid
					 if(ValuesGenerator.getBoolean(0.10f,random)) {
					 	Field field = data0.getClass().getDeclaredField("valid");
			         	field.setAccessible(true);
			         	field.set(data0, false);
				 	 }

					 //random number of appointments with random recurrence
					 while(num_Appts > 0){

						int startHour=ValuesGenerator.getRandomIntBetween(random, -1, 35);
				 		int startMinute=ValuesGenerator.getRandomIntBetween(random, -1, 60);
					 	int startDay=ValuesGenerator.getRandomIntBetween(random, -1, 33);
					 	int startMonth=ValuesGenerator.getRandomIntBetween(random, -1, 13);
					 	int startYear=ValuesGenerator.getRandomIntBetween(random, 2018, 2020);
					 	String title="Birthday Party";
					 	String description="This is my birthday party.";
					 	String emailAddress="xyz@gmail.com";
					 	
					 	Appt appt = new Appt(startHour, startMinute, startDay,startMonth,startYear,title,description,emailAddress);
						
						
						//recurrence
						int sizeArray=ValuesGenerator.getRandomIntBetween(random, 0, 8);
						int[] recurDays=ValuesGenerator.generateRandomArray(random, sizeArray);
						int recur=ApptRandomTest.RandomSelectRecur(random);
						int recurIncrement = ValuesGenerator.RandInt(random);
						int recurNumber=ApptRandomTest.RandomSelectRecurForEverNever(random);
						appt.setRecurrence(recurDays, recur, recurIncrement, recurNumber);

					 	appt.setValid();

						//only save half of the appointments
						if(ValuesGenerator.getBoolean(0.50f,random)) {
					 		data0.saveAppt(appt);
						}

						//delete one out of every ten appointments
						if(ValuesGenerator.getBoolean(0.10f,random)) {
							data0.deleteAppt(appt);
						}

						num_Appts--;
				 	 }

					 //test getApptRange();
					 LinkedList<CalDay> cal_day = new LinkedList<CalDay>();
					 cal_day = (LinkedList<CalDay>) data0.getApptRange(first_day, last_day);

					
					 elapsed = (Calendar.getInstance().getTimeInMillis() - startTime);
					/* if(iteration!=0)
						 System.out.println("elapsed time: "+ elapsed + " of "+TestTimeout);*/

		 			}catch(DateOutOfRangeException e){
		 			}
				 }
			 }
	
}
