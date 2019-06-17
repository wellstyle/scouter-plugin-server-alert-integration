package scouter.plugin.server.alert.integration.slack;

import com.google.gson.Gson;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.*;
import scouter.plugin.server.alert.integration.common.sender.http.DefaultHttpSender;

import java.io.IOException;

/**
 * Thread class that sends messages via slack
 *
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 * @author yj.seo on 2019. 6. 5.
 */
public class SlackTask implements Runnable {

    private final MonitoringGroupConfigure groupConf;
    private final AlertPack pack;
    private final AlertLogger logger = new AlertLogger();

    public SlackTask(MonitoringGroupConfigure groupConf, AlertPack alertPack) {
        this.groupConf = groupConf;
        this.pack = alertPack;
    }

    @Override
    public void run() {
        try {
            logger.println("Start thread.");

            String objType = AlertPackUtil.getObjType(pack);
            String objName = AlertPackUtil.getObjName(pack);

            String webhookURL = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_WEBHOOK_URL, objType);
            String channel = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_CHANNEL, objType);
            String botName = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_BOT_NAME, objType);
            String iconURL = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_ICON_URL, objType);
            String iconEmoji = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_ICON_EMOJI, objType);

            assert !StringUtil.isEmpty(webhookURL);
            assert !StringUtil.isEmpty(channel);

            Content content = new Content(pack, objType, objName);
            SlackMessage message = new SlackMessage(content.toString(), channel, botName, iconURL, iconEmoji);
            String payload = new Gson().toJson(message);

            DefaultHttpSender httpSender = new DefaultHttpSender();
            httpSender.post(webhookURL, payload);

            logger.println("End thread.");
        } catch (IOException e) {
            logger.println("[IOException] : " + e);
        }
    }

}
