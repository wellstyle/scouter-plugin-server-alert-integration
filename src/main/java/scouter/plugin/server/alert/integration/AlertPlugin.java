package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.plugin.server.alert.integration.common.PluginLogger;
import scouter.plugin.server.alert.integration.common.Properties;
import scouter.plugin.server.alert.integration.slack.SlackTask;
import scouter.plugin.server.alert.integration.telegram.TelegramTask;
import scouter.server.Configure;

/**
 * AlertPlugin main class
 *
 * @author yj.seo on 2019. 6. 5.
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

        // Slack
        if (AlertServices.SLACK.shouldRun(groupConf, pack)) {
            println("[alert] Start SlackTask.");
            Thread slackThread = new Thread(new SlackTask(groupConf, pack), "SlackTask");
            slackThread.start();
        }

        // Telegram
        if (AlertServices.TELEGRAM.shouldRun(groupConf, pack)) {
            println("[alert] Start TelegramTask.");
            Thread telegramThread = new Thread(new TelegramTask(groupConf, pack), "TelegramTask");
            telegramThread.start();
        }
    }

    private void println(Object o) {
        if(conf.getBoolean(Properties.EXT_PLUGIN_ALERT_DEBUG, true)) { //default false normally, but true now for test purpose.
            PluginLogger.println(o);
        }
    }
}
