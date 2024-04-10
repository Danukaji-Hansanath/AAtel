package org.danukaji.Bot.Film.Download;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class DownloadThread extends Thread {
    public void run() {
        TorrentDownloadBot torrentDownloadBot = new TorrentDownloadBot();
        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(torrentDownloadBot);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
