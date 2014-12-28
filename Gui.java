import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.io.*;
import java.util.*;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;


public class Gui extends JFrame {

	/**Gbemiga Adeosun
	 * C13433288
	 * OOSD-2
	 * Paddy Matthews
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField employeeFirstnameTextField,EmployeeSurnameTextField, ppsNumberTextField,
	filenameTextField, benifitInKindTextField, newfilenameTextField,
	numberOfDependentKidsField, numberOfIncomeTextField;
	private JLabel employeeLabel, employeeFirstnameLabel, employeeSurnameLabel, ppsNumberLabel, 
	filenameLabel, employeeTaxLabel,benifitInKindLabel, numberOfDependentKidsLabel, numberOfIncomeLabel,
	persoanlCircumstanceLabel;
	private JFormattedTextField  birthdayField, monthlySalaryTextField;
	private JLabel employeeMonthlySalaryLabel, ppsnSearchLabel, ChildCarerLabel, dateOfBirthLabel, partnersIncomeLabel;
	private JButton AddTaxDetails, start, saveDetails, openFile, saveFile, empDetails,
	firstEmp,lastEmp,nextEmp, searchButton,previousEmp, deleteButton, updateButton;
	@SuppressWarnings("rawtypes")
	private JComboBox persoanlCircumstanceComboBox;
	private Date myDate;
	private JDialog open, saveBar;
	private JTextArea detailsTextPane;
	private JCheckBox ChildCarerBox;
	JScrollPane scroll;
	LinkedList<Employee> employees =  new LinkedList<Employee>();
	private int index = 0;
	static Gui frame;
	private JTextField searchTextField;
	private JTextField partnerIncomeTextField;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Gui();
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
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Gui() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 599, 426);

		final JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenuItem fileNew = new JMenuItem("New File");
		fileNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				contentPane.add(employeeLabel);
				contentPane.add(employeeFirstnameLabel);
				contentPane.add(employeeFirstnameTextField);
				contentPane.add(employeeSurnameLabel);
				contentPane.add(ppsNumberLabel);
				contentPane.add(EmployeeSurnameTextField);
				contentPane.add(ppsNumberTextField);
				contentPane.add(dateOfBirthLabel);
				contentPane.add(birthdayField);
				contentPane.add(employeeMonthlySalaryLabel);
				contentPane.add(monthlySalaryTextField);
				contentPane.add(AddTaxDetails);
				contentPane.add(saveDetails);
			}
		});
		JMenuItem fileOpen = new JMenuItem("Open File");
		fileOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				open.setVisible(true);
			}
		});
		JMenuItem fileSave = new JMenuItem("Save File");
		fileSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				saveBar.setVisible(true);
			}
		});
		JMenu OptionMenu = new JMenu("Options");
		JMenuItem displayFile = new JMenuItem("Display");
		displayFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				setJMenuBar(menuBar);
				contentPane.add(firstEmp);
				contentPane.add(lastEmp);
				contentPane.add(nextEmp);
				contentPane.add(previousEmp);
				contentPane.add(scroll);
				scroll.setViewportView(detailsTextPane);
				readFromFile();
			}
		});
		
		JMenuItem search = new JMenuItem("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				setJMenuBar(menuBar);
				contentPane.add(scroll);
				contentPane.add(searchButton);
				contentPane.add(ppsnSearchLabel);
				contentPane.add(searchTextField);
				contentPane.add(updateButton);
				contentPane.add(deleteButton);
				scroll.setViewportView(detailsTextPane);
			}
		});
		
		fileMenu.add(fileNew);
		fileMenu.add(fileOpen);
		fileMenu.add(fileSave);
		menuBar.add(fileMenu);
		OptionMenu.add(displayFile);
		OptionMenu.add(search);
		menuBar.add(OptionMenu);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel logo = new JLabel();
		logo.setIcon(new ImageIcon("RevenueCommissioner.jpg"));
		logo.setBounds(143, 28, 334, 170);
		contentPane.add(logo);

		start = new JButton("Enter");
		start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				setJMenuBar(menuBar);
				open.setVisible(true);
			}
		});
		start.setBounds(222, 262, 135, 61);
		contentPane.add(start);

		scroll = new JScrollPane();
		scroll.setBounds(25, 43, 545, 269);

		firstEmp = new JButton("First");
		firstEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				detailsTextPane.setText(employees.getFirst().toString());
			}
		});
		firstEmp.setBounds(54, 323, 89, 23);

		previousEmp = new JButton("Previous");
		previousEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					detailsTextPane.setText(employees.get(index-1).toString());
					index--;
				}
				catch (Exception t){
					detailsTextPane.setText("There are no more Details" );
				}

			}
		});
		previousEmp.setBounds(181, 323, 89, 23);

		nextEmp = new JButton("Next");
		nextEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					detailsTextPane.setText(employees.get(index+1).toString());
					index++;
				}
				catch (Exception t)
				{
					detailsTextPane.setText("There are no more Details" );
				}

			}

		});
		nextEmp.setBounds(320, 323, 89, 23);
		
		lastEmp = new JButton("Last");
		lastEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				index = employees.size()-1 ;
				detailsTextPane.setText(employees.getLast().toString());
			}
		});
		lastEmp.setBounds(448, 323, 89, 23);

		searchTextField = new JTextField();
		searchTextField.setBounds(64, 12, 135, 20);
		searchTextField.setColumns(10);

		ppsnSearchLabel = new JLabel("PPS No:");
		ppsnSearchLabel.setBounds(10, 15, 46, 14);

		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for(Employee emp: employees){
					if(searchTextField.getText().equals(emp.getPpsNumber())){
						detailsTextPane.setText(emp.toString());
					}
				}
			}
		});
		searchButton.setBounds(210, 12, 95, 21);

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				employees.remove(index);
				index--;
			}
		});
		deleteButton.setBounds(367, 11, 79, 20);

		updateButton = new JButton("Update");
		updateButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(Employee emp :employees){
					if(searchTextField.getText().equals(emp.getPpsNumber())){
						contentPane.removeAll();
						contentPane.revalidate();  
						repaint();
						contentPane.add(employeeLabel);
						contentPane.add(employeeFirstnameLabel);
						contentPane.add(employeeFirstnameTextField);
						contentPane.add(AddTaxDetails);
						contentPane.add(saveDetails);
						employeeFirstnameTextField.setText(emp.getFirstname());
						contentPane.add(employeeSurnameLabel);
						contentPane.add(ppsNumberLabel);
						contentPane.add(EmployeeSurnameTextField);
						EmployeeSurnameTextField.setText(emp.getSurname());
						contentPane.add(ppsNumberTextField);
						ppsNumberTextField.setText(emp.getPpsNumber());
						contentPane.add(dateOfBirthLabel);
						contentPane.add(birthdayField);
						birthdayField.setText(emp.getDateOfBirth());
						contentPane.add(employeeMonthlySalaryLabel);
						contentPane.add(monthlySalaryTextField);
						monthlySalaryTextField.setText(Double.toString(emp.getMonthlySalary()));
						
					}
				}
				
			}
		});
		updateButton.setBounds(463, 11, 88, 20);
		
		detailsTextPane = new JTextArea();

		employeeLabel = new JLabel("Add Employee Details");
		employeeLabel.setBounds(234, 11, 122, 14);

		employeeFirstnameLabel = new JLabel("Employee Firstname:");
		employeeFirstnameLabel.setBounds(140, 52, 122, 14);	

		employeeFirstnameTextField = new JTextField();
		employeeFirstnameTextField.setBounds(283, 46, 155, 26);
		employeeFirstnameTextField.setColumns(10);

		employeeSurnameLabel = new JLabel("Employee Surname:");
		employeeSurnameLabel.setBounds(140, 93, 122, 14);

		ppsNumberLabel = new JLabel("PPS Number:");
		ppsNumberLabel.setBounds(140, 133, 109, 14);
		
		EmployeeSurnameTextField = new JTextField();
		EmployeeSurnameTextField.setColumns(10);
		EmployeeSurnameTextField.setBounds(283, 87, 155, 26);

		ppsNumberTextField = new JTextField();
		ppsNumberTextField.setColumns(10);
		ppsNumberTextField.setBounds(283, 127, 155, 26);

		birthdayField = new JFormattedTextField(new SimpleDateFormat("dd/MM/yyyy"));
		birthdayField.setValue(new Date());
		birthdayField.setBounds(283, 168, 155, 26);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			myDate = dateFormat.parse(birthdayField.getText());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date now = new Date();
		String strDate = sdf.format(now);

		@SuppressWarnings("deprecation")
		int year = myDate.getYear();
		Date today = null;
		try {
			today = dateFormat.parse( strDate );
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		@SuppressWarnings("deprecation")
		int todaysYear = today.getYear();
		final int yearsAge = todaysYear - year;

		dateOfBirthLabel = new JLabel("Date Of Birth:");
		dateOfBirthLabel.setBounds(140, 173, 109, 14);
		//contentPane.add(dateOfBirthLabel);	

		///////////////////////////////////////////////////////////////////////////////////////////////
		//////
		////TAX


		employeeTaxLabel = new JLabel("Add Employee Tax Details");
		employeeTaxLabel.setBounds(234, 11, 152, 14);
		
		numberOfDependentKidsLabel = new JLabel("No. of dependent kids:");
		numberOfDependentKidsLabel.setBounds(140, 52, 152, 14);

		numberOfDependentKidsField = new JTextField();
		numberOfDependentKidsField.setBounds(283, 46, 155, 26);
		numberOfDependentKidsField.setColumns(10);

		numberOfIncomeLabel = new JLabel("No. of income:");
		numberOfIncomeLabel.setBounds(140, 93, 122, 14);

		numberOfIncomeTextField = new JTextField();
		numberOfIncomeTextField.setColumns(10);
		numberOfIncomeTextField.setBounds(283, 87, 155, 26);

		String[] persoanlCircumstance = { "Single", "Widowed", "Surviving Civil Partner", "Married" };
		persoanlCircumstanceComboBox = new JComboBox(persoanlCircumstance);
		persoanlCircumstanceComboBox.setBounds(283, 127, 155, 26);

		persoanlCircumstanceLabel = new JLabel("Persoanl Circumstance:");
		persoanlCircumstanceLabel.setBounds(140, 133, 155, 14);

		benifitInKindTextField = new JTextField();
		benifitInKindTextField.setColumns(10);
		benifitInKindTextField.setBounds(283, 168, 155, 26);

		benifitInKindLabel = new JLabel("Benifit In Kind:");
		benifitInKindLabel.setBounds(140, 173, 109, 14);

		ChildCarerLabel = new JLabel("Child Carer Credit:");
		ChildCarerLabel.setBounds(140, 209, 109, 14);

		ChildCarerBox = new JCheckBox("");
		ChildCarerBox.setBounds(283, 205, 21, 23);
		
		partnersIncomeLabel = new JLabel("Partner's Income:");
		partnersIncomeLabel.setBounds(140, 244, 109, 14);
				
		partnerIncomeTextField = new JTextField("0");
		partnerIncomeTextField.setBounds(283, 238, 155, 26);
		partnerIncomeTextField.setColumns(10);

		employeeMonthlySalaryLabel = new JLabel("Monthly Salary:");
		employeeMonthlySalaryLabel.setBounds(140, 208, 122, 14);
		
		monthlySalaryTextField = new JFormattedTextField(new DecimalFormat("######"));
		monthlySalaryTextField.setValue(new Integer(3500));
		monthlySalaryTextField.setBounds(283, 205, 155, 26);

		saveDetails = new JButton("Save");
		saveDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EmployeeTaxDetails details;
				details = new EmployeeTaxDetails(persoanlCircumstanceComboBox.getSelectedItem().toString(),Integer.parseInt(numberOfDependentKidsField.getText()),
						yearsAge, Double.parseDouble(benifitInKindTextField.getText()),Integer.parseInt(numberOfIncomeTextField.getText()), ChildCarerBox.isSelected(),Double.parseDouble(partnerIncomeTextField.getText()));
				Employee employee1 = new Employee(ppsNumberTextField.getText(),
						EmployeeSurnameTextField.getText(),
						employeeFirstnameTextField.getText(),
						birthdayField.getText(),
						Double.parseDouble(monthlySalaryTextField.getText()),
						details);
				details.calculateTax(employee1);
				details.calAnnualSalary(employee1);
				details.calculateTaxcredit(employee1);
				details.calculatePRSI(employee1);
				details.calculateGrossIncome(employee1);
				details.netTaxDue();
				details.totalDeductions();
				details.calculateUsc(employee1);
				employees.add(employee1);
				for(Employee emp: employees){
					System.out.println(emp.toString());
				}
				employeeFirstnameTextField.setText("");
				EmployeeSurnameTextField.setText("");
				ppsNumberTextField.setText("");
				birthdayField.setText("");
				monthlySalaryTextField.setText("");
			}
		}); 
		saveDetails.setBounds(326, 300, 89, 23);

		/////////////////////////////////////////////////////////////////////////////////////////////////

		empDetails = new JButton("Employee");
		empDetails.setBounds(143, 300, 122, 23);
		empDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				contentPane.add(employeeLabel);
				contentPane.add(employeeFirstnameLabel);
				contentPane.add(employeeFirstnameTextField);
				contentPane.add(employeeSurnameLabel);
				contentPane.add(ppsNumberLabel);
				contentPane.add(EmployeeSurnameTextField);
				contentPane.add(ppsNumberTextField);
				contentPane.add(dateOfBirthLabel);
				contentPane.add(birthdayField);
				contentPane.add(employeeMonthlySalaryLabel);
				contentPane.add(monthlySalaryTextField);
				contentPane.add(AddTaxDetails);
				contentPane.add(saveDetails);
			}
		});
		
		AddTaxDetails = new JButton("Add Tax Details");
		AddTaxDetails.setBounds(143, 300, 122, 23);
		AddTaxDetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				contentPane.add(employeeTaxLabel);
				contentPane.add(numberOfDependentKidsLabel);
				contentPane.add(numberOfDependentKidsField);
				contentPane.add(numberOfIncomeLabel);
				contentPane.add(numberOfIncomeTextField);
				contentPane.add(persoanlCircumstanceComboBox);
				contentPane.add(persoanlCircumstanceLabel);
				contentPane.add(benifitInKindTextField);
				contentPane.add(benifitInKindLabel);
				contentPane.add(ChildCarerBox);
				contentPane.add(ChildCarerLabel);
				contentPane.add(partnersIncomeLabel);
				contentPane.add(partnerIncomeTextField);
				contentPane.add(empDetails);
				contentPane.add(saveDetails);
			}
		});

		open = new JDialog(frame, "Open File");
		open.getContentPane().setLayout(new FlowLayout());
		open.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		open.setBounds(200, 200, 278, 120);

		filenameLabel = new JLabel("Enter Filename:");
		open.getContentPane().add(filenameLabel);

		filenameTextField = new JTextField();
		filenameTextField.setColumns(10);
		filenameTextField.setText("Final.dat");

		newfilenameTextField = new JTextField();
		newfilenameTextField.setColumns(10);
		open.getContentPane().add(newfilenameTextField);
		newfilenameTextField.setText("Final.dat");

		openFile = new JButton("Open");
		open.getContentPane().add(openFile);
		openFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				contentPane.removeAll();
				contentPane.revalidate();  
				repaint();
				setJMenuBar(menuBar);
				contentPane.add(firstEmp);
				contentPane.add(lastEmp);
				contentPane.add(nextEmp);
				contentPane.add(previousEmp);
				contentPane.add(scroll);
				contentPane.add(searchButton);
				contentPane.add(ppsnSearchLabel);
				contentPane.add(searchTextField);
				contentPane.add(updateButton);
				contentPane.add(deleteButton);
				scroll.setViewportView(detailsTextPane);
				readFromFile();
				open.dispose();
			}
		});

		saveBar = new JDialog(frame, "Save File");
		saveBar.getContentPane().setLayout(new FlowLayout());
		saveBar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		saveBar.setBounds(200, 200, 278, 120);
		
		filenameLabel = new JLabel("Enter Filename:");
		saveBar.getContentPane().add(filenameLabel);

		filenameTextField = new JTextField();
		filenameTextField.setColumns(10);

		saveBar.getContentPane().add(filenameTextField);

		filenameTextField.setText("Final.dat");
		saveFile = new JButton("Save");
		saveBar.getContentPane().add(saveFile);
		saveFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveToFile();
				saveBar.dispose();
			}
		});
	}

	public void saveToFile(){
		ObjectOutputStream fileOut;

		try{
			fileOut = new ObjectOutputStream(new FileOutputStream(filenameTextField.getText()));
			for (Employee employee : employees){
				fileOut.writeObject(employee);

			}
			fileOut.close();
			System.out.println("Employee contents saved");
		}
		catch (IOException e){
			System.out.println("IO Error : " + e.getMessage());
		}
	}

	public boolean readFromFile(){

		int index=0;
		ObjectInputStream fileIn;
		Employee employee;
		String fileName = newfilenameTextField.getText();

		try{
			fileIn = new ObjectInputStream(new FileInputStream(fileName));
			detailsTextPane.setText("Opened file successfully\n");
			employee = (Employee) fileIn.readObject();
			index = 1;
			while (employee != null){
				employees.add(employee);
				index++;
				employee = (Employee) fileIn.readObject();
			}


			fileIn.close();
			return true;
		}
		catch (IOException e){
			if (index > 0)
			{
				detailsTextPane.append(employees.getFirst().toString()); 
				return true; 
			}
			else{
				detailsTextPane.setText("IO Error : " + e.getMessage());
				return false;
			}
		}

		catch (ClassNotFoundException e){
			detailsTextPane.setText("Class Error : " + e.getMessage());
			return false;
		}
	}
}