package com.example.thuctapimtech.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


import com.example.thuctapimtech.DAO.ThongKeDao;
import com.example.thuctapimtech.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class DoanhThuFragment extends Fragment {
    TextView tvDoanhThu;
    Button btnDoanhThu, btnTuNgay, btnDenNgay;
    EditText edTuNgay, edDenNgay;

    int mYear,mMonth,mDay;

    ThongKeDao thongKeDao;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.fragment_doanh_thu, container, false);

       tvDoanhThu = v.findViewById(R.id.tvDoanhThu);
       btnDoanhThu = v.findViewById(R.id.btnDoanhThu);
       btnTuNgay = v.findViewById(R.id.btnTuNgay);
       btnDenNgay = v.findViewById(R.id.btnDenNgay);
       edTuNgay = v.findViewById(R.id.edTuNgay);
       edDenNgay = v.findViewById(R.id.edDenNgay);
       thongKeDao = new ThongKeDao(getContext());

        DatePickerDialog.OnDateSetListener mDateTuNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                edTuNgay.setText(sdf.format(c.getTime()));
            }
        };

        DatePickerDialog.OnDateSetListener mDateDenNgay = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mYear = year;
                mMonth = month;
                mDay = dayOfMonth;
                GregorianCalendar c = new GregorianCalendar(mYear,mMonth,mDay);
                edDenNgay.setText(sdf.format(c.getTime()));
            }
        };


       btnTuNgay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateTuNgay,mYear,mMonth,mDay);
               d.show();
           }
       });

       btnDenNgay.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Calendar c = Calendar.getInstance();
               mYear = c.get(Calendar.YEAR);
               mMonth = c.get(Calendar.MONTH);
               mDay = c.get(Calendar.DAY_OF_MONTH);
               DatePickerDialog d = new DatePickerDialog(getActivity(),0,mDateDenNgay,mYear,mMonth,mDay);
               d.show();
           }
       });

       btnDoanhThu.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String tuNgay = edTuNgay.getText().toString();
               String denNgay = edDenNgay.getText().toString();
               if(!tuNgay.isEmpty() && !denNgay.isEmpty()){
                   Calendar cTuNgay = Calendar.getInstance();
                   Calendar cDenNgay = Calendar.getInstance();

                   cTuNgay.set(Integer.parseInt(tuNgay.substring(0,4)),Integer.parseInt(tuNgay.substring(5,7)),Integer.parseInt(tuNgay.substring(8)));
                   cDenNgay.set(Integer.parseInt(denNgay.substring(0,4)),Integer.parseInt(denNgay.substring(5,7)),Integer.parseInt(denNgay.substring(8)));

                   if(cDenNgay.after(cTuNgay)){
                       if(thongKeDao.getDoanhThu(tuNgay, denNgay) == 0){
                           Toast.makeText(getContext(), "Không có đơn hàng trong thời gian này !", Toast.LENGTH_SHORT).show();
                       } else {
                          tvDoanhThu.setText("Doanh thu:" + thongKeDao.getDoanhThu(tuNgay, denNgay) + "đ");
                       }
                   }else {
                       Toast.makeText(getContext(), "Thời gian không hợp lệ !", Toast.LENGTH_SHORT).show();
                   }
               }  else {
                   Toast.makeText(getContext(), "Vui lòng chọn thời gian !", Toast.LENGTH_SHORT).show();
               }
           }
       });


       return v;
    }

    public static DoanhThuFragment newInstance(){
        DoanhThuFragment fragment = new DoanhThuFragment();
        return fragment;
    }

}