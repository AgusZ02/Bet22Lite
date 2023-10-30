


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import configuration.ConfigXML;
import configuration.UtilDate;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Jarraitzailea;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessEmaitzakIpini  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccessEmaitzakIpini(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccessEmaitzakIpini()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
		   
		   Team team1= new Team("Atletico");
		   Team team2= new Team("Athletic");
		   Team team3= new Team("Eibar");
		   Team team4= new Team("Barcelona");
		   Team team5= new Team("Getafe");
		   Team team6= new Team("Celta");
		   Team team7= new Team("Alaves");
		   Team team8= new Team("Deportivo");
		   Team team9= new Team("Espanol");
		   Team team10= new Team("Villareal");
		   Team team11= new Team("Las_Palmas");
		   Team team12= new Team("Sevilla");
		   Team team13= new Team("Malaga");
		   Team team14= new Team("Valencia");
		   Team team15= new Team("Girona");
		   Team team16= new Team("Leganes");
		   Team team17= new Team("Real_Sociedad");
		   Team team18= new Team("Levante");
		   Team team19= new Team("Betis");
		   Team team20= new Team("Real_Madrid");
		   Team team21= new Team("LA_Lakers");
		   Team team22= new Team("Phoenix_Suns");
		   Team team23= new Team("Atlanta_Hawks");
		   Team team24= new Team("Houston_Rockets");
		   Team team25= new Team("Miami_Heat");
		   Team team26= new Team("Chicago_Bulls");
		   Team team27= new Team("Boston_Celtics");
		   Team team28= new Team("Memphis_Grizzlies");
		   Team team29= new Team("Nadal");
		   Team team30= new Team("Alcaraz");
		   Team team31= new Team("Djokovic");
		   Team team32= new Team("Federer");
		   
	    
			Event ev1=new Event(1, "Atletico-Athletic", UtilDate.newDate(year,month,17), team1, team2);
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17), team3, team4);
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17), team5, team6);
			Event ev4=new Event(4, "Alaves-Deportivo", UtilDate.newDate(year,month,17), team7, team8);
			Event ev5=new Event(5, "Espanol-Villareal", UtilDate.newDate(year,month,17), team9, team10);
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17), team11, team12);
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17), team13, team14);
			Event ev8=new Event(8, "Girona-Leganes", UtilDate.newDate(year,month,17), team15, team16);
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17), team17, team18);
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17), team19, team20);

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1), team1, team2);
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1), team3, team4);
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1), team5, team6);
			Event ev14=new Event(14, "Alaves-Deportivo", UtilDate.newDate(year,month,1), team7, team8);
			Event ev15=new Event(15, "Espanol-Villareal", UtilDate.newDate(year,month,1), team9, team10);
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1), team11, team12);
			

			Event ev17=new Event(17, "Malaga-Valencia", UtilDate.newDate(year,month+1,28), team13, team14);
			Event ev18=new Event(18, "Girona-Leganes", UtilDate.newDate(year,month+1,28), team15, team16);
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28), team17, team18);
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28), team19, team20);
			Event ev21=new Event(21, "Real Madrid-Barcelona", UtilDate.newDate(year, month-2, 21), team20, team4);
			
			Event ev22=new Event(22, "LA Lakers-Phoenix Suns", UtilDate.newDate(year,month,17), team21, team22);
			Event ev23=new Event(23, "Atlanta Hawks-Houston Rockets", UtilDate.newDate(year,month,17), team23, team24);
			Event ev24=new Event(24, "Miami Heat-Chicago Bulls", UtilDate.newDate(year,month,17), team25, team26);
			Event ev25=new Event(25, "Boston Celtics-Memphis Grizzlies", UtilDate.newDate(year,month,1), team27, team28);
			
			Event ev26=new Event(26, "Nadal-Alcaraz", UtilDate.newDate(year,month,1), team29, team30);
			Event ev27=new Event(27, "Djokovic-Federer", UtilDate.newDate(year,month,17), team31, team32);
			
			Sport sp1=new Sport("Futbol");
			Sport sp2=new Sport("Baloncesto");
			Sport sp3=new Sport("Tennis");
			
			sp1.addEvent(ev1);
			sp1.addEvent(ev2);
			sp1.addEvent(ev3);
			sp1.addEvent(ev4);
			sp1.addEvent(ev5);
			sp1.addEvent(ev6);
			sp1.addEvent(ev7);
			sp1.addEvent(ev8);
			sp1.addEvent(ev9);
			sp1.addEvent(ev10);
			sp1.addEvent(ev11);
			sp1.addEvent(ev12);
			sp1.addEvent(ev13);
			sp1.addEvent(ev14);
			sp1.addEvent(ev15);
			sp1.addEvent(ev16);
			sp1.addEvent(ev17);
			sp1.addEvent(ev18);
			sp1.addEvent(ev19);
			sp1.addEvent(ev20);
			sp1.addEvent(ev21);
			sp2.addEvent(ev22);
			sp2.addEvent(ev23);
			sp2.addEvent(ev24);
			sp2.addEvent(ev25);
			sp3.addEvent(ev26);
			sp3.addEvent(ev27);
			
			ev1.setSport(sp1);
			ev2.setSport(sp1);
			ev3.setSport(sp1);
			ev4.setSport(sp1);
			ev5.setSport(sp1);
			ev6.setSport(sp1);
			ev7.setSport(sp1);
			ev8.setSport(sp1);
			ev9.setSport(sp1);
			ev10.setSport(sp1);
			ev11.setSport(sp1);
			ev12.setSport(sp1);
			ev13.setSport(sp1);
			ev14.setSport(sp1);
			ev15.setSport(sp1);
			ev16.setSport(sp1);
			ev17.setSport(sp1);
			ev18.setSport(sp1);
			ev19.setSport(sp1);
			ev20.setSport(sp1);
			ev21.setSport(sp1);
			ev22.setSport(sp2);
			ev23.setSport(sp2);
			ev24.setSport(sp2);
			ev25.setSport(sp2);
			ev26.setSport(sp3);
			ev27.setSport(sp3);
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
			Question q7;
			Question q8;
			Question q9;
			Question q10;
			Question q11;
			
			Registered ad1=new Registered("admin", "123", 1234,true);
			Registered reg1 =new Registered("registered", "123", 1234);
			Registered reg2 = new Registered("andrea", "123", 1111);
			Registered reg3 = new Registered("markel", "123", 1111);
			Registered reg4 = new Registered("mikel", "123", 1111);
									
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
				
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
				
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
				
			}
			q7 = ev21.addQuestion("Emaitza?", 1);
			q8 = ev21.addQuestion("Emaitza?", 1);
			q9 = ev22.addQuestion("Irabazlea?", 1.5);
			q10 = ev26.addQuestion("Irabazlea?", 1.5);
			q11 = ev27.addQuestion("Zeinek irabaziko du lehenengo set-a", 3.0);
			
			
			Quote quote1 = q1.addQuote(1.3, "1", q1); 
			Quote quote2 = q2.addQuote(2.5, "X", q2); 
			Quote quote3 = q3.addQuote(100.0, "2", q3);
			Quote quote4 = q7.addQuote(2.5, "2", q7);
			Quote quote5 = q8.addQuote(2.0, "1", q8);
			Quote quote6 = q7.addQuote(5.0, "1", q7);
			Quote quote7 = q9.addQuote(3.0, "1", q9);
			Quote quote8 = q9.addQuote(1.5, "2", q9);
			Quote quote9 = q10.addQuote(2.5, "1", q10);
			Quote quote10 = q10.addQuote(1.6, "2", q10);
			Quote quote11 =q11.addQuote(2.3, "1", q11);
			Quote quote12 =q11.addQuote(1.5, "2", q11);
			
			ApustuAnitza apA1 = new ApustuAnitza(reg1, 5.0);
			ApustuAnitza apA3 = new ApustuAnitza(reg4, 34.5);
			ApustuAnitza apA4 = new ApustuAnitza(reg2, 14.5);
			ApustuAnitza apA5 = new ApustuAnitza(reg3, 10.0);
			ApustuAnitza apA6 = new ApustuAnitza(reg4, 4.5);
			ApustuAnitza apA7 = new ApustuAnitza(reg1, 6.0);
			ApustuAnitza apA8 = new ApustuAnitza(reg1, 2.5);
			ApustuAnitza apA9 = new ApustuAnitza(reg2, 4.0);
			ApustuAnitza apA10= new ApustuAnitza(reg2, 7.5);
			ApustuAnitza apA11= new ApustuAnitza(reg3, 4.5);
			ApustuAnitza apA12= new ApustuAnitza(reg3, 6.5);
			ApustuAnitza apA13= new ApustuAnitza(reg2, 6.5);
		
			Apustua ap1 = new Apustua(apA1, quote4);
			Apustua ap2 = new Apustua(apA1, quote1);
			apA1.addApustua(ap2);
			apA1.addApustua(ap1);
			
			Apustua ap3 = new Apustua(apA3, quote4);
			apA3.addApustua(ap3);
			
			Apustua ap4 = new Apustua(apA4, quote4);
			apA4.addApustua(ap4);
			
			Apustua ap5 = new Apustua(apA5, quote4);
			apA5.addApustua(ap5);
			
			Apustua ap6 = new Apustua(apA6, quote1);
			Apustua ap13 = new Apustua(apA6, quote2);
			apA6.addApustua(ap6);
			apA6.addApustua(ap13);
			
			Apustua ap7 = new Apustua(apA7, quote7);
			apA7.addApustua(ap7);
			
			Apustua ap8 = new Apustua(apA8, quote9);
			apA8.addApustua(ap8);
			
			Apustua ap9 = new Apustua(apA9, quote8);
			apA9.addApustua(ap9);
			
			Apustua ap10= new Apustua(apA10, quote10);
			apA10.addApustua(ap10);
			
			Apustua ap11= new Apustua(apA11, quote10);
			apA11.addApustua(ap11);
			
			Apustua ap12= new Apustua(apA12, quote11);
			apA12.addApustua(ap12);
			
			Apustua ap14= new Apustua(apA13, quote8);
			apA13.addApustua(ap14);
			
			quote4.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			quote1.addApustua(ap1);
			ap1.eguneratuApustuKant(sp1);
			
			quote4.addApustua(ap3);
			ap3.eguneratuApustuKant(sp1);
			
			quote4.addApustua(ap4);
			ap4.eguneratuApustuKant(sp1);
					
			quote4.addApustua(ap5);
			ap5.eguneratuApustuKant(sp1);
						
			quote1.addApustua(ap6);
			quote2.addApustua(ap6);
			ap6.eguneratuApustuKant(sp1);
			ap6.eguneratuApustuKant(sp1);
			
			quote7.addApustua(ap7);
			ap7.eguneratuApustuKant(sp2);
			quote8.addApustua(ap9);
			ap9.eguneratuApustuKant(sp2);
			
			quote9.addApustua(ap8);
			ap8.eguneratuApustuKant(sp3);
			quote10.addApustua(ap10);
			ap10.eguneratuApustuKant(sp3);
			
			quote10.addApustua(ap11);
			ap11.eguneratuApustuKant(sp3);
			quote11.addApustua(ap12);
			ap12.eguneratuApustuKant(sp3);
			
			quote8.addApustua(ap14);
			ap14.eguneratuApustuKant(sp2);
			
			reg1.addApustuAnitza(apA1);
			
			
			
			
			
			
			
			
			Transaction t1 = new Transaction(reg1, apA1.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t3 = new Transaction(reg2, apA4.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t4 = new Transaction(reg3, apA5.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t5 = new Transaction(reg4, apA3.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t6 = new Transaction(reg4, apA6.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t7 = new Transaction(reg1, apA7.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t8 = new Transaction(reg1, apA8.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t9 = new Transaction(reg2, apA9.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t10 = new Transaction(reg2, apA10.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t11 = new Transaction(reg3, apA11.getBalioa(), new Date(), "ApustuaEgin");
			Transaction t12 = new Transaction(reg3, apA12.getBalioa(), new Date(), "ApustuaEgin");
			
			reg1.addTransaction(t1);
			reg2.addTransaction(t3);
			reg3.addTransaction(t4);
			reg4.addTransaction(t5);
			reg4.addTransaction(t6);
			reg1.addTransaction(t7);
			reg1.addTransaction(t8);
			reg2.addTransaction(t9);
			reg2.addTransaction(t10);
			reg3.addTransaction(t11);
			reg3.addTransaction(t12);
			
			
			
			
			
			team1.addEvent(ev1);
			team2.addEvent(ev1);
			team3.addEvent(ev2);
			team4.addEvent(ev2);
			team5.addEvent(ev3);
			team6.addEvent(ev3);
			team7.addEvent(ev4);
			team8.addEvent(ev4);
			team9.addEvent(ev5);
			team10.addEvent(ev5);
			team11.addEvent(ev6);
			team12.addEvent(ev6);
			team13.addEvent(ev7);
			team14.addEvent(ev7);
			team15.addEvent(ev8);
			team16.addEvent(ev8);
			team17.addEvent(ev9);
			team18.addEvent(ev9);
			team19.addEvent(ev10);
			team20.addEvent(ev10);
			team1.addEvent(ev11);
			team2.addEvent(ev11);
			team3.addEvent(ev12);
			team4.addEvent(ev12);
			team5.addEvent(ev13);
			team6.addEvent(ev13);
			team7.addEvent(ev14);
			team8.addEvent(ev14);
			team9.addEvent(ev15);
			team10.addEvent(ev15);
			team11.addEvent(ev16);
			team12.addEvent(ev16);
			team13.addEvent(ev17);
			team14.addEvent(ev17);
			team15.addEvent(ev18);
			team16.addEvent(ev18);
			team17.addEvent(ev19);
			team18.addEvent(ev19);
			team19.addEvent(ev20);
			team20.addEvent(ev20);
			team20.addEvent(ev21);
			team4.addEvent(ev21);
			team21.addEvent(ev22);
			team22.addEvent(ev22);
			team23.addEvent(ev23);
			team24.addEvent(ev23);
			team25.addEvent(ev24);
			team26.addEvent(ev24);
			team27.addEvent(ev25);
			team28.addEvent(ev25);
			team29.addEvent(ev26);
			team30.addEvent(ev26);
			team31.addEvent(ev27);
			team32.addEvent(ev27);
			
			
			db.persist(team1);
			db.persist(team2);
			db.persist(team3);
			db.persist(team4);
			db.persist(team5);
			db.persist(team6);
			db.persist(team7);
			db.persist(team8);
			db.persist(team9);
			db.persist(team10);
			db.persist(team11);
			db.persist(team12);
			db.persist(team13);
			db.persist(team14);
			db.persist(team15);
			db.persist(team16);
			db.persist(team17);
			db.persist(team18);
			db.persist(team19);
			db.persist(team20);
			db.persist(team21);
			db.persist(team22);
			db.persist(team23);
			db.persist(team24);
			db.persist(team25);
			db.persist(team26);
			db.persist(team27);
			db.persist(team28);
			db.persist(team29);
			db.persist(team30);
			db.persist(team31);
			db.persist(team32);
			
			db.persist(apA1);
			db.persist(apA3);
			db.persist(apA4);
			db.persist(apA5);
			db.persist(apA6);
			db.persist(apA7);
			db.persist(apA8);
			db.persist(apA9);
			db.persist(apA10);
			db.persist(apA11);
			db.persist(apA12);
			db.persist(apA13);
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
			db.persist(q7);
			db.persist(q8);
			db.persist(q9);
			db.persist(q10);
			db.persist(q11);
			
			
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);		
			db.persist(ev21);
			db.persist(ev22);
			db.persist(ev23);
			db.persist(ev24);
			db.persist(ev25);
			db.persist(ev26);
			db.persist(ev27);
			
			db.persist(sp1);
			db.persist(sp2);
			db.persist(sp3);
			
			db.persist(ad1);
			db.persist(reg1);
			db.persist(reg2);
			db.persist(reg3);
			db.persist(reg4);
			
			db.persist(quote3);
			db.persist(quote2); 
			db.persist(quote1); 
			db.persist(quote4);
			db.persist(quote5);
			db.persist(quote6);
			db.persist(quote7);
			db.persist(quote8);
			db.persist(quote9);
			db.persist(quote10);
			db.persist(quote11);
			db.persist(quote12);
			
			db.persist(ap1);
			db.persist(ap2);
			db.persist(ap3);
			db.persist(ap4);
			db.persist(ap5);
			db.persist(ap6);
			db.persist(ap7);
			db.persist(ap8);
			db.persist(ap9);
			db.persist(ap10);
			db.persist(ap11);
			db.persist(ap12);
			db.persist(ap13);
			db.persist(ap14);
			
			db.persist(t1);
			db.persist(t3);
			db.persist(t4);
			db.persist(t5);
			db.persist(t6);
			db.persist(t7);
			db.persist(t8);
			db.persist(t9);
			db.persist(t10);
			db.persist(t11);
			db.persist(t12);
			
			
			
			db.getTransaction().commit();
			
			this.DiruaSartu(reg1, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg2, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg3, 50.0, new Date(), "DiruaSartu");
			this.DiruaSartu(reg4, 50.0, new Date(), "DiruaSartu");
			
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void EmaitzakIpini(Quote quote) throws EventNotFinished{ //Agustin
		
		Quote q = db.find(Quote.class, quote); //1
		String result = q.getForecast(); //2
		
		if(new Date().compareTo(q.getQuestion().getEvent().getEventDate())<0) //3
			throw new EventNotFinished(); //4

		Vector<Apustua> listApustuak = q.getApustuak(); //5
		db.getTransaction().begin(); //6
		Question que = q.getQuestion();  //7
		Question question = db.find(Question.class, que); //8
		question.setResult(result); //9
		for(Quote quo: question.getQuotes()) { //10
			for(Apustua apu: quo.getApustuak()) { //11
				
				Boolean b=apu.galdutaMarkatu(quo); //12
				if(b) { //13
					apu.getApustuAnitza().setEgoera("galduta"); //14
				}else { //15
					apu.setEgoera("irabazita"); //16
				}
			}
		}
		db.getTransaction().commit(); //17
		for(Apustua a : listApustuak) { //18
			db.getTransaction().begin(); //19
			Boolean bool=a.getApustuAnitza().irabazitaMarkatu(); //20
			db.getTransaction().commit(); //21
			if(bool) { //22
				this.ApustuaIrabazi(a.getApustuAnitza()); //23
			}
		}
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public void DiruaSartu(Registered u, Double dirua, Date data, String mota) {
		Registered user = (Registered) db.find(Registered.class, u.getUsername()); 
		db.getTransaction().begin();
		Transaction t = new Transaction(user, dirua, data, mota); 
		System.out.println(t.getMota());
		user.addTransaction(t);
		user.updateDiruKontua(dirua);
		db.persist(t);
		db.getTransaction().commit();
	}
	public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}

	public void ApustuaIrabazi(ApustuAnitza apustua) {
		ApustuAnitza apustuAnitza = db.find(ApustuAnitza.class, apustua.getApustuAnitzaNumber());
		Registered reg = (Registered) apustuAnitza.getUser();
		Registered r = (Registered) db.find(Registered.class, reg.getUsername());
		db.getTransaction().begin();
		apustuAnitza.setEgoera("irabazita");
		Double d=apustuAnitza.getBalioa();
		for(Apustua ap: apustuAnitza.getApustuak()) {
			d = d*ap.getKuota().getQuote();
		}
		r.updateDiruKontua(d);
		r.setIrabazitakoa(r.getIrabazitakoa()+d);
		r.setZenbat(r.getZenbat()+1);
		Transaction t = new Transaction(r, d, new Date(), "ApustuaIrabazi"); 
		db.persist(t);
		db.getTransaction().commit();
	}
	public Question findQuestionFromQuote(Quote q){
		Quote quo = db.find(Quote.class, q.getQuoteNumber());
		return quo.getQuestion(); 
	}
}