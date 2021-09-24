package DAM.lab01.compraventa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import java.util.List;

import DAM.lab01.compraventa.model.Categoria;

public class SeleccionarCategoriaActivity extends AppCompatActivity {

    private RecyclerView productosView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Categoria> listaCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar_categoria);


        productosView = (RecyclerView) findViewById(R.id.productosList);

        productosView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        productosView.setLayoutManager(layoutManager);

        Categoria c1 = new Categoria("MLA1512", "Agro");
        Categoria c2  =new Categoria("MLA1403", "Alimentos y Bebidas");
//        listaCategorias.add(c1);
//        listaCategorias.add(c2);

        mAdapter = new CategoryRecyclerAdapter(listaCategorias); //TODO cargar categorias de JSON
        productosView.setAdapter(mAdapter);
    }
}