/**Gbemiga Adeosun
	 * C13433288
	 * OOSD-2
	 * Paddy Matthews
	 * 
	 */
import java.io.*;

public class Employee implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String ppsNumber;
	private String surname;
	private String firstname;
	private String dateOfBirth;
	private double monthlySalary;
	private EmployeeTaxDetails taxDetails;
	
	public Employee() {
		this.ppsNumber = "";
		this.surname = "";
		this.firstname = "";
		this.dateOfBirth = "";
		this.monthlySalary = 0;
		this.taxDetails = null;
		
	}
	
	public Employee(String ppsNumber, String surname, String firstname,
			String dateOfBirth, double monthlySalary,
			EmployeeTaxDetails taxDetails) {
		this.ppsNumber = ppsNumber;
		this.surname = surname;
		this.firstname = firstname;
		this.dateOfBirth = dateOfBirth;
		this.monthlySalary = monthlySalary;
		this.taxDetails = taxDetails;
	}
	
	public Employee(String ppsNumber, String surname, String firstname,
			String dateOfBirth, double monthlySalary
			) {
		this.ppsNumber = ppsNumber;
		this.surname = surname;
		this.firstname = firstname;
		this.dateOfBirth = dateOfBirth;
		this.monthlySalary = monthlySalary;
		this.taxDetails = null;
	}

	public String getPpsNumber() {
		return ppsNumber;
	}

	public void setPpsNumber(String ppsNumber) {
		this.ppsNumber = ppsNumber;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public double getMonthlySalary() {
		return monthlySalary;
	}

	public void setMonthlySalary(double monthlySalary) {
		this.monthlySalary = monthlySalary;
	}

	public EmployeeTaxDetails getTaxDetails() {
		return taxDetails;
	}

	public void setTaxDetails(EmployeeTaxDetails taxDetails) {
		this.taxDetails = taxDetails;
	}

	@Override
	public String toString() {
		return "Employee Details" +"\n-------------------------------\n"
				+ "PPSNumber: " + ppsNumber + "\nSurname: " + surname
				+ "\nFirstname: " + firstname + "\nDate Of Birth: " + dateOfBirth
				+ "\nMonthly Salary:" +" €" + monthlySalary + "\n\nTax Details"+ "\n-------------------------------\n"
				+ taxDetails ;
	}

		
	
}
