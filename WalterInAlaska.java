public class WalterInAlaska<E>
{
    public boolean Walter(
        LinkedBinaryTree<E> tree,
        Position<E> root,
        Position<E> leftSidePosition,
        Position<E> rightSidePosition) // initially null
    {
        if(root != null && rightSidePosition == null && leftSidePosition == null)
            return tree.left(root) != tree.right(root) &&
                tree.left(root) != null &&
                tree.right(root) != null &&
                Walter(tree, null, tree.left(root),tree.right(root));

        if(root == null && rightSidePosition == null && leftSidePosition == null)
            return true;

        if (rightSidePosition == null ||
            leftSidePosition == null || // Check if only one of them is null
            rightSidePosition.getElement() != leftSidePosition.getElement())
            return false;

        return Walter(tree, root, tree.left(leftSidePosition),tree.right(rightSidePosition)) &&
                Walter(tree, root, tree.left(rightSidePosition),tree.right(leftSidePosition));
    }

    public static void main(String[] args) {
        LinkedBinaryTree<Integer> tree = new LinkedBinaryTree<>();
        Position root = tree.addRoot(5);
        Position l1 = tree.addLeft(root, 4);
        Position l2 = tree.addRight(root,4);
        Position r1 = tree.addLeft(l1, 3);
        Position r2 = tree.addRight(l2,3);

        System.out.println(new WalterInAlaska<Integer>().Walter(tree, root, null,null));
    }
}
