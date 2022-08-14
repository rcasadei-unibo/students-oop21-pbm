package main.charts;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class OperationJSONUtente {
    private String username;
    private String password;
    double totalAmount;
    /*
    public OperationJSONUtente(String username, String password) {
        this.username = username;
        this.password = password;
    }*/
    
    //this method check if the username and password exist in the JSON file utente 
    boolean userPasswordCheck (String username, String password) {
        
        System.out.println("what's up bro ");
        
        try {
            // create a reader
            String fileName = "utente.json";
            Path pathToFile = Paths.get(fileName);
            System.out.println(pathToFile.toAbsolutePath());
            Reader reader = Files.newBufferedReader(pathToFile.toAbsolutePath());

            // create parser
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

            // read user
            JsonArray users = (JsonArray) parser.get("users");
            Iterator<Object> iterator = users.iterator();
            System.out.println("hey man ");
            while(iterator.hasNext()) {
                JsonObject user = (JsonObject) iterator.next();
                System.out.println("ciao ");
                // check username and password
                if(((String)user.get("username") == username) && ((String)user.get("budget") == password)) {
                    //close reader
                    reader.close();
                    return true;
                }
            }

            //close reader
            reader.close();
            return false;

        } catch (Exception ex) {
            System.out.println("oh shit! ");
            ex.printStackTrace();
            return false;
        }
    }
    
    
    //search user
    boolean userExist (String username) {
        
        try {
            // create a reader
            Reader reader = Files.newBufferedReader(Paths.get("utente.json"));

            // create parser
            JsonObject parser = (JsonObject) Jsoner.deserialize(reader);

            // read user
            JsonArray users = (JsonArray) parser.get("users");
            Iterator<Object> iterator = users.iterator();
            while(iterator.hasNext()) {
                JsonObject user = (JsonObject) iterator.next();
                // check username and password
                if(((String)user.get("username") == username)) {
                    //close reader
                    reader.close();
                    return true;
                }
            }

            //close reader
            reader.close();
            return false;

        } catch (Exception ex) {
            System.out.println("oh shit! ");
            ex.printStackTrace();
            return false;
        }
    }
    
    
    

}
