import java.util.*;

public class TagsComparator {
    Set<String> sameTags;
    Set<String> in1NotIn2;
    Set<String> in2NotIn1;

    public TagsComparator(HashSet sameTags, HashSet isIn1butNotIn2, HashSet isIn2butNotIn1) {
        this.sameTags = sameTags;
        this.in1NotIn2 = in1NotIn2;
        this.in2NotIn1 = in2NotIn1;
    }

     public int imageCompare(Image img1, Image img2){
        Set<String> tags1 = img1.tags;
        Set<String> tags2 = img2.tags;
        sameTags = new HashSet<>();
        sameTags.addAll(tags1);
        sameTags.retainAll(tags2);

        in1NotIn2 = new HashSet<>();
        in1NotIn2.addAll(tags1);
        in1NotIn2.removeAll(tags2);

        in2NotIn1 = new HashSet<>();
        in2NotIn1.addAll(tags2);
        in2NotIn1.removeAll(tags1);

    }

    public int minimumScore(Set<String> sameTags, Set<String> in1NotIn2, Set<String> in2NotIn1){
        List<Integer> listOfScores = new ArrayList<>(sameTags.size(), in1NotIn2.size(), in2NotIn1.size());
        int score = Collections.min(listOfScores);
        return score;
    }


}
