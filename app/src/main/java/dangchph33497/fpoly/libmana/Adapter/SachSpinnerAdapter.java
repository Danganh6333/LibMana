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

import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.R;

public class SachSpinnerAdapter extends ArrayAdapter<Sach> {
    private Context context;
    private ArrayList<Sach>sachs;
    TextView tvSpMaSach,tvSpTenSach;
    public SachSpinnerAdapter(@NonNull Context context, ArrayList<Sach>sachs) {
        super(context, 0, sachs);
        this.context = context;
        this.sachs = sachs;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_sach,null);
        }
        final Sach sach = sachs.get(position);
        if(sach !=null){
            tvSpMaSach = view.findViewById(R.id.tvSpMaSach);
            tvSpTenSach = view.findViewById(R.id.tvSpTenSach);
            tvSpMaSach.setText(sach.maSach+".");
            tvSpTenSach.setText(sach.tenSach);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_sach,null);
        }
        final Sach sach = sachs.get(position);
        if(sach !=null){
            tvSpMaSach = view.findViewById(R.id.tvSpMaSach);
            tvSpTenSach = view.findViewById(R.id.tvSpTenSach);
            tvSpMaSach.setText(sach.maSach+".");
            tvSpTenSach.setText(sach.tenSach);
        }
        return view;
    }
}
