package dangchph33497.fpoly.libmana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

import dangchph33497.fpoly.libmana.Fragment.TopFragment;
import dangchph33497.fpoly.libmana.Model.Top;
import dangchph33497.fpoly.libmana.R;

public class TopAdapter extends ArrayAdapter<Top> {
    private Context context;
    private ArrayList<Top> topArrayList;
    private TopFragment topFragment;
    TextView tvSach,tvSoLuong;

    public TopAdapter(@NonNull Context context, TopFragment topFragment, ArrayList<Top>topArrayList) {
        super(context, 0,topArrayList);
        this.context = context;
        this.topArrayList = topArrayList;
        this.topFragment = topFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_top,null);
        }
        final Top top = topArrayList.get(position);
        if(top != null){
            tvSach = view.findViewById(R.id.tvSach);
            tvSach.setText("Sách: "+top.tenSach);
            tvSoLuong = view.findViewById(R.id.tvSoLuong);
            tvSoLuong.setText("Số lượng: "+top.soLuong);
        }
        return  view;
    }
}
