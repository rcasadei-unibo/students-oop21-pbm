package main.charts;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.json.TransactionJson;

public class TableBuilder {
    
    public static TableView buildTable(TransactionJson[] transaction, String sDate1, String sDate2) throws ParseException{ 
        Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Date date2 = new SimpleDateFormat("dd/MM/yyyy").parse(sDate2);    
        
        TableView tableView = new TableView();

        TableColumn<Object, Object> column1 = new TableColumn<>("Transazione");
        
        column1.setCellValueFactory(new PropertyValueFactory<>("Date"));


        TableColumn<Object, Object> column2 = new TableColumn<>("Data");
        
        column2.setCellValueFactory(new PropertyValueFactory<>("Data"));
        
        TableColumn<Object, Object> column3 = new TableColumn<>("Ora");
                
        column3.setCellValueFactory(new PropertyValueFactory<>("Ora"));
        
        TableColumn<Object, Object> column4 = new TableColumn<>("Data");
                
        column4.setCellValueFactory(new PropertyValueFactory<>("Importo"));


        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);
        tableView.getColumns().add(column3);
        tableView.getColumns().add(column4);
        
        TransactionJson[] prova = TestChart.esempioTransaction();
        for(int i=0; i<transaction.length; i++) {
            Date dateTrans = new SimpleDateFormat("dd/MM/yyyy").parse(transaction[i].getDate());
            
            if(dateTrans.after(date1) && dateTrans.before(date2)) {
                List<Object> list = ReflexionUtils.getListOfFields(transaction[i]);
                
                tableView.getItems().add(list);
                
            }
            
        }
        
        return tableView;
    }
    
    public static class ReflexionUtils {

        public static List<Object>  getListOfFields(Object bean){
          List<Object> result = new ArrayList<Object>();
          for (Field f : bean.getClass().getDeclaredFields()) {
            try{
              String name = f.getName();
              name = name.substring(0,1).toUpperCase() + name.substring(1);
              Method m = bean.getClass().getDeclaredMethod("get"+name, null);
              Object o = m.invoke(bean, null);
              result.add(o);
            } catch (Exception e){
              System.out.println(e.getMessage());
            }
          }
          return result;
        }
      }
}
