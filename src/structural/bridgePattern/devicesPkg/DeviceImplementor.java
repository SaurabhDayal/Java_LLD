package structural.bridgePattern.devicesPkg;

// Implementation aka Implementor
public interface DeviceImplementor {
    boolean isEnabled();

    void enable();

    void disable();

    int getVolume();

    void setVolume(int percent);

    int getChannel();

    void setChannel(int channel);

    void printStatus();
}