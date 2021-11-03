package ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Manager;
import model.Player;
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
        alert.setHeaderText("Looks like you've skipped some field.");
        alert.setContentText("Fill all the fields and try again");
        alert.show();
    }

    private void wrongFormat(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Invalid format!");
        alert.setHeaderText("Looks like some field has a different format.");
        alert.setContentText("Check the formats and try again");
        alert.show();
    }

    private void closeCSV(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("The file is being used");
        alert.setHeaderText("Looks like the file you are trying to modify is open by another program or you do not have access to it");
        alert.setContentText("Close the program or modify the access permissions and try again");
        alert.show();
    }

    private void dataRequired(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Data required!");
        alert.setHeaderText("Looks like there is no data uploaded.");
        alert.setContentText("Upload a file or add a player to search information");
        alert.show();
    }


    @FXML
    void ALLWINDOWScancel(ActionEvent event) throws IOException {
        if(selectedFile!=null){
            manager.readCsv(selectedFile);
        }
        showMenu();
    }


    //-------------------------------------------------------- MENU CODE --------------------------------------------------------

    @FXML
    void MENUloadACsv(ActionEvent event) throws IOException {
        showLoadCsv();
    }

    @FXML
    void MENUsearch(ActionEvent event) throws IOException {
        if(manager.getActualFile()!=null){
            showSearch();
        }
        else{
            dataRequired();
        }

    }

    @FXML
    void MENUaddPlayer(ActionEvent event) throws IOException {
        if(manager.getActualFile()!=null){
            showAddPlayer();
        }
        else{
            dataRequired();
        }
    }

    //-------------------------------------------------------- LOAD CSV CODE --------------------------------------------------------

    private void fileAcceptedAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Load finished!");
        alert.setContentText("Looks like all worked great");
        alert.setContentText("File successfully loaded");
        alert.showAndWait();
    }

    @FXML
    void LOADCSVdone(ActionEvent event) throws IOException {
        manager.readCsv(selectedFile);
        showMenu();
    }

    @FXML
    void LOADCSVfileDropped(DragEvent event) {
        event.acceptTransferModes(TransferMode.ANY);
        selectedFile = event.getDragboard().getFiles().get(0);

        if(event.getDragboard().getFiles().get(0) != null ){
            fileAcceptedAlert();
        }
    }

    @FXML
    void LOADCSVchoose(ActionEvent event) {
        File oldFile = selectedFile;

        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Csv files", "*.csv"));
        File suggestedDirectory = new File("@../../data");
        fileChooser.setInitialDirectory(suggestedDirectory);

        selectedFile = fileChooser.showOpenDialog(mainStage);

        if(selectedFile != oldFile){
            fileAcceptedAlert();
        }
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
    private TextField ADDPLAYERrebounds;

    @FXML
    private TextField ADDPLAYERassist;

    @FXML
    private TextField ADDPLAYERsteals;

    @FXML
    private TextField ADDPLAYERblocks;


    @FXML
    private ComboBox<Teams> ADDPLAYERteam;

    void playerCreatedAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Player created!");
        alert.setHeaderText("Looks like all worked perfectly");
        alert.setContentText("Player added");
        alert.show();
    }

    @FXML
    void ADDPLAYERdone(ActionEvent event) {
        String names = ADDPLAYERname.getText();
        String lastNames = ADDPLAYERlastNames.getText();
        String ageS = ADDPLAYERage.getText();
        Teams team = ADDPLAYERteam.getValue();
        String pPGS = ADDPLAYERpointsPerGame.getText();
        String reboundsS = ADDPLAYERrebounds.getText();
        String assistsS = ADDPLAYERassist.getText();
        String stealsS = ADDPLAYERsteals.getText();
        String blocksS = ADDPLAYERblocks.getText();

        if(names.equals("") || lastNames.equals("") || ageS.equals("") || pPGS.equals("")
                || reboundsS.equals("") || assistsS.equals("") || stealsS.equals("") || blocksS.equals("") || team == null){

            missingInfo();
        }else{
            try{
                manager.addPlayer(names+" "+lastNames, ageS,team.toString(), pPGS, reboundsS, assistsS, stealsS, blocksS);
                playerCreatedAlert();

                showAddPlayer();

            }catch (Exception e){
                wrongFormat();
            }
        }
    }

    //-------------------------------------------------------- SEARCH CODE --------------------------------------------------------

    private final ObservableList<Player> dataList = FXCollections.observableArrayList();

    String [] comparation = new String[2];
    String comparationMsg = "";

    Player selectedPlayer;

    @FXML
    private ComboBox<String> SEARCHfilter;

    @FXML
    private TableView<Player> SEARCHplayersTv;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcFullname;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcAge;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcTeam;

    @FXML
    private TableColumn<Player, String> SEARCHplayersPointsPerGame;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcRebounds;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcAssists;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcSteals;

    @FXML
    private TableColumn<Player, String> SEARCHplayersTcBlocks;

    @FXML
    private TextField SEARCHfilterFrom;

    @FXML
    private TextField SEARCHfilterTo;


    @FXML
    private Button SEARCHcompareButton;

    private void showTimeAlert(int n,String comparation){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Looks like the search it's done");
        if(n == 2){
            String[] parts=comparation.split("°");
            comparationMsg = "The search using a binary search tree it's: "+ parts[0] + "\n" + " and using a balanced tree it's: " + parts[1];
        }else{
            comparationMsg = "The search took: " + comparation;
        }
        alert.setContentText(comparationMsg);
        alert.showAndWait();
    }
    @FXML
    void SEARCHeditPlayer(MouseEvent event) {

    }

    @FXML
    void SEARCHEditFullName(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,0);
    }

    @FXML
    void SEARCHEditAge(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,1);
    }

    @FXML
    void SEARCHEditAssists(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,5);
    }

    @FXML
    void SEARCHEditBlocks(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,7);
    }

    @FXML
    void SEARCHEditPpg(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,3);
    }

    @FXML
    void SEARCHEditRebounds(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,4);
    }

    @FXML
    void SEARCHEditSteals(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,6);
    }

    @FXML
    void SEARCHEditTeam(TableColumn.CellEditEvent<Player, String> event) throws IOException {
        int index=event.getRowValue().getIndex();
        manager.updateCSV(event.getNewValue(),index,2);
    }

    @FXML
    void SEARCHfilterChanged(ActionEvent event) {
        if(SEARCHfilter.getValue().equals("Points per game") || SEARCHfilter.getValue().equals("Steals")){
            SEARCHcompareButton.setVisible(true);
        }else{
            SEARCHcompareButton.setVisible(false);

        }
    }

    @FXML
    void SEARCHsearch(ActionEvent event) throws IOException {
        dataList.clear();
        String searchTime="";
        ArrayList<String> aL = new ArrayList<>();
        Double from = Double.parseDouble(SEARCHfilterFrom.getText());
        Double to = Double.parseDouble(SEARCHfilterTo.getText());
        switch (SEARCHfilter.getValue()) {
            case "Points per game":
                aL = manager.rangeSearch(3, from, to);
                break;
            case "Assists":
                aL = manager.rangeSearch(4,from,to);
                break;
            case "Rebounds":
                aL = manager.rangeSearch(5,from,to);
                break;
            case "Steals":
                aL=manager.rangeSearch(6,from, to);
                break;
            case "Blocks":
                aL = manager.rangeSearch(7,from, to);
                break;
        }

        for (int i = 0; i < aL.size(); i++) {
            String[] parts = aL.get(i).split(manager.getSEPARATOR());
            Player p = new Player(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],Integer.parseInt(parts[parts.length-1]));
            dataList.add(p);
        }

        refreshSearchTV();

        String info = manager.getTime();
        showTimeAlert(1,info);
    }

    @FXML
    void SEARCHsearchAndCompare(ActionEvent event) throws IOException {
        dataList.clear();
        String searchTime="";
        ArrayList<String> aL = new ArrayList<>();
        Double from = Double.parseDouble(SEARCHfilterFrom.getText());
        Double to = Double.parseDouble(SEARCHfilterTo.getText());
        switch (SEARCHfilter.getValue()) {
            case "Points per game":
                aL = manager.rangeSearch(-3, from, to);
                break;
            case "Steals":
                aL=manager.rangeSearch(-6,from, to);
                break;
        }

        for (int i = 0; i < aL.size(); i++) {
            String[] parts = aL.get(i).split(manager.getSEPARATOR());
            Player p = new Player(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7],Integer.parseInt(parts[parts.length-1]));
            dataList.add(p);
        }

        refreshSearchTV();

        String info = manager.getTime();
        showTimeAlert(2,info);
    }

    @FXML
    void SEARCHsaveChanges(ActionEvent event) throws IOException {
        manager.readCsv(selectedFile);
        SEARCHsearch(event);
    }

    @FXML
    void SEARCHremovePlayer(ActionEvent event) throws IOException {
        selectedPlayer = SEARCHplayersTv.getSelectionModel().getSelectedItem();
        if(selectedPlayer != null){
            manager.deleteRow(selectedPlayer.getIndex());
            selectedPlayer = null;
            manager.readCsv(selectedFile);
            SEARCHsearch(event);
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Something went wrong");
            alert.setHeaderText("Looks like there's no player selected");
            alert.setContentText("Select a player and try again");
            alert.show();
        }
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

        comparation[0] = "";
        comparation[1] = "";
        selectedPlayer = null;

        ObservableList<String> filterItems = FXCollections.observableArrayList(items);

        SEARCHfilter.setItems(filterItems);

        popUpStage.show();
    }

    private void refreshSearchTV(){
        SEARCHplayersTcFullname.setCellValueFactory(new PropertyValueFactory<>("name"));
        SEARCHplayersTcAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        SEARCHplayersTcTeam.setCellValueFactory(new PropertyValueFactory<>("team"));
        SEARCHplayersPointsPerGame.setCellValueFactory(new PropertyValueFactory<>("pointsPerGame"));
        SEARCHplayersTcAssists.setCellValueFactory(new PropertyValueFactory<>("assists"));
        SEARCHplayersTcRebounds.setCellValueFactory(new PropertyValueFactory<>("rebounds"));
        SEARCHplayersTcSteals.setCellValueFactory(new PropertyValueFactory<>("steals"));
        SEARCHplayersTcBlocks.setCellValueFactory(new PropertyValueFactory<>("blocks"));

        ObservableList<Player> aList = FXCollections.observableArrayList(dataList);
        SEARCHplayersTv.setItems(aList);

        SEARCHplayersTcFullname.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcAge.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcTeam.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersPointsPerGame.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcAssists.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcRebounds.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcSteals.setCellFactory(TextFieldTableCell.forTableColumn());
        SEARCHplayersTcBlocks.setCellFactory(TextFieldTableCell.forTableColumn());


        SEARCHplayersTv.getColumns().setAll(SEARCHplayersTcFullname,SEARCHplayersTcAge,SEARCHplayersTcTeam,
                SEARCHplayersPointsPerGame,SEARCHplayersTcAssists,SEARCHplayersTcRebounds,
                SEARCHplayersTcSteals,SEARCHplayersTcBlocks);
    }


}
