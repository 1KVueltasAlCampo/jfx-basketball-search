package model;

import au.com.bytecode.opencsv.CSVReader;
import dataStructures.*;

import java.io.*;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private final static String SEPARATOR = ";";
    private AVLTree<Double, Integer> pointsPerGame;
    private final static int pointsPerGameIndex = 3;
    private BSTree<Double,Integer>  ppgBST;
    private final static int ppgBSTIndex = -3;
    private RBTree<Double,Integer> assists;
    private final static int assistsIndex = 4;
    private LinkedList<Double,Integer> rebounds;
    private final static int reboundsIndex= 5;
    private AVLTree<Double,Integer> steals;
    private final static int stealsIndex = 6;
    private BSTree<Double,Integer> stBST;
    private final static int stBstIndex=-6;
    private RBTree<Double, Integer> blocks;
    private final static int blocksIndex = 7;
    private String time;

    private File actualFile;

    public Manager(){
        pointsPerGame=new AVLTree<>();
        ppgBST=new BSTree<>();
        assists = new RBTree<>();
        rebounds=new LinkedList<>();
        stBST=new BSTree<>();
        blocks=new RBTree<>();
        steals=new AVLTree<>();
        time = "";
    }

    public void updateCsv(ArrayList<Player> newArr) throws IOException {
        if(actualFile != null){
            BufferedWriter bf = new BufferedWriter(new FileWriter(actualFile));
        }
    }

    public void addPlayer(String name,String age,String team,String pointsPerGame,String assists,String rebounds,String steals,String blocks) throws IOException {
        String theText = name+SEPARATOR+age+SEPARATOR+team+SEPARATOR+pointsPerGame+SEPARATOR+assists+SEPARATOR+rebounds+SEPARATOR+steals+SEPARATOR+blocks;
        Files.write(actualFile.toPath(), theText.getBytes(), StandardOpenOption.APPEND);
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
                AVLNode<Double,Integer> exampleNode = pointsPerGame.search(ppg);
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

            if(!box[reboundsIndex].equals("")){
                Double ppg = Double.parseDouble(box[reboundsIndex]);
                LinkedListNode<Double,Integer> exampleNode = rebounds.search(ppg);
                if(exampleNode==null){
                    rebounds.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                }
            }


            if(!box[assistsIndex].equals("")){
                Double ppg = Double.parseDouble(box[assistsIndex]);
                RBNode<Double,Integer> exampleNode = assists.search(ppg);
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
                AVLNode<Double,Integer> exampleNode = steals.search(ppg);
                BSTNode<Double,Integer> bstExampleNode = stBST.search(ppg);
                if(exampleNode==null){
                    steals.insert(ppg,index);
                    stBST.insert(ppg,index);
                }
                else{
                    exampleNode.getValue().add(index);
                    bstExampleNode.getValue().add(index);
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
                    long time11=System.nanoTime();
                    indexAL = ppgBST.searchElement(start);
                    long time22=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time += df.format(((float)(time22-time11)/1000000))+" milliseconds°";
                    time1=System.nanoTime();
                    indexAL=pointsPerGame.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    long time11=System.nanoTime();
                    indexAL=ppgBST.searchByRange(start,end);
                    long time22=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time+= df.format(((float)(time22-time11)/1000000))+" milliseconds°";
                    time1=System.nanoTime();
                    indexAL=pointsPerGame.searchByRange(start,end);
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

            case reboundsIndex:
                if(start==end){
                    time1=System.nanoTime();
                    indexAL = rebounds.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    time1=System.nanoTime();
                    indexAL = rebounds.searchByRange(start,end);
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

            case stBstIndex:
                if(start==end){
                    long time11=System.nanoTime();
                    indexAL = stBST.searchElement(start);
                    long time22=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time += df.format(((float)(time22-time11)/1000000))+" milliseconds°";
                    time1=System.nanoTime();
                    indexAL=steals.searchElement(start);
                    time2=System.nanoTime();
                }
                else{
                    long time11=System.nanoTime();
                    indexAL=stBST.searchByRange(start,end);
                    long time22=System.nanoTime();
                    DecimalFormat df = new DecimalFormat("#.####");
                    df.setRoundingMode(RoundingMode.CEILING);
                    time+= df.format(((float)(time22-time11)/1000000))+" milliseconds°";
                    time1=System.nanoTime();
                    indexAL=steals.searchByRange(start,end);
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

    public AVLTree<Double, Integer> getPointsPerGame() {
        return pointsPerGame;
    }

    public void setPointsPerGame(AVLTree<Double, Integer> pointsPerGame) {
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

    public AVLTree<Double, Integer> getSteals() {
        return steals;
    }

    public void setSteals(AVLTree<Double, Integer> steals) {
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

    public RBTree<Double, Integer> getAssists() {
        return assists;
    }

    public void setAssists(RBTree<Double, Integer> assists) {
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

    public ArrayList<Teams> getTeams(){
        ArrayList<Teams> teams=  new ArrayList<>(Arrays.asList(Teams.values()));
        return teams;
    }

    public void removePlayer(Player selectedPlayer) {
    }
}
