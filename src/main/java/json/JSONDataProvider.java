package json;


//DEPENDENCIES IN MAVEN
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * TestNG DataProvider CLass for extracting JSON  data
 */
public class JSONDataProvider {

    //local variables
    public static String dataFile = "";
    public static String testCaseName = "NA";

    public JSONDataProvider() {
    }


    /**
     * fetchData - generic DataProvider method that extracts data by JSON key:value pairs
     * @param method
     * @return
     * @throws Exception
     */
    @DataProvider(name = "myData_JSON")
    public static Object[][] fetchData(Method method) throws Exception {
        Object rowID, description;
        Object result[][];
        testCaseName = method.getName();
        JSONArray testData = (JSONArray) extractData_JSON(dataFile).get(method.getName());
        List<JSONObject> testDataList = new ArrayList<JSONObject>();
        for(int i=0; i < testData.size(); i++){ //add information of the JSONFile
        testDataList.add((JSONObject)testData.get(i));
        }

        //include filter Placeholder
        if ( System.getProperty("includePattern") != null ) {
            String include = System.getProperty("includePattern");
            List<JSONObject> newList = new ArrayList<JSONObject>();
            List<String> tests = Arrays.asList(include.split(",", -1));

            for ( String getTest : tests ) {
                for ( int i = 0; i < testDataList.size(); i++ ) {
                    if ( testDataList.get(i).toString().contains(getTest) ) {
                        newList.add(testDataList.get(i));
                    }
                }
            }

            // reassign testRows after filtering tests
            testDataList = newList;
        }

        //exclude Filter PlaceHolder
        if ( System.getProperty("excludePattern") != null ) {
            String exclude =System.getProperty("excludePattern");
            List<String> tests = Arrays.asList(exclude.split(",", -1));

            for ( String getTest : tests ) {
                // start at end of list and work backwards so index stays in sync
                for ( int i = testDataList.size() - 1 ; i >= 0; i-- ) {
                    if ( testDataList.get(i).toString().contains(getTest) ) {
                        testDataList.remove(testDataList.get(i));
                    }
                }
            }
        }



        //create object for dataprovider to return
        try {
            result = new Object[testDataList.size()][testDataList.get(0).size()];
            for (int i = 0; i < testDataList.size(); i++) {
                result[i] = new Object[]{testData.get(i)};
            }
        }catch (IndexOutOfBoundsException ie){
            result = new Object[0][0];
        }

        //add in rowID and description for later use
        try{
            result = new Object[testDataList.size()][testDataList.get(0).size()];

            for(int i = 0; i<testDataList.size(); i++){
            rowID = testDataList.get(i).get("rowID");
            description = testDataList.get(i).get("descripcion");
            result[i] = new Object[]{rowID, description, testDataList.get(i)};
            }
        }catch (IndexOutOfBoundsException ie){
            result = new Object[0][0];
        }


        return result;
    }

    /**
     * extractData_JSON - method to extract JSON data from a FILE
     *
     * @param file
     * @return
     * @throws Exception
     */
    public static JSONObject extractData_JSON(String file) throws Exception {
        FileReader reader = new FileReader(file);
        JSONParser jsonParser = new JSONParser();
        return (JSONObject) jsonParser.parse(reader);
    }

    public static String lookupMessage(String propFilePath, String code) throws Exception{
        Properties props = new Properties();
        props.load(new FileInputStream(propFilePath));
        String getMsg = props.getProperty(code,null);
        if(getMsg != null){
            return getMsg;
        }
        else{
            throw new  Exception("ERROR: The code" + code + "Was not found!");
        }

    }


}
