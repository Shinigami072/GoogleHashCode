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
