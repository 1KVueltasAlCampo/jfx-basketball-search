package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Manager;
import model.Teams;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ManagerGUI {



    public  ManagerGUI() throws IOException {
        mainStage = new Stage();
        popUpStage = new Stage();

        manager = new Manager();
        showMenu();
    }

    //-------------------------------------------------------- ALL WINDOWS CODE --------------------------------------------------------
    Stage mainStage;
    Stage popUpStage;

    Manager manager;

    File selectedFile;

    private void missingInfo(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Missing information!");
        alert.setHeaderText("Looks like you've skipped some field");
        alert.setContentText("Fill all the fields and try again");
        alert.show();
    }

    private void wrongFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid format!");
        alert.setHeaderText("Looks like some field has a different format");
        alert.setContentText("Check the formats and try again");
        alert.show();
    }


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
    void MENUeditDatabase(ActionEvent event) throws IOException{

    }

    @FXML
    void MENUsearch(ActionEvent event) throws IOException {
    showSearch();
    }

    @FXML
    void MENUaddPlayer(ActionEvent event) throws IOException {
        showAddPlayer();
    }

    //-------------------------------------------------------- LOAD CSV CODE --------------------------------------------------------

    @FXML
    void LOADCSVdone(ActionEvent event) throws IOException {
        manager.readCsv(selectedFile);
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
    private TextField ADDPLAYERage;

    @FXML
    private TextField ADDPLAYERpointsPerGame;

    @FXML
    private TextField ADDPLAYERRebounds;

    @FXML
    private TextField ADDPLAYERassist;

    @FXML
    private TextField ADDPLAYERsteals;

    @FXML
    private TextField ADDPLAYERblocks;


    @FXML
    private ComboBox<Teams> ADDPLAYERteam;

    @FXML
    void ADDPLAYERdone(ActionEvent event) {

    }

    //-------------------------------------------------------- SEARCH CODE --------------------------------------------------------

    @FXML
    private ComboBox<String> SEARCHfilter;

    @FXML
    private TableView<?> SEARCHplayersTv;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcFullname;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcAge;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcTeam;

    @FXML
    private TableColumn<?, ?> SEARCHplayersPointsPerGame;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcRebounds;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcAssists;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcSteals;

    @FXML
    private TableColumn<?, ?> SEARCHplayersTcBlocks;

    @FXML
    private TextField SEARCHfilterFrom;

    @FXML
    private TextField SEARCHfilterTo;


    @FXML
    private Button SEARCHcompareButton;

    @FXML
    void SEARCHfilterChanged(ActionEvent event) {
        if(SEARCHfilter.getValue().equals("Points per game") || SEARCHfilter.getValue().equals("Assists")){
            SEARCHcompareButton.setVisible(true);
        }else{
            SEARCHcompareButton.setVisible(false);

        }
    }

    @FXML
    void SEARCHsearch(ActionEvent event) {
        System.out.println(SEARCHfilter.getValue());
        switch (SEARCHfilter.getVisibleRowCount()){

        }
        //manager.rangeSearch(SEARCHfilter,SEARCHfilterFrom,SEARCHfilterTo);
    }

    @FXML
    void SEARCHsearchAndCompare(ActionEvent event) {

    }

    //-------------------------------------------------------- SHOW WINDOWS CODE --------------------------------------------------------

    private void showMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        mainStage.setScene(e);
        popUpStage.hide();
        mainStage.show();

    }

    private void showLoadCsv() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("LoadCsv.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        popUpStage.setScene(e);
        mainStage.hide();
        popUpStage.show();
    }

    private void showAddPlayer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        popUpStage.setScene(e);
        mainStage.hide();

        ObservableList<Teams> items = FXCollections.observableArrayList(manager.getTeams());
        ADDPLAYERteam.setItems(items);
        popUpStage.show();
    }

    private void showSearch() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene e = new Scene(root);
        popUpStage.setScene(e);
        mainStage.hide();

        ArrayList<String> items = new ArrayList<>();
        items.add("Points per game");
        items.add("Assists");
        items.add("Rebounds");
        items.add("Steals");
        items.add("Blocks");


        ObservableList<String> filterItems = FXCollections.observableArrayList(items);

        SEARCHfilter.setItems(filterItems);

        popUpStage.show();
    }


}
