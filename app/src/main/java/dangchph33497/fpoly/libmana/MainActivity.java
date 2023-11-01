package dangchph33497.fpoly.libmana;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import dangchph33497.fpoly.libmana.Fragment.AddUserFragment;
import dangchph33497.fpoly.libmana.Fragment.ChangePassFragment;
import dangchph33497.fpoly.libmana.Fragment.DoanhThuFragment;
import dangchph33497.fpoly.libmana.Fragment.LoaiSachFragment;
import dangchph33497.fpoly.libmana.Fragment.PhieuMuonFragment;
import dangchph33497.fpoly.libmana.Fragment.SachFragment;
import dangchph33497.fpoly.libmana.Fragment.ThanhVienFragment;
import dangchph33497.fpoly.libmana.Fragment.TopFragment;

public class MainActivity extends AppCompatActivity {
    TextView tvUser;
    View view;
    DrawerLayout drawerLayout;
    Toolbar mToolbar;
    FragmentManager fragmentManager;
    AddUserFragment addUserFragment;
    ChangePassFragment changePassFragment;
    DoanhThuFragment doanhThuFragment;
    LoaiSachFragment loaiSachFragment;
    PhieuMuonFragment phieuMuonFragment;
    SachFragment sachFragment;
    ThanhVienFragment thanhVienFragment;
    TopFragment topFragment;
    NavigationView nav_view_001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawerLayout = findViewById(R.id.drawerLayout);
        mToolbar = findViewById(R.id.mToolbar);
        nav_view_001 = findViewById(R.id.nav_view);
        view = nav_view_001.getHeaderView(0);
        tvUser = view.findViewById(R.id.tvUser);
        //set toolbar thay cho actionbar
        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,mToolbar,R.string.Open,R.string.Close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        addUserFragment = new AddUserFragment();
        changePassFragment = new ChangePassFragment();
        doanhThuFragment = new DoanhThuFragment();
        loaiSachFragment = new LoaiSachFragment();
        phieuMuonFragment = new PhieuMuonFragment();
        sachFragment = new SachFragment();
        thanhVienFragment = new ThanhVienFragment();
        topFragment = new TopFragment();

        Intent i = getIntent();
        String user = i.getStringExtra("user");
        tvUser.setText("Chào Mừng\n"+user);
        //admin có quyền thêm người dùng
        if(user.equalsIgnoreCase("admin")){
            nav_view_001.getMenu().findItem(R.id.ThemNguoiDung).setVisible(true);
        }else {
            nav_view_001.getMenu().findItem(R.id.ThemNguoiDung).setVisible(false);
        }

        fragmentManager = getSupportFragmentManager();
        nav_view_001.setNavigationItemSelectedListener(item -> {
            if(item.getItemId() ==R.id.QuanLyPhieuMuon){
                fragmentManager.beginTransaction().replace(R.id.frag_container,phieuMuonFragment).commit();
            }else if(item.getItemId() ==R.id.QuanLyLoaiSach){
                fragmentManager.beginTransaction().replace(R.id.frag_container,loaiSachFragment).commit();
            }else if(item.getItemId() ==R.id.QuanLySach){
                fragmentManager.beginTransaction().replace(R.id.frag_container,sachFragment).commit();
            }else if(item.getItemId() ==R.id.QuanLyThanhVien){
                fragmentManager.beginTransaction().replace(R.id.frag_container,thanhVienFragment).commit();
            }else  if(item.getItemId() ==R.id.MuonSachMuonNhieuNhat){
                fragmentManager.beginTransaction().replace(R.id.frag_container,topFragment).commit();
            }else if(item.getItemId() ==R.id.DoanhThu){
                fragmentManager.beginTransaction().replace(R.id.frag_container,doanhThuFragment).commit();
            }else if(item.getItemId() ==R.id.ThemNguoiDung){
                fragmentManager.beginTransaction().replace(R.id.frag_container,addUserFragment).commit();
            }else if(item.getItemId() ==R.id.DoiMatKhau){
                fragmentManager.beginTransaction().replace(R.id.frag_container,changePassFragment).commit();
            }else if(item.getItemId() ==R.id.DangXuat){
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
            getSupportActionBar().setTitle(item.getTitle());
            drawerLayout.close();
            return true;
        });
    }
}
