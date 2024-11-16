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
	

}
