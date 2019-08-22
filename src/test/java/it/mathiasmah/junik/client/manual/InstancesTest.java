package it.mathiasmah.junik.client.manual;


import it.mathiasmah.junik.client.Client;
import it.mathiasmah.junik.client.exceptions.UnikException;
import it.mathiasmah.junik.client.models.Instance;
import it.mathiasmah.junik.client.models.RunInstance;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

@Tag("Manual")
public class InstancesTest {

    private static Client client;

    @BeforeAll
    public static void before() {
        client = new Client("http://localhost:3000");
    }

    @Test
    public void getAllAvailable() throws UnikException {
        List<Instance> allAvailable = client.instances().getAllAvailable();
        System.out.println(allAvailable);
    }

    @Test
    public void describe() throws UnikException {
        Instance instance = client.instances().describe("test-instance");
        System.out.println(instance);
    }

    @Test
    public void start() throws UnikException {
        client.instances().start("test-instance");
    }

    @Test
    public void stop() throws UnikException {
        client.instances().stop("test-instance3");
    }

    @Test
    public void run() throws UnikException {
        RunInstance runInstance = new RunInstance();
        runInstance.setImageName("test");
        runInstance.setInstanceName("test-instance3");
        runInstance.setMemoryMb(10);
        runInstance.setNoCleanUp(false);
        runInstance.setDebugMode(false);
        runInstance.setMounts(Collections.singletonMap("/test", "test-volume"));

        Instance instance = client.instances().run(runInstance);
        System.out.println(instance);
    }

    @Test
    public void delete() throws UnikException {
        client.instances().delete("test-instance", false);
    }

    @Test
    public void logsAsString() throws UnikException {
        String logs = client.instances().logsAsString("VboxUnikInstanceListener");
        System.out.println(logs);
    }

    @Test
    public void logsAsStream() throws UnikException {
        client.instances().logToStream("VboxUnikInstanceListener", true, System.out);
    }
}
