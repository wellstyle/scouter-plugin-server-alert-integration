package scouter.plugin.server.alert.integration.common;

/**
 * @author yj.seo on 2019. 06. 11.
 */
public class Properties {

    private Properties() {
        throw new IllegalStateException("static class");
    }

    // Common
    public static final String EXT_PLUGIN_ALERT_DEBUG = "ext_plugin_alert_debug";

    // Slack
    public static final String EXT_PLUGIN_ALERT_SLACK_ENABLE = "ext_plugin_alert_slack_enable";
    public static final String EXT_PLUGIN_ALERT_SLACK_LEVEL = "ext_plugin_alert_slack_level";
    public static final String EXT_PLUGIN_ALERT_SLACK_WEBHOOK_URL = "ext_plugin_alert_slack_webhook_url";
    public static final String EXT_PLUGIN_ALERT_SLACK_CHANNEL = "ext_plugin_alert_slack_channel";
    public static final String EXT_PLUGIN_ALERT_SLACK_BOT_NAME = "ext_plugin_alert_slack_bot_name";
    public static final String EXT_PLUGIN_ALERT_SLACK_ICON_URL = "ext_plugin_alert_slack_icon_url";
    public static final String EXT_PLUGIN_ALERT_SLACK_ICON_EMOJI = "ext_plugin_alert_slack_icon_emoji";

    // Telegram
    public static final String EXT_PLUGIN_ALERT_TELEGRAM_ENABLE = "ext_plugin_alert_telegram_enable";
    public static final String EXT_PLUGIN_ALERT_TELEGRAM_LEVEL = "ext_plugin_alert_telegram_level";
    public static final String EXT_PLUGIN_ALERT_TELEGRAM_BOT_TOKEN = "ext_plugin_alert_telegram_bot_token";
    public static final String EXT_PLUGIN_ALERT_TELEGRAM_CHAT_ID = "ext_plugin_alert_telegram_chat_id";
}
