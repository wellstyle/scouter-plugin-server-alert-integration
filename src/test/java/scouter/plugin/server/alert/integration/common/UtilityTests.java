package scouter.plugin.server.alert.integration.common;

import org.junit.Test;
import scouter.lang.pack.AlertPack;

import static org.junit.Assert.assertEquals;

public class UtilityTests {

    @Test
    public void testGetValue() {
        AlertPack alertPack = new AlertPack();
        alertPack.message = "/wid-account-018c7d617462955c5/wid-account-018c7d617462955c5 is not running. OBJECT  objType=wid-account objHash=z1d6f0r6 objName=/wid-account-018c7d617462955c5/wid-account-018c7d617462955c5 addr=10.40.202.120 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";

        String objectName = Utility.getValue(alertPack.message, "objName");
        String objectType = Utility.getValue(alertPack.message, "objType");

        assertEquals("/wid-account-018c7d617462955c5/wid-account-018c7d617462955c5", objectName);
        assertEquals("wid-account", objectType);
    }

    @Test
    public void testGetValueException() {
        String objectName = Utility.getValue("message", "objName");
        String objectType = Utility.getValue(null, "objType");

        assertEquals("", objectName);
        assertEquals("", objectType);
    }

}
