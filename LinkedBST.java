import java.util.ArrayList;
import java.util.List;
//ben schwartz
//LinkedBST.java
//yes

public class LinkedBST
{
	private TreeNode root;
	
	public LinkedBST()
	{
		root = null;
	}
	//adds node
	public boolean addNode(Comparable obj)
	{
		if(root == null)
		{//only need to check if empty once
			root = new TreeNode(obj);
			return true;
		}//call recursive method if not 
		return addHelper(root, obj);
	}
	//recursive method
	private boolean addHelper(TreeNode current, Comparable node)
	{
		if(current == null)
		{//stopping condition
			current = new TreeNode(node);
			return true;	
		}
		else 
		{
			if(node.compareTo(current.getValue()) == 0)
				return false;//false if duplicate
			else if(node.compareTo(current.getValue()) < 0)
			{//if it goes to left
				if(current.getLeft() == null)
				{//if home is found will add
					current.setLeft(new TreeNode(node));
					return true;
				}
				else//continues recursion
					return addHelper(current.getLeft(), node);
			}
			else
			{//same for right leg
				if(current.getRight() == null)
				{
					current.setRight(new TreeNode(node));
					return true;
				}	
				else
					return addHelper(current.getRight(), node);
			}
		}
	}
	//delete node
	public boolean deleteNode(Comparable find)
	{ //will create a findNode object, and use for this method
		findNode pointers = new findNode(find, root);
		if(!pointers.exists)
		{//if it is not found
			return false;
		}
		else if(pointers.kids() == 0)
		{//if only leaf
			if(pointers.getCurrent() == root)
			{//special case of root
				root = null;
				return true;				
			}
			if(pointers.isLeft)
			{//if to be deleted node is left of previous node
				pointers.getPrevious().setLeft(null);
				return true;
			}
			else
			{//if it is to right
				pointers.getPrevious().setRight(null);
				return true;
			}
		}
		else if(pointers.kids() == 1)
		{//for one kid
			if(pointers.getCurrent() == root)
			{
				if(pointers.getCurrent().getRight() == null)
				{
					root = pointers.getCurrent().getLeft();
					return true;
				}
				else
				{
					root = pointers.getCurrent().getRight();
					return true;
				}
			}
			boolean currentLeftExists = pointers.getCurrent().getRight() == null;
			//this boolean is for capturing which node current will be replaced with
			if(pointers.isLeft)
			{//if current is to left of prev, and the only child is left OR right of current
				if(currentLeftExists)
				{//when current is replaced with its left node
					pointers.getPrevious().setLeft(pointers.getCurrent().getLeft());
					return true;
				}
				else
				{//when current is replaced with its right node
					pointers.getPrevious().setLeft(pointers.getCurrent().getRight());
					return true;
				}
			}
			else
			{//if current is to right of prev, and the only child is left OR right of current
				if(currentLeftExists)
				{
					pointers.getPrevious().setRight(pointers.getCurrent().getLeft());
					return true;
				}
				else
				{
					pointers.getPrevious().setRight(pointers.getCurrent().getRight());
					return true;
				}
			}
		}
		else
		{//when current has 2 kids
			//when deleting with 2 kids, must start with left node first, temp
			TreeNode temp = pointers.getCurrent().getLeft();
			if(temp.getRight() == null)
			{//when the node to the left is the highest value
				pointers.getCurrent().setValue(temp.getValue());
				if(temp.getLeft() == null)
					pointers.getCurrent().setLeft(null);
				else
					pointers.getCurrent().setLeft(temp.getLeft());
				return true;
			}
			else
			{//finds highest value on right side when it has a right
				TreeNode prevOftoChange = prevHighestValue(temp);	//finds previous node of highest value
				TreeNode toChange = prevOftoChange.getRight();//node thats value will be copied
				pointers.getCurrent().setValue(toChange.getValue());//changes value, now needs to be deleted
				if(toChange.getLeft() != null)
				{//if there is a left to node that is copied and deleted
					prevOftoChange.setRight(toChange.getLeft());
				}
				else
				{//if the to change has no kids
					prevOftoChange.setRight(null);
				}
				return true;
			}
		}
	}
	//recursive method for 2 kids
	private TreeNode prevHighestValue(TreeNode current)
	{//this will return the node that points to the previous node to be copied and deleted
			if(current.getRight().getRight() == null)
				return current;
			else
				return prevHighestValue(current.getRight());
	}
	//search method, will have public and helper, RECURSIVE
	public boolean search(Comparable key)
	{//will call recursive method and return it
		if(searchHelper(root, key))
			return true;
		else
			return false;
	}
	private boolean searchHelper(TreeNode current, Comparable key)
	{//will compare a node until if finds it or reaches the end
		if(current == null)//stopping condition
			return false;
		else
		{
			//0(log n) efficiency
			if(key.compareTo(current.getValue()) == 0)
				return true;
			else if(key.compareTo(current.getValue()) < 0)
				return searchHelper(current.getLeft(), key);
			else
				return searchHelper(current.getRight(), key);
		}
	}
	//leaf count method and helper
	public int countLeaf()
	{//returns recurive call
		return countLeaf(root);
	}
	//recursive helper
	private int countLeaf(TreeNode current)
	{
		if(current == null)
			return 0;//stopping condition 
		else if(current.getLeft() == null && current.getRight() == null)
			return 1;//will add if it is leaf
		else//recursive call
			return countLeaf(current.getLeft()) + countLeaf(current.getRight());
	}
	//find path method
	public int findPath(Comparable find)
	{//return number of branches
		if(search(find))//shameful, but i dont care
			return findPath(root, find);
		else
			return -1;
	}
	//recursive help
	private int findPath(TreeNode current, Comparable find)
	{
		if(find.compareTo(current.getValue()) == 0)
			return 0;//if it finds the vaule
		else if(find.compareTo(current.getValue()) < 0)//will add a branch and then
			return 1 + findPath(current.getLeft(), find);
		else
			return 1 + findPath(current.getRight(), find);
	}
	//counts number of nodes in tree
	public int countNode()
	{//returns recursive call
		return countNode(root);
	}
	private int countNode(TreeNode current)
	{//will add when not null, and stopping condition ill return 0
		if(current == null)
			return 0;
		else
			return 1 + countNode(current.getLeft()) + countNode(current.getRight());
	} 
	//public traverse methods
	public List preOrder()
	{//construct arraylist and return it
		ArrayList pip = new ArrayList();
		preOrderTraverse(root, pip);
		return pip;
	}
	public List inOrder()
	{//""
		ArrayList pip = new ArrayList();
		inOrderTraverse(root, pip);
		return pip;
	}
	public List postOrder()
	{//""
		ArrayList pip = new ArrayList();
		postOrderTraverse(root, pip);
		return pip;
	}
	//private recursive traverse methods
	private void preOrderTraverse(TreeNode current, ArrayList list)
	{
		if(current != null)
		{//adds root before legs
			list.add(current.getValue());
			preOrderTraverse(current.getLeft(), list);
			preOrderTraverse(current.getRight(), list);
		}		
	}
	private void inOrderTraverse(TreeNode current, ArrayList list)
	{
		if(current != null)
		{//root inbetween legs
			inOrderTraverse(current.getLeft(), list);
			list.add(current.getValue());
			inOrderTraverse(current.getRight(), list);
		}
	}
	private void postOrderTraverse(TreeNode current, ArrayList list)
	{
		if(current != null)
		{//root after legs
			postOrderTraverse(current.getLeft(), list);
			postOrderTraverse(current.getRight(), list);
			list.add(current.getValue());
		}		
	}	
}


