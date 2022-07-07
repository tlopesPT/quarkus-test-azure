package com.tlopes.azure;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public interface IMessageClientWrapper {
    // @PostConstruct unfornutally you cannot annotqate a method with @PostConstruct. You need to add it to the implementation
    void onStart();

    // Same @PreDestroy
    void onStop() throws InterruptedException;

    String sendMessage();
}
