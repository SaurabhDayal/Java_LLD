package structural.bridgePattern;

import structural.bridgePattern.devicesImplementorPkg.DeviceImplementor;
import structural.bridgePattern.devicesImplementorPkg.Radio;
import structural.bridgePattern.devicesImplementorPkg.Tv;
import structural.bridgePattern.remotesAbstractionPkg.AdvancedRemote;
import structural.bridgePattern.remotesAbstractionPkg.BasicRemote;

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