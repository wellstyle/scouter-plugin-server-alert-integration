package scouter.plugin.server.alert.integration;

import org.junit.Before;
import org.junit.Test;
import scouter.lang.pack.AlertPack;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class NotificationServiceTests {

    private MonitoringGroupConfigure groupConfigure;
    private AlertPack alertPack;

    @Before
    public void setUp() {
        groupConfigure = mock(MonitoringGroupConfigure.class);
        alertPack = mock(AlertPack.class);
    }

    @Test
    public void givenSendAlertTrueAndAlertLevelEqualAndLessThan_whenShouldRun_thenTrue() {
        alertPack.objType = "scouter";
        alertPack.level = 2;
        when(groupConfigure.getBoolean("ext_plugin_slack_send_alert", alertPack.objType, false)).thenReturn(true);
        when(groupConfigure.getInt("ext_plugin_slack_level", alertPack.objType, 0)).thenReturn(2);

        assertTrue(NotificationService.SLACK.shouldRun(groupConfigure, alertPack));

        when(groupConfigure.getBoolean("ext_plugin_slack_send_alert", alertPack.objType, false)).thenReturn(true);
        when(groupConfigure.getInt("ext_plugin_slack_level", alertPack.objType, 0)).thenReturn(1);

        assertTrue(NotificationService.SLACK.shouldRun(groupConfigure, alertPack));
    }

    @Test
    public void givenSendAlertFalse_whenShouldRun_thenFalse() {
        alertPack.objType = "scouter";
        alertPack.level = 2;
        when(groupConfigure.getBoolean("ext_plugin_slack_send_alert", alertPack.objType, false)).thenReturn(false);
        when(groupConfigure.getInt("ext_plugin_slack_level", alertPack.objType, 0)).thenReturn(2);

        assertFalse(NotificationService.SLACK.shouldRun(groupConfigure, alertPack));
    }

    @Test
    public void givenAlertLevelGreaterThan_whenShouldRun_thenFalse() {
        alertPack.objType = "scouter";
        alertPack.level = 2;
        when(groupConfigure.getBoolean("ext_plugin_slack_send_alert", alertPack.objType, false)).thenReturn(false);
        when(groupConfigure.getInt("ext_plugin_slack_level", alertPack.objType, 0)).thenReturn(3);

        assertFalse(NotificationService.SLACK.shouldRun(groupConfigure, alertPack));
    }
    
}