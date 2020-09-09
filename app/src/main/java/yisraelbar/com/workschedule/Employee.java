package yisraelbar.com.workschedule;

class Employee {
    private String userName;
    private String userPass;
    private String name;
    private String cellphone;
    private int employeeNumber;
    private  String facility;
    private  String address;

    public Employee (){

    }

    public Employee (String username, String password){
        this.userName=username;
        this.userPass=password;
    }

    public Employee(String userName, String userPass, String name, String cellphone, int employeeNumber, String facility, String address) {
        this.userName = userName;
        this.userPass = userPass;
        this.name = name;
        this.cellphone = cellphone;
        this.employeeNumber = employeeNumber;
        this.facility = facility;
        this.address = address;
    }

//    public Employee(Employee e1) {
//
//    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String callphone) {
        this.cellphone = callphone;
    }

    public int getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(int employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", name='" + name + '\'' +
                ", cellphone='" + cellphone + '\'' +
                ", employeeNumber=" + employeeNumber +
                ", facility='" + facility + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
