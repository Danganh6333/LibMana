package dangchph33497.fpoly.libmana.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import dangchph33497.fpoly.libmana.Database.DbHelper;
import dangchph33497.fpoly.libmana.Model.PhieuMuon;


public class PhieuMuonDAO {
    private final SQLiteDatabase sqLiteDatabase;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public PhieuMuonDAO(Context context) {
        DbHelper dbHelper = new DbHelper(context);
        sqLiteDatabase = dbHelper.getWritableDatabase();
    }
    public long AddPhieuMuon(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("maTT",phieuMuon.maTT);
        values.put("maTV",phieuMuon.maTV);
        values.put("maSach",phieuMuon.maSach);
        values.put("tienThue",phieuMuon.tienThue);
        values.put("ngay", String.valueOf(phieuMuon.ngay));
        values.put("traSach",phieuMuon.traSach);
        return sqLiteDatabase.insert("PhieuMuon",null,values);
    }
    public int UpdatePhieuMuon(PhieuMuon phieuMuon){
        ContentValues values = new ContentValues();
        values.put("maTT",phieuMuon.maTT);
        values.put("maTV",phieuMuon.maTV);
        values.put("maSach",phieuMuon.maSach);
        values.put("tienThue",phieuMuon.tienThue);
        values.put("ngay", String.valueOf(phieuMuon.ngay));
        values.put("traSach",phieuMuon.traSach);
        return sqLiteDatabase.update("PhieuMuon",values,"maPM=?",new String[]{String.valueOf(phieuMuon.maPM)});
    }
    public int DeletePhieuMuon(String id){
        return sqLiteDatabase.delete("PhieuMuon","maPM=?",new String[]{id});
    }
    public List<PhieuMuon> getAll(){
        String sql = "SELECT * FROM PhieuMuon";
        return getData(sql);
    }
    public PhieuMuon getID(String id){
        String sql = "SELECT * FROM PhieuMuon WHERE maPM=?";
        List<PhieuMuon> list = getData(sql,id);
        return list.get(0);
    }
    private List<PhieuMuon> getData(String sql,String...selectionArgs){
        List<PhieuMuon> list = new ArrayList<>();

        Cursor cursor = sqLiteDatabase.rawQuery(sql,selectionArgs);
        while (cursor.moveToNext()){
            try {
                PhieuMuon phieuMuon = new PhieuMuon();
                phieuMuon.maPM = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maPM")));
                phieuMuon.maTT = cursor.getString(cursor.getColumnIndexOrThrow("maTT"));
                phieuMuon.maTV = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maTV")));
                phieuMuon.maSach = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("maSach")));
                phieuMuon.tienThue = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("tienThue")));
                phieuMuon.traSach = Integer.parseInt(cursor.getString(cursor.getColumnIndexOrThrow("traSach")));
                phieuMuon.ngay = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("ngay")));
                list.add(phieuMuon);
            }catch (Exception ex){
                ex.printStackTrace();
            }

        }
        return list;
    }
}
