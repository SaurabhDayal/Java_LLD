package behavioral.commandPattern.commandsPkg;

import behavioral.commandPattern.componentsPkg.Light;

public record SwitchLightsCommand(Light light) implements Command {

    @Override
    public void execute() {
        light.switchLights();
    }
}
