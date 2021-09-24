package DAM.lab01.compraventa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import DAM.lab01.compraventa.model.Categoria;
import DAM.lab01.compraventa.model.Publicacion;

public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CategoryViewHolder> {

    private List<Categoria> myDataSet;
    public CategoryRecyclerAdapter(List<Categoria> mDataSet) {
        myDataSet = mDataSet;
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView labelCategoria;
        View view;

        public CategoryViewHolder(@NonNull View fila) {
            super(fila);
            view = fila;
            labelCategoria = fila.findViewById(R.id.labelCategoria);
        }
    }

    @NonNull
    @Override
    public CategoryRecyclerAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fila_cat, viewGroup, false);
        CategoryViewHolder vh = new CategoryViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder categoryViewHolder, int position) {
        Categoria c1 = myDataSet.get(position);

        //TODO definir colores especificos

        categoryViewHolder.labelCategoria.setText(c1.getName());
    }

    @Override
    public int getItemCount() { return myDataSet.size(); }
}
