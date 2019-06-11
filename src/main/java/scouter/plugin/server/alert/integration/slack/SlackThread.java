package scouter.plugin.server.alert.integration.slack;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.Content;
import scouter.plugin.server.alert.integration.common.Utility;
import scouter.server.Logger;
import scouter.server.core.AgentManager;

import java.io.IOException;

/**
 * Thread class that sends messages via slack
 *
 * @author Se-Wang Lee(ssamzie101@gmail.com) on 2016. 5. 2.
 * @author yj.seo(wellstyle@gmail.com) on 2019. 6. 5.
 */
public class SlackThread extends Thread {

    private final MonitoringGroupConfigure groupConf;
    private final AlertPack pack;
    private String objType = "undefined";

    public SlackThread(MonitoringGroupConfigure groupConf, AlertPack alertPack) {
        if (groupConf == null || alertPack == null) {
            throw new IllegalArgumentException("groupConf or alertPack is null");
        }

        this.groupConf = groupConf;
        this.pack = alertPack;
    }

    @Override
    public void run() {
        try {
            objType = pack.objType;
            String objName = AgentManager.getAgentName(pack.objHash);

            if (pack.objType.equals("scouter")) {
                objType = Utility.getValue(pack.message, "objType");
                objName = Utility.getValue(pack.message, "objName");
            }

            Content content = new Content(pack, objType, objName);

            String webhookURL = groupConf.getValue("ext_plugin_slack_webhook_url", objType);
            String channel = groupConf.getValue("ext_plugin_slack_channel", objType);
            String botName = groupConf.getValue("ext_plugin_slack_botName", objType);
            String iconURL = groupConf.getValue("ext_plugin_slack_icon_url", objType);
            String iconEmoji = groupConf.getValue("ext_plugin_slack_icon_emoji", objType);

            assert webhookURL != null;

            SlackMessage message = new SlackMessage(content.toString(), channel, botName, iconURL, iconEmoji);
            String payload = new Gson().toJson(message);

            HttpPost post = new HttpPost(webhookURL);
            post.addHeader("Content-Type", "application/json");
            // charset set utf-8
            post.setEntity(new StringEntity(payload, "utf-8"));

            CloseableHttpClient client = HttpClientBuilder.create().build();

            // send the post request
            HttpResponse response = client.execute(post);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                println("Slack message sent to [" + channel + "] successfully.");
            } else {
                println("Slack message sent failed. Verify below information.");
                println("[webhookURL] : " + webhookURL);
                println("[request payload] : " + payload);
                println("[response status] : " + response.getStatusLine());
                println("[response body] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
                throw new SlackRequestException("Slack message sent failed.");
            }
        } catch (IOException e) {
            println("[IOException] : " + e);
        }
    }

    private void println(Object o) {
        if (groupConf.getBoolean("ext_plugin_slack_debug", objType, true)) { //default false normally, but true now for test purpose.
            System.out.println(o);
            Logger.println(o);
        }
    }

}
