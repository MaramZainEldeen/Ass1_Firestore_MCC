package com.example.lect_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.InformationViewHolder> {

       Context context ;
       ArrayList<User> list_information ;
// لكي يتم ارسال الlist  من خلاله
    public RecyclerViewAdapter(Context c, ArrayList<User> list_info) {
        this.context = c ;
        this.list_information = list_info;
    }

    @NonNull
    @Override
    //يتم انشاؤها اول ما يتم انشاء العنصر على الشاشة(يتم استدعاؤها مرة واحدة فقط)
    public InformationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // لعرض التصميم الخاص ب المنتجات التي تم انشاؤه
        View view_item =LayoutInflater.from(context).inflate(R.layout.item_user,parent ,false);
       // InformationViewHolder info = new InformationViewHolder(view_item);
        return new InformationViewHolder(view_item);
    }
// تستخدم لربط البيانات المتواجدة داخل ال (list) و ما بين ال (ViewHolder) التي قمت بتخزينه مسبقا
    @Override
    public void onBindViewHolder(@NonNull InformationViewHolder holder, int position) {
        // جلب المنتج المتواجد في هذا الموقع (position)
        User info_user =  list_information.get(position);
        // جلب العناصر المتواجدة داخل هذا (view holder )
        // جلب

        // جلب الاسم و الرقم و العنوان من الكلاس الذي تم انشاؤه أسفل
        holder.tv_name.setText(info_user.getName());
        holder.tv_num.setText(info_user.getNumber());
       holder.tv_address.setText(info_user.getAdress());
    }

    @Override
    public int getItemCount() {
        return list_information.size();
    }

    // holder class for RecyclerView
    class InformationViewHolder extends RecyclerView.ViewHolder{
        // تعريف العناصر المتواجدة داخل التصميم ب استخدام ال id
     TextView tv_name ;
     TextView tv_num;
     TextView tv_address;

        public InformationViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name_user);
            tv_num = itemView.findViewById(R.id.number_user);
            tv_address =itemView.findViewById(R.id.address_user);
        }
    }

}
