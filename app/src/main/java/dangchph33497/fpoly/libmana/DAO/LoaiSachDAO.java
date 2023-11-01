package dangchph33497.fpoly.libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.LoaiSach;


public class LoaiSachDAO {
    private SQLiteDatabase sqLiteDatabase;
    DbHelper dbHelper;

    public LoaiSachDAO(Context context) {
        dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long AddLS(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("tenLoai",loaiSach.tenLoai);
        return sqLiteDatabase.insert("LoaiSach",null,values);
    }
    public int UpdateLS(LoaiSach loaiSach){
        ContentValues values = new ContentValues();
        values.put("tenLoai",loaiSach.tenLoai);
        String[] whereArgs = new String[]{String.valueOf(loaiSach.maLoai)};
        return sqLiteDatabase.update("LoaiSach",values,"maLoai=?",whereArgs);
    }
    public int DeleteLS(String id) {
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM Sach WHERE maLoai=?", new String[]{String.valueOf(id)});
        if (cursor.getCount() > 0) {
            return 0;
        }
        int check = sqLiteDatabase.delete("LoaiSach", "maLoai=?", new String[]{id});
        if (check == 0){
            return -1;
        }else {
            return 1;
        }
    }
    public List<LoaiSach> getAll(){
        String sql = "SELECT * FROM LoaiSach";
        return getData(sql);
    }
    public LoaiSach getID(String id){
        String sql = "SELECT * FROM LoaiSach WHERE maLoai=?";
        List<LoaiSach> list = getData(sql,id);
        return list.get(0);
    }
    private List<LoaiSach> getData(String sql,String...selectionArgs){
        List<LoaiSach> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            LoaiSach loaiSach = new LoaiSach();
            loaiSach.maLoai = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maLoai")));
            loaiSach.tenLoai = cursor.getString(cursor.getColumnIndexOrThrow("tenLoai"));
            list.add(loaiSach);
        }
        return list;
    }
}
