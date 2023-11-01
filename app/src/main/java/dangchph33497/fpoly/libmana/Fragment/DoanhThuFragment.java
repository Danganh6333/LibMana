package dangchph33497.fpoly.libmana.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import dangchph33497.fpoly.libmana.DAO.ThongKeDAO;
import dangchph33497.fpoly.libmana.R;


public class DoanhThuFragment extends Fragment {
    Button btnTuNgay,btnDenNgay,btnDoanhThu;
    EditText edTuNgay,edDenNgay;
    TextView tvDoanhThu;
    int nYear,nMonth,nDay;
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View  view = inflater.inflate(R.layout.fragment_doanh_thu, container, false);
        edTuNgay = view.findViewById(R.id.edTuNgay);
        edDenNgay = view.findViewById(R.id.edDenNgay);
        edTuNgay.setEnabled(false);
        edDenNgay.setEnabled(false);
        tvDoanhThu = view.findViewById(R.id.tvDoanhThu);
        btnDenNgay = view.findViewById(R.id.btnDenNgay);
        btnDoanhThu = view.findViewById(R.id.btnDoanhThu);
        btnTuNgay = view.findViewById(R.id.btnTuNgay);
        btnTuNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                nYear = calendar.get(Calendar.YEAR);
                nMonth = calendar.get(Calendar.MONTH);
                nDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),0,nDateTuNgay,nYear,nMonth,nDay);
                datePickerDialog.show();
            }
        });
        btnDenNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                nYear = calendar.get(Calendar.YEAR);
                nMonth = calendar.get(Calendar.MONTH);
                nDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),0,nDateDenNgay,nYear,nMonth,nDay);
                datePickerDialog.show();
            }
        });
        btnDoanhThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tuNgay = edTuNgay.getText().toString();
                String denNgay = edDenNgay.getText().toString();
                ThongKeDAO thongKeDAO = new ThongKeDAO(getActivity());
                tvDoanhThu.setText("Doanh Thu: "+thongKeDAO.getDoanhThu(tuNgay,denNgay)+"VNĐ");
            }
        });
        return view;
    }
    DatePickerDialog.OnDateSetListener nDateTuNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            nYear = year;
            nMonth = month;
            nDay = dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(nYear,nMonth,nDay);
            edTuNgay.setText(dateFormat.format(calendar.getTime()));
        }
    };
    DatePickerDialog.OnDateSetListener nDateDenNgay = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            nYear = year;
            nMonth = month;
            nDay = dayOfMonth;
            GregorianCalendar calendar = new GregorianCalendar(nYear,nMonth,nDay);
            edDenNgay.setText(dateFormat.format(calendar.getTime()));
        }
    };
}