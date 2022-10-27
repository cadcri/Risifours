package com.issougames.risifours;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

import static org.junit.Assert.assertEquals;

public class RedisConnectionTest {
    private RedisConnection conn;

    @Rule
    public GenericContainer redis = new GenericContainer(DockerImageName.parse("redis"))
            .withExposedPorts(6379);

    @Before
    public void setUp() {
        String address = redis.getHost();
        Integer port = redis.getFirstMappedPort();
        conn = new RedisConnection(address, port);
    }

    @Test
    public void testSimplePutAndGet() {
        conn.put("test", "example");
        String retrieved = conn.get("test");
        assertEquals(retrieved,"example");
    }
}