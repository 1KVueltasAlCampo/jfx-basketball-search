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
        RBNode<Double,String> newNode = new RBNode<Double,String>(1.0,"hola");
        testRBTree.insert(newNode);
        assertEquals(testRBTree.getRoot(),newNode);
    }
}
