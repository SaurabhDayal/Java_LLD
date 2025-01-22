package structural.bridgePattern;

import structural.bridgePattern.devicesPkg.DeviceImplementor;
import structural.bridgePattern.devicesPkg.Radio;
import structural.bridgePattern.devicesPkg.Tv;
import structural.bridgePattern.remotesPkg.AdvancedRemote;
import structural.bridgePattern.remotesPkg.BasicRemote;

// Bridge pattern decouples an abstraction from its implementation
// so that the two can vary independently.
public class BridgeMain {
    public static void main(String[] args) {
        System.out.println();
        testDevice(new Tv());
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
        System.out.println();
        testDevice(new Radio());
    }

    public static void testDevice(DeviceImplementor deviceImplementor) {
        System.out.println("Tests with basic remote.");
        BasicRemote basicRemote = new BasicRemote(deviceImplementor);
        basicRemote.power();
        deviceImplementor.printStatus();

        System.out.println("Tests with advanced remote.");
        AdvancedRemote advancedRemote = new AdvancedRemote(deviceImplementor);
        advancedRemote.power();
        advancedRemote.mute();
        deviceImplementor.printStatus();
    }
}