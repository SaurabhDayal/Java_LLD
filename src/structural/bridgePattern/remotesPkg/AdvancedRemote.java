package structural.bridgePattern.remotesPkg;

import structural.bridgePattern.devicesPkg.DeviceImplementor;

public class AdvancedRemote extends BasicRemote {

    public AdvancedRemote(DeviceImplementor deviceImplementor) {
        super.deviceImplementor = deviceImplementor;
    }

    public void mute() {
        System.out.println("Remote: mute");
        deviceImplementor.setVolume(0);
    }
}