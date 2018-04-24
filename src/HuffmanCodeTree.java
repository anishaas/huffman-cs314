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
		buildTree(head);
	}
	
	//build tree from priority queue
	public static void buildTree(PriorityQueue.Node head) {
		PriorityQueue.Node temp = head;
		PriorityQueue.Node next = head.getNext();
		//while pair is present
		while(temp != null && next != null) {
			//create new node
			TreeNode left = new TreeNode(head.getData(), head.getFrequency());
			TreeNode right = new TreeNode(next.getData(), next.getFrequency());	
			TreeNode parent = new TreeNode(left, right);
			temp = next;
			next = next.getNext();
			enqueue(parent, temp, next);
			while(temp != null) {
				System.out.println(temp.getData() + ": " + temp.getFrequency());
				temp = temp.getNext();
			}
		}
	}
	
	private static void enqueue(TreeNode parent, PriorityQueue.Node target, PriorityQueue.Node pointer) {
		//enqueue node using target and pointer 
		//to identify correct position
		int value = parent.getFrequency();
		while(target.getFrequency() != value) {
			target = pointer;
			pointer = pointer.getNext();
		}
		//first occurrence of target value
		while(pointer.getFrequency() == value) {
			//move target and pointer
			target = pointer;
			pointer = pointer.getNext();
		}
		//position found 
		PriorityQueue.Node node = new PriorityQueue.Node();
		node.setFrequency(parent.getFrequency());
		//insert node between target and pointer
		target.setNext(node);
		node.setNext(pointer);
	}

}
