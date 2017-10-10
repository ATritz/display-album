package tritza.com.albums.display;

import io.reactivex.Observable;
import java.util.List;
import retrofit2.http.GET;

public interface AlbumApi {

    @GET("/photos")
    Observable<List<Album>> getPhotos();

}
