package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author Software Engineering teachers
 */
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;


public class MainUserGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JFrame thisw ; 
	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	 
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton1;
	private JRadioButton rdbtnNewRadioButton2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private JButton loginButton;

	private JButton registerButton;
	
	/**
	 * This is the default constructor
	 */
	public MainUserGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(1);
			}
		});

		initialize();
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		thisw = this;
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			
			loginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new LoginGUI(); 
					thisw.setVisible(false);
					a.setVisible(true);
				}
			});
			loginButton.setBounds(0, 100, 481, 43);
			jContentPane.add(loginButton);
			
			registerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
			registerButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new RegisterGUI(); 
					thisw.setVisible(false);
					a.setVisible(true);
				}
			});
			registerButton.setBounds(0, 141, 481, 38);
			jContentPane.add(registerButton);
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 63, 481, 38);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton1() {
		if (rdbtnNewRadioButton1 == null) {
			rdbtnNewRadioButton1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton1);
		}
		return rdbtnNewRadioButton1;
	}
	private JRadioButton getRdbtnNewRadioButton2() {
		if (rdbtnNewRadioButton2 == null) {
			rdbtnNewRadioButton2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton2);
		}
		return rdbtnNewRadioButton2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 189, 481, 63);
			panel.add(getRdbtnNewRadioButton1());
			panel.add(getRdbtnNewRadioButton2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		loginButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		registerButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
} // @jve:decl-index=0:visual-constraint="0,0"

