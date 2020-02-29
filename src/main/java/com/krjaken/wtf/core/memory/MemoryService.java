package com.krjaken.wtf.core.memory;

import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import com.krjaken.wtf.core.memory.db.neo4j.Neo4JController;
import com.krjaken.wtf.interaces.WtfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Service
public class MemoryService implements WtfService {
    private Neo4JController neo4JController;
    private String n4jUrl;
    private String neo4jUser;
    private String neo4jPassword;

    public MemoryService() {
        init();
    }

    public void runInitializationRussianLanguage() {
        String script = "CREATE " +
                "(l:Language{name: 'Russian'}), " +
                "(word:Word{language:'RU', infinitive:'Сигизмунд', partOfSpeech:'Verb' })";
        neo4JController.createNodes(script, null);
    }

    public List<LanguageDto> findAll() {
        log.info("findAll");
        List<LanguageDto> list = new ArrayList<>();
        LanguageDto languageDto = new LanguageDto();
        languageDto.setId(1L);
        languageDto.setLenguage_original("русс");
        languageDto.setLenguage_eng("eng");
        list.add(languageDto);
        return list;
    }

    public void init() {
        try {
            n4jUrl = System.getProperty("neo4jUrl");
            neo4jUser = System.getProperty("neo4jUser");
            neo4jPassword = System.getProperty("neo4jPassword");
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
