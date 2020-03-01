package com.krjaken.wtf.core.memory;

import com.krjaken.wtf.api.core.enums.LanguageEnum;
import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import com.krjaken.wtf.core.memory.db.neo4j.Neo4JController;
import com.krjaken.wtf.interaces.WtfService;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


@Slf4j
@Service
public class MemoryService implements WtfService {
    private Neo4JController neo4JController;

    public MemoryService() {
        init();
    }

    public void createLanguage(LanguageDto languageDto) {
        log.info("putLanguage: {}", languageDto);
        String script = "CREATE (:Language{eng:'" + languageDto.getLenguage_eng() +
                "', origin:'" + languageDto.getLenguage_original() + "'})";
        neo4JController.createNodes(script, null);
    }

    public void inputLanguageData(String filePath, LanguageDto languageDto) {
        log.info("runInitializationRussianLanguage run path: {}, language: {}", filePath, languageDto);
        File rusFile = new File(filePath);
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(rusFile), StandardCharsets.UTF_8));
            String line;
            while ((line = reader.readLine()) != null) {
                log.info(line);
                String script = "MATCH (l:Language) WHERE l.eng='" + languageDto.getLenguage_eng() + "' WITH l " +
                        "CREATE (:Word {value:'" + line + "'})-[:IS_PART]->(l)";
                neo4JController.createNodes(script, null);
            }
            reader.close();

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(String.format("Exception occurred trying to read '%s'.", rusFile));
        }
    }

    public LanguageDto getLanguage(LanguageEnum languageEnum) {
        log.info("getLanguage {}", languageEnum.getEngName());
        Node node = neo4JController.getNode("MATCH (l:Language) WHERE l.eng = '" + languageEnum.getEngName() + "' RETURN l", null);
        if (node != null) {
            log.info(node.toString());
            return new LanguageDto(node.get("eng").asString(), node.get("origin").asString());
        }
        return null;
    }

    public List<LanguageDto> findAll() {
        log.info("findAll");
        List<LanguageDto> list = new ArrayList<>();
        LanguageDto languageDto = new LanguageDto();
        languageDto.setLenguage_original("русс");
        languageDto.setLenguage_eng("eng");
        list.add(languageDto);
        return list;
    }

    public void init() {
        log.info("init Neo4J");
        try {
            InputStream input = new FileInputStream("src/main/resources/core/db/dataBase.properties");
            Properties prop = new Properties();
            prop.load(input);
            String n4jUrl = prop.getProperty("neo4jUrl");
            String neo4jUser = prop.getProperty("neo4jUser");
            String neo4jPassword = prop.getProperty("neo4jPassword");
            neo4JController = new Neo4JController(n4jUrl, neo4jUser, neo4jPassword);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public void down() {

    }

    public void getAverageSuccessTranslate() {

    }
}
