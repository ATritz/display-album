package tritza.com.albums.model;

public class Album {

    private final String albumId;
    private final String id;
    private final String title ;
    private final String url;
    private final String thumbnailUrl;

    /**
     * Model for Albums
     */
    public Album(String albumId, String id, String title, String url, String thumbnailUrl) {
        this.albumId = albumId;
        this.id = id;
        this.title = title;
        this.url = url;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getAlbumId() {
        return albumId;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }
}
