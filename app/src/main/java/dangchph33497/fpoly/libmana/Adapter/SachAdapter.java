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
import dangchph33497.fpoly.libmana.DAO.SachDAO;
import dangchph33497.fpoly.libmana.Fragment.SachFragment;
import dangchph33497.fpoly.libmana.Model.LoaiSach;
import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.R;

public class SachAdapter extends ArrayAdapter<Sach> {
    private Context context;
    SachFragment sachFragment;
    private ArrayList<Sach> sachArrayList;
    TextView tvMaSach,tvMaLoai,tvTenSach,tvGiaThue;
    ImageView imgDel;
    SachDAO sachDAO;
    public SachAdapter(@NonNull Context context, SachFragment sachFragment, ArrayList<Sach>sachArrayList) {
        super(context, 0, sachArrayList);
        this.context = context;
        this.sachArrayList = sachArrayList;
        this.sachFragment = sachFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view==null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_sach,null);
        }
        final Sach sach = sachArrayList.get(position);
        if(sach!=null){
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            LoaiSach loaiSach = loaiSachDAO.getID(String.valueOf(sach.maLoai));
            tvMaSach = view.findViewById(R.id.tvMaSach);
            tvMaSach.setText("Mã Sach: "+sach.maSach);
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên Sach: "+sach.tenSach);
            tvGiaThue = view.findViewById(R.id.tvGiaThue);
            tvGiaThue.setText("Giá Sách: "+sach.giaThue);
            tvMaLoai = view.findViewById(R.id.tvLoaiSach);
            tvMaLoai.setText("Loại Sách: "+loaiSach.tenLoai);
            imgDel = view.findViewById(R.id.imgDel);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sachDAO = new SachDAO(context);
                String id = String.valueOf(sach.getMaSach());
                int check = sachDAO.DeleteSach(id);
                if(check>0){
                    sachFragment.xoa(String.valueOf(sach.maSach));
                } else if (check == 0) {
                    Toast.makeText(context, "Đang dùng ở bảng khác", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(context, "Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return  view;
    }
}
