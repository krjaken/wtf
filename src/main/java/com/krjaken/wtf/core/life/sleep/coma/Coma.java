package com.krjaken.wtf.core.life.sleep.coma;

import com.krjaken.wtf.core.curiosity.CuriosityCore;
import com.krjaken.wtf.core.memory.MemoryService;
import com.krjaken.wtf.interaces.WtfController;
import org.springframework.stereotype.Controller;

@Controller
public class Coma implements WtfController {
    private MemoryService memoryService;
    private CuriosityCore curiosityCore;
    public Coma(MemoryService memoryService, CuriosityCore curiosityCore){
        this.memoryService = memoryService;
        this.curiosityCore = curiosityCore;
    }

    public void startComa(){
        memoryService.down();
        curiosityCore.down();
    }

    public void outOfComa(){

    }
}
