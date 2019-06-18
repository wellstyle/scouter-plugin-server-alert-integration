package scouter.plugin.server.alert.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import scouter.lang.pack.AlertPack;
import scouter.plugin.server.alert.integration.common.MonitoringGroupConfigure;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

/**
 * @author yj.seo on 2019. 06. 17.
 */
@RunWith(MockitoJUnitRunner.class)
public class MessagingAppTest {

    @Mock
    MonitoringGroupConfigure groupConfigure;
    @Mock
    AlertPack alertPack;

    @Before
    public void setUp() {
        alertPack.objType = "scouter";
        alertPack.level = 2;
    }

    @Test
    public void shouldRun_givenSendAlertTrueAndAlertLevelEqualAndLessThan_thenTrue() {
        for (MessagingApp app : MessagingApp.values()) {
            when(groupConfigure.getBoolean(app.enableProperty, alertPack.objType, false)).thenReturn(true);
            when(groupConfigure.getInt(app.levelProperty, alertPack.objType, 0)).thenReturn(2);

            assertTrue(app.shouldRun(groupConfigure, alertPack));

            when(groupConfigure.getBoolean(app.enableProperty, alertPack.objType, false)).thenReturn(true);
            when(groupConfigure.getInt(app.levelProperty, alertPack.objType, 0)).thenReturn(1);

            assertTrue(app.shouldRun(groupConfigure, alertPack));
        }
    }

    @Test
    public void shouldRun_givenSendAlertFalse_thenFalse() {
        for (MessagingApp app : MessagingApp.values()) {
            when(groupConfigure.getBoolean(app.enableProperty, alertPack.objType, false)).thenReturn(false);
            when(groupConfigure.getInt(app.levelProperty, alertPack.objType, 0)).thenReturn(2);

            assertFalse(app.shouldRun(groupConfigure, alertPack));
        }
    }

    @Test
    public void shouldRun_givenAlertLevelGreaterThan_thenFalse() {
        for (MessagingApp app : MessagingApp.values()) {
            when(groupConfigure.getBoolean(app.enableProperty, alertPack.objType, false)).thenReturn(true);
            when(groupConfigure.getInt(app.levelProperty, alertPack.objType, 0)).thenReturn(3);

            assertFalse(app.shouldRun(groupConfigure, alertPack));
        }
    }
}