package tritza.com.albums.display;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface AlbumApi {

    String SERVICE_ENDPOINT = "http://jsonplaceholder.typicode.com";

    @GET("/photos")
    Observable<AlbumList> getPhotos();

}
