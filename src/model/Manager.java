package model;

import au.com.bytecode.opencsv.CSVReader;
import dataStructures.*;

import java.io.*;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private final static String SEPARATOR = ";";
    private RBTree<Double, Integer> pointsPerGame;
    private final static int pointsPerGameIndex = 3;
    private BSTree<Double,Integer>  ppgBST;
    private final static int ppgBSTIndex = -3;
    private AVLTree<Double,Integer> assists;
    private final static int assistsIndex = 4;
    private RBTree<Double,Integer> steals;
    private final static int stealsIndex = 6;
    private RBTree<Double, Integer> blocks;
    private final static int blocksIndex = 7;
    private String time;

    private File actualFile;

    public Manager(){
        pointsPerGame=new RBTree<>();
        ppgBST=new BSTree<>();
        assists = new AVLTree<>();
        blocks=new RBTree<>();
        steals=new RBTree<>();
    }

    public void updateCsv(ArrayList<Player> newArr) throws IOException {
        if(actualFile != null){
            BufferedWriter bf = new BufferedWriter(new FileWriter(actualFile));
        }
    }

    public void readCsv(File file) throws IOException {
        actualFile=file;
        BufferedReader br = new BufferedReader(new FileReader(file));
        br.readLine();
        String aux = br.readLine();
        int index = 1;
        String[] box;
        while(aux!=null){
            box = aux.split(SEPARATOR);

            if(!box[pointsPerGameIndex].equals("")){
                Double ppg = Double.parseDouble(box[pointsPerGameIndex]);
                RBNode<Double,Integer> exampleNode = pointsPerGame.search(ppg);
                BSTNode<Double,Integer> bstExampleNode = ppgBST.search(ppg);
                if(exampleNode==null){
                    pointsPerGame.insert(ppg,index);
                    ppgBST.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                    bstExampleNode.getValue().add(index);
                }
            }

            if(!box[assistsIndex].equals("")){
                Double ppg = Double.parseDouble(box[assistsIndex]);
                AVLNode<Double,Integer> exampleNode = assists.search(ppg);
                if(exampleNode==null){
                    assists.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                }
            }

            if(!box[blocksIndex].equals("")){
                Double ppg = Double.parseDouble(box[blocksIndex]);
                RBNode<Double,Integer> exampleNode = blocks.search(ppg);
                if(exampleNode==null){
                    blocks.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                }
            }

            if(!box[stealsIndex].equals("")){
                Double ppg = Double.parseDouble(box[stealsIndex]);
                RBNode<Double,Integer> exampleNode = steals.search(ppg);
                if(exampleNode==null){
                    steals.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                }
            }

            index++;
            aux=br.readLine();
        }
    }

    public ArrayList<String> rangeSearch(int tree,double start,double end) throws IOException {
        time="";
        ArrayList<Integer> indexAL = new ArrayList<>();
        ArrayList<String> informationAL = new ArrayList<>();
        long time1=0,time2=0;
        switch (tree){
            case pointsPerGameIndex:
                if(start==end){
                    time1=System.nanoTime();
                    indexAL = pointsPerGame.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    indexAL = pointsPerGame.searchByRange(start,end);
                    time2=System.nanoTime();
                }
                break;
            case ppgBSTIndex:
                if(start==end){
                    time1=System.nanoTime();
                    pointsPerGame.searchElement(start);
                    time2=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time+= df.format(((float)(time2-time1)/1000000))+" milliseconds-";
                    time1=System.nanoTime();
                    indexAL = ppgBST.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    pointsPerGame.searchByRange(start,end);
                    time2=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time+= df.format(((float)(time2-time1)/1000000))+" milliseconds-";
                    time1=System.nanoTime();
                    indexAL=ppgBST.searchByRange(start,end);
                    time2=System.nanoTime();
                }
                break;
            case assistsIndex:
                if(start==end){
                    time1=System.nanoTime();
                    indexAL = assists.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    indexAL = assists.searchByRange(start,end);
                    time2=System.nanoTime();
                }
                break;
            case blocksIndex:
                if(start==end){
                    time1=System.nanoTime();
                    indexAL = blocks.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    indexAL=blocks.searchByRange(start,end);
                    time2=System.nanoTime();
                }
                break;
            case stealsIndex:
                if(start==end){
                    time1=System.nanoTime();
                    indexAL=steals.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    indexAL=steals.searchByRange(start,end);
                    time2=System.nanoTime();
                }
                break;
        }
        if(!indexAL.isEmpty()){
            CSVReader reader = new CSVReader(new FileReader(actualFile));
            ArrayList<String[]> aux = (ArrayList<String[]>) reader.readAll();
            for(int i=0;i<indexAL.size();i++){
                String text = String.join(",",aux.get(indexAL.get(i))).replaceAll("\\[","");
                text.replaceAll("]","");
                informationAL.add(text);
            }
        }
        DecimalFormat df = new DecimalFormat("#.####");
        df.setRoundingMode(RoundingMode.CEILING);
        time+= df.format(((float)(time2-time1)/1000000))+" milliseconds";

        return informationAL;
    }

    public RBTree<Double, Integer> getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(RBTree<Double, Integer> pointsPerGame) {
        this.pointsPerGame = pointsPerGame;
    }

    public String getSEPARATOR() {
        return SEPARATOR;
    }

    public static int getPointsPerGameIndex() {
        return pointsPerGameIndex;
    }

    public RBTree<Double, Integer> getBlocks() {
        return blocks;
    }

    public void setBlocks(RBTree<Double, Integer> blocks) {
        this.blocks = blocks;
    }

    public static int getBlocksIndex() {
        return blocksIndex;
    }

    public RBTree<Double, Integer> getSteals() {
        return steals;
    }

    public void setSteals(RBTree<Double, Integer> steals) {
        this.steals = steals;
    }

    public static int getStealsIndex() {
        return stealsIndex;
    }

    public File getActualFile() {
        return actualFile;
    }

    public void setActualFile(File actualFile) {
        this.actualFile = actualFile;
    }

    public AVLTree<Double, Integer> getAssists() {
        return assists;
    }

    public void setAssists(AVLTree<Double, Integer> assists) {
        this.assists = assists;
    }

    public static int getAssistsIndex() {
        return assistsIndex;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    //--------------------------------------------------------------------- GUI METHODS ----------------------------------------------------------
    public void addPLayer(String fullName, int age, Teams team, double pointsPerGame, double rebounds, double assists, double steals, double blocks){

    }

    public ArrayList<Teams> getTeams(){
        ArrayList<Teams> teams=  new ArrayList<>(Arrays.asList(Teams.values()));
        return teams;
    }
}
