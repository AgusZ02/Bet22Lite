
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import exceptions.QuestionAlreadyExist;
import test.dataAccess.TestDataAccess;

public class CreateQuestionDAB {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	
	
	 @Test
	 //sut.createQuestion:  The event has NOT one question with a queryText. 
	 public void test1() {
	 	try {		
	 		//define paramaters
	 		String eventText="event1";
	 		String queryText="query1";
	 		Float betMinimum=new Float(2);		
	 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 		Date oneDate=null;;
	 		try {
	 			oneDate = sdf.parse("05/10/2022");
	 		} catch (ParseException e) {
	 			e.printStackTrace();
	 		}			
	 		//configure the state of the system (create object in the dabatase)
	 		testDA.open();
	 		ev = testDA.addEventWithQuestion(eventText,oneDate,"query2", betMinimum);
	 		testDA.close();					
	 		//invoke System Under Test (sut)  
	 		Question q=sut.createQuestion(ev, queryText, betMinimum);				
	 		//verify the results
	 		assertNotNull(q);
	 		assertEquals(q.getQuestion(),queryText);
	 		assertEquals(q.getBetMinimum(),betMinimum,0);		
	 		//q datubasean dago
	 		testDA.open();
	 		boolean exist = testDA.existQuestion(ev,q);			
	 		assertTrue(exist);
	 		testDA.close();		
	 	   } catch (QuestionAlreadyExist e) {
	 		// if the program goes to this point fail  
	 		fail();
	 		} finally {
	 			  //Remove the created objects in the database (cascade removing)   
	 			testDA.open();
	 	          boolean b=testDA.removeEvent(ev);
	 	          testDA.close();
	 	      //     System.out.println("Finally "+b);          
	 	        }
	 	   }
	 @Test
	 //sut.createQuestion:  The event is null. The test fail
	 	public void test2() {
	 		try {			
	 			//define paramaters
	 			String eventText="event1";
	 			String queryText="query1";
	 			Float betMinimum=new Float(2);			
	 			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 			Date oneDate=null;;
	 			try {
	 				oneDate = sdf.parse("05/10/2022");
	 			} catch (ParseException e) {
	 				e.printStackTrace();
	 			}				
	 			//invoke System Under Test (sut)  
	 			Question q=sut.createQuestion(null, queryText, betMinimum);						
	 			//verify the results
	 			assertNull(q);						
	 		   } catch (QuestionAlreadyExist e) {
	 			// if the program goes to this point fail  
	 			fail();
	 			} 
	 		   }
	 @Test
	 //sut.createQuestion:  The question is null. The test fail
	 public void test3() {
	 	try {		
	 		//define paramaters
	 		String eventText="event1";
	 		String queryText=null;
	 		Float betMinimum=new Float(2);		
	 		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	 		Date oneDate=null;;
	 		try {
	 			oneDate = sdf.parse("05/10/2022");
	 		} catch (ParseException e) {
	 			e.printStackTrace();
	 		}			
	 		//configure the state of the system (create object in the dabatase)
	 		testDA.open();
	 		ev = testDA.addEventWithQuestion(eventText,oneDate,"query2", betMinimum);
	 		testDA.close();					
	 		//invoke System Under Test (sut)  
	 		Question q=sut.createQuestion(ev, queryText, betMinimum);				
	 		//verify the results
	 		assertNull(q);				
	 		//q datubasean dago
	 		testDA.open();
	 		boolean exist = testDA.existQuestion(ev,q);			
	 		assertTrue(!exist);
	 		testDA.close();		
	 	   } catch (QuestionAlreadyExist e) {
	 		// if the program goes to this point fail  
	 		fail();
	 		} finally {
	 			  //Remove the created objects in the database (cascade removing)   
	 			testDA.open();
	 	          boolean b=testDA.removeEvent(ev);
	 	          testDA.close();
	 	      //     System.out.println("Finally "+b);          
	 	        }
	 	   }
}

