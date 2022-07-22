package ClientSources;

import java.awt.*;
import java.util.EnumMap;

public class ResourceCache {
    private final EnumMap<ImageResource, Image> imageCache;

    public ResourceCache() {
        imageCache = new EnumMap<>(ImageResource.class);
    }

    public void cache(ImageResource type, Image image) {
        imageCache.put(type, image);
    }

    public Image tryGet(ImageResource type, IImageLoader loader) {
        return imageCache.computeIfAbsent(type, loader::getImage);
    }
}
