package tritza.com.albums.display;

import android.support.annotation.NonNull;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import tritza.com.albums.model.Album;

public class DisplayAlbumPresenter {

    @NonNull private CompositeDisposable compositeDisposable = new CompositeDisposable();
    @NonNull private AlbumApi albumApi;
    @NonNull private DisplayAlbumScreen screen;

    /**
     * Constructor for presenter
     * @param screen
     */
    public DisplayAlbumPresenter(DisplayAlbumScreen screen) {
        this.screen = screen;
        albumApi = new RetrofitService().getAlbumApi();
        requestAlbums();
    }

    /**
     * Make the request to fetch the list of albums and display it on the screen
     */
    private void requestAlbums() {
        compositeDisposable.add(albumApi.getPhotos().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map(new Function<List<Album>, List<Album>>() {
                @Override
                public List<Album> apply(@io.reactivex.annotations.NonNull final List<Album> albumList) throws Exception {
                    return albumList;
                }
            }).subscribe(new Consumer<List<Album>>() {
                @Override
                public void accept(@io.reactivex.annotations.NonNull final List<Album> albums) throws Exception {
                    screen.displayAlbums(albums);
                }
            }));
    }
}
