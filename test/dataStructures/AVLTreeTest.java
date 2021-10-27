package dataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AVLTreeTest {
    private AVLTree<Double,String> testAVLTree;
    private void setupScene1(){
        testAVLTree = new AVLTree<>();
    }

    @Test
    public void insertionTest1(){
        setupScene1();
        testAVLTree.insert(3.0,"adios");
        assertEquals(testAVLTree.getRoot().getValue().get(0),"adios");
    }

    @Test
    public void insertionTest2(){
        setupScene1();
        testAVLTree.insert(2.5,"e");
        testAVLTree.insert(2.6,"n");
        testAVLTree.insert(2.9," ");
        testAVLTree.insert(4.7,"o");
        testAVLTree.insert(9.2,"n");
        testAVLTree.insert(4.8,"r");
        testAVLTree.insert(4.9,"d");
        testAVLTree.insert(9.1,"e");

        testAVLTree.inorderTraversal();
        assertEquals(testAVLTree.getTreeStructure(),"en orden");
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

    @Test
    public void searchTest1(){
        setupScene1();
        testAVLTree.insert(1.0,"o");
        testAVLTree.insert(2.7,"n");
        testAVLTree.insert(3.6,"d");
        testAVLTree.insert(3.2,"e");
        testAVLTree.insert(1.1,"d");
        testAVLTree.insert(2.0,"a");
        testAVLTree.insert(4.0,"r");
        testAVLTree.insert(5.0,"o");
        assertEquals(testAVLTree.searchElement(3.2).toString(),"[e]");
    }

    @Test
    public void searchTest2(){
        setupScene1();
        testAVLTree.insert(1.0,"o");
        testAVLTree.insert(2.7,"n");
        testAVLTree.insert(3.6,"d");
        testAVLTree.insert(3.2,"e");
        testAVLTree.insert(1.1,"d");
        testAVLTree.insert(2.0,"a");
        testAVLTree.insert(4.0,"r");
        testAVLTree.insert(5.0,"o");
        assertEquals(testAVLTree.searchElement(6.5),null);
    }
}
