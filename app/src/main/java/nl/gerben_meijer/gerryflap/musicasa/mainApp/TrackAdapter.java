package nl.gerben_meijer.gerryflap.musicasa.mainApp;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;

import nl.gerben_meijer.gerryflap.musicasa.R;

/**
 * Created by Gerryflap on 2015-06-12.
 */
public class TrackAdapter extends BaseAdapter{

    private ArrayList<Track> mData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Activity activity;

    public TrackAdapter(Activity activity) {
        mInflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(final Track item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Track getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(Collection<Track> TrackCollection){
        for (Track track: TrackCollection){
            mData.add(track);
        }
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        System.out.println("getView " + position + " " + convertView);
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.track_list_view_layout, null);
            holder = new ViewHolder();
            holder.view = (LinearLayout) convertView;
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        ((TextView)holder.view.findViewById(R.id.trackName)).setText(mData.get(position).getName());
        ((TextView)holder.view.findViewById(R.id.artistName)).setText(mData.get(position).getArtist());
        holder.view.setPadding(20,20,20,20);
            /*if(position%2 == 0) {
                holder.view.setBackgroundColor(0xFFAAAAAA);
            } else {
                holder.view.setBackgroundColor(0xFFBBBBBB);
            }
            */
        return convertView;
    }

    public void clear() {
        this.mData.clear();
    }

    public class ViewHolder{
        public LinearLayout view;
    }

}
