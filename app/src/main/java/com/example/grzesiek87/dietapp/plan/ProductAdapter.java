package com.example.grzesiek87.dietapp.plan;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.grzesiek87.dietapp.R;

import java.util.List;

/**
 * Adapter do wyświetlania wszystkich produktów w bazie produktów
 */
public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{

    private List<Product> mProducts;
    DishProductJoinViewModel dishProductJoinViewModel;

    @NonNull
    @Override
    public ProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.product_item,parent,false);
        ProductAdapter.ViewHolder viewHolder = new ProductAdapter.ViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.calories.setText(Double.toString(mProducts.get(position).getCalories()));
        holder.carbo.setText(Double.toString(mProducts.get(position).getCarbohydrates()));
        holder.proteins.setText(Double.toString(mProducts.get(position).getProteins()));
        holder.fats.setText(Double.toString(mProducts.get(position).getFats()));
        holder.productname.setText(mProducts.get(position).getName());
    }

    /**
     * Ustawienie naszych produktów i powiadomienie recyclerview o zmianach
     * @param mProducts Lista produktów
     */
    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
        notifyDataSetChanged();
    }

    public List<Product> getmProducts() {
        return mProducts;
    }

    public Product getmProduct(int index){
        return mProducts.get(index);
    }

    @Override
    public int getItemCount() {
        if(mProducts != null) {return mProducts.size(); }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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
