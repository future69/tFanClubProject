package tFanClubProject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import net.miginfocom.swing.MigLayout;
import javax.swing.JSplitPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class viewPrescriptionList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					String username = null;
					viewPrescriptionList frame = new viewPrescriptionList(username);
					frame.setVisible(true);
				} catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public viewPrescriptionList(String username) 
	{
		
		String accountUsername = username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 646, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[55px,grow][][grow][][][][][][][][][][][][][][][][][][][][]", "[23px][grow][][][][][][][][][][][][][][]"));
		
		viewPrescriptionListController vPLC = new viewPrescriptionListController(); 
		
		// User Info label
		String fullName = vPLC.passPatientHomepageInfo(username);
		JLabel lblNewLabel_1 = new JLabel("Patient");
		contentPane.add(lblNewLabel_1, "cell 9 2");
		JLabel lblNewLabel_2 = new JLabel(fullName);
		contentPane.add(lblNewLabel_2, "cell 1 1");
		
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
		
		// Back button to return to homepage
		JButton btnBack = new JButton("Back");
		btnBack.setHorizontalAlignment(SwingConstants.RIGHT);
		btnBack.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				JFrame homepage = new homePagePatient(accountUsername);
				homepage.setVisible(true);
				dispose();
			}
		});
		// Search bar
		JLabel lblNewLabel = new JLabel("Prescription ID: ");
		contentPane.add(lblNewLabel, "flowx,cell 9 5");
		textField = new JTextField();
		contentPane.add(textField, "cell 9 5,alignx left");
		textField.setColumns(10);
		JButton btnNewButton_1 = new JButton("Search");
		contentPane.add(btnNewButton_1, "cell 9 5");
		
		// Prescription table
		String [] columnNames = {"Prescription ID", "Prescribed date", "Prescribed status"};
		String [][] data = vPLC.getPrescriptions(accountUsername);;
		
		table = new JTable(data, columnNames);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add (scrollPane, "flowx,cell 9 10");
		contentPane.add(btnBack, "cell 20 13,alignx left,aligny top");
		
		
	
		
		
		
	}
}
	


