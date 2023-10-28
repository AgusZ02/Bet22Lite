package businessLogic;
import java.util.Collection;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.ApustuAnitza;
import domain.Apustua;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.Sport;
import domain.Team;
import domain.Transaction;
import exceptions.EventFinished;
import exceptions.EventNotFinished;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;
	private static final String initialize = "initialize";
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(initialize)) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals(initialize));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(initialize)) {
			da.open(true);
			da.initializeDB();			
			da.close();

		}
		dbManager=da;
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public List<Event> getEvents(Date date)  {
		dbManager.open(false);
		List<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public List<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		List<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option initialize is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    @WebMethod	
    public Registered isLogin(String username, String password) {
    	dbManager.open(false);
    	Registered u = dbManager.isLogin(username, password);
    	dbManager.close();
    	return u;
		
	}
    @WebMethod	
    public boolean isRegister(String username) {
    	dbManager.open(false);
    	boolean u = dbManager.isRegister(username);
    	dbManager.close();
    	return u;
    }
    @WebMethod	
    public void storeRegistered(String username, String password, Integer bankAccount) {
    	dbManager.open(false);
    	dbManager.storeRegistered(username, password, bankAccount);
    	dbManager.close();
    }
    @WebMethod	
    public boolean gertaerakSortu(String description,Date eventDate, String sport) throws EventFinished{
    	if(new Date().compareTo(eventDate)>0) throw new EventFinished("Gertaera honen data dagoeneko pasa da");
    	dbManager.open(false);
    	boolean b = dbManager.gertaerakSortu(description, eventDate, sport);
    	dbManager.close();
    	return b;
    }
    @WebMethod	
    public void storeQuote(String forecast, Double quote, Question question) throws QuoteAlreadyExist {
    	dbManager.open(false);
    	dbManager.storeQuote(forecast, quote, question);
    	dbManager.close();
    }
    @WebMethod	
    public Collection<Question> findQuestion(Event event){
    	dbManager.open(false);
		Collection<Question> v = dbManager.findQuestion(event);
		dbManager.close();
		return v;
	}
    @WebMethod	
    public Collection<Quote> findQuote(Question question){
    	dbManager.open(false);
		Collection<Quote> v = dbManager.findQuote(question);
		dbManager.close();
		return v;
    }
    @WebMethod	
    public void diruaSartu(Registered u, Double dirua, String mota) {
    	Date data = new Date();
    	dbManager.open(false); 
    	dbManager.diruaSartu(u, dirua, data, mota);
    	dbManager.close();
    }
    @WebMethod	
    public boolean apustuaEgin(Registered u, List<Quote> q, Double balioa, Integer apustuaGalarazi) {
    	dbManager.open(false);
    	boolean b = dbManager.apustuaEgin(u, q, balioa, apustuaGalarazi);
    	dbManager.close();
    	return b; 
    }
    @WebMethod	
    public List<Apustua> findApustua(Registered u){
    	dbManager.open(false);
    	List<Apustua> a = dbManager.findApustua(u); 
    	dbManager.close();
    	return a; 
    }
    
    @WebMethod
    public List<ApustuAnitza> findApustuAnitza(Registered u){
    	dbManager.open(false);
    	List<ApustuAnitza> a = dbManager.findApustuAnitza(u); 
    	dbManager.close();
    	return a;
    }

    @WebMethod	
    public void apustuaEzabatu(Registered user1, ApustuAnitza apustua) {
    	dbManager.open(false);
    	dbManager.apustuaEzabatu(user1, apustua);
    	dbManager.close();
    }
    @WebMethod	
    public List<Transaction> findTransakzioak(Registered u){
    	dbManager.open(false);
    	List<Transaction> l = dbManager.findTransakzioak(u);
    	dbManager.close();
    	return l; 
    }
    @WebMethod	
    public void emaitzakIpini(Quote quote) throws EventNotFinished {
    	dbManager.open(false);
    	dbManager.emaitzakIpini(quote);
    	dbManager.close();
    }
    @WebMethod	
    public boolean gertaeraEzabatu(Event ev) {
    	dbManager.open(false);
    	boolean b = dbManager.gertaeraEzabatu(ev); 
    	dbManager.close();
    	return b; 
    }
    @WebMethod	
    public String saldoaBistaratu(Registered u) {
    	dbManager.open(false);
    	String dirua = dbManager.saldoaBistaratu(u);
    	dbManager.close();
    	return dirua;
    }
    
 
	
	@WebMethod
	public List<Registered> rankingLortu(){
		dbManager.open(false);
		List<Registered> rank = dbManager.rankingLortu();
		dbManager.close();
		return rank;
	}
	
	@WebMethod
	public List<Event> getEventsAll(){
		dbManager.open(false);
		List<Event> ev = dbManager.getEventsAll();
		dbManager.close();
		return ev;
	}
	
	@WebMethod
	public boolean gertaerakKopiatu(Event e, Date date) {
		dbManager.open(false);
		Boolean b=dbManager.gertaerakKopiatu(e, date);
		dbManager.close();
		return b;
	}
	
	@WebMethod
	public boolean jarraitu(Registered jabea, Registered jarraitua, Double limit) {
		dbManager.open(false);
		Boolean b=dbManager.jarraitu(jabea, jarraitua, limit);
		dbManager.close();
		return b;
	}
	
	
	@WebMethod
	public Sport popularrenaLortu() {
		dbManager.open(false);
		Sport s=dbManager.popularrenaLortu();
		dbManager.close();
		return s;
	}
	
	@WebMethod
	public void ezJarraituTaldea(Registered u) {
		dbManager.open(false);
		dbManager.ezJarraituTaldea(u);
		dbManager.close();
	}
	
	@WebMethod
	public List<Team> getAllTeams(){
		dbManager.open(false);
		List<Team> s=dbManager.getAllTeams();
		dbManager.close();
		return s;
	}
	
	@WebMethod
	public void jarraituTaldea(Registered u, Team t) {
		dbManager.open(false);
		dbManager.jarraituTaldea(u, t);
		dbManager.close();
	}
	
	@WebMethod
	public Registered findUser(Registered user) {
		dbManager.open(false);
		Registered u= dbManager.findUser(user);
		dbManager.close();
		return u;
	}
	
	@WebMethod
	public List<Event> getEventsTeam(Team t){
		dbManager.open(false);
		List<Event> ev= dbManager.getEventsTeam(t);
		dbManager.close();
		return ev;
	}
	
	
	@WebMethod
	public Event findEvent(Quote q) {
		dbManager.open(false);
		Event ev = dbManager.findEvent(q);
		dbManager.close();
		return ev;
	}
	
	@WebMethod
	public Question findQuestionFromQuote(Quote q) {
		dbManager.open(false);
		Question ev = dbManager.findQuestionFromQuote(q);
		dbManager.close();
		return ev;
	}
	
	@WebMethod
	public Event findEventFromApustua(Apustua q) {
		dbManager.open(false);
		Event ev = dbManager.findEventFromApustua(q);
		dbManager.close();
		return ev;
	}
	
	
	@WebMethod
	public Team findTeam(Registered u) {
		dbManager.open(false);
		Team team = dbManager.findTeam(u);
		dbManager.close();
		return team;
	}
	
	public Sport findSport(Event q) {
		dbManager.open(false);
		Sport team = dbManager.findSport(q);
		dbManager.close();
		return team;
	}
}

