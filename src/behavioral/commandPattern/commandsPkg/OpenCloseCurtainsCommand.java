package behavioral.commandPattern.commandsPkg;

import behavioral.commandPattern.receiverPkg.Curtains;

public record OpenCloseCurtainsCommand(Curtains curtains) implements Command {

    @Override
    public void execute() {
        curtains.openClose();
    }
}