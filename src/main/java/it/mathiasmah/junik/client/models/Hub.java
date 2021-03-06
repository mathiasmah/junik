package it.mathiasmah.junik.client.models;

/**
 * A Pojo that holds all the data needed to connect to a Unik hub.
 *
 * @see it.mathiasmah.junik.client.Hubs
 */
public class Hub {

    private String url;
    private String user;
    private String pass;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Hub{" +
                "url='" + url + '\'' +
                ", user='" + user + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
