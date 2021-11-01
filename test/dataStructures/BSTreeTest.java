package dataStructures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BSTreeTest {
    private BSTree<Double,String> testBSTree;
    private void setupScene1(){
        testBSTree = new BSTree<>();
    }

    @Test
    public void insertionTest1(){
        setupScene1();
        testBSTree.insert(3.0,"adios");
        assertEquals(testBSTree.getRoot().getValue().get(0),"adios");
    }

    @Test
    public void insertionTest2(){
        setupScene1();
        testBSTree.insert(2.5,"e");
        testBSTree.insert(2.6,"n");
        testBSTree.insert(2.9," ");
        testBSTree.insert(4.7,"o");
        testBSTree.insert(9.2,"n");
        testBSTree.insert(4.8,"r");
        testBSTree.insert(4.9,"d");
        testBSTree.insert(9.1,"e");

        testBSTree.inorderTraversal(testBSTree.getRoot());
        assertEquals(testBSTree.getTreeStructure(),"en orden");
    }

    @Test
    public void insertionTest3(){
        BSTree<Double, Long> ayuda = new BSTree<>();
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
        testBSTree.insert(1.0,"o");
        testBSTree.insert(2.7,"n");
        testBSTree.insert(3.6,"d");
        testBSTree.insert(3.2,"e");
        testBSTree.insert(1.1,"d");
        testBSTree.insert(2.0,"a");
        testBSTree.insert(4.0,"r");
        testBSTree.insert(5.0,"o");
        assertEquals(testBSTree.searchElement(3.2).toString(),"[e]");
    }

    @Test
    public void searchTest2(){
        setupScene1();
        testBSTree.insert(4.0,"o");
        testBSTree.insert(6.7,"n");
        testBSTree.insert(4.6,"d");
        testBSTree.insert(3.2,"e");
        testBSTree.insert(2.1,"d");
        testBSTree.insert(1.0,"a");
        testBSTree.insert(7.0,"r");
        testBSTree.insert(8.0,"o");
        assertEquals(testBSTree.searchElement(6.5),null);
    }


}
