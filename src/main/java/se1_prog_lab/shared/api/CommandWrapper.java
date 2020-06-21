package se1_prog_lab.shared.api;

import se1_prog_lab.client.commands.BasicCommand;

import java.io.Serializable;

public class CommandWrapper implements Serializable {
    private final BasicCommand command;
    private final AuthData authData;

    public CommandWrapper(BasicCommand command, AuthData authData) {
        this.command = command;
        this.authData = authData;
    }

    public BasicCommand getCommand() {
        return command;
    }

    public AuthData getAuthData() {
        return authData;
    }
}