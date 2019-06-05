package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;

public enum NotificationService {

    SLACK {
        boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack) {
            return groupConf.getBoolean("ext_plugin_slack_send_alert", pack.objType, false) &&
                groupConf.getInt("ext_plugin_slack_level", pack.objType, 0) <= pack.level;
        }
    };

    abstract boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack);
}
