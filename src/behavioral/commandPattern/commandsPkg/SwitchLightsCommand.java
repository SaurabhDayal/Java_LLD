package behavioral.commandPattern.commandsPkg;

import behavioral.commandPattern.receiverPkg.Light;

public record SwitchLightsCommand(Light light) implements Command {

    @Override
    public void execute() {
        light.switchLights();
    }
}
