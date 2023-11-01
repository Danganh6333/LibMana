package dangchph33497.fpoly.libmana.Fragment;

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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.Calendar;

import dangchph33497.fpoly.libmana.Adapter.ThanhVienAdapter;
import dangchph33497.fpoly.libmana.DAO.ThanhVienDAO;
import dangchph33497.fpoly.libmana.Model.ThanhVien;
import dangchph33497.fpoly.libmana.R;


public class ThanhVienFragment extends Fragment {
    ListView lvThanhVien;
    FloatingActionButton fabThanhVien;
    Dialog dialog;
    EditText edMaTV,edTenTV,edNamSinh;
    Button btnSave,btnCancel;
    ArrayList<ThanhVien>thanhViens;
    ThanhVienAdapter thanhVienAdapter;
    ThanhVien thanhVien;
    static ThanhVienDAO thanhVienDAO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_thanh_vien, container, false);
        lvThanhVien = view.findViewById(R.id.lvThanhVien);
        fabThanhVien = view.findViewById(R.id.fabThanhVien);
        thanhVienDAO = new ThanhVienDAO(getActivity());
        capNhatLv();
        fabThanhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog(getActivity(),0);
            }
        });
        lvThanhVien.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                thanhVien = thanhViens.get(i);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return view;
    }
    protected void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_thanh_vien);
        edMaTV = dialog.findViewById(R.id.edMaTV);
        edTenTV = dialog.findViewById(R.id.edTenTV);
        edNamSinh = dialog.findViewById(R.id.edNamSinh);
        btnSave = dialog.findViewById(R.id.btnSaveTV);
        btnCancel = dialog.findViewById(R.id.btnCancelTV);
        edMaTV.setEnabled(false);
        if(type !=0){
            edMaTV.setText(String.valueOf(thanhVien.maTV));
            edTenTV.setText(thanhVien.hoTen);
            edNamSinh.setText(thanhVien.namSinh);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanhVien = new ThanhVien();
                
                thanhVien.hoTen = edTenTV.getText().toString();
                thanhVien.namSinh = edNamSinh.getText().toString();
                if(validate()>0){
                    if(type ==0){
                        if(thanhVienDAO.AddThanhVien(thanhVien)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        thanhVien.maTV = Integer.parseInt(edMaTV.getText().toString());
                        if(thanhVienDAO.UpdateThanhVien(thanhVien)>0){
                            Toast.makeText(context, "Sửa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                capNhatLv();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa Thành Viên");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                thanhVienDAO.DeleteThanhVien(Id);
                capNhatLv();
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = builder.create();
        builder.show();
    }
    void capNhatLv(){
        thanhViens = (ArrayList<ThanhVien>) thanhVienDAO.getAll();
        thanhVienAdapter = new ThanhVienAdapter(getActivity(),this,thanhViens);
        lvThanhVien.setAdapter(thanhVienAdapter);
    }
    public int validate(){
        int check = 1;
        String namSinh = edNamSinh.getText().toString();
        int nam = Calendar.getInstance().get(Calendar.YEAR);
        if(edTenTV.getText().length()==0 ||edNamSinh.getText().length()<4){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        if(namSinh.compareTo(String.valueOf(nam))>0){
            Toast.makeText(getContext(), "Bạn chưa đủ tuổi", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }

}