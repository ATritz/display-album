package tritza.com.albums.display;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;
import tritza.com.albums.R;
import tritza.com.albums.model.Album;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    List<Album> items;
    private Context context;

    /**
     * Adapter to display the list of albums
     */
    public CardAdapter(Context context) {
        super();
        items = new ArrayList<Album>();
        this.context = context;
    }

    public void addData(Album album) {
        items.add(album);
        notifyDataSetChanged();
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycler_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Album album = this.items.get(i);

        viewHolder.title.setText(album.getTitle());

        Picasso.with(context).load(album.getUrl()).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView) ImageView imageView;
        @BindView(R.id.title) TextView title;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}