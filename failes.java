package CSC212_PROJECT;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
 
public class files {

	Linkedlist<String> stopWords;
	index index1;
	invertedIndex inverted;
	InvertedIndexBST invertedBST;
	int numberOfTokens, numberOfVocabs,numberOfDocs;
	Linkedlist<String> uniqueWords = new Linkedlist<String>();

	public files() {
		stopWords = new Linkedlist<String>();
		index1 = new index();
		inverted = new invertedIndex();
		invertedBST = new InvertedIndexBST();

	}

	public void saveStopWords(String fileName) {

		try {
			File f = new File(fileName);
			Scanner s = new Scanner(f);

			while (s.hasNextLine()) {
				String line = s.nextLine();
				stopWords.insert(line);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void saveDocuments(String fileName) {

		String line = null;
		try {
			File f = new File(fileName);
			Scanner s = new Scanner(f);

			s.nextLine();
			while (s.hasNextLine()) {
				line = s.nextLine();

				String x = line.substring(0, line.indexOf(','));
				int id = Integer.parseInt(x.trim());
				String content = line.substring(line.indexOf(',') + 1).trim();
				Linkedlist<String> words_in_doc = createWordList(content, id);
				get_Tokens_words_Number(content);
				index1.addDoc(new Documents(id, words_in_doc));
				numberOfDocs++;
			}
		} catch (Exception e) {
		}
	}

	public Linkedlist<String> createWordList(String content, int id) {
		Linkedlist<String> words_in_doc = new Linkedlist<String>();
		buildInvertedIndex(content, words_in_doc, id);
		return words_in_doc;
	}

	public void buildInvertedIndex(String content, Linkedlist<String> words_in_doc, int id) {

		content = content.replaceAll("-", "");
		content = content.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
		String[] tokens = content.split("\\s+");

		for (String w : tokens) {

			if (!isStopWord(w)) {
				words_in_doc.insert(w);
				inverted.add(w, id);
				invertedBST.add(w, id);
				
			}
		}
	}

	public boolean isStopWord(String word) {
		if (stopWords == null || stopWords.empty())
			return false;
		stopWords.findfirst();
		while (!stopWords.last()) {
			if (stopWords.retrieve().equals(word))
				return true;
			stopWords.findnext();
		}
		if (stopWords.retrieve().equals(word))
			return true;

		return false;
	}

	public void displayDocByIds(Linkedlist<Integer> ids) {
		if (ids.empty()) {
			System.out.println("no documents exist");
			return;
		}
		ids.findfirst();
		while (!ids.last()) {
			Documents d = index1.getDocById(ids.retrieve());
			if (d != null) {
				System.out.print("Document: " + d.id + " ");
				d.words.display();
				System.out.println();
			}

			ids.findnext();
		}
		Documents d = index1.getDocById(ids.retrieve());
		if (d != null) {
			System.out.print("Document: " + d.id + " ");
			d.words.display();
			System.out.println();
		}
	}

	public void get_Tokens_words_Number(String s) {
		s = s.replaceAll("\'", " ");
		s = s.replaceAll("-", " ");
		s = s.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
		String[] tokens = s.split("\\s+");
		numberOfTokens += tokens.length;

		for (String w : tokens) {
			if (!uniqueWords.search(w)) {
				uniqueWords.insert(w);
				numberOfVocabs++;
			}
		}
		
	}

	
}
 

	
