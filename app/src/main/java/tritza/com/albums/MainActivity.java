package tritza.com.albums;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import tritza.com.albums.display.Album;
import tritza.com.albums.display.AlbumApi;
import tritza.com.albums.display.RetrofitService;

public class MainActivity extends AppCompatActivity {

    @NonNull private CompositeDisposable compositeDisposable = new CompositeDisposable();

    @NonNull private AlbumApi albumApi;

    private TextView mOutputTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOutputTextView = (TextView) findViewById(R.id.output);
        // Initialize the city endpoint
        albumApi = new RetrofitService().getAlbumApi();
        // Trigger our request and display afterwards
        requestGeonames();
    }

    private void displayGeonames(@NonNull final List<Album> albums) {
        // Cheap way to display a list of Strings — I was too lazy to   implement a RecyclerView
        final StringBuilder output = new StringBuilder();
        for (final Album album : albums) {
            output.append(album.getTitle()).append("\n");
        }
        mOutputTextView.setText(output.toString());
    }

    private void requestGeonames() {
        compositeDisposable.add(albumApi.getPhotos().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Function<List<Album>, List<Album>>() {
                @Override
                public List<Album> apply(@io.reactivex.annotations.NonNull final List<Album> albumList) throws Exception {
                    // we want to have the geonames and not the wrapper object
                    return albumList;
                }
            }).subscribe(new Consumer<List<Album>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull final List<Album> geonames) throws Exception {
                    displayGeonames(geonames);
                }
            }));
    }

    public void onAlbumListReceived() {
    }
}
