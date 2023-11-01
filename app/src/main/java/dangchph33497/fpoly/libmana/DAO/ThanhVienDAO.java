package dangchph33497.fpoly.libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.ThanhVien;

public class ThanhVienDAO {
    private SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    public ThanhVienDAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long AddThanhVien(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.hoTen);
        values.put("namSinh",thanhVien.namSinh);
        return  sqLiteDatabase.insert("ThanhVien",null,values);
    }
    public int UpdateThanhVien(ThanhVien thanhVien){
        ContentValues values = new ContentValues();
        values.put("hoTen",thanhVien.hoTen);
        values.put("namSinh",thanhVien.namSinh);
        return sqLiteDatabase.update("ThanhVien",values,"maTV=?",new String[]{String.valueOf(thanhVien.maTV)});
    }
    public int DeleteThanhVien(String id){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maTV=?",new String[]{id});
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("ThanhVien","maTV=?",new String[]{id});
        if(check==0){
            return -1;
        }else {
            return 1;
        }
    }
    //get tất cả data
    public List<ThanhVien> getAll(){
        String sql = "SELECT * FROM ThanhVien";
        return getData(sql);
    }
    //ge data theo id
    public ThanhVien getID(String id){
        String sql = "SELECT * FROM ThanhVien WHERE maTV=?";
        List<ThanhVien> list = getData(sql,id);
        return list.get(0);
    }
    //get data nhiều tham số
    private List<ThanhVien> getData(String sql,String...selectionArgs){
        List<ThanhVien> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThanhVien thanhVien = new ThanhVien();
            thanhVien.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maTV")));
            thanhVien.hoTen = cursor.getString(cursor.getColumnIndexOrThrow("hoTen"));
            thanhVien.namSinh = cursor.getString(cursor.getColumnIndexOrThrow("namSinh"));
            list.add(thanhVien);
        }
        return list;
    }
}
