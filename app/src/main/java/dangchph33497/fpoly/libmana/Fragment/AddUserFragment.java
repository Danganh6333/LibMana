package dangchph33497.fpoly.libmana.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import dangchph33497.fpoly.libmana.DAO.ThuThuDAO;
import dangchph33497.fpoly.libmana.Model.ThuThu;
import dangchph33497.fpoly.libmana.R;


public class AddUserFragment extends Fragment {
    EditText edTenDangNhap,edHoVaTen,edNhapMatKhau,edNhapLaiMatKhau;
    Button btnThem,btnTroVe;
    ThuThuDAO thuThuDAO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_user, container, false);
        edTenDangNhap = view.findViewById(R.id.edTenDangNhap);
        edHoVaTen = view.findViewById(R.id.edHoVaTen);
        edNhapMatKhau = view.findViewById(R.id.edNhapMatKhau);
        edNhapLaiMatKhau = view.findViewById(R.id.edNhapLaiMatKhau);
        btnThem = view.findViewById(R.id.btnThem);
        btnTroVe = view.findViewById(R.id.btnTroVe);

        thuThuDAO = new ThuThuDAO(getActivity());

        btnTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenDangNhap.setText("");
                edHoVaTen.setText("");
                edNhapMatKhau.setText("");
                edNhapLaiMatKhau.setText("");
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ThuThu thuThu = new ThuThu();
                thuThu.maTT = edTenDangNhap.getText().toString();
                thuThu.hoTen = edHoVaTen.getText().toString();
                thuThu.matKhau = edNhapMatKhau.getText().toString();
                if(validate()>0){
                    if(thuThuDAO.AddThuThu(thuThu)>0){
                        Toast.makeText(getActivity(), "Lưu thành công", Toast.LENGTH_SHORT).show();
                        edTenDangNhap.setText("");
                        edHoVaTen.setText("");
                        edNhapMatKhau.setText("");
                        edNhapLaiMatKhau.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Lưu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    public int validate(){
        int check = 1;
        if(edTenDangNhap.getText().length()==0 || edHoVaTen.getText().length()==0 ||
                edNhapMatKhau.getText().length() ==0 || edNhapLaiMatKhau.getText().length() ==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check =-1;
        }else {
            String pass = edNhapMatKhau.getText().toString();
            String rePass = edNhapLaiMatKhau.getText().toString();
            if(!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }
}