import java.util.*;

public class TagsComparator {

    public static Map<Set<String>,Map<Set<String>,Integer>> values = new HashMap<>();
    public static int imageCompare(Set<String> tags1, Set<String> tags2){
        values.computeIfAbsent(tags1, k3-> new HashMap<>())
                .computeIfAbsent(tags2, k4 -> helper(tags1,tags2));

        return values.computeIfAbsent(tags2, k-> new HashMap<>())
                .computeIfAbsent(tags1, k2 -> values.get(tags1).get(tags2));

    }

    public static int helper(Set<String> tags1,Set<String> tags2){

        Set<String> sameTags = new HashSet<>(tags1);
        sameTags.retainAll(tags2);

        Set<String> in1NotIn2 = new HashSet<>(tags1);
        in1NotIn2.removeAll(tags2);

        Set<String> in2NotIn1 = new HashSet<>(tags2);
        in2NotIn1.removeAll(tags1);

        return Collections.min(Arrays.asList(
                sameTags.size(),
                in1NotIn2.size(),
                in2NotIn1.size()));
    }

}
