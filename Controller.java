package sample;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author Villan
 */
public class Controller implements Initializable {
    func func = new func();

    ObservableList list= FXCollections.observableArrayList();
    ListView<String> listOf = new ListView<String>();
    @FXML
    private ChoiceBox<String>  choiceBox1;
    @FXML
    private ChoiceBox<String>  choiceBox2;
    @FXML
    private ListView profList;

    @FXML
    private Button profButton;
    @FXML
    private AnchorPane anchorRoot;
    @FXML
    private StackPane parentContainer;
    @FXML
    private ListView specTable;

    @FXML
    void btn1handle(ActionEvent event) throws SQLException {
        ArrayList<String> prof=func.findProf(choiceBox1.getSelectionModel().getSelectedItem(),choiceBox2.getSelectionModel().getSelectedItem());
        profList.getItems().addAll(prof);
    }

    @FXML
    void findbtnhandler(ActionEvent event)throws SQLException{
        ArrayList<String> univ=func.findUniv(profList.getSelectionModel().getSelectedItems().toString().substring(1,profList.getSelectionModel().getSelectedItems().toString().length()-1));
        System.out.println(profList.getSelectionModel().getSelectedItems().toString());
        //System.out.println(univ);
        for(int i = 0; i <univ.size();i++){
            System.out.println(univ.get(i));
            //specTable.getItems().add(univ.get(i).toString());
        }
        specTable.getItems().addAll(univ);
        //specTable.getItems().setAll(univ.toArray());
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            load();
        }catch (SQLException e){
            System.out.println(e);
        }

    }


    @FXML
    public  void load() throws SQLException {
        list.removeAll(list);
        list.addAll("Математика","Физика","Биология","Всемирная история","Казахский /Русский язык" , "Химия","Творческий экзамен","Иностранный язык",
                "География","Человек. Общество. Право");
        list.sorted();
        choiceBox1.getItems().addAll(list);
        choiceBox2.getItems().addAll(list);

    }

}