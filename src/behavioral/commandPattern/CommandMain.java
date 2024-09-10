package behavioral.commandPattern;

import behavioral.commandPattern.commandsPkg.OpenCloseCurtainsCommand;
import behavioral.commandPattern.commandsPkg.SwitchLightsCommand;
import behavioral.commandPattern.componentsPkg.FloorLamp;
import behavioral.commandPattern.componentsPkg.Room;

public class CommandMain {

    public static void main(String[] args) {

        Room room = new Room();
        room.setCommand(new OpenCloseCurtainsCommand(room.getCurtains()));
        room.executeCommand();
        System.out.println(room.curtainsOpen());

        System.out.println("==========================================");

        FloorLamp lamp = new FloorLamp();
        lamp.setCommand(new SwitchLightsCommand(lamp.getLight()));
        lamp.executeCommand();
        System.out.println(lamp.isLightOn());
    }
}
