import java.util.*;

public class TagsComparator {

    public static int imageCompare(Set<String> tags1, Set<String> tags2){

        Set<String> sameTags = new HashSet<>();
        sameTags.addAll(tags1);
        sameTags.retainAll(tags2);

        Set<String> in1NotIn2 = new HashSet<>();
        in1NotIn2.addAll(tags1);
        in1NotIn2.removeAll(tags2);

        Set<String> in2NotIn1 = new HashSet<>();
        in2NotIn1.addAll(tags2);
        in2NotIn1.removeAll(tags1);
        
        int score = minimumScore(sameTags,in1NotIn2,in2NotIn1);
        
        return score;

    }

    public static int minimumScore(Set<String> sameTags, Set<String> in1NotIn2, Set<String> in2NotIn1){
        List<Integer> listOfScores = new ArrayList<Integer>();
        listOfScores.add(sameTags.size());
        listOfScores.add(in1NotIn2.size());
        listOfScores.add(in2NotIn1.size());
        
        int score = Collections.min(listOfScores);
        return score;
    }



}
