package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.plugin.server.alert.integration.common.AlertLogger;
import scouter.plugin.server.alert.integration.slack.SlackTask;
import scouter.plugin.server.alert.integration.telegram.TelegramTask;
import scouter.server.Configure;

/**
 * AlertPlugin main class
 *
 * @author yj.seo on 2019. 6. 5.
 */
public class AlertPlugin {
    private final MonitoringGroupConfigure groupConf;
    private final AlertLogger logger = new AlertLogger();

    public AlertPlugin() {
        groupConf = new MonitoringGroupConfigure(Configure.getInstance());
    }

    @ServerPlugin(PluginConstants.PLUGIN_SERVER_ALERT)
    public void alert(final AlertPack pack) {
        logger.println("[alert] " + pack);

        // Slack
        if (AlertServices.SLACK.shouldRun(groupConf, pack)) {
            logger.println("[alert] Start SlackTask.");
            Thread slackThread = new Thread(new SlackTask(groupConf, pack), "SlackTask");
            slackThread.start();
        }

        // Telegram
        if (AlertServices.TELEGRAM.shouldRun(groupConf, pack)) {
            logger.println("[alert] Start TelegramTask.");
            Thread telegramThread = new Thread(new TelegramTask(groupConf, pack), "TelegramTask");
            telegramThread.start();
        }
    }

}
