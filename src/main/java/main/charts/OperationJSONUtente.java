package main.charts;

import java.io.Reader;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.Map;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

public class OperationJSONUtente {
    String username;
    double totalAmount;
    
    boolean userExist (String username, String password) {
        
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
            ex.printStackTrace();
            return false;
        }
    }

}
