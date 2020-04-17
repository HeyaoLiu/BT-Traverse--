public class Session {

    public static final int MIN = Integer.MIN_VALUE;

    public static void main(String[] args) {

        int[][] arr = {
                {1, 0, 3},
                {15, 1, 6},
                {3, 2, 4},
                {6, MIN, 8},
                {8, 7, MIN}
        };   

        BinaryTreeDemo btd = new BinaryTreeDemo();

        btd.buildTree(arr);
        btd.writeDotFile();

        {
            btd.printPreOrder_Recursion_L2R();
            btd.printPreOrder_Recursion_R2L();

            btd.printPreOrder_WithoutRecursion_L2R();
            btd.printPreOrder_WithoutRecursion_R2L();
            System.out.println();
        }
        {
            btd.printInOrder_Recursion_L2R();
            btd.printInOrder_Recursion_R2L();

            btd.printInOrder_WithoutRecursion_L2R();
            btd.printInOrder_WithoutRecursion_R2L();
            System.out.println();
        }
        {
            btd.printPostOrder_Recursion_L2R();
            btd.printPostOrder_Recursion_R2L();

            btd.printPostOrder_WithoutRecursion_R2L();
            btd.printPostOrder_WithoutRecursion_L2R();
            System.out.println();
        }
        {
            btd.printLevelOrder_L2R();
            btd.printLevelOrder_R2L();

            btd.printLevelOrderReversely_L2R();
            btd.printLevelOrderReversely_R2L();
            System.out.println();
        }

        btd.printLevel();

        System.out.println("Value of root is = " + btd.getRoot().getX());
        System.out.println("Size of this binary tree is = " + btd.size());
        System.out.println("Height of this binary tree is = " + btd.getHeight());

    }
}
