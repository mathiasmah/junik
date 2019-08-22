package it.mathiasmah.junik.client.models;

/**
 * A Pojo that holds all the data needed to create a new {@link Volume}
 *
 * @see it.mathiasmah.junik.client.Volumes
 */
public class CreateVolume {
    private String name;
    private String type;
    private boolean raw;
    private boolean noCleanup;
    private String provider;
    private long size;
    private String tarFile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isRaw() {
        return raw;
    }

    public void setRaw(boolean raw) {
        this.raw = raw;
    }

    public boolean isNoCleanup() {
        return noCleanup;
    }

    public void setNoCleanup(boolean noCleanup) {
        this.noCleanup = noCleanup;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTarFile() {
        return tarFile;
    }

    public void setTarFile(String tarFile) {
        this.tarFile = tarFile;
    }
}
