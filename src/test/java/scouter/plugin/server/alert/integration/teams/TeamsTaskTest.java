package scouter.plugin.server.alert.integration.teams;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2020. 04. 20.
 */
public class TeamsTaskTest {

    private static final String MESSAGE_TEMPLATE = "app01 is not running. OBJECT  objType=tomcat objHash=z1d6f0r6 objName=app01 addr=127.0.0.1";

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;
    private String objType = "tomcat";

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = "scouter";
        alertPack.level = 0x1;
        alertPack.title = "An object has been inactivated.";
        alertPack.message = MESSAGE_TEMPLATE;

        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue(TeamsTask.EXT_PLUGIN_ALERT_TEAMS_WEBHOOK_URL, objType)).thenReturn("https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241");

        TeamsTask telegramTask = new TeamsTask(groupConfigure, alertPack);
        telegramTask.run();
    }

}