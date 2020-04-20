package scouter.plugin.server.alert.integration.teams;

import com.google.gson.Gson;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.AlertLogger;
import scouter.plugin.server.alert.integration.common.AlertPackUtil;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.StringUtil;
import scouter.plugin.server.alert.integration.common.sender.http.DefaultHttpSender;

import java.io.IOException;

/**
 * Thread class that sends messages via teams
 *
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 * @author yj.seo on 2019. 6. 5.
 */
public class TeamsTask implements Runnable {

    static final String EXT_PLUGIN_ALERT_TEAMS_WEBHOOK_URL = "ext_plugin_alert_teams_webhook_url";

    private final MonitoringGroupConfigure groupConf;
    private final AlertPack pack;
    private final AlertLogger logger = new AlertLogger();

    public TeamsTask(MonitoringGroupConfigure groupConf, AlertPack alertPack) {
        this.groupConf = groupConf;
        this.pack = alertPack;
    }

    @Override
    public void run() {
        try {
            logger.println("Start thread.");

            String objType = AlertPackUtil.getObjType(pack);
            String objName = AlertPackUtil.getObjName(pack);

            String webhookURL = groupConf.getValue(EXT_PLUGIN_ALERT_TEAMS_WEBHOOK_URL, objType);

            assert !StringUtil.isEmpty(webhookURL);

            TeamsMessage message = new TeamsMessage(pack, objType, objName);
            String payload = new Gson().toJson(message);

            DefaultHttpSender httpSender = new DefaultHttpSender();
            httpSender.post(webhookURL, payload);

            logger.println("End thread.");
        } catch (IOException e) {
            logger.println("[IOException] : " + e);
        }
    }

}
