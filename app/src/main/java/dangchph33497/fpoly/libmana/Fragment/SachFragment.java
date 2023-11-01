
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
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dangchph33497.fpoly.libmana.Adapter.LoaiSachSpinnerAdapter;
import dangchph33497.fpoly.libmana.Adapter.SachAdapter;
import dangchph33497.fpoly.libmana.DAO.LoaiSachDAO;
import dangchph33497.fpoly.libmana.DAO.SachDAO;
import dangchph33497.fpoly.libmana.Model.LoaiSach;
import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.R;


public class SachFragment extends Fragment {
    ListView lvSach;
    ArrayList<Sach> sachArrayList;
    FloatingActionButton fabSach;
    Dialog dialog;
    EditText edMaSach,edTenSach,edGiaThue;
    Spinner spinner;
    Button btnSave,btnCancel;
    Sach sach;
    static SachDAO sachDAO;
    SachAdapter sachAdapter;
    LoaiSachSpinnerAdapter sachSpinnerAdapter;
    ArrayList<LoaiSach> loaiSachArrayList;
    LoaiSach loaiSach;
    LoaiSachDAO loaiSachDAO;
    int maLoaiSach,position;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sach, container, false);
        lvSach = view.findViewById(R.id.lvSach);
        fabSach = view.findViewById(R.id.fabSach);
        sachDAO = new SachDAO(getActivity());
        capNhatLv();
        fabSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                sach = sachArrayList.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return view;
    }
    protected  void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_sach);
        edMaSach = dialog.findViewById(R.id.edMaSach);
        edTenSach = dialog.findViewById(R.id.edTenSach);
        edGiaThue = dialog.findViewById(R.id.edGiaThue);
        spinner = dialog.findViewById(R.id.spinner);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        btnSave = dialog.findViewById(R.id.btnSave);
        loaiSachArrayList = new ArrayList<LoaiSach>();
        loaiSachDAO = new LoaiSachDAO(context);
        loaiSachArrayList = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        sachSpinnerAdapter = new LoaiSachSpinnerAdapter(context,loaiSachArrayList);
        spinner.setAdapter(sachSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                maLoaiSach = loaiSachArrayList.get(position).maLoai;
                Toast.makeText(context, "Chọn "+loaiSachArrayList.get(position).tenLoai, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        edMaSach.setEnabled(false);
        if(type!=0){
            edMaSach.setText(String.valueOf(sach.maSach));
            edTenSach.setText(sach.tenSach);
            edGiaThue.setText(String.valueOf(sach.giaThue));
            for (int i = 0; i < loaiSachArrayList.size(); i++) {
                if(sach.maLoai == (loaiSachArrayList.get(i).maLoai)){
                    position=1;
                }
            }
            spinner.setSelection(position);
        }
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sach = new Sach();
                sach.tenSach  = edTenSach.getText().toString();
                sach.giaThue =Integer.parseInt(edGiaThue.getText().toString());
                sach.maLoai = maLoaiSach;
                if(spinner.getAdapter().isEmpty()){
                    Toast.makeText(context, "Không nên để trống", Toast.LENGTH_SHORT).show();
                }else {
                    if(validate()>0) {
                        if (type == 0) {
                            if (sachDAO.AddSach(sach) > 0) {
                                Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            sach.maSach = Integer.parseInt(edMaSach.getText().toString());
                            if (sachDAO.UpdateSach(sach) > 0) {
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
        });
        dialog.show();
    }
    public void xoa(final String Id){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setMessage("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                sachDAO.DeleteSach(Id);
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
        sachArrayList = (ArrayList<Sach>) sachDAO.getAll();
        sachAdapter=new SachAdapter(getActivity(),this,sachArrayList);
        lvSach.setAdapter(sachAdapter);
    }
    public  int validate(){
        int check =1;
        if(edTenSach.getText().length()==0||edGiaThue.getText().length()==0){
            Toast.makeText(getContext(), "Chưa đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return check;
    }
}