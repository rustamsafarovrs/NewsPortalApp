package devteam.rs.newsportaltest;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<News> mNewsItems;
    private Context mContext;

    public Adapter(Context context, List<News> news) {
        this.mContext = context;
        this.mNewsItems = news;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        News item = mNewsItems.get(position);

        holder.title.setText(item.getTitle());
        holder.subtitle.setText(item.getSubtitle());

        holder.idTextView.setText(String.valueOf(item.getId()));

        final String pid = holder.idTextView.getText().toString();

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext, pid, Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.NEWS_ID, pid);
                mContext.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        if (mNewsItems == null)
            return 0;
        return mNewsItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView idTextView;
        TextView title;
        TextView subtitle;
        CardView cardView;

        ViewHolder(View itemView) {
            super(itemView);
            idTextView = itemView.findViewById(R.id.pid);
            title = itemView.findViewById(R.id.news_item_title);
            subtitle = itemView.findViewById(R.id.news_item_subtitle);
            cardView = itemView.findViewById(R.id.news_item_card_view);
        }
    }
}