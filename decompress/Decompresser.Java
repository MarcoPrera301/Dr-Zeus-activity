import java.util.*;

public class Decompresser {

    private Data getFrecuency(String txt) {
        int n = (int) txt.charAt(0);
        Map<String, Character> table = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char key = txt.charAt(1 + i * 2);
            char val = txt.charAt(1 + i * 2 + 1);
            // build reverse map: huffman_code -> original char
            // for now store frequency so we can rebuild the tree
            table.put(String.valueOf(key), val);
        }

        return new Data(1 + n * 2, table);
    }

    public String DecompressGivenFrecuency(Data data, String binarytxt) {
        // Rebuild Huffman tree from frequency table
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (Map.Entry<String, Character> entry : data.getTable().entrySet()) {
            char ch = entry.getKey().charAt(0);
            int freq = (int) entry.getValue();
            pq.offer(new int[]{ch, freq, -1, -1}); // {char, freq, left, right}
        }

        // Store nodes in a list; indices are node IDs
        List<int[]> nodes = new ArrayList<>();
        for (int[] node : pq) {
            nodes.add(node);
        }

        PriorityQueue<int[]> pq2 = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        for (int i = 0; i < nodes.size(); i++) {
            pq2.offer(new int[]{nodes.get(i)[0], nodes.get(i)[1], -1, -1, i});
        }

        // Rebuild using a simple node array: {char(-1 if internal), freq, leftIdx, rightIdx}
        List<int[]> tree = new ArrayList<>();
        PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        for (Map.Entry<String, Character> entry : data.getTable().entrySet()) {
            int ch = (int) entry.getKey().charAt(0);
            int freq = (int) entry.getValue();
            tree.add(new int[]{ch, freq, -1, -1});
            heap.offer(new int[]{tree.size() - 1, freq});
        }

        while (heap.size() > 1) {
            int[] left = heap.poll();
            int[] right = heap.poll();
            int newFreq = left[1] + right[1];
            tree.add(new int[]{-1, newFreq, left[0], right[0]});
            heap.offer(new int[]{tree.size() - 1, newFreq});
        }

        int root = heap.poll()[0];

        // Decode the binary string by walking the tree
        StringBuilder result = new StringBuilder();
        int current = root;

        for (int i = 0; i < binarytxt.length(); i++) {
            char bit = binarytxt.charAt(i);
            if (bit == '0') {
                current = tree.get(current)[2]; // left
            } else {
                current = tree.get(current)[3]; // right
            }

            if (tree.get(current)[2] == -1 && tree.get(current)[3] == -1) {
                // leaf node
                result.append((char) tree.get(current)[0]);
                current = root;
            }
        }

        return result.toString();
    }

    public String decompress(String txt) {
        Data data = getFrecuency(txt);
        String binarytxt = txt.substring(data.getLength());
        return DecompressGivenFrecuency(data, binarytxt);
    }
}

class Data {
    private int length;
    private Map<String, Character> frecuency_table;

    public Data(int length, Map<String, Character> frecuency_table) {
        this.length = length;
        this.frecuency_table = frecuency_table;
    }

    public int getLength() {
        return length;
    }

    public Map<String, Character> getTable() {
        return frecuency_table;
    }

    public Character get(String key) {
        return frecuency_table.get(key);
    }
}
