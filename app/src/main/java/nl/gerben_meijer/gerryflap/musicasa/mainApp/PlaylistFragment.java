package nl.gerben_meijer.gerryflap.musicasa.mainApp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.gerben_meijer.gerryflap.musicasa.MusicPlayer;
import nl.gerben_meijer.gerryflap.musicasa.R;

/**
 * Created by Gerryflap on 2015-06-12.
 */
public class PlaylistFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaylistFragment newInstance(int sectionNumber) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        MusicPlayer.getInstance().loadAndPlayTrack(1);
        fragment.setArguments(args);
        return fragment;
    }

    public PlaylistFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_playlist, container, false);
        TrackAdapter listAdapter = new TrackAdapter(this.getActivity());
        ((ListView) rootView.findViewById(R.id.suggstionListView)).setAdapter(listAdapter);
        listAdapter.addItem(new Track("Swektrack van de eeuw", "Henk"));
        listAdapter.addItem(new Track("Zware negermuziek", "Piet"));
        listAdapter.addItem(new Track("Harde gangster shit", "Swag"));
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Musicasa) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }
}

