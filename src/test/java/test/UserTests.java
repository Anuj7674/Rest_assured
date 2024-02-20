package test;


import com.github.javafaker.Faker;
import endpoints.UserEndPoint;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import payload.User;

public class UserTests {

    Faker faker;
    User userpayload;
    Logger logger;


    @BeforeTest
    public void setupData(){

        faker = new Faker();
        userpayload = new User();

        userpayload.setId(faker.idNumber().hashCode());
        userpayload.setFirstname(faker.name().firstName());
        userpayload.setUsername(faker.name().username());
        userpayload.setLastname(faker.name().lastName());
        userpayload.setEmail(faker.internet().emailAddress());
        userpayload.setPassword(faker.internet().password(5,10));
        userpayload.setPhone(faker.phoneNumber().cellPhone());


        System.out.println();
        logger= LogManager.getLogger(this.getClass());
    }

    @Test(priority = 1)
    public void testPostUser()
    {

        logger.info("User Creating");
        Response response = UserEndPoint.createUser(userpayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode() , 200);
        logger.info("User is created");
        System.out.println();
        System.out.println();

    }
    @Test(priority = 2)
    public void  testGetUserByName()
    {
        logger.info("reading user");
        Response response =UserEndPoint.readUser(this.userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("got user info");
    }

    @Test(priority = 3)
    public  void testUpdateUserByName(){

        logger.info("updating info user");
        userpayload.setFirstname(faker.name().firstName());
        userpayload.setLastname(faker.name().lastName());
        userpayload.setEmail(faker.internet().emailAddress());

        Response response =UserEndPoint.updateUser(this.userpayload.getUsername(), userpayload);
        response.then().log().body();

        Assert.assertEquals(response.getStatusCode(),200);

        // Check updated value

        Response afterresponse =UserEndPoint.readUser(this.userpayload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.statusCode(),200);
        logger.info("Updated");

    }

    @Test(priority = 4)
    public void testDeleteByName(){

        Response response = UserEndPoint.deleteUser(this.userpayload.getUsername());
logger.info("deleted");
    }
}
