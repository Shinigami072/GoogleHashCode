import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {

    static Slide nextSlide(PictureHolder hodler,Slide current){
        Set<Image> tags = new HashSet<>();
        for(String tag:current.sumOfTags)
            tags.addAll(hodler.get(tag));

        Slide s = null;
        int c = 0;
        for(Image img:tags){
            if(!img.isUsed)
            {
                int t =0;
                Image img2 = null;
                if(!img.isVertical){
                    t=TagsComparator.imageCompare(current.sumOfTags,img.tags);
                }else{
                    Set<String> sum_tags=new HashSet<>();
                    img2=hodler.getRVI(img.idInFile);

                    sum_tags.addAll(img.tags);
                    sum_tags.addAll(img2.tags);
                    t=TagsComparator.imageCompare(current.sumOfTags,img.tags);
                }

                if(c<t)
                {

                    if(img2 ==null){
                        s = new Slide(img);
                    }
                    else{
                        s = new Slide(img,img2);
                    }
                    c=t;
                }
            }
        }
        if(s!=null) {
            s.setUsed();
            return s;
        }
        else
            return null;
    }

    public static void main(String... args) throws IOException {
        PictureHolder h = PictureHolder.make_Holder(args[0]);
        Set<Image> max_i= h.getLSet();

        Image i = PictureHolder.getMaxTags(max_i);
        i.isUsed=true;
        Slide start = new Slide(i);
        Slideshow show = new Slideshow(new ArrayList());
        show.add(start);
        do {
            Slide next = nextSlide(h,show.lastSlide());
            if(next!=null)
                show.add(next);
            else
                break;
        }while (true);
        do {
            Slide next = nextSlide(h,show.firstSlide());
            if(next!=null)
                show.addFront(next);
            else
                break;
        }while (true);

        System.out.println(show.points());
        Slideshow.submissionFile(show,"out.txt");

    }
}
