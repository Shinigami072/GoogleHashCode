import java.util.*;

public class Slideshow {

    LinkedList<Slide> slideShow = new LinkedList<>();

    interface Builder{
        Slide nextSlide(PictureHolder hodler, Set<String> tags);
    }


    private Slideshow() {
    }

    public static Slideshow make_show(PictureHolder holder){
        return make_show(holder,new SlideshowUtils.BuilderSimple());
    }

    public static Slideshow make_show(PictureHolder holder,Builder b) {
        Slideshow sh = new Slideshow();

        Slide start = b.nextSlide(holder, holder.most_common);
        sh.addLast(start);

        //front
        do {
            Slide next = b.nextSlide(holder, sh.last().getSumOfTags());
            System.out.print("\rslide:" + sh.size());
            if (next != null)
                sh.addLast(next);
            else
                break;
        } while (true);
        System.out.println();

        //back
        do {
            Slide next = b.nextSlide(holder, sh.first().getSumOfTags());
            System.out.print("\rslide:" + sh.size());
            if (next != null)
                sh.addFirst(next);
            else
                break;
        } while (true);

        System.out.println();
        return sh;
    }

    private int size() {
        return slideShow.size();
    }

    public long points() {
        long p = 0;
        for (int i = 0; i < slideShow.size() - 1; i++)
            p += TagsComparator.imageCompare(slideShow.get(i).getSumOfTags(), slideShow.get(i + 1).getSumOfTags());
        return p;
    }

    public void addLast(Slide s) {
        slideShow.addLast(s);
    }

    public Slide last() {
        return slideShow.getLast();
    }

    public void addFirst(Slide next) {

        slideShow.addFirst(next);
    }

    public Slide first() {
        return slideShow.getFirst();
    }
}
