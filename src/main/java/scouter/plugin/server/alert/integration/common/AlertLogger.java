package scouter.plugin.server.alert.integration.common;

import scouter.server.Configure;
import scouter.server.Logger;

/**
 * @author yj.seo on 2019. 06. 17.
 */
public class AlertLogger {

    private static final String EXT_PLUGIN_ALERT_DEBUG = "ext_plugin_alert_debug";
    private static final String LOG_PREFIX = "[ext_plugin_alert] ";

    private boolean debug;

    public AlertLogger() {
        Configure conf = Configure.getInstance();
        this.debug = conf.getBoolean(EXT_PLUGIN_ALERT_DEBUG, false);
    }

    public void println(String message) {
        String threadName = "[" + Thread.currentThread().getName() + "] ";

        if (debug) {
            Logger.println(LOG_PREFIX + threadName + message);
        }
    }
}
