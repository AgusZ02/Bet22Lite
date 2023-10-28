import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.*;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.internal.matchers.Null;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Quote;
import exceptions.EventNotFinished;
import org.mockito.ArgumentCaptor;
public class EmaitzakIpiniBLBMTest {
    
   
    DataAccess daMock = Mockito.mock(DataAccess.class);
    BLFacade sut = new BLFacadeImplementation();
    

    @Test
    public void test1(){
        try {
            
            Mockito.doThrow(new IllegalArgumentException()).when(daMock).emaitzakIpini(null);
            sut.emaitzakIpini(null);
            fail("No debería de ejecutar con parámetro null");
        } catch (IllegalArgumentException e) {
            assertTrue(true);
        } catch (EventNotFinished e) {
            fail("Error incorrecto.");
        }
    }

    @Test
    public void test2(){
        Quote q = new Quote(2.0, "cuotaNoBD");
        try {
                Mockito.doThrow(new NullPointerException()).when(daMock).emaitzakIpini(q);
                ArgumentCaptor<Quote> argument = ArgumentCaptor.forClass(Quote.class);
                assertThrows(NullPointerException.class,
                ()->{
                    sut.emaitzakIpini(q);
                    Mockito.verify(daMock, Mockito.times(1)).emaitzakIpini(argument.capture());
                    assertEquals(argument.getValue().getForecast(), q.getForecast());
                    
                });
            } catch (EventNotFinished e) {
                assertTrue(true);
            }
    }
    @Test
    public void test3(){
        Quote q = new Quote(2.0, "cuotaEnBDFechaFutura");
        DataAccess spy = spy(new DataAccess());
        try {
                Mockito.doThrow(new EventNotFinished()).when(daMock).emaitzakIpini(q);
                ArgumentCaptor<Quote> argument = ArgumentCaptor.forClass(Quote.class);
                assertThrows(EventNotFinished.class,
                ()->{
                    sut.emaitzakIpini(q);
                    Mockito.verify(daMock, Mockito.times(1)).emaitzakIpini(argument.capture());
                    assertEquals(argument.getValue().getForecast(), q.getForecast());
                    
                });
            } catch (EventNotFinished e) {
                assertTrue(true);
            } 
    }

}
