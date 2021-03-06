package calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import java.io.*;
import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;
import calendar.DataHandler;
import java.lang.reflect.*;


import java.util.GregorianCalendar;
import java.util.LinkedList;


public class DataHandlerTest{
	private final ByteArrayOutputStream content_out = new ByteArrayOutputStream();

  //clean up files before every test
  @Before
  public void setUp() {
      File testfile = new File("calendar_test.xml");
      testfile.delete();
      testfile = new File("calendar.xml");
      testfile.delete();
  }

  //clean up files after every test
  @After
  public void tearDown() {
      File testfile = new File("calendar_test.xml");
      testfile.delete();
      testfile = new File("calendar.xml");
      testfile.delete();
  }
  
  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      DataHandler data0 = new DataHandler();
      assertTrue(data0.save());
  }

  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  DataHandler data0 = new DataHandler();
      assertTrue(data0.save());
      GregorianCalendar new_day = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(new_day);
      Appt appt0 = new Appt(5, 6, 7, 8, 2018, "nothing", "is special but a meeting", "huang@gmail.com");
      day0.addAppt(appt0);
      DataHandler data1 = new DataHandler("calendar_test.xml");
      assertTrue(data1.saveAppt(appt0));
  }

  //save appointments - autosave off
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GregorianCalendar new_day = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(new_day);
      
      Appt appt0 = new Appt(5, 6, 7, 8, 2018, "nothing", "is special but a meeting", "huang@gmail.com");
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml", false);
      assertTrue(data0.saveAppt(appt0));
      
      //new start
      Appt appt1 = new Appt(5, 6, 7, 8, 2018, "nothing", "is special but a meeting", "huang@gmail.com");
      int[] recurDaysArr = {3, 4, 5};
      appt1.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 3, Appt.RECUR_NUMBER_FOREVER);//2-3
      day0.addAppt(appt1);
      DataHandler data1 = new DataHandler("calendar_test.xml");
      assertTrue(data1.saveAppt(appt1));
  }

  
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar new_day = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(new_day);
      Appt appt0 = new Appt(5, 6, 7, 8, 2020, "Meeting", "This is a meeting", "huang@gmail.com");
      day0.addAppt(appt0);
      DataHandler data0 = new DataHandler("calendar_test.xml");
      data0.saveAppt(appt0);
      assertTrue(data0.deleteAppt(appt0));
  }

  
  	
  //save appointments - invalid appointment
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GregorianCalendar new_day;
      new_day = new GregorianCalendar(2018, 7, 8);
      CalDay day0; 
      day0 = new CalDay(new_day);
      Appt appt0;
      appt0 = new Appt(-50, 6, 7, 8, 2020, "nothing", "is specail but a meeting", "huang@gmail.com");
      appt0.setValid();
      day0.addAppt(appt0);
      DataHandler data0;
      data0= new DataHandler("calendar_test.xml");
      assertFalse(data0.saveAppt(appt0));
     // assertFalse(data0.deleteAppt(appt0));
  }

   
    @Test(timeout = 4000)
    public void test05()  throws Throwable  {
        GregorianCalendar new_day;
        new_day = new GregorianCalendar(2018, 7, 8);
        CalDay day0;
        day0= new CalDay(new_day);
        Appt appt0;
        appt0= new Appt(5, 6, 7, 8, 2018, "nothing", "is special but a meeting", "huang@gmail.com");
        day0.addAppt(appt0);
        DataHandler data0;
        data0= new DataHandler("calendar_test.xml", false);
        data0.saveAppt(appt0);
        assertTrue(data0.deleteAppt(appt0));
        Appt appt1 = new Appt(5, 6, 7, 8, 2018, "Meeting", "This is a meeting", "huang@gmail.com");
        day0.addAppt(appt0);
        DataHandler data1 = new DataHandler("calendar_test.xml", false);
        assertFalse(data1.deleteAppt(appt0));
        
    }

 
    @Test(timeout = 4000)
    public void test06()  throws Throwable  {
        GregorianCalendar new_day = new GregorianCalendar(2018, 7, 8);
        CalDay day0 = new CalDay(new_day);
        Appt appt0 = new Appt(-100, 5, 6, 7, 2018, "Meeting", "This is a meeting", "huang@gmail.com");
        appt0.setValid();
        day0.addAppt(appt0);
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        data0.saveAppt(appt0);
        assertFalse(data0.deleteAppt(appt0));
    }

    @Test(expected = DateOutOfRangeException.class)
    public void test7()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 5, 5);
        GregorianCalendar lastday = new GregorianCalendar(2018, 5, 10);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(lastday, firstday);
        fail();
        
        //new start
        GregorianCalendar new_firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar new_lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2050, "nothing", "is special but a meeting", "huang@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> new_calDays = new LinkedList<CalDay>();
        new_calDays = (LinkedList<CalDay>) data0.getApptRange(new_firstday, new_lastday);
        int numberappt = 0;
        for (int i = 0; i < new_calDays.size(); i++) {
            LinkedList<Appt>  appts = new_calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);     
    }

    @Test(timeout = 4000)
    public void test08()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2050, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "Meeting", "This is a meeting", "huang@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(33, numberappt);
        
        //new start
        GregorianCalendar new_firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar new_lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt1 = new Appt(5, 5, 1, 1, 2018, "something", "will happend this day.", "huang@oregonstate.edu");
        int[] new_recurDaysArr = {};
        appt1.setRecurrence(new_recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, Appt.RECUR_NUMBER_FOREVER);
        appt1.setValid();
        data0.saveAppt(appt1);
        LinkedList<CalDay> new_calDays = new LinkedList<CalDay>();
        new_calDays = (LinkedList<CalDay>) data0.getApptRange(new_firstday, new_lastday);
        int new_numberappt = 0;
        for (int i = 0; i < new_calDays.size(); i++) {
            LinkedList<Appt>  appts = new_calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                new_numberappt++;
            }
        }
        assertEquals(5, new_numberappt);
        
    }

    @Test(timeout = 4000)
    public void test09()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 10, 20);
        Appt appt0 = new Appt(8, 10, 1, 5, 2000, "asdf", "adsfsdf", "huang@gmail.com");
        int[] recurDaysArr = {100, 200, 300};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
        
        //new start
        GregorianCalendar new_lastday = new GregorianCalendar(2019, 10, 20);
        int[] new_recurDaysArr = {1, 2, 3, 4, 5};
        appt0.setRecurrence(new_recurDaysArr, Appt.RECUR_BY_MONTHLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> new_calDays = new LinkedList<CalDay>();
        new_calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, new_lastday);
        int new_numberappt = 0;
        for (int i = 0; i < new_calDays.size(); i++) {
            LinkedList<Appt>  appts = new_calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                new_numberappt++;
            }
        }
        assertEquals(22, new_numberappt);
      
    }

 
    @Test(timeout = 4000)
    public void test10()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar lastday = new GregorianCalendar(2019, 10, 20);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(firstday, lastday);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
        
        GregorianCalendar new_firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar new_lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "asdfdfaf", "asdfasdfasdfasdf.", "huang@oregonstate.edu");
        int[] recurDaysArr = {1};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> new_calDays = new LinkedList<CalDay>();
        new_calDays = (LinkedList<CalDay>) data0.getApptRange(new_firstday, new_lastday);
        int new_numberappt = 0;
        for (int i = 0; i < new_calDays.size(); i++) {
            LinkedList<Appt>  appts = new_calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                new_numberappt++;
            }
        }
        assertEquals(2, new_numberappt);
    }
    ///mutation test start from here
    @Test(timeout = 4000)
    public void new_test01()  throws Throwable  {
        DataHandler new_data = new DataHandler("calendar_test.xml");
        Field file = new_data.getClass().getDeclaredField("valid");
        file.setAccessible(true);
        file.set(new_data, false);
        GregorianCalendar first_day = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar last_day = new GregorianCalendar(2018, 5, 1);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) new_data.getApptRange(first_day, last_day);
    }
    @Test(timeout = 4000)
    public void new_test02()  throws Throwable  {
        System.setOut(new PrintStream(content_out));
        DataHandler new_data = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar last_day = new GregorianCalendar(2018, 1, 1);
        Appt new_appt = new Appt(5, 5, 1, 1, 2018, "asdfasdf", "asdffasdf.", "huangpu@oregonstate.edu");
        int[] recurDaysArr = {1};
        new_appt.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 1);
        new_appt.setValid();
        new_data.saveAppt(new_appt);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) new_data.getApptRange(first_day, last_day);
        assertEquals("", content_out.toString());
    }
  
    @Test(timeout = 4000)
    public void new_test03()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar last_day = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "asdfasdf", "Tasdfasdft.", "huang@oregonstate.edu");
        int[] recurDaysArr = {6};
        appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 1, 100);
        appt0.setValid();
        data0.saveAppt(appt0);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(first_day, last_day);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(5, numberappt);
    }
 
    @Test(timeout = 4000)
    public void new_test04()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar last_day = new GregorianCalendar(2019, 10, 20);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data0.getApptRange(first_day, last_day);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
        
        
        
        
        //new_start
        DataHandler data1 = new DataHandler("calendar_test.xml");
        GregorianCalendar firstday = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar lastday = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "Event", "This is an event.", "home@yahoo.com");
        int[] recurDaysArr = {8};
        appt0.setRecurrence(recurDaysArr, -404, 1, 100);
        appt0.setValid();
        data1.saveAppt(appt0);
        LinkedList<CalDay> calDays1 = new LinkedList<CalDay>();
        calDays1 = (LinkedList<CalDay>) data1.getApptRange(firstday, lastday);
        int numberappts = 0;
        for (int i = 0; i < calDays1.size(); i++) {
            LinkedList<Appt>  appts1 = calDays1.get(i).getAppts();
            for(int ii = 0; ii < appts1.size(); ii++) {
                numberappts++;
            }
        }
        assertEquals(1, numberappts);
        
        
        
        
    }
   
    
    @Test(timeout = 4000)
    public void new_test05()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar last_day = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "asdfasdf", "asdfdasdf.", "dasfasdf@yahoo.com");
        appt0.setValid();
        data0.saveAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertTrue(testfile.delete());
        
        
        
        //new_start
        DataHandler data1 = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day1 = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar last_day1 = new GregorianCalendar(2018, 10, 20);
        Appt appt1 = new Appt(8, 10, 1, 5, 2050, "asdffasdfa", "asdfasdf", "sadfasdfaf@gmail.com");
        int[] recurDaysArr = {2,3,4};
        appt1.setRecurrence(recurDaysArr, Appt.RECUR_BY_YEARLY, 2, Appt.RECUR_NUMBER_FOREVER);
        appt1.setValid();
        data1.saveAppt(appt1);
        LinkedList<CalDay> calDays = new LinkedList<CalDay>();
        calDays = (LinkedList<CalDay>) data1.getApptRange(first_day1, last_day1);
        int numberappt = 0;
        for (int i = 0; i < calDays.size(); i++) {
            LinkedList<Appt>  appts = calDays.get(i).getAppts();
            for(int ii = 0; ii < appts.size(); ii++) {
                numberappt++;
            }
        }
        assertEquals(0, numberappt);
        
        
        
    }

    @Test(timeout = 4000)
    public void new_test06()  throws Throwable  {
        DataHandler data0 = new DataHandler("calendar_test.xml", false);
        GregorianCalendar first_day = new GregorianCalendar(2018, 0, 1);
        GregorianCalendar last_day = new GregorianCalendar(2018, 1, 1);
        Appt appt0 = new Appt(5, 5, 1, 1, 2018, "asdfasdf", "asdfasdf.", "asdfasdfasdf@yahoo.com");
        appt0.setValid();
        data0.saveAppt(appt0);
        data0.deleteAppt(appt0);
        File testfile = new File("calendar_test.xml");
        assertFalse(testfile.delete());
        
        
        //new_start
        
        DataHandler new_data = new DataHandler("calendar_test.xml");
        GregorianCalendar first_day1 = new GregorianCalendar(2018, 1, 1);
        GregorianCalendar last_day1 = new GregorianCalendar(2050, 10, 20);
        Appt appt = new Appt(8, 10, 1, 5, 2000, "sdfasdfasdfasdfas", "Tasdfasdfasdfdfg", "sdafasdfk@gmail.com");
        appt.setValid();
        new_data.saveAppt(appt);
        LinkedList<CalDay> new_calDays = new LinkedList<CalDay>();
        new_calDays = (LinkedList<CalDay>) new_data.getApptRange(first_day1, last_day1);
        int numberappt1 = 0;
        for (int i = 0; i < new_calDays.size(); i++) {
            LinkedList<Appt>  appts1 = new_calDays.get(i).getAppts();
            for(int ii = 0; ii < appts1.size(); ii++) {
                numberappt1++;
            }
        }
        assertEquals(0, numberappt1);
        
        
    }
     
    
}