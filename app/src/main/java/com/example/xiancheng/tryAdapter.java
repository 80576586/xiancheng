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
    private final int contentView=0;
    private final int dateView=1;
    private static ImageView imageView;
    private Context context;
    private List<Map<String, Object>> list;

    public tryAdapter(Context context, List<Map<String, Object>> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public int getItemCount () {
        return list.size();
    }

    @Override
    public int getItemViewType(int i){
        if (list.get(i).size()!=1){
            return contentView;
        }
        else
            return dateView;

    }



    //@NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view;

            if (i == contentView) {
                view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
                return new contentViewHolder(view);
            } else {
               view=LayoutInflater.from(context).inflate(R.layout.item_date,viewGroup,false);
               return new dateViewHolder(view);
            }



    }

        @Override
        public void onBindViewHolder (@NonNull RecyclerView.ViewHolder holder,int i) {
            if (holder instanceof dateViewHolder) {
                dateViewHolder viewHolder = (dateViewHolder) holder;
                viewHolder.date1.setText(list.get(i).get("format1").toString());

            } else if (holder instanceof contentViewHolder) {
                    contentViewHolder viewHolder = (contentViewHolder) holder;

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
                    }

        class contentViewHolder extends RecyclerView.ViewHolder {
            TextView text1;
            TextView textView2;
            ImageView imageView;
            LinearLayout linearLayout;

            public contentViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(R.id.tv1);
                textView2 = itemView.findViewById(R.id.tv2);
                imageView = itemView.findViewById(R.id.iv);
                linearLayout = itemView.findViewById(R.id.ln);

            }
        }

        class dateViewHolder extends RecyclerView.ViewHolder {
                TextView date1;

            public dateViewHolder( @NonNull View itemView) {
                super(itemView);
                date1 = itemView.findViewById(R.id.date1);
            }


        }

}
