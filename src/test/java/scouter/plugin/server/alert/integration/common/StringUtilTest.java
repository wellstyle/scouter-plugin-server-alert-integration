package scouter.plugin.server.alert.integration.common;

import org.junit.Test;
import scouter.lang.pack.AlertPack;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {

    @Test
    public void testGetValue() {
        AlertPack alertPack = new AlertPack();
        alertPack.message = "/app-018c7d617462955c5/app-018c7d617462955c5 is not running. OBJECT  objType=app objHash=z1d6f0r6 objName=/app-018c7d617462955c5/app-018c7d617462955c5 addr=10.40.202.120 2.5.1 2018-12-16 08:55 GMT_ENV_java8plus 2019-06-05 02:50:08.02 {counter=0,detected=tomcat,ADC=false}";

        String objectName = StringUtil.getValue(alertPack.message, "objName");
        String objectType = StringUtil.getValue(alertPack.message, "objType");

        assertEquals("/app-018c7d617462955c5/app-018c7d617462955c5", objectName);
        assertEquals("app", objectType);
    }

    @Test
    public void testGetValueException() {
        String objectName = StringUtil.getValue("message", "objName");
        String objectType = StringUtil.getValue(null, "objType");

        assertEquals("", objectName);
        assertEquals("", objectType);
    }

}
