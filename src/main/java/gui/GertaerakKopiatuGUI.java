package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacade;
import domain.Event;

public class GertaerakKopiatuGUI extends JFrame{
	private static final long serialVersionUID = 1L;
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();
	private JLabel lblGertaerakKopiatu;
	private JScrollPane scrollBar;
	private JList<Event> list;
	private DefaultListModel<Event> eventsLista = new DefaultListModel<>();
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JTextField txtYear;
	private JTextField txtMonth;
	private JTextField txtDay;
	private JButton btnCopy;
	private JLabel lblError;
	private JButton btnInfo;
	private Boolean b =false;
	
	public GertaerakKopiatuGUI() {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(455, 370));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("GertaerakKopiatu"));
		
		lblGertaerakKopiatu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertaerakKopiatu"));
		lblGertaerakKopiatu.setBackground(Color.PINK);
		lblGertaerakKopiatu.setOpaque(true);
		lblGertaerakKopiatu.setHorizontalAlignment(SwingConstants.CENTER);
		lblGertaerakKopiatu.setBounds(2, 10, 439, 13);
		getContentPane().add(lblGertaerakKopiatu);
		
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnCopy.setEnabled(true);
				btnInfo.setEnabled(true);
			}
		});
		list.setModel(eventsLista);
		list.setBounds(137, 121, 1, 1);
		getContentPane().add(list);
		
		eventsLista.addAll(businessLogic.getEventsAll());
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(25, 33, 387, 181);
		getContentPane().add(scrollBar);
		
		lblYear = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year")); //$NON-NLS-1$ //$NON-NLS-2$
		lblYear.setOpaque(true);
		lblYear.setForeground(Color.WHITE);
		lblYear.setBackground(Color.PINK);
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setBounds(58, 224, 59, 13);
		getContentPane().add(lblYear);
		
		lblMonth = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Month")); //$NON-NLS-1$ //$NON-NLS-2$
		lblMonth.setOpaque(true);
		lblMonth.setForeground(Color.WHITE);
		lblMonth.setBackground(Color.PINK);
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setBounds(174, 224, 86, 13);
		getContentPane().add(lblMonth);
		
		lblDay = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Day")); //$NON-NLS-1$ //$NON-NLS-2$
		lblDay.setOpaque(true);
		lblDay.setForeground(Color.WHITE);
		lblDay.setBackground(Color.PINK);
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setBounds(312, 224, 59, 13);
		getContentPane().add(lblDay);
		
		txtYear = new JTextField();
		txtYear.setHorizontalAlignment(SwingConstants.CENTER);
		txtYear.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtYear.setBounds(39, 241, 96, 19);
		getContentPane().add(txtYear);
		txtYear.setColumns(10);
		
		txtMonth = new JTextField();
		txtMonth.setHorizontalAlignment(SwingConstants.CENTER);
		txtMonth.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtMonth.setBounds(174, 241, 96, 19);
		getContentPane().add(txtMonth);
		txtMonth.setColumns(10);
		
		txtDay = new JTextField();
		txtDay.setHorizontalAlignment(SwingConstants.CENTER);
		txtDay.setText(""); //$NON-NLS-1$ //$NON-NLS-2$
		txtDay.setBounds(298, 241, 96, 19);
		getContentPane().add(txtDay);
		txtDay.setColumns(10);
		
		btnCopy = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Copiar")); //$NON-NLS-1$ //$NON-NLS-2$
		btnCopy.setBackground(Color.PINK);
		btnCopy.setForeground(Color.DARK_GRAY);
		btnCopy.setEnabled(false);
		
		btnInfo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InfoIkusi")); //$NON-NLS-1$ //$NON-NLS-2$
		btnInfo.setForeground(Color.DARK_GRAY);
		btnInfo.setBackground(Color.PINK);
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setForeground(Color.WHITE);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
			}
		});
		btnClose.setBounds(327, 291, 85, 21);
		getContentPane().add(btnClose);
		
		lblError = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(39, 270, 346, 13);
		getContentPane().add(lblError);
		lblError.setVisible(false);
		
		btnCopy.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				try {
					if(Integer.parseInt(txtMonth.getText())>=0 && Integer.parseInt(txtMonth.getText())<=12 && Integer.parseInt(txtDay.getText())<=31 && Integer.parseInt(txtDay.getText())>=0) {
						lblError.setVisible(false);
						java.util.Date date =newDate(Integer.parseInt(txtYear.getText()),Integer.parseInt(txtMonth.getText())-1,Integer.parseInt(txtDay.getText()));	
						if(date.compareTo(new Date())>=0) {
							b = businessLogic.gertaerakKopiatu(list.getSelectedValue(), date);
						}else if(date.compareTo(new Date())<0){
							lblError.setVisible(true);
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("GertaeraKopiatuError"));
						}else if(!b) {
							lblError.setVisible(true);
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("GertaeraSorError"));
						}else{
							lblError.setVisible(true);
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("GertaeraSorCorrect"));
						}
						eventsLista.removeAllElements();
						eventsLista.addAll(businessLogic.getEventsAll());
						btnCopy.setEnabled(false);
						btnInfo.setEnabled(false);
					}else {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("DateError"));
						lblError.setVisible(true);
					}
				}catch(NumberFormatException a) {
					lblError.setVisible(true);
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("DateError"));
				}
			}
		});
		btnCopy.setBounds(25, 291, 92, 21);
		getContentPane().add(btnCopy);
		btnCopy.setEnabled(false);
		btnInfo.setEnabled(false);
		
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new GertaeraInfo(list.getSelectedValue());
				a.setVisible(true);
			}
		});
		btnInfo.setBounds(148, 291, 139, 21);
		getContentPane().add(btnInfo);
		
		
	}
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
}
