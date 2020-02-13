package com.krjaken.wtf.core.life;

import com.krjaken.wtf.core.curiosity.CuriosityCore;
import com.krjaken.wtf.core.interaces.WtfService;
import com.krjaken.wtf.core.life.sleep.SleepingService;
import com.krjaken.wtf.core.life.wake.WakingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LifeCycleService implements WtfService {
    private WakingService wakingService;
    private SleepingService sleepingService;
    private CuriosityCore curiosityCore;

    public LifeCycleService(WakingService wakingService,
                            SleepingService sleepingService,
                            CuriosityCore curiosityCore) {
        this.wakingService = wakingService;
        this.sleepingService = sleepingService;
        this.curiosityCore = curiosityCore;
        init();
    }

    @Override
    public void init() {
        log.info("startLifeCycle");
        wakingService.wakeUp();
        curiosityCore.init();
    }

    @Override
    public void down() {
        log.info("endLifeCycle");
        sleepingService.sleep();
        sleepingService.coma();
    }

    public void death() {
        sleepingService.coma();
    }
}
