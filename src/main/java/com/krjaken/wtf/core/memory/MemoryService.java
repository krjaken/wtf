package com.krjaken.wtf.core.memory;

import com.krjaken.wtf.core.memory.db.dtos.LanguageDto;
import com.krjaken.wtf.core.memory.db.mysql.MySql;
import com.krjaken.wtf.interaces.WtfService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemoryService implements WtfService {
    private MySql mySql;

    public MemoryService(MySql mySql) {
        this.mySql = mySql;
    }

    public List<LanguageDto> findAll() {
        return mySql.findAll();
    }

    public void init() {

    }

    public void down() {

    }

    public void getAverageSuccessTranslate() {

    }
}
