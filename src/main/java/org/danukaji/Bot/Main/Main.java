package org.danukaji.Bot.Main;
import org.danukaji.Bot.Film.Download.DownloadThread;
import org.danukaji.Bot.Film.Upload.UploadThread;
import org.danukaji.Bot.Database.Database;
public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.DatabaseConnection();
        ThreadGroup filmthreadGroup = new ThreadGroup("Film-Group");
        DownloadThread downloadThread = new DownloadThread();
        UploadThread uploadThread = new UploadThread();
        Thread nt = new Thread(filmthreadGroup,downloadThread,"Film-Download-Thread");
        Thread fut = new Thread(filmthreadGroup,uploadThread,"Film-Upload -Thread");
        fut.start();
        nt.start();
    }
}
