package scouter.plugin.server.alert.integration.common;

import scouter.server.Configure;
import scouter.server.Logger;

/**
 * @author yj.seo on 2019. 06. 17.
 */
public class AlertLogger {

    private boolean debug;

    public AlertLogger() {
        Configure conf = Configure.getInstance();
        this.debug = conf.getBoolean(Properties.EXT_PLUGIN_ALERT_DEBUG, true);
    }

    public void println(String message) {
        String threadName = "[" + Thread.currentThread().getName() + "] ";
        if (debug) {
            Logger.println(threadName + message);
            System.out.println(threadName + message);
        }
    }
}
