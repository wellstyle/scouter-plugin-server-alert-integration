package scouter.plugin.server.alert.integration.slack;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.*;

import java.io.IOException;

/**
 * Thread class that sends messages via slack
 *
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 * @author yj.seo(wellstyle@gmail.com) on 2019. 6. 5.
 */
public class SlackTask implements Runnable {

    private final MonitoringGroupConfigure groupConf;
    private final AlertPack pack;
    private String objType = "undefined";

    public SlackTask(MonitoringGroupConfigure groupConf, AlertPack alertPack) {
        this.groupConf = groupConf;
        this.pack = alertPack;
    }

    @Override
    public void run() {
        try {
            objType = AlertPackUtil.getObjType(pack);
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

            HttpResponse response = RestClient.post(webhookURL, payload);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                println("Message sent to [" + channel + "] successfully.");
            } else {
                println("Message sent to [" + channel + "] failed. Verify below information.");
                println("[URL] : " + webhookURL);
                println("[Payload] : " + payload);
                println("[Response Status] : " + response.getStatusLine());
                println("[Response Body] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
        } catch (IOException e) {
            println("[IOException] : " + e);
        }
    }

    private void println(Object o) {
        if (groupConf.getBoolean(Properties.EXT_PLUGIN_ALERT_SLACK_DEBUG, objType, true)) { //default false normally, but true now for test purpose.
            PluginLogger.println(o);
        }
    }

}
