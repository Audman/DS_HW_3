public class WalterInAlaska<E>
{
    public boolean Walter(
        LinkedBinaryTree<E> tree,
        Position<E> root,
        Position<E> leftSidePosition,
        Position<E> rightSidePosition) // initially null
    {
        if(root != null && rightSidePosition == null && leftSidePosition == null)
        {
            Position<E> tempRoot = root;
            root = null;
            return Walter(tree, root, tree.left(root),tree.right(root));
        }

        if (rightSidePosition == null ||
            leftSidePosition == null || // Check if only one of them is null
            rightSidePosition.getElement() != leftSidePosition.getElement())
            return false;

        return Walter(tree, root, tree.left(leftSidePosition),tree.right(rightSidePosition)) &&
                Walter(tree, root, tree.left(rightSidePosition),tree.right(leftSidePosition));
    }
}
