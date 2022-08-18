package main.charts;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
/*
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
*/
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    void printJson () {        
        
        JSONParser parser = new JSONParser();

        try {     
            JSONArray users = (JSONArray) parser.parse(new FileReader(new File(getClass().getClassLoader().getResource("utente.json").toURI())));

            for (Object user : users)
            {
              JSONObject person = (JSONObject) user;

              String nome = (String) person.get("name");
              System.out.println(nome);

              String cognome = (String) person.get("lastName");
              System.out.println(cognome);

              String email = (String) person.get("email");
              System.out.println(email);

              JSONArray conti = (JSONArray) person.get("banckAccounts");
              System.out.println("conti bancari");
              if (conti != null) {
                  for (Object conto : conti)
                  {
                      JSONObject banca = (JSONObject) conto;
    
                      String nameBanckAccount = (String) banca.get("nameBanckAccount");
                      System.out.println("  " + nameBanckAccount);
                  }
              }
              
              JSONArray moneyBoxes = (JSONArray) person.get("moneyBoxes");
              System.out.println("moneyBoxes");
              if (moneyBoxes != null) {
                  for (Object box : moneyBoxes)
                  {
                      JSONObject moneyBox = (JSONObject) box;
    
                      String nomeBox = (String) moneyBox.get("nameMoneyBox");
                      System.out.println("  " + nomeBox);
                  }
              }
              
              JSONArray invAccs = (JSONArray) person.get("InvestimentAccounts");
              System.out.println("account di investimento");
              for (Object invAcc : invAccs)
              {
                  JSONObject InvestimentAccount = (JSONObject) invAcc;

                  String nameInv = (String) InvestimentAccount.get("nameInvestimentAccount");
                  System.out.println("  " + nameInv);
              }
            }
           
        } catch (Exception ex) {
            System.out.println("oh shit! ");
            ex.printStackTrace();
        }
        
    }
    
    
    //search user
    boolean userExist (String username) {
        
        JSONParser parser = new JSONParser();
        
        try {
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(new FileReader(new File(getClass()
                    .getClassLoader().getResource("utente.json").toURI())));

            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                //System.out.println("input utente = " + username + ",  utente file json = " + userName);
                
                if (userName.equals(username)) {
                    return true;
                }
              
            }
            
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    
    boolean userPasswordCheck (String username, String password) {
        
        JSONParser parser = new JSONParser();
        
        try {
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(new FileReader(new File(getClass()
                    .getClassLoader().getResource("utente.json").toURI())));

            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                
                //System.out.println("input username = " + username + ",  username json = " + userName);
                
                String psw = (String) person.get("password");
                
                //System.out.println("input password = " + password + ",  password json = " + psw);
                
                if (userName.equals(username) && psw.equals(password)) {
                    return true;
                } 
              
            }
            
            return false;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    void initializeUser(String name, String lastName, String username, String email, String password) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            JSONObject utente = new JSONObject();
            utente.put("name", name);
            utente.put("lastName", lastName);
            utente.put("username", username);
            utente.put("email", email);
            utente.put("password", password);
    
            JSONArray list = new JSONArray();
    
            utente.put("banckAccounts", list);
            
            utente.put("moneyBoxes", list);
            
            utente.put("InvestimentAccounts", list);
            
            users.add(utente);
                
            FileWriter file = new FileWriter(input,false);
            
            System.out.println("scrivo su file");
            //System.out.println(users);
            file.write(users.toJSONString());
            file.flush();
            file.close();
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void newBanckAccount(String username, String nameBanckAccount) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                //System.out.println("input utente = " + username + ",  utente file json = " + userName);
                
                if (userName.equals(username)) {
                    
                    JSONArray banckAccounts = (JSONArray) person.get("banckAccounts");
                    
                    
                    JSONObject banckAccount = new JSONObject();
                    banckAccount.put("nameBanckAccount", nameBanckAccount);
                    
                    JSONArray list = new JSONArray();
                    banckAccount.put("transactions", list);
                    
                    banckAccounts.addAll(Arrays.asList(banckAccount));
                    
                    person.put("banckAccounts", banckAccounts);
                    
                    //System.out.println("il nuovo file json");
                    //System.out.println(users);
                        
                    FileWriter file = new FileWriter(input,false);
                    
                    //System.out.println("scrivo su file");
                    //System.out.println(users);
                    file.write(users.toJSONString());
                    file.flush();
                    file.close();
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    void newMoneyBox(String username, String nameMoneyBox) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                //System.out.println("input utente = " + username + ",  utente file json = " + userName);
                
                if (userName.equals(username)) {
                    
                    JSONArray moneyBoxes = (JSONArray) person.get("moneyBoxes");
                    
                    JSONObject moneyBox = new JSONObject();
                    moneyBox.put("nameMoneyBox", nameMoneyBox);
                    
                    JSONArray list = new JSONArray();
                    moneyBox.put("transactions", list);
                    
                    moneyBoxes.addAll(Arrays.asList(moneyBox));
                    
                    person.put("moneyBoxes", moneyBoxes);
                    
                    //System.out.println("il nuovo file json");
                    //System.out.println(users);
                        
                    FileWriter file = new FileWriter(input,false);
                    
                    //System.out.println("scrivo su file");
                    //System.out.println(users);
                    file.write(users.toJSONString());
                    file.flush();
                    file.close();
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void newInvestimentAccount(String username, String nameInvestimentAccount) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                //System.out.println("input utente = " + username + ",  utente file json = " + userName);
                
                if (userName.equals(username)) {
                    
                    JSONArray investimentAccounts = (JSONArray) person.get("investimentAccounts");
                    
                    JSONObject investimentAccount = new JSONObject();
                    investimentAccount.put("nameInvestimentAccount", nameInvestimentAccount);
                    
                    JSONArray list = new JSONArray();
                    investimentAccount.put("Asset", list);
                    
                    investimentAccounts.addAll(Arrays.asList(investimentAccount));
                    
                    person.put("investimentAccounts", investimentAccounts);
                    
                    //System.out.println("il nuovo file json");
                    //System.out.println(users);
                        
                    FileWriter file = new FileWriter(input,false);
                    
                    //System.out.println("scrivo su file");
                    //System.out.println(users);
                    file.write(users.toJSONString());
                    file.flush();
                    file.close();
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    void newAsset(String username, String nameInvestimentAccount, String symbolAsset, String nameAsset) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                //System.out.println("input utente = " + username + ",  utente file json = " + userName);
                
                if (userName.equals(username)) {
                    
                    JSONArray investimentAccounts = (JSONArray) person.get("InvestimentAccounts");
                    
                    for(Object a : investimentAccounts) {
                        
                        JSONObject investimentAccount = (JSONObject) a;
                        
                        String nameInvAcc = (String) investimentAccount.get("nameInvestimentAccount");
                        
                        if (nameInvAcc.equals(nameInvestimentAccount)) {
                            
                            JSONArray assets = (JSONArray) investimentAccount.get("assets");
                            
                            JSONObject asset = new JSONObject();
                            asset.put("nameAsset", nameAsset);
                            asset.put("symbolAsset", symbolAsset);
                            
                            JSONArray list = new JSONArray();
                            asset.put("transactions", list);
                            
                            assets.addAll(Arrays.asList(asset));
                            
                            investimentAccount.put("assets", assets);
                            
                            //System.out.println("il nuovo file json");
                            //System.out.println(users);
                                
                            FileWriter file = new FileWriter(input,false);
                            
                            //System.out.println("scrivo su file");
                            //System.out.println(users);
                            file.write(users.toJSONString());
                            file.flush();
                            file.close();
                            
                        }
                        
                    }
                    
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    void newBanckTransaction(String username, String nameBanckAccount, String nameTransaction, 
            double amount, String date, String time) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                
                
                if (userName.equals(username)) {
                   
                    JSONArray banckAccounts = (JSONArray) person.get("banckAccounts");
                    
                    for (Object c: banckAccounts) {
                        
                        JSONObject banckAccount = (JSONObject) c;
                        String nameAccount = (String) banckAccount.get("nameBanckAccount");
                        //System.out.println(nameAccount);
                        
                        if (nameAccount.equals(nameBanckAccount)) {
                            
                            JSONArray transactions = (JSONArray) banckAccount.get("transactions");
                            
                            JSONObject transaction = new JSONObject();
                            
                            transaction.put("nameTransaction", nameTransaction);
                            transaction.put("amount", amount);
                            transaction.put("date", date);
                            transaction.put("time", time);
                            
                            transactions.addAll(Arrays.asList(transaction));
                            
                            banckAccount.put("transactions", transactions);
                            
                            System.out.println("il nuovo file json");
                            System.out.println(users);
                                
                            FileWriter file = new FileWriter(input,false);
                            
                            //System.out.println("scrivo su file");
                            //System.out.println(users);
                            file.write(users.toJSONString());
                            file.flush();
                            file.close();
                            
                        }
                    }
                                        
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void newMoneyBoxTransaction(String username, String nameMoneyBox, String contractor, String currency, 
            double amount, String date, String time) {
        
        JSONParser parser = new JSONParser();
        
        try {
            File input = new File(getClass().getClassLoader().getResource("utente.json").toURI());
            
            FileReader reader = new FileReader(input);
            // create jsonArray from file
            JSONArray users = (JSONArray) parser.parse(reader);
            
            // read user
            for (Object user : users)
            {
                JSONObject person = (JSONObject) user;
                
                String userName = (String) person.get("username");
                
                
                if (userName.equals(username)) {
                   
                    JSONArray moneyBoxes = (JSONArray) person.get("moneyBoxes");
                    
                    for (Object c: moneyBoxes) {
                        
                        JSONObject moneyBox = (JSONObject) c;
                        String nameMBox = (String) moneyBox.get("nameMoneyBox");
                        //System.out.println(nameAccount);
                        
                        if (nameMBox.equals(nameMoneyBox)) {
                            
                            JSONArray transactions = (JSONArray) moneyBox.get("transactions");
                            
                            JSONObject transaction = new JSONObject();
                            
                            transaction.put("contractor", contractor);
                            transaction.put("amount", amount);
                            transaction.put("currency", currency);
                            transaction.put("date", date);
                            transaction.put("time", time);
                            
                            transactions.addAll(Arrays.asList(transaction));
                            
                            moneyBox.put("transactions", transactions);
                            
                            System.out.println("il nuovo file json");
                            System.out.println(users);
                                
                            FileWriter file = new FileWriter(input,false);
                            
                            //System.out.println("scrivo su file");
                            //System.out.println(users);
                            file.write(users.toJSONString());
                            file.flush();
                            file.close();
                            
                        }
                    }
                                        
                }
              
            }
            
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    

}
