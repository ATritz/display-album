package tritza.com.albums.display;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;
import tritza.com.albums.model.Album;

public interface AlbumApi {

    /**
     * Api call to retreive photos
     * @return
     */
    @GET("/photos")
    Observable<List<Album>> getPhotos();

}
