import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.Vector;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import exceptions.EventNotFinished;
import exceptions.QuoteAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class EmaitzakIpiniDAW {
    //sut:system under test
	static DataAccess sut=new DataAccess();
	 
    //additional operations needed to execute the test 
    static TestDataAccess testDA=new TestDataAccess();
    static TestFacadeImplementation bl = new TestFacadeImplementation();

   @Test
    public void test1(){
        Date d = new Date(2024, 11, 12);
        
        Event e = bl.addEventWithQuestion("test", d, "testq", 1); //crea un evento test y le asigna una pregunta testq en la BD
        Quote q = null;
        try{
            q =sut.storeQuote("test", 2.0, e.getQuestions().get(0));
            sut.emaitzakIpini(q);
            fail("Debería dar error");
        } catch(QuoteAlreadyExist ex){
            fail();
        } catch(EventNotFinished ex1){
            
            assertTrue(true);
        } finally{
            assertTrue(bl.removeEvent(e));
        } 
    }
    
    @Test
    public void test2(){ //3F,10T, 11F,18T,22F
        double valor = 2.2;
        Event e = bl.addEventWithQuestion("testEvent", new Date(), "testQuestion", 2);
        Quote q1 = bl.addQuotesTo(e.getQuestions().get(0), 2.0, "testQuote1");
        Quote q2 = bl.addQuotesTo(e.getQuestions().get(0), 3.0, "testQuote2");
        Quote q3 = bl.addQuotesTo(e.getQuestions().get(0), 4.0, "testQuote3");
        Registered u = bl.addUser("testUser");
        Apustua apu = bl.addApuestaTo(q1, u, valor);
        
        //Comprueba que todos los objetos se han creado
        assertNotNull(e);
        assertNotNull(q1);
        assertNotNull(u);
        assertNotNull(apu);
        try {
            sut.emaitzakIpini(q1);
            // Busca la pregunta desde la base de datos DataAccess original
            Question f = sut.findQuestionFromQuote(q1);
            assertEquals(q1.getForecast(), f.getResult());
        } catch (Exception ex) {
            fail();
        }finally{
            //Borra el usuario y el evento creado (y todo lo demás en cascada)
            assertTrue(bl.removeEvent(e));
            assertTrue(bl.removeUser(u.getUsername()));
        }
        
    }

    @Test
    public void test3(){ //3F,10T,11F,18F
        double valor = 2.2;
        Event e = bl.addEventWithQuestion("testEvent", new Date(), "testQuestion", 2);
        Quote q1 = bl.addQuotesTo(e.getQuestions().get(0), 2.0, "testQuote1");
        Quote q2 = bl.addQuotesTo(e.getQuestions().get(0), 3.0, "testQuote2");
        Quote q3 = bl.addQuotesTo(e.getQuestions().get(0), 4.0, "testQuote3");
        Registered u = bl.addUser("testUser");
        Apustua apu = bl.addApuestaTo(q1, u, valor);
        Apustua apu2 = bl.addApuestaTo(q2, u, valor);
        Apustua apu3 = bl.addApuestaTo(q3, u, valor);
        Apustua apu4 = bl.addApuestaTo(q1, u, valor);
        //Comprueba que todos los objetos se han creado
        assertNotNull(e);
        assertNotNull(q1);
        assertNotNull(u);
        assertNotNull(apu);

        try {
            sut.emaitzakIpini(q1);
            // Busca la pregunta desde la base de datos DataAccess original
            Question f = sut.findQuestionFromQuote(q1);
            // Comprueba que se ha cambiado su atributo de pronostico ganador
            assertEquals(q1.getForecast(), f.getResult());
            
            boolean same = false;
            for (Quote quote : f.getQuotes()) {
                if (quote.equals(q1)) {
                    same = true;     
                }
                for (Apustua apuesta : quote.getApustuak()) {
                    if (apuesta.getKuota().equals(q1)) {
                        assertEquals("irabazita",apuesta.getEgoera());
                        assertEquals("irabazita",apuesta.getApustuAnitza().getEgoera());
                        
                    } else {
                        assertEquals("galduta",apuesta.getEgoera());
                        assertEquals("galduta",apuesta.getApustuAnitza().getEgoera());
                    }
                }
                
            }
            assertTrue(same);
            
            
        } catch (Exception ex) {
            fail();
        }finally{
            //Borra el usuario y el evento creado (y todo lo demás en cascada)
            assertTrue(bl.removeEvent(e));
            assertTrue(bl.removeUser(u.getUsername()));

        }
        
    }


    @Test
    public void test4(){ //Sin apuestas
        double valor = 2.2;
        Event e = bl.addEventWithQuestion("testEvent", new Date(), "testQuestion", 2);
        Quote q1 = bl.addQuotesTo(e.getQuestions().get(0), 2.0, "testQuote1");
        Quote q2 = bl.addQuotesTo(e.getQuestions().get(0), 3.0, "testQuote2");
        Quote q3 = bl.addQuotesTo(e.getQuestions().get(0), 4.0, "testQuote3");
        Registered u = bl.addUser("testUser");
        //Comprueba que todos los objetos se han creado
        assertNotNull(e);
        assertNotNull(q1);
        assertNotNull(u);

        try {
            sut.emaitzakIpini(q1);
            // Busca la pregunta desde la base de datos DataAccess original
            Question f = sut.findQuestionFromQuote(q1);
            // Comprueba que se ha cambiado su atributo de pronostico ganador
            assertEquals(q1.getForecast(), f.getResult());
            
            boolean same = false;
            for (Quote quote : f.getQuotes()) {
                if (quote.equals(q1)) {
                    same = true;     
                }
                for (Apustua apuesta : quote.getApustuak()) {
                    if (apuesta.getKuota().equals(q1)) {
                        assertEquals("irabazita",apuesta.getEgoera());
                        assertEquals("irabazita",apuesta.getApustuAnitza().getEgoera());
                        
                    } else {
                        assertEquals("galduta",apuesta.getEgoera());
                        assertEquals("galduta",apuesta.getApustuAnitza().getEgoera());
                    }
                }
                
            }
            assertTrue(same);
            
            
        } catch (Exception ex) {
            fail();
        }finally{
            //Borra el usuario y el evento creado (y todo lo demás en cascada)
            assertTrue(bl.removeEvent(e));
            assertTrue(bl.removeUser(u.getUsername()));

        }
        
    }



}
