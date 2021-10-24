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
        assertEquals(testRBTree.getRoot().getValue().get(0),"hola");
    }

    @Test
    public void insertionTest2(){
        setupScene1();
        testRBTree.insert(1.0,"o");
        testRBTree.insert(1.1,"r");
        testRBTree.insert(2.0,"d");
        testRBTree.insert(3.2,"n");
        testRBTree.insert(5.0,"o");
        testRBTree.insert(3.6,"a");
        testRBTree.insert(4.0,"d");
        testRBTree.insert(2.7,"e");

        testRBTree.inorderTraversal();
        assertEquals(testRBTree.getTreeStructure(),"ordenado");
    }

    @Test
    public void insertionTest3(){
        RBTree<Double, Long> ayuda = new RBTree<>();
        ayuda.insert(4.0,1L);
        ayuda.insert(4.0,17L);
        ayuda.insert(3.0,4L);
        ayuda.insert(2.0,23L);
        ayuda.insert(1.0,9L);
        assertEquals(ayuda.searchByRange(1.0,3.0).toString(),"[4, 23, 9]");
    }

    @Test
    public void insertionTest4(){
        RBTree<Double, Long> ayuda = new RBTree<>();
        ayuda.insert(9.0,22L);
        ayuda.insert(8.0,21L);
        ayuda.insert(7.0,20L);
        ayuda.insert(6.1,19L);
        ayuda.insert(6.0,18L);
        ayuda.insert(5.0,17L);
        ayuda.insert(4.0,1L);
        ayuda.insert(4.0,17L);
        ayuda.insert(3.0,4L);
        ayuda.insert(2.0,23L);
        ayuda.insert(1.0,9L);
        assertEquals(ayuda.searchByRange(2.0,6.1).toString(),"[19, 18, 17, 17, 1, 4, 23]");
    }

    //maldito kennet me hiciste escribir algo que no queria y ahora me toca hacer otro commit
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
        assertEquals(testRBTree.searchElement(3.2).toString(),"[e]");
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
