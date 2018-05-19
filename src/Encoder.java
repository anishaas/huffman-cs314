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
        e.buildKey(root);
    }

    public static void encodeFile(TreeNode root) {
        buildKey(root);
    }

    private static HashMap<String, String> buildKey(TreeNode root) {
        HashMap<String, String> codes = new HashMap<>();
        buildCodes(root, codes);
        return codes;
    }

    private static HashMap<String, String> buildCodes(TreeNode root, HashMap<String, String> codes) {
        String code = "";
        getChildCodes(root, codes, code);
        return codes;
    }

    private static HashMap<String, String> getChildCodes(TreeNode node, HashMap<String, String> codes, String code) {

        if(node != null) {
            if(node.getValue() != null) {
                codes.put(node.getValue(), code);
            }
            String left = code + "0";
            String right = code + "1";
            getChildCodes(node.getLeft(), codes, left);
            getChildCodes(node.getRight(), codes, right);
        }
        return codes;
    }

}
