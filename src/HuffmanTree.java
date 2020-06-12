import java.util.HashMap;
import java.util.Map;

public class HuffmanTree implements Comparable<HuffmanTree> {

    private Node root;
    private int freq = 1;
    Map<Character,String> code = new HashMap<>();
    Map<String,Character> decode = new HashMap<>();
    private static int count = 0;
    private int seq;

    public HuffmanTree(char symbol){
        root = new Node();
        root.symbol = symbol;
    }

    public HuffmanTree(HuffmanTree t1, HuffmanTree t2){
        root = new Node();
        root.left = t1.root;
        root.right = t2.root;
        freq = t1.freq + t2.freq;
        count++;
        seq = count;
    }

    public char getRootSymbol(){
        return root.symbol;
    }

    public void incrFreq(){
        freq++;
    }

    public int getFreq(){
        return freq;
    }

    public int getSeq(){
        return seq;
    }

    public int compareTo(HuffmanTree other) {
        return other.freq - freq;
    }

    public void makeCode(){
        String s = "";
        recNode(root, s);
    }

    private void recNode(Node n, String s) {
        if(n.symbol != 0){
            code.put(n.symbol,s);
            decode.put(s,n.symbol);
            return;
        }
        else{
            recNode(n.left,s+0);
            recNode(n.right,s+1);
        }
    }

    public void printCode(){
        System.out.println("Huffman code:");
        for(Map.Entry<Character,String> e : code.entrySet()){
            System.out.println(e.toString());
        }
        System.out.println();
    }

    public String encode(String s){
        String output = "";
        for(int i = 0; i < s.length(); i++){
            output += code.get(s.charAt(i));
        }
        return output;
    }

    public String decode(String s){
        String output = "";
        StringBuffer sb = new StringBuffer(s);
        int i = 1;
        while(sb.length() > 0){
            boolean found = false;
            for(Map.Entry<String,Character> e : decode.entrySet()){
                if(sb.subSequence(0,i).equals(e.getKey())){
                    found = true;
                    output += e.getValue();
                    sb.delete(0,i);
                    break;
                }
            }
            if(found) i = 1;
            else i++;
        }
        return output;
    }

    class Node{
        Node right;
        Node left;
        char symbol = 0;
    }


}
