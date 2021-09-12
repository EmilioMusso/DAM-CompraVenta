package DAM.lab01.compraventa;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import DAM.lab01.compraventa.model.Publicacion;

public class PublicacionesAdapter extends ArrayAdapter<Publicacion> {

    public PublicacionesAdapter(Context context, List<Publicacion> datos) {
        super(context, 0, datos);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {



        /// INI PATRON RECICLADO:
        LayoutInflater inflater = LayoutInflater.from(this.getContext());
        View fila = convertView;
        if(fila==null) {
            fila = inflater.inflate(R.layout.fila_publicacion, parent, false);
        }
        /// FIN PATRON RECICLADO.

        /// INI PATRON HOLDER: (mantiene punteros a c/u de los widgets)

        /// FIN PATRON HOLDER.


        //return super.getView(position, convertView, parent);
        return fila;
    }
}
