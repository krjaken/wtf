package com.krjaken.wtf.core.life.wake;

import com.krjaken.wtf.core.interaces.WtfService;
import com.krjaken.wtf.core.memory.MemoryService;
import org.springframework.stereotype.Service;

@Service
public class WakingService implements WtfService {
    private MemoryService memoryService;

    public WakingService(MemoryService memoryService){
        this.memoryService = memoryService;
    }

    public void wakeUp() {
        memoryService.init();
    }

    @Override
    public void init() {

    }

    @Override
    public void down() {

    }
}
