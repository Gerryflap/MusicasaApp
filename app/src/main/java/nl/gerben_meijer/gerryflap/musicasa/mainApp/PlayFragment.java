package nl.gerben_meijer.gerryflap.musicasa.mainApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.MediaController;

import nl.gerben_meijer.gerryflap.musicasa.MusicPlayer;
import nl.gerben_meijer.gerryflap.musicasa.R;
import nl.gerben_meijer.gerryflap.musicasa.login.LoginActivity;

/**
 * Created by Gerryflap on 2015-06-12.
 */
public class PlayFragment extends Fragment implements View.OnClickListener {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlayFragment newInstance(int sectionNumber) {
        PlayFragment fragment = new PlayFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    public PlayFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_play, container, false);
        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((Musicasa) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }

    @Override
    public void onClick(View v) {
        this.startActivity(new Intent(this.getActivity().getApplicationContext(), LoginActivity.class));
    }
}

