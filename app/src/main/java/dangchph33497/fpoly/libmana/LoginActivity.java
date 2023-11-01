package dangchph33497.fpoly.libmana;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import dangchph33497.fpoly.libmana.DAO.ThuThuDAO;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText edTenDangNhap,edMatKhau;
    CheckBox chkLuu;
    Button btnDangNhap,btnBoQua;
    String strUser,strPass;
    ThuThuDAO thuThuDAO;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edTenDangNhap = findViewById(R.id.edTenDangNhap);
        edMatKhau = findViewById(R.id.edMatKhau);
        chkLuu = findViewById(R.id.chkLuu);
        btnDangNhap = findViewById(R.id.btnDangNhap);
        btnBoQua = findViewById(R.id.btnBoQua);

        thuThuDAO = new ThuThuDAO(this);

        sharedPreferences = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        edTenDangNhap.setText(sharedPreferences.getString("USERNAME",""));
        edMatKhau.setText(sharedPreferences.getString("PASSWORD",""));
        chkLuu.setChecked(sharedPreferences.getBoolean("REMEMBER",false));

        btnBoQua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edTenDangNhap.setText("");
                edMatKhau.setText("");
            }
        });
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });

    }
    public void checkLogin(){
        strUser = edTenDangNhap.getText().toString();
        strPass = edMatKhau.getText().toString();
        if(strUser.isEmpty() || strPass.isEmpty()){
            Toast.makeText(this, "Tên đăng nhập và mật khẩu không được bỏ trống", Toast.LENGTH_SHORT).show();
        }else {
            if (thuThuDAO.checkLogin(strUser, strPass) > 0 || (strUser.equals("admin") && strPass.equals("admin"))) {
                Toast.makeText(this, "Login thành công", Toast.LENGTH_SHORT).show();
                rememberUser(strUser, strPass, chkLuu.isChecked());

                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("user", strUser);
                startActivity(i);
                finish();
            }else {
                Toast.makeText(this, "Tên đăng nhập và mật khẩu không đúng", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void rememberUser(String u,String p,boolean status){
        SharedPreferences sharedPreferences1 = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences1.edit();
        if(!status){
            editor.clear();
        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",status);
        }
        editor.commit();
    }
}