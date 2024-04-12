package org.danukaji.Bot.Film.Upload;

import org.danukaji.Bot.Utilities.Utiles;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class UploadBot extends TelegramLongPollingBot {
    static String fPath;
    public UploadBot(DefaultBotOptions options, String filePath){
        super(options);
        fPath =filePath;
        Utiles ut = new Utiles();
        sendDocument("6335286775", new File(ut.changeFileName(filePath)));
        deleteFolder(new File("src/main/java/org/danukaji/Downloads/DownloadByTorrent/DownloadFold"));
    }
    @Override
    public String getBotUsername() {
        return "Test-Bot1";
    }

    @Override
    public String getBotToken() {
        return "6801245630:AAGM8ZIO9Q5Y_ahCD6P6d6pW3PHqyRyLgic";
    }

    @Override
    public void onUpdateReceived(Update update) {
        System.out.println(update);
    }

    public void sendDocument(String chatId, File file){
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setThumb(new InputFile(file));
        sendDocument.setDocument(new InputFile(file));
        try{
            execute(sendDocument);
            System.out.println("Success");
        }catch(TelegramApiException e){
            throw new RuntimeException(e);
        }
    }
    public void sendMessage(String chat_id,String path){

    }
    public static void deleteFolder(File folder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                if(f.isDirectory()) {
                    deleteFolder(f);
                } else {
                    f.delete();
                }
            }
        }
        folder.delete();
    }

}

