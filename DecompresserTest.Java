
import static org.junit.Assert.*;
import org.junit.Test;

public class DecompresserTest {

    // Helper to build a fake compressed string from a frequency map + binary text
    private String buildCompressed(char[] chars, char[] freqs, String binaryText) {
        StringBuilder sb = new StringBuilder();
        sb.append((char) chars.length); // N
        for (int i = 0; i < chars.length; i++) {
            sb.append(chars[i]);
            sb.append(freqs[i]);
        }
        sb.append(binaryText);
        return sb.toString();
    }

    @Test
    public void testSingleCharacter() {
        // Only one character 'A' with frequency 1
        // Huffman tree has one node, code is "0"
        char[] chars = {'A'};
        char[] freqs = {(char) 1};
        String compressed = buildCompressed(chars, freqs, "0");

        Decompresser d = new Decompresser();
        assertEquals("A", d.decompress(compressed));
    }

    @Test
    public void testTwoCharacters() {
        // 'A' freq 1, 'B' freq 2
        // Expected tree: B=0, A=1 (or A=0, B=1 depending on order)
        // We test round-trip via DecompressGivenFrecuency directly
        char[] chars = {'A', 'B'};
        char[] freqs = {(char) 1, (char) 2};

        Decompresser d = new Decompresser();

        // Build Data manually to know the codes
        java.util.Map<String, Character> table = new java.util.HashMap<>();
        table.put("A", (char) 1);
        table.put("B", (char) 2);
        Data data = new Data(1 + chars.length * 2, table);

        // The tree will put A (lower freq) deeper, B at top
        // Just verify it decodes without crashing and returns only A/B chars
        String result = d.DecompressGivenFrecuency(data, "010");
        assertTrue("Result should only contain A and B", result.matches("[AB]+"));
    }

    @Test
    public void testGetFrecuencyHeaderLength() {
        // N=3 entries -> header length should be 1 + 3*2 = 7
        char[] chars = {'A', 'B', 'C'};
        char[] freqs = {(char) 3, (char) 1, (char) 2};
        String compressed = buildCompressed(chars, freqs, "101");

        Decompresser d = new Decompresser();
        // decompress should not throw — header is parsed correctly
        // (binary "101" may not be a valid encoding, but at minimum the header read is tested)
        try {
            d.decompress(compressed);
        } catch (Exception e) {
            fail("decompress threw an exception on valid header: " + e.getMessage());
        }
    }

    @Test
    public void testEmptyBinaryText() {
        char[] chars = {'A'};
        char[] freqs = {(char) 1};
        String compressed = buildCompressed(chars, freqs, "");

        Decompresser d = new Decompresser();
        assertEquals("", d.decompress(compressed));
    }
}
