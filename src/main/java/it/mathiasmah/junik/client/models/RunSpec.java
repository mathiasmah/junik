package it.mathiasmah.junik.client.models;

import java.util.List;

/**
 * A Pojo that holds the specification of a running instance
 *
 * @see Instance
 */
public class RunSpec {
    private List<DeviceMapping> deviceMappings;
    private int defaultInstanceMemory;
    private int minInstanceDiskMB;
    private StorageDriver storageDriver;
    private VsphereNetworkType vsphereNetworkType;
    private String compiler;

    public List<DeviceMapping> getDeviceMappings() {
        return deviceMappings;
    }

    public void setDeviceMappings(List<DeviceMapping> deviceMappings) {
        this.deviceMappings = deviceMappings;
    }

    public int getDefaultInstanceMemory() {
        return defaultInstanceMemory;
    }

    public void setDefaultInstanceMemory(int defaultInstanceMemory) {
        this.defaultInstanceMemory = defaultInstanceMemory;
    }

    public int getMinInstanceDiskMB() {
        return minInstanceDiskMB;
    }

    public void setMinInstanceDiskMB(int minInstanceDiskMB) {
        this.minInstanceDiskMB = minInstanceDiskMB;
    }

    public StorageDriver getStorageDriver() {
        return storageDriver;
    }

    public void setStorageDriver(StorageDriver storageDriver) {
        this.storageDriver = storageDriver;
    }

    public VsphereNetworkType getVsphereNetworkType() {
        return vsphereNetworkType;
    }

    public void setVsphereNetworkType(VsphereNetworkType vsphereNetworkType) {
        this.vsphereNetworkType = vsphereNetworkType;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    @Override
    public String toString() {
        return "RunSpec{" +
                "deviceMappings=" + deviceMappings +
                ", defaultInstanceMemory=" + defaultInstanceMemory +
                ", minInstanceDiskMB=" + minInstanceDiskMB +
                ", storageDriver=" + storageDriver +
                ", vsphereNetworkType=" + vsphereNetworkType +
                ", compiler='" + compiler + '\'' +
                '}';
    }
}
