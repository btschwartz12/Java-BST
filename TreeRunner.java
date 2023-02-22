//ben schwartz
//TreeRunner.java
//yes
import java.util.Scanner;
public class TreeRunner {
	
	public static void main(String[] args)
	{
		Scanner reader = new Scanner(System.in);
		//KeyboardReader reader = new KeyboardReader();

		LinkedBST piper = new LinkedBST();

		int choice = -1;
		System.out.println("WELCOME TO MY LinkedBST RUNNER ASSIGNMENT! SELECT FROM THE FOLLOWING...");
		while(choice != 0)
		{
			System.out.print("\n 1. addNode"
								   +"\n 2.  preOrder"
								   +"\n 3.  postOrder"
								   +"\n 4.  inOrder"
								   +"\n 5.  deleteNode"
								   +"\n 6.  search"
								   +"\n 7.  countLeaf"
								   +"\n 8.  findPath"
								   +"\n 9.  countNode"
								   + "\n10. build a normal tree" 
								   +"\n 0.	 Exit \n"

								   +"\nWhat is your selection?   ");
			choice = reader.nextInt();
			//choice = reader.readInt();
		
			
			
		
		if(choice == 1)
		{
			System.out.print("\nEnter a String: ");
			String pip = reader.next();
			//String pip = reader.readLine();
			if(piper.addNode(pip))
				System.out.println("\n\""+pip+"\" has been successfully added to the tree.");
			else
				System.out.println("\n\""+pip+"\" already exists in the tree, cannot be added");
		}
		else if(choice == 2)
		{
			System.out.println("PREORDER = "+piper.preOrder());
		}
        else if(choice == 3)
        {
            System.out.println("POSTORDER = "+piper.postOrder());
        }
        else if(choice == 4)
        {
            System.out.println("INORDER = "+piper.inOrder());
        }
        else if(choice == 5)
        {
        		System.out.print("\nEnter a String: ");
			String pip = reader.next();
			//String pip = reader.readLine();
			if(piper.deleteNode(pip))
				System.out.println(pip+" was successfully deleted");
			else
				System.out.println(pip+" was not found, and cant be deleted");
        }
        else if(choice == 6)
        {
        	System.out.print("\nEnter a String: ");
			String pip = reader.next();
			//String pip = reader.readLine();
			
			if(piper.search(pip))
				System.out.println(pip+" was found");
			else
				System.out.println(pip+" was not found");
		}
		else if(choice == 7)
		{
			int pip = piper.countLeaf();
			System.out.println("# of leaves : "+pip);
		}
		else if(choice == 8)
		{
			System.out.print("\nEnter a String: ");
			String pip = reader.next();
			//String pip = reader.readLine();
			int pipp = piper.findPath(pip);
			if(pipp == -1)
				System.out.println(pip+" was not found");
			else
				System.out.println(pip+" takes "+pipp+" branches to reach.");	
		}
		else if(choice == 9)
		{
			int pip = piper.countNode();
			System.out.println("# of nodes : "+pip);
		}
		else if(choice == 10)
		{
			String s1 = "metbgrwuc";
			//String[] pipo = {"oA", "nB", "tC", "mD", "rE", "wF", "qG", "sH"};
			for(int i = 0; i < s1.length(); i++)
				piper.addNode(s1.substring(i, i+1));
		}
		else
			continue;
		}
		reader.close();
	}
}