package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.ResourceBundle;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Transaction;
import domain.Registered;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MugimenduakBistaratuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	
	private Registered user;
	private DefaultListModel<Transaction> apustuaEgin = new DefaultListModel<>();
	private transient BLFacade businessLogic = MainGUI.getBusinessLogic();
	
	
	
	
	public MugimenduakBistaratuGUI(Registered u) {
		user = u; 
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void jbInit() throws Exception {
		JScrollPane scrollBar2;
		JScrollPane scrollBar3;
		JLabel lblNewLabel1;
		JLabel lblNewLabel2;
		JLabel lblNewLabel3;
		JLabel lblNewLabel4;
		JLabel lblNewLabel5;

		JLabel lblNewLabel;
		JButton btnNewButton;
		JLabel lblMugimenduakBistaratu;
		JList<Transaction> list;
		JScrollPane scrollBar;
		JScrollPane scrollBar1;
		JList<Transaction> list1;
		DefaultListModel<Transaction> apustuaEzabatu = new DefaultListModel<>();
		JList<Transaction> list2;
		DefaultListModel<Transaction> diruaSartu = new DefaultListModel<>();
		JList<Transaction> list3;
		DefaultListModel<Transaction> apustuaIrabazi = new DefaultListModel<>();
		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(3000, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MugimenduakBistaratu"));
		
		lblMugimenduakBistaratu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Mugimenduak"));
		lblMugimenduakBistaratu.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblMugimenduakBistaratu.setHorizontalAlignment(SwingConstants.CENTER);
		lblMugimenduakBistaratu.setBounds(662, 10, 242, 57);
		getContentPane().add(lblMugimenduakBistaratu);
		
		list = new JList<>();
		list.setModel(diruaSartu);
		list.setBounds(0, 0, 419, 150);
		getContentPane().add(list);
		
		list1 = new JList<>();
		list1.setModel(apustuaEgin);
		list1.setBounds(645, 217, 173, -100);
		getContentPane().add(list1);
		
		list2 = new JList<>();
		list2.setModel(apustuaEzabatu);
		list2.setBounds(1063, 101, 1, 1);
		getContentPane().add(list2);
		
		list3 = new JList<>();
		list3.setModel(apustuaIrabazi);
		list3.setBounds(1063, 101, 1, 1);
		getContentPane().add(list3);
		
		for(Transaction l : businessLogic.findTransakzioak(user)) {
			if(l.getMota().compareTo("DiruaSartu")==0) {
				diruaSartu.addElement(l);
			}else if(l.getMota().compareTo("ApustuaEgin")==0){
				apustuaEgin.addElement(l);
			}else if(l.getMota().compareTo("ApustuaEzabatu")==0){
				apustuaEzabatu.addElement(l);
			}else if(l.getMota().compareTo("ApustuaIrabazi")==0) {
				apustuaIrabazi.addElement(l);
			}
		}
		list.setBackground(Color.green);
		list1.setBackground(Color.red);
		list2.setBackground(Color.green);
		list3.setBackground(Color.green);
		scrollBar = new JScrollPane(list);
		scrollBar.setBounds(26, 89, 340, 150);
		getContentPane().add(scrollBar);
		
		
		
		scrollBar1 = new JScrollPane(list1);
		scrollBar1.setBounds(397, 89, 338, 150);
		getContentPane().add(scrollBar1);
		
		
		
		scrollBar2 = new JScrollPane(list2);
		scrollBar2.setBounds(767, 89, 338, 150);
		getContentPane().add(scrollBar2);
		
		scrollBar3 = new JScrollPane(list3);
		scrollBar3.setBounds(1141, 89, 347, 150);
		getContentPane().add(scrollBar3);
		
		lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SarDirMug"));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel.setBounds(27, 74, 339, 13);
		getContentPane().add(lblNewLabel);
		
		lblNewLabel1 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApEginMug"));
		lblNewLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel1.setBounds(397, 77, 338, 13);
		getContentPane().add(lblNewLabel1);
		
		lblNewLabel2 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApEzabMug"));
		lblNewLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel2.setBounds(767, 74, 338, 13);
		getContentPane().add(lblNewLabel2);
		
		lblNewLabel3 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Saldoa"));
		lblNewLabel3.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel3.setBounds(585, 358, 132, 13);
		getContentPane().add(lblNewLabel3);
		
		lblNewLabel4 = new JLabel(businessLogic.saldoaBistaratu(user));
		lblNewLabel4.setBounds(719, 359, 102, 13);
		getContentPane().add(lblNewLabel4);
		
		lblNewLabel5 = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ApIrabMug"));
		lblNewLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel5.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel5.setBounds(1141, 74, 347, 13);
		getContentPane().add(lblNewLabel5);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
			}
		});
		btnNewButton.setBounds(1342, 403, 132, 36);
		getContentPane().add(btnNewButton);
		
	}
	
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}

}
