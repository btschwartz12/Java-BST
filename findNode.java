public class findNode
{
	private TreeNode prev;//previous root of to be deleted
	private TreeNode current;//to be deleted
	private TreeNode root;//saves root
	public boolean exists;//node is in tree
	public boolean isLeft;//if current is to left of prev
	
	public findNode(Comparable obj, TreeNode root2)
	{
		root = root2;
		exists = false;
		isLeft = true;
		prev = prevHelp(root, obj);

		if(root != null && obj.compareTo(root.getValue()) == 0)
		{//if deleting root
			current = root;
			prev = root;
			exists = true;
		}
		else if(prev == null)
		{//if node doesnt exist
			current = null;
			prev = null;
			exists = false;
		}
		else if(prev.getLeft() != null && obj.compareTo(prev.getLeft().getValue()) == 0)
		{//if node exists and is to left of prev
			current = prev.getLeft();
			exists = true;
			isLeft = true;
		}
		else if(prev.getRight() != null && obj.compareTo(prev.getRight().getValue()) == 0)
		{//if node exists and is to right of prev
			current = prev.getRight();
			exists = true;
			isLeft = false;
		}
		else
		{//for error
			System.out.println("ERROR");
			prev = null;
			current = null;
		}
	}
	private TreeNode prevHelp(TreeNode current, Comparable obj)
	{//recursive that finds node that points to search obj node
		if(current == null)
		{
			return null;
		}	
		TreeNode left = current.getLeft();
		TreeNode right = current.getRight();
		if(left == null && right == null)
			return null;
		else if(left == null)
		{
			if(obj.compareTo(right.getValue()) == 0)
				return current;
		}
		else if(right == null)
		{
			if(obj.compareTo(left.getValue()) == 0)
				return current;
		}
		else if(left != null && right != null)
		{
			if(obj.compareTo(left.getValue()) == 0 || obj.compareTo(right.getValue()) == 0)
				return current;
		}	
		if(obj.compareTo(current.getValue()) < 0)
			return prevHelp(current.getLeft(), obj);
		if(obj.compareTo(current.getValue()) > 0)
			return prevHelp(current.getRight(), obj);
		return null;
	}
	public int kids()
	{//finds number of children
		int pip = 0;
		if(!(current.getLeft() == null))
			pip++;
		if(!(current.getRight() == null))
			pip++;
		return pip;
	}
	public TreeNode getPrevious()
	{
		return prev;
	}
	public TreeNode getCurrent()
	{
		return current;
	}

}