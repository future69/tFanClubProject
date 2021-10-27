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
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("New label");
		lblWelcome.setBounds(10, 11, 262, 35);
		contentPane.add(lblWelcome);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loginPage = new LoginPage();
				loginPage.setVisible(true);
				
				dispose();
			}
		});
		btnLogout.setBounds(335, 11, 89, 23);
		contentPane.add(btnLogout);
		
		//Set username to Jlabel
		homePageAdminController adminController = new homePageAdminController();
		String fullName = adminController.passAdminHomepageInfo(username);
		lblWelcome.setText("Welcome Patient, " + fullName);
		
		textField = new JTextField();
		textField.setBounds(134, 114, 153, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("View my prescriptions");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame viewprespage = new viewPrescriptionList(username);
				
				viewprespage.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBounds(134, 169, 138, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Prescription number : ");
		lblNewLabel.setBounds(10, 117, 114, 14);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Search");
		btnNewButton_1.setBounds(297, 113, 89, 23);
		contentPane.add(btnNewButton_1);
		
			
		
	}
}
