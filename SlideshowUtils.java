import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;
import java.util.SortedSet;

public class SlideshowUtils {

    static class BuilderSimple implements Slideshow.Builder {

        @Override
        public Slide nextSlide(PictureHolder hodler, Set<String> tags) {
            System.out.println("getting tags:" + tags);
            SortedSet<Image> images = hodler.getImages(tags);
            System.out.println("recieved tags");
            for (Image i : images) {
                if (!i.isUsed)
                    if (!i.isVertical) {
                        System.out.println("H");
                        return new Slide(i);
                    } else {
                        System.out.println("comp pairs");
                        SortedSet<Image> jSet = hodler.getPair(tags, i.tags);
                        for (Image j : jSet) {
                            if (j != null && j.idInFile != i.idInFile) {
                                System.out.println("V");
                                return new Slide(i, j);
                            }
                        }
                    }
            }

            return null;
        }
    }

    static class BuilderVerticalOverload implements Slideshow.Builder {

        SortedSet<Image> common;

        @Override
        public Slide nextSlide(PictureHolder hodler, Set<String> tags) {
            if (common == null) {
                System.out.println("getting tags:" + tags);
                common = hodler.new ImageSortedSet();
                for (String tag : hodler.most_common)
                    common.addAll(hodler.get(tag));
            }
            common.removeIf(image -> image.isUsed);

            for (Image i : common) {
                if (!i.isUsed) {
                    if (!i.isVertical) {
                        return new Slide(i);
                    } else {
                        SortedSet<Image> jSet = hodler.getPair(tags, i.tags);
                        for (Image j : jSet) {
                            if (j != null && j.idInFile != i.idInFile) {
                                return new Slide(i, j);
                            }

                        }
                    }
                }
            }

            return null;
        }
    }

    static class BuilderHorizontal implements Slideshow.Builder {

        @Override
        public Slide nextSlide(PictureHolder hodler, Set<String> tags) {
            SortedSet<Image> images = hodler.getImages(tags);
            for (Image i : images) {
                if (!i.isVertical)
                    return new Slide(i);
            }

            return null;
        }
    }

    public static void submissionFile(Slideshow slideshow, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(String.valueOf(slideshow.slideShow.size()));
        writer.write("\n");
        for (int i = 0; i < slideshow.slideShow.size(); i++) {

            for (int j = 0; j < slideshow.slideShow.get(i).images.size(); j++) {
                writer.write(String.valueOf(slideshow.slideShow.get(i).images.get(j).idInFile));
                writer.write(" ");
            }

            writer.write("\n");
        }
        writer.close();
    }


}
