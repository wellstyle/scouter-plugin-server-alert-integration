package scouter.plugin.server.alert.integration;

import scouter.lang.AlertLevel;
import scouter.lang.TextTypes;
import scouter.lang.pack.AlertPack;
import scouter.lang.pack.XLogPack;
import scouter.lang.plugin.PluginConstants;
import scouter.lang.plugin.annotation.ServerPlugin;
import scouter.plugin.server.alert.integration.common.AlertLogger;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;
import scouter.server.Configure;
import scouter.server.core.AgentManager;
import scouter.server.db.TextRD;
import scouter.util.DateUtil;
import scouter.util.Hexa32;

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

        for (MessagingApp app : MessagingApp.values()) {
            if (app.shouldRun(groupConf, pack)) {
                logger.println("[alert] Start " + app.threadName);
                app.newThread(groupConf, pack).start();
            }
        }
    }

    @ServerPlugin(PluginConstants.PLUGIN_SERVER_XLOG)
    public void xlog(XLogPack pack) {
        String objType = AgentManager.getAgent(pack.objHash).objType;

        if (Boolean.FALSE.equals(groupConf.getBoolean("ext_plugin_alert_xlog_enabled", objType, false))) {
            return;
        }

        // XLog Error
        if (pack.error != 0) {
            String date = DateUtil.yyyymmdd(pack.endTime);
            String service = TextRD.getString(date, TextTypes.SERVICE, pack.service);
            String error = TextRD.getString(date, TextTypes.ERROR, pack.error);
            String txId = Hexa32.toString32(pack.txid);
            String gxId = Hexa32.toString32(pack.gxid);

            AlertPack ap = new AlertPack();
            ap.level = AlertLevel.ERROR;
            ap.objHash = pack.objHash;
            ap.title = "XLog Error";
            ap.message = String.format("%s --- service: %s, txid: %s, gxid: %s", error, service, txId, gxId);
            ap.objType = objType;
            alert(ap);
        }

    }

}
