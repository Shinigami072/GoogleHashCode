import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    Slide nextSlide(PictureHolder hodler,Slide current){
        Set<Image> tags = new HashSet<>();
        for(String tag:current.sumOfTags)
            tags.addAll(hodler.get(tag));

        Image im = null;
        int c = 0;
        for(Image img:tags){
            if(!img.isUsed && !img.isVertical)
            {
                int t =TagsComparator.imageCompare(current.sumOfTags,img.tags);
                if(c<t)
                {
                    im = img;
                    c=t;
                }
            }
        }
        im.isUsed=true;
        return new Slide(im);
    }

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
