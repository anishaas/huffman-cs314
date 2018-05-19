import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Encoder {

    public static void main(String args[]) throws FileNotFoundException {
        Scanner input = new Scanner(new File("poem.txt"));
        FrequencyCalculator calc = new FrequencyCalculator();
        HashMap<String, Integer> map = calc.getFrequencyMap(input);
        PriorityQueue q = new PriorityQueue();
        PriorityQueue queue = q.buildQueue(map);

        HuffmanCodeTree t = new HuffmanCodeTree();
        TreeNode root = t.buildTree(queue);
        System.out.println(" ");
        System.out.println("print tree");
        t.printTree(root);

        Encoder e = new Encoder();
        e.encodeFile(root);
    }

    public HashMap<String, String> encodeFile(TreeNode root) {
        HashMap<String, String> codes = new HashMap<>();
        String code = "";
        buildLeftCodes(root, codes, code);
        buildRightCodes(root, codes, code);
        return codes;
    }

    private static HashMap<String, String> buildLeftCodes(TreeNode node, HashMap<String, String> codes, String code) {

        if(node == null) {
            return codes;
        } else {
            if(node.getValue() != null) {
                codes.put(node.getValue(), code);
            }
            code += "0";
            return buildLeftCodes(node.getLeft(), codes, code);
        }
    }

    private static HashMap<String, String> buildRightCodes(TreeNode node, HashMap<String, String> codes,String code) {

        if(node == null) {
            return codes;
        } else {
            if(node.getValue() != null) {
                codes.put(node.getValue(), code);
            }
            code += "1";
            return buildRightCodes(node.getRight(), codes, code);
        }
    }

}
