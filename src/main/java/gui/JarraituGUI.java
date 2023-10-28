package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import businessLogic.BLFacade;
import domain.Registered;
import javax.swing.JTextField;

public class JarraituGUI extends JFrame{
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();
	private static final long serialVersionUID = 1L;
	private JLabel lblRanking;
	private JList<Registered> list;
	private DefaultListModel<Registered> rankingLista = new DefaultListModel<>();
	private JScrollPane scrollBar;
	private JButton btnClose;
	private JButton btnInfo;
	private Registered user;
	private JButton btnSeguir;
	private JTextField textField;
	private Boolean b;
	private JLabel lblError;
	private JLabel lblDiruaSartu;
	
	public JarraituGUI(Registered u) {
		user=u;
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(400, 320));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Ranking"));
		
		lblRanking = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Ranking"));
		lblRanking.setBackground(Color.PINK);
		lblRanking.setForeground(Color.BLACK);
		lblRanking.setOpaque(true);
		lblRanking.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRanking.setHorizontalAlignment(SwingConstants.CENTER);
		lblRanking.setBounds(10, 21, 366, 13);
		getContentPane().add(lblRanking);
		
		list = new JList<>();
		
		lblError = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		
		btnSeguir = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Seguir")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSeguir.setForeground(Color.DARK_GRAY);
		btnSeguir.setBackground(Color.PINK);
		btnSeguir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textua = textField.getText().trim(); 
				Double zenb; 
				try {
					zenb = Double.parseDouble(textua);
					if(zenb>0.0) {
						if((user).getUsername().equals((list.getSelectedValue()).getUsername())){
							lblError.setVisible(true);
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("JarraituError2") + (list.getSelectedValue()).getUsername());
						}else {
							b = businessLogic.jarraitu(user, list.getSelectedValue(), zenb);
							if(b) {
								lblError.setVisible(true);
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("JarraituCorrect") + (list.getSelectedValue()).getUsername());
							}else {
								lblError.setVisible(true);
								lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("JarraituError"));
							}
						}
					}else {
						lblError.setVisible(true); 
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber")); 
					}
				}catch(NumberFormatException e1) {
					lblError.setVisible(true); 
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				}
				btnInfo.setEnabled(false);
				btnSeguir.setEnabled(false);
			}
		});
		btnInfo = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ver2")); //$NON-NLS-1$ //$NON-NLS-2$
		btnInfo.setForeground(Color.DARK_GRAY);
		btnInfo.setBackground(Color.PINK);
		btnInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new ApustuBerdinakGUI(list.getSelectedValue(), user);
				a.setVisible(true);
			}
		});
		btnInfo.setBounds(124, 227, 122, 21);
		getContentPane().add(btnInfo);
		
		
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(textField.getText()!=null) {
					btnInfo.setEnabled(true);
					btnSeguir.setEnabled(true);
					lblError.setVisible(false);
				}
			}
		});
		list.setModel(rankingLista);
		list.setBounds(1, 1, 116, 94);
		getContentPane().add(list);
		
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(10, 36, 366, 153);
		getContentPane().add(scrollBar);
		
		btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnClose.setBackground(Color.DARK_GRAY);
		btnClose.setForeground(Color.WHITE);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
			}
		});
		btnClose.setBounds(291, 227, 85, 21);
		getContentPane().add(btnClose);
		
		rankingLista.addAll(businessLogic.rankingLortu());
		for(int i=0; i<rankingLista.getSize(); i++) {
			rankingLista.get(i).setMode("RankingGUI");
		}
		
		btnInfo.setEnabled(false);
		
		btnSeguir.setEnabled(false);
		btnSeguir.setBounds(10, 227, 104, 21);
		getContentPane().add(btnSeguir);
		
		textField = new JTextField();
		textField.setBounds(92, 196, 272, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		lblDiruaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruLimitea"));
		lblDiruaSartu.setOpaque(true);
		lblDiruaSartu.setBackground(Color.PINK);
		lblDiruaSartu.setBounds(10, 199, 78, 13);
		getContentPane().add(lblDiruaSartu);
		
		
		lblError.setVisible(false);
		lblError.setBounds(20, 258, 344, 13);
		getContentPane().add(lblError);
		
	}
	
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
