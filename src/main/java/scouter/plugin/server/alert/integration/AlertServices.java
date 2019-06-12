package scouter.plugin.server.alert.integration;

import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.Properties;

public enum AlertServices {

    SLACK {
        boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack) {
            return groupConf.getBoolean(Properties.EXT_PLUGIN_ALERT_SLACK_ENABLE, pack.objType, false) &&
                groupConf.getInt(Properties.EXT_PLUGIN_ALERT_SLACK_LEVEL, pack.objType, 0) <= pack.level;
        }
    },
    TELEGRAM {
        boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack) {
            return groupConf.getBoolean(Properties.EXT_PLUGIN_ALERT_TELEGRAM_ENABLE, pack.objType, false) &&
                groupConf.getInt(Properties.EXT_PLUGIN_ALERT_TELEGRAM_LEVEL, pack.objType, 0) <= pack.level;
        }
    };

    abstract boolean shouldRun(MonitoringGroupConfigure groupConf, AlertPack pack);
}
