package scouter.plugin.server.alert.integration.slack;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.MonitoringGroupConfigure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2019. 06. 10.
 */
public class SlackThreadTests {

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;
    private String objType = "app";

    @Before
    public void setUp() throws Exception {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = "scouter";
        alertPack.level = 0;
        alertPack.title = "An object has been inactivated.";
        alertPack.message = "/app-018c7d617462955c5/app-018c7d617462955c5 is not running. OBJECT  objType=" +
            objType +
            " objHash=z1d6f0r6 objName=/app-018c7d617462955c5/app-018c7d617462955c5 addr=10.40.202.120 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";

        when(groupConfigure.getValue("ext_plugin_slack_channel", objType)).thenReturn("#app");
        when(groupConfigure.getValue("ext_plugin_slack_botName", objType)).thenReturn("scouter");
        when(groupConfigure.getValue("ext_plugin_slack_icon_url", objType)).thenReturn("");
        when(groupConfigure.getValue("ext_plugin_slack_icon_emoji", objType)).thenReturn(":computer:");
        when(groupConfigure.getBoolean("ext_plugin_slack_debug", objType, true)).thenReturn(true);
        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue("ext_plugin_slack_webhook_url", objType)).thenReturn("https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241");

        SlackThread slackThread = new SlackThread(groupConfigure, alertPack);
        slackThread.run();
    }

    @Test(expected = SlackRequestException.class)
    public void whenNotfoundUrl_ThenSlackRequestException() {
        when(groupConfigure.getValue("ext_plugin_slack_webhook_url", objType)).thenReturn("https://hooks.slack.com/services/team/service/token");

        SlackThread slackThread = new SlackThread(groupConfigure, alertPack);
        slackThread.run();
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenNullArgument_ThenIllegalArgumentException() {
        SlackThread slackThread = new SlackThread(groupConfigure, null);
        slackThread.run();
    }
}