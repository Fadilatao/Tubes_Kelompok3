import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class BalanceController {
    @FXML
    private TextField Total_Field;

    @FXML
    private Button eight;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;

    public void initialize(){
        zero.setOnAction(e -> Total_Field.appendText("0"));
        one.setOnAction(e -> Total_Field.appendText("1"));
        two.setOnAction(e -> Total_Field.appendText("2"));
        three.setOnAction(e -> Total_Field.appendText("3"));
        four.setOnAction(e -> Total_Field.appendText("4"));
        five.setOnAction(e -> Total_Field.appendText("5"));
        six.setOnAction(e -> Total_Field.appendText("6"));
        seven.setOnAction(e -> Total_Field.appendText("7"));
        eight.setOnAction(e -> Total_Field.appendText("8"));
        nine.setOnAction(e -> Total_Field.appendText("9"));

        Total_Field.setEditable(false);


    }
    public double totalGet(){
        String total = Total_Field.getText() ;
        if (!total.isEmpty()) {
        try {
            double doubleValue = Double.parseDouble(total);
            return doubleValue;
        }catch (NumberFormatException e) {
            
            System.out.println("Invalid input. Unable to convert the string to a double.");
        }
        }

        return 0.0  ;  
    }
    public void connectdb(){
        try {
            Connection connection;
            PreparedStatement pst;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/budgetdb", "root","");
            System.out.println("Connect success");
            String sql = "INSERT INTO `incomelist` (`total`) VALUES (?)";
            pst = connection.prepareStatement(sql);

            try{
                pst.setDouble(1,totalGet());
                System.out.println("adding to mysql table succes");
            }catch(Exception e){
                System.out.println("failed");
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
