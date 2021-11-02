package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;

public class homePagePatient extends JFrame {

	private JPanel contentPane;
	private JTextField textField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = null;
					homePagePatient frame = new homePagePatient(username);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	//LoginPage passes in the username
	public homePagePatient(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[][][][][][114px][10px][153px][][][][][127px][][][]", "[35px][23px][23px][][][][][][][]"));
		
		//Set username to Jlabel
		homePageAdminController adminController = new homePageAdminController();
		String fullName = adminController.passAdminHomepageInfo(username);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loginPage = new LoginPage();
				loginPage.setVisible(true);
				
				dispose();
			}
		});
		contentPane.add(btnLogout, "cell 12 0,alignx right,aligny top");
		
		JLabel lblWelcome = new JLabel("New label");
		contentPane.add(lblWelcome, "cell 0 1,grow");
		lblWelcome.setText("Welcome Patient, ");
		JLabel lblNewLabel_1 = new JLabel(fullName);
		contentPane.add(lblNewLabel_1, "cell 1 1");
		
		
		JLabel lblNewLabel = new JLabel("Prescription number : ");
		contentPane.add(lblNewLabel, "flowx,cell 1 5,growx,aligny center");
		
		textField = new JTextField();
		contentPane.add(textField, "cell 5 5,growx,aligny center");
		textField.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Search");
		contentPane.add(btnNewButton_1, "cell 7 5,alignx left,aligny top");
		
		JButton btnNewButton = new JButton("View my prescriptions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame viewprespage = new viewPrescriptionList(username);
				viewprespage.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnNewButton, "cell 5 7,alignx left,aligny top");
		
			
		
	}
}
