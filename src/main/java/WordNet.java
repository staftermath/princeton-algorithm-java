import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class WordNet {
    HashMap<Integer, String> idMap = new HashMap<>();
    HashMap<String, HashSet<Integer>> nouns = new HashMap<>();
    Digraph wordnet;
    int root;

    public WordNet(String synsets, String hypernyms) throws FileNotFoundException {
        if (synsets == null || hypernyms == null) throw new IllegalArgumentException();

        // create word-id dictionary
        Scanner inputStream = new Scanner(new FileInputStream(synsets));
        inputStream = new Scanner(new FileInputStream(synsets));
        String thisLine = null;
        String[] lineItems = null;
        int id;
        while (inputStream.hasNext()) {
            thisLine = inputStream.nextLine();
            lineItems = thisLine.split(",");
            id = Integer.parseInt(lineItems[0]);
            this.idMap.put(id, lineItems[1]);
            for (String syn: lineItems[1].split(" ")) {
                if (!nouns.containsKey(syn)) nouns.put(syn, new HashSet<>());
                nouns.get(syn).add(id);
            }
        }

        // create Graph
        wordnet = new Digraph(idMap.size());
        inputStream = new Scanner(new FileInputStream(hypernyms));
        int child;
        while (inputStream.hasNext()) {
            thisLine = inputStream.nextLine();
            lineItems = thisLine.split(",");
            child = Integer.parseInt(lineItems[0]);
            if (lineItems.length == 1) {
                root = Integer.parseInt(lineItems[0]);
            }
            else {
                for (int i=1; i<lineItems.length; i++) {
                    wordnet.addEdge(child, Integer.parseInt(lineItems[i]));
                }
            }
        }
        for (int i : wordnet.E.keySet()) {
            if (!wordnet.E.containsKey(i)) {
                this.root = i;
                return;
            }
        }
    }

    public Iterable<String> nouns() {
        return this.nouns.keySet();
    }

    public boolean isNoun(String word) {
        if (word == null) throw new IllegalArgumentException();
        return this.nouns.containsKey(word);
    }

    public int distance(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        HashSet<Integer> idSetA = this.nouns.get(nounA);
        HashSet<Integer> idSetB = this.nouns.get(nounB);
        int distance = this.wordnet.V();
        for (Integer idB: idSetB) {
            BreadthFirstSearch bfs = new BreadthFirstSearch(this.wordnet, idB);
            for (Integer idA: idSetB) {
                int sap = this.sap(idA, bfs);
                int thisDistance = this.getLevelDifference(sap, idB) + this.getLevelDifference(sap, idA);
                if (thisDistance < distance) distance = thisDistance;
            }
        }
        return distance;
    }

    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        HashSet<Integer> idSetA = this.nouns.get(nounA);
        HashSet<Integer> idSetB = this.nouns.get(nounB);
        int sap = this.root;
        int distance = wordnet.V();
        for (Integer idB: idSetB) {
            BreadthFirstSearch bfs = new BreadthFirstSearch(this.wordnet, idB);
            for (Integer idA: idSetB) {
                int thisSap = this.sap(idA, bfs);
                int thisDistance = this.getLevelDifference(thisSap, idA) + this.getLevelDifference(thisSap, idB);
                if (distance > thisDistance) {
                    sap = thisSap;
                    distance = thisDistance;
                }
            }
        }
        return this.idMap.get(sap);
    }

    private Integer sap(int idA, BreadthFirstSearch bfs) {
        if (bfs.hasPathTo(idA)) return idA;
        Deque queue = new Deque();
        queue.addFirst(idA);
        while (!queue.empty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                int top = queue.popFirst();
                for (Integer n: this.wordnet.adj(top)) {
                    if (bfs.hasPathTo(n)) return n;
                    else queue.addLast(n);
                }
            }
        }
        return -1;
    }

    private int getLevelDifference(Integer higher, Integer lower) {
        if (higher.equals(lower)) return 0;
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(lower);
        int distance = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            distance++;
            for (int i=0;i<size;i++) {
                Integer top = queue.pop();
                if (top.equals(higher)) return distance;
                else {
                    for (Integer hyper: this.wordnet.adj(top)) {
                        queue.add(hyper);
                    }
                }
            }
        }
        return -1;
    }
}
