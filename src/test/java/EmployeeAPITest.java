import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tradeix.com.utills.EmployeePOJO;
import tradeix.com.utills.RESTAssuredTestUtils;

/**
 * Test class for insert delete and update Employee record
 * @author Mahesh Kumaraswami
 *
 */
public class EmployeeAPITest {

    RESTAssuredTestUtils restapi;
    Response response;
    EmployeePOJO employeepojo;
    String body=null;
    String randomName=null;

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RESTAssuredTestUtils.class);
    /**
     * Rest URL
     */
    public static String baseUri="http://dummy.restapiexample.com/";

    /**
     * Scenario: Create Update and Delete Employee Record
     * Given A User creates a new employee record
     * Then validates the employee record save in the backend
     * when the user updates employee salary
     * And validates the employee record to see if the salary is updated
     * And user deletes employee record
     * */
    @Test
    public void doCreateUpdateDeleteEmployeeRecord() throws Throwable {
        String url= baseUri + "api/v1/create";
        randomName=restapi.getRandomAlphaString(5);
        body= restapi.buildJSONRequestBodyStringForProcessService(randomName,"100","35");
        response = restapi.postRESTResponse(body,url);
        String employeeId = response.jsonPath().getString("id");
        System.out.println("EmployeeID ::"+employeeId);

        //Get the employee record stored using the employee id
        String getemployeeRecordUri= baseUri + "api/v1/employee/" +employeeId;
        response= restapi.dogetEmployeeRecord(getemployeeRecordUri);

        //Validate the employee data (Age, Salary and Name) recorded earlier
        Assert.assertTrue("Employee name is not Matching" ,response.jsonPath().getString("employee_name").equals(employeepojo.getEmployeeName()));
        Assert.assertTrue("Employee age is not Matching" ,response.jsonPath().getString("employee_age").equals(employeepojo.getEmployeeAge()));
        Assert.assertTrue("Employee salary is not Matching" ,response.jsonPath().getString("employee_salary").equals(employeepojo.getEmployeeSalary()));

        //Updated employee Salary
        String updateemployeeRecordUri= baseUri + "api/v1/update/" +employeeId;

        body= restapi.buildJSONRequestBodyStringForProcessService(employeepojo.getEmployeeName(),"500",employeepojo.getEmployeeAge());
        response = restapi.putRESTResponse(body,updateemployeeRecordUri);

        //Validate the updated employee record
        response= restapi.dogetEmployeeRecord(getemployeeRecordUri);
        System.out.println("After Update ::"+response.jsonPath().getString("employee_salary"));
        Assert.assertTrue("Updated Employee salary is not Matching" ,response.jsonPath().getString("employee_salary").equals(employeepojo.getEmployeeSalary()));

        //Delete employee Record
        String deleteloyeeRecordUri= baseUri + "api/v1/delete/" +employeeId;
        response= restapi.dodeleteEmployeeRecord(deleteloyeeRecordUri);
        System.out.println("After Delete ::"+ response.jsonPath().getString("success.text"));
        Assert.assertTrue("Employee Record Not deleted" ,response.jsonPath().getString("success.text").equals("successfully! deleted Records"));

    }

}
