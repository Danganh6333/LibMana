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

import dangchph33497.fpoly.libmana.Model.ThanhVien;
import dangchph33497.fpoly.libmana.R;

public class ThanhVienSpinnerAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    private ArrayList<ThanhVien>thanhViens;
    TextView tvSpMaTV,tvSpTenTV;
    public ThanhVienSpinnerAdapter(@NonNull Context context, ArrayList<ThanhVien>thanhViens) {
        super(context, 0,thanhViens);
        this.context = context;
        this.thanhViens = thanhViens;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_thanh_vien,null);
        }
        final ThanhVien thanhVien = thanhViens.get(position);
        if(thanhVien !=null){
            tvSpMaTV = view.findViewById(R.id.tvSpMaTV);
            tvSpMaTV.setText(thanhVien.maTV+". ");

            tvSpTenTV = view.findViewById(R.id.tvSpTenTV);
            tvSpTenTV.setText(thanhVien.hoTen);
        }
        return view;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.spinner_thanh_vien,null);
        }
        final ThanhVien thanhVien = thanhViens.get(position);
        if(thanhVien!=null){
            tvSpMaTV = view.findViewById(R.id.tvSpMaTV);
            tvSpMaTV.setText(thanhVien.maTV+". ");

            tvSpTenTV = view.findViewById(R.id.tvSpTenTV);
            tvSpTenTV.setText(thanhVien.hoTen);
        }
        return view;
    }
}
