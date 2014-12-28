import java.util.ArrayList;


public class Testing {

	ArrayList<Employee> employees = new ArrayList<Employee>();
	EmployeeTaxDetails details;
	
	public Testing() {
		
		
		Employee employee1 = new Employee("1234a","Adeosun"	,"Bemi"
				, "12-May-2001", 5000, details);
		//details = new EmployeeTaxDetails("Single",2, 3,1, false,0);
			System.out.println(employee1.toString());
			System.out.println(details.calculateTax(employee1));
			//employee1.;
			System.out.println(details.toString());
			employees.add(employee1);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		new Testing();

	}

}
