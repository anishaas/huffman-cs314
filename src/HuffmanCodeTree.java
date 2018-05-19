import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class HuffmanCodeTree {
	
	public static void main(String args[]) throws FileNotFoundException {

	}
	
	public void printTree(TreeNode root) {
		if(root==null) 
			return;
		Queue<TreeNode> q = new LinkedList<TreeNode>();
		q.add(root);
		//use null as delimiter
		q.add(null);
		while(!q.isEmpty()) {
			//want to retrieve null, use poll 
			root = q.poll();
			if(root != null) {
				//print root
				System.out.print(root.getValue() + ":" + " " + root.getFrequency() + "    ");
				//add left and right to queue
				if(root.getLeft() != null) {
					q.add(root.getLeft());
				}
				if(root.getRight() != null) {
					q.add(root.getRight());
				}
				//reached delimiter in queue, print new line
			} else {
				if(!q.isEmpty()) {
					System.out.println();
					//add null back at the end of the queue
					q.add(null);
				}
			}
		}
	}
	
	//build tree from priority queue
	public TreeNode buildTree(PriorityQueue queue) {
		TreeNode root = null;
		if(queue != null) {
			while(queue.getLength() > 2) {
				TreeNode n1 = queue.dequeueTreeNode();
				TreeNode n2 = queue.dequeueTreeNode();
				TreeNode parent = new TreeNode(n1, n2);
				queue.enqueueTreeNode(parent);
			}
			
			//final two trees
			TreeNode left = queue.dequeueTreeNode();
			TreeNode right = queue.dequeueTreeNode();
			root = new TreeNode(left, right);
		}
		return root;
	}
}
