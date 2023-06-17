import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.time.LocalDate;
// import java.sql.LocalDate;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class IncomeController {
    @FXML
    private ChoiceBox<String> Category_field;

    @FXML
    private DatePicker Date_Field;

    @FXML
    private TextArea Notes_Field;
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

    public void initialize() {
        refresh();
    }

    public void refresh() {
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

        constraint();
        setCategory();
    }

    public void setCategory() {

        Category_field.getItems().add("Hadiah");
        Category_field.getItems().add("Gaji");
    }

    public String categoryGet() {
        String category_Value;
        category_Value = Category_field.getValue();
        return category_Value;
    }

    public void constraint() {
        Date_Field.setDayCellFactory(picker -> new DateCell() {

            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                if (date.isAfter(LocalDate.now())) {
                    setDisable(true);
                    setStyle("-fx-background-color: #cccccc;"); // Optionally, style the disabled dates
                }
            }
        });
    }

    public Date dateGet() {
        LocalDate value = Date_Field.getValue();
        if (value != null) {
            return java.sql.Date.valueOf(value);
        } else {
            return null;
        }
    }

    public String notesGet() {
        String Notes_value = null;
        Notes_value = Notes_Field.getText();
        return Notes_value;
    }

    public Double totalGet() {
        String total = Total_Field.getText();
        if (!total.isEmpty()) {
            try {
                double doubleValue = Double.parseDouble(total);
                return doubleValue;
            } catch (NumberFormatException e) {

                System.out.println("Invalid input. Unable to convert the string to a double.");
            }
        }

        return 0.0;
    }

    public void AddincomeToMysql() {

        try {
            Connection connection;
            PreparedStatement pst;
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost/incomedb", "root", "");
            System.out.println("connection success");
            String sql = "INSERT INTO `incomelist` (`Timestamp`, `Category`, `Total`, `Notes`) VALUES (?, ?, ?, ?)";
            pst = connection.prepareStatement(sql);
            try {
                pst.setDate(1, dateGet());
                pst.setString(2, categoryGet());
                pst.setDouble(3, totalGet());
                pst.setString(4, notesGet());
                pst.execute();
                System.out.println("adding data to mysql table success");
                clear();

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("Success");
                alert.setHeaderText(null);
                alert.setContentText("Save successful!");
                alert.showAndWait();

            } catch (Exception e) {
                System.out.println("adding data to mysql table failed");
            }
        } catch (Exception e) {
            System.out.println("connect failed");
        }

    }

    public void clear() {
        Category_field.setValue(null); // Clear the selected value in the ChoiceBox
        Date_Field.setValue(null); // Clear the selected date in the DatePicker
        Notes_Field.clear(); // Clear the text in the TextArea
        Total_Field.clear(); // Clear the text in the TextField
    }

    public void deleteCharacter() {
        String currentText = Total_Field.getText();
        if (!currentText.isEmpty()) {
            String newText = currentText.substring(0, currentText.length() - 1);
            Total_Field.setText(newText);
        }
    }
}
