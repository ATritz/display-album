package tritza.com.albums.display;

import java.util.List;
import tritza.com.albums.model.Album;

public interface DisplayAlbumScreen {

    /**
     * Display the list of albums
     * @param albums
     */
    void displayAlbums(List<Album> albums);
}
