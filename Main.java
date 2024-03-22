import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Admin.getAdmin("Tina Jouzdani","1234567");
        //k=0
        Admin.getAdmin().addEmployee("Soofia Akbari","097636","1400/6/8",EmployeeType.FULLTIME,50);
        //k=1
        Admin.getAdmin().addEmployee("Farzaneh Khorsandi","875368","1401/2/3",EmployeeType.PROJECT,100);
        //k=2
        Admin.getAdmin().addEmployee("Mahshid Hajalizadeh","35799","1400/5/9",EmployeeType.PARTTIME,70);
        //k=3
        Admin.getAdmin().addEmployee("Mehrgol Jenabpour","8397","1402/9/12",EmployeeType.PARTTIME,80);
        //k=4
        Admin.getAdmin().addEmployee("Bahareh Rahnama","376780","1401/12/9",EmployeeType.PROJECT,90);
        Admin.getAdmin().getEmployees().get(0).work(70);
        Admin.getAdmin().getEmployees().get(1).work(5);
        Admin.getAdmin().getEmployees().get(2).work(100);
        Admin.getAdmin().getEmployees().get(3).work(110);
        Admin.getAdmin().getEmployees().get(4).work(7);
        System.out.println("Employees' salary:");
        for(int k = 0;k<Admin.getAdmin().getEmployees().size();k++){
            System.out.println(Admin.getAdmin().getEmployees().get(k).getFirstAndLastName()+" : "+Admin.getAdmin().getEmployees().get(k).getSalary()+" $");
        }
    }
}
abstract class User{
    private String firstAndLastName;
    private String phoneNumber;
    User(String firstAndLastName,String phoneNumber){
        this.firstAndLastName=firstAndLastName;
        this.phoneNumber=phoneNumber;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
    abstract public String getBasicInformation();
}
abstract class Employee extends User{
    private String hiringDate;
    Employee(String firstAndLastName, String phoneNumber,String hiringDate) {
        super(firstAndLastName, phoneNumber);
        this.hiringDate=hiringDate;
    }

    public String getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(String hiringDate) {
        this.hiringDate = hiringDate;
    }
    abstract public double getSalary();
    abstract public double work(int x);
    @Override
    public final String getBasicInformation(){
        String string;
        string="First and Last name: "+this.getFirstAndLastName()+"\nPhone number: "+ this.getPhoneNumber()+"\nHiring date: "+hiringDate;
        return string;
    }

}
class FullTimeEmployee extends Employee{
    private int workingDay;
    private final double salary;
    FullTimeEmployee(String firstAndLastName, String phoneNumber, String hiringDate,double salary) {
        super(firstAndLastName, phoneNumber, hiringDate);
        this.salary = salary;
    }

    public int getWorkingDay() {
        return workingDay;
    }

    @Override
    public double getSalary() {
        double y;
        if(workingDay>=30){
            y=((double) workingDay /30)*salary;
            workingDay-=(workingDay/30)*30;
            return y;
        }else{
            return 0;
        }
    }
    @Override
    public double work(int days) {
        this.workingDay+=days;
        return (double)workingDay;
    }
}
class PartTimeEmployee extends Employee{
final private double hourlyWages;
private double workingHours;
    PartTimeEmployee(String firstAndLastName, String phoneNumber, String hiringDate, double hourlyWages) {
        super(firstAndLastName, phoneNumber, hiringDate);
        this.hourlyWages = hourlyWages;
    }

    @Override
    public double getSalary() {
        double y;
        y=(workingHours*hourlyWages);
        workingHours-=workingHours;
        return y;
    }

    @Override
    public double work(int hours) {
        workingHours+=hours;
        return workingHours;
    }
}
class ProjectEmploye extends Employee{
    int numberOfProject;
    final private double wage;
    ProjectEmploye(String firstAndLastName, String phoneNumber, String hiringDate, double wage) {
        super(firstAndLastName, phoneNumber, hiringDate);
        this.wage = wage;
    }

    @Override
    public double getSalary() {
        double y=numberOfProject*wage;
        numberOfProject-=numberOfProject;
        return y;
    }

    @Override
    public double work(int project) {
        numberOfProject+=project;
        return (double)numberOfProject;
    }
}
class Admin extends User{
    private static Admin admin;
    private ArrayList<Employee> employees=new ArrayList<Employee>();
    private Admin(String firstAndLastName, String phoneNumber) {
        super(firstAndLastName, phoneNumber);
    }
    public static Admin getAdmin(String firstAndLastName, String phoneNumber){
        if(admin!=null){
            return admin;
        }else{
            admin=new Admin(firstAndLastName,phoneNumber);
            return admin;
        }

    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public static Admin getAdmin(){
        return admin;
    }

    @Override
    public String getBasicInformation() {
        String string;
        string="First and Last name: "+admin.getFirstAndLastName()+"\nPhone number: "+ admin.getPhoneNumber();
        return string;
    }
    public void addEmployee(String firstAndLastName, String phoneNumber,String hiringDate,EmployeeType type,double salary){
        if(type==EmployeeType.FULLTIME){
            FullTimeEmployee newEmployee=new FullTimeEmployee(firstAndLastName,phoneNumber,hiringDate,salary);
            employees.add(newEmployee);
        } else if(type==EmployeeType.PARTTIME){
            PartTimeEmployee newEmployee=new PartTimeEmployee(firstAndLastName,phoneNumber,hiringDate,salary);
            employees.add(newEmployee);
        } else if(type==EmployeeType.PROJECT){
            ProjectEmploye newEmployee=new ProjectEmploye(firstAndLastName,phoneNumber,hiringDate,salary);
            employees.add(newEmployee);
        }

    }
    public String showUser(){
        String string;
        string="Admin:"+"\n"+admin.getBasicInformation()+"\nEmployees:";
        for(int i=0;i<employees.size();i++){
            string=string+"\n"+employees.get(i).getBasicInformation();
        }
        return string;
    }
}
enum EmployeeType{
    FULLTIME,PARTTIME,PROJECT
}