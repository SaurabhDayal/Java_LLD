package structural.bridgePattern;

import structural.bridgePattern.devicesPkg.DeviceImplementor;
import structural.bridgePattern.devicesPkg.Radio;
import structural.bridgePattern.devicesPkg.Tv;
import structural.bridgePattern.remotesPkg.AdvancedRemote;
import structural.bridgePattern.remotesPkg.BasicRemote;

// Bridge is a structural design pattern that lets you split
// a large class or a set of closely related classes into two separate
// hierarchies—abstraction and implementation—which
// can be developed independently of each other.
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