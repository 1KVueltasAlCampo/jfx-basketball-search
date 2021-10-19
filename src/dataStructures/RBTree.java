package dataStructures;

public class RBTree<K extends Comparable<K>,V>{

    private final static boolean BLACK = false;
    private final static boolean RED = true;

    private boolean leftRotate;
    private boolean rightRotate;
    private boolean leftThenRightRotate;
    private boolean rightThenLeftRotate;
    private RBNode<K,V> root;

    public RBTree(){
        root = null;
    }

    public RBNode<K,V> rotateLeft(RBNode<K,V> node){
        RBNode aux = node.getRight();
        RBNode aux2 = aux.getLeft();
        aux.setLeft(node);
        node.setRight(aux2);
        node.setParent(aux);
        if(aux2!=null){
            aux2.setParent(node);
        }
        return aux;
    }

    public RBNode<K,V> rotateRight(RBNode<K,V> node){
        RBNode aux = node.getLeft();
        RBNode aux2 = aux.getRight();
        aux.setRight(node);
        node.setLeft(aux2);
        node.setParent(aux);
        if(aux2!=null){
            aux2.setParent(node);
        }
        return aux;
    }

    public void insert(RBNode<K,V> node){
        if(root!=null){
            root=insert(root,node);
        }
        else{
            root=node;
            root.setColor(BLACK);
        }
    }

    public RBNode<K,V> insert(RBNode<K,V> root,RBNode<K,V> node){
        boolean flag = false;
        if(root==null){
            return node;
        }

        else if(node.compareTo(root) == -1) {
            root.setLeft(insert(root.getLeft(),node));
            root.getLeft().setParent(root);
            if(root!=this.root) {
                if(root.getColor()==RED && root.getLeft().getColor() ==RED){
                    flag = true; //RED RED COINCIDENCE
                }
            }
        }
        else {
            root.setRight(insert(root.getRight(),node));
            root.getRight().setParent(root);
            if(root!=this.root) {
                if(root.getColor() == RED && root.getRight().getColor() == RED) {
                    flag = true; //RED RED COINCIDENCE
                }
            }
        }
        //ROTATIONS
        if(leftRotate) {
            root = rotateLeft(root);
            root.setColor(BLACK);
            root.getLeft().setColor(RED);
            leftRotate = false;
        }
        else if(rightRotate) {
            root = rotateRight(root);
            root.setColor(BLACK);
            root.getRight().setColor(RED);
            rightRotate = false;
        }
        else if(rightThenLeftRotate) {
            root.setRight(rotateRight(root.getRight()));
            root.getRight().setParent(root);
            root = rotateLeft(root);
            root.setColor(BLACK);
            root.getLeft().setColor(RED);
            rightThenLeftRotate = false;
        }
        else if(leftThenRightRotate) {
            root.setLeft(rotateLeft(root.getLeft()));
            root.getLeft().setParent(root);
            root = rotateRight(root);
            root.setColor(BLACK);
            root.getRight().setColor(RED);
            leftThenRightRotate = false;
        }
        //RED RED conflict
        if(flag) {
            if(root.getParent().getRight() == root) {
                if(root.getParent().getLeft()==null || root.getParent().getLeft().getColor()==BLACK) {
                    if(root.getLeft()!=null && root.getLeft().getColor()==RED) {
                        rightThenLeftRotate = true;
                    }
                    else if(root.getRight()!=null && root.getRight().getColor()==RED){
                        leftRotate = true;
                    }
                }
                else{
                    root.getParent().getLeft().setColor(BLACK);
                    root.setColor(BLACK);
                    if(root.getParent()!=this.root) {
                        root.getParent().setColor(RED);
                    }
                }
            }
            else {
                if(root.getParent().getRight()==null || root.getParent().getRight().getColor()==BLACK) {
                    if(root.getLeft()!=null && root.getLeft().getColor()==RED) {
                        rightRotate = true;
                    }
                    else if(root.getRight()!=null && root.getRight().getColor()==RED) {
                        leftThenRightRotate = true;
                    }
                }
                else {
                    root.getParent().getRight().setColor(BLACK);
                    root.setColor(BLACK);
                    if(root.getParent()!=this.root) {
                        root.getParent().setColor(RED);
                    }
                }
            }
            flag = false;
        }
        return(root);
    }

    public RBNode<K, V> getRoot() {
        return root;
    }

    public void setRoot(RBNode<K, V> root) {
        this.root = root;
    }
}
