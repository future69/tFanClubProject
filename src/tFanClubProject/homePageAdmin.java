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
import net.miginfocom.swing.MigLayout;

public class homePageAdmin extends JFrame {

	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = null;
					homePageAdmin frame = new homePageAdmin(username);
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
	public homePageAdmin(String username) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[262px][89px]", "[35px][23px]"));
		
		JLabel lblWelcome = new JLabel("New label");
		contentPane.add(lblWelcome, "cell 0 0,grow");
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loginPage = new LoginPage();
				loginPage.setVisible(true);
				
				dispose();
			}
		});
		contentPane.add(btnLogout, "cell 1 0,growx,aligny center");
		
		//Set username to Jlabel
		homePageAdminController adminController = new homePageAdminController();
		String fullName = adminController.passAdminHomepageInfo(username);
		lblWelcome.setText("Welcome Admin, " + fullName);
		
		//Goes to add user page
		JButton btnAddUser = new JButton("Add/Update");
		btnAddUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame adduserpage = new addUserAdmin(username);
				
				adduserpage.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnAddUser, "cell 0 1,alignx left,aligny top");
		
		
		
	}
}
