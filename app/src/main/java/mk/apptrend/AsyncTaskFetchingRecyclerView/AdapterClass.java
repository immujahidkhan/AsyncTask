package mk.apptrend.AsyncTaskFetchingRecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Mujahid on 2/1/2018.
 */

public class AdapterClass extends RecyclerView.Adapter<AdapterClass.MyCardView> {

    Context context;
    ArrayList<ModelClass> list;

    public AdapterClass(Context context, ArrayList<ModelClass> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public MyCardView onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.cardview,parent,false);
        return new MyCardView(v);
    }

    @Override
    public void onBindViewHolder(MyCardView holder, int position) {
        ModelClass data = list.get(position);
        holder.id.setText(data.getId());
        holder.name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyCardView extends RecyclerView.ViewHolder{
        TextView id,name;
        public MyCardView(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.card_id);
            name = itemView.findViewById(R.id.card_name);
        }
    }
}
