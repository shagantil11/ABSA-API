package AbsaAPI;

import java.util.Properties;

import Constant.constant;
import util.Utility;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import Constant.constant;
import groovy.json.JsonException;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;





public class DogBreedApiTest {
	
	 final static Logger logger = Logger.getLogger(DogBreedApiTest.class);


	    Properties properties;
	    public static ExtentTest test;

	    ExtentReports extent = utils.ExtentManager.getInstance();

	    @BeforeTest
	    public void setUp() {

	        try {
	            properties = Utility.fetchApiDetails(constant.API_PROPERTY_FILE_PATH);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

	    @Test(priority = 4)
	    public void getListOfAllDogBreed() {
	        test = extent.startTest("Get List of All Dog Breed");
	        //Base URL
	        RestAssured.baseURI = properties.getProperty("baseURL");

	        Response res = given().
	                when().
	                get(properties.getProperty("resources")).
	                then().
	                assertThat().
	                statusCode(200).and().
	                contentType(ContentType.JSON).and().extract().response();

	     
	        String responseString = res.asString();
	        logger.info("==Response String == " + responseString);

	      
	        JsonPath js = new JsonPath(responseString);
	        logger.info("Json Response String" + "\n" +
	                js);

	        test.log(LogStatus.INFO, "Response Payload");
	        test.log(LogStatus.PASS, responseString);

	    }
	    @Test(priority = 3)
	    public void verifyRetriverInResponsePayload() {
	        test = extent.startTest("Verify Retriver In Response Payload");
	        //Base URL
	        RestAssured.baseURI = properties.getProperty("baseURL");

	        Response res = given().
	                when().
	                get(properties.getProperty("resources")).
	                then().
	                assertThat().
	                statusCode(200).and().
	                contentType(ContentType.JSON).and().extract().response();

	       

	        String responseString = res.asString();
	        logger.info("responseString" + responseString);

	       
	        JsonPath js = new JsonPath(responseString);
	        logger.info("Json Response String" + "\n" +
	                js);
	        if (responseString.contains(constant.RETIREVER)) {

	            test.log(LogStatus.PASS, "Verify retriver is withinList :");
	        } else
	            test.log(LogStatus.FAIL, "Verify retriver is not withinList : ");
	    }

	    @Test(priority = 2)
	    public void getSubBreedListFromRetriver() throws JsonException, IOException {

	        test = extent.startTest("Get Retriver SubBreedList");
	       
	        RestAssured.baseURI = properties.getProperty("baseURL");

	       
	        Response res = given().
	                when().
	                get(properties.getProperty("resources_for_retriver_breed")).
	                then().
	                assertThat().
	                statusCode(200).and().
	                contentType(ContentType.JSON).and().extract().response();

	        
	        String responseString = res.asString();
	        logger.info("responseString" + responseString);
	        test.log(LogStatus.INFO.PASS, responseString);

	    }

	    @Test(priority = 1)
	    public void getRandomImgOrLinkForGolden() throws JsonException, IOException {

	        test = extent.startTest("Get Random Image or Link  For Golden");
	        
	        RestAssured.baseURI = properties.getProperty("baseURL");

	        
	        Response res = given().
	                when().
	                get(properties.getProperty("resouce_for_random_image")).
	                then().
	                assertThat().
	                statusCode(200).and().
	                contentType(ContentType.JSON).and().extract().response();

	       
	        String responseString = res.asString();

	        
	        responseString = responseString.replace("\\", "");
	        test.log(LogStatus.INFO.PASS, responseString);
	        logger.info("responseString" + responseString);
	    }

	    @AfterTest
	    public void reportEnd() {
	        extent.flush();

	    }

}
