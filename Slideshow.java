import java.util.ArrayList;

public class Slideshow {

    int numberOfSlides;
    ArrayList<Slide> slideShow;

    public Slideshow (ArrayList slideShow)
    {
        this.numberOfSlides=slideShow.size();
        this.slideShow=slideShow;
    }
    public void add(Slide s){
        slideShow.add(s);
    }

    public Slide lastSlide() {
        return slideShow.get(slideShow.size()-1);
    }

    public long points(){
        long p=0;
        for(int i=0;i<slideShow.size()-1;i++)
            p+=TagsComparator.imageCompare(slideShow.get(i).sumOfTags,slideShow.get(i+1).sumOfTags);
        return p;
    }

    public Slide firstSlide() {
        return slideShow.get(0);
    }

    public void addFront(Slide next) {
        slideShow.add(0,next);
    }

//    public void show(){
//        System.out.println(numberOfSlides);
//        for (Slide s:slideShow) {
//            if(s.images.get(0).isVertical){
//                System.out.print("V"+" ");
//            }
//            else{
//                System.out.print("H"+" ");
//            }
//            System.out.print(s.sumOfTags+" ");
//            for (int i = 0; i < s.images.size(); i++) {
//                for (int j = 0; j < s.images.get(i).tags.size(); j++) {
//                    System.out.print(s.images.get(i).tags.get(j) + " ");
//                }
//            }
//        }
//    }

}
