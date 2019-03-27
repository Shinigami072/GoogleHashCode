import jdk.nashorn.api.tree.Tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PictureHolder {

    ArrayList<Image> mImages;
    Map<String, SortedSet<Image>> mTags;
    Map<Set<String>, SortedSet<Image>> mvPairs;
    SortedSet<Image> vertical;
    SortedSet<String> sortedKeys;
    Set<String> most_common;

    class ImageSortedSet extends TreeSet<Image> {
        ImageSortedSet(Comparator<Image> c) {
            super(c);
        }

        ImageSortedSet() {
            super();
        }

        ImageSortedSet(Set<String> tags) {
            super((a, b) -> {
                        int scr = TagsComparator.imageCompare(tags, a.tags) - TagsComparator.imageCompare(tags, b.tags);
                        if (scr != 0 && !a.isUsed && !b.isUsed)
                            return scr;
                        else if (a.isUsed)
                            return -1;
                        else if (b.isUsed)
                            return 1;
                        else
                            return a.idInFile - b.idInFile;
                    }
            );
        }
    }

    class KeySortedSet extends TreeSet<String> {
        KeySortedSet(Comparator<String> c) {
            super(c);
        }

        KeySortedSet() {
            super((a, b) -> {
                int c = -mTags.get(a).size() + mTags.get(b).size();
                if (c != 0)
                    return c;
                else
                    return a.compareTo(b);
            });
        }

        public KeySortedSet(Collection<String> tags) {
            super(tags);
        }
    }

    public static PictureHolder make_Holder(String path) throws IOException {
        ArrayList<Image> pictures = new ArrayList<>();
        BufferedReader in = new BufferedReader(new FileReader(path));
        int i = Integer.parseInt(in.readLine());
        int id = 0;
        while (i > 0) {
            i--;
            String[] line = in.readLine().split(" ");
            boolean isHorizontal = line[0].equals("H");

            int tagC = Integer.parseInt(line[1]);
            Set<String> tags = Set.of(Arrays.copyOfRange(line, 2, 2 + tagC));
            pictures.add(new Image(tags, !isHorizontal, id));
            id++;
        }
        return new PictureHolder(pictures);
    }

    private PictureHolder(ArrayList<Image> images) {
        mTags = new HashMap<>();
        vertical = new ImageSortedSet();
        mImages = images;
        mvPairs = new HashMap<>();
        initTags();
    }

    private void initTags() {
        int minTags = -1;
        for (Image i : mImages) {
            if (minTags < 0 || minTags > i.numberOfTags)
                minTags = i.numberOfTags;

            for (String tag : i.tags) {
                mTags.computeIfAbsent(tag, t -> new ImageSortedSet()).add(i);

                if (i.isVertical) {
                    vertical.add(i);
                }
            }
        }
        sortedKeys = new KeySortedSet();
        sortedKeys.addAll(mTags.keySet());

        Iterator<String> iter = sortedKeys.iterator();
        for (int j = 0; j < minTags; j++)
            iter.next();
        String endKey = iter.next();

        most_common = sortedKeys.headSet(endKey);
    }

    public Set<Image> get(String tag) {
        Set<Image> m = mTags.get(tag);
        m.removeIf( image -> image.isUsed);
        return m;
    }

    public int getCount(String tag) {
        int count = 0;
        for (Image img : mTags.get(tag)) {
            if (!img.isUsed)
                count++;
        }
        return count;
    }

    public int getHCount(String tag) {
        int count = 0;
        for (Image img : mTags.get(tag)) {
            if (!img.isUsed && !img.isVertical)
                count++;
        }
        return count;
    }

    public SortedSet<Image> getPair(Set<String> tags, Set<String> pair) {
        vertical.removeIf(image -> image.isUsed);
        Set<String> limited_tags = new KeySortedSet(most_common);
        limited_tags.removeAll(pair);

        SortedSet<Image> im = mvPairs.computeIfAbsent(limited_tags, k -> {
            SortedSet<Image> images = new ImageSortedSet(
                    (Image a, Image b) ->
                    {
                        Set<String> as = new HashSet<>(a.tags);
                        as.addAll(pair);

                        Set<String> bs = new HashSet<>(b.tags);
                        bs.addAll(pair);

                        int c = TagsComparator.imageCompare(tags, as) - TagsComparator.imageCompare(tags, bs);
                        if (c != 0) {
                            return c;
                        } else {
                            return a.idInFile - b.idInFile;
                        }
                    }
            );
            for (String tag : limited_tags) {
                images.addAll(get(tag));
            }
            images.removeIf(image -> image.isUsed || !image.isVertical);
            System.out.println("computing"+k);
            return images;
        });

        if (im.size() != 0){
            im.removeIf(image -> image.isUsed);
            return im;}
        else{
            System.out.println("vertical");
            return vertical;
        }
    }

    public SortedSet<Image> getImages(Set<String> tags) {
        SortedSet<Image> images = new ImageSortedSet(tags);

        for (String tag : tags) {
            images.addAll(mTags.get(tag));
        }
        images.removeIf(image -> image.isUsed);

        return images;
    }

    Random random = new Random();

    public Image getRVI(Set<String> tags, Image ip) {
        vertical.removeIf(image -> image.isUsed);
        Set<String> readyset = new HashSet<>(tags);
        readyset.removeAll(ip.tags);

        SortedSet<Image> images = new ImageSortedSet(tags);
        images.addAll(vertical);
        Iterator<Image> it = images.iterator();

        while (it.hasNext()) {
            Image i = it.next();
            if (!i.isUsed && ip.idInFile != i.idInFile) {
                vertical.remove(i);
                return i;
            }
        }
        return null;
    }
}
