package by.tms.project.controller.command;

import static by.tms.project.controller.command.PagePath.ERROR_404_PAGE;
import static by.tms.project.controller.command.PagePath.INDEX;

/**
 * The Class Router
 *
 * @author ShchebetovaEK
 */
public class Router {
    private String page = INDEX;
    private Router.RouterType type;

    /**
     * Instantiates a new Router
     */
    public Router() {
        this.type = Router.RouterType.FORWARD;
    }

    /**
     * Get page
     *
     * @return the page
     */
    public String getPage() {
        return page;
    }

    /**
     * Set page
     *
     * @param page the page
     */
    public void setPage(String page) {
        if (page == null) {
            page = ERROR_404_PAGE;
        }
        this.page = page;
    }

    /**
     * Get router type.
     *
     * @return the router type
     */
    public Router.RouterType getType() {
        return this.type;
    }

    /**
     * Set redirect.
     */
    public void setRedirect() {
        this.type = Router.RouterType.REDIRECT;
    }

    public enum RouterType {
        FORWARD,
        REDIRECT;

        RouterType() {
        }
    }
}
