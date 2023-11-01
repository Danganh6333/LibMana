package dangchph33497.fpoly.libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.Sach;

public class SachDAO {
    SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;
    public SachDAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long AddSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue);
        values.put("maLoai",sach.maLoai);
        return  sqLiteDatabase.insert("Sach",null,values);
    }
    public int UpdateSach(Sach sach){
        ContentValues values = new ContentValues();
        values.put("tenSach",sach.tenSach);
        values.put("giaThue",sach.giaThue);
        values.put("maLoai",sach.maLoai);
        return sqLiteDatabase.update("Sach",values,"maSach=?",new String[]{String.valueOf(sach.maSach)});
    }
    public int DeleteSach(String id){
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM PhieuMuon WHERE maSach=?",new String[]{id});
        if(cursor.getCount()>0){
            return 0;
        }
        int check = sqLiteDatabase.delete("Sach","maSach=?",new String[]{id});
        if(check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public List<Sach> getAll(){
        String sql = "SELECT * FROM Sach";
        return getData(sql);
    }
    public Sach getID(String id){
        String sql = "SELECT * FROM Sach WHERE maSach=?";
        List<Sach> list = getData(sql,id);
        return list.get(0);
    }
    private List<Sach> getData(String sql,String...selectionArgs){
        List<Sach> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            Sach sach = new Sach();
            sach.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maSach")));
            sach.tenSach = cursor.getString(cursor.getColumnIndexOrThrow("tenSach"));
            sach.giaThue = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("giaThue")));
            sach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maLoai")));
            list.add(sach);
        }
        return list;
    }
}
