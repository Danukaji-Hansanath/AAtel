package org.danukaji.Bot.Film.Upload;

import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendDocument;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.File;

public class UploadBot extends TelegramLongPollingBot {

    public UploadBot(DefaultBotOptions options){
        super(options);
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
        File fl = new File("src/main/java/org/danukaji/Git-2.44.0-64-bit.exe");
        if(fl.exists()){
            senDecument("6335286775", fl);
        }

    }

    public void senDecument(String chatId, File file){
        SendDocument sendDocument = new SendDocument();
        sendDocument.setChatId(chatId);
        sendDocument.setDocument(new InputFile(file));
        try{
            execute(sendDocument);
            System.out.println("Success");
        }catch(TelegramApiException e){
            throw new RuntimeException(e);
        }
    }

}

