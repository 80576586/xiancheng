package com.example.xiancheng;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.BreakIterator;
import java.util.List;
import java.util.Map;

public class LinearAdapter extends RecyclerView.Adapter <LinearAdapter.ViewHolder>{

    private Context context;
    private List<Map<String, Object>> list1;
    private final int comment_line=0;
    private final int long_comment=1;
    private final int short_comment=2;

    public LinearAdapter(Context context, List<Map<String, Object>> list1) {
        this.context = context;
        this.list1 = list1;
    }
    @Override
    public int getItemViewType(int position) {
        if (list1.get(position).get("author")!=null)
            return long_comment;
        else
            return comment_line;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view;
        if (i==comment_line)
        {
            view=LayoutInflater.from(context).inflate(R.layout.item_comment,parent,false);
            return new TwoHolder(view);
        }
        else
            view=LayoutInflater.from(context).inflate(R.layout.item2,parent,false);
            return new OneHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull LinearAdapter.ViewHolder holder, int i) {
        if (holder instanceof OneHolder){
            OneHolder viewHolder= (OneHolder) holder;
            viewHolder.textViewAuthor.setText(list1.get(i).get("author").toString());
            viewHolder.textViewComment.setText(list1.get(i).get("content").toString());
            String author=list1.get(i).get("avatar").toString();
            Glide.with(context)
                    .load(list1.get(i).get("avatar"))
                    .placeholder(R.drawable.loading1)
                    .into(((OneHolder) holder).imageView1);
        }
        else {
            TwoHolder viewHolder= (TwoHolder) holder;

            String short_comment=list1.get(i).get("short_comments").toString();
            int a=Integer.parseInt(short_comment);
            if (a>0)
                viewHolder.tvComment.setText(short_comment+"条短评");
            else
                viewHolder.nonComment.setText("还没有评论");
        }
    }

    @Override
    public int getItemCount() {
        return list1.size();
    }

    class OneHolder extends LinearAdapter.ViewHolder{
        TextView textViewAuthor;
        TextView textViewComment;
        ImageView imageView1;

        public OneHolder(View itemView){
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.author);
            textViewComment = itemView.findViewById(R.id.comments);
            imageView1=itemView.findViewById(R.id.picture);
        }
    }

    class TwoHolder extends LinearAdapter.ViewHolder{
        TextView tvComment;
        TextView nonComment;

        public TwoHolder(View itemView){
            super(itemView);
            tvComment = itemView.findViewById(R.id.tvComment);
            nonComment=itemView.findViewById(R.id.nonComment);

        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
