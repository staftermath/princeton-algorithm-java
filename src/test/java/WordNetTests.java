import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;


public class WordNetTests {
    String hypernyms15Tree = getClass().getClassLoader().getResource("hypernyms15Tree.txt").getPath();
    String synsets15 = getClass().getClassLoader().getResource("synsets15.txt").getPath();
    WordNet wordNet15;

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        this.wordNet15 = new WordNet(this.synsets15, this.hypernyms15Tree);
    }

    @Test
    public void testInitialization(){

        assertEquals(14, wordNet15.wordnet.E());
        assertEquals(15, wordNet15.wordnet.V());
        assertEquals(0, wordNet15.root);
    }


    @ParameterizedTest
    @ValueSource(strings = {"a", "b", "n", "o"})
    public void testIsNounReturnsTrue(String noun){
        assertTrue(wordNet15.isNoun(noun));
    }

    @ParameterizedTest
    @ValueSource(strings = {"abc", "noo", "t"})
    public void testIsNounReturnsFalse(String noun){
        assertFalse(wordNet15.isNoun(noun));
    }

    @ParameterizedTest
    @CsvSource({"b,c,2", "a,i,3", "i,a,3", "h, n, 6"})
    public void testDistance(String nounA, String nounB, int distance) {
        assertEquals(distance, wordNet15.distance(nounA, nounB));
    }

    @ParameterizedTest
    @CsvSource({"b,c,a", "a,i,a", "i,a,a", "h, n, b"})
    public void testSap(String nounA, String nounB, String sap) {
        assertEquals(sap, wordNet15.sap(nounA, nounB));
    }
}
