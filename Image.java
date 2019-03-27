import java.util.Set;

public class Image implements Comparable<Image>{
    Set<String> tags;
    int numberOfTags;
    boolean isVertical;
    int idInFile;
    boolean isUsed;

    public Image(Set<String> tags, boolean isVertical, int idInFile) {
        this.tags = tags;
        this.numberOfTags = tags.size();
        this.isVertical = isVertical;
        this.idInFile = idInFile;
        this.isUsed = false;
    }

    @Override
    public String toString() {
        return idInFile+(isVertical?"V":"H" )+ (isUsed?"U":"N");
    }

    @Override
    public int compareTo(Image o) {
        int i = numberOfTags-o.numberOfTags;
        if(i!=0)
            return i;
        else
            return idInFile-o.idInFile;
    }
}
