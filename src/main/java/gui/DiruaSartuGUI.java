package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Registered;
import java.awt.Color;

public class DiruaSartuGUI extends JFrame{
	
	private JTextField textFieldDiruaSartu;
	private JLabel lblTitulo;
	private JLabel lblError;
	private JLabel jlblDiruaSartu;
	private JButton btnDiruaSartu;
	private Registered user; 
	private static final long serialVersionUID = 1L;
	private JButton jButtonClose;
	private transient BLFacade businessLogic; 

	
	public DiruaSartuGUI(Registered u) {
		getContentPane().setLayout(null);
		this.setSize(new Dimension(450, 325));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu"));
		
		user = u; 
		
		businessLogic = MainGUI.getBusinessLogic(); 
		jlblDiruaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruKop"));
		jlblDiruaSartu.setHorizontalAlignment(SwingConstants.RIGHT);
		jlblDiruaSartu.setBounds(10, 117, 123, 13);
		getContentPane().add(jlblDiruaSartu);
		
		textFieldDiruaSartu = new JTextField();
		textFieldDiruaSartu.setBounds(143, 114, 253, 19);
		getContentPane().add(textFieldDiruaSartu);
		textFieldDiruaSartu.setColumns(10);
		
		lblTitulo = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu").toUpperCase());
		lblTitulo.setFont(new Font("Times New Roman", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(138, 27, 179, 65);
		getContentPane().add(lblTitulo);
		
		lblError = new JLabel();
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(143, 91, 253, 13);
		lblError.setVisible(false);
		getContentPane().add(lblError);
		
		btnDiruaSartu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Sartu"));
		btnDiruaSartu.setBackground(Color.PINK);
		btnDiruaSartu.setForeground(Color.DARK_GRAY);
		btnDiruaSartu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String textua = textFieldDiruaSartu.getText().trim();
				Double zenb; 
				
				try {
					
					zenb = Double.parseDouble(textua);
					
					if(zenb>0.0) {
						
						businessLogic.diruaSartu(user, zenb, "DiruaSartu");
						lblError.setVisible(true); 
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("DiruCorrect"));
						
					}else {
					
						lblError.setVisible(true); 
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
					}
					
				}catch(NumberFormatException e1) {
					lblError.setVisible(true); 
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
				}
				
			}
		});
		btnDiruaSartu.setBounds(115, 180, 112, 32);
		getContentPane().add(btnDiruaSartu);
		
		jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		jButtonClose.setForeground(Color.WHITE);
		jButtonClose.setBackground(Color.DARK_GRAY);
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCloseActionPerformed(e);
			}
		});
		
			
		jButtonClose.setBounds(274, 183, 112, 26);
		getContentPane().add(jButtonClose);
		
		
	}
	private void jButtonCloseActionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
