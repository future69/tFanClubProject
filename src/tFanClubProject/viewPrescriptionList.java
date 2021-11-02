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

public class viewPrescriptionList extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton btnNewButton;

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
		contentPane.setLayout(new MigLayout("", "[55px,grow][][][][][][][][][][][][][][][][][][]", "[23px][grow][][][][][][][][][]"));
		
		viewPrescriptionListController vPLC = new viewPrescriptionListController(); 
		


		String [] columnNames = {"Prescription ID", "Prescribed date"};
		String [][] data = vPLC.getPrescriptions(accountUsername);
		table = new JTable(data, columnNames);
				
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);;
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add (scrollPane, "flowx,cell 1 5");
		
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
		
		btnNewButton = new JButton("Test");
		contentPane.add(btnNewButton, "cell 16 7,aligny top");
		contentPane.add(btnBack, "cell 16 8,alignx left,aligny top");
		btnNewButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				vPLC.getPrescriptions(accountUsername);
			}
			
		});
	}
}
	


