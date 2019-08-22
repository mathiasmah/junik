package it.mathiasmah.junik.client.models;

/**
 * Defines a mapping between an image mount point and a volume
 */
public class DeviceMapping {
    private String mountPoint;
    private String deviceName;

    public String getMountPoint() {
        return mountPoint;
    }

    public void setMountPoint(String mountPoint) {
        this.mountPoint = mountPoint;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "DeviceMapping{" +
                "mountPoint='" + mountPoint + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
