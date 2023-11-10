import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.Factory;
import domain.Event;
import domain.ExtendedIterator;

public class EventIteratorTest {
    
    @Test
    public void testDecreciente(){
        //	obtener el	objeto Facade	local
        int isLocal =	1;
        SimpleDateFormat sdf =	new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            BLFacade blFacade = new Factory().create(isLocal);
            date =	sdf.parse("17/12/2023"); //	17	del	mes	que	viene
            ExtendedIterator<Event>	i =	blFacade.getEventsIterator(date);
            List<Event> events = blFacade.getEvents(date);
            int index = events.size();
            Event e;
            System.out.println("RECORRIDO	HACIA	ATRÁS");
            i.goLast();	//	Hacia atrás
            String s = "";
            while (i.hasPrevious())	{
                index--;
                e =	i.previous();
                assertEquals(e, events.get(index));
                System.out.println(e.toString());
            }
            assertEquals(0, index);
           
        } catch (ParseException	e1)	{
            System.out.println("Problems	with	date??	" +	"17/12/2020");
        } catch (MalformedURLException e2){
            System.out.println("Problems	with	WS??");
        }
    }

    @Test public void testCreciente(){
        int isLocal =	1;
        
        
        SimpleDateFormat sdf =	new SimpleDateFormat("dd/MM/yyyy");
        Date date;
        try {
            BLFacade blFacade = new Factory().create(isLocal);
            date =	sdf.parse("17/12/2023"); //	17	del	mes	que	viene
            ExtendedIterator<Event>	i =	blFacade.getEventsIterator(date);
            List<Event> events = blFacade.getEvents(date);
            int index = 0;
            Event e;
            System.out.println("RECORRIDO	HACIA	ADELANTE");
            i.goFirst();	//	Hacia adelante
            while (i.hasNext())	{
                e =	i.next();
                assertEquals(e, events.get(index));
                System.out.println(e.toString());
                index++;
            }
            assertEquals(events.size(), index);
        } catch (ParseException	e1)	{
                System.out.println("Problems	with	date??	" +	"17/12/2020");
        } catch (MalformedURLException e2){
            System.out.println("Problems	with	WS??");
        }
    }
}
