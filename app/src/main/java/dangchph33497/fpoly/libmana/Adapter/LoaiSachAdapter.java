package dangchph33497.fpoly.libmana.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;


import dangchph33497.fpoly.libmana.DAO.LoaiSachDAO;
import dangchph33497.fpoly.libmana.Fragment.LoaiSachFragment;
import dangchph33497.fpoly.libmana.Model.LoaiSach;
import dangchph33497.fpoly.libmana.R;

public class LoaiSachAdapter extends ArrayAdapter<LoaiSach> {
    private  Context context;
    LoaiSachFragment loaiSachFragment;
    private ArrayList<LoaiSach> loaiSachList;
    TextView tvMaLoai,tvTenLoai;
    ImageView imgDel;
    public LoaiSachAdapter(@NonNull Context context, LoaiSachFragment loaiSachFragment, @NonNull ArrayList<LoaiSach> loaiSachList) {
        super(context, 0, loaiSachList);
        this.context = context;
        this.loaiSachList = loaiSachList;
        this.loaiSachFragment = loaiSachFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_loai_sach,null);
        }
        final LoaiSach loaiSach = loaiSachList.get(position);
        if(loaiSach!=null){
            tvMaLoai = view.findViewById(R.id.tvMaLoai);
            tvMaLoai.setText("Mã Loại: "+loaiSach.maLoai);
            tvTenLoai = view.findViewById(R.id.tvTenLoai);
            tvTenLoai.setText("Tên Loại: "+loaiSach.tenLoai);
            imgDel = view.findViewById(R.id.imgDel);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
                String id = String.valueOf(loaiSach.getMaLoai());
                int check = loaiSachDAO.DeleteLS(id);
                if(check>0){
                    loaiSachFragment.xoa(String.valueOf(loaiSach.maLoai));
                } else if (check==0) {
                    Toast.makeText(context, "Đang dùng ở bảng khác", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}
