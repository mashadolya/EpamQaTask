package tutby.pageobject.menu;

public enum HeaderRightMenuItem {
    /**
     * These variables can be used to navigate the right header menu of the Mail page.
     */
    USER("head-user"),
    SETTINGS("head-settings-controls"),
    THEMES("yandex-plus-renderer"),
    SEARCH("search-box");

    private final String menuItem;

    HeaderRightMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
