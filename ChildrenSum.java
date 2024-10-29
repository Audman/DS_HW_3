import java.util.ArrayList;

public class ChildrenSum
{
    public ArrayList<Position<Integer>> ChildrenSum(LinkedBinaryTree<Integer> tree, Position<Integer> root, ArrayList<Position<Integer>> arraylist)
    {
        if (tree.isExternal(root)) return arraylist;

        if (tree.left(root) == null) {
            if(root.getElement() == tree.right(root).getElement())
                arraylist.addLast(root);
            return ChildrenSum(tree, tree.right(root), arraylist);
        }

        if (tree.right(root) == null) {
            if(root.getElement() == tree.left(root).getElement())
                arraylist.addLast(root);
            return ChildrenSum(tree, tree.left(root), arraylist);
        }

        if (root.getElement() == tree.left(root).getElement() + tree.right(root).getElement())
            arraylist.addLast(root);

        return arraylist;
    }
}
