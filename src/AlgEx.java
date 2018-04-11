import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

public class AlgEx implements Runnable {
    private static class Vertex {
        int key;

        Vertex right;
        Vertex left;

        public Vertex() {
            key = 0;
            right = null;
            left = null;
        }

        public Vertex(int num) {
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

        public boolean add(int num) {
            Vertex vertex = new Vertex(num);
            Vertex iter = root;

            if (root == null) {
                this.root = vertex;
                return true;
            }

            while (iter != null) {
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

        public void directLeftRound(Vertex vertex, PrintWriter writer) throws Exception {
            if (vertex != null) {
                writer.write(vertex.key + "\n");
                directLeftRound(vertex.left, writer);
                directLeftRound(vertex.right, writer);
            }
        }
    }

    @Override
    public void run() {
        BufferedReader reader;
        PrintWriter writer = null;
        String line;
        Tree tree;

        try {
            reader = new BufferedReader(new FileReader("input.txt"));
            writer = new PrintWriter("output.txt");
            tree = new Tree();

            while ((line = reader.readLine()) != null) {
                tree.add(Integer.valueOf(line));
            }
            reader.close();

            tree.directLeftRound(tree.root, writer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        new Thread(null, new AlgEx(), "", 128 * 1024 * 1024).start();
    }
}
