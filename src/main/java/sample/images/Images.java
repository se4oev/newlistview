package sample.images;

import javafx.scene.image.Image;

import java.net.URL;

public class Images {
    private static final String imagesBasePath = '/' + Images.class.getPackage().getName().replace('.', '/') + '/';

    public Images() {
    }

    public static Image createImage(String path) {
        if (path.charAt(0) != '/') {
            path = imagesBasePath + path;
        }

        URL url = Images.class.getResource(path);
        return url == null ? null : new Image(url.toString());
    }
}
