package com.krjaken.wtf.core.curiosity;

import com.krjaken.wtf.interaces.WtfService;
import com.krjaken.wtf.core.memory.MemoryService;
import org.springframework.stereotype.Service;

@Service
public class CuriosityCore implements WtfService {
    private MemoryService memoryService;

    public CuriosityCore(MemoryService memoryService){
        this.memoryService = memoryService;
    }

    public void init() {
        memoryService.getAverageSuccessTranslate();
    }

    public void down() {

    }
}
