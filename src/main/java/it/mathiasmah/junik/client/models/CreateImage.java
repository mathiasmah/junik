package it.mathiasmah.junik.client.models;

import java.util.Collections;
import java.util.List;

/**
 * A Pojo that holds all the data needed to create a new {@link Image}
 *
 * @see it.mathiasmah.junik.client.Images
 */
public class CreateImage {

    private String name;
    private String tarFile;
    private boolean noCleanup;
    private boolean force;
    private List<String> args;
    private String provider;
    private String base;
    private String language;
    private List<String> mounts;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarFile() {
        return tarFile;
    }

    public void setTarFile(String tarFile) {
        this.tarFile = tarFile;
    }

    public boolean isNoCleanup() {
        return noCleanup;
    }

    public void setNoCleanup(boolean noCleanup) {
        this.noCleanup = noCleanup;
    }

    public boolean isForce() {
        return force;
    }

    public void setForce(boolean force) {
        this.force = force;
    }

    public List<String> getArgs() {
        if (args == null) {
            args = Collections.emptyList();
        }
        return args;
    }

    public void setArgs(List<String> args) {
        this.args = args;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public List<String> getMounts() {
        if (mounts == null) {
            mounts = Collections.emptyList();
        }
        return mounts;
    }

    public void setMounts(List<String> mounts) {
        this.mounts = mounts;
    }
}
