import java.util.*;

public class Codec {

    // Encode
    public String serialize(TreeNode root) {
        StringJoiner preorder = new StringJoiner(",");
        preorder(root, preorder);
        return preorder.toString();
    }
    
    private void preorder(TreeNode root, StringJoiner preorder) {
        if (root == null) {
            preorder.add("null");
            return;
        }
        preorder.add(String.valueOf(root.val));
        preorder(root.left, preorder);
        preorder(root.right, preorder);
    }

    // Decode
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue);
    }
    
    private TreeNode deserialize(Queue<String> queue) {
        String value = queue.poll();
        if (value.equals("null")) return null;
        
        TreeNode root = new TreeNode(Integer.valueOf(value));
        root.left = deserialize(queue);
        root.right = deserialize(queue);
        return root;
    }
}

