package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import businessLogic.BLFacade;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.Font;

public class TaldeaApostatuGUI extends JFrame{
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;

	private JLabel lblTeam;
	private Registered user;
	private JFrame thisw;

	private JLabel lblEquipo;

	private JLabel lblEvent;
	private JList<Event> list;
	private DefaultListModel<Event> events = new DefaultListModel<>();
	private JScrollPane scrollBar;
	private JLabel lblQuestion;
	private JList<Question> list1;
	private DefaultListModel<Question> questions = new DefaultListModel<>();
	private JScrollPane scrollBar1;
	private DefaultListModel<Quote> quotes = new DefaultListModel<>();
	private JLabel lblQuotes;
	private JList<Quote> list2;
	private JScrollPane scrollBar2;
	private JButton btnClose;
	private JButton btnApostatu;

	private JLabel lblErrorQuestion;

	private JLabel lblErrorQuote;
	
	public TaldeaApostatuGUI(Registered u) {
		thisw=this;
		user=u;
		getContentPane().setLayout(null);
		
		this.setSize(new Dimension(550, 820));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("TaldeaApostatu"));
		
		lblTeam = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("TeamEvents"));
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTeam.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTeam.setOpaque(true);
		lblTeam.setBounds(10, 47, 313, 57);
		getContentPane().add(lblTeam);
		
		lblErrorQuote = new JLabel();
		lblErrorQuote.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblErrorQuestion = new JLabel();
		lblErrorQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		
		lblEquipo = new JLabel(businessLogic.findTeam(user).getIzena());
		lblEquipo.setFont(new Font("Tahoma", Font.PLAIN, 20));
		ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\Equipos\\"+businessLogic.findTeam(user).getIzena()+".png"); // load the image to a imageIcon
		Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH); // scale it the smooth way  
	    imageIcon = new ImageIcon(newimg);
        lblEquipo.setIcon(imageIcon);
		lblEquipo.setBounds(333, 47, 173, 57);
		getContentPane().add(lblEquipo);
		
		lblEvent = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
		lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblEvent.setBounds(31, 138, 80, 27);
		getContentPane().add(lblEvent);
		
		list = new JList<>();
		list.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostatu.setEnabled(false);
				questions.removeAllElements();
				for(domain.Question question : businessLogic.findQuestion(list.getSelectedValue()))
					questions.addElement(question); 
				if(questions.size()==0) {
					lblErrorQuestion.setVisible(true);
					lblErrorQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries"));
				}else {
					lblErrorQuestion.setVisible(false);
					lblErrorQuote.setVisible(false);
				}
			}
		});
		list.setModel(events);
		list.setBounds(97, 110, 1, 1);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(31, 176, 475, 144);
		getContentPane().add(scrollBar);
		
		events.addAll(businessLogic.getEventsTeam(businessLogic.findTeam(user)));
		
		lblQuestion = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuestion.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuestion.setBounds(31, 342, 80, 29);
		getContentPane().add(lblQuestion);
		
		list1 = new JList<>();
		list1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostatu.setEnabled(false);
				quotes.removeAllElements();
				for(domain.Quote quote : businessLogic.findQuote((Question)list1.getSelectedValue()))
					quotes.addElement(quote);
				if(quotes.size()==0) {
					btnApostatu.setEnabled(false);
					lblErrorQuote.setVisible(true);
					lblErrorQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuote"));
				}else {
					btnApostatu.setEnabled(true);
					lblErrorQuote.setVisible(false);
				}
				if(list2.getSelectedValue()==null) {
					btnApostatu.setEnabled(false);
				}
			}
		});
		list1.setModel(questions);
		list1.setBounds(48, 196, 1, 1);
		getContentPane().add(list1);
		
		scrollBar1 = new JScrollPane(list1);
		scrollBar1.setBounds(31, 381, 475, 130);
		getContentPane().add(scrollBar1);
		
		lblQuotes = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Quote")); //$NON-NLS-1$ //$NON-NLS-2$
		lblQuotes.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblQuotes.setBounds(31, 521, 80, 22);
		getContentPane().add(lblQuotes);
		
		list2 = new JList<>();
		list2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		list2.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnApostatu.setEnabled(true);
			}
		});
		list2.setModel(quotes);
		list2.setBounds(64, 325, 1, 1);
		getContentPane().add(list2);
		
		scrollBar2 = new JScrollPane(list2);
		scrollBar2.setBounds(31, 553, 475, 134);
		getContentPane().add(scrollBar2);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
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
		btnClose.setBounds(364, 712, 142, 47);
		getContentPane().add(btnClose);
		
		btnApostatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ApustuaEgin")); //$NON-NLS-1$ //$NON-NLS-2$
		btnApostatu.setBackground(Color.PINK);
		btnApostatu.setForeground(Color.DARK_GRAY);
		btnApostatu.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnApostatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a =new apustuaEginGUI(user, (Quote)list2.getSelectedValue());
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnApostatu.setBounds(31, 712, 142, 47);
		btnApostatu.setEnabled(false);
		getContentPane().add(btnApostatu);
		
		lblErrorQuestion.setBounds(137, 342, 369, 29);
		getContentPane().add(lblErrorQuestion);
		
		lblErrorQuote.setBounds(121, 541, 385, 29);
		getContentPane().add(lblErrorQuote);
		
	}
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
