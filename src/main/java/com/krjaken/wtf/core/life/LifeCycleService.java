package com.krjaken.wtf.core.life;

import com.krjaken.wtf.api.WtfRestApiService;
import com.krjaken.wtf.core.curiosity.CuriosityCore;
import com.krjaken.wtf.core.life.sleep.SleepingService;
import com.krjaken.wtf.core.life.wake.WakingService;
import com.krjaken.wtf.interaces.WtfService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LifeCycleService implements WtfService {
    private WakingService wakingService;
    private SleepingService sleepingService;
    private CuriosityCore curiosityCore;
    private WtfRestApiService wtfRestApiService;

    public LifeCycleService(WakingService wakingService,
                            SleepingService sleepingService,
                            CuriosityCore curiosityCore,
                            WtfRestApiService wtfRestApiService) {
        this.wakingService = wakingService;
        this.sleepingService = sleepingService;
        this.curiosityCore = curiosityCore;
        this.wtfRestApiService = wtfRestApiService;
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
