package tradeix.com.utills;

/**
 * POJO class for employee getter and setter of employee record to be used in  Rest assured test
 * @author Mahesan Kumaraswami
 */
public class EmployeePOJO {
    private static String employeeName = null;
    private static String employeeAge = null;
    private static String employeeSalary = null;
    private static String employeeID = null;


    public static String getEmployeeName() {
        return employeeName;
    }

    public static void setEmployeeName(String name) {
        employeeName = name;
    }

    public static void setEmployeeAge(String age) {
        employeeAge = age;
    }

    public static String getEmployeeAge() {
        return employeeAge;
    }

    public static void setEmployeeSalary(String salary) {
        employeeSalary = salary;
    }

    public static String getEmployeeSalary() {
        return employeeSalary;
    }

    public static String getEmployeeID() {
        return employeeID;
    }

    public static void setEmployeeID(String id) { employeeID = id; }

}
