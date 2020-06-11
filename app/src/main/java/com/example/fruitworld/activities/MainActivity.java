package com.example.fruitworld.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fruitworld.R;
import com.example.fruitworld.adapters.AdaptadorFruta;
import com.example.fruitworld.models.Fruta;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    // ListView, GridView i Adaptadors
    private ListView lista;
    private GridView gridView;
    private AdaptadorFruta miAdaptadorList;
    private AdaptadorFruta miAdaptadorGrid;


    // Lista del nostre objecte
    private List<Fruta> Fruita;


    //Items al menu dopcions
    private MenuItem itemListView;
    private MenuItem itemGridView;

    //Varaiables
    private int contador=0;
    private final int SWICH_LIST_VIEW = 0;
    private  final int SWICH_GRID_VIEW =1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // afegim el icono a l app
        this.ForcarIconos();

        //omplim la llista d' items
        this.Fruita = getTotaFruita();

        // instancciem el listview
      this.lista = (ListView) findViewById(R.id.listview);
      this.gridView=(GridView) findViewById(R.id.gridview_main);

      //adjunten els eventos clic a les llistes
        this.lista.setOnItemClickListener(this);
        this.gridView.setOnItemClickListener(this);


        // enllacem el nostre adaptador personalitzat
        // estes linies sobrescriuen l les linies d abans
       this.miAdaptadorList= new AdaptadorFruta(this,R.layout.llista_view, Fruita);
       this.miAdaptadorGrid= new AdaptadorFruta(this,R.layout.grid_view, Fruita);
        lista.setAdapter(miAdaptadorList);
        gridView.setAdapter(miAdaptadorGrid);

        //registrem els contextos menu
        registerForContextMenu(this.lista);
        registerForContextMenu(this.gridView);

       // Toast.makeText(this, "Holaaa esto es un mensage", Toast.LENGTH_LONG).show();

    }

    // metodde per afegir fruites a la llista
    private List<Fruta> getTotaFruita(){
       List<Fruta> list = new ArrayList<Fruta>();
      list.add(new Fruta("Platano","Gran Canaries",R.mipmap.ic_platano));
      list.add(new Fruta("Manzana", "Andalucia", R.mipmap.ic_masana));
      list.add(new Fruta("Cereza", "Catalunya", R.mipmap.ic_cirera));
      list.add(new Fruta("Pera", "Murcia", R.mipmap.ic_pera));
      list.add(new Fruta("Naranja", "Valencia", R.mipmap.ic_taronja));
        list.add(new Fruta("Platano","Gran Canaries",R.mipmap.ic_platano));
        list.add(new Fruta("Manzana", "Andalucia", R.mipmap.ic_masana));
        list.add(new Fruta("Cereza", "Catalunya", R.mipmap.ic_cirera));
        list.add(new Fruta("Pera", "Murcia", R.mipmap.ic_pera));
        list.add(new Fruta("Naranja", "Valencia", R.mipmap.ic_taronja));
    // retornem llista
      return list;
    }

    // metode per forcar els iconos
    private  void ForcarIconos(){
        getSupportActionBar().setIcon(R.mipmap.ic_miicono);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

    }
    // afegim menu a la barra accio
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflem el layout amb els nostre layout
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_opcions, menu);
        // despues creem la referencia als botons
        this.itemListView= menu.findItem(R.id.listviewfru);
        this.itemGridView = menu.findItem(R.id.gridview_menu);
        return  true;
    }

    // metode per als botons
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.afegir:
                this.anadirFruta( new Fruta("Afegit nº "+(++contador),"unknow", R.mipmap.ic_nova_fruita));
                return true;
            case R.id.listviewfru:
                this.opcioItem(this.SWICH_LIST_VIEW);
                return  true;
            case R.id.gridview_menu:
                this.opcioItem(this.SWICH_GRID_VIEW);

                return  true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
        // metode per crear menu contextual
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater= getMenuInflater(); // creem el menu
        //creem el titol del menu
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) menuInfo;
        menu.setHeaderTitle(this.Fruita.get(info.position).getNombre()); // obtenim el nom
        inflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()){
            case R.id.eliminar_id:
                this.eliminarFruta(info.position);
                return true;
            default:
                return super.onContextItemSelected(item);
        }

    }
    // metode per canviar les vistes

    private void opcioItem (int opcio){
        if (opcio == SWICH_LIST_VIEW){

            this.lista.setVisibility(View.VISIBLE);
            this.gridView.setVisibility(View.INVISIBLE);
            this.itemListView.setVisible(false);
            this.itemGridView.setVisible(true);
           }
        else if (opcio == SWICH_GRID_VIEW){

            this.gridView.setVisibility(View.VISIBLE);
            this.lista.setVisibility(View.INVISIBLE);
            this.itemGridView.setVisible(false);
            this.itemListView.setVisible(true);
               }
           }





    // metode per saber quin item cliquem
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        this.clicFruita(Fruita.get(position));
    }
    // metode per mostrar un misatge de l item
    private  void clicFruita(Fruta Fruita){
        //diferenciem entre els fruits coeguts
        if(Fruita.getOrigen().equals("unknow")){
             Toast.makeText(this, "Perdon, no tengo informacion de " + Fruita.getNombre(), Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "La mejor fruta de "+Fruita.getOrigen()+ " es "+ Fruita.getNombre(), Toast.LENGTH_LONG).show();

        }
    }

    // metode per afegir
    private void  anadirFruta(Fruta fruites){
        this.Fruita.add(fruites); // afegim fruita
        // avisem al adapter que actualitzem la llista
        this.miAdaptadorList.notifyDataSetChanged();
      this.miAdaptadorGrid.notifyDataSetChanged();

    }
    // metode per eliminar
    private  void eliminarFruta (int posicion){
        this.Fruita.remove(posicion); // eliminen item de la llista
        // avisem al adapter que actulitzem la llista
        this.miAdaptadorList.notifyDataSetChanged();
        this.miAdaptadorGrid.notifyDataSetChanged();
    }

}
