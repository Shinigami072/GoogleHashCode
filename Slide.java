import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public  class Slide {
    ArrayList<Image> images=null;
    Set<String> sumOfTags = new HashSet<>();

    public Slide(ArrayList<Image> images, Set<String> sumOfTags) {
        this.images = images;
        this.sumOfTags = sumOfTags;
    }

    public Slide(Image img1, Image img2){
        this.images.add(img1);
        this.images.add(img2);

        sumOfTags = tagsSumator(img1, img2);
    }
    
     public Slide(Image img1){
        this.images.add(img1);
        sumOfTags = img1.tags;
    }

    public Set<String> tagsSumator(Image img1, Image img2){
        HashSet<String> newSum = new HashSet<>();
        newSum.addAll(img1.tags);
        newSum.addAll(img2.tags);
        return newSum;
    }
}
