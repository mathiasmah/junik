package it.mathiasmah.junik.client.models;

import java.time.ZonedDateTime;

/**
 * A Pojo that represents a Unik image.
 *
 * @see it.mathiasmah.junik.client.Images
 */
public class Image {
    private String id;
    private String name;
    private long sizeMb;
    private ZonedDateTime created;
    private Infrastructure infrastructure;
    private StageSpec stageSpec;
    private RunSpec runSpec;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getSizeMb() {
        return sizeMb;
    }

    public void setSizeMb(long sizeMb) {
        this.sizeMb = sizeMb;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public StageSpec getStageSpec() {
        return stageSpec;
    }

    public void setStageSpec(StageSpec stageSpec) {
        this.stageSpec = stageSpec;
    }

    public RunSpec getRunSpec() {
        return runSpec;
    }

    public void setRunSpec(RunSpec runSpec) {
        this.runSpec = runSpec;
    }

    @Override
    public String toString() {
        return "Image{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sizeMb=" + sizeMb +
                ", created=" + created +
                ", infrastructure=" + infrastructure +
                ", stageSpec=" + stageSpec +
                ", runSpec=" + runSpec +
                '}';
    }
}
