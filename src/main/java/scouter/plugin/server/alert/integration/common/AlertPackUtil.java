package scouter.plugin.server.alert.integration.common;

import scouter.lang.pack.AlertPack;
import scouter.server.core.AgentManager;

/**
 * @author yj.seo on 2019. 06. 12.
 */
public class AlertPackUtil {

    private AlertPackUtil() {
        throw new IllegalStateException("utility class");
    }

    public static String getObjType(AlertPack pack) {
        if (pack.objType.equals("scouter")) {
            String objType = StringUtil.getValue(pack.message, "objType");
            return objType.isEmpty() ? pack.objType : objType;
        } else {
            return pack.objType;
        }

    }

    public static String getObjName(AlertPack pack) {
        if (pack.objType.equals("scouter")) {
            String objName = StringUtil.getValue(pack.message, "objName");
            return objName.isEmpty() ? AgentManager.getAgentName(pack.objHash) : objName;
        } else {
            return AgentManager.getAgentName(pack.objHash);
        }
    }
}
