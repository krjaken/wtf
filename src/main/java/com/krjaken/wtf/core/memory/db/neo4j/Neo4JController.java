package com.krjaken.wtf.core.memory.db.neo4j;

import com.krjaken.wtf.Config;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

@Service
@Slf4j
public class Neo4JController implements AutoCloseable {
    private Driver driver = null;

    public Neo4JController(Config config) {
        log.info("init Neo4J");
        try {

            String n4jUrl = config.getProperty("neo4jUrl");
            String neo4jUser = config.getProperty("neo4jUser");
            String neo4jPassword = config.getProperty("neo4jPassword");
            driver = GraphDatabase.driver(n4jUrl, AuthTokens.basic(neo4jUser, neo4jPassword));
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        driver.close();
        log.info("Neo4J driver closed");
    }

    public void executeScript(String script, Map<String, String> parameters) {
        log.info("executeScript {}", script);
        try (Session session = driver.session()) {
            if (parameters == null) {
                session.run(script);
            } else {
                session.run(script, parameters(parameters));
            }
        }
    }

    public Node getNode(String script, Map<String, String> parameters) {
        try (Session session = driver.session()) {
            return session.writeTransaction((TransactionWork<Node>) tx -> {
                Result result;
                if (parameters == null) {
                    result = tx.run(script);
                } else {
                    result = tx.run(script,
                            parameters(parameters));
                }

                return result.single().get(0).asNode();
            });
        }
    }

    public List<Node> getNodes(String script, Map<String, String> parameters) {
        try (Session session = driver.session()) {
            return session.writeTransaction((TransactionWork<List<Node>>) tx -> {
                List<Node> nodes = new LinkedList<>();
                Result result;
                if (parameters == null) {
                    result = tx.run(script);
                } else {
                    result = tx.run(script,
                            parameters(parameters));
                }
                while (result.hasNext()) {
                    Record next = result.next();
                    for (Map.Entry<String, Object> column : next.asMap().entrySet()) {
                        Object value = column.getValue();
                        if (value instanceof Node) {
                            nodes.add((Node) value);
                        } else {
                            new IllegalThreadStateException("Component is not a NODE");
                        }
                    }
                }

                return nodes;
            });
        }
    }

}
