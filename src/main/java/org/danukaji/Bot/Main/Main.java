package org.danukaji.Bot.Main;
import org.danukaji.Bot.Film.Download.DownloadThread;
import org.danukaji.Bot.Database.Database;

import java.io.File;

public class Main {
    public static void main(String[] args) {

        ThreadGroup filmthreadGroup = new ThreadGroup("Film-Group");
        DownloadThread downloadThread = new DownloadThread();
        Thread nt = new Thread(filmthreadGroup,downloadThread,"Film-Download-Thread");
        nt.start();

    }

}
