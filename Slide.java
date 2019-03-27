import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Slide {
    List<Image> images;
    private Set<String> sumOfTags;

    public Slide(Image img1, Image img2) {
        assert (img1.isVertical && img2.isVertical);
        assert (!img1.isUsed && !img2.isUsed);
        images = new ArrayList<>(2);
        this.images.add(img1);
        this.images.add(img2);

        setUsed(true);
    }

    public Slide(Image img1) {
        assert (!img1.isVertical);
        assert (!img1.isUsed);
        images = new ArrayList<>(1);
        this.images.add(img1);
        sumOfTags = img1.tags;
        setUsed(true);
    }

    public Set<String> tagsSumator() {
        Set<String> newSum = new HashSet<>();
        for (Image i : images) {
            newSum.addAll(i.tags);

        }
        return newSum;
    }

    public void setUsed(boolean v) {
        for (int i = 0; i < images.size(); i++) {
            images.get(i).isUsed = v;
        }
    }

    public Set<String> getSumOfTags() {
        if (sumOfTags == null)
            sumOfTags = tagsSumator();

        return sumOfTags;
    }

    @Override
    public String toString() {
        String out = "";
        for (int i = 0; i < images.size(); i++) {
            out += images.get(i).idInFile;
            if (i < images.size() - 1)
                out += " ";
        }

        return out;
    }
}
