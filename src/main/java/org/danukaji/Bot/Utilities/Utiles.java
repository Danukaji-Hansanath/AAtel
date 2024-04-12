package org.danukaji.Bot.Utilities;

import java.io.File;
import java.io.IOException;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import static org.glassfish.grizzly.http.server.util.ExpandJar.deleteDir;

public class Utiles {
    public static Utiles ut;
    public static List<String> folders = new ArrayList<>();
    public static List <String> filePath = new ArrayList<>();
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

    public void deleteDerectory(String dir) {
        File fir = new File(dir);
        if (fir.exists()) {
            deleteDir(fir);
            System.out.println("Directory Deleted");
        } else {
            System.out.println("Failed");
        }
    }

    public void FileRename() {

    }

    public boolean isFileExist(String dir) {
        boolean isFileEx;
        File fl = new File(dir);
        if (fl.exists()) {
            isFileEx = true;
            System.out.println("File Exist");
        } else {
            isFileEx = false;
            System.out.println("File Not Found");
        }
        return isFileEx;
    }

    public List<String> getFolders(String dir) {

        File folDir = new File(dir);
        if (folDir.isDirectory()) {
            int fdCount = countFolder(dir);
            if (fdCount > 0) {
                File[] files = folDir.listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.isDirectory()) {
                            getFolders(file.toString());
                            folders.add(file.toString());
                        }
                    }
                }
            }
        }
        return folders;
    }
    public List <String> getFile(List <String> folders){

        for(String folder:folders){
            File rFolder = new File(folder);
            if(rFolder.isDirectory()){
                File[] files = rFolder.listFiles();
                for(File exFile : files ){
                    if(exFile.isFile()){
                        filePath.add(exFile.toString());
                    }
                }
            }
        }
        return filePath;
    }
    public List <String> fileFilter(List <String> files){
        List <String> nFiles = new ArrayList<>();
        for(String dir : files){
            String[] videoExtensions = {".mp4", ".avi", ".mkv", ".mov", ".wmv", ".flv", ".pdf",".exe"};
            for(String extention:videoExtensions){
                if(dir.toLowerCase().endsWith(extention)){
                    nFiles.add(dir);
                }
            }
        }
        return nFiles;
    }
    public static int countFolder(String dirPath) {
        File dir = new File(dirPath);
        int folderCount = 0;

        if (dir.exists() && dir.isDirectory()) {
            File[] files = dir.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        folderCount++;
                    }

                }
            }
        } else {
            System.out.println("Directory does not exists .... " + dirPath);
        }

        return folderCount;
    }
    public String changeFileName(String filePath){
        String fileName = null;
        File file = new File(filePath);
        fileName = file.getName();
        System.out.println(fileName);
        String newFileName = fileName.replaceAll("\\[YTS\\.MX\\]","[DSK.FILMS]");
        File newFile =new File(file.getParent(),newFileName);
        if(file.renameTo(newFile)){
            System.out.println("File Rename Successfully....");
        }else{
            System.out.println("Failed to rename file....'");
        }
        return newFile.getAbsolutePath().toString();
    }

}