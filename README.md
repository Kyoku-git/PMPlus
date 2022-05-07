## Information
PMPlus is an advanced messaging plugin that aims to be fully customizable and user-friendly.
### Support
The following links can help you with any problems. If you cannot find an answer in our docs, join our Discord server to request support. If you encounter a bug or want to suggest something, use our feedback page.
- [Documentation](https://docs.crowned.dev/plugins/pm+)
- [Discord Server](https://discord.crowned.dev)
- [Feedback Page](https://feedback.crowned.dev)

## Features
### Current Features
- Messaging: Privately message other players
- Replies: Reply to a previous message with a short command
- Toggle: Toggle whether you can reiceve messages from other players. _Can be bypassed with the `pmplus.bypass` permission._
- SocialSpy: Spy on other players' conversations to enforce rules. Or just spy on people for fun if that's your thing.
- PlaceholderAPI: Use any placeholder you want within the messages of PM+. _Note: Placeholders cannot be assigned to more than one player per message_

### Planned Features
- Ignoring: Allows players to block other players from messaging them while still having their messages open
- Sounds: Plays a sound when a player recieves a message. This will be toggleable and configurable in the config.
- I am looking to add many more features. If you have a suggestion, post it on our [feedback page](https://feedback.crowned.dev)!

## Commands and Permissions
- `msg` [pmplus.msg (default)] - message another player (message, tell, pm, whisper)
- `reply` [pmplus.reply (default)] - reply to a previous message (r)
- `togglepms` [pmplus.toggle (default)] - toggle whether you can recieve messages (togglepm, togglemsgs)
- `socialspy` [pmplus.socialspy] - toggling seeing other players' conversations (spy)

## Configuration
```
# ██████╗ ███╗   ███╗██████╗ ██╗     ██╗   ██╗ ██████╗
# ██╔══██╗████╗ ████║██╔══██╗██║     ██║   ██║██╔════╝
# ██████╔╝██╔████╔██║██████╔╝██║     ██║   ██║╚█████╗
# ██╔═══╝ ██║╚██╔╝██║██╔═══╝ ██║     ██║   ██║ ╚═══██╗
# ██║     ██║ ╚═╝ ██║██║     ███████╗╚██████╔╝██████╔╝
# ╚═╝     ╚═╝     ╚═╝╚═╝     ╚══════╝ ╚═════╝ ╚═════╝
# Plugin by Kyoku


Options:
  AllowSelfMessage: false
  # more options will come in future updates

Messages:
  MessageToSender: "&e(To &6%recipient%&e) &f%message%"
  MessageToRecipient: "&e(From &6%sender%&e) &f%message%"
  NoReplyTarget: "&cNo one has messaged you."
  RecipientMessagesOff: "&c%recipient% currently has their messages off."
  MessagesToggledOn: "&aYou will now receive messages."
  MessagesToggledOff: "&cYou will no longer receive messages."
  SocialSpyEnabled: "&aYou have enabled social spy."
  SocialSpyDisabled: "&cYou have disabled social spy."
  SocialSpyFormat: "&e[SPY] (&6%sender% &eto &6%recipient%&e) &f%message%"
  MessageCommandUsage: "&cInvalid arguments: &7/msg <player> <message>"
  ReplyCommandUsage: "&cInvalid arguments: &7/r <message>"
  NoPermission: "&cYou do not have permission to use that command."
  PlayerNotFound: "&cThe player %recipient% could not be found."
  CannotMessageSelf: "&cYou cannot message yourself!"
  ```
