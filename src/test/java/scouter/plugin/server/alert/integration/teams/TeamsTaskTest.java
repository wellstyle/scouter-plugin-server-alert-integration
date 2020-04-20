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

    private static final String OBJ_TYPE = "search_plus_ad_host";
    private static final byte LEVEL = 0x1;
    private static final String TITLE= "WARNING_CPU_HIGH";
    private static final String MESSAGE= "cpu high 98.698616%";
    private static final String WEBHOOK_URL = "https://webhook.site/ca5fab1b-e5f9-452d-9a38-d5b7f9d7b241";

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);

        alertPack.objType = OBJ_TYPE;
        alertPack.level = LEVEL;
        alertPack.title = TITLE;
        alertPack.message = MESSAGE;

        when(groupConfigure.isTrace()).thenReturn(true);
    }

    @Test
    public void run() {
        when(groupConfigure.getValue(TeamsTask.EXT_PLUGIN_ALERT_TEAMS_WEBHOOK_URL, OBJ_TYPE)).thenReturn(WEBHOOK_URL);

        TeamsTask teamsTask = new TeamsTask(groupConfigure, alertPack);
        teamsTask.run();
    }

}