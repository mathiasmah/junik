package it.mathiasmah.junik.client.models;

import java.time.ZonedDateTime;

public class Volume {
    private String id;
    private String name;
    private long sizeMb;
    private String attachment;
    private Infrastructure infrastructure;
    private ZonedDateTime created;

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

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Infrastructure getInfrastructure() {
        return infrastructure;
    }

    public void setInfrastructure(Infrastructure infrastructure) {
        this.infrastructure = infrastructure;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "Volume{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", sizeMb=" + sizeMb +
                ", attachment='" + attachment + '\'' +
                ", infrastructure=" + infrastructure +
                ", created=" + created +
                '}';
    }
}
