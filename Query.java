package CSC212_PROJECT;

public class Query {

	invertedIndex inverted;

	public Query(invertedIndex i) {
		this.inverted = i;
	}

	public Linkedlist<Integer> AndQuery(Linkedlist<Integer> a, Linkedlist<Integer> b) {
		Linkedlist<Integer> result = new Linkedlist<Integer>();
		if (a.empty() || b.empty())
			return result;
		a.findfirst();
		b.findfirst();
		while(!a.empty()&&!b.empty()) {
			if(a.retrieve()==b.retrieve()) {
				result.insert(a.retrieve());
				if(!a.last())
				a.findnext();
				else
					break;
				if(!b.last())
				b.findnext();
				else
					break;
			}else if(a.retrieve()<b.retrieve())
				if(!a.last())
				a.findnext();
				else break;
			
			else {
				if(!b.last())
				b.findnext();
				else
					break;
			}
			
		}
		return result;

	}

	public boolean isExsistInResult(Linkedlist<Integer> result, int x) {
		if (result.empty())
			return false;
		result.findfirst();
		while (true) {
			if (result.retrieve() == x)
				return true;
			if (!result.last())
				result.findnext();
			else
				break;
		}

		return false;
	}

	public Linkedlist<Integer> AndQuery(String s) {
		Linkedlist<Integer> a = new Linkedlist<Integer>();
		Linkedlist<Integer> b = new Linkedlist<Integer>();
		String[] x = s.split("AND");
		if (x.length == 0)
			return a;

		boolean found = inverted.search(x[0].trim().toLowerCase());
		if (found)
			a = inverted.invertedIndex.retrieve().docIds;
		for (int i = 1; i < x.length; i++) {
			found = inverted.search(x[i].trim().toLowerCase());
			if (found)
				b = inverted.invertedIndex.retrieve().docIds;
			a = AndQuery(a, b);
		}

		return a;

	}

	public Linkedlist<Integer> OrQuery(Linkedlist<Integer> a, Linkedlist<Integer> b) {
		Linkedlist<Integer> result = new Linkedlist<Integer>();

		if (a.empty() || b.empty())
			return result;

		a.findfirst();
		b.findfirst();

		while (!a.empty() || !b.empty()) {
			if (!a.empty() && (b.empty() || a.retrieve() < b.retrieve())) {
				result.insert(a.retrieve());
				if (!a.last())
					a.findnext();
				else
					a = new Linkedlist<Integer>(); // Exhaust 'a'
			} else if (!b.empty() && (a.empty() || b.retrieve() < a.retrieve())) {

				result.insert(b.retrieve());
				if (!b.last())
					b.findnext();
				else
					b = new Linkedlist<Integer>();
			} else {

				result.insert(a.retrieve());
				if (!a.last())
					a.findnext();
				else
					a = new Linkedlist<Integer>();
				if (!b.last())
					b.findnext();
				else
					b = new Linkedlist<Integer>();
			}
		}
		return result;
	}

	public Linkedlist<Integer> OrQuery(String s) {
		Linkedlist<Integer> a = new Linkedlist<Integer>();
		Linkedlist<Integer> b = new Linkedlist<Integer>();
		String[] x = s.split("OR");
		if (x.length == 0)
			return a;

		boolean found = inverted.search(x[0].trim().toLowerCase());
		if (found)
			a = inverted.invertedIndex.retrieve().docIds;
		for (int i = 1; i < x.length; i++) {
			found = inverted.search(x[i].trim().toLowerCase());
			if (found)
				b = inverted.invertedIndex.retrieve().docIds;
			a = OrQuery(a, b);
		}
		return a;
	}

	public Linkedlist<Integer> BothQuerys(String s) {
		Linkedlist<Integer> a = new Linkedlist<Integer>();
		Linkedlist<Integer> b = new Linkedlist<Integer>();
		String[] x = s.split("OR");
		if (x.length == 0)
			return a;
		a = AndQuery(x[0]);
		for (int i = 1; i < x.length; i++) {
			b = AndQuery(x[i]);
			a = OrQuery(a, b);
		}
		return a;

	}

}
