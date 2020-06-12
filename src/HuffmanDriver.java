import java.util.ArrayList;
import java.util.Collections;

public class HuffmanDriver {

    public static void main(String[] args) {

        String input = "she sells sea shells by the sea shore";
        ArrayList<HuffmanTree> symbols = new ArrayList<>();

        //populate the list of symbols
        for (int i = 0; i < input.length(); i++) {
            boolean found = false;
            for (HuffmanTree hf : symbols) {
                if (hf.getRootSymbol() == input.charAt(i)) {
                    hf.incrFreq();
                    found = true;
                }
            }
            if (!found) {
                symbols.add(new HuffmanTree(input.charAt(i)));
            }
        }
        Collections.sort(symbols);

        //print the one-node trees
        System.out.println("Symbol frequencies:");
        for (HuffmanTree hf : symbols) {
            System.out.println(hf.getRootSymbol() + ": " + hf.getFreq());
        }
        System.out.println();


        //build the Huffman tree
        for(int i = symbols.size()-1; i > 0 ; i--){
            HuffmanTree temp = new HuffmanTree(symbols.get(i-1),symbols.get(i));
            symbols.add(temp);
            Collections.sort(symbols, new HFComparator());
        }


        //build and print the Huffman code
        HuffmanTree hf = symbols.get(0);
        hf.makeCode();
        hf.printCode();

        //encode the string
        String compInput = hf.encode(input);
        System.out.println("Encoded input string: \n" + compInput);
        System.out.println();


        //save string as longs
        long compInput1 = Long.parseLong(compInput.substring(0,63),2);
        long compInput2 = Long.parseLong(compInput.substring(63,compInput.length()),2);
        System.out.println("Encoded input as long(s):");
        System.out.println(compInput1 + " (8 bytes)"); // 8 bytes
        System.out.println(compInput2 + " (8 bytes)"); // 8 bytes
        System.out.println("Total: 16 bytes");
        System.out.println();

        // convert longs to back to string
        String binaryString = Long.toBinaryString(compInput1);
        String binaryString2 = Long.toBinaryString(compInput2);

        // replace leading zeroes
        int lZ = 63 - binaryString.length();
        String leadingZeroes = "";
        while(lZ > 0){
            leadingZeroes += "0";
            lZ--;
        }
        binaryString = leadingZeroes + binaryString;
        lZ = (compInput.length() - 63) - binaryString2.length();
        leadingZeroes = "";
        while(lZ > 0){
            leadingZeroes += "0";
            lZ--;
        }

        // print recovered binary string
        binaryString = binaryString + leadingZeroes + binaryString2;
        System.out.println("Recovered encoded input string: \n" + binaryString);
        System.out.println();

        //decode the string
        String decompInput = hf.decode(binaryString);
        System.out.println("Recovered input string: \n" + decompInput);
        System.out.println("Total: " + decompInput.length() * 2 + " bytes");

        System.out.println("Compression Ratio: " + 72f/16);
    }
}
