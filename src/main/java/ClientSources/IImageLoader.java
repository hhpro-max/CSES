package ClientSources;

import java.awt.*;
@FunctionalInterface
public interface IImageLoader {
    Image getImage(ImageResource type);
}
