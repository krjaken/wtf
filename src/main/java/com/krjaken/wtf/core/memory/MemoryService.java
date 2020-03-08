package com.krjaken.wtf.core.memory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.krjaken.wtf.api.core.enums.LanguageEnum;
import com.krjaken.wtf.core.languages.dtos.ConceptDto;
import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import com.krjaken.wtf.core.memory.db.neo4j.Neo4JController;
import com.krjaken.wtf.interaces.WtfService;
import lombok.extern.slf4j.Slf4j;
import org.neo4j.driver.types.Node;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MemoryService implements WtfService {
    private Neo4JController neo4JController;
    private ObjectMapper objectMapper = new ObjectMapper();

    public MemoryService(Neo4JController neo4JController) {
        this.neo4JController = neo4JController;
        setNodeParameterUniq("Language", "eng");
        setNodeParameterUniq("Word", "value");
    }

    public void createLanguage(LanguageDto languageDto) {
        log.info("putLanguage: {}", languageDto);
        String script = String.format("CREATE (:%s)", languageDto);
        neo4JController.executeScript(script, null);
    }

    private void setNodeParameterUniq(String nodeLabel, String parameter) {
        log.info("setNodeParameterUniq nodeLebel: {}, parameter: {}", nodeLabel, parameter);
        try {
            neo4JController.executeScript("CREATE CONSTRAINT ON (n:" + nodeLabel + ") ASSERT n." + parameter + " IS UNIQUE", null);
        } catch (Exception e) {
            log.warn("CONSTRAINT to node: {}, parameter: {}, not created. Reason: {}", nodeLabel, parameter, e.getMessage());
        }
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
                neo4JController.executeScript(script, null);
            }
            reader.close();

        } catch (Exception e) {
            log.error(e.getMessage());
            log.error(String.format("Exception occurred trying to read '%s'.", rusFile));
        }
    }

    public void inputConcept(ConceptDto conceptDto, LanguageDto languageDto) {
        log.info("inputConcept run conceptDto: {}, language: {}", conceptDto, languageDto);
        String script = "MATCH (l:Language) WHERE l.eng='" + languageDto.getLenguage_eng() + "' WITH l " +
                String.format("CREATE (concept:Concept %s)", conceptDto.createCypherScript()) + "-[:IS_PART]->(l) ";
        log.info(script);
        if (conceptDto.getPrototype()!=null){
            script+="MATCH (prototype:Concept) WHERE prototype.conceptExample ='"+conceptDto.getPrototype().getConceptExample()+"' WITH prototype " +
                    "CREATE (concept)-[:PROTOTYPE]->(prototype) ";
        }
        neo4JController.executeScript(script, null);
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

    public List<LanguageDto> getLanguages() {
        log.info("getLanguages");
        List<Node> nodes = neo4JController.getNodes("MATCH (l:Language) RETURN l", null);
        List<LanguageDto> list = new ArrayList<>();
        for (Node node : nodes) {
            try {
                log.info(objectMapper.writeValueAsString(node));
            } catch (JsonProcessingException e) {
                log.info(e.getMessage());
            }
            LanguageDto languageDto = new LanguageDto();
            Object original = node.get("origin");
            languageDto.setLenguage_original(original != null ? original.toString() : null);
            Object eng = node.get("eng");
            languageDto.setLenguage_eng(eng != null ? eng.toString() : null);
            list.add(languageDto);
        }
        return list;
    }

    public void init() {

    }

    public void down() {

    }

    public void getAverageSuccessTranslate() {

    }
}
