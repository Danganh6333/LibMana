package dangchph33497.fpoly.libmana.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    static final String name ="PNLib";
    static final int version =1;

    public DbHelper(@Nullable Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Tạo bảng ThuThu
        String createTableThuThu =
                "CREATE TABLE ThuThu (\n" +
                "    maTT    TEXT PRIMARY KEY,\n" +
                "    hoTen   TEXT NOT NULL,\n" +
                "    matKhau TEXT NOT NULL\n" +
                ");\n";
        sqLiteDatabase.execSQL(createTableThuThu);

        //Tạo bảng ThanhVien
        String createTableThanhVien =
                "CREATE TABLE ThanhVien (\n" +
                "    maTV    INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    hoTen   TEXT    NOT NULL,\n" +
                "    namSinh TEXT    NOT NULL\n" +
                ");\n";
        sqLiteDatabase.execSQL(createTableThanhVien);

        String insertTableThanhVien = "INSERT INTO ThanhVien(hoTen,namSinh)VALUES" +
                "('Vũ Thiện Khiêm',2002),('Thục Trọng Hà',2000),('Hàng Hữu Vĩnh',2003)," +
                "('Ca Quang Khải',2010),('Mạnh Hằng Anh',1999),('Ngọ Phương Dung',1980)," +
                "('Đèo Minh Xuân',2009),('Nghiêm Thiện Mỹ',2001),('Mạnh Hoài Bắc',1973)," +
                "('Hướng Quang Hà',2007),('Lèng Minh Hiếu',1990),('Lèng Minh Hiếu',2000)";
        sqLiteDatabase.execSQL(insertTableThanhVien);

        //Tạo bảng LoaiSach
        String createTableLoaiSach =
                "CREATE TABLE LoaiSach (\n" +
                "    maLoai  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenLoai TEXT    NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(createTableLoaiSach);

        String insertTableLoaiSach = "INSERT INTO LoaiSach " +
                "(tenLoai\n" +
                ")VALUES('Khoa Học'),('Chính Trị'),('CNTT'),('Triết Học')," +
                "('Tình Cảm'),('Kinh Dị'),('Bí Ẩn'),('Khoa Học Viễn Tưởng')";
        sqLiteDatabase.execSQL(insertTableLoaiSach);

        //Tạo bảng Sach
        String createTableSach =
                "CREATE TABLE Sach (\n" +
                "    maSach  INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    tenSach TEXT    NOT NULL,\n" +
                "    giaThue INTEGER NOT NULL,\n" +
                "    maLoai  INTEGER REFERENCES LoaiSach (maLoai) \n" +
                ");\n";
        sqLiteDatabase.execSQL(createTableSach);

        String insertTableSach = "INSERT INTO Sach " +
                "(tenSach,giaThue,maLoai)\n" +
                "VALUES('Chemistry, Tenth Edition by Raymond Chang',150000,1)," +
                "('Being and Nothingness by Jean-Paul Sartre',90000,3)," +
                "('Code Complete: A Practical Handbook of Software Construction by Steve McConnell',50000,6)," +
                "('The Republic by Plato',80000,8)," +
                "('Beyond Good and Evil by Nietzsche Friedrich',100000,7)," +
                "('1984 by George Orwen',900000,1)," +
                "('Atlas of Human Anatomy by Frank H. Netter',50000,5)," +
                "('Hyperspace A Scientific Odyssey Through Parallel Universes, Time Warps, and the 10th Dimension by Michio Kaku',70000,4)," +
                "('Practical Electronics for Inventors, 4th Edition by Paul Scherz, Simon Monk',60000,4),"+
                "('A Short History of Nearly Everything by Bill Bryson',50000,2)" ;
        sqLiteDatabase.execSQL(insertTableSach);

        //Tạo bảng PhieuMuon
        String createTablePhieuMuon =
                "CREATE TABLE PhieuMuon (\n" +
                "    maPM     INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "    maTT     TEXT    REFERENCES ThuThu (maTT),\n" +
                "    maTV     INTEGER REFERENCES ThanhVien (maTV),\n" +
                "    maSach   INTEGER REFERENCES Sach (maSach),\n" +
                "    tienThue INTEGER NOT NULL,\n" +
                "    ngay     DATE    NOT NULL,\n" +
                "    traSach  INTEGER NOT NULL\n" +
                ");";
        sqLiteDatabase.execSQL(createTablePhieuMuon);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTableThuThu = "DROP TABLE IF EXISTS ThuThu;";
        sqLiteDatabase.execSQL(dropTableThuThu);
        String dropTableThanhVien ="DROP TABLE IF EXISTS ThanhVien";
        sqLiteDatabase.execSQL(dropTableThanhVien);
        String dropTableLoaiSach = "DROP TABLE IF EXISTS LoaiSach";
        sqLiteDatabase.execSQL(dropTableLoaiSach);
        String dropTableSach = "DROP TABLE IF EXISTS Sach";
        sqLiteDatabase.execSQL(dropTableSach);
        String dropTablePhieuMuon = "DROP TABLE IF EXISTS PhieuMuon";
        sqLiteDatabase.execSQL(dropTablePhieuMuon);
        onCreate(sqLiteDatabase);
    }
}
