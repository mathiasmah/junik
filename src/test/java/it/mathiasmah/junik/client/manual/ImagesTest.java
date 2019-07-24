package it.mathiasmah.junik.client.manual;


import it.mathiasmah.junik.client.Client;
import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.CreateImage;
import it.mathiasmah.junik.client.models.Image;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@Tag("Manual")
public class ImagesTest {

    private static Client client;

    @BeforeAll
    public static void before() {
        client = new Client("http://localhost:3000");
    }

    @Test
    public void getAllAvailable() throws UnikException {
        List<Image> allAvailable = client.images().getAllAvailable();
        System.out.println(allAvailable);
    }

    @Test
    public void describe() throws UnikException {
        Image image = client.images().describe( "test");
        System.out.println(image);
    }

    @Test
    public void create() throws UnikException {
        CreateImage createImage = new CreateImage();
        createImage.setName("test");
        createImage.setNoCleanup(true);
        createImage.setForce(false);
        createImage.setProvider("virtualbox");
        createImage.setLanguage("nodejs");
        createImage.setBase("rump");
        createImage.setTarFile("src/test/resources/node-unikernel.tar.gz");
        createImage.setMounts(Collections.singletonList("/test"));

        Image image = client.images().create(createImage);
        System.out.println(image);
    }

    @Test
    public void delete() throws UnikException {
        client.images().delete("test2",false);
    }
}
