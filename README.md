# scouter-plugin-server-alert-integration

[![Build Status](https://travis-ci.com/wellstyle/scouter-plugin-server-alert-integration.svg?branch=master)](https://travis-ci.com/wellstyle/scouter-plugin-server-alert-integration)
[![Code Coverage](https://codecov.io/gh/wellstyle/scouter-plugin-server-alert-integration/branch/master/graph/badge.svg)](https://codecov.io/gh/wellstyle/scouter-plugin-server-alert-integration)

## Scouter server plugin send a alert to telegram, slack and teams

- This plug-in sends alert messages generated from the server to the `telegram` messenger specific channel.
- This plug-in sends alert messages generated from the server to the `slack` workspace specific channel.
- Each monitoring group(`monitoring_group_type` or `obj_type`) can send to different channels.
- Currently supported types of Alert are as follows
    - All alert occurred from agents.
	  - on exceeding CPU threshold of Host agent(warning / fatal)
	  - on exceeding Memory threshold of Host agent (warning / fatal)
	  - on exceeding Disk usage threshold of Host agent (warning / fatal)
	  - agent's connection
	  - agent's disconnection
	  - agent's reconnection
	  - else all alert

## Properties (conf/scouter.conf)

App | name | desc | default 
--- | --- | --- | ---
Common | `ext_plugin_alert_debug` | debug logging option | true
Slack | `ext_plugin_alert_slack_enable` | use alert to a slack workspace or not (true / false) | false
Slack | `ext_plugin_alert_slack_level` | alert level to send (0: INFO, 1: WARN, 2: ERROR, 3: FATAL) | 0
Slack | `ext_plugin_alert_slack_webhook_url` | slack webhook url
Slack | `ext_plugin_alert_slack_channel` | slack channel name
Slack | `ext_plugin_alert_slack_bot_name` | slack bot name 
Slack | `ext_plugin_alert_slack_icon_url` | slack icon url 
Slack | `ext_plugin_alert_slack_icon_emoji` | slack icon emoji
Slack | `<objectType>.ext_plugin_alert_slack_level` | alert level to send of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_slack_level`
Slack | `<objectType>.ext_plugin_alert_slack_channel` | slack channel name of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_slack_channel`
Telegram | `ext_plugin_alert_telegram_enable` | use alert to a telegram messenger or not (true / false) | false
Telegram | `ext_plugin_alert_telegram_level` | alert level to send (0: INFO, 1: WARN, 2: ERROR, 3: FATAL) | 0
Telegram | `ext_plugin_alert_telegram_bot_token` | telegram bot token
Telegram | `ext_plugin_alert_telegram_chat_id` | telegram chat-room id
Telegram | `<objectType>.ext_plugin_alert_telegram_level` | alert level to send of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_telegram_level`
Telegram | `<objectType>.ext_plugin_alert_telegram_chat_id` | slack telegram chat-room id of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_telegram_chat_id`
Teams | `ext_plugin_alert_teams_enable` | use alert to a teams or not (true / false) | false
Teams | `ext_plugin_alert_teams_webhook_url` | teams webhook url
Teams | `ext_plugin_alert_teams_level` | alert level to send (0: INFO, 1: WARN, 2: ERROR, 3: FATAL) | 0
Teams | `<objectType>.ext_plugin_alert_teams_webhook_url` | teams webhook url of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_teams_webhook_url`
Teams | `<objectType>.ext_plugin_alert_teams_level` | alert level to send of `monitoring_group_type` or `obj_type` | `ext_plugin_alert_teams_level`

### Example

``` properties
# Alert Common
ext_plugin_alert_debug=true

# Alert Slack
ext_plugin_alert_slack_enable=true
ext_plugin_alert_slack_level=0
ext_plugin_alert_slack_webhook_url=https://hooks.slack.com/services/T02XXXXX/B159XXXXX/W5CDXXXXXXXXXXXXXXXXXXXX
ext_plugin_alert_slack_channel=#scouter
ext_plugin_alert_slack_bot_name=scouter
ext_plugin_alert_slack_icon_emoji=:computer:

## Alert Slack for Monitoring Group
group-1.ext_plugin_alert_slack_channel=#group-1
group-2.ext_plugin_alert_slack_channel=#group-2
group-2.ext_plugin_alert_slack_level=2

# Alert Telegram
ext_plugin_alert_telegram_enable=true
ext_plugin_alert_telegram_level=0
ext_plugin_alert_telegram_bot_token=NNNNNNNNN:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
ext_plugin_alert_telegram_chat_id=@ScouterDemoChannel

## Alert Telegram for Monitoring Group
group-1.ext_plugin_alert_telegram_chat_id=@ScouterDemoGroup1Channel
group-2.ext_plugin_alert_telegram_chat_id=@ScouterDemoGroup2Channel

# Alert Teams
ext_plugin_alert_teams_enable=true
ext_plugin_alert_teams_level=0
ext_plugin_alert_teams_webhook_url=https://outlook.office365.com/webhook/value1@value2/IncomingWebhook/value3/value4

## Alert Teams for Monitoring Group
group-1.ext_plugin_alert_teams_webhook_url=https://outlook.office365.com/webhook/value1@value2/IncomingWebhook/value3/value4
group-2.ext_plugin_alert_teams_webhook_url=https://outlook.office365.com/webhook/value1@value2/IncomingWebhook/value3/value4
group-2.ext_plugin_alert_teams_level=1
```

## Dependencies

- Project
    - scouter.common
    - scouter.server
- Library
    - commons-codec-1.9.jar
    - commons-logging-1.2.jar
    - gson-2.6.2.jar
    - httpclient-4.5.2.jar
    - httpcore-4.4.4.jar

## Build & Deploy

- Build
    - mvn clean package
- Deploy
    - copy scouter-plugin-server-alert-integration-xxx.jar and all dependent libraries(exclude scouter.server and scouter.common) to lib directory of scouter server home.

## Appendix

- integration [scouter-plugin-server-alert-telegram](https://github.com/scouter-contrib/scouter-plugin-server-alert-telegram), [scouter-plugin-server-alert-slack](https://github.com/scouter-contrib/scouter-plugin-server-alert-slack)
- [telegram reference](https://github.com/scouter-contrib/scouter-plugin-server-alert-telegram#appendix)

