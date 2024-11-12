package CSC212_PROJECT;

class Documents<T>{
	Linkedlist<String> words=new Linkedlist<String>();
	int id;
	public Documents(int id,Linkedlist<String> word) {
		this.id=id;
		words=word;
	}
}
public class index {

	Linkedlist<Documents> alldocs;
	public index() {
		alldocs=new Linkedlist<Documents>();
	}
	public void addDoc(Documents d) {
		alldocs.insert(d);
	}
	public void displayDocs() {
		if(alldocs.empty())
			System.out.println("Empty documents");
		
		alldocs.findfirst();
		while(!alldocs.last()) {
			Documents d=alldocs.retrieve();
			System.out.println("-----------------------------------------------");
			System.out.println("id: "+d.id);
			d.words.display();
			alldocs.findnext();
		}
		Documents d=alldocs.retrieve();
		System.out.println("-----------------------------------------------");
		System.out.println("id: "+d.id);
		d.words.display();
		
	}
	
	
	public static void main(String[] args) {
		index i1=new index();
		Linkedlist<String> w1=new Linkedlist<String>();
		w1.insert("national");
		w1.insert("flag");
		w1.insert("green");
		Documents d1=new Documents(1, w1);
		i1.addDoc(d1);
		
		Linkedlist<String> w2=new Linkedlist<String>();
		w2.insert("extends");
		w2.insert("color");
		w2.insert("pole");
		Documents d2=new Documents(2, w2);
		i1.addDoc(d2);
		i1.displayDocs();

	}

}
