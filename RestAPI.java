import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestAPI {

    private static Object HttpURLConnection;
    private static String get;

    // http://localhost:8080/RESTfulExample/json/product/get
    public static void main(String[] args) {

        HttpURLConnection conn;
        try {

            URL url = new URL("http://localhost:3000/employees");
            conn = (HttpURLConnection) url.openConnection();
            getMethod("GET", conn);
           // conn.setDoInput(false);
            conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            postMethod("POST",conn);

        } catch (
                IOException e) {

            e.printStackTrace();

        }
    }

    private static void postMethod(String post,HttpURLConnection conn) {
try {
    //conn.setDoOutput(true);
    conn.setRequestMethod(post);
    conn.setRequestProperty("Content-Type", "application/json");

    String input="{\"id\":5,\"first_name\":\"json\",\"last_name\":\"xyz\"}";
    OutputStream os = conn.getOutputStream();
    os.write(input.getBytes());
    os.flush();

    if (conn.getResponseCode() != 201) {
        throw new RuntimeException("Failed : HTTP error code : "
                + conn.getResponseCode());
    }

    BufferedReader br = new BufferedReader(new InputStreamReader(
            (conn.getInputStream())));

    String output;
    System.out.println("Output from Server .... \n");
    while ((output = br.readLine()) != null) {
        System.out.println(output);
    }

    conn.disconnect();



}catch (IOException ex){
    ex.printStackTrace();
}

    }



        private static void getMethod (String get, HttpURLConnection conn){
            try {
                conn.setRequestMethod(get);
                conn.setRequestProperty("Accept", "application/json");

                if (conn.getResponseCode() != 200) {
                    throw new RuntimeException("Failed : HTTP error code : "
                            + conn.getResponseCode());
                }
//InputStreamReader inputStream=new InputStreamReader(conn.getInputStream());
             //   BufferedReader br = new BufferedReader(inputStream);
                BufferedReader br = new BufferedReader(new InputStreamReader(
                                      conn.getInputStream()));

                String output;
                System.out.println("Output from Server .... \n");
                while ((output = br.readLine()) != null) {
                    System.out.println(output);
                }
                //inputStream.close();
               // br.close();

                conn.disconnect();
            } catch (IOException e) {

                e.printStackTrace();

            }

        }
    }

