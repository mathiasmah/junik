package it.mathiasmah.junik.client.models;

import java.time.ZonedDateTime;

/**
 * A Pojo that represents a Unik instance.
 *
 * @see it.mathiasmah.junik.client.Instances
 */
public class Instance {
    private String id;
    private String name;
    private InstanceState state;
    private String ipAddress;
    private String imageId;
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

    public InstanceState getState() {
        return state;
    }

    public void setState(InstanceState state) {
        this.state = state;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
        return "Instance{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state=" + state +
                ", ipAddress='" + ipAddress + '\'' +
                ", imageId='" + imageId + '\'' +
                ", infrastructure=" + infrastructure +
                ", created=" + created +
                '}';
    }
}
