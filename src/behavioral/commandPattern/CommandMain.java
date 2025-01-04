package behavioral.commandPattern;

import behavioral.commandPattern.commandsPkg.OpenCloseCurtainsCommand;
import behavioral.commandPattern.commandsPkg.SwitchLightsCommand;
import behavioral.commandPattern.invokerPkg.FloorLamp;
import behavioral.commandPattern.invokerPkg.Room;

public class CommandMain {

    public static void main(String[] args) {

        Room room = new Room();
        room.setCommand(new OpenCloseCurtainsCommand(room.getCurtains()));
        room.executeCommand();
        System.out.println("Curtains open: " + room.curtainsOpen());

        System.out.println("==========================================");

        FloorLamp lamp = new FloorLamp();
        lamp.setCommand(new SwitchLightsCommand(lamp.getLight()));
        lamp.executeCommand();
        System.out.println("Light on: " + lamp.isLightOn());
    }
}
