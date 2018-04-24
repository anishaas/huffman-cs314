import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HuffmanCodeTree {
	
	public static void main(String args[]) throws FileNotFoundException {
		Scanner input = new Scanner(new File("poem.txt"));
		FrequencyCalculator calc = new FrequencyCalculator();
		HashMap<String, Integer> map = calc.getFrequencyMap(input);
		PriorityQueue.Node head = PriorityQueue.buildQueue(map);
		PriorityQueue.Node temp = head;
		while(temp != null) {
			System.out.println(temp.data + ": " + temp.frequency);
			temp = temp.next;
		}
		System.out.println();
		System.out.println("Enqueued new node:");
		buildTree(head);
	}
	
	//build tree from priority queue
	public static void buildTree(PriorityQueue.Node head) {
		PriorityQueue.Node temp = head;
		PriorityQueue.Node next = head.getNext();
		TreeNode left = new TreeNode(null, null);
		TreeNode right = new TreeNode(null, null);
		//while pair is present
		while(temp != null && next != null) {
			//create new node
			left = new TreeNode(temp.getData(), temp.getFrequency());
			right = new TreeNode(next.getData(), next.getFrequency());	
			TreeNode parent = new TreeNode(left, right);
			temp = next.getNext();
			next = temp.getNext();
			//move head
			head = temp;
			enqueue(parent, temp, next);
		}
		
		//last node becomes root
		if(temp != null) {
			TreeNode root = new TreeNode(left, right);
		}
	}
	
	private static void printTree(TreeNode root) {
		
	}
	
	private static void enqueue(TreeNode parent, PriorityQueue.Node target, PriorityQueue.Node pointer) {
		//enqueue node using target and pointer 
		//to identify correct position
		PriorityQueue.Node node = new PriorityQueue.Node();
		node.setFrequency(parent.getFrequency());
		int value = parent.getFrequency();
		while(target.getFrequency() != value) {
			target = pointer;
			if(pointer.getNext() != null) {
				pointer = pointer.getNext();	
			}
		}
		
		//first occurrence of target value
		while(pointer.getFrequency() == value) {
			//move target and pointer
			target = pointer;
			pointer = pointer.getNext();
		}
		//position found 
		//insert node between target and pointer
		target.setNext(node);
		node.setNext(pointer);
	}

}
