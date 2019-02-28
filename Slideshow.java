import java.util.ArrayList;

public class Slideshow {

    int numberOfSlides;
    ArrayList<Slide> slideShow;

    public Slideshow (ArrayList slideShow)
    {
        this.numberOfSlides=slideShow.size();
        this.slideShow=slideShow;
    }

    public void show(){
        System.out.println(numberOfSlides);
        for (Slide:slideShow) {
            System.out.println(Slide.);
        }
    }

}
