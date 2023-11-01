package dangchph33497.fpoly.libmana.Fragment;

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

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import dangchph33497.fpoly.libmana.Adapter.LoaiSachAdapter;
import dangchph33497.fpoly.libmana.DAO.LoaiSachDAO;
import dangchph33497.fpoly.libmana.Model.LoaiSach;
import dangchph33497.fpoly.libmana.R;


public class LoaiSachFragment extends Fragment {
    ListView lvLoaiSach;
    ArrayList<LoaiSach>loaiSachArrayList;
    FloatingActionButton fabLoaiSach;
    Dialog dialog;
    EditText edMaLoaiSach,edTenLoaiSach;
    Button btnSave,btnCancel;
    static LoaiSachDAO loaiSachDAO;
    LoaiSachAdapter loaiSachAdapter;
    LoaiSach loaiSach;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_loai_sach, container, false);
        lvLoaiSach = view.findViewById(R.id.lvLoaiSach);
        fabLoaiSach = view.findViewById(R.id.fabLoaiSach);
        loaiSachDAO = new LoaiSachDAO(getActivity());
        capNhatLv();
        fabLoaiSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog(getActivity(),0);
            }
        });
        lvLoaiSach.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                loaiSach = loaiSachArrayList.get(position);
                openDialog(getActivity(),1);
                return false;
            }
        });
        return view;
    }
    public void openDialog(final Context context,final int type){
        dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_loai_sach);
        edMaLoaiSach = dialog.findViewById(R.id.edMaLoaiSach);
        edTenLoaiSach = dialog.findViewById(R.id.edTenLoaiSach);
        btnSave = dialog.findViewById(R.id.btnSave);
        btnCancel = dialog.findViewById(R.id.btnCancel);
        edMaLoaiSach.setEnabled(false);
        if(type!=0){
            edMaLoaiSach.setText(String.valueOf(loaiSach.maLoai));
            edTenLoaiSach.setText(loaiSach.tenLoai);
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
                loaiSach = new LoaiSach();
                loaiSach.tenLoai = edTenLoaiSach.getText().toString();
                if(validate()>0){
                    if(type==0){
                        if(loaiSachDAO.AddLS(loaiSach)>0){
                            Toast.makeText(context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        loaiSach.maLoai = Integer.parseInt(edMaLoaiSach.getText().toString());
                        if(loaiSachDAO.UpdateLS(loaiSach)>0){
                            Toast.makeText(context, "Sưa thành công", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                    capNhatLv();
                    dialog.dismiss();
                }
            }
        });
        dialog.show();
    }
    public void xoa(final String ID){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Xóa");
        builder.setTitle("Bạn có muốn xóa không");
        builder.setCancelable(true);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                loaiSachDAO.DeleteLS(ID);
                capNhatLv();;
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
        loaiSachArrayList = (ArrayList<LoaiSach>) loaiSachDAO.getAll();
        loaiSachAdapter = new LoaiSachAdapter(getActivity(),this,loaiSachArrayList);
        lvLoaiSach.setAdapter(loaiSachAdapter);
    }
    public int validate(){
        int check = 1;
        if(edTenLoaiSach.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đủ thông tin", Toast.LENGTH_SHORT).show();
            check=-1;
        }
        return  check;
    }
}
