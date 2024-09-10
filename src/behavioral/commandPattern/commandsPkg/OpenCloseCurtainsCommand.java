package behavioral.commandPattern.commandsPkg;

import behavioral.commandPattern.componentsPkg.Curtains;

public record OpenCloseCurtainsCommand(Curtains curtains) implements Command {

    @Override
    public void execute() {
        curtains.openClose();
    }
}