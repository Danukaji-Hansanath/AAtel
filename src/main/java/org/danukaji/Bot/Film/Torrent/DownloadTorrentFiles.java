package org.danukaji.Bot.Film.Torrent;

import com.frostwire.jlibtorrent.AlertListener;
import com.frostwire.jlibtorrent.LibTorrent;
import com.frostwire.jlibtorrent.SessionManager;
import com.frostwire.jlibtorrent.TorrentInfo;
import com.frostwire.jlibtorrent.alerts.AddTorrentAlert;
import com.frostwire.jlibtorrent.alerts.Alert;
import com.frostwire.jlibtorrent.alerts.AlertType;
import com.frostwire.jlibtorrent.alerts.BlockFinishedAlert;

import java.io.File;
import java.util.concurrent.CountDownLatch;

import org.danukaji.Bot.Film.Download.TorrentDownloadBot;
import org.danukaji.Bot.Film.Torrent.DownloadTorrentFiles;
import org.danukaji.Bot.Utilities.Utiles;

/**
 *
 *
 * @author danukaji
 *
 *
 */
public final class DownloadTorrentFiles {

    public void DownloadTorrent(String torFile) throws Throwable {
        // comment this line for a real application
        String [] args = new String[]{torFile};
        TorrentDownloadBot torrentDownloadBot = new TorrentDownloadBot();
        File torrentFile = new File(args[0]);
        System.out.println("Using libtorrent version: " + LibTorrent.version());
        final SessionManager s = new SessionManager();
        final CountDownLatch signal = new CountDownLatch(1);
        s.addListener(new AlertListener() {
            @Override
            public int[] types() {
                return null;
            }

            @Override
            public void alert(Alert<?> alert) {
                AlertType type = alert.type();

                switch (type) {
                    case ADD_TORRENT:
                        System.out.println("Torrent added");
                        ((AddTorrentAlert) alert).handle().resume();
                        break;
                    case BLOCK_FINISHED:
                        BlockFinishedAlert a = (BlockFinishedAlert) alert;
                        int p = (int) (a.handle().status().progress() * 100);
                        if(p==0 || p==20 || p == 40 || p == 60 || p == 80){
                            String message = "Your File is Downloading "+ p +"%" ;
                            System.out.println("Progress: " + p + " for torrent name: " + a.torrentName());
                            System.out.println((s.stats().totalDownload())/(1024*8));
                        }

                        break;
                    case TORRENT_FINISHED:
                        System.out.println("Torrent finished");
                        signal.countDown();
                        Utiles utils = new Utiles();
                        utils.isFileExist("src/main/java/org/danukaji/Downloads/DownloadByTorrent/[ DevCourseWeb.com ] Ubuntu for Non-Geeks - A Pain-Free, Get-Things-Done Guide, 4th Edition (True PDF)/~Get Your Files Here !/Bonus Resources.txt");
                        break;
                }
            }
        });

        s.start();
        TorrentInfo ti = new TorrentInfo(torrentFile);
        s.download(ti, new File("src/main/java/org/danukaji/Downloads/DownloadByTorrent"));
        signal.await();
        s.stop();

    }

}
