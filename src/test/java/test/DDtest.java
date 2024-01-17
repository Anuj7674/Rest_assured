package test;

import endpoints.UserEndPoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import payload.User;
import utilities.Dataproviders;

public class DDtest {
User userpayload;

    @Test(priority = 1 , dataProvider = "Data",dataProviderClass = Dataproviders.class)
    public void testPostUser(String userID , String userName, String fname ,String lname, String Usermail, String pwd , String phon){

        userpayload = new User();

        userpayload.setId(Integer.parseInt(userID));
        userpayload.setFirstname(fname);
        userpayload.setUsername(userName);
        userpayload.setLastname(lname);
        userpayload.setEmail(Usermail);
        userpayload.setPassword(pwd);
        userpayload.setPhone(phon);

        Response response = UserEndPoint.createUser(userpayload);
        Assert.assertEquals(response.getStatusCode(), 200);

    }

    @Test(priority=2,dataProvider = "UserNames",dataProviderClass = Dataproviders.class)
    public  void testDeleteUserByName(String userName){

        Response response = UserEndPoint.deleteUser(userName);
        Assert.assertEquals(response.getStatusCode(),200);
    }

}
