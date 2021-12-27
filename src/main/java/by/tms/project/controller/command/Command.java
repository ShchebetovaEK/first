package by.tms.project.controller.command;

import by.tms.project.exception.CommandException;
import jakarta.servlet.http.HttpServletRequest;

/**
 * The interface Command.
 *
 * @author ShchebetovaEK
 */
public interface Command {

    /**
     * Execute command.
     *
     * @param request the request
     * @return the router
     * @throws CommandException the command exception
     */

    Router execute (HttpServletRequest request) throws CommandException;
}
