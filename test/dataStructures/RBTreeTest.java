package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class RBTreeTest {
    private RBTree<Double,String> testRBTree;
    private void setupScene1(){
        testRBTree = new RBTree<>();
    }

    @Test
    public void insertionTest1(){
        setupScene1();
        testRBTree.insert(1.0,"hola");
        assertEquals(testRBTree.getRoot().getValue(),"hola");
    }

    @Test
    public void insertionTest2(){
        setupScene1();
        testRBTree.insert(1.0,"o");
        testRBTree.insert(1.1,"d");
        testRBTree.insert(2.0,"a");
        testRBTree.insert(2.7,"n");
        testRBTree.insert(3.2,"e");
        testRBTree.insert(3.6,"d");
        testRBTree.insert(4.0,"r");
        testRBTree.insert(5.0,"o");
        testRBTree.inorderTraversal();
        assertEquals(testRBTree.getTreeStructure(),"ordenado");
    }

    @Test
    public void insertionTest3(){
        RBTree<Double, ArrayList<Long>> ayuda = new RBTree<>();
        ArrayList<Long> aaa = new ArrayList<>();
        aaa.add(1L);
        aaa.add(5L);
        aaa.add(6L);
        aaa.add(84L);
        ArrayList<Long> bbb = new ArrayList<>();
        bbb.add(20L);
        bbb.add(21L);
        bbb.add(22L);
        bbb.add(23L);
        ayuda.insert(4.0,bbb);
        ayuda.insert(3.0,aaa);
        ayuda.insert(2.0,bbb);
        ayuda.insert(1.0,aaa);
        assertEquals(ayuda.searchFromARange(2.0,3.0).toString(),"[1, 5, 6, 84, 20, 21, 22, 23]");
    }

    @Test
    public void searchTest1(){
        setupScene1();
        testRBTree.insert(1.0,"o");
        testRBTree.insert(2.7,"n");
        testRBTree.insert(3.6,"d");
        testRBTree.insert(3.2,"e");
        testRBTree.insert(1.1,"d");
        testRBTree.insert(2.0,"a");
        testRBTree.insert(4.0,"r");
        testRBTree.insert(5.0,"o");
        assertEquals(testRBTree.searchElement(3.2),"e");
    }

    @Test
    public void searchTest2(){
        setupScene1();
        testRBTree.insert(1.0,"o");
        testRBTree.insert(2.7,"n");
        testRBTree.insert(3.6,"d");
        testRBTree.insert(3.2,"e");
        testRBTree.insert(1.1,"d");
        testRBTree.insert(2.0,"a");
        testRBTree.insert(4.0,"r");
        testRBTree.insert(5.0,"o");
        assertEquals(testRBTree.searchElement(6.5),null);
    }


}
