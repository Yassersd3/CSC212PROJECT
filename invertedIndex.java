package CSC212_PROJECT;

class content {
	String word;
	Linkedlist<Integer> docIds;

	public content(String s) {
		docIds = new Linkedlist<Integer>();
		word = s;
	}

	public boolean isIdExist(int id) {
		if (docIds.empty())
			return false;
		docIds.findfirst();
		while (!docIds.last())
			if (docIds.retrieve().equals(id))
				return true;
		docIds.findnext();
		if (docIds.retrieve().equals(id))
			return true;
		return false;

	}

}

public class invertedIndex {

	Linkedlist<content> invertedIndex;

	public invertedIndex() {
		invertedIndex = new Linkedlist<content>();
	}

	public boolean search(String s) {
		if (invertedIndex.empty())
			return false;
		invertedIndex.findfirst();
		while (!invertedIndex.last()) {
			if (invertedIndex.retrieve().word.equals(s))
				return true;
			invertedIndex.findnext();
		}
		
		if (invertedIndex.retrieve().word.equals(s))
			return true;
		return false;
	}

	public void add(String s, int id) {
		if (!search(s)) {
			content c = new content(s);
			c.docIds.insert(id);
			invertedIndex.insert(c);
		} else {
			content exist = invertedIndex.retrieve();
			exist.docIds.insert(id);
		}

	}

	public void display() {
		invertedIndex.findfirst();
		while (!invertedIndex.last()) {
			System.out.println("-----------------------------------------------");
			System.out.print("word: "+invertedIndex.retrieve().word+"	");
			invertedIndex.retrieve().docIds.display();
			invertedIndex.findnext();
		}
		System.out.println("-----------------------------------------------");
		System.out.print("word: "+invertedIndex.retrieve().word+"	");
		invertedIndex.retrieve().docIds.display();
	}

	public static void main(String[] args) {
		invertedIndex x=new invertedIndex();
		x.add("dd", 1);
		x.add("kk", 2);
		x.add("dd", 2);
		x.display();

	}
}
