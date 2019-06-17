package scouter.plugin.server.alert.integration.common;

import scouter.lang.AlertLevel;
import scouter.lang.pack.AlertPack;
import scouter.server.Configure;

public class Content {

    private String type;
    private String name;
    private String level;
    private String title;
    private String message;
    private String scouterHostName;

    public Content(AlertPack pack, String objType, String objName) {
        this.type = objType;
        this.name = objName;
        this.level = AlertLevel.getName(pack.level);
        this.title = pack.title;
        this.message = pack.message;
        this.scouterHostName = Configure.getInstance().server_id;

    }

    public String toString() {
        return "[SCOUTER] : " + this.scouterHostName + "\n" +
            "[TYPE] : " + this.type + "\n" +
            "[NAME] : " + this.name + "\n" +
            "[LEVEL] : " + this.level + "\n" +
            "[TITLE] : " + this.title + "\n" +
            "[MESSAGE] : " + this.message;
    }
}
