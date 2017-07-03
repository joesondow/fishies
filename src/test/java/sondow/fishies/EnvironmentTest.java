package sondow.fishies;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.EnvironmentVariables;

public class EnvironmentTest {

    @Rule
    public final EnvironmentVariables envVars = new EnvironmentVariables();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public final void testGet() {
        envVars.set("twitter_access_id", "1234567890ABCDEFGHIJKLMNOPQRSTUVWXYZmoondoggy");
        envVars.set("twitter_handle", "SchoolsOfFish");
        assertEquals("moondoggy", Environment.get("twitter_access_id"));
        assertEquals("SchoolsOfFish", Environment.get("twitter_handle"));
    }

}
