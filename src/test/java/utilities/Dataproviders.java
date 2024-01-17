package utilities;

import org.testng.annotations.DataProvider;
import utilities.XLutilites;

import java.io.IOException;

public class Dataproviders {


    @DataProvider(name = "Data")
    public Object[][] getAllData() throws IOException
    {
        String path = System.getProperty("user.dir")+ ("/testData//Userdata.xlsx");

        XLutilites XL = new XLutilites(path);
        int rownum= XL.getRowCount("Sheet1");
        int colcount= XL.getCellCount("Sheet1",rownum);

        Object apidata[][] = new Object[rownum][colcount];

        for(int i = 1;i<=rownum;i++){

            for (int j=0;  j<colcount;j++){

                apidata[i-1][j]=XL.getCellData("Sheet1" , i,j);
            }
        }
        return  apidata;

    }

    @DataProvider(name = "UserNames")
    public Object[] getUserName() throws  IOException
    {
        String path = System.getProperty("user.dir")+ ("/testData//Userdata.xlsx");
        XLutilites XL = new XLutilites(path);
        int rowcount= XL.getRowCount("Sheet1");
        Object apidata[] = new Object[rowcount];
        for (int i =1 ; i<= rowcount ; i++){

            apidata[i-1] =  XL.getCellData("Sheet1",i,1);

        }
        return apidata;

    }
}
