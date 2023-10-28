package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ResourceBundle;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Team;
import domain.TeamRenderer;
import domain.Registered;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class TaldeaJarraituGUI extends JFrame{
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();

	private static final long serialVersionUID = 1L;
	private Registered user;

	private JLabel lblAukeratu;

	private JScrollPane scrollBar;

	private JList<Team> list;
	private DefaultListModel<Team> teams = new DefaultListModel<>();
	private JButton btnClose;
	private JButton btnSeguir;
	
	private JFrame thisw;
	
	public TaldeaJarraituGUI(Registered u) {
		thisw=this;
		user=u;
		
		getContentPane().setLayout(null);
		this.setSize(new Dimension(650, 525));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Seguir"));
		
		btnSeguir = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Seguir")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSeguir.setFont(new Font("Tahoma", Font.PLAIN, 16));

		lblAukeratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("AukTeam") + ":");
		lblAukeratu.setOpaque(true);
		lblAukeratu.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAukeratu.setBackground(Color.PINK);
		lblAukeratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblAukeratu.setBounds(20, 21, 588, 47);
		getContentPane().add(lblAukeratu);
		
		btnSeguir.setEnabled(false);
		
		list = new JList<>();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				btnSeguir.setEnabled(true);
			}
		});
		list.setBackground(Color.WHITE);
		list.setModel(teams);
		list.setBounds(126, 134, 1, 1);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(20, 78, 588, 331);
		getContentPane().add(scrollBar);
		
		teams.addAll(businessLogic.getAllTeams());
		
		list.setCellRenderer(new TeamRenderer());
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
				JFrame a= new DestacadosGUI(user);
				a.setVisible(true);
			}
		});
		btnClose.setForeground(Color.WHITE);
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setBounds(454, 419, 154, 43);
		getContentPane().add(btnClose);
		
		
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				businessLogic.jarraituTaldea(user, list.getSelectedValue());
				JFrame a = new DestacadosGUI(user);
				a.setVisible(true);
				thisw.setVisible(false);
			}
		});
		btnSeguir.setForeground(Color.DARK_GRAY);
		btnSeguir.setBackground(Color.PINK);
		btnSeguir.setBounds(20, 419, 154, 43);
		getContentPane().add(btnSeguir);
		
	}
	
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
