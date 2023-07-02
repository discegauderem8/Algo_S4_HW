import java.util.Date;

class HashMap {
    private class Entity {
        int key;
        int value;
    }

    private class Basket { // СЃРїРёСЃРѕРє
        private Node head;

        class Node {
            Entity entity;
            Node next;
        }

        public Integer find(int key) { // O(N) -> O(1)
            Node node = head;
            while (node != null) {
                if (node.entity.key == key) {
                    return node.entity.value;
                }
                node = node.next;
            }
            return null;
        }

        public boolean push(int key, int value) {
            Node node = new Node();
            node.entity = new Entity();
            node.entity.key = key;
            node.entity.value = value;

            if (head == null) {
                head = node;
            } else {
                Node cur = head;

                while (cur.next != null) {
                    if (cur.entity.key == key) {
                        return false;
                    }
                    cur = cur.next;
                }
                cur.next = node;
            }
            return true;
        }
    }

    final static int INIT_SIZE = 16;
    private Basket[] baskets;

    public HashMap(int size) {
        baskets = new Basket[size];
    }

    public HashMap() {
        this(INIT_SIZE);
    }

    public int getIndex(int key) { // O(1)
        return key % baskets.length; // [0, baskets.length - 1]
    }

    public Integer find(int key) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null)
            return null;
        return basket.find(key);
    }

    public boolean push(int key, int value) {
        int index = getIndex(key);
        Basket basket = baskets[index];
        if (basket == null) {
            Basket b = new Basket();
            boolean res = b.push(key, value);
            baskets[index] = b;
            return res;
        } else {
            return basket.push(key, value);
        }
    }
}

class BinaryTree {
    Node root;


    public void put(int value) {
        root = put(root, value);
        root.color = Color.BLACK;
    }

    class Node {

        public Node(int value) {
            this.value = value;
        }

        int value;
        Node left;
        Node right;
        Color color;
    }

    enum Color {RED, BLACK}

    public boolean find(int value) { // O(log N)
        Node cur = root;
        while (cur != null) {
            if (cur.value == value) {
                return true;
            }
            if (cur.value < value) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }
        return false;
    }


    private Node leftSwap(Node node) {
        Node x = node.right;
        node.right = x.left;
        x.left = node;
        x.color = node.color;
        node.color = Color.RED;

        return x;
    }

    private Node rightSwap(Node node) {
        Node x = node.left;
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = Color.RED;

        return x;
    }


    private void colorSwap(Node node) {
        node.color = Color.RED;
        node.left.color = Color.BLACK;
        node.right.color = Color.BLACK;
    }


    private Node put(Node node, int value) {
        if (node == null) return new Node(value);

        if (value < node.value) node.left = put(node.left, value);
        else if (value > node.value) node.right = put(node.right, value);
        else node.value = value;

        if ((node.right != null && node.right.color == Color.RED) && (node.left != null && node.left.color != Color.RED)) {
            node = leftSwap(node);
        }
        if ((node.left != null && node.left.color == Color.RED) && (node.left.left != null && node.left.left.color == Color.RED)) {
            node = rightSwap(node);
        }
        if ((node.left != null && node.left.color == Color.RED) && (node.right != null && node.right.color == Color.RED)) {
            colorSwap(node);
        }

        return node;
    }

    public void print() {
        print(root);
    }

    private void print(Node node) {
        if (node == null)
            return;
        System.out.println(node.value);

        if (node.left != null)
            System.out.println("L:" + node.left.value);

        if (node.right != null)
            System.out.println("R:" + node.right.value);

        print(node.left);
        print(node.right);
    }

}

public class Main {
    public static void main(String[] args) {
//        HashMap map = new HashMap();
//
//        for (int i = 1; i <= 5; i++) {
//            map.push(i, i);
//        }
//
//        System.out.println(map.find(3));
//        System.out.println(map.find(6));

        BinaryTree binaryTree = new BinaryTree();
        binaryTree.put(13);
        binaryTree.put(2);
        binaryTree.put(14);
        binaryTree.put(27);
//        binaryTree.balance();

        binaryTree.print();

        System.out.println("******");
        binaryTree.put(5);

        binaryTree.print();
    }

}
