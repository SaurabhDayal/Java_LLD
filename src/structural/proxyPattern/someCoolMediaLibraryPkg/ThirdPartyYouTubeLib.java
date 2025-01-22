package structural.proxyPattern.someCoolMediaLibraryPkg;

import java.util.HashMap;

// Service interface
public interface ThirdPartyYouTubeLib {
    HashMap<String, Video> popularVideos();

    Video getVideo(String videoId);
}