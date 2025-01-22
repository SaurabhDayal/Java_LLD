package structural.proxyPattern.proxyPkg;

import structural.proxyPattern.someCoolMediaLibraryPkg.ThirdPartyYouTubeClass;
import structural.proxyPattern.someCoolMediaLibraryPkg.ThirdPartyYouTubeLib;
import structural.proxyPattern.someCoolMediaLibraryPkg.Video;

import java.util.HashMap;

// Proxy class
public class YouTubeCacheProxy implements ThirdPartyYouTubeLib {

    // real Service
    private ThirdPartyYouTubeLib service;

    private HashMap<String, Video> cachePopular;
    private HashMap<String, Video> cacheAll;

    public YouTubeCacheProxy() {
        this.service = new ThirdPartyYouTubeClass();
        this.cachePopular = new HashMap<>();
        this.cacheAll = new HashMap<>();
    }

    @Override
    public HashMap<String, Video> popularVideos() {
        if (cachePopular.isEmpty()) {
            cachePopular = service.popularVideos();
        } else {
            System.out.println("Retrieved list from cache.");
        }
        return cachePopular;
    }

    @Override
    public Video getVideo(String videoId) {
        Video video = cacheAll.get(videoId);
        if (video == null) {
            video = service.getVideo(videoId);
            cacheAll.put(videoId, video);
        } else {
            System.out.println("Retrieved video '" + videoId + "' from cache.");
        }
        return video;
    }

    public void reset() {
        cachePopular.clear();
        cacheAll.clear();
    }
}