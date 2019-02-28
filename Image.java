import java.util.Set;

public class Image {
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
}
