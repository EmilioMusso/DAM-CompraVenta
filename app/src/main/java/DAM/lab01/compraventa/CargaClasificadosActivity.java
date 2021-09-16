package DAM.lab01.compraventa;

import static java.lang.String.*;

import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import DAM.lab01.compraventa.exceptions.CamposInvalidosException;
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
    private Boolean admiteDescuentoEnvio;
    private SeekBar sliderDescuentoEnvio;
    private LinearLayout blockDescuentoEnvio;
    private TextView txtDescuentoEnvioValue;
    private Integer descuentoEnvio;
    private CheckBox boxRetiroEnPersona;
    private Boolean admiteRetiroEnPersona;
    private TextView txtDireccionRetiroEnPersona;
    private CharSequence direccionRetiro;
    private CheckBox boxTerminosCondiciones;
    private Boolean aceptaTerminosCondiciones;
    private Button btnPublicar;

    final String[] categoriasDisponibles = {"INDUMENTARIA", "ELECTRONICA", "ENTRETENIMIENTO", "JARDIN", "VEHICULOS", "JUGUETES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_clasificados);

        txtTitulo = findViewById(R.id.txtTitulo);
        titulo = valueOf(txtTitulo.getText());
        txtTitulo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });
        txtEmail = findViewById(R.id.txtEmail);
        email = valueOf(txtEmail.getText());
        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });
        txtDescripcion = findViewById(R.id.txtDescripcion);
        descripcion = valueOf(txtDescripcion.getText());
        txtPrecio = findViewById(R.id.txtPrecio);
        precio = valueOf(txtPrecio.getText());
        txtPrecio.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });

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

        this.switchDescuentoEnvio = (Switch) findViewById(R.id.switchDescuentoEnvio);
        this.sliderDescuentoEnvio = (SeekBar) findViewById(R.id.sliderCostoEnvio);
        this.txtDescuentoEnvioValue = (TextView) findViewById(R.id.txtDescuentoEnvioValue);
        this.blockDescuentoEnvio = (LinearLayout) findViewById(R.id.blockDescuentoEnvio);
        this.sliderDescuentoEnvio.setVisibility(View.GONE);
        this.txtDescuentoEnvioValue.setVisibility(View.GONE);
        this.blockDescuentoEnvio.setVisibility(View.GONE);
        this.descuentoEnvio = 0;
        this.sliderDescuentoEnvio.setProgress(descuentoEnvio);
        this.txtDescuentoEnvioValue.setText(descuentoEnvio.toString());
        this.switchDescuentoEnvio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                admiteDescuentoEnvio = selected;
                if(selected) {
                    sliderDescuentoEnvio.setVisibility(View.VISIBLE);
                    txtDescuentoEnvioValue.setVisibility(View.VISIBLE);
                    blockDescuentoEnvio.setVisibility(View.VISIBLE);
                    sliderDescuentoEnvio.setProgress(descuentoEnvio);
                    txtDescuentoEnvioValue.setText(descuentoEnvio.toString()+"0");
                } else {
                    sliderDescuentoEnvio.setVisibility(View.GONE);
                    txtDescuentoEnvioValue.setVisibility(View.GONE);
                    blockDescuentoEnvio.setVisibility(View.GONE);
                }
            }
        });

        this.sliderDescuentoEnvio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int value, boolean b) {
                //Toast.makeText(getApplicationContext(), "VALOR SLIDER: "+value,Toast.LENGTH_SHORT).show();
                descuentoEnvio = value;
                sliderDescuentoEnvio.setProgress(descuentoEnvio);
                txtDescuentoEnvioValue.setText(descuentoEnvio.toString()+"0");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        this.boxRetiroEnPersona = (CheckBox) findViewById(R.id.boxRetiroEnPersona);
        this.txtDireccionRetiroEnPersona = (TextView) findViewById(R.id.txtDireccionRetiroEnPersona);
        txtDireccionRetiroEnPersona.setVisibility(View.GONE);
        this.boxRetiroEnPersona.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                admiteRetiroEnPersona = selected;
                if(selected) {
                    txtDireccionRetiroEnPersona.setVisibility(View.VISIBLE);
                    direccionRetiro = txtDireccionRetiroEnPersona.getText();
                    btnPublicar.setEnabled(camposCompletos());
                } else {
                    txtDireccionRetiroEnPersona.setVisibility(View.GONE);
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });

        txtDireccionRetiroEnPersona.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });

        this.boxTerminosCondiciones = (CheckBox) findViewById(R.id.boxTerminosCondiciones);
        this.btnPublicar = (Button) findViewById(R.id.btnPublicar);
        this.btnPublicar.setEnabled(camposCompletos());
        this.boxTerminosCondiciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                aceptaTerminosCondiciones = selected;
                btnPublicar.setEnabled(camposCompletos());
            }
        });

        /*
        publicacion = new Publicacion(
                titulo,
                email,
                descripcion,
                precio,
                categoria,
                descuentoEnvio*10,
                direccionRetiro,
                );
        */
    }

    private Boolean camposCompletos() {
        return (!txtTitulo.getText().toString().isEmpty() &&
                !txtEmail.getText().toString().isEmpty() &&
                !txtPrecio.getText().toString().isEmpty() &&
                ((boxRetiroEnPersona.isChecked() && !txtDireccionRetiroEnPersona.getText().toString().isEmpty()) || !boxRetiroEnPersona.isChecked()) &&
                boxTerminosCondiciones.isChecked()
        );
    }

    private Boolean camposInvalidos() throws CamposInvalidosException {
        if(emailInvalido())
            throw new CamposInvalidosException("Ingrese una direccion de mail valida.");
        else return true;
    }

    private boolean emailInvalido() {
        return true;
    }
}