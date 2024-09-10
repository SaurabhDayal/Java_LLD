package structural.bridgePattern.remotesAbstractionPkg;

import structural.bridgePattern.devicesImplementorPkg.DeviceImplementor;

public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(DeviceImplementor deviceImplementor) {
        super.deviceImplementor = deviceImplementor;
    }

    public void mute() {
        System.out.println("Remote: mute");
        deviceImplementor.setVolume(0);
    }
}