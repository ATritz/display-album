package tritza.com.albums.display;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;
import tritza.com.albums.R;
import tritza.com.albums.model.Album;

public class DisplayAlbumActivity extends AppCompatActivity implements DisplayAlbumScreen{

    @NonNull private CompositeDisposable compositeDisposable = new CompositeDisposable();

    private DisplayAlbumPresenter presenter;
    private ProgressDialog progressDialog;
    private CardAdapter cardAdapter;

    @BindView(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new DisplayAlbumPresenter(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cardAdapter = new CardAdapter(this);
        recyclerView.setAdapter(cardAdapter);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching...");
        progressDialog.show();
    }

    /**
     * Method to dislay a list of albums
     * @param albums albums to display
     */
    public void displayAlbums(List<Album> albums) {
        progressDialog.dismiss();
        for (final Album album : albums) {
            cardAdapter.addData(album);
        }
    }
}
