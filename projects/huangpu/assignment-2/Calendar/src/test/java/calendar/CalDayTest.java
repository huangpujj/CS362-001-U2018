/** A JUnit test class to test the class CalDay. */
package calendar;

import org.junit.Test;
import static org.junit.Assert.*;
import calendar.Appt;
import calendar.CalDay;

import java.util.*;

public class CalDayTest{

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
	  GregorianCalendar day = new GregorianCalendar(2018, 5, 8);
      CalDay day0 = new CalDay(day);
      assertTrue(day0.isValid());

  }
 
  @Test(timeout = 4000)
  public void test01()  throws Throwable  {
	  GregorianCalendar day = new GregorianCalendar(2018, 9, 10);
      CalDay day0 = new CalDay(day);
      String s0,s1;
      s0= day0.toString();
      s1 = "\t --- 11/10/2018 --- \n --- -------- Appointments ------------ --- \n\n";
      assertTrue(s0.equals(s1));
  }
  
  @Test(timeout = 4000)
  public void test02()  throws Throwable  {
      GregorianCalendar day = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(day);
      Appt appt0 = new Appt(2, 3, 4, 5, 2018, "nothing", "is important", "huang@oregonstate.edu");
      Appt appt1 = new Appt(3, 4, 5, 6, 2018, "nothing2", "is important", "huang@oregonstate.edu");
      Appt appt2 = new Appt(4, 5, 6, 7, 2018, "nothing2", "is important", "huang@oregonstate.edu");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      assertEquals(day0.getSizeAppts(), 3);
  }
  
  
  @Test(timeout = 4000)
  public void test03()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(someday);
      Appt appt0,appt1,appt2;
      appt0= new Appt(7, 6, 2018, "No free time", "This time is not aviliable.", "huang@oregonstate.edu");
      appt1 = new Appt(0, 5, 7, 6, 2018, "nothing", "in the morning.", "huang@oregonstate.edu");
      appt2 = new Appt(20, 10, 7, 6, 2018, "nothing", "in the evening.", "huang@oregonstate.edu");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      day0.addAppt(appt2);
      assertEquals(day0.getSizeAppts(), 3);
      String string0 = day0.getFullInfomrationApp(day0);
      assertEquals("6-10-2018 \n\tNo free time This time is not aviliable. \n\t12:05AM nothing in the morning. \n\t8:10PM nothing in the evening. ", string0);
  }
  
  @Test(timeout = 4000)
  public void test04()  throws Throwable  {
      GregorianCalendar oneday = new GregorianCalendar(2018, 5, 10);
      CalDay day0 = new CalDay(oneday);
      Appt appt0,appt1;
      appt0 = new Appt(10, 5, 7, 6, 2018, "appt2", "there is a appt", "huang@oregonstate.edu");
      appt1 = new Appt(8, 5, 7, 6, 2018, "appt", "there is a appt", "huang@oregonstate.edu");
      day0.addAppt(appt0);
      day0.addAppt(appt1);
      String string0 = day0.toString();
  }
  
  @Test(timeout = 4000)
  public void test05()  throws Throwable  {
      GregorianCalendar someday = new GregorianCalendar(2020, 6, 9);
      CalDay day0 = new CalDay(someday);
      Appt appt0 = new Appt(7, 5, -10, 6, 2020, "appointment", "There is a appointment", "huang@oregonstate.edu");
      appt0.setValid();
      day0.addAppt(appt0);
      String s0;
      s0 = day0.toString();
      assertEquals("\t --- 8/9/2020 --- \n --- -------- Appointments ------------ --- \n\n", s0);
  }


  @Test(timeout = 4000)
  public void test06()  throws Throwable  {
      CalDay day0 = new CalDay();
      String s0,s1;
      s0 = day0.toString();
      s1 = "\t --- 7/10/2018 --- \n --- -------- Appointments ------------ --- \n\n";
      assertFalse(s0.equals(s1));
  }
  
  
  
  
  
	}