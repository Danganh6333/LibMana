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

import dangchph33497.fpoly.libmana.Model.LoaiSach;
import dangchph33497.fpoly.libmana.R;

public class LoaiSachSpinnerAdapter extends ArrayAdapter<LoaiSach> {
    private Context context;
    private ArrayList<LoaiSach>loaiSachArrayList;
    TextView tvMaLoaiSachSp,tvTenLoaiSachSp;
        public LoaiSachSpinnerAdapter(@NonNull Context context, @NonNull ArrayList<LoaiSach> loaiSachArrayList) {
        super(context, 0, loaiSachArrayList);
        this.context = context;
        this.loaiSachArrayList = loaiSachArrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_loai_sach_item,null);
        }
        final LoaiSach loaiSach = loaiSachArrayList.get(position);
        if(loaiSach!=null){
            tvMaLoaiSachSp = view.findViewById(R.id.tvSpMaLoaiSach);
            tvMaLoaiSachSp.setText(loaiSach.maLoai+".");
            tvTenLoaiSachSp = view.findViewById(R.id.tvSpTenLoaiSach);
            tvMaLoaiSachSp.setText(loaiSach.tenLoai);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_loai_sach_item,null);
        }
        final LoaiSach loaiSach = loaiSachArrayList.get(position);
        if(loaiSach!=null){
            tvMaLoaiSachSp = view.findViewById(R.id.tvSpMaLoaiSach);
            tvTenLoaiSachSp = view.findViewById(R.id.tvSpTenLoaiSach);
            tvMaLoaiSachSp.setText(loaiSach.maLoai+".");
            tvTenLoaiSachSp.setText(loaiSach.tenLoai);
        }
        return view;
    }
}
