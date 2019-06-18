package scouter.plugin.server.alert.integration;

import org.junit.BeforeClass;
import org.junit.Test;
import scouter.lang.AlertLevel;
import scouter.lang.pack.AlertPack;

/**
 * @author yj.seo on 2019. 06. 17.
 */
public class AlertPluginTest {

    @BeforeClass
    public static void setup() {
        System.setProperty("scouter.config", "./src/test/resources/scouter.conf");
    }

    @Test
    public void alert() {
        AlertPack pack = genAlertPack();

        AlertPlugin alertPlugin = new AlertPlugin();
        alertPlugin.alert(pack);
    }

    private AlertPack genAlertPack() {
        AlertPack ap = new AlertPack();

        ap.level = AlertLevel.WARN;
        ap.objHash = 100;
        ap.title = "Elapsed time exceed a threshold.";
        ap.message = "[100 agent] exceed a threshold";
        ap.time = System.currentTimeMillis();
        ap.objType = "someObjectType";

        return ap;
    }

}
