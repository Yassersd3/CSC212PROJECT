package CSC212_PROJECT;

public class InvertedIndexBST {

	BST<content> invertedIndexBST;

	public InvertedIndexBST() {
		invertedIndexBST = new BST<content>();
	}

	public boolean search(String s) {
		return invertedIndexBST.findkey(s);
	}

	public void add(String s, int id) {
		if (!search(s)) {
			content c = new content(s);
			c.docIds.insert(id);
			invertedIndexBST.insert(s, c);
		} else {
			content exist = invertedIndexBST.retrieve();
			exist.docIds.insert(id);
		}

	}

	public void display() {
		if (invertedIndexBST.empty()) {
			System.out.println("Empty");
			return;
		}
		invertedIndexBST.inOrder();

	}

}
