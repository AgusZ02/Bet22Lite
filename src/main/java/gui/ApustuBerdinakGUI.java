package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.ApustuAnitza;
import domain.Registered;

public class ApustuBerdinakGUI extends JFrame{
	private BLFacade businessLogic = MainGUI.getBusinessLogic();
	private static final long serialVersionUID = 1L;
	private JLabel lblApustuak;
	private JList list;
	private DefaultListModel<ApustuAnitza> apustuLista = new DefaultListModel<ApustuAnitza>();
	private JScrollPane scrollBar;
	private JButton btnClose;
	private JLabel lblError;
	private static final String ETIQUETAS= "Etiquetas";
	
	
	public ApustuBerdinakGUI(Registered copia) {
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 300));
		this.setTitle(ResourceBundle.getBundle(ETIQUETAS).getString("ApustuBerdinak"));
		
		lblApustuak = new JLabel(ResourceBundle.getBundle(ETIQUETAS).getString("ErabApustuak"));
		lblApustuak.setBackground(Color.PINK);
		lblApustuak.setOpaque(true);
		lblApustuak.setHorizontalAlignment(SwingConstants.CENTER);
		lblApustuak.setBounds(10, 10, 366, 13);
		getContentPane().add(lblApustuak);
		
		list = new JList();
		list.setModel(apustuLista);
		list.setBounds(138, 70, 1, 1);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(10, 34, 366, 166);
		getContentPane().add(scrollBar);
		apustuLista.addAll(businessLogic.findApustuAnitza(copia));
		
		btnClose = new JButton(ResourceBundle.getBundle(ETIQUETAS).getString("Close"));
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setForeground(Color.WHITE);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});
		btnClose.setBounds(278, 210, 98, 21);
		getContentPane().add(btnClose);
		
		lblError = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setBounds(70, 218, 213, 13);
		getContentPane().add(lblError);
		
	}
	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
