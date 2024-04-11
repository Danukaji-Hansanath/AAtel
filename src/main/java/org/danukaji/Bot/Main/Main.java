package org.danukaji.Bot.Main;
import org.danukaji.Bot.Film.Download.DownloadThread;
import org.danukaji.Bot.Database.Database;
public class Main {
    public static void main(String[] args) {
        Database database = new Database();
        database.DatabaseConnection();
        ThreadGroup threadGroup = new ThreadGroup("Film-Group");
        DownloadThread downloadThread = new DownloadThread();
        Thread nt = new Thread(threadGroup,downloadThread,"Film-Download-Thread");
        nt.start();
    }
}
