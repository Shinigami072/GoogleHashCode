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
        int id=1;
        while(i>0){
            i--;
            String[] line =in.readLine().split(" ");
            boolean isHorizontal = line[0].equals("H");

            int tagC = Integer.parseInt(line[1]);
            Set<String> tags= Set.of(Arrays.copyOfRange(line,2,tagC-2));
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
            if(!img.isUsed)
                count++;
        }
        return count;
    }


}
