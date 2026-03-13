import java.util.Scanner;


class Node {
    int data;
    Node left;
    Node right;
    int height;
    
    Node(int data) {
        this.data = data;
        this.left = null;
        this.right = null;
        this.height = 1;
    }
}


class AVL {
    public static void main(String[] args) {
        Operations avl = new Operations();
        Scanner sc = new Scanner(System.in);
        Node root = null;
        int n = sc.nextInt();
        while (n-- > 0) {
            int data = sc.nextInt();
            root = avl.insert(root, data);
        }
        System.out.println("Preorder traversal of the AVL tree is:");
        avl.preOrder(root);
    }
}

class Operations {

    Node insert(Node root, int data) {

        if(root == null){
            return new Node(data);
        }
        if(data <= root.data){
            root.left = insert(root.left, data);
        } else {
            root.right = insert(root.right, data);
        }

        // update height after insertion
        root.height = 1 + Math.max((root.left == null ? 0 : root.left.height), (root.right == null ? 0 : root.right.height));

        // check balance factor and perform rotations if necessary
        if (getBF(root) == 2) {
            if(getBF(root.left) == 1) {
                // Left Left Case
                root = rightRotate(root);
            } else {
                // Left Right Case
                root.left = leftRotate(root.left);
                root = rightRotate(root);
            }
        } else if (getBF(root) == -2) {
            if(getBF(root.right) == -1) {
                // Right Right Case
                root = leftRotate(root);
            } else {
                // Right Left Case
                root.right = rightRotate(root.right);
                root = leftRotate(root);
            }
        }

        return root;
    }

    int getBF(Node node) {
        if(node == null) return 0;
        return (node.left == null ? 0 : node.left.height) - (node.right == null ? 0 : node.right.height);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = 1 + Math.max((y.left == null ? 0 : y.left.height), (y.right == null ? 0 : y.right.height));
        x.height = 1 + Math.max((x.left == null ? 0 : x.left.height), (x.right == null ? 0 : x.right.height));

        // Return new root
        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        // Update heights
        x.height = 1 + Math.max((x.left == null ? 0 : x.left.height), (x.right == null ? 0 : x.right.height));
        y.height = 1 + Math.max((y.left == null ? 0 : y.left.height), (y.right == null ? 0 : y.right.height));

        // Return new root
        return y;
    }

    void preOrder(Node node) {
        if(node != null) {
            System.out.print(node.data + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

}
