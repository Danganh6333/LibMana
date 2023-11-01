package dangchph33497.fpoly.libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.ThuThu;

public class ThuThuDAO {
    private SQLiteDatabase sqLiteDatabase;

    public ThuThuDAO(Context context){
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long AddThuThu(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.maTT);
        values.put("hoTen",thuThu.hoTen);
        values.put("matKhau",thuThu.matKhau);

        return sqLiteDatabase.insert("ThuThu",null,values);

    }
    public int UpdateThuThu(ThuThu thuThu){
        ContentValues values = new ContentValues();
        values.put("maTT",thuThu.maTT);
        values.put("matKhau",thuThu.matKhau);

        return sqLiteDatabase.update("ThuThu",values,"maTT=?",new String[]{thuThu.maTT});
    }
    public int DeleteThuThu(String id){
        return sqLiteDatabase.delete("ThuThu","maTT=?",new String[]{id});
    }
    public List<ThuThu> getData(String sql,String...selectionArgs){
        List<ThuThu> list = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            ThuThu thuThu = new ThuThu();
            thuThu.maTT = cursor.getString(cursor.getColumnIndexOrThrow("maTT"));
            thuThu.hoTen = cursor.getString(cursor.getColumnIndexOrThrow("hoTen"));
            thuThu.matKhau = cursor.getString(cursor.getColumnIndexOrThrow("matKhau"));

            list.add(thuThu);
        }
        return list;
    }
    private  List<ThuThu> getAll(){
        String sql = "SELECT*FROM ThuThu";
        return getData(sql);
    }
    public ThuThu getID(String id){
        String sql = "SELECT * FROM ThuThu WHERE maTT=?";
        List<ThuThu> list = getData(sql,id);
        return list.get(0);
    }
    public int checkLogin(String id,String password){
        String sql = "SELECT * FROM ThuThu WHERE maTT=? AND matKhau=?";
        List<ThuThu> list = getData(sql,id,password);
        if(list.size()==0)
            return -1;
        return 1;
    }
}
