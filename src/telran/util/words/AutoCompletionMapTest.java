package telran.util.words;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AutoCompletionMapTest {
	AutoCompletionMapImpl words;
@BeforeEach
void setUp() throws Exception {
	words = new AutoCompletionMapImpl();
	words.addWord("abc");
	words.addWord("gyu");
	words.addWord("guo");
	words.addWord("jku");
	words.addWord("ads");
	words.addWord("ftg");
	words.addWord("Juo");
}

	@Test
	void testAdd() {
		assertTrue(words.addWord("bhy"));
		assertTrue(words.addWord("bkj"));
		assertFalse(words.addWord("bkj"));
		assertTrue(words.addWord("gih"));
	}
	
	@Test
	void testRemoveWord() {
		assertTrue(words.removeWord("ads"));
		assertFalse(words.removeWord("pou"));
	}
	
	@Test
	void testGetCompletionOptions() {
		assertIterableEquals(Arrays.asList(new String[]{"abc", "ads"}), words.getCompletionOptions("a"));
		assertIterableEquals(Arrays.asList(new String[] {"guo", "gyu"}), words.getCompletionOptions("g"));
		assertIterableEquals(Arrays.asList(new String[] {}), words.getCompletionOptions("l"));
		
	}
	
	@Test
	void testRemoveIf() {
		assertEquals(1, words.removeIf(s -> s.startsWith("jk")));
		assertEquals(2, words.removeIf(s -> s.startsWith("g")));
	}

}
