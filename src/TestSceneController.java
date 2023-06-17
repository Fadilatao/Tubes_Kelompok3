import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TestSceneController {

    @FXML
    private AnchorPane root;
    

    @FXML
    void Analytics_Clicked(ActionEvent event) throws Exception {
        Parent analyticsParent = FXMLLoader.load(getClass().getResource("views/Analytics_Scene.fxml"));
        Scene AnalyticsScene = new Scene(analyticsParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(AnalyticsScene);
        window.show();
    }

    @FXML
    void Budgets_Clicked(ActionEvent event)throws Exception {
        Parent BudgetsParent = FXMLLoader.load(getClass().getResource("views/Budgets_Scene.fxml"));
        Scene BudgetsScene = new Scene(BudgetsParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(BudgetsScene);
        window.show();
    }

    @FXML
    void Records_Clicked(ActionEvent event) throws Exception {
        Parent RecordsParent = FXMLLoader.load(getClass().getResource("views/Record_Scene.fxml"));
        Scene RecordsScene = new Scene(RecordsParent);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(RecordsScene);
        window.show();
    }
  
    

}