package scouter.plugin.server.alert.integration.telegram;

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
 * @author yj.seo on 2019. 6. 5.
 */
public class TelegramTask implements Runnable {

    private static final String URL_TEMPLATE = "https://api.telegram.org/bot<TOKEN>/sendMessage";
    private final MonitoringGroupConfigure groupConf;
    private final AlertPack pack;
    private String objType = "undefined";

    public TelegramTask(MonitoringGroupConfigure groupConf, AlertPack alertPack) {
        this.groupConf = groupConf;
        this.pack = alertPack;
    }

    @Override
    public void run() {
        try {
            objType = AlertPackUtil.getObjType(pack);
            String objName = AlertPackUtil.getObjName(pack);

            // Get server configurations for telegram
            String token = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN, objType);
            String chatId = groupConf.getValue(Properties.EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID, objType);

            assert !StringUtil.isEmpty(token);
            assert !StringUtil.isEmpty(chatId);

            // Make a request URL using telegram bot api
            String url = URL_TEMPLATE.replace("<TOKEN>", token);
            Content content = new Content(pack, objType, objName);
            TelegramMessage message = new TelegramMessage(chatId, content.toString());
            String payload = new Gson().toJson(message);

            // send the post request
            HttpResponse response = RestClient.post(url, payload);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                println("Message sent to [" + chatId + "] successfully.");
            } else {
                println("Message sent to [" + chatId + "] failed. Verify below information.");
                println("[URL] : " + url);
                println("[Payload] : " + payload);
                println("[Response Status] : " + response.getStatusLine());
                println("[Response Body] : " + EntityUtils.toString(response.getEntity(), "UTF-8"));
            }
        } catch (IOException e) {
            println("[IOException] : " + e);
        }
    }

    private void println(Object o) {
        if (groupConf.getBoolean(Properties.EXT_PLUGIN_ALERT_TELEGRAM_DEBUG, objType, true)) { //default false normally, but true now for test purpose.
            PluginLogger.println(o);
        }
    }

}
