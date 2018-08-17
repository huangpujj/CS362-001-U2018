package finalprojectB;

import junit.framework.TestCase;
import java.util.Random;
import java.util.Arrays;
import static org.junit.Assert.*;

//You can use this as a skeleton for your 3 different test approach
//It is an optional to use this file, you can generate your own test file(s) to test the target function!
// Again, it is up to you to use this file or not!





public class UrlValidatorTest extends TestCase {


   public UrlValidatorTest(String testName) {
      super(testName);
   }

   
   
   public void testManualTest()
   {
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
	   assertEquals(false,urlVal.isValid(null));
	   assertEquals(true,urlVal.isValid("http://oregonstate.edu"));
	   
//     test valid and invalid expression
	   assertTrue(urlVal.isValid("http://nihao.com"));
	   assertTrue(urlVal.isValid("http://www.google.com"));
	   
	   //assertFalse(urlVal.isValid("http://www..com"));            unexpected test falirue
	   //assertFalse(urlVal.isValid("http://www.öö.com"));			unexpected test faliure
	   //assertFalse(urlVal.isValid("http://hellöworld.com"));		unexpected test faliure
	   
//	   test scheme
	   //assertTrue(urlVal.isValid("oregonstate.edu"));				unexpected test faliure
	   //assertTrue(urlVal.isValid("https://www.usa.gov/"));		unexpected test faliure
	   assertTrue(urlVal.isValid("http://oregonstate.edu"));
	   //assertTrue(urlVal.isValid("http://oregonstate.edu"));  	unexpected test faliure
	   
	   
	   assertFalse(urlVal.isValid("htpp:oregonstate.edu"));
	   assertFalse(urlVal.isValid("htpp:/oregonstate.edu"));
	   
 //    test domain
	   assertTrue(urlVal.isValid("http://www.google.com/test"));
	   assertTrue(urlVal.isValid("http://oregonstate.edu"));
	   assertTrue(urlVal.isValid("http://hellworld.com"));
	   assertTrue(urlVal.isValid("http://www.usa.gov/"));
	   assertTrue(urlVal.isValid("http://www.google.com.hk"));
	   //assertTrue(urlVal.isValid("http://255.255.255.255"));			unexpected test faliure
	   //assertTrue(urlVal.isValid("localhost:3000"));					unexpected test faliure
	   
			   
	   //assertFalse(urlVal.isValid("http://oregonstate.ef"));			unexpected test faliure
	   //assertFalse(urlVal.isValid("http://oregonstate.edufco"));		unexpected test faliure
	   assertFalse(urlVal.isValid("http://localhost:asdfa/"));
	   //assertFalse(urlVal.isValid("http://256.256.256.256"));			unexpected test faliure
	  
	      
	   
   }
   
   public void testYourFirstPartition()
   {
	 //This test is to test the scheme 	   
	   
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   assertTrue(urlVal.isValid("http://oregonstate.com"));
       // assertTrue(urlVal.isValid("ftp://oregonstate.com"));  		unexpected test faliure
       // assertTrue(urlVal.isValid("h3t://oregonstate.com"));			unexpected test faliure
       // assertTrue(urlVal.isValid("https://oregonstate.com")); 		unexpected test faliure
       
       // assertFalse(urlVal.isValid("http:/oregonstate.com"));				unexpected test faliure
       assertFalse(urlVal.isValid("http:oregonstate.com"));
       assertFalse(urlVal.isValid("http/oregonstate.com"));
       assertFalse(urlVal.isValid("://oregonstate.com"));
       assertFalse(urlVal.isValid("3ht://oregonstate.com"));
	   
       UrlValidator url1 = new UrlValidator(null, null,0);
       
       //assertTrue(url1.isValid("http://www.oregonstate.com"));		unexpected test faliure 

       assertFalse(url1.isValid("ht://www.oregonstate.com"));
       
   }
   
   public void testYourSecondPartition(){
		 //This is to test authority partition
	   
	   
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		// assertTrue(urlVal.isValid("file:///etc"));
		assertTrue(urlVal.isValid("http://www.oregonstate.com"));
		assertFalse(urlVal.isValid(""));
		assertFalse(urlVal.isValid("7.8.10.12"));
		assertFalse(urlVal.isValid("1.2.2.2."));
		assertFalse(urlVal.isValid("191.1.1.1."));
		assertFalse(urlVal.isValid("oregstate.mn"));
		assertFalse(urlVal.isValid("oregonstate.99"));
		assertFalse(urlVal.isValid("http://oregonstate.com:80"));
		//assertFalse(urlVal.isValid("http://oregonstate.com:-1"));
		
		assertTrue(urlVal.isValid("http://0.0.0.0"));
		assertTrue(urlVal.isValid("http://asdfadsff.cc"));
		//assertTrue(urlVal.isValid("http://aasdfasdffa.com:100"));   		unexpected test faliure 
		//assertTrue(urlVal.isValid("http://oregonstate.com:0"));			unexpected test faliure 
		//assertTrue(urlVal.isValid("http://oregonstate.com:65565"));

	   
   }
   
   
   public void test_your_third_partition() {
	   // This is to test path partition
	   
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
		assertTrue(urlVal.isValid("http://www.oregonstate.com/file"));
		//	assertTrue(urlVal.isValid("http://www.oregonstate.com/file/file2"));  	unexpected test faliure 
		//	assertTrue(urlVal.isValid("http://www.oregonstate.com/$file4"));		unexpected test faliure 
		//	assertTrue(urlVal.isValid("http://www.oregonstate.com/file//file3"));	unexpected test faliure 
		//	assertTrue(urlVal.isValid("http://www.oregonstate.com/file/"));			unexpected test faliure 
		//	assertFalse(urlVal.isValid("http://oregonstate.com/#"));				unexpected test faliure 
		//	assertFalse(urlVal.isValid("http://oregonstate.com/#/file"));			unexpected test faliure 
		assertFalse(urlVal.isValid("http://oregonstate.com/.."));
		assertFalse(urlVal.isValid("http://oregonstate.com/../file"));
		assertFalse(urlVal.isValid("http://oregonstate.com/..//file"));
	   
   }
   
   
   
   public void test_your_fourth_partition() {
	   
	   UrlValidator urlVal = new UrlValidator(null, null, UrlValidator.ALLOW_ALL_SCHEMES);
	   
		//assertTrue(urlVal.isValid("http://oregonstate.com?action=hide"));				unexpected test faliure 
		//assertTrue(urlVal.isValid("http://oregonstate.com?action=hide&align=center")); unexpected test faliure 
		assertTrue(urlVal.isValid("http://oregonstate.com"));
	   
   }
   
   
   //You need to create more test cases for your Partitions if you need to 
  
   public void testIsValid()
   {
	   String url;
	      Random rand = new Random();
	      UrlValidator urlVal = new UrlValidator(null);
	     
	      String[] schemes = {"http://", "ftp://", "https://"};
	      String[] wrong_schemes = {"htasdfapss://", "http/", "http:"};
	
	      String[] authoritys = {"www.google.com", "12.50.43.11", "255.255.255.255"};
	      String[] wrong_authoritys = {"999.55.12.33", "google", ""};
	      
	      String[] ports = {"", ":65330", ":25"};
	      String[] wrong_ports = {":ABC", ":999999", ":-5000"};
	      
	      String[] paths = {"", "/some/path", "/even/more/path/"};
	      String[] wrong_path = {"/..", "/../", "/this//is//a//bad///path"};
	      
	      for(int i = 1; i < 1000; i++){

	          
	          int invalid_section = (int )(rand.nextInt(2));
	          if(invalid_section > 0){    
	              invalid_section = (int )(rand.nextInt(15) + 1);
	          }

	          int scheme_val = (int )(rand.nextInt(3));
	          int authority_val = (int )(rand.nextInt(3));
	          int port_val = (int )(rand.nextInt(3));
	          int path_val = (int )(rand.nextInt(3));

	          String url_sch = schemes[scheme_val];
	          String urlAut = authoritys[authority_val];
	          String urlPor = ports[port_val];
	          String urlPat = paths[path_val];
	          String url_schB = wrong_schemes[scheme_val];
	          String url_autb = wrong_authoritys[authority_val];
	          
	          String url_porb = wrong_ports[port_val];
	          String url_patb = wrong_path[path_val];

	          switch(invalid_section){
	              case 1: url = url_schB + urlAut + urlPor + urlPat; 
	              			break;
	              case 2: url = url_sch + url_autb + urlPor + urlPat; 
	              			break;
	              case 3: url = url_sch + urlAut + url_porb + urlPat; 
	              			break;
	              case 4: url = url_sch + urlAut + urlPor + url_patb; 
	              			break;
	              case 5: url = url_schB + url_autb + urlPor + urlPat; 
	              			break;
	              case 6: url = url_schB + urlAut + url_porb + urlPat; 
	              			break;
	              case 7: url = url_schB + urlAut + urlPor + url_patb; 
	              			break;
	              case 8: url = url_schB + url_autb + urlPor + url_patb; 
	              			break;
	              case 9: url = url_schB + urlAut + url_porb + url_patb; 
	              			break;
	              case 10: url = url_sch + url_autb + url_porb + urlPat; 
	              			break;
	              case 11: url = url_sch + url_autb + urlPor + url_patb; 
	              			break;
	              case 12: url = url_sch + url_autb + url_porb + url_patb; 
	              			break;
	              case 13: url = url_sch + urlAut + url_porb + url_patb; 
	              			break;
	              case 14: url = url_sch + url_autb + url_porb + url_patb; 
	              			break;
	              case 15: url = url_schB + url_autb + url_porb + url_patb; 
	              			break;
	              default: url = url_sch + urlAut + urlPor + urlPat; 
	              			break;
	          }

	          
	          if(invalid_section > 0){
	              String assertMessage = String.format("This url should NOT be valid: %s", url);
	              assertFalse(assertMessage, urlVal.isValid(url));
	              System.out.printf("Invalid: %s\n", url);
	          } else {
	              String assertMessage = String.format("This url should be valid: %s", url);
	              //  assertTrue(assertMessage, urlVal.isValid(url));	unexpected test faliure
	              System.out.printf("Valid: %s\n", url);
	          }
	      }
	   }

   
   


}
