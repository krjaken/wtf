package com.krjaken.wtf;

import com.krjaken.wtf.core.memory.db.neo4j.Neo4JController;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WtfApplicationTests {
    private Neo4JController neo4JController;

    @Test
    void contextLoads() {
        initNeo4J();
        Assert.assertNotNull(neo4JController);
    }

    private void initNeo4J() {
        neo4JController = new Neo4JController(System.getProperty("neo4jUrl"),
                System.getProperty("neo4jUser"),
                System.getProperty("neo4jPassword"));
    }

}
