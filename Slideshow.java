import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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


    public void submissionFile(Slideshow slideshow) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        writer.write(slideshow.numberOfSlides);
        writer.write("\n");
        for (int i = 0; i < slideshow.numberOfSlides; i++) {
            for (int j = 0; j < slideshow.slideShow.get(i).images.size(); j++) {
                writer.write(slideshow.slideShow.get(i).images.get(j).idInFile);
                writer.write(" ");
            }
            writer.write("\n");
        }
        writer.close();
    }


}
