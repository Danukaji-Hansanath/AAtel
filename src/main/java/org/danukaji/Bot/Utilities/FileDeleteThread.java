package org.danukaji.Bot.Utilities;

public class FileDeleteThread extends Thread {
    public static String[] filePath;

    @Override
    public void run() {
        Utiles delFile = new Utiles();
        filePath[0] = "";
        for(int i = 0; i<=10;i++){
            delFile.DeleteFile(filePath[i]);
            System.out.println("Successfull Delete "+filePath[i]);
        }
    }
}
