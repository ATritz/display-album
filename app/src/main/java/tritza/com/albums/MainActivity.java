package tritza.com.albums;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import io.reactivex.Observable;
import retrofit2.Retrofit;
import tritza.com.albums.display.AlbumApi;
import tritza.com.albums.display.AlbumList;
import tritza.com.albums.display.RetrofitService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit service = RetrofitService.createRetrofitService(AlbumApi.SERVICE_ENDPOINT);

        AlbumApi albumApi = service.create(AlbumApi.class);
        Observable<AlbumList> observable = albumApi.getPhotos();
    }
}
