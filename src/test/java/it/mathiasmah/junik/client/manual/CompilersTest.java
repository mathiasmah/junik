package it.mathiasmah.junik.client.manual;


import it.mathiasmah.junik.client.Client;
import it.mathiasmah.junik.client.exceptions.UnikException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.List;

@Tag("Manual")
public class CompilersTest {

    private static Client client;

    @BeforeAll
    public static void before() {
        client = new Client("http://localhost:3000");
    }

    @Test
    public void getAllAvailable() throws UnikException {
        List<String> allAvailable = client.compilers().getAllAvailable();
        System.out.println(allAvailable);
    }

    @Test
    public void describe() throws UnikException {
        String describe = client.compilers().describe("virtualbox", "osv", "java");
        System.out.println(describe);
    }
}
