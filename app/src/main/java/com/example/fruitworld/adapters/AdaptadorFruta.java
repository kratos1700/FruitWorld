package com.example.fruitworld.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.fruitworld.R;
import com.example.fruitworld.models.Fruta;

import java.util.List;

// implementem els metodes de BaseAdapter
public class AdaptadorFruta  extends BaseAdapter {
    // creem els atributs que necesitem
    private Context contexte; // on anira guardat este adaptador
    private  int layaut; // pasarem la referencia del layaut
    private List<Fruta> list; // li pasarem una llista de fruites

    // Constructor
    public AdaptadorFruta(Context contexte, int layaut, List<Fruta> list) {
        this.contexte = contexte;
        this.layaut = layaut;
        this.list = list;
    }

    //metodes a sobre escriure
    @Override
    public int getCount() {
        return this.list.size(); //retornem el tameny de la llista de fruita
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);// retornem la posicio del item
    }

    @Override
    public long getItemId(int id) {
        return id;
    } // retornem la id del item  NO S USA MOLT ESTE METODE

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // PATRON VIEWHOLDER ------------------------------
        ViweHolder holder;

        if (convertView == null){
            // aqui preparem el layaut per poder dibuixar dins
            // agafem el view per defecte, l unfem i el retornem amb el nostre layout
            // PART IMPORTANT A IMPLEMENTAR, a la rxiu R, estan totes les referencies
            convertView = LayoutInflater.from(contexte).inflate(layaut,null);
            holder = new ViweHolder();
            // referenciem l element a modificar i el rellenem
            // a l objecte holder li guardem la referencia de la id del txtView
            holder.nomTexView = (TextView) convertView.findViewById(R.id.nombrefruta);
            holder.Origen = (TextView) convertView.findViewById(R.id.origenfruta);
            holder.iconoFruta = (ImageView) convertView.findViewById(R.id.iconofruta);
            convertView.setTag(holder);
        } else{
            holder=(ViweHolder) convertView.getTag();
        }

        // ara li pasarem les nostres dades depenent de la posicio
        //final Fruta currentFruit= (Fruta) getItem(position);
        final Fruta currentFruit= (Fruta) getItem(position);;


        holder.nomTexView.setText(currentFruit.getNombre());
        holder.Origen.setText(currentFruit.getOrigen());
        holder.iconoFruta.setImageResource(currentFruit.getIcono());
        // retornem la vista
        return  convertView;



    }
    static class ViweHolder{
        private TextView nomTexView; // per cada element que tinguessim s'hauria de crear un atribut
        private TextView Origen;
        private ImageView iconoFruta;
    }
}
