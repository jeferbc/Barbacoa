package jeffersonbernalcardona.barbacoa;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Aldebarantech on 19/09/2016.
 */
public class CervezasFragment extends Fragment {
    public CervezasFragment() {
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hamburguer, container, false);
    }
}
