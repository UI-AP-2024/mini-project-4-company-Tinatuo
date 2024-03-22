import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

    }
}
abstract class User{
    private String firstAndLastName;
    private int phoneNumber;
    User(String firstAndLastName,int phoneNumber){
        this.firstAndLastName=firstAndLastName;
        this.phoneNumber=phoneNumber;
    }

    public String getFirstAndLastName() {
        return firstAndLastName;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }
    abstract public String getBasicInformation();
}
abstract class Employee extends User{
    private String hiringDate;
    Employee(String firstAndLastName, int phoneNumber,String hiringDate) {
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
        string="First and Last name: "+this.getFirstAndLastName()+"\nPhone number: "+ String.valueOf(this.getPhoneNumber())+"\nHiring date: "+hiringDate;
        return string;
    }

}
class FullTimeEmployee extends Employee{
    private int workingDay;
    private final double salary;
    FullTimeEmployee(String firstAndLastName, int phoneNumber, String hiringDate,double salary) {
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
    PartTimeEmployee(String firstAndLastName, int phoneNumber, String hiringDate, double hourlyWages) {
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
    ProjectEmploye(String firstAndLastName, int phoneNumber, String hiringDate, double wage) {
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
    private Admin(String firstAndLastName, int phoneNumber) {
        super(firstAndLastName, phoneNumber);
    }
    public Admin getAdmin(String firstAndLastName, int phoneNumber){
        if(admin!=null){
            return admin;
        }else{
            admin=new Admin(firstAndLastName,phoneNumber);
            return admin;
        }

    }
    public Admin getAdmin(){
        return admin;
    }

    @Override
    public String getBasicInformation() {
        String string;
        string="First and Last name: "+admin.getFirstAndLastName()+"\nPhone number: "+ String.valueOf(admin.getPhoneNumber());
        return string;
    }
    public void addEmployee(Employee employee,String firstAndLastName, int phoneNumber,String hiringDate,EmployeeType type,double salary){
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