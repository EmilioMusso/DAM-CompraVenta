package DAM.lab01.compraventa;

import static java.lang.String.*;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private Integer precio;
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
    private String direccionRetiro;
    private CheckBox boxTerminosCondiciones;
    private Boolean aceptaTerminosCondiciones;
    private Button btnPublicar;

    final String[] categoriasDisponibles = {"INDUMENTARIA", "ELECTRONICA", "ENTRETENIMIENTO", "JARDIN", "VEHICULOS", "JUGUETES"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_clasificados);

        txtTitulo = findViewById(R.id.txtTitulo);
        txtTitulo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });
        txtEmail = findViewById(R.id.txtEmail);
        txtEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    btnPublicar.setEnabled(camposCompletos());
                }
            }
        });

        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtPrecio = findViewById(R.id.txtPrecio);

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
        this.admiteDescuentoEnvio = false;
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
                txtDescuentoEnvioValue.setText(descuentoEnvio.toString()+"0%");
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
                    direccionRetiro = txtDireccionRetiroEnPersona.getText().toString();
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
        this.boxTerminosCondiciones.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean selected) {
                aceptaTerminosCondiciones = selected;
                btnPublicar.setEnabled(camposCompletos());
            }
        });

        this.btnPublicar = (Button) findViewById(R.id.btnPublicar);
        this.btnPublicar.setEnabled(camposCompletos());
        this.btnPublicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                titulo = valueOf(txtTitulo.getText());
                email = valueOf(txtEmail.getText());
                descripcion = valueOf(txtDescripcion.getText());
                precio = (txtPrecio.getText().toString().isEmpty() ? 0 : Integer.valueOf(txtPrecio.getText().toString()));
                if(admiteDescuentoEnvio==false)
                    descuentoEnvio = 0;

                try {
                    if(!camposInvalidos()) {
                        publicacion = new Publicacion(
                                titulo,
                                email,
                                descripcion,
                                precio,
                                categoria,
                                descuentoEnvio*10,
                                direccionRetiro);

                        Toast.makeText(getApplicationContext(), "Registro exitoso",Toast.LENGTH_SHORT).show();
                        clearTexts();
                    }
                } catch (CamposInvalidosException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private Boolean camposCompletos() {
        return (!txtTitulo.getText().toString().isEmpty() &&
                !txtEmail.getText().toString().isEmpty() &&
                ((boxRetiroEnPersona.isChecked() && !txtDireccionRetiroEnPersona.getText().toString().isEmpty()) || !boxRetiroEnPersona.isChecked()) &&
                boxTerminosCondiciones.isChecked()
        );
    }

    private Boolean camposInvalidos() throws CamposInvalidosException {
        if(tituloInvalido())
            throw new CamposInvalidosException("Actual: "+titulo); //El titulo solo puede contener letras (mayusculas o minusculas), números, comas, puntos o saltos de linea.");
        if(emailInvalido())
            throw new CamposInvalidosException("Por favor ingrese una direccion de mail valida.");
        if(descripcionInvalida())
            throw new CamposInvalidosException("La descripcion solo puede contener letras (mayusculas o minusculas), números, comas, puntos o saltos de linea.");
        if(precioInvalido())
            throw new CamposInvalidosException("Por favor ingrese un precio para el producto mayor a 0.");
        if(descuentoEnvioInvalido())
            throw new CamposInvalidosException("Por favor seleccione un porcentaje mayor a 0 o quite la opcion de ofrecer descuento de envio.");
        if(direccionRetiroInvalida())
            throw new CamposInvalidosException("La direccion de retiro solo puede contener letras (mayusculas o minusculas), números, comas, puntos o saltos de linea.");
        else return false;
    }

    private boolean direccionRetiroInvalida() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9,.\n]$");
        Matcher matcher = pattern.matcher(direccionRetiro);
        return !matcher.matches();
    }

    private boolean descripcionInvalida() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9,.\n]$");
        Matcher matcher = pattern.matcher(descripcion);
        return !matcher.matches();
    }

    private boolean tituloInvalido() {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9,.\n]$");
        Matcher matcher = pattern.matcher(titulo);
        return !matcher.matches();
    }

    private boolean precioInvalido() {
        return (precio==0);
    }

    private boolean emailInvalido() {
        Pattern pattern = Pattern.compile("^@[a-z]{3}$");
        Matcher matcher = pattern.matcher(email);
        return !matcher.matches();
    }

    private boolean descuentoEnvioInvalido() {
        return (admiteDescuentoEnvio && sliderDescuentoEnvio.getProgress()==0);
    }

    private void clearTexts() {
        txtTitulo.setText("");
        txtEmail.setText("");
        txtPrecio.setText("");
        txtDescripcion.setText("");
        txtDireccionRetiroEnPersona.setText("");
        txtDescuentoEnvioValue.setText("");
        boxRetiroEnPersona.setChecked(false);
        boxTerminosCondiciones.setChecked(false);
        switchDescuentoEnvio.setChecked(false);
        sliderDescuentoEnvio.setProgress(0);
    }
}