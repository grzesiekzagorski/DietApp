package com.example.grzesiek87.dietapp.plan;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.grzesiek87.dietapp.R;
import com.example.grzesiek87.dietapp.activities.AddToDishActivity;

import java.util.List;

/**
 * Adapter wyświetlający wszystkie składniki. Wybrany przez nas produkt będzie wysłany
 * przy pomocy BroadcastManagera i odebrany w aktywnosci.
 */
public class AddToDishAdapter extends RecyclerView.Adapter<AddToDishAdapter.ViewHolder>{

    private List<Product> mProducts;
    private Context context;

    @NonNull
    @Override
    public AddToDishAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.product_item2,parent,false);
        AddToDishAdapter.ViewHolder viewHolder = new AddToDishAdapter.ViewHolder(v);
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AddToDishAdapter.ViewHolder holder, final int position) {
        holder.calories.setText(Double.toString(mProducts.get(position).getCalories()));
        holder.carbo.setText(Double.toString(mProducts.get(position).getCarbohydrates()));
        holder.proteins.setText(Double.toString(mProducts.get(position).getProteins()));
        holder.fats.setText(Double.toString(mProducts.get(position).getFats()));
        holder.productname.setText(mProducts.get(position).getName());
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String send = Integer.toString(position+1);
                Intent intent = new Intent("custom-message");
                intent.putExtra("item",send);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
        });
    }


    public void setmProducts(List<Product> mProducts) {
        this.mProducts = mProducts;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        if(mProducts != null) {return mProducts.size(); }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView calories,carbo,proteins,productname,fats;
        Button edit,add;
        public ViewHolder(View itemView) {
            super(itemView);
            calories = (TextView)itemView.findViewById(R.id.productCalories);
            carbo = (TextView)itemView.findViewById(R.id.productCarbo);
            proteins = (TextView)itemView.findViewById(R.id.productProteins);
            productname = (TextView)itemView.findViewById(R.id.productName);
            fats = (TextView)itemView.findViewById(R.id.productFats);
            edit = (Button) itemView.findViewById(R.id.editButton);
            add = (Button)itemView.findViewById(R.id.addToDishButton);
        }
    }
}
