package structural.bridgePattern.remotesAbstractionPkg;


import structural.bridgePattern.devicesImplementorPkg.DeviceImplementor;

public class BasicRemote implements Remote {

    protected DeviceImplementor deviceImplementor;

    public BasicRemote() {
    }

    public BasicRemote(DeviceImplementor deviceImplementor) {
        this.deviceImplementor = deviceImplementor;
    }

    @Override
    public void power() {
        System.out.println("Remote: power toggle");
        if (deviceImplementor.isEnabled()) {
            deviceImplementor.disable();
        } else {
            deviceImplementor.enable();
        }
    }

    @Override
    public void volumeDown() {
        System.out.println("Remote: volume down");
        deviceImplementor.setVolume(deviceImplementor.getVolume() - 10);
    }

    @Override
    public void volumeUp() {
        System.out.println("Remote: volume up");
        deviceImplementor.setVolume(deviceImplementor.getVolume() + 10);
    }

    @Override
    public void channelDown() {
        System.out.println("Remote: channel down");
        deviceImplementor.setChannel(deviceImplementor.getChannel() - 1);
    }

    @Override
    public void channelUp() {
        System.out.println("Remote: channel up");
        deviceImplementor.setChannel(deviceImplementor.getChannel() + 1);
    }
}