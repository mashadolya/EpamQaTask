package framework;

import org.sikuli.script.FindFailed;
import org.sikuli.script.ImagePath;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

public class SikuliHelper {
    private static Screen screen = new Screen();
    private static final String IMAGES_PATH = "imagesPath";
    private static final String WAIT_TIME = "waitTime";
    private static final float SIMILAR_PERCENT = (float) .8;
    private static Log log = Log.getInstance();

    public static void dragAndDrop(String firstPic, String secondPic) {
        setImagesPath();
        try {
            screenWait();
            screen.dragDrop(new Pattern(firstPic).similar(SIMILAR_PERCENT), new Pattern(secondPic).similar(SIMILAR_PERCENT));
            log.info(String.format("%1$s '%2$s' %3$s '%4$s'", log.getLoc("loc.img"), firstPic, log.getLoc("loc.img.move"), secondPic));
        } catch (FindFailed findFailed) {
            log.error(String.format("%1$s '%2$s' %3$s '%4$s'", log.getLoc("loc.img"), firstPic, log.getLoc("loc.img.not.move"), secondPic));
        }
    }

    public static void click(String pic) {
        setImagesPath();
        try {
            screenWait();
            screen.click(new Pattern(pic).similar(SIMILAR_PERCENT));
            log.info(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.img"), pic, log.getLoc("loc.img.click")));
        } catch (FindFailed findFailed) {
            log.error(String.format("%1$s '%2$s' %3$s", log.getLoc("loc.img"), pic, log.getLoc("log.img.not.click")));
        }
    }

    public static boolean find(String pic) {
        setImagesPath();
        screenWait();
        return screen.exists(new Pattern(pic).exact().similar(SIMILAR_PERCENT)) != null;
    }

    private static void screenWait() {
        screen.wait(Double.parseDouble(PropertyReader.getProperty(WAIT_TIME)));
    }

    private static void setImagesPath() {
        ImagePath.setBundlePath(PropertyReader.getProperty(IMAGES_PATH));
    }

}
