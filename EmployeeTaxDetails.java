import java.io.Serializable;

/**Gbemiga Adeosun
	 * C13433288
	 * OOSD-2
	 * Paddy Matthews
	 */
public class EmployeeTaxDetails implements Serializable{


	private double taxDue;
	private double taxCredits;
	private double netTaxDue;
	private double annualSalary;
	private String persoanlCircumstance;
	private int numberOfDependentKids;
	private double benifitInKind;
	private double grossIncome;
	private int numberOfIncome;
	private Boolean childcarerCredit;
	private double partnerSalary;
	private double usc,prsi, employerPrsi, totalDeductions;
	double taxExemption;
	private int age;
	
	public EmployeeTaxDetails(){
		this.persoanlCircumstance = "";
		this.numberOfDependentKids = 0;
		this.benifitInKind = 0;
		this.numberOfIncome = 0;
		this.age =0;
		this.childcarerCredit = null;
		//this.prsi =0;
	}

	public EmployeeTaxDetails(String persoanlCircumstance,
			int numberOfDependentKids,int age, double benifitInKind,
			int numberOfIncome, Boolean childcarerCredit,
			double partnerSalary){
		
		this.persoanlCircumstance = persoanlCircumstance;
		this.numberOfDependentKids = numberOfDependentKids;
		this.age = age;
		this.benifitInKind = benifitInKind;
		this.numberOfIncome = numberOfIncome;
		this.childcarerCredit = childcarerCredit;
		this.partnerSalary = partnerSalary;
	}
	
	public Boolean getChildcarerCredit(){
		return childcarerCredit;
	}

	public void setChildcarerCredit(Boolean childcarerCredit) {
		this.childcarerCredit = childcarerCredit;
	}

	public double getPrsi() {
		return prsi;
	}

	public void setPrsi(double prsi) {
		this.prsi = prsi;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getPersoanlCircumstance() {
		return persoanlCircumstance;
	}

	public void setPersoanlCircumstance(String persoanlCircumstance) {
		this.persoanlCircumstance = persoanlCircumstance;
	}

	public int getNumberOfDependentKids() {
		return numberOfDependentKids;
	}

	public void setNumberOfDependentKids(int numberOfDependentKids) {
		this.numberOfDependentKids = numberOfDependentKids;
	}

	public double getBenifitInKind() {
		return benifitInKind;
	}

	public void setBenifitInKind(double benifitInKind) {
		this.benifitInKind = benifitInKind;
	}

	public int getNumberOfIncome() {
		return numberOfIncome;
	}

	public void setNumberOfIncome(int numberOfIncome) {
		this.numberOfIncome = numberOfIncome;
	}
	
	public double getPartnerSalary() {
		return partnerSalary;
	}

	public void setPartnerSalary(double partnerSalary) {
		this.partnerSalary = partnerSalary;
	}
	public double calAnnualSalary(Employee emp){
		annualSalary= emp.getMonthlySalary()*12;
		return annualSalary;
	}
	
	public double calculateTax(Employee employee1){
		//taxExemption();
		double annualSalary;
		double tax;
		double balance;
		double taxBal;
		double totalTax = 0;
		double lowerRate = 0.2;
		double taxExemption = 33800;
		double taxExemption2 = 37800;
		double taxExemption3 = 42800;
		double taxExemption4 = 24800;
		double childLimit =575;
		double childLimit2 =830;
		double newLimit;
		double newLimit2;
		if(numberOfDependentKids>=0 && numberOfDependentKids <=2){
			newLimit = childLimit * numberOfDependentKids;
			taxExemption = taxExemption + newLimit;
			taxExemption2 = taxExemption2 + newLimit;
			taxExemption3 = taxExemption3 + newLimit;
			taxExemption4 = taxExemption4 + newLimit;
			
		}else 
			if(numberOfDependentKids>2){
			int kidsLeft = numberOfDependentKids - 2;
			newLimit = childLimit * 2;
			newLimit2 = childLimit2 * kidsLeft;
			taxExemption = taxExemption + newLimit + newLimit2;
			taxExemption2 = taxExemption2 + newLimit + newLimit2;
			taxExemption3 = taxExemption3 + newLimit + newLimit2;
			taxExemption4 = taxExemption4 + newLimit + newLimit2;
		}
		if(persoanlCircumstance.equals("Single")|| persoanlCircumstance.equals("Widowed") || persoanlCircumstance.equals("Surviving Civil Partner")
				&& (numberOfDependentKids==0)){
			annualSalary = (employee1.getMonthlySalary() * 12) + benifitInKind;
			if(annualSalary <= taxExemption){
				totalTax = annualSalary  * lowerRate;
			}else
				if(annualSalary > taxExemption){
					tax= taxExemption * lowerRate;
					balance = annualSalary - taxExemption;
					taxBal = balance * 0.4;
					totalTax = tax + taxBal;
				}
		}else
			if(persoanlCircumstance.equals("Single")|| persoanlCircumstance.equals("Widowed")
					|| persoanlCircumstance.equals("Surviving Civil Partner") && childcarerCredit==true){
				annualSalary =  (employee1.getMonthlySalary() * 12) + benifitInKind;
				if(annualSalary <= taxExemption2){
					totalTax = annualSalary  * lowerRate;
				}else
					if(annualSalary > taxExemption2){
						tax= taxExemption2 * lowerRate;
						balance = annualSalary - taxExemption2;
						taxBal = balance * 0.4;
						totalTax = tax + taxBal;
				
					}
			}else
				if(persoanlCircumstance.equals("Married")|| persoanlCircumstance.equals("In a Civil Partnership")
						 && numberOfIncome==1){
					annualSalary = (employee1.getMonthlySalary() * 12) + benifitInKind;
					if(annualSalary <= taxExemption3){
						totalTax = annualSalary  * lowerRate;
					}else
						if(annualSalary > taxExemption3){
							tax= taxExemption3 * lowerRate;
							balance = annualSalary - taxExemption3;
							taxBal = balance * 0.4;
							totalTax = tax + taxBal;
					
						}
				}else
					if(persoanlCircumstance.equals("Married")|| persoanlCircumstance.equals("In a Civil Partnership")
							 && numberOfIncome==2){
						annualSalary =  (employee1.getMonthlySalary() * 12) + benifitInKind;
						
						if(annualSalary > partnerSalary && annualSalary <= taxExemption3 && partnerSalary <= taxExemption4){
							tax = annualSalary  * lowerRate;
							double otherTax = partnerSalary * lowerRate;
							totalTax = tax + otherTax;
						}else
							if(annualSalary > partnerSalary && annualSalary > taxExemption3 && partnerSalary <= taxExemption4){
								tax= annualSalary * lowerRate;
								balance = annualSalary - taxExemption3;
								taxBal = balance * 0.4;
								double otherTax = partnerSalary * lowerRate;
								totalTax = tax + taxBal + otherTax;
								
							}else
								if(annualSalary > partnerSalary && annualSalary > taxExemption3 && partnerSalary > taxExemption4){
									tax= annualSalary * lowerRate;
									balance = annualSalary - taxExemption3;
									taxBal = balance * 0.4;
									double otherTax = partnerSalary * lowerRate;
									double otherBal = partnerSalary - taxExemption4;
									double otherTax2 = otherBal * lowerRate;
									totalTax = tax + taxBal + otherTax+ otherTax2;
									
								}
						}
		taxDue= totalTax;
		return taxDue;
	}
	
	public double calculateTaxcredit(Employee employee1){
		if(persoanlCircumstance.equals("Single")){
			taxCredits= 1650 + 1650;
			return taxCredits;
		}else
			if(persoanlCircumstance.equals("Married")){
				taxCredits= 1650 + 3300;
				return taxCredits;
		}
		if(persoanlCircumstance.equals("Married")|| persoanlCircumstance.equals("Surviving Civil Partner") && age >65){
			taxCredits= 1650 + 3300 + 490;
			return taxCredits;
		}else
			if(persoanlCircumstance.equals("Single") || persoanlCircumstance.equals("Widowed")|| persoanlCircumstance.equals("Widowed") && age> 65){
				taxCredits= 1650 + 1650 + 245;
				return taxCredits;
			}
		
		return taxCredits;
	}
	
	public double calculateGrossIncome(Employee emp){
		grossIncome = (emp.getMonthlySalary() * 12) + benifitInKind;
		
		return grossIncome;
		
	}
	
	public double calculateUsc(Employee employee1){
		double income =  (employee1.getMonthlySalary() * 12) + benifitInKind;
		if(income < 10036){
			usc= income/100*0;
			return usc;
		}else
			if(income <= 12102 && income >= 10036){
			usc= income/100*1.5;
			return usc;
		}else
			if(income> 12102 && income < 17576){
				usc= income /100* 3.5;
				return usc;
			}else
				if(income> 17576.01 && income < 70044){
					usc= income /100* 7;
					return usc;
				}else
					if(income > 70044){
						usc= income /100*8 ;
						return usc;
					}
		
		return usc;
		
	}
	
	public double calculatePRSI(Employee employee1){
		double weeklyIncome = employee1.getMonthlySalary()/4;
		double employeePRSI;
		double employerPRSI1;
		double employerPRSI2 = 0;
		if(weeklyIncome< 352){
			employeePRSI =0;
			employerPRSI1 = weeklyIncome /100*8.5;
			employerPrsi = employerPRSI1 + employerPRSI2;
			prsi = (employeePRSI*4) * 12;
			return employerPrsi;
		}else
			if(weeklyIncome> 352){
				employeePRSI = weeklyIncome /100*4;
				employerPRSI1 = 356 /100*8.5;
				employerPRSI2 = (weeklyIncome-365)/100*10.75;
				employerPrsi = employerPRSI1 + employerPRSI2;
				prsi = (employeePRSI*4) * 12;
				return employerPrsi;
			}
		return prsi;
			
	}
	
	public double netTaxDue(){
		netTaxDue= taxDue-taxCredits;
		
		return netTaxDue;
		
	}
	public double totalDeductions(){
		totalDeductions= netTaxDue + prsi + usc;
		
		return totalDeductions;
		
	}

	
	public String toString() {
		return 
				"Persoanl Circumstance: "+ persoanlCircumstance +
				"\nNumber Of DependentKids: "+ numberOfDependentKids
				+ "\nnumber Of Income: " + numberOfIncome +
				"\nChildcarer Credit: " + childcarerCredit 
				+ "\nPartner's Salary: " + partnerSalary+
				
				"\n\n\nEmployeeTax Details \n"+
				"Annual Salary:    €" + annualSalary +
				"\nbenifitInKind:    €" + benifitInKind +
				"\n-------------------------------"+
				"\nGross Income:     €" + grossIncome+
				"\nTax Due:         €"+ taxDue+
				"\n-------------------------------"+
				"\nLESS:TAX CREDITS: €"+ taxCredits+
				"\n-------------------------------"+
				"\nNet Tax Due      €"+ netTaxDue+
				"\nPRSI:            €" + prsi +
				"\nUCS:              €" + usc +
				"\nTotal Deductions: €" + totalDeductions ;
	}

	

	
}



