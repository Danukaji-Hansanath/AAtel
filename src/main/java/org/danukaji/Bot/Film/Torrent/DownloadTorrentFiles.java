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
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.danukaji.Bot.Film.Download.TorrentDownloadBot;

import org.danukaji.Bot.Film.Upload.UploadBot;
import org.danukaji.Bot.Utilities.Utiles;
import org.telegram.telegrambots.bots.DefaultBotOptions;

import org.telegram.telegrambots.meta.TelegramBotsApi;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

/**
 * @author danukaji
 */
public final class DownloadTorrentFiles {

    public void DownloadTorrent(String torFile) throws Throwable {
        // comment this line for a real application
        String[] args = new String[]{torFile};
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
                        if (p == 80) {
                            String message = "Your File is Downloading " + p + "%";
                            System.out.println("Progress: " + p + " for torrent name: " + a.torrentName());
                            System.out.println((s.stats().totalDownload()) / (1024 * 8));
                        }

                        break;
                    case TORRENT_FINISHED:
                        System.out.println("Torrent finished");
                        signal.countDown();
                        Utiles utils = new Utiles();
                        List<String> utStr = utils.getFile(utils.getFolders("src/main/java/org/danukaji/Downloads/DownloadByTorrent"));
                        List<String> realFiles = utils.fileFilter(utStr);
                        String file = null;
                        for (String fl : realFiles) {
                            System.out.println(fl);
                            DefaultBotOptions options = new DefaultBotOptions();
                            options.setBaseUrl("http://127.0.0.1:1300/bot");
                            UploadBot bot = new UploadBot(options ,fl);
                            TelegramBotsApi botsApi = null;
                            try {
                                botsApi = new TelegramBotsApi(DefaultBotSession.class);
                                botsApi.registerBot(bot);
                            } catch (TelegramApiException e) {
                                throw new RuntimeException(e);
                            }
                        }
                }
            }
        });

        s.start();
        TorrentInfo ti = new TorrentInfo(torrentFile);
        s.download(ti, new File("src/main/java/org/danukaji/Downloads/DownloadByTorrent/DownloadFold"));
        signal.await();
        s.stop();

    }

}
