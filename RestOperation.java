import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestOperation {
    public static void main(String[] args) {

        java.net.HttpURLConnection conn;
        try {

            URL url = new URL("http://localhost:3000/employees/1");
            conn = (HttpURLConnection) url.openConnection();
            System.out.println("****");
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");


            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }
            Scanner sc=new Scanner(url.openStream());
            String output="";
            while (sc.hasNext()){
                output+=sc.nextLine();
            }
            System.out.println("Json string....");
            System.out.println(output);
            ObjectMapper mapper=new ObjectMapper();
            EmployeeClass emp=mapper.readValue(output,EmployeeClass.class);
            System.out.println("Employee Object\n"+emp);

        } catch (
                IOException e) {

            e.printStackTrace();

        }
    }


}
