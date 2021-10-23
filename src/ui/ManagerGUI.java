package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Teams;

import java.io.File;
import java.io.IOException;

public class ManagerGUI {



    public  ManagerGUI(){
        mainStage = new Stage();
        popUpStage = new Stage();
    }
    //-------------------------------------------------------- ALL WINDOWS CODE --------------------------------------------------------
    Stage mainStage;
    Stage popUpStage;

    File selectedFile;

    @FXML
    void ALLWINDOWScancel(ActionEvent event) throws IOException {
        showMenu();
    }
    //-------------------------------------------------------- MENU CODE --------------------------------------------------------

    @FXML
    void MENUloadACsv(ActionEvent event) throws IOException {
        showLoadCsv();
    }

    @FXML
    void MENUeditDatabase(ActionEvent event) {

    }

    @FXML
    void MENUsearch(ActionEvent event) {

    }

    @FXML
    void MENUaddPlayer(ActionEvent event) throws IOException {
        showAddPlayer();
    }

    //-------------------------------------------------------- LOAD CSV CODE --------------------------------------------------------

    @FXML
    void LOADCSVdone(ActionEvent event) throws IOException {
        showMenu();
    }

    @FXML
    void LOADCSVfileDropped(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
        selectedFile = event.getDragboard().getFiles().get(0);
    }

    @FXML
    void LOADCSVchoose(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        selectedFile = fileChooser.showOpenDialog(mainStage);
    }

    //-------------------------------------------------------- ADD A PLAYER CODE --------------------------------------------------------

    @FXML
    private TextField ADDPLAYERname;

    @FXML
    private TextField ADDPLAYERlastNames;

    @FXML
    private ComboBox<Teams> ADDPLAYERteam;

    @FXML
    private TextField ADDPLAYERtrueShooting;

    @FXML
    private TextField ADDPLAYERusage;

    @FXML
    private TextField ADDPLAYERassist;

    @FXML
    private TextField ADDPLAYERrebound;

    @FXML
    private TextField ADDPLAYERdefensive;

    @FXML
    private TextField ADDPLAYERage;

    @FXML
    void ADDPLAYERdone(ActionEvent event) {

    }



    //-------------------------------------------------------- SHOW WINDOWS CODE --------------------------------------------------------

    public void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        mainStage.setScene(e);
        popUpStage.hide();
        mainStage.show();

    }

    public void showLoadCsv() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadCsv.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        popUpStage.setScene(e);
        mainStage.hide();
        popUpStage.show();
    }

    public void showAddPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        popUpStage.setScene(e);
        mainStage.hide();
        popUpStage.show();
    }
}
