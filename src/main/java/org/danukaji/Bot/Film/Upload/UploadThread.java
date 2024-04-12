/*package org.danukaji.Bot.Film.Upload;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class UploadThread extends Thread {
    private static String directory;


    public String getDirectory() {
        return directory;
    }

    @Override
    public void run() {
        DefaultBotOptions options = new DefaultBotOptions();
        options.setBaseUrl("http://127.0.0.1:1300/bot");
        UploadBot bot = new UploadBot(options);
        TelegramBotsApi botsApi = null;
        try {
            botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }

    }
}*/
