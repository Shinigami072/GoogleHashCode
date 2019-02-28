import java.util.ArrayList;

public class Image {
    ArrayList<String> tags;
    int numberOfTags;
    boolean sizeOfImage;
    boolean isVertical;
    int idInFile;

    public Image(ArrayList<String> tags, int numberOfTags, boolean sizeOfImage, boolean isVertical, int idInFile) {
        this.tags = tags;
        this.numberOfTags = numberOfTags;
        this.sizeOfImage = sizeOfImage;
        this.isVertical = isVertical;
        this.idInFile = idInFile;
    }
}
