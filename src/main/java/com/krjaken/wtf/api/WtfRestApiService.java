package com.krjaken.wtf.api;

import com.krjaken.wtf.core.curiosity.CuriosityCore;
import com.krjaken.wtf.core.life.LifeCycleService;
import com.krjaken.wtf.core.memory.MemoryService;
import com.krjaken.wtf.interaces.WtfService;
import org.springframework.stereotype.Service;

@Service
public class WtfRestApiService implements WtfService {
    private MemoryService memoryService;
    private CuriosityCore curiosityCore;
    private LifeCycleService lifeCycleService;

    public WtfRestApiService(MemoryService memoryService,
                             CuriosityCore curiosityCore,
                             LifeCycleService lifeCycleService){
        this.memoryService = memoryService;
        this.curiosityCore = curiosityCore;
        this.lifeCycleService = lifeCycleService;
    }

    @Override
    public void init() {
        //todo
    }

    @Override
    public void down() {
        //todo
    }
}
