import java.io.IOException;
import java.util.Set;

public class Main {

    public static void main(String... args) throws IOException {
        System.out.println("loadin to holder");
        PictureHolder h = PictureHolder.make_Holder(args[0]);
        System.out.println("loaded holder");
        System.out.println(TagsComparator.values);
        System.out.println("making slideshow");
        Slideshow show = Slideshow.make_show(h,new SlideshowUtils.BuilderVerticalOverload());
        System.out.printf("slideshow: %,d", show.points());
        SlideshowUtils.submissionFile(show, "out.txt");

    }
}
