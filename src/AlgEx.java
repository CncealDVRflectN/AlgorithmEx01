import java.io.File;
import java.io.PrintStream;
import java.util.Scanner;

public class AlgEx {
    private static class Vertex {
        Integer key;
        Vertex right;
        Vertex left;

        public Vertex() {
            key = null;
            right = null;
            left = null;
        }

        public Vertex(Integer num) {
            key = num;
            right = null;
            left = null;
        }
    }

    private static class Tree {
        Vertex root;

        public Tree() {
            root = null;
        }

        public Tree(Vertex vertex) {
            root = vertex;
        }

        public boolean add(Vertex vertex) {
            Vertex iter = root;
            if (root == null) {
                this.root = vertex;
                return true;
            }
            while (iter != null && iter.key != vertex.key) {
                if (vertex.key > iter.key) {
                    if (iter.right == null) {
                        iter.right = vertex;
                        break;
                    } else {
                        iter = iter.right;
                    }
                } else if (vertex.key < iter.key) {
                    if (iter.left == null) {
                        iter.left = vertex;
                        break;
                    } else {
                        iter = iter.left;
                    }
                } else if (vertex.key == iter.key) {
                    return false;
                }
            }
            return true;
        }

        public void directLeftRound(Vertex vertex, PrintStream writer) throws Exception {
            if(vertex != null){
                writer.println(vertex.key);
                directLeftRound(vertex.left, writer);
                directLeftRound(vertex.right, writer);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("input.txt"));
        Tree tree = new Tree();
        PrintStream writer = new PrintStream("output.txt");
        while (scanner.hasNextInt()) {
            tree.add(new Vertex(scanner.nextInt()));
        }
        tree.directLeftRound(tree.root, writer);
        writer.close();
        scanner.close();
    }
}
