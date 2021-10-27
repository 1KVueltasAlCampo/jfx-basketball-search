package model;

import au.com.bytecode.opencsv.CSVReader;
import dataStructures.RBNode;
import dataStructures.RBTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private RBTree<Double, Integer> pointsPerGame;
    private final static String SEPARATOR = ";";
    private final static int pointsPerGameIndex = 3;
    private final static int POINTSPERGAME=1;
    private RBTree<Double, Integer> blocks;
    private final static int blocksIndex = 7;
    private RBTree<Double,Integer> steals;
    private final static int stealsIndex = 6;
    private File actualFile;

    public Manager(){
        pointsPerGame=new RBTree<>();
        blocks=new RBTree<>();
        steals=new RBTree<>();
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
                if(exampleNode==null){
                    pointsPerGame.insert(ppg,index);
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
        ArrayList<Integer> indexAL = new ArrayList<>();
        ArrayList<String> informationAL = new ArrayList<>();
        switch (tree){
            case pointsPerGameIndex:
                indexAL = pointsPerGame.searchByRange(start,end);
                break;
            case blocksIndex:
                indexAL=blocks.searchByRange(start,end);
                break;
            case stealsIndex:
                indexAL=steals.searchByRange(start,end);
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
        return informationAL;
    }



    //--------------------------------------------------------------------- GUI METHODS ----------------------------------------------------------
    public void addPLayer(String fullName, int age, Teams team, float trueShooting, float usage, float assist, float rebound, float defensive){

    }

    public ArrayList<Teams> getTeams(){
        ArrayList<Teams> teams=  new ArrayList<>(Arrays.asList(Teams.values()));
        return teams;
    }
}
