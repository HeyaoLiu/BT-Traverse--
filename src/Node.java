public class Node {
    private int x;
    Node father;
    Node left;
    Node right;
    private int level;

    public Node(int x) {
        this.x = x;
        level = 0;
    }

    public int getX(){
        return this.x;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
