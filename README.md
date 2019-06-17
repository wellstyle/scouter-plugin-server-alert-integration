# scouter-plugin-server-alert-integration

[![Build Status](https://travis-ci.com/wellstyle/scouter-plugin-server-alert-integration.svg?branch=master)](https://travis-ci.com/wellstyle/scouter-plugin-server-alert-integration)
[![Code Coverage](https://codecov.io/gh/wellstyle/scouter-plugin-server-alert-integration/branch/master/graph/badge.svg)](https://codecov.io/gh/wellstyle/scouter-plugin-server-alert-integration)

Scouter에서 발생하는 `Alert` 메시지를 `Telegram`과 `Slack`으로 전송하는 플러그인 입니다.

## Info

- 기존 [scouter-plugin-server-alert-telegram](https://github.com/scouter-project/scouter-plugin-server-alert-telegram), [scouter-plugin-server-alert-slack](https://github.com/scouter-project/scouter-plugin-server-alert-slack) 플러그인을 통합
- 다른 서비스들도 간단하게 추가하기 위한 구조로 변경
- `scouter-plugin-server-alert-slack`의 [monitoring_group_type 별 알림을 줄 수 있는 옵션](https://github.com/scouter-project/scouter-plugin-server-alert-slack/commit/2817d9bdfe250b6567450507a1043c82e3725742) 적용하여 모니터링 그룹에 따라 멀티 채널로 전송 가능

## Configuration 

### Example 

``` properties
# Alert Common
ext_plugin_alert_debug=true # default: true

# Alert Slack
ext_plugin_alert_slack_enable=true # default: false
ext_plugin_alert_slack_level=0 # default: 0
ext_plugin_alert_slack_webhook_url=https://hooks.slack.com/services/T02XXXXX/B159XXXXX/W5CDXXXXXXXXXXXXXXXXXXXX
ext_plugin_alert_slack_channel=#scouter
ext_plugin_alert_slack_bot_name=scouter
ext_plugin_alert_slack_icon_emoji=:computer:

## Alert Slack for Monitoring Group
group-1.ext_plugin_alert_slack_channel=#group-1
group-2.ext_plugin_alert_slack_channel=#group-2
group-2.ext_plugin_alert_slack_level=2 # monitoring_group_type=group-2 에 level=2 적용

# Alert Telegram
ext_plugin_alert_telegram_enable=true # default: false
ext_plugin_alert_telegram_level=0 # default: 0
ext_plugin_alert_telegram_bot_token=NNNNNNNNN:XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
ext_plugin_alert_telegram_chat_id=@ScouterDemoChannel # 공개 채널인 경우는 @채널명, 비공개 채널인 경우는 chatId

## Alert Telegram for Monitoring Group
group-1.ext_plugin_alert_telegram_chat_id=@ScouterDemoGroup1Channel
group-2.ext_plugin_alert_telegram_chat_id=@ScouterDemoGroup2Channel
```

## Dependencies

- Project
    - scouter.common
    - scouter.server
- Library
    - for slack, telegram
        - commons-codec-1.9.jar
        - commons-logging-1.2.jar
        - gson-2.6.2.jar
        - httpclient-4.5.2.jar
        - httpcore-4.4.4.jar

## Build

- Build
    - mvn clean package
