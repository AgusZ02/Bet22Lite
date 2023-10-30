import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class GertaerakSortuDAB {
		//sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();
		 static TestFacadeImplementation bl = new TestFacadeImplementation();
		 
		 @Test
			//sut.createQuestion:  Sport wrong, needed to be false. 
			public void test1() {
				//define paramaters
				String deporte="fusbol";
				String des="Atletico-Athletic";
				boolean resp;
				String fecha= "01/11/2023";
				
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
				Date eventDate=null;
				
				
				try {
					eventDate = sdf.parse(fecha);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
				
				
				try {
					//invoke System Under Test (sut)  
					sut.open(true);
					resp=sut.gertaerakSortu(des, eventDate, deporte);
					sut.close();
					
					//verify the results
					assertFalse(resp);
					//Event evento;
					//a=evento.getDescription();
					//the event is not in the DB
					testDA.open();
					boolean a = testDA.exiteEvento(eventDate, des);
					testDA.close();
					assertFalse(a);
				}
				catch (Exception e) {
					fail();
				
				} finally {
					//Remove the created objects in the database (cascade removing)   
					testDA.open();
			        boolean b=testDA.eliminateEvent(eventDate, des);
			        testDA.close();
				    System.out.println("Finally t1"+b); 
				}
				          
			}
		 
			
				
				@Test
			//sut.createQuestion:  insert the event in DB. There are not more events on that date
				public void test2() {
						
					String description = "Atletico-Athletic";
					String sport = "Futbol";
					boolean resp;
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date eventDate=null;
					String fecha= "17/11/2023";

					
					try {
						eventDate = sdf.parse(fecha);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					try {
						eventDate = sdf.parse("30/10/2022");
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {	
						
						//invoke System Under Test (sut)
						sut.open(true);
						resp = sut.gertaerakSortu(description, eventDate, sport);
						sut.close();
						
						//verify the results
						assertTrue(resp);
						testDA.open();
						Event ev = testDA.getEvent(eventDate, description);
						testDA.close();			
						assertEquals(ev.getDescription(),description);
						assertEquals(ev.getEventDate(),eventDate);
						assertEquals(ev.getSport().getIzena(),sport);
						
						//event IS in database
						testDA.open();
						boolean exists = testDA.exiteEvento(eventDate, description);
						testDA.close();
						assertTrue(exists);
						
					} catch(Exception e) {
						fail();
					} finally {
						//Remove the created objects in the database (cascade removing)
						testDA.open();
				       // boolean b=testDA.removeEvent(ev);
				        testDA.close();
				        System.out.println("Finally ");//+b);
					}
				}
			@Test
			//sut.createQuestion:  trying to insert two equal events
			public void test3() {
					
					//define paramaters
					String deporte="Futbol";
					String des="Atletico-Athletic";
				//	boolean resp;
					boolean resp2;
					String fecha= "17/11/2023";
					
					SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
					Date eventDate=null;
					
					
					
					try {
						eventDate = sdf.parse(fecha);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					//Event e = bl.addEventWithQuestion(des, eventDate, "pregunta", 1);
					try {	
					//invoke sut two times 
					sut.open(true);
					
					Event eventoDB=testDA.crearEvento(des, eventDate); 
					resp2=sut.gertaerakSortu(des, eventDate, deporte);//tiene que dar f
					sut.close();
					
					
					//verify the results
				//	assertTrue(resp);
					assertFalse(resp2);
					
				// 	testDA.open();
					//Event ev=testDA.getEvent(eventDate, des);
				//	testDA.close();
					
					assertEquals(eventoDB.getDescription(), des);
					assertEquals(eventoDB.getEventDate(), eventDate);
					//assertEquals(eventoDB.getSport().getIzena(), deporte);
					
					//check if the first event is in DB
	
					testDA.open();
					boolean existe = testDA.exiteEvento(eventDate, des);
					testDA.close();
				
					assertTrue(existe);
				} catch (Exception ex) {
					fail();
				
				} finally {
					
					//Remove the created objects in the database (cascade removing)   
					testDA.open();
			          boolean b=testDA.eliminateEvent(eventDate, des);
			          testDA.close();
			           System.out.println("Finally t3"+b);
					
				}
				
			}	
				   
				   
}