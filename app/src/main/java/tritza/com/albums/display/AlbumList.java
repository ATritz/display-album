package tritza.com.albums.display;

import java.util.List;

public class AlbumList {

    private final List<Album> albumList;

    public AlbumList(List<Album> albumList) {
        this.albumList = albumList;
    }

    public List<Album> getAlbumList() {
        return albumList;
    }
}
