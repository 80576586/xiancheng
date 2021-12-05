package com.example.xiancheng;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class tryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int contentView=1;
    private final int dateView=2;
    private static ImageView imageView;
    private Context context;
    private List<Map<String, Object>> list;

    public tryAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }




    @Override
    public int getItemViewType(int i){

//       if(list.size()>12)
            return contentView;
//        else
//            return dateView;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //View view = LayoutInflater.from(context).inflate(R.layout.item,viewGroup,false);
            View view;

            if (i == contentView) {
                view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
            } else {
                view = LayoutInflater.from(context).inflate(R.layout.item_date, viewGroup, false);
            }
        return new contentViewHolder(view);


    }

        @Override
        public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder,int i){
//            if (holder instanceof dateViewHolder) {
//                dateViewHolder viewHolder = (dateViewHolder) holder;
//                viewHolder.text11.setText(list.get(i).get("title").toString());
//                viewHolder.textView21.setText(list.get(i).get("hint").toString());
//                Glide.with(context)
//                        .load(list.get(i).get("images"))
//                        .placeholder(R.drawable.loading)
//                        .into(viewHolder.imageView1);
//                String id = list.get(i).get("id").toString();
//                viewHolder.linearLayout1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent;
//                        intent = new Intent(context, MainActivity2.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putString("id", id);
//                        intent.putExtras(bundle);
//                        context.startActivity(intent);
//                    }
//                });
//
//            }

//            else if (holder instanceof contentViewHolder) {
            if (holder instanceof contentViewHolder) {
                contentViewHolder viewHolder = (contentViewHolder) holder;
          //      for ( i=1;i<=list.size();i++) {
          //          if (i != 6 &&(i + 1) % 7 == 0) {

          //              viewHolder.date1.setText(list.get(i).get("format1").toString());
         //           } else {

                        viewHolder.textView2.setText(list.get(i).get("hint").toString());
                        viewHolder.text1.setText(list.get(i).get("title").toString());

                        Glide.with(context)
                                .load(list.get(i).get("images"))
                                .placeholder(R.drawable.loading)
                                .into(viewHolder.imageView);
                        String id = list.get(i).get("id").toString();

                        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent;
                                intent = new Intent(context, MainActivity2.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("id", id);
                                intent.putExtras(bundle);
                                context.startActivity(intent);
                            }
                        });
                    }
         //       }
        //    }
        }


        @Override
        public int getItemCount () {
            return list.size();
        }


        class contentViewHolder extends RecyclerView.ViewHolder {
            TextView text1;
            TextView textView2;
            ImageView imageView;
            LinearLayout linearLayout;
            TextView date1;
            public contentViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(R.id.tv1);
                textView2 = itemView.findViewById(R.id.tv2);
                imageView = itemView.findViewById(R.id.iv);
                linearLayout = itemView.findViewById(R.id.ln);
                date1 = itemView.findViewById(R.id.date1);
            }
        }

        class dateViewHolder extends RecyclerView.ViewHolder {
            TextView text11;
            TextView textView21;
            ImageView imageView1;
            LinearLayout linearLayout1;


            public dateViewHolder(View view, @NonNull View itemView) {
                super(itemView);
                text11 = itemView.findViewById(R.id.tv11);
                textView21 = itemView.findViewById(R.id.tv21);
                imageView1 = itemView.findViewById(R.id.iv1);
                linearLayout1 = itemView.findViewById(R.id.ln1);
            }
        }

}