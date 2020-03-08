package com.krjaken.wtf.core.curiosity;

import com.krjaken.wtf.core.curiosity.research.Research;
import com.krjaken.wtf.interaces.WtfService;
import com.krjaken.wtf.core.memory.MemoryService;
import org.springframework.stereotype.Service;

@Service
public class CuriosityCore implements WtfService {
    private MemoryService memoryService;
    private Research research;

    public CuriosityCore(MemoryService memoryService){
        this.memoryService = memoryService;
    }

    public void init() {
        memoryService.getAverageSuccessTranslate();
    }

    public void down() {

    }

    public void searchInfo(){

    }

    public void research(){

    }
}
