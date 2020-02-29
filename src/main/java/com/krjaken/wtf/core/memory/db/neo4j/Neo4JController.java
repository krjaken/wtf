package com.krjaken.wtf.core.memory.db.neo4j;

import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.*;

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
    }

    public Object createNodes(String script, Map<String, String> parameters) {
        try (Session session = driver.session()) {
            return session.writeTransaction((TransactionWork<Object>) tx -> {
                Result result = tx.run(script,
                        parameters(parameters));
                return result.single().get(0).asString();
            });
        }
    }

}
