package tutby.pageobject.menu;

public enum MailUserMenuItem {
    /**
     * These variables can be used to navigate the right header user menu of the Mail page.
     */
    ACCOUNT_MANAGMENT("Управление аккаунтом"),
    CHANGE_PASSWORD("Сменить пароль"),
    HELP_FEEDBACK("Помощь и обратная связь"),
    LOG_OUT("Выйти из сервисов Яндекса"),
    MAIN_PAGE("Главная страница Яндекса");

    private final String menuItem;

    MailUserMenuItem(String menuItem) {
        this.menuItem = menuItem;
    }

    public String getMailUserMenuItem() {
        return menuItem;
    }

}
