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
    abstract public String getBasicInformation(String firstAndLastName,int phoneNumber , String hiringDate);
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
    public final String getBasicInformation(String firstAndLastName, int phoneNumber,String hiringDate){
        String string;
        string="First and Last name: "+firstAndLastName+"\nPhone number: "+ String.valueOf(phoneNumber)+"\nHiring date: "+hiringDate;
        return string;
    }

}
class FullTimeEmployee extends Employee{
    private int workingDay;
    private final double salary;
    FullTimeEmployee(String firstAndLastName, int phoneNumber, String hiringDate,int workingDay,double salary) {
        super(firstAndLastName, phoneNumber, hiringDate);
        this.salary = salary;
        this.workingDay=workingDay
    }

    public int getWorkingDay() {
        return workingDay;
    }

    @Override
    public double getSalary() {

    }

    @Override
    public double work(int x) {
        return 0;
    }
}