import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;


public class WordNetTests {

    @Test
    public void testInitialization() throws FileNotFoundException {
        String hypernyms15Tree = getClass().getClassLoader().getResource("hypernyms15Tree.txt").getPath();
        String synsets15 = getClass().getClassLoader().getResource("synsets15.txt").getPath();

        WordNet wordNet = new WordNet(synsets15, hypernyms15Tree);
        assertEquals(14, wordNet.wordnet.E());
        assertEquals(15, wordNet.wordnet.V());
        assertEquals(0, wordNet.root);
    }
}
