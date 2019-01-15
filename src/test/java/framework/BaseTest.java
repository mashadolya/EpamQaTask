package framework;


import org.testng.annotations.Test;

public abstract class BaseTest extends BaseEntity {

    public abstract void runTest();

    @Test
    protected void xTest() {
        info(getLoc("loc.test.start"));
        runTest();
        info(getLoc("loc.test.end"));
        logger.makeSeparator();
    }

    protected String formatLogMsg(final String message) {
        return String.format("%1$s %2$s", this.getClass().getName(), message);
    }
}
