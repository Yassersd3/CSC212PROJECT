package CSC212_PROJECT;

class BSTNode<T> {
	public String key;
	public T data;
	public BSTNode<T> left, right;

	public BSTNode(String k, T val) {
		key = k;
		data = val;
		left = right = null;
	}

	public BSTNode(String k, T val, BSTNode<T> l, BSTNode<T> r) {
		key = k;
		data = val;
		left = l;
		right = r;
	}
}

public class BST<T> {
	BSTNode<T> root, current;

	public BST() {
		root = current = null;
	}

	public boolean empty() {
		return root == null;
	}

	public boolean full() {
		return false;
	}

	public T retrieve() {
		return current.data;
	}

	public boolean findkey(String tkey) {
		BSTNode<T> p = root;

		if (empty())
			return false;

		while (p != null) {
			current = p;
			if (tkey.compareToIgnoreCase(p.key) == 0) {
				return true;
			} else if (tkey.compareToIgnoreCase(p.key) < 0)
				p = p.left;
			else
				p = p.right;
		}

		return false;
	}

	public boolean insert(String k, T val) {

		BSTNode<T> tmp = current;
		if (findkey(k)) {
			current = tmp;
			return false;
		}

		BSTNode<T> p = new BSTNode(k, val);
		if (empty()) {
			root = current = new BSTNode<T>(k, val);
			return true;
		} else {
			if (k.compareToIgnoreCase(current.key) < 0)
				current.left = p;
			else
				current.right = p;
			current = p;
			return true;
		}
	}

	public void inOrder() {
		if (empty())
			System.out.println("Empty tree");
		else
			inOrder(root);
	}

	public void inOrder(BSTNode p) {
		if (p == null)
			return;
		inOrder(p.left);
		System.out.println("Key: " + p.key);
		System.out.print("[");
		((content) p.data).docIds.display();
		System.out.println("]");
		System.out.println("----------------------------------------------");
		inOrder(p.right);
	}

}