package dangchph33497.fpoly.libmana.DAO;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.Sach;
import dangchph33497.fpoly.libmana.Model.Top;

public class ThongKeDAO {
    private SQLiteDatabase database;
    private Context context ;
    public ThongKeDAO(Context context){
        this.context = context;
        DbHelper dbHelper = new DbHelper(context);
        database = dbHelper.getWritableDatabase();
    }
    //Thống kê top 10
    public List<Top> getTop(){
        String sqlTop ="SELECT maSach,COUNT(maSach) AS soLuong FROM PhieuMuon GROUP BY maSach ORDER BY soLuong DESC LIMIT 10";//Lấy top 10
        List<Top> list = new ArrayList<>();
        SachDAO sachDAO = new SachDAO(context);
        Cursor cursor = database.rawQuery(sqlTop,null);
        while(cursor.moveToNext()){
            Top top = new Top();
            Sach sach = sachDAO.getID(cursor.getString(cursor.getColumnIndexOrThrow("maSach")));
            top.tenSach = sach.tenSach;
            top.soLuong = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("soLuong")));
            list.add(top);
        }
        return list;
    }
    //Thống kê doanh thu
    public int getDoanhThu(String tuNgay,String denNgay){
        String sqlDoanhThu = "SELECT SUM(tienThue) AS doanhThu FROM PhieuMuon WHERE ngay BETWEEN ? AND ?";
        List<Integer> list = new ArrayList<>();
        Cursor cursor = database.rawQuery(sqlDoanhThu,new String[]{tuNgay,denNgay});
        while(cursor.moveToNext()){
            try {
                list.add(Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("doanhThu"))));
            }catch (Exception e){
                list.add(0);
            }
        }
        return list.get(0);
    }
}
