package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class viewPrescription extends JFrame 
{

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					String username = null;
					viewPrescription frame = new viewPrescription(username);
					frame.setVisible(true);
				}
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public viewPrescription(String username) 
	{
		
		String accountUsername = username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[55px,grow][][grow][][][][][][][][][][][][][][][][][][][][]", "[23px][grow][][][][][][][][][][][][][][]"));
		
		// Logout button
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame loginPage = new LoginPage();
				loginPage.setVisible(true);
				
				dispose();
			}
		});
		btnLogout.setBounds(335, 11, 89, 23);
		contentPane.add(btnLogout, "cell 21 2");
		
		// home button
		JButton btnHome = new JButton("Home");
		btnHome.setHorizontalAlignment(SwingConstants.RIGHT);
		btnHome.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFrame homepage = new homePagePatient(accountUsername);
				homepage.setVisible(true);
				dispose();
			}
		});
		contentPane.add(btnHome, "cell 20 2,alignx left,aligny top");
		
		
		viewPrescriptionController vPC = new viewPrescriptionController(); 
		
		// User Info label
		String fullName = vPC.passPatientHomepageInfo(username);
		JLabel lblNewLabel_1 = new JLabel("Patient");
		contentPane.add(lblNewLabel_1, "cell 9 2");
		JLabel lblNewLabel_2 = new JLabel(fullName);
		contentPane.add(lblNewLabel_2, "cell 1 1");
		
		// data table
		String [] columnNames = {"Prescription ID", "Dosage"};
		String [][] data = vPC.getPrescription(accountUsername);;
		
		table = new JTable(data, columnNames);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add (scrollPane, "flowx,cell 9 10");
	}

}
