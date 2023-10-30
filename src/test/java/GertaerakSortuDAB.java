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
						
<<<<<<< Updated upstream
							//define paramaters
							String deporte="Futbol";
							String des="Atletico-Athletic";
							boolean resp;
							String fecha= "17/11/2023";
													
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							Date eventDate=null;
							 
							try {
								eventDate= sdf.parse(fecha);
							} catch (ParseException e) {
								e.printStackTrace();
							}
							
						
=======
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
>>>>>>> Stashed changes
					try {	
							//invoke System Under Test (sut)  
							sut.open(true);
							//boolean a = testDA.exiteEvento(eventDate, des);
							
							resp =sut.gertaerakSortu(des, eventDate, deporte);
							sut.close();
							
							//verify the results
							//Event evento = testDA.getEvent(eventDate, des);
							assertFalse(resp);
							
							testDA.open();
							Event e=testDA.getEvent(eventDate, des);
							testDA.close();
						
							assertEquals(e.getDescription(),des);
							assertEquals(e.getEventDate(), eventDate);
							assertEquals(e.getSport().getIzena(), deporte);
							
							//the event is in DB
							
							testDA.open();
							boolean existe = testDA.exiteEvento(eventDate, des);
							testDA.close();
							
							assertTrue(existe);
							
					} catch (Exception e) {
						// TODO Auto-generated catch block
						//e.printStackTrace();
						fail();
						
						
					}	finally {
						//Remove the created objects in the database (cascade removing)   
						testDA.open();
				          boolean b=testDA.eliminateEvent(eventDate, des);
				          testDA.close();
				           System.out.println("Finally t2 "+b);
				           
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
					
<<<<<<< Updated upstream
					resp=sut.gertaerakSortu(des, eventDate, deporte);//tieme que dar f
=======
					Event eventoDB=testDA.crearEvento(des, eventDate); 
>>>>>>> Stashed changes
					resp2=sut.gertaerakSortu(des, eventDate, deporte);//tiene que dar f
					sut.close();
					
					
					//verify the results
					assertTrue(resp);
					assertTrue(resp2);
					
					testDA.open();
					Event ev=testDA.getEvent(eventDate, des);
					testDA.close();
					
					assertEquals(ev.getDescription(), des);
					assertEquals(ev.getEventDate(), eventDate);
					assertEquals(ev.getSport().getIzena(), deporte);
					
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
