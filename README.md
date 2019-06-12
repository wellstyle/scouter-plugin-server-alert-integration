# scouter-plugin-server-alert-integration

## Info

- Server Plugin and Built-in Plugin
- Scouter Alert messages are sent over variable notification services (slack, telegram, email, etc...)
- Configurable by monitoring group

## Configuration 

### Example 

``` properties
# <SCOUTER_HOME>/server/conf/scouter.conf

# Alert Common
ext_plugin_alert_debug=true

# Alert Slack
ext_plugin_slack_send_alert=true
ext_plugin_slack_debug=true
ext_plugin_slack_level=1
ext_plugin_slack_webhook_url=https://hooks.slack.com/services/T02XXXXX/B159XXXXX/W5CDXXXXXXXXXXXXXXXXXXXX
ext_plugin_slack_channel=#scouter
ext_plugin_slack_botName=scouter
ext_plugin_slack_icon_emoji=:computer:
ext_plugin_slack_icon_url=http://XXX.XXX.XXX/XXX.gif
ext_plugin_slack_xlog_enabled=true

## Alert Slack for Monitoring Group
app.ext_plugin_slack_channel=#wonder-auth
app-host.ext_plugin_slack_channel=#wonder-auth
```

## Dependencies

- Project
    - scouter.common
    - scouter.server
- Library
    - slack, telegram
        - commons-codec-1.9.jar
        - commons-logging-1.2.jar
        - gson-2.6.2.jar
        - httpclient-4.5.2.jar
        - httpcore-4.4.4.jar

## Build

- Build
    - mvn clean package
