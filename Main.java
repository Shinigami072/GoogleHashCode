import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

public class Main {
    public static void main(String... args) throws IOException {
        PictureHolder h = PictureHolder.make_Holder(args[0]);
        Set<Image> max_i= h.getLSet();

        Image i = PictureHolder.getMaxTags(max_i);
        i.isUsed=true;
        Slide start = new Slide(i);
        Slideshow show = new Slideshow(new ArrayList());
        show.add(start);
        show.lastSlide();


    }
}
