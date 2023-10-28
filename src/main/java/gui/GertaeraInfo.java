package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import domain.Event;
import domain.Question;
import domain.Quote;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacade;

import javax.swing.event.ListSelectionEvent;

public class GertaeraInfo extends JFrame{
	private static final long serialVersionUID = 1L;
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();	
	private JLabel lblTitle;
	private JLabel lblDate;
	private JScrollPane scrollBar;
	private JList<Question> list;
	private DefaultListModel<Question> questionLista = new DefaultListModel<>();
	private JList<Quote> list1;
	private JScrollPane scrollBar1;
	private DefaultListModel<Quote> quoteLista = new DefaultListModel<>();
	private JLabel lblEvent;
	private JLabel lblSport;
	private JButton btnNewButton;

	public GertaeraInfo(Event ev) {
	this.setSize(new Dimension(455, 390));
	this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("GertaeraInfo"));
	getContentPane().setLayout(null);
	
	lblTitle = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertaeraInfo"));
	lblTitle.setBounds(10, 25, 421, 13);
	lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
	lblTitle.setForeground(Color.WHITE);
	lblTitle.setBackground(Color.PINK);
	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
	lblTitle.setOpaque(true);
	getContentPane().add(lblTitle);
	
	lblDate = new JLabel();
	lblDate.setBounds(24, 62, 387, 13);
	lblDate.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblDate.setForeground(Color.PINK);
	lblDate.setBackground(Color.WHITE);
	lblDate.setOpaque(true);
	lblDate.setHorizontalAlignment(SwingConstants.CENTER);
	getContentPane().add(lblDate);
	lblDate.setText(ev.getEventDate().toString());
	
	list = new JList<>();
	list1 = new JList<>();
	questionLista.addAll(ev.getQuestions());
	list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged(ListSelectionEvent e) {
			quoteLista.removeAllElements();
			quoteLista.addAll((list.getSelectedValue()).getQuotes());

		}
	});
	list.setBounds(48, 121, 1, 1);
	list.setModel(questionLista);
	getContentPane().add(list);
	
	
	scrollBar = new JScrollPane(list);
	scrollBar.setBounds(34, 112, 363, 95);
	getContentPane().add(scrollBar);
	
	list1.setBounds(44, 263, 1, 1);
	list1.setModel(quoteLista);
	getContentPane().add(list1);
	
	scrollBar1 = new JScrollPane(list1);
	scrollBar1.setBounds(34, 217, 363, 95);
	getContentPane().add(scrollBar1);
	
	btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	btnNewButton.setBackground(Color.DARK_GRAY);
	btnNewButton.setForeground(Color.WHITE);
	btnNewButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			jButtonCloseActionPerformed(e);
		}
	});
	btnNewButton.setBounds(330, 322, 85, 21);
	getContentPane().add(btnNewButton);
	
	lblEvent = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
	lblEvent.setBackground(Color.WHITE);
	lblEvent.setForeground(Color.PINK);
	lblEvent.setOpaque(true);
	lblEvent.setHorizontalAlignment(SwingConstants.CENTER);
	lblEvent.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblEvent.setBounds(24, 43, 387, 13);
	getContentPane().add(lblEvent);
	lblEvent.setText(ev.toString());
	
	lblSport = new JLabel(businessLogic.findSport(ev).getIzena());
	lblSport.setFont(new Font("Tahoma", Font.PLAIN, 12));
	lblSport.setOpaque(true);
	lblSport.setForeground(Color.PINK);
	lblSport.setBackground(Color.WHITE);
	lblSport.setHorizontalAlignment(SwingConstants.CENTER);
	lblSport.setBounds(24, 80, 387, 22);
	getContentPane().add(lblSport);
	if(businessLogic.findSport(ev).getIzena().equals("Futbol")) {
		ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\futbol.png"); // load the image to a imageIcon
	    Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // scale it the smooth way  
	    imageIcon = new ImageIcon(newimg);
        lblSport.setIcon(imageIcon);
	}else if(businessLogic.findSport(ev).getIzena().equals("Baloncesto")) {
		ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\basker.png"); // load the image to a imageIcon
	    Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // scale it the smooth way  
	    imageIcon = new ImageIcon(newimg);
        lblSport.setIcon(imageIcon);
	}else if(businessLogic.findSport(ev).getIzena().equals("Tennis")) {
		ImageIcon imageIcon = new ImageIcon(".\\src/main/resources\\data\\tennis.png"); // load the image to a imageIcon
	    Image image = imageIcon.getImage(); // transform it 
	    Image newimg = image.getScaledInstance(20, 20, Image.SCALE_SMOOTH); // scale it the smooth way  
	    imageIcon = new ImageIcon(newimg);
        lblSport.setIcon(imageIcon);
	}
	}
	
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
