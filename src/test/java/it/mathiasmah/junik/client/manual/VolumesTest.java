package it.mathiasmah.junik.client.manual;

import it.mathiasmah.junik.client.Client;
import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.CreateVolume;
import it.mathiasmah.junik.client.models.Volume;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

@Tag("Manual")
public class VolumesTest {

    private static Client client;

    @BeforeAll
    public static void before() {
        client = new Client("http://localhost:3000");
    }

    @Test
    public void getAllAvailable() throws UnikException {
        List<Volume> allAvailable = client.volumes().getAllAvailable();
        System.out.println(allAvailable);
    }

    @Test
    public void describe() throws UnikException {
        Volume volume = client.volumes().describe( "test-volume");
        System.out.println(volume);
    }

    @Test
    public void attach() throws UnikException {
        client.volumes().attach("test-volume","test-instance3", "/test");
    }

    @Test
    public void detach() throws UnikException {
        client.volumes().detach("test-volume");
    }

    @Test
    public void create() throws UnikException {
        CreateVolume createVolume = new CreateVolume();
        createVolume.setName("test-volume");
        createVolume.setNoCleanup(true);
        createVolume.setSize(10);
        createVolume.setProvider("virtualbox");

        Volume volume = client.volumes().create(createVolume);
        System.out.println(volume);
    }

    @Test
    public void delete() throws UnikException {
        client.volumes().delete("true",false);
    }
}
