package fileConverter.dto;

public class Employee {

    private String ID;
    private String Firstname;
    private String Lastname;
    private int age;
    private double salary;
    
    public Employee() { }
    
	public Employee(String iD, String firstname, String lastname, int age, double salary) {
		super();
		ID = iD;
		Firstname = firstname;
		Lastname = lastname;
		this.age = age;
		this.salary = salary;
	}
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getFirstname() {
		return Firstname;
	}
	public void setFirstname(String firstname) {
		Firstname = firstname;
	}
	public String getLastname() {
		return Lastname;
	}
	public void setLastname(String lastname) {
		Lastname = lastname;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "<" + ID + ", " + Firstname + ", " + Lastname + ", " + age
				+ ", " + salary + ">";
	}
    
    
	
}
