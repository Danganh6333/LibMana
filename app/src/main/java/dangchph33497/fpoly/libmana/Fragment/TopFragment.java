package dangchph33497.fpoly.libmana.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import dangchph33497.fpoly.libmana.Adapter.TopAdapter;
import dangchph33497.fpoly.libmana.DAO.ThongKeDAO;
import dangchph33497.fpoly.libmana.Model.Top;
import dangchph33497.fpoly.libmana.R;


public class TopFragment extends Fragment {
    ListView lvTop;
    ArrayList<Top> topArrayList;
    TopAdapter topAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_top, container, false);
        lvTop = view.findViewById(R.id.lvTop);
        ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
        topArrayList = (ArrayList<Top>) thongKeDAO.getTop();
        topAdapter = new TopAdapter(getActivity(),this,topArrayList);
        lvTop.setAdapter(topAdapter);
        return view;
    }
}