package com.example.grzesiek87.dietapp.plan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.grzesiek87.dietapp.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Adapter do RecyclerView w ViewPager
 */
public class SlideRecyclerViewAdapter extends RecyclerView.Adapter<SlideRecyclerViewAdapter.ViewHolder>{
    private Context mContext;
    private List<Product> mProducts;


    public SlideRecyclerViewAdapter(Context context, List<Product> products){
        mContext = context;
        mProducts = products;

    }

    public SlideRecyclerViewAdapter(Context context){
        mContext = context;
    }

    public void setmProducts(ArrayList<Product> mProducts) {
        this.mProducts = mProducts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.delete.setTag(mProducts.get(position));
        holder.calories.setText(Double.toString(mProducts.get(position).getCalories()));
        holder.carbo.setText(Double.toString(mProducts.get(position).getCarbohydrates()));
        holder.proteins.setText(Double.toString(mProducts.get(position).getProteins()));
        holder.fats.setText(Double.toString(mProducts.get(position).getFats()));
        holder.productname.setText(mProducts.get(position).getName());

        holder.delete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Product deleted = (Product) v.getTag();
                int index = mProducts.indexOf(deleted);
                mProducts.remove(index);
                notifyItemRemoved(index);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (mProducts != null) {
            return mProducts.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView calories,carbo,proteins,productname,price,fats;
        Button edit,delete;

        public ViewHolder(View itemView) {
            super(itemView);
            calories = (TextView)itemView.findViewById(R.id.productCalories);
            carbo = (TextView)itemView.findViewById(R.id.productCarbo);
            proteins = (TextView)itemView.findViewById(R.id.productProteins);
            productname = (TextView)itemView.findViewById(R.id.productName);
            fats = (TextView)itemView.findViewById(R.id.productFats);
            edit = (Button) itemView.findViewById(R.id.editButton);
            delete = (Button)itemView.findViewById(R.id.deleteButton);


        }
    }
}