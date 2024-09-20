package com.example.thuctapimtech.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thuctapimtech.Adapter.SanPhamKHAdapter;
import com.example.thuctapimtech.DAO.DaoSanPham;
import com.example.thuctapimtech.Model.IClickItemRCV;
import com.example.thuctapimtech.Model.SanPham;
import com.example.thuctapimtech.R;
import java.util.ArrayList;

public class Home_KH_Fragment extends Fragment {

       RecyclerView rcv_spKH;
       ArrayList<SanPham> lstSP;

       DaoSanPham spDao;
       SanPhamKHAdapter adapter;


       private ISendDataListiner mISendDataListiner;




    public interface ISendDataListiner{
           void senData(String tenSP, String tenHang, int gia);
       }


       public Home_KH_Fragment(){

       }

       public static Home_KH_Fragment newInstance(){
           Home_KH_Fragment fragment = new Home_KH_Fragment();
           return fragment;
       }


//    @Override
//    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//       rcv_spKH.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
//           @Override
//           public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//               FragmentManager manager = getActivity().getSupportFragmentManager();
//               manager.beginTransaction().replace(R.id.frame_container, CTSP_kh.newInstance()).commit();
//
//
//               return false;
//           }
//
//           @Override
//           public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//           }
//
//           @Override
//           public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//           }
//       });
//
//    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
          View v = inflater.inflate(R.layout.fragment_home__k_h, container, false);
          rcv_spKH = v.findViewById(R.id.rcv_sanPham_kh);
          lstSP = new ArrayList<>();
          spDao = new DaoSanPham(getContext());


          loadData();


        return v;
    }

    public void loadData(){
        lstSP =(ArrayList<SanPham>) spDao.getAll();
       adapter = new SanPhamKHAdapter(getContext(), lstSP, new IClickItemRCV() {
           @Override
           public void iclickItem(RecyclerView.ViewHolder viewHolder, int position, int type) {

           }
       });

        rcv_spKH.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rcv_spKH.setAdapter(adapter);


    }

}