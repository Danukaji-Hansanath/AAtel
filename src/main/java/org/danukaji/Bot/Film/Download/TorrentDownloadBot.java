package org.danukaji.Bot.Film.Download;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.GetFile;
import org.telegram.telegrambots.meta.api.objects.Document;
import org.telegram.telegrambots.meta.api.objects.File;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.danukaji.Bot.Film.Torrent.DownloadTorrentFiles;
public class TorrentDownloadBot extends TelegramLongPollingBot {
    private static String torFile;
    @Override
    public String getBotUsername() {
        return "Download";
    }

    @Override
    public String getBotToken() {
        return "7152486316:AAHoSH5qDyuDYjz0n_JgG9r_QPtElac_4rU";
    }

    @Override
    public void onUpdateReceived(Update update) {
        var msg = update.getMessage();
        System.out.println(msg);
        if(msg != null && msg.hasDocument()){
            System.out.println("Has A File");
            Document document = update.getMessage().getDocument();
            String fileId = document.getFileId();
            try {
                GetFile getFileRequest = new GetFile();
                getFileRequest.setFileId(fileId);
                File file = execute(getFileRequest);

                if (file != null) {
                    downloadFile1(file.getFilePath(), document.getFileName());
                } else {
                    System.out.println("Failed to get file information.");
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void downloadFile1(String filePath,String fileName) throws TelegramApiException, IOException {
        String fileUrl = "https://api.telegram.org/file/bot" + getBotToken() + "/" + filePath;
        torFile = "src/main/java/org/danukaji/Downloads/Torrents/"+fileName;
        try (InputStream inputStream = new URL(fileUrl).openStream();
             FileOutputStream outputStream = new FileOutputStream(torFile)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            System.out.println("File downloaded successfully.");
            DownloadTorrentFiles downloadTorrentFiles = new DownloadTorrentFiles();
            downloadTorrentFiles.DownloadTorrent(torFile);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) throws TelegramApiException {
        TorrentDownloadBot torrentDownloadBot = new TorrentDownloadBot();
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(torrentDownloadBot);
    }
}