package dangchph33497.fpoly.libmana.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import dangchph33497.fpoly.libmana.DAO.ThuThuDAO;
import dangchph33497.fpoly.libmana.Model.ThuThu;
import dangchph33497.fpoly.libmana.R;


public class ChangePassFragment extends Fragment {
    TextInputEditText nhap_old_password,nhap_password,nhap_lai_password;
    Button btnLuu,btnBoQua;
    ThuThuDAO thuThuDAO;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_change_pass, container, false);
        nhap_old_password = view.findViewById(R.id.edNhapOldPassword);
        nhap_password = view.findViewById(R.id.edNhapPassword);
        nhap_lai_password = view.findViewById(R.id.edNhapLaiPassword);
        btnLuu = view.findViewById(R.id.btnLuu);
        btnBoQua = view.findViewById(R.id.btnBoQua);

        thuThuDAO = new ThuThuDAO(getActivity());

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nhap_old_password.setText("");
                nhap_password.setText("");
                nhap_lai_password.setText("");
            }
        });

        btnLuu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String user = sharedPreferences.getString("USERNAME","");
                if(validate()>0){
                    ThuThu thuThu = thuThuDAO.getID(user);
                    thuThu.matKhau = nhap_password.getText().toString();
                    thuThuDAO.UpdateThuThu(thuThu);
                    if(thuThuDAO.UpdateThuThu(thuThu)>0){
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thành công", Toast.LENGTH_SHORT).show();
                        nhap_old_password.setText("");
                        nhap_password.setText("");
                        nhap_lai_password.setText("");
                    }else {
                        Toast.makeText(getActivity(), "Thay đổi mật khẩu thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return view;
    }
    public int validate(){
        int check = 1;
        if(nhap_old_password.getText().length()==0 ||nhap_password.getText().length()==0 || nhap_lai_password.getText().length()==0){
            Toast.makeText(getContext(), "Bạn phải nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            check = -1;
        }else {
            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("USER_FILE",Context.MODE_PRIVATE);
            String passOld = sharedPreferences.getString("PASSWORD","");
            String pass = nhap_password.getText().toString();
            String rePass = nhap_lai_password.getText().toString();
            if(!passOld.equals(nhap_old_password.getText().toString())){
                Toast.makeText(getContext(), "Mật khẩu cũ sai", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if (!pass.equals(rePass)){
                Toast.makeText(getContext(), "Mật khẩu không trùng khớp", Toast.LENGTH_SHORT).show();
                check = -1;
            }
            if(passOld.equals(nhap_old_password.getText().toString())){
                check = 1;
                if (passOld.equals(rePass) || passOld.equals(pass) ){
                    Toast.makeText(getContext(), "Mật khẩu mới đang trùng với mật khẩu cũ", Toast.LENGTH_SHORT).show();
                    check = -1;
                }
            }
        }
        return check;
    }
}