package gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Sport;
import domain.Registered;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Color;
import java.awt.Font;

public class PopularGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;

	private JLabel lblPopular;
	private Registered user;
	private JFrame thisw;

	private JLabel lblSport;

	private JList<Event> list;
	private DefaultListModel<Event> eventLista = new DefaultListModel<>();

	private JScrollPane scrollBar;

	private JLabel lblEvent;
	private JLabel lblQuestion;
	private JList<Question> list1;
	private DefaultListModel<Question> questionLista = new DefaultListModel<>();
	private JScrollPane scrollBar1;
	private JLabel lblQuote;
	private JList<Quote> list2;
	private DefaultListModel<Quote> quoteLista = new DefaultListModel<>();
	private JScrollPane scrollBar2;

	private JButton btnClose;
	private JButton btnApostar;
	
	private Sport s;
	private JLabel lblErrorQuestion;
	private JLabel lblErrorQuote;

	public PopularGUI(Registered u) {
		user=u;
		thisw=this;
		getContentPane().setLayout(null);
		this.setSize(new Dimension(550, 820));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Popular"));
		
		s=businessLogic.popularrenaLortu();
		
		lblPopular = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SugerenciaPopular"));
		lblPopular.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblPopular.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPopular.setBounds(10, 43, 327, 58);
		getContentPane().add(lblPopular);
		
		lblSport = new JLabel();
		lblSport.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblSport.setHorizontalAlignment(SwingConstants.LEFT);
		lblSport.setBounds(347, 30, 179, 85);
		getContentPane().add(lblSport);
		
		lblSport.setText(s.getIzena());
		if(s.getIzena().equals("Futbol")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\futbol.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblSport.setIcon(imageIcon);
		}else if(s.getIzena().equals("Baloncesto")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\basker.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblSport.setIcon(imageIcon);
		}else if(s.getIzena().equals("Tennis")) {
			ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\tennis.png"); // load the image to a imageIcon
		    Image image = imageIcon.getImage(); // transform it 
		    Image newimg = image.getScaledInstance(40, 40, Image.SCALE_SMOOTH); // scale it the smooth way  
		    imageIcon = new ImageIcon(newimg);
	        lblSport.setIcon(imageIcon);
		}
		list = new JList<>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(false);
				questionLista.removeAllElements();
				for(domain.Question question : businessLogic.findQuestion((Event)list.getSelectedValue()))
					questionLista.addElement(question); 
				if(questionLista.size()==0) {
					lblErrorQuestion.setVisible(true);
					lblErrorQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries"));
				}else {
					lblErrorQuestion.setVisible(false);
					lblErrorQuote.setVisible(false);
				}
			}
		});
		list.setModel(eventLista);
		list.setBounds(10, 89, 1, 1);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(36, 154, 472, 130);
		getContentPane().add(scrollBar);
		
		lblEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
		lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEvent.setBounds(36, 126, 116, 18);
		getContentPane().add(lblEvent);
		
		lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestion.setBounds(36, 294, 95, 25);
		getContentPane().add(lblQuestion);
		
		list1 = new JList();
		list1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(false);
				quoteLista.removeAllElements();
				for(domain.Quote quote : businessLogic.findQuote((Question)list1.getSelectedValue()))
					quoteLista.addElement(quote);
				if(quoteLista.size()==0) {
					btnApostar.setEnabled(false);
					lblErrorQuote.setVisible(true);
					lblErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuote"));
				}else {
					btnApostar.setEnabled(true);
					lblErrorQuote.setVisible(false);
				}
				if(list2.getSelectedValue()==null) {
					btnApostar.setEnabled(false);
				}
			}
		});
		list1.setModel(questionLista);
		list1.setBounds(36, 191, 1, 1);
		getContentPane().add(list1);
		
		scrollBar1 = new JScrollPane(list1);
		scrollBar1.setBounds(36, 329, 472, 155);
		getContentPane().add(scrollBar1);
		
		lblQuote = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuote.setBounds(36, 494, 95, 25);
		getContentPane().add(lblQuote);
		
		list2 = new JList();
		list2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostar.setEnabled(true);
			}
		});
		list2.setModel(quoteLista);
		list2.setBounds(46, 315, 1, 1);
		getContentPane().add(list2);
		
		scrollBar2 = new JScrollPane(list2);
		scrollBar2.setBounds(36, 529, 472, 157);
		getContentPane().add(scrollBar2);
		
		eventLista.addAll(s.getEvents());
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
				JFrame a = new DestacadosGUI(user);
				a.setVisible(true);
			}
		});
		btnClose.setBounds(361, 711, 147, 47);
		getContentPane().add(btnClose);
		
		btnApostar = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApostar.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApostar.setEnabled(false);
		btnApostar.setBackground(Color.PINK);
		btnApostar.setForeground(Color.DARK_GRAY);
		btnApostar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a =new apustuaEginGUI(user, (Quote)list2.getSelectedValue());
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnApostar.setBounds(36, 711, 160, 47);
		getContentPane().add(btnApostar);
		
		lblErrorQuestion = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrorQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErrorQuestion.setBounds(141, 294, 367, 25);
		getContentPane().add(lblErrorQuestion);
		
		lblErrorQuote = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblErrorQuote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErrorQuote.setBounds(128, 494, 380, 24);
		getContentPane().add(lblErrorQuote);
	}
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
