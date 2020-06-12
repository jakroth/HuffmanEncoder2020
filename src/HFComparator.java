import java.util.Comparator;

public class HFComparator implements Comparator<HuffmanTree> {

    @Override
    public int compare(HuffmanTree o1, HuffmanTree o2) {
        if(o1.getFreq() == o2.getFreq()){
            return o2.getSeq() - o1.getSeq();
        }
        return o2.getFreq() - o1.getFreq();
    }
}
