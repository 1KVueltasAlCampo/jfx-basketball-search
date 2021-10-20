package dataStructures;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

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
        testRBTree.insert(2.7,"n");
        testRBTree.insert(3.6,"d");
        testRBTree.insert(3.2,"e");
        testRBTree.insert(1.1,"d");
        testRBTree.insert(2.0,"a");
        testRBTree.insert(4.0,"r");
        testRBTree.insert(5.0,"o");
        testRBTree.inorderTraversal();
        assertEquals(testRBTree.getTreeStructure(),"o r d e n a d o ");
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
