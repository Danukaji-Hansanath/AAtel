package org.danukaji.Bot.Utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static org.glassfish.grizzly.http.server.util.ExpandJar.deleteDir;

public class Utiles {
    //Delete Files After Upload Using This Bot
    public void DeleteFile(String filePath) {
        File delFile = new File(filePath);
        if (delFile.exists()) {
            boolean deletionResult = delFile.delete();
            if (deletionResult) {
                System.out.println("File Delete SuccessFully...");
            } else {
                System.out.println("File Deletion Failed...");
            }
        } else {
            System.out.println("File does not exist");
        }
    }
    public void deleteDerectory(String dir){
        File fir = new File(dir);
        if(fir.exists()){
            deleteDir(fir);
            System.out.println("Directory Deleted");
        }else{
            System.out.println("Failed");
        }
    }
    public void FileRename() {

    }
    public void isFileExist(String dir){
        File fl = new File(dir);
        if(fl.exists()){
            System.out.println("File Exist");
        }else{
            System.out.println("File Not Found");
        }
    }
    public void WildCardFileFinder() throws IOException {
        Path startingDir = Paths.get("src/main/java/org/danukaji/Downloads/DownloadByTorrent");
        String pattern = "glob:**/*.{webm,mkv,FLV,flv,vob,wmv,ogv,avi,mov,mp4,rmbv,pdf}";


    }
}