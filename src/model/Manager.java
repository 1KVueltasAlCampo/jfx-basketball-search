package model;

import dataStructures.RBNode;
import dataStructures.RBTree;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Manager {
    private RBTree<Double, ArrayList<Long>> pointsPerGame;
    private final static int pointsPerGameIndex = 3;
    private final static int POINTSPERGAME=1;
    private RBTree<Double, ArrayList<Long>> blocks;
    private final static int blocksIndex = 7;
    private RBTree<Double,ArrayList<Long>> steals;
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
        long index = 1;
        String[] box;
        while(aux!=null){
            box = aux.split(",");

            if(!box[pointsPerGameIndex].equals("")){
                Double ppg = Double.parseDouble(box[pointsPerGameIndex]);
                RBNode<Double,ArrayList<Long>> exampleNode = pointsPerGame.search(ppg);
                if(exampleNode==null){
                    pointsPerGame.insert(ppg,new ArrayList<Long>());
                }
                else{
                    exampleNode.getValue().add(index);
                }
            }

            index++;
            aux=br.readLine();
        }
    }

    public ArrayList<Long> rangeSearch(int tree,double start,double end){
        switch (tree){
            case POINTSPERGAME:
                //pointsPerGame.
                break;
        }
        return null;
    }

    //--------------------------------------------------------------------- GUI METHODS ----------------------------------------------------------
    public void addPLayer(String fullName, int age, Teams team, float trueShooting, float usage, float assist, float rebound, float defensive){

    }

    public ArrayList<Teams> getTeams(){
        ArrayList<Teams> teams=  new ArrayList<>(Arrays.asList(Teams.values()));
        return teams;
    }
}
