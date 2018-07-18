package com.example.grzesiek87.dietapp.QrScanFood;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grzesiek87.dietapp.R;

import java.util.List;

public class QrFoodAdapter extends RecyclerView.Adapter<QrFoodAdapter.ViewHolder>  {


    private List<QrFood> QrFoods;

    public QrFoodAdapter() {

    }

    @NonNull
    @Override
    public QrFoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 1. utwórz inflater (narzędzie do wczytywania widoków stworzonych w XML)
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        // 2. wczytaj widok jednego wiersza
        View rowView = inflater.inflate(R.layout.qr_list_element, parent, false);

        // 3.  obiekt ViewHolder, który będzie trzymać odwołania
        // do elementów jednego wiersza
        QrFoodAdapter.ViewHolder viewHolder = new QrFoodAdapter.ViewHolder(rowView);

        // 4. zwróć nowo utworzony obiekt
        return viewHolder;
    }

    /**
     * Ustaw produkt.
     * @param QrFoods lista produktów
     */
    public void setReviews(List<QrFood> QrFoods) {
        this.QrFoods = QrFoods;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull QrFoodAdapter.ViewHolder holder, int position) {
        //bindowanie danych do konkretnego wiersza
        //holder.id.setText(Integer.toString(QrFoods.get(position).id));
        holder.name.setText(QrFoods.get(position).name);
        holder.weight.setText(QrFoods.get(position).weight);
        holder.energy.setText(QrFoods.get(position).energy);
        //TODO dodać wyświetlanie ID
    }

    @Override
    public int getItemCount() {
        // w momencie tworzenia adaptera nie otrzymujemy recenzjie
        // dlatego reviews może być nullem
        if (QrFoods != null) {
            return QrFoods.size();
        }

        return 0;
    }

    /**
     * Pozyskanie produktu z danej pozycji.
     * Lista produktów może być nullem ponieważ produkty
     * nie są przekazywane przez konstruktor.
     *
     * @param index pozycja produktu
     * @return recenzja lub null
     */
    public QrFood getQrFood(int index) {
        return (QrFoods != null) ? QrFoods.get(index) : null;
    }

    /**
     * Holder dla widoków.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        //widgety z qr_list_element.xml
        public TextView id;
        public TextView name;
        public TextView weight;
        public TextView energy;


        public ViewHolder(View itemView) {
            super(itemView);
            //wyszukanie widgetów
//                id = itemView.findViewById(R.id.id);
            name = itemView.findViewById(R.id.textName);
            weight = itemView.findViewById(R.id.textWeight);
            energy = itemView.findViewById(R.id.textEnergy);

        }
    }
}

