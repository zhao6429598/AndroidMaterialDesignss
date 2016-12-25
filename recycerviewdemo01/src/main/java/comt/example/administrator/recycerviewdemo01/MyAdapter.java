package comt.example.administrator.recycerviewdemo01;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Author: zwf(zhaoweifeng@1000phone.com)
 * Date  : 2016-12-25
 * Time  : 21:12
 * Project: AndroidMaterialDesign
 * Descrite:
 *
 * TODO 1.创建ViewHolder
 *
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<String> data;
    private OnChlidClickListener chlidClickListener;
    private RecyclerView recyclerView;

    public MyAdapter(Context context, List<String> data) {
        this.context = context;
        this.data = data;
    }


    public  void setChlidClickListener(OnChlidClickListener chlidClickListener){
        this.chlidClickListener = chlidClickListener;
    }

    /**
     * 返回ViewHolder 创建ViewHolder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        view.setOnClickListener(this);

        return viewHolder;
    }


    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    /**
     * 绑定数据
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String s = data.get(position);
        if (s != null) {
            holder.text.setText(s);
        }

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        this.recyclerView = null;
    }

    /**
     * 返回数据的长度
     * @return
     */
    @Override
    public int getItemCount() {
        return data!= null?data.size():0;
    }

    @Override
    public void onClick(View v) {

         if (recyclerView != null  && chlidClickListener != null) {
             //点击的位置
             int childAdapterPosition = recyclerView.getChildAdapterPosition(v);
             long itemId = getItemId(childAdapterPosition);
             String s = data.get(childAdapterPosition);
             chlidClickListener.onChlidClick(childAdapterPosition,itemId,s);
         }
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{


        private  TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            text =  (TextView) itemView.findViewById(R.id.text);
        }

    }

    /**
     * 监听
     */
    public  interface  OnChlidClickListener{

        void onChlidClick(int position,long id,String data);

    }



    public  void remove(String str){
        int i = data.indexOf(str); //获取数据的位置
        if (i >= 0){
            remove(i);
//            notifyItemRemoved(i); //指定删除的位置
        }
     //   notifyDataSetChanged(); //刷新和ListView相同
    }

    public void remove(int position) {

        data.remove(position);
        notifyItemRemoved(position);
    }

    //尾部添加
    public void  add(String str){
        add(data.size(),str);

    }
    //指定位置添加
    public void  add(int position,String str){

        if (position < data.size())
        {
            data.add(position,str);
        }else {
            data.add(str);
        }
        notifyItemInserted(position);
    }


    public void  change(int position){
        notifyItemChanged(position);
    }
}
