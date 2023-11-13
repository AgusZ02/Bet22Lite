
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.Test;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import test.businessLogic.TestFacadeImplementation;


public class EmaitzakIpiniTest2 {
    
    //sut:system under test
	static DataAccessEmaitzakIpini sut=new DataAccessEmaitzakIpini(true);
	 
	 //additional operations needed to execute the test 
	static TestFacadeImplementation testDA=new TestFacadeImplementation();


	@Test
	public void test1(){
		try {
			sut.EmaitzakIpini(null);
			fail("No debería de ejecutar con parámetro null");
		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void test2(){
		Event e =testDA.addEventWithQuestion("testEvent", new Date(), "testQuestion", 1);
		Quote q = testDA.addQuotesTo(e.getQuestions().get(0), 1, "testQuote");
		
		try {
			sut.open(false);
			sut.EmaitzakIpini(q);
			sut.findQuestionFromQuote(q);
			Question question = sut.findQuestionFromQuote(q);
			assertNotNull(question);
			assertEquals(e.getQuestions().get(0).getQuestionNumber(), question.getQuestionNumber());
			assertEquals(q.getForecast(),question.getResult());
			
		} catch (Exception ex) {
			fail("No debería de dar error");
		} finally{
			testDA.removeEvent(e);
			sut.close();
		}
	}
}
