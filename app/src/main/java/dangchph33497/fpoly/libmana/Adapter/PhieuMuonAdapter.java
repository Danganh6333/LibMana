package dangchph33497.fpoly.libmana.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dangchph33497.fpoly.libmana.DAO.SachDAO;
import dangchph33497.fpoly.libmana.DAO.ThanhVienDAO;
import dangchph33497.fpoly.libmana.Fragment.PhieuMuonFragment;
import dangchph33497.fpoly.libmana.Model.PhieuMuon;
import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.Model.ThanhVien;
import dangchph33497.fpoly.libmana.R;

public class PhieuMuonAdapter extends ArrayAdapter<PhieuMuon> {
    private Context context;
    PhieuMuonFragment phieuMuonFragment;
    private ArrayList<PhieuMuon>phieuMuons;
    TextView tvMaPM,tvTenTV,tvTenSach,tvTraSach,tvTienThue,tvNgay;
    ImageView imgDel;
    SachDAO sachDAO;
    ThanhVienDAO thanhVienDAO;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public PhieuMuonAdapter(@NonNull Context context, PhieuMuonFragment phieuMuonFragment, @NonNull ArrayList<PhieuMuon> objects) {
        super(context, 0, objects);
        this.context = context;
        this.phieuMuons = objects;
        this.phieuMuonFragment = phieuMuonFragment;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_phieu_muon,null);
        }
        final PhieuMuon phieuMuon = phieuMuons.get(position);
        if(phieuMuon !=null){
            tvMaPM = view.findViewById(R.id.tvMaPM);
            tvMaPM.setText("Mã phiếu: "+phieuMuon.maPM);
            imgDel = view.findViewById(R.id.imgDel);
            sachDAO = new SachDAO(context);
            Sach sach = sachDAO.getID(String.valueOf(phieuMuon.maSach));
            tvTenSach = view.findViewById(R.id.tvTenSach);
            tvTenSach.setText("Tên sách: "+sach.tenSach);
            thanhVienDAO = new ThanhVienDAO(context);
            ThanhVien thanhVien = thanhVienDAO.getID(String.valueOf(phieuMuon.maTV));
            tvTenTV = view.findViewById(R.id.tvTenTV);
            tvTenTV.setText("Thành Viên : "+thanhVien.hoTen);
            tvTienThue = view.findViewById(R.id.tvTienThue);
            tvTienThue.setText("Tiền thuê :"+phieuMuon.tienThue);
            tvNgay = view.findViewById(R.id.tvNgay);
            tvNgay.setText("Ngày thuê: "+dateFormat.format(phieuMuon.ngay));
            tvTraSach = view.findViewById(R.id.tvTraSachs);
            if(phieuMuon.traSach==1){
                tvTraSach.setTextColor(Color.BLUE);
                tvTraSach.setText("Đã trả sách");
            }else {
                tvTraSach.setTextColor(Color.RED);
                tvTraSach.setText("Chưa trả sách");
            }
        }
        imgDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuonFragment.Xoa(String.valueOf(phieuMuon.maPM));
            }
        });
        return view;
    }
}
