import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class WordNet {
    HashMap<Integer, String> idMap = new HashMap<>();
    HashMap<String, Integer> nouns = new HashMap<>();
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
                nouns.put(syn, id);
            }
        }

        // create Graph
        wordnet = new Digraph(idMap.size());
        inputStream = new Scanner(new FileInputStream(hypernyms));
        int child;
        boolean hasRoot = false;
        while (inputStream.hasNext()) {
            thisLine = inputStream.nextLine();
            lineItems = thisLine.split(",");
            child = Integer.parseInt(lineItems[0]);
            if (lineItems.length == 1) {
                root = Integer.parseInt(lineItems[0]);
                hasRoot = true;
            }
            else {
                for (int i=1; i<lineItems.length; i++) {
                    wordnet.addEdge(child, Integer.parseInt(lineItems[i]));
                }
            }
        }
        if (!hasRoot) throw new IllegalArgumentException("Input has no root DAG.");
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
        String sap = this.sap(nounA, nounB);
        return this.getLevelDifference(sap, nounA) + this.getLevelDifference(sap, nounB);
    }

    public String sap(String nounA, String nounB) {
        if (nounA == null || nounB == null || !isNoun(nounA) || !isNoun(nounB)) throw new IllegalArgumentException();
        int start = this.nouns.get(nounB);
        BreadthFirstSearch bfs = new BreadthFirstSearch(this.wordnet, start);
        HashSet<Integer> visited = new HashSet<>();
        Deque queue = new Deque();
        queue.addFirst(this.nouns.get(nounA));
        while (!queue.empty()) {
            int top = queue.popFirst();
            for (Integer hyper: this.wordnet.adj(top)) {
                if (bfs.hasPathTo(hyper)) return this.idMap.get(top);
                queue.addLast(hyper);
            }
        }
        return null;
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

    private int getLevelDifference(String higher, String lower) {
        Integer higherId = this.nouns.get(higher);
        Integer lowerId = this.nouns.get(lower);
        return this.getLevelDifference(higherId, lowerId);
    }
}
