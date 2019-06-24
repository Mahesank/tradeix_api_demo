package tradeix.com.utills;

import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

/**
 * Utility class for common functions for Rest assured test
 * @author Mahesan Kumaraswami
 */
public class RESTAssuredTestUtils {
    static EmployeePOJO employeepojo;

    /**
     * logger
     */
    private static final Logger logger = LoggerFactory.getLogger(RESTAssuredTestUtils.class);

     /**
     * post REST response
     * @param requestBody ,
     * @param baseURI,
     * @return returnResponse string
     */
    public static Response postRESTResponse(String requestBody,String baseURI){
        Response response = given ()
                .header ( "Content-Type", "application/json" )
                .body ( requestBody )
                .when ()
                .post (baseURI)
                .then ()
                .assertThat ().statusCode ( 200 )
                .extract ().response ();
        return response;
    }

    /**
     * post REST response
     * @param baseURI,
     * @return returnResponse string
     */
    public static Response getRESTResponse(String baseURI){
        Response response = given ()
                .header ( "Content-Type", "application/json" )
                .when ()
                .get (baseURI)
                .then ()
                .assertThat ().statusCode ( 200 )
                .extract ().response ();
        return response;
    }

    /**
     * Method to get Build json body to a post/put request to insert employee record
     * @return The JSON body in String
     * @throws Throwable  Exception of JSON object
     */
    public static String buildJSONRequestBodyStringForProcessService(String employeeName, String salary, String age)throws Throwable{
        employeepojo.setEmployeeName(employeeName);
        employeepojo.setEmployeeAge(age);
        employeepojo.setEmployeeSalary(salary);

        //Function to construct the JSON body to post and put Rest methods
        JSONObject employeeJSONObject = new JSONObject();
        employeeJSONObject.put("name", employeeName);
        employeeJSONObject.put("salary", salary);
        employeeJSONObject.put("age", age);
        String body= (employeeJSONObject.toString ());
        return body;
    }


    /**
     * put REST response
     * @param requestBody ,
     * @param baseURI,
     * @return returnResponse string
     */
    public static Response putRESTResponse(String requestBody,String baseURI){
        Response response = given ()
                .header ( "Content-Type", "application/json" )
                .body ( requestBody )
                .when ()
                .put (baseURI)
                .then ()
                .assertThat ().statusCode ( 200 )
                .extract ().response ();
        return response;
    }


    /**
     * Method to generate the random string
     * @param len of random string that needs to be generated
     * @return
     *        random string generated for the given length
     */
    public static String getRandomAlphaString(int len) {
        String AB = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        SecureRandom rnd = new SecureRandom();

        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++)
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        return sb.toString();
    }

    /**
     * Method to get the employee data using employee id
     * @param createurl
     * @throws Throwable
     */
    public static Response dogetEmployeeRecord(String createurl) throws Throwable {
        Response response = getRESTResponse(createurl);
        return response;
    }

    /**
     * Method to delete the employee data using employee id
     * @param deleteurl
     * @throws Throwable
     */
    public static Response dodeleteEmployeeRecord(String deleteurl) throws Throwable {
        Response response = deleteRESTResponse(deleteurl);
        return response;
    }

    /**
     * delete REST response
     * @param baseURI,
     * @return returnResponse string
     */
    public static Response deleteRESTResponse(String baseURI){
        Response response = given ()
                .header ( "Content-Type", "application/json" )
                .when ()
                .delete (baseURI)
                .then ()
                .assertThat ().statusCode ( 200 )
                .extract ().response ();
        return response;
    }
}
