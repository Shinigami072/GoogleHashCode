import java.util.ArrayList;

public class Image {
    ArrayList<String> tags;
    int numberOfTags;
    boolean sizeOfImage;
    boolean isVertical;
    int idInFile;
    boolean isUsed;

    public Image(ArrayList<String> tags, int numberOfTags, boolean sizeOfImage, boolean isVertical, int idInFile, boolean isUsed) {
        this.tags = tags;
        this.numberOfTags = numberOfTags;
        this.sizeOfImage = sizeOfImage;
        this.isVertical = isVertical;
        this.idInFile = idInFile;
        this.isUsed = isUsed;
    }
}
