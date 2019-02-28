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
        for (Slide s:slideShow) {
            if(s.images.get(0).isVertical){
                System.out.print("V"+" ");
            }
            else{
                System.out.print("H"+" ");
            }
            System.out.print(s.sumOfTags+" ");
            for (int i = 0; i < s.images.size(); i++) {
                for (int j = 0; j < s.images.get(i).tags.size(); j++) {
                    System.out.print(s.images.get(i).tags.get(j) + " ");
                }
            }
        }
    }

}
