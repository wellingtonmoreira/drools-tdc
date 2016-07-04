package com.wmoreira.engine.config;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.builder.KieScanner;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;

public class KieBeans {
    @Bean
    public KieContainer kieContainer() {
        KieServices ks = KieServices.Factory.get();
        ReleaseId relId = ks.newReleaseId("br.com.wmoreira","rules","1.0-SNAPSHOT");
        KieContainer container = ks.newKieContainer(relId);
        KieScanner scanner = KieServices.Factory.get().newKieScanner(container);
        scanner.start(5000l);
        return container;
    }

    @Bean
    @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public KieSession kieSession(KieContainer container) {
        return container.newKieSession("Base_Session");
    }
}
