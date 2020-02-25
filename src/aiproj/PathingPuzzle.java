package aiproj;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue; 
import java.util.List;
import java.util.Random;

public class PathingPuzzle { 

	int[][] visited; // array indicating visited nodes for tree creation
	int[][] puzzle;  // the puzzle of size n indicated in constructor
	int[][] BFSpuzzle; // bfs of the tree at each node which gives this array indicating how many steps to reach each index
	BinaryTree tree; // tree for the puzzle
	Queue<Node> q = new LinkedList<>(); // queue used for bfs insertion for the tree
	PathingPuzzle(int n) // constructor for a pathing puzzle of size n x n
	{
		int[][] output = new int[n][n];
		int[][] visited = new int[n][n];
		for(int i = 0; i < n; i++) // creates the output puzzle
		{
			for(int j = 0; j<n; j++)
			{
				if(i == n-1 && j == n-1)
				{
					output[i][j] = 0;
				}
				else
				{
					output[i][j] = new Random().nextInt(maxLegal(i, j, n)) + 1; //randomizes value at each index to a legal move
				}
			}
		}
		
		for(int i = 0; i < n; i++) // prints the puzzle
		{
			for(int j = 0; j < n; j++)
			{
				System.out.print(output[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i = 0; i < n; i++) //initalizes the visited array to 0 for each index 
		{
			for(int j = 0; j < n; j++)
			{
				visited[i][j] = 0;
			}
		}
		BinaryTree tree = new BinaryTree(0, 0);
		visited[0][0] = 1;
		this.puzzle = output;
		this.visited = visited; 
		this.tree = tree;
	}
	
	
	public static int maxLegal (int i, int j, int n) // gets max legal move for an index x, y (i, j) for puzzle of size nxn
	{
		int a1 = n - (i + 1); 
		int a2 = i;
		int b1 = n - (j + 1);
		int b2 = j;
		int maximum = a1;
		if(a2 > maximum)
		{
			maximum = a2;
		}
		if(b1 > maximum)
		{
			maximum = b1;
		}
		if(b2 > maximum)
		{
			maximum = b2;
		}
		//System.out.println("The max legal is " + maximum + " for integers i " + i + " and j " + j + " for array size " + n);
		return maximum;
	}
	
	public Node decisionTree(Node current) // creates a tree for a puzzle given the root node to start and recursively inserts nodes 
	{
		int value = puzzle[current.x][current.y];
		boolean a = false;
		boolean b = false;
		boolean c = false;
		boolean d = false;
		if(current.x + value >= 0 && current.x + value < puzzle.length)
		{
			if(visited[current.x + value][current.y] == 0)
			{
				visited[current.x + value][current.y] = 1;
				q.add(current.addChild(new Node(current.x + value, current.y)));
				a = true;
			}
			else
			{
				a = true;
			}
		}
		else
		{
			a = true;
		}
		if(current.x - value >= 0 && current.x - value < puzzle.length)
		{
			if(visited[current.x - value][current.y] == 0)
			{
				visited[current.x - value][current.y] = 1;
				q.add(current.addChild(new Node(current.x - value, current.y)));
				b = true;
			}
			else
			{
				b = true;
			}
		}
		else
		{
			b = true;
		}
		if(current.y + value >= 0 && current.y + value < puzzle.length)
		{
			if(visited[current.x][current.y + value] == 0)
			{
				visited[current.x][current.y + value] = 1;
				q.add(current.addChild(new Node(current.x, current.y + value)));
				c = true;
			}
			else
			{
				c = true;
			}
		}
		else
		{
			c = true;
		}
		if(current.y - value >= 0 && current.y - value < puzzle.length)
		{
			if(visited[current.x][current.y - value] == 0)
			{
				visited[current.x][current.y - value] = 1;
				q.add(current.addChild(new Node(current.x, current.y - value)));
				d = true;
			}
			else
			{
				d = true;
			}
		}
		else
		{
			d = true;
		}
		if(a == true && b == true && c == true && d == true)
		{
			if(q.isEmpty() == false)
			{
				//System.out.println(q.peek().x + "," + q.peek().y);
				decisionTree(q.poll());
			}
		}
		return null;
	}
	
	public void BFSall() // fills the bfspuzzle array with how many moves it takes to get to each index
	{
		BFSpuzzle = new int[puzzle.length][puzzle.length];
		for(int i = 0; i < puzzle.length; i++)
		{
			for(int j = 0; j < puzzle.length; j++)
			{
				BFSpuzzle[i][j] = BFS(i, j, tree.root);
			}
		}
		
		for(int i = 0; i < puzzle.length; i++)
		{
			for(int j = 0; j < puzzle.length; j++)
			{
				System.out.print(BFSpuzzle[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public int BFS(int x, int y, Node root) // returns how many turns to get to index x, y for tree with Node root
	{
		if(x >= puzzle.length || y >= puzzle.length)
		{
			throw new IllegalArgumentException();
		}
		int counter = 0;
		Queue<Node> s = new LinkedList<>();
		s.add(root);
		s.add(null);
		while(s.isEmpty() == false)
		{
			Node temp = s.poll();
			if(temp == null)
			{
				counter++;
				s.add(null);
				if(s.peek() == null)
				{
					break;
				}
				else
				{
					continue;
				}
			}
			if(temp.x == x && temp.y == y)
			{
				//System.out.println("Found the position " + x + "," + y + " at a depth of " + counter);
				return counter;
			}
			for(int i = 0; i < temp.children.size(); i++)
			{
				s.add(temp.children.get(i));
			}
		}
		return counter;
	}
	
	public void HillClimbing(int iterations) 
	{
		int eval = BFS(puzzle.length - 1, puzzle.length - 1, tree.root);
		System.out.println("The initial evaluation before the " + iterations + " iterations of Hill Climbing is " + eval);
		for(int i = 0; i < iterations; i++)
		{
			int x = new Random().nextInt(5);
			int y = new Random().nextInt(5);
			while((x == 4 && y == 4) || (x == 0 && y ==0))
			{
				x = new Random().nextInt(5);
				y = new Random().nextInt(5);
			}
			//System.out.println(x + "," + y);
			int temp = puzzle[x][y];
			puzzle[x][y] = new Random().nextInt(maxLegal(x, y, puzzle.length)) + 1;
			while(temp == puzzle[x][y])
			{
				puzzle[x][y] = new Random().nextInt(maxLegal(x, y, puzzle.length)) + 1;
			}
			for(int k = 0; k < puzzle.length; k++)
			{
				for(int n = 0; n < puzzle.length; n++)
				{
					visited[k][n] = 0;
				}
			}
			visited[0][0] = 1;
			BinaryTree tempTree = new BinaryTree(0, 0);
			decisionTree(tempTree.root);
			int neweval = BFS(puzzle.length - 1, puzzle.length - 1, tempTree.root);
			if(neweval <= eval)
			{
				tree = tempTree;
			}
			else
			{
				puzzle[x][y] = temp;
			}
		}
		System.out.println("-------------");
		eval = BFS(puzzle.length - 1, puzzle.length - 1, tree.root);
		System.out.println("The evaluation after the " + iterations + " iterations of Hill Climbing is " + eval);
		for(int i = 0; i<puzzle.length; i++)
		{
			for(int j = 0; j<puzzle.length; j++)
			{
				System.out.print(puzzle[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		BFSall();
		
	}
}
	
class Node 
{ 
    int x, y; 
    List<Node> children;
    Node parent;
  
    public Node(int x, int y) 
    { 
        this.x = x;
        this.y = y;
        if(this.children == null)
        {
        	this.children = new ArrayList<>();	
        }
    } 
    
    public Node addChild(Node child)
    {
    	child.setParent(this);
    	this.children.add(child);
    	return child;
    }
    
    public void setParent(Node parent)
    {
    	this.parent = parent; 
    }
} 

class BinaryTree
{
	Node root;
	
	BinaryTree(int x, int y)
	{
		root = new Node(x, y);
	}
	
	BinaryTree()
	{
		root = null;
	}
}
