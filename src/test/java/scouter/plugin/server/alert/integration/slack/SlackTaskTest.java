package scouter.plugin.server.alert.integration.slack;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;
import scouter.plugin.server.alert.integration.common.Properties;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2019. 06. 10.
 */
public class SlackTaskTest {

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;
    private String objType = "app";

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = "scouter";
        alertPack.level = 0;
        alertPack.title = "An object has been inactivated.";
        alertPack.message = "/app-018c7d617462955c5/app-018c7d617462955c5 is not running. OBJECT  objType=" +
            objType +
            " objHash=z1d6f0r6 objName=/app-018c7d617462955c5/app-018c7d617462955c5 addr=127.0.0.1 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";

        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_CHANNEL, objType)).thenReturn("#app");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_BOT_NAME, objType)).thenReturn("scouter");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_ICON_URL, objType)).thenReturn("");
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_ICON_EMOJI, objType)).thenReturn(":computer:");
        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_WEBHOOK_URL, objType)).thenReturn("https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241");

        SlackTask slackTask = new SlackTask(groupConfigure, alertPack);
        slackTask.run();
    }

    @Test
    public void whenNotfoundUrl_ThenSlackRequestException() {
        when(groupConfigure.getValue(Properties.EXT_PLUGIN_ALERT_SLACK_WEBHOOK_URL, objType)).thenReturn("https://hooks.slack.com/services/team/service/token");

        SlackTask slackTask = new SlackTask(groupConfigure, alertPack);
        slackTask.run();
    }
}