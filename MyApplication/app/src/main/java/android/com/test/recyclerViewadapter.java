package android.com.test;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Administrator on 2018/5/22.
 */

public class recyclerViewadapter extends RecyclerView.Adapter {
    private List<DataBean> lists;
    private Context context;

    public recyclerViewadapter(List<DataBean> lists, Context context) {
        this.lists = lists;
        this.context = context;
    }

    class myholder extends RecyclerView.ViewHolder{

        private TextView tv1,tv2;
        public myholder(View itemView) {
            super(itemView);
            tv1= (TextView) itemView.findViewById(R.id.tv1);
            tv2= (TextView) itemView.findViewById(R.id.tv2);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        myholder holder =new myholder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Log.d("TAG", "onBindViewHolder: "+lists.get(position).getAutor());
        ((myholder)holder).tv1.setText(lists.get(position).getAutor());
        ((myholder)holder).tv2.setText(lists.get(position).getContent());

    }

    @Override
    public int getItemCount() {
        return lists.size();
    }
}
