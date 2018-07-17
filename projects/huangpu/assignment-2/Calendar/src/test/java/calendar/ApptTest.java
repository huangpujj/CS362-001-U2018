/** A JUnit test class to test the class ApptTest. */

package calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import javax.lang.model.element.Element;

import calendar.Appt;
import calendar.CalendarUtil;
public class ApptTest  {

  @Test(timeout = 4000)
  public void test00()  throws Throwable  {
      Appt appt = new Appt(10, 30, 9, 14, 2018, "Birthday Party", "This is my birthday party", "xyz@gmail.com");
      appt.setValid();
      assertEquals(2, appt.getRecurBy());
      assertFalse(appt.isRecurring());
      assertEquals(0, appt.getRecurIncrement());
      assertEquals("Birthday Party",appt.getTitle());
      assertTrue(!appt.getValid());
    
      
  }
@Test(timeout = 4000)
 public void test01()  throws Throwable  {
	 Appt appt1 = new Appt(12,32,12,13,2018,"happy new year","this is 2018","huang@oregonstate.edu");
	 appt1.setValid();
	 assertTrue(appt1.hasTimeSet());
	 assertFalse(appt1.isOn(12, 13, 2020));
	
	 assertEquals(12, appt1.getStartHour());
	 assertEquals(32, appt1.getStartMinute());
	 assertEquals(12, appt1.getStartDay());
	 assertEquals(13, appt1.getStartMonth());
	 assertEquals(2018, appt1.getStartYear());
	 assertEquals("happy new year", appt1.getTitle());
	 assertEquals("this is 2018", appt1.getDescription());
	 assertEquals("huang@oregonstate.edu",appt1.getEmailAddress());
     
}
@Test(timeout = 4000)
 public void test02() throws Throwable {
	Appt  appt = new Appt(12,4,2019,"hello world","test","puhuang");
	appt.setValid();
	int [] array = {1,2,3,4,5};
	String string = appt.toString();
    //assertEquals("\t4/12/2019 at -1:-1am,hello world,test\n", string);
	appt.setRecurrence(array, 2, 1,3);
	 assertFalse(appt.hasTimeSet());
}

@Test(timeout = 4000)
public void test03()  throws Throwable  {
    Appt appt = new Appt(8, 8, 10, 11, 2020, "nothing", "is important", "than breakfast");
    appt.setValid();
    assertTrue(appt.getValid());
    assertEquals(null, appt.getXmlElement());
    
    
}

@Test(timeout = 4000)
public void test04() throws Throwable  {
    Appt appt = new Appt(10, 10, 8, 6, 2020, "Nothing", "is important", "than breakfast");
    appt.setValid();
    String string0 = appt.toString();
    assertEquals("\t6/8/2020 at 10:10am ,Nothing, is important\n", string0);
    assertTrue(appt.getValid());
}

@Test(timeout = 4000)
public void test05()  throws Throwable  {
    Appt appt = new Appt(6, -9, 8, 8, 10, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test06()  throws Throwable  {
    Appt appt = new Appt(-10, 10, 10, 8, 100,"Nothing", "is important", "than breakfast");
    appt.setValid();
    String string0 = appt.toString();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test07()  throws Throwable  {
    Appt appt = new Appt(8, 8, -10, 2, 1600, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test08()  throws Throwable  {
    Appt appt = new Appt(8, 8, 8, -10, 2600, "Nothing", "is important", "than breakfast");
    appt.setValid();
    String string0 = appt.toString();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test09()  throws Throwable  {
    Appt appt = new Appt(8, 8, 8, 8, -2018, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test10()  throws Throwable  {
    Appt appt = new Appt(10, 60, 10, 10, 10, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test11()  throws Throwable  {
    Appt appt = new Appt(24, 8, 8, 8, 8, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test12()  throws Throwable  {
    Appt appt = new Appt(8, 8, 34, 8, 8, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test13()  throws Throwable  {
    Appt appt = new Appt(8, 8, 8, 14, 8, "Nothing", "is important", "than breakfast");
    appt.setValid();
    assertFalse(appt.getValid());
}

@Test(timeout = 4000)
public void test14()  throws Throwable  {
    Appt appt0 = new Appt(10, 45, 10, 18, 2020, "Nothing", "is important", "than breakfast");
    assertFalse(appt0.isOn(9, 8, 2020));
}

@Test(timeout = 4000)
public void test15()  throws Throwable  {
    Appt appt0 = new Appt(10, 45, 10, 18, 2020, "Nothing", "is important", "than breakfast");
    assertFalse(appt0.isOn(8, 18, 2020));
}

@Test(timeout = 4000)
public void test16()  throws Throwable  {
    Appt appt0 = new Appt(10, 45, 10, 15, 2020, "Nothing", "is important", "than breakfast");
    assertFalse(appt0.isOn(10, 8, 2020));
}

@Test(timeout = 4000)
public void test17()  throws Throwable  {
    Appt appt0 = new Appt(11, 11, 2018, "Nothing", "is important", "than breakfast");
    int[] recurDaysArr = {7, 3, 6};
    appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 4, Appt.RECUR_NUMBER_FOREVER);
    assertTrue(appt0.isRecurring());
}


@Test(timeout = 4000)
public void test18()  throws Throwable  {
    Appt appt0 = new Appt(11, 11, 2018, "Nothing", "is important", "than breakfast");
    int[] recurDaysArr = null;
    appt0.setRecurrence(recurDaysArr, Appt.RECUR_BY_WEEKLY, 4, Appt.RECUR_NUMBER_FOREVER);
    assertTrue(appt0.isRecurring());
}


@Test(timeout = 4000)
public void test19()  throws Throwable  {
    Appt appt0 = new Appt(11, 11, 2018, "Nothing", "is important", "than breakfast");
    assertFalse(appt0.isRecurring());
}








	

}
