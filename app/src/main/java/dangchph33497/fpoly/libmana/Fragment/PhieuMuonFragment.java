package dangchph33497.fpoly.libmana.Fragment;


import static java.time.LocalDate.now;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import dangchph33497.fpoly.libmana.Adapter.PhieuMuonAdapter;
import dangchph33497.fpoly.libmana.Adapter.SachSpinnerAdapter;
import dangchph33497.fpoly.libmana.Adapter.ThanhVienSpinnerAdapter;
import dangchph33497.fpoly.libmana.DAO.PhieuMuonDAO;
import dangchph33497.fpoly.libmana.DAO.SachDAO;
import dangchph33497.fpoly.libmana.DAO.ThanhVienDAO;
import dangchph33497.fpoly.libmana.Model.PhieuMuon;
import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.Model.ThanhVien;
import dangchph33497.fpoly.libmana.R;


public class PhieuMuonFragment extends Fragment {
    ArrayList<PhieuMuon> phieuMuonArrayList;
    PhieuMuon phieuMuon;
    Dialog dialog;
    Spinner spMaTV,spMaSach;
    TextView tvNgay,tvTienThue;
    CheckBox chkTraSachs;
    EditText edMaPM;
    Button btnLuu,btnBoQua;
    static PhieuMuonDAO phieuMuonDAO;
    ThanhVienSpinnerAdapter thanhVienSpinnerAdapter;
    PhieuMuonAdapter phieuMuonAdapter;
    ArrayList<ThanhVien> thanhVienArrayList;
    ThanhVienDAO thanhVienDAO;
    ThanhVien thanhVien;
    int maThanhVien;
    SachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<Sach>sachArrayList;
    SachDAO sachDAO;
    Sach sach;
    int maSach,tienThue;
    int positionTV,positionSach;
    ListView lvPhieuMuon;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_phieu_muon, container, false);
        lvPhieuMuon = view.findViewById(R.id.lvPhieuMuon);
        FloatingActionButton fabPhieuMuon = view.findViewById(R.id.fabPhieuMuon);
        phieuMuonDAO = new PhieuMuonDAO(getActivity());
        capNhatLv();
        fabPhieuMuon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lvPhieuMuon.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                phieuMuon = phieuMuonArrayList.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return view;
    }

    protected void openDialog(final Context context,final int Type) {
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phieu_muon);
        edMaPM = dialog.findViewById(R.id.edMaPM);
        spMaTV = dialog.findViewById(R.id.spMaTV);
        spMaSach = dialog.findViewById(R.id.spMaSach);
        tvNgay = dialog.findViewById(R.id.tvNgay);
        tvTienThue = dialog.findViewById(R.id.tvTienThue);
        chkTraSachs = dialog.findViewById(R.id.chkTraSachs);
        btnLuu = dialog.findViewById(R.id.btnLuu1);
        btnBoQua = dialog.findViewById(R.id.btnBoQua);
        thanhVienDAO = new ThanhVienDAO(context);
        thanhVienArrayList = new ArrayList<ThanhVien>();
        thanhVienArrayList = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienSpinnerAdapter = new ThanhVienSpinnerAdapter(context, thanhVienArrayList);
        spMaTV.setAdapter(thanhVienSpinnerAdapter);
        spMaTV.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                maThanhVien = thanhVienArrayList.get(i).maTV;
                Toast.makeText(context, "Chọn " + thanhVienArrayList.get(i).hoTen, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        sachDAO = new SachDAO(context);
        sachArrayList = new ArrayList<Sach>();
        sachArrayList = (ArrayList<Sach>) sachDAO.getAll();
        sachSpinnerAdapter = new SachSpinnerAdapter(context, sachArrayList);
        spMaSach.setAdapter(sachSpinnerAdapter);
        spMaSach.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                maSach = sachArrayList.get(position).maSach;
                tienThue = sachArrayList.get(position).giaThue;
                Toast.makeText(context, "Chọn " + sachArrayList.get(position).tenSach, Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        edMaPM.setEnabled(false);
        if (Type != 0) {
            edMaPM.setText(String.valueOf(phieuMuon.maPM));
            for (int i = 0; i < thanhVienArrayList.size(); i++) {
                if (phieuMuon.maTV == (thanhVienArrayList.get(i).maTV)) {
                    positionTV = i;
                }
            }
            spMaTV.setSelection(positionTV);
            for (int i = 0; i < sachArrayList.size(); i++) {
                if (phieuMuon.maSach == (sachArrayList.get(i).maSach)) {
                    positionSach = i;
                }
            }
            spMaSach.setSelection(positionSach);
            tvNgay.setText("Ngày thuê: " + dateFormat.format(phieuMuon.ngay));
            tvTienThue.setText("Tiền thuê: " + phieuMuon.tienThue);
            if (chkTraSachs.isChecked()) {
                phieuMuon.traSach = 1;
            } else {
                phieuMuon.traSach = 0;
            }
        }
        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phieuMuon = new PhieuMuon();
                phieuMuon.maSach = maSach;
                phieuMuon.maTV = maThanhVien;
                phieuMuon.ngay = Date.valueOf(String.valueOf(now()));
                phieuMuon.tienThue = tienThue;
                if (chkTraSachs.isChecked()) {
                    phieuMuon.traSach = 1;
                } else {
                    phieuMuon.traSach = 0;
                }
                if (validate() > 0) {
                    if(spMaSach.getAdapter().isEmpty() || spMaTV.getAdapter().isEmpty()) {
                        Toast.makeText(context, "Spinner đang trống", Toast.LENGTH_SHORT).show();
                    } else  {
                        if (Type == 0) {
                            if (phieuMuonDAO.AddPhieuMuon(phieuMuon) > 0) {
                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            phieuMuon.maPM = Integer.parseInt(edMaPM.getText().toString());
                            if(phieuMuon.traSach==0){
                                Toast.makeText(context, "Không được sửa", Toast.LENGTH_SHORT).show();
                            }else {
                                if (phieuMuonDAO.UpdatePhieuMuon(phieuMuon) > 0) {
                                    Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }

                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void Xoa(final String id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                phieuMuonDAO.DeletePhieuMuon(id);
                capNhatLv();
                dialog.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        phieuMuonArrayList = (ArrayList<PhieuMuon>) phieuMuonDAO.getAll();
        phieuMuonAdapter = new PhieuMuonAdapter(getActivity(),this,
                phieuMuonArrayList);
        lvPhieuMuon.setAdapter(phieuMuonAdapter);
    }
    public  int validate(){
        int check = 1;
        return check;
    }
}