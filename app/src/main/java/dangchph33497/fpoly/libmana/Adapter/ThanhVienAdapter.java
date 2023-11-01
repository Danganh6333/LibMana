package dangchph33497.fpoly.libmana.Adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import dangchph33497.fpoly.libmana.DAO.ThanhVienDAO;
import dangchph33497.fpoly.libmana.Fragment.ThanhVienFragment;
import dangchph33497.fpoly.libmana.Model.ThanhVien;
import dangchph33497.fpoly.libmana.R;

public class ThanhVienAdapter extends ArrayAdapter<ThanhVien> {
    private Context context;
    ThanhVienFragment thanhVienFragment;
    private ArrayList<ThanhVien>thanhViens;
    TextView tvMaTV,tvTenTV,tvNamSinh;
    ImageView imgDel;
    public ThanhVienAdapter(@NonNull Context context, ThanhVienFragment thanhVienFragment,@NonNull ArrayList<ThanhVien>list) {
        super(context, 0,list);
        this.context = context;
        this.thanhViens = list;
        this.thanhVienFragment = thanhVienFragment;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_thanh_vien,null);
        }
        final ThanhVien item = thanhViens.get(position);
        if(item!=null){
            tvMaTV = view.findViewById(R.id.tvMaTV);
            tvMaTV.setText("Mã thành viên "+item.maTV);

            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Tên thành viên "+item.hoTen);

            tvNamSinh = view.findViewById(R.id.tvNamSinh);
            tvNamSinh.setText("Năm Sinh "+item.namSinh);

            imgDel = view.findViewById(R.id.imgDel);
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Giụ function xóa trong ThanhVienFragment
                ThanhVienDAO thanhVienDAO = new ThanhVienDAO(context);
                String id = String.valueOf(item.getMaTV());
                int check = thanhVienDAO.DeleteThanhVien(id);
                if(check>0){
                    thanhVienFragment.xoa(String.valueOf(item.maTV));
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