package warmup;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SimilarPairs {
	
	static Map<Integer, Tree> treeMap = new HashMap<Integer,SimilarPairs.Tree>();
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
        int res;
        
        String line = in.nextLine();
        String[] n_split = line.split(" ");
        int n = Integer.valueOf(n_split[0]);
        int t = Integer.valueOf(n_split[1]);
        while((line=in.nextLine()) != null) {
        	n_split = line.split(" ");
        	int parent = Integer.valueOf(n_split[0]);
        	int child = Integer.valueOf(n_split[1]);
        	Tree tree = null;
        	if (treeMap.get(parent) != null) {
        		tree = new Tree(parent);
        	} else {
        		tree = treeMap.get(parent);
        	}
        	Tree childSubTree = null;
        	if (treeMap.get(child) != null) {
        		childSubTree = new Tree(child);
        	} else {
        		childSubTree = treeMap.get(child);
        	}
        	tree.addChild(childSubTree);
        }
		
	}
	
	static class Tree {
		ArrayList<Tree> subTrees = new ArrayList<Tree>();
		int data;
		
		Tree(int val) {
			data = val;
		}
		
		void addChild(Tree child) {
		    subTrees.add(child);	
		}
		
		public boolean equals(Object anotherTree) {
			if (!(anotherTree instanceof Tree)) {
				return false;
			} else {
				Tree other = (Tree) anotherTree;
				if (other.data == data) {
					return true;
				} else {
					return false;
				}
			}
		}
		
		public int hashCode() {
			return data;
		}
	}
}
