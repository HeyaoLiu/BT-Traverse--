import java.io.File;
import java.io.FileWriter;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;


public class BinaryTreeDemo {
    private List<Integer> list;
    private List<Node> listNode;
    private Node root;
    private int height;

    BinaryTreeDemo() {
        list = new LinkedList<>();
        listNode = new LinkedList<>();
        height = 0;
        root = null;
    }

    public Node getRoot() {
        return root;
    }

    public int getHeight() {
        return height;
    }

    public void buildTree(int[][] arr) {
        if (arr.length == 0) {
            System.out.println("Length of 2D array is illegal.");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].length != 3) {
                System.out.println("Length of " + i + " array is not right.");
            }
            Node[] nodes = new Node[3];

            for (int k = 0; k < 3; k++) {
                nodes[k] = renewNode(arr[i][k]);
            }

            nodes[0].left = nodes[1];
            if (nodes[1] != null) {
                nodes[1].father = nodes[0];
            }
            nodes[0].right = nodes[2];
            if (nodes[2] != null) {
                nodes[2].father = nodes[0];
            }
        }
        setRoot();
        resetLevel();
    }


    private Node renewNode(int x) {
        Node ans = getNode(x);
        if (list.contains(x) == false && x != Integer.MIN_VALUE) {
            list.add(x);
            ans = new Node(x);
            listNode.add(ans);
        }
        return ans;
    }

    private Node getNode(int x) {
        for (int i = 0; i < listNode.size(); i++) {
            if (x == listNode.get(i).getX()) {
                return listNode.get(i);
            }
        }
        return null;
    }

    public int size() {
        return listNode.size();
    }

    private void setRoot() {
        int k = 0;
        for (int i = 0; i < listNode.size(); i++) {
            if (listNode.get(i).father == null) {
                k++;
                root = listNode.get(i);
            }
        }
        if (k != 1) {
            assert (false);
        }
    }

    private void resetLevel() {
        root.setLevel(0);
        setLevel(root);
    }

    private void setLevel(Node node) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            node.left.setLevel(node.getLevel() + 1);

            if (node.left.getLevel() > height) {
                height = node.left.getLevel() + 1;
            }
        }
        if (node.right != null) {
            node.right.setLevel(node.getLevel() + 1);
            if (node.right.getLevel() > height) {
                height = node.right.getLevel() + 1;
            }
        }
        setLevel(node.left);
        setLevel(node.right);
    }

    public void writeDotFile() {
        String path = "/Users/mingwang/Documents/NEU/6205homework/ForHW7/out/BTD.dot";
        try (FileWriter filewriter = new FileWriter(new File(path))) {
            String header = "digraph g {\nedge [color = red]\n";
            filewriter.write(header);
            Queue<Node> queue = new LinkedList<>();
            queue.add(root);
            while (queue.size() != 0) {
                Node tem = queue.poll();
                if (tem.left != null) {
                    filewriter.write(tem.getX() + " -> " + tem.left.getX() + "\n");
                    queue.add(tem.left);
                }
                if (tem.right != null) {
                    filewriter.write(tem.getX() + " -> " + tem.right.getX() + "\n");
                    queue.add(tem.right);
                }
            }
            filewriter.write("}");
        } catch (Exception e) {

        }
    }

    public void printPreOrder_Recursion_L2R() {
        System.out.print("Pre-order, recursion, left to right = ");
        printPreOrder_Recursion_L2R(root);
        System.out.println();
    }

    private void printPreOrder_Recursion_L2R(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getX() + "    ");

        printPreOrder_Recursion_L2R(node.left);

        printPreOrder_Recursion_L2R(node.right);

    }

    public void printPreOrder_Recursion_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Pre-order, recursion, right to left = ");
        printPreOrder_Recursion_R2L(root);
        System.out.println();
    }

    private void printPreOrder_Recursion_R2L(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getX() + "    ");

        printPreOrder_Recursion_R2L(node.right);

        printPreOrder_Recursion_R2L(node.left);

    }

    public void printPreOrder_WithoutRecursion_L2R() {
        if (root == null) {
            return;
        }
        System.out.print("Pre-order, no recursion, left to right = ");
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (stack.size() != 0) {
            Node node = stack.pop();
            if (node == null) {
                continue;
            }
            System.out.print(node.getX() + "    ");

            stack.push(node.right);

            stack.push(node.left);

        }
        System.out.println();
    }

    public void printPreOrder_WithoutRecursion_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Pre-order, no recursion, right to left = ");
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (stack.size() != 0) {
            Node node = stack.pop();
            if (node == null)
                continue;
            System.out.print(node.getX() + "    ");

            stack.push(node.left);

            stack.push(node.right);
        }
        System.out.println();
    }

    public void printInOrder_Recursion_L2R() {
        System.out.print("In-order, recursion, left to right = ");
        printInOrder_Recursion_L2R(root);
        System.out.println();
    }

    private void printInOrder_Recursion_L2R(Node node) {
        if (node != null) {
            printInOrder_Recursion_L2R(node.left);
            System.out.print(node.getX() + "    ");
            printInOrder_Recursion_L2R(node.right);
        }
    }

    public void printInOrder_Recursion_R2L() {
        if (root == null)
            return;
        System.out.print("In-order, recursion, right to left = ");
        printInOrder_Recursion_R2L(root);
        System.out.println();
    }

    private void printInOrder_Recursion_R2L(Node node) {
        if (node != null) {
            printInOrder_Recursion_R2L(node.right);
            System.out.print(node.getX() + "    ");
            printInOrder_Recursion_R2L(node.left);
        }
    }

    public void printInOrder_WithoutRecursion_L2R() {
        printInorderNoRecursionL2R();
        System.out.println();
    }

    private void printInorderNoRecursionL2R() {
        if (root == null) {
            return;
        }
        System.out.print("In-order, no recursion, left to right = ");
        Stack<Node> stack = new Stack<>();
        Node t = root;
        while (true) {
            if (t != null) {
                stack.push(t);
                t = t.left;
            } else {
                if (stack.size() == 0) {
                    return;
                } else {
                    t = stack.pop();
                    System.out.print(t.getX() + "    ");
                    t = t.right;
                }
            }
        }
    }

    public void printInOrder_WithoutRecursion_R2L() {
        printInorderNoRecursionR2L();
        System.out.println();
    }

    private void printInorderNoRecursionR2L() {
        if (root == null) {
            return;
        }
        System.out.print("In-order, no recursion, right to left = ");
        Stack<Node> stack = new Stack<>();
        Node t = root;
        while (true) {
            if (t != null) {
                stack.push(t);
                t = t.right;
            } else {
                if (stack.size() == 0) {
                    return;
                } else {
                    t = stack.pop();
                    System.out.print(t.getX() + "    ");
                    t = t.left;
                }
            }
        }
    }

    public void printPostOrder_Recursion_L2R() {
        if (root == null) {
            return;
        }
        System.out.print("Post-order, recursion, left to right = ");
        printPostOrder_Recursion_L2R(root);
        System.out.println();
    }

    private void printPostOrder_Recursion_L2R(Node node) {
        if (node != null) {
            printPostOrder_Recursion_L2R(node.left);
            printPostOrder_Recursion_L2R(node.right);
            System.out.print(node.getX() + "    ");
        }
    }

    public void printPostOrder_Recursion_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Post-order, recursion, right to left = ");
        printPostOrder_Recursion_R2L(root);
        System.out.println();
    }

    private void printPostOrder_Recursion_R2L(Node node) {
        if (node != null) {
            printPostOrder_Recursion_R2L(node.right);
            printPostOrder_Recursion_R2L(node.left);
            System.out.print(node.getX() + "    ");
        }
    }

    public void printPostOrder_WithoutRecursion_L2R() {
        if (root == null) {
            return;
        }
        System.out.print("Post-order, no recursion, left to right = ");
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (s1.size() != 0) {
            Node node = s1.pop();
            s2.push(node);
            if (node.right != null) {
                s1.push(node.right);
            }
            if (node.left != null) {
                s1.push(node.left);
            }
        }

        while (s2.size() != 0) {
            Node node = s2.pop();
            System.out.print(node.getX() + "    ");
        }
        System.out.println();
    }

    public void printPostOrder_WithoutRecursion_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Post-order, no recursion, right to left = ");
        Stack<Node> s1 = new Stack<>();
        Stack<Node> s2 = new Stack<>();
        s1.push(root);
        while (s1.size() != 0) {
            Node node = s1.pop();
            s2.push(node);
            if (node.left != null) {
                s1.push(node.left);
            }
            if (node.right != null) {
                s1.push(node.right);
            }
        }
        while (s2.size() != 0) {
            Node node = s2.pop();
            System.out.print(node.getX() + "    ");
        }
        System.out.println();
    }

    public void printLevelOrder_L2R() {
        if (root == null) {
            return;
        }
        System.out.print("Level-order, left to right = ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node node = queue.poll();
            if (node == null) {
                continue;
            }
            System.out.print(node.getX() + "    ");
            queue.add(node.left);
            queue.add(node.right);
        }
        System.out.println();
    }

    public void printLevelOrder_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Level-order, right to left = ");
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node node = queue.poll();
            if (node == null) {
                continue;
            }
            System.out.print(node.getX() + "    ");
            queue.add(node.right);
            queue.add(node.left);
        }
        System.out.println();
    }

    public void printLevelOrderReversely_L2R() {
        if (root == null) {
            return;
        }
        System.out.print("Level-order, reversely, left to right = ");
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node node = queue.poll();
            if (node == null) {
                continue;
            }
            stack.add(node);
            queue.add(node.right);
            queue.add(node.left);
        }
        while (stack.size() != 0) {
            Node node = stack.pop();
            System.out.print(node.getX() + "    ");
        }
        System.out.println();
    }

    public void printLevelOrderReversely_R2L() {
        if (root == null) {
            return;
        }
        System.out.print("Level-order, reversely, right to left = ");
        Queue<Node> queue = new LinkedList<>();
        Stack<Node> stack = new Stack<>();
        queue.add(root);
        while (queue.size() != 0) {
            Node node = queue.poll();
            if (node == null) {
                continue;
            }
            stack.add(node);
            queue.add(node.left);
            queue.add(node.right);
        }
        while (stack.size() != 0) {
            Node node = stack.pop();
            System.out.print(node.getX() + "    ");
        }
        System.out.println();
    }

    public void printLevel() {
        print(root);
        System.out.println();
    }

    private void print(Node node) {
        if (node == null) {
            return;
        }
        System.out.println("Node = " + node.getX() + ", level = " + node.getLevel());

        print(node.left);

        print(node.right);

    }

}

















