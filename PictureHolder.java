import java.util.*;

public class PictureHolder {

    Collection<Image> mImages;
    Map<String, Set<Image>> mTags;


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
