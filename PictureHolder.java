import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PictureHolder {

    Collection<Image> mImages;
    Map<String, Set<Image>> mTags;


    public static PictureHolder make_Holder(String path) throws IOException {
        ArrayList<Image> pictures = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(path));
        int i = Integer.parseInt(in.readLine());
        int id=0;
        while(i>0){
            i--;
            String[] line =in.readLine().split(" ");
            boolean isHorizontal = line[0].equals("H");

            int tagC = Integer.parseInt(line[1]);
            Set<String> tags= Set.of(Arrays.copyOfRange(line,2,2+tagC));
            pictures.add(new Image(tags,!isHorizontal,id));
            id++;

        }
        return new PictureHolder(pictures);
    }
    public PictureHolder(Collection<Image> images) {
        mTags = new HashMap<>();
        mImages = images;
        initTags();
    }

    private void initTags() {
        for (Image i : mImages) {
            for (String tag : i.tags) {
                mTags.computeIfAbsent(tag, (t -> new HashSet<Image>()));
                mTags.get(tag).add(i);
            }
        }
    }

    public Set<Image> get(String tag){
        return mTags.get(tag);
    }
    public int getCount(String tag){
        int count =0;
        for (Image img:mTags.get(tag)) {
            if(!img.isUsed && !img.isVertical)
                count++;
        }
        return count;
    }
    public Set<Image> getLSet(){
        int m_Count =-1;
        Set<Image> s = null;
        for(String tag:mTags.keySet()){
            int c = getCount(tag);
            if(c>m_Count)
            {
                m_Count=c;
                s=get(tag);
            }
        }

        return  s;
    }

    public static Image getMaxTags(Set<Image> images){
        Image i=null;
        int c =-1;
        for (Image img:images) {
            if(!img.isUsed&&!img.isVertical)
            if(img.tags.size()>c){
                c=img.tags.size();
                i=img;
            }
        }
        return i;
    }

}
