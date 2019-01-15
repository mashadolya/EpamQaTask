package tutby.pageobject.menu;

public enum LeftBoxMenuItem {
    /**
     * These variables can be used to navigate the left mailbox menu of the Mail page.
     */
    INBOX("Входящие"),
    SENT("Отправленные"),
    TRASH("Удалённые"),
    SPAM("Спам"),
    DRAFTS("Черновики"),
    CREATE_FOLDER("Создать папку");

    private final String menuItem;

    LeftBoxMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMenuItem() {
        return menuItem;
    }
}
