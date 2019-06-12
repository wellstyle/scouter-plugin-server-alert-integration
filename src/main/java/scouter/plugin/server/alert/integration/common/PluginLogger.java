package scouter.plugin.server.alert.integration.common;

import scouter.server.Logger;

/**
 * @author yj.seo on 2019. 06. 12.
 */
public class PluginLogger {

    private PluginLogger() {
        throw new IllegalStateException("static class");
    }

    public static void println(Object o) {
        String threadName = Thread.currentThread().getName();
        System.out.println("[" + threadName + "] " + o);
        Logger.println("[" + threadName + "] " + o);
    }
}
