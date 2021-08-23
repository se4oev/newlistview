package sample.common;

import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class AppUtils {

    private final static Text TEXT = new Text();
    public static final Font DEFAULT_FONT = new Font("Ubuntu", 14.0);

    public static double getTextHeight(String text, double wrappingWidth) {
//        Text TEXT = new Text();
        TEXT.setText(text);
        TEXT.setWrappingWidth(wrappingWidth);
        TEXT.setFont(DEFAULT_FONT);
        TEXT.applyCss();
        return TEXT.getLayoutBounds().getHeight();
    }
}
