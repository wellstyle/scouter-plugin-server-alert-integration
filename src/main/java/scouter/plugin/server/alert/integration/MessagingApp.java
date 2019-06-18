package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.slack.SlackTask;
import scouter.plugin.server.alert.integration.telegram.TelegramTask;

/**
 * @author yj.seo on 2019. 06. 17.
 */
public enum MessagingApp {

    SLACK("SlackTask", "ext_plugin_alert_slack_enable", "ext_plugin_alert_slack_level") {
        public Thread newThread(MonitoringGroupConfigure groupConf, AlertPack pack) {
            return new Thread(new SlackTask(groupConf, pack), this.threadName);
        }
    },
    TELEGRAM("TelegramTask", "ext_plugin_alert_telegram_enable", "ext_plugin_alert_telegram_level") {
        public Thread newThread(MonitoringGroupConfigure groupConf, AlertPack pack) {
            return new Thread(new TelegramTask(groupConf, pack), this.threadName);
        }
    };

    final String threadName;
    final String enableProperty;
    final String levelProperty;

    MessagingApp(String threadName, String enableProperty, String levelProperty) {
        this.threadName = threadName;
        this.enableProperty = enableProperty;
        this.levelProperty = levelProperty;
    }

    public boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack) {
        return groupConf.getBoolean(this.enableProperty, pack.objType, false) &&
            groupConf.getInt(this.levelProperty, pack.objType, 0) <= pack.level;
    }

    abstract Thread newThread(MonitoringGroupConfigure groupConf, AlertPack pack);
}
