package dataStructures;

import java.util.ArrayList;

public class BSTree<K extends Comparable<K>,V> implements TreeInterface<K,V> {

    private BSTNode<K,V> root;

        public BSTree(){
        root=null;
        }

    @Override
    public void insert(K key, V value) {
        BSTNode<K,V> node = new BSTNode<>(key, value);
        if(root!=null){
            root=insert(root,node);
        }
        else{
            root=node;

        }
    }

    public BSTNode<K,V> insert  (BSTNode<K,V> root,BSTNode<K,V> node ){
            if(root==null){
                return node;
            }
            else if(node.compareTo(root)==1){
                    if(root.getRightNode()!=null){
                        insert(root.getRightNode(),node);
                    }
                    else{

                        root.setRightNode(node);
                        root.getRightNode().setParent(root);
                    }

            }
            else {
                    if(root.getLeftNode()!=null){
                        insert(root.getLeftNode(),node);
                    }
                    else{
                        root.setLeftNode(node);
                        root.getLeftNode().setParent(root);


                    }
            }
        return root;
    }

    @Override
    public ArrayList<V> searchElement(K key) {
        BSTNode<K,V> element = searchElement(root,key);
        if(element!=null){
            return element.getValue();
        }
        return null;
    }

    public BSTNode<K,V> searchElement(BSTNode<K,V> node,K key){
        if(node==null){
            return node;
        }
        else{
            K aux = node.getKey();
            if(aux.equals(key)){
                return node;
            }
            else{
                if(key.compareTo(aux)<0){
                    return searchElement(node.getLeftNode(),key);
                }
                else{
                    return searchElement(node.getRightNode(),key);
                }
            }
        }
    }

    public BSTNode<K,V> search(K key){
        return searchElement(root,key);
    }

    @Override
    public void remove(K key) {
        if(root.getKey()==key){
            root = null;
        }
        else if(root.getKey().compareTo(key)>0){
            remove(root.getRightNode(),key);
        }
        else if(root.getKey().compareTo(key)<0){
            remove(root.getLeftNode(),key);
        }

    }

    public void remove(BSTNode<K,V> root, K key){
        if(root.getKey()==key){
            if(root.getLeftNode()==null&&root.getRightNode()==null){
                if(root.getParent()!=null){
                    if(root.getParent().getRightNode().getKey()==root.getKey()){
                        root.getParent().setRightNode(null);
                    }
                    else if(root.getParent().getLeftNode().getKey()==root.getKey()){
                        root.getParent().setLeftNode(null);
                    }
                }
            }
            else if(root.getLeftNode()!=null&&root.getRightNode()==null){
                    if(root.getParent()!=null){
                        if(root.getParent().getRightNode().getKey()==root.getKey()){
                            BSTNode<K,V> temp=root.getLeftNode();
                            root.setLeftNode(null);
                            root=temp;
                            root.getParent().setRightNode(root);
                        }
                        else if(root.getParent().getLeftNode().getKey()==root.getKey()){
                            BSTNode<K,V> temp=root.getLeftNode();
                            root.setLeftNode(null);
                            root=temp;
                            root.getParent().setLeftNode(root);
                        }
                    }
            }
            else if(root.getLeftNode()==null&&root.getRightNode()!=null){
                if(root.getParent()!=null){
                    if(root.getParent().getRightNode().getKey()==root.getKey()){
                        BSTNode<K,V> temp=root.getRightNode();
                        root.setRightNode(null);
                        root=temp;
                        root.getParent().setRightNode(root);
                    }
                    else if(root.getParent().getLeftNode().getKey()==root.getKey()){
                        BSTNode<K,V> temp=root.getRightNode();
                        root.setRightNode(null);
                        root=temp;
                        root.getParent().setLeftNode(root);
                    }
                }
            }
            else if(root.getRightNode()!=null&&root.getLeftNode()!=null){
                if(root.getParent()!=null){
                    if(root.getParent().getRightNode().getKey()==root.getKey()){
                        BSTNode<K,V> temp=root.getLeftNode();
                        BSTNode<K,V> temp2=root.getRightNode();
                        root=temp;
                        root.getParent().setRightNode(root);
                        root.getParent().getRightNode().setRightNode(temp2);
                    }
                    else if(root.getParent().getLeftNode().getKey()==root.getKey()){
                        BSTNode<K,V> temp=root.getLeftNode();
                        BSTNode<K,V> temp2=root.getRightNode();
                        root=temp;
                        root.getParent().setLeftNode(root);
                        root.getParent().getLeftNode().setRightNode(temp2);
                    }
                }
            }

        }
        else if(root.getKey().compareTo(key)>0){
            remove(root.getRightNode(),key);
        }
        else if(root.getKey().compareTo(key)<0){
            remove(root.getLeftNode(),key);
        }
    }

    @Override
    public ArrayList<V> searchByRange(K min,K max){
        ArrayList<V> aL = new ArrayList<>();
        searchByRange(root,aL,min,max);

        return aL;
    }

    public void searchByRange(BSTNode<K,V> node, ArrayList<V> ll, K min, K max) {
        if (node == null) {
            return;
        }
        if (node.getKey().compareTo(max) < 0) { //k1 < node.data
            searchByRange(node.getRightNode(),ll, min, max);
        }
        if (node.getKey().compareTo(max) <= 0 && node.getKey().compareTo(min) >= 0) { //k1 <= node.data && k2 >= node.data
            ll.addAll(node.getValue());
        }
        searchByRange(node.getLeftNode(),ll, min, max);
    }
}
