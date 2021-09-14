package DAM.lab01.compraventa;

import static java.lang.String.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import DAM.lab01.compraventa.model.Publicacion;

public class CargaClasificadosActivity extends AppCompatActivity {

    private Publicacion publicacion;
    private String titulo;
    private EditText txtTitulo;
    private String email;
    private EditText txtEmail;
    private String descripcion;
    private EditText txtDescripcion;
    private String precio;
    private EditText txtPrecio;
    private String categoria;
    private Spinner spinnerCategoria;
    private Switch switchDescuentoEnvio;
    private SeekBar sliderDescuentoEnvio;
    private Integer descuentoEnvio;
    private CheckBox boxRetiroEnPersona;
    private CheckBox boxTerminosCondiciones;
    private Button btnPublicar;

    final String[] categoriasDisponibles = {"INDUMENTARIA", "ELECTRONICA", "ENTRETENIMIENTO", "JARDIN", "VEHICULOS", "JUGUETES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_clasificados);


        txtTitulo = findViewById(R.id.txtTitulo);
        titulo = valueOf(txtTitulo.getText());
        txtEmail = findViewById(R.id.txtEmail);
        email = valueOf(txtEmail.getText());
        txtDescripcion = findViewById(R.id.txtDescripcion);
        descripcion = valueOf(txtDescripcion.getText());
        txtPrecio = findViewById(R.id.txtPrecio);
        precio = valueOf(txtPrecio.getText());

        this.spinnerCategoria = (Spinner) findViewById(R.id.spinnerCategoria);
        ArrayAdapter<CharSequence> spinnerAdapter = new ArrayAdapter<CharSequence>(this,
                R.layout.support_simple_spinner_dropdown_item,
                categoriasDisponibles);
        this.spinnerCategoria.setAdapter(spinnerAdapter);
        this.spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                categoria = (String) spinnerAdapter.getItem(position);
                //Toast.makeText(getApplicationContext(), "SELECCIONO: "+categoria,Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        this.switchDescuentoEnvio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchDescuentoEnvio.isChecked()) {
                    //sliderDescuentoEnvio.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "TODO OPEN SLIDER",Toast.LENGTH_LONG).show();
                } else {
                    //sliderDescuentoEnvio.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "TODO CERRAR SLIDER",Toast.LENGTH_LONG).show();
                }
            }
        });

        /*
        this.sliderDescuentoEnvio.setTranslationX(0);
        this.descuentoEnvio = 0;
        this.sliderDescuentoEnvio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                Toast.makeText(getApplicationContext(), "VALOR SLIDER: "+value,Toast.LENGTH_LONG).show();
                descuentoEnvio = value;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });*/



        /*publicacion = new Publicacion(
                titulo,
                email,
                descripcion,
                precio,
                categoria,
                descuentoEnvio,
                null,null);*/

    }
}