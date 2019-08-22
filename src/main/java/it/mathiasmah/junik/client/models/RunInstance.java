package it.mathiasmah.junik.client.models;

import java.util.Collections;
import java.util.Map;

/**
 * A Pojo that holds all the data needed to run a new {@link Instance}
 *
 * @see it.mathiasmah.junik.client.Instances
 */
public class RunInstance {
    private String instanceName;
    private String imageName;
    private Map<String, String> mounts;
    private Map<String, String> env;
    private int memoryMb;
    private boolean noCleanUp;
    private boolean debugMode;

    public String getInstanceName() {
        return instanceName;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public Map<String, String> getMounts() {
        if (mounts == null) {
            mounts = Collections.emptyMap();
        }
        return mounts;
    }

    public void setMounts(Map<String, String> mounts) {
        this.mounts = mounts;
    }

    public Map<String, String> getEnv() {
        if (env == null) {
            env = Collections.emptyMap();
        }
        return env;
    }

    public void setEnv(Map<String, String> env) {
        this.env = env;
    }

    public int getMemoryMb() {
        return memoryMb;
    }

    public void setMemoryMb(int memoryMb) {
        this.memoryMb = memoryMb;
    }

    public boolean isNoCleanUp() {
        return noCleanUp;
    }

    public void setNoCleanUp(boolean noCleanUp) {
        this.noCleanUp = noCleanUp;
    }

    public boolean isDebugMode() {
        return debugMode;
    }

    public void setDebugMode(boolean debugMode) {
        this.debugMode = debugMode;
    }
}
