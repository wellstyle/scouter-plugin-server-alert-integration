package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.plugin.server.alert.integration.slack.SlackThread;
import scouter.server.Configure;
import scouter.server.Logger;

/**
 * @author yj.seo (wellstyle@gmail.com) on 2019. 6. 5.
 */
public class AlertPlugin {
    private final Configure conf = Configure.getInstance();
    private final MonitoringGroupConfigure groupConf;

    public AlertPlugin() {
        groupConf = new MonitoringGroupConfigure(conf);
    }

    @ServerPlugin(PluginConstants.PLUGIN_SERVER_ALERT)
    public void alert(final AlertPack pack) {
        println("[alert] " + pack);

        if (NotificationService.SLACK.shouldRun(groupConf, pack)) {
            println("[slack] " + pack);
            SlackThread slackThread = new SlackThread(groupConf, pack);
            slackThread.setUncaughtExceptionHandler(new ThreadExceptionHandler("[slack]"));
            slackThread.start();
        }
    }

    private void println(Object o) {
        if(conf.getBoolean("ext_plugin_alert_debug", true)) { //default false normally, but true now for test purpose.
            System.out.println(o);
            Logger.println(o);
        }
    }
}
