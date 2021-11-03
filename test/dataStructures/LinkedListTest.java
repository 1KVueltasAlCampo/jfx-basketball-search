package dataStructures;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class LinkedListTest {
    LinkedList  linkedListTest;

    public void LinkedListTestScene1(){
        linkedListTest=new LinkedList();
    }

    @Test
    public void insertionTest(){
        LinkedListTestScene1();
        linkedListTest.insert(1,2);
        assertEquals(linkedListTest.search(1),linkedListTest.search(1));
    }

    @Test
    public void searchTest(){
        LinkedListTestScene1();
        linkedListTest.insert(1,2);
        linkedListTest.insert(2,3);
        linkedListTest.insert(3,4);
        LinkedListNode temp= linkedListTest.search(2);
        assertEquals(temp.getKey(),linkedListTest.search(2).getKey());
    }


}
