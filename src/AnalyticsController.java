import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;
import model_class.analysis;
import model_class.expense;
import model_class.income;


public class AnalyticsController {

    @FXML
    private AnchorPane root;
    
    @FXML
    private PieChart expense_chart;
    @FXML
    private AnchorPane side_anchor;


    @FXML
    private PieChart income_chart;
    ObservableList<expense> List1 =  FXCollections.observableArrayList();
    ObservableList<income> List2 =  FXCollections.observableArrayList();
    @FXML
    void Analytics_Clicked(ActionEvent event) throws Exception {
        Parent analyticsParent = FXMLLoader.load(getClass().getResource("Analytics_Scene.fxml"));
        Scene AnalyticsScene = new Scene(analyticsParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AnalyticsScene);
        window.show();
    }

   
    @FXML
    void Records_Clicked(ActionEvent event) throws Exception {
        Parent RecordsParent = FXMLLoader.load(getClass().getResource("Record_Scene.fxml"));
        Scene RecordsScene = new Scene(RecordsParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RecordsScene);
        window.show();
    }
    @FXML
    void budgets_Clicked(ActionEvent event) throws Exception {
        Parent BudgetParent = FXMLLoader.load(getClass().getResource("Budget_Scene.fxml"));
        Scene BudgetScene = new Scene(BudgetParent);

        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(BudgetScene);
        window.show();
    } 
    // ===================================================================================== 
    public void initialize(){
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/expensedb", "root","");
            preparedStatement = connection.prepareStatement("SELECT * FROM `expenseList`");
            System.out.println("Expense Analitics Connect success");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
               List1.add(new expense(rs.getDate("Timestamp"),
                rs.getString("Category"),
                rs.getDouble("Total"),
                rs.getString("Notes")));
                    
            }             
        } catch (Exception e) {
            System.out.println("connect failed");
        }
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();
        Map<String, Double> categoryMap = new HashMap<>();

        for (expense item : List1) {
            String category = item.getCategory();
            double value = item.getTotal();
            
      
            if (categoryMap.containsKey(category)) {
              
                double currentTotal = categoryMap.get(category);
                double newTotal = currentTotal + value;
                categoryMap.put(category, newTotal);
            } else {
              
                categoryMap.put(category, value);
            }
        }

        
        for (Map.Entry<String, Double> entry : categoryMap.entrySet()) {
            String category = entry.getKey();
            double value = entry.getValue();
            chartData.add(new PieChart.Data(category, value));
        }

        double Sum = chartData.stream().mapToDouble(PieChart.Data::getPieValue).sum();
        chartData.forEach(data -> 
        data.setName(data.getName() + " (" + analysis.SumPercentage(Sum, data.getPieValue()) + ")"));

        expense_chart.setData(chartData);
        expense_chart.setLegendVisible(true);

         try {
            Connection connection2;
            PreparedStatement preparedStatement2;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection2 = DriverManager.getConnection("jdbc:mysql://localhost/incomedb", "root","");
            preparedStatement2 = connection2.prepareStatement("SELECT * FROM `incomelist`");
            System.out.println("Income connect success");
            ResultSet rs = preparedStatement2.executeQuery();
            while (rs.next()) {
                List2.add(new income(rs.getDate("Timestamp"),
                rs.getString("Category"),
                rs.getDouble("Total"),
                rs.getString("Notes")));
                
            }

        } catch (Exception e) {
            System.out.println("conncect failed");
        }
        ObservableList<PieChart.Data> chartData2 = FXCollections.observableArrayList();
        Map<String, Double> categoryMap2 = new HashMap<>();

        for (income item : List2) {
            String category2 = item.getCategory();
            double value = item.getTotal();
            
            
            if (categoryMap.containsKey(category2)) {
              
                double currentTotal2 = categoryMap.get(category2);
                double newTotal2 = currentTotal2 + value;
                categoryMap2.put(category2, newTotal2);
            } else {
                
                categoryMap2.put(category2, value);
            }
        }

         
        for (Map.Entry<String, Double> entry : categoryMap2.entrySet()) {
            String category2 = entry.getKey();
            double value2 = entry.getValue();
            chartData2.add(new PieChart.Data(category2, value2));
        }

        double Sum2 = chartData2.stream().mapToDouble(PieChart.Data::getPieValue).sum();
        chartData2.forEach(data -> 
        data.setName(data.getName() + 
        " (" + analysis.SumPercentage(Sum2, data.getPieValue()) 
        + ")"));

        income_chart.setData(chartData2);
        income_chart.setLegendVisible(true);
    }
    

}

