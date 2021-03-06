# Java-AIML-Bot

[![License: CC BY-NC-ND 4.0](https://img.shields.io/badge/License-CC%20BY--NC--ND%204.0-lightgrey.svg)](https://creativecommons.org/licenses/by-nc-nd/4.0/)


## DEVELOPMENT STOPPED ON THIS. IT WAS MEANT TO BE USED AS A COLLEGE PROJECT. YOU CAN STILL CHAT WITH THIS BOT [HERE](t.me/baalajimaestro_bot)


First you need to get the library and add it to your project. There are few possibilities for this:

If you use Maven, Gradle, etc; you should be able to import the dependency directly from Maven Central Repository. For example:

With Maven:

   <dependency>
      <groupId>org.telegram</groupId>
      <artifactId>telegrambots</artifactId>
      <version>3.6.1</version>
   </dependency>
With Gradle:

  compile group: 'org.telegram', name: 'telegrambots', version: '3.5'
Don't like Maven Central Repository? It can also be taken from Jitpack.

Import the library .jar direclty to your project. You can find it here, don't forget to take last version, it usually is a good idea. Depending on the IDE you are using, the process to add a library is different, here is a video that may help with Intellij or Eclipse

Build our first bot

Now that we have the library, we can start coding. There are few steps to follow, in this tutorial (for the sake of simplicity), we are going to build a Long Polling Bot:

Create your actual bot: The class must extends TelegramLongPollingBot and implement necessary methods:

public class MyAmazingBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        // TODO
    }

    @Override
    public String getBotUsername() {
        // TODO
        return null;
    }

    @Override
    public String getBotToken() {
        // TODO
        return null;
    }
}
getBotUsername(): This method must always return your Bot username. May look like:

    @Override
    public String getBotUsername() {
        return "myamazingbot";
    }
getBotToken(): This method must always return your Bot Token (If you don't know it, you may want to talk with @BotFather). May look like:

    @Override
    public String getBotToken() {
        return "123456789:qwertyuioplkjhgfdsazxcvbnm";
    }
onUpdateReceived: This method will be called when an Update is received by your bot. In this example, this method will just read messages and echo the same text:

@Override
public void onUpdateReceived(Update update) {
    // We check if the update has a message and the message has text
    if (update.hasMessage() && update.getMessage().hasText()) {
        SendMessage message = new SendMessage() // Create a SendMessage object with mandatory fields
                .setChatId(update.getMessage().getChatId())
                .setText(update.getMessage().getText());
        try {
            execute(message); // Call method to send the message
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
Instantiate TelegramBotsApi and register our new bot: For this part, we need to actually perform 3 steps: Initialize Api Context, Instantiate Telegram Api and Register our Bot. In this tutorial, we are going to make it in our main method:

public class Main {
    public static void main(String[] args) {

        // TODO Initialize Api Context

        // TODO Instantiate Telegram Bots API

        // TODO Register our bot
    }
}
Initialize Api Context: This can be easily done calling the only method present in ApiContextInitializer:

public class Main {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        // TODO Instantiate Telegram Bots API

        // TODO Register our bot
    }
}
Instantiate Telegram Bots API: Easy as well, just create a new instance. Remember that a single instance can handle different bots but each bot can run only once (Telegram doesn't support concurrent calls to GetUpdates):

public class Main {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        // TODO Register our bot
    }
}
Register our bot: Now we need to register a new instance of our previously created bot class in the api:

public class Main {
    public static void main(String[] args) {

        ApiContextInitializer.init();

        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new MyAmazingBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
Play with your bot: Done, now you just need to run this main method and your Bot should start working.
