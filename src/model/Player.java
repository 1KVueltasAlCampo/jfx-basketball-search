package model;

import javafx.beans.property.SimpleStringProperty;

public class Player {

    SimpleStringProperty name;
    SimpleStringProperty age;
    SimpleStringProperty team;
    SimpleStringProperty pointsPerGame;
    SimpleStringProperty assists;
    SimpleStringProperty rebounds;
    SimpleStringProperty steals;
    SimpleStringProperty blocks;
    int index;

    public Player(String name,String age,String team,String pointsPerGame,String assists,String rebounds,String steals,String blocks,int index){
        this.name = new SimpleStringProperty(name);
        this.age = new SimpleStringProperty(age);
        this.team = new SimpleStringProperty(team);
        this.pointsPerGame = new SimpleStringProperty(pointsPerGame);
        this.assists = new SimpleStringProperty(assists);
        this.blocks = new SimpleStringProperty(blocks);
        this.rebounds = new SimpleStringProperty(rebounds);
        this.steals = new SimpleStringProperty(steals);
        this.index=index;
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getAge() {
        return age.get();
    }

    public SimpleStringProperty ageProperty() {
        return age;
    }

    public void setAge(String age) {
        this.age.set(age);
    }

    public String getTeam() {
        return team.get();
    }

    public SimpleStringProperty teamProperty() {
        return team;
    }

    public void setTeam(String team) {
        this.team.set(team);
    }

    public String getPointsPerGame() {
        return pointsPerGame.get();
    }

    public SimpleStringProperty pointsPerGameProperty() {
        return pointsPerGame;
    }

    public void setPointsPerGame(String pointsPerGame) {
        this.pointsPerGame.set(pointsPerGame);
    }

    public String getAssists() {
        return assists.get();
    }

    public SimpleStringProperty assistsProperty() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists.set(assists);
    }

    public String getRebounds() {
        return rebounds.get();
    }

    public SimpleStringProperty reboundsProperty() {
        return rebounds;
    }

    public void setRebounds(String rebounds) {
        this.rebounds.set(rebounds);
    }

    public String getSteals() {
        return steals.get();
    }

    public SimpleStringProperty stealsProperty() {
        return steals;
    }

    public void setSteals(String steals) {
        this.steals.set(steals);
    }

    public String getBlocks() {
        return blocks.get();
    }

    public SimpleStringProperty blocksProperty() {
        return blocks;
    }

    public void setBlocks(String blocks) {
        this.blocks.set(blocks);
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
