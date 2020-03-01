package com.krjaken.wtf.core.memory.db.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;
import org.neo4j.driver.types.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.neo4j.driver.Values.parameters;

@Slf4j
public class Neo4JController implements AutoCloseable {
    private final Driver driver;

    public Neo4JController(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
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
