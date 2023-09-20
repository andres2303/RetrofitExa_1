package com.example.retrofitexa_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.retrofitexa_1.Retrofit.AlumnoApi;
import com.example.retrofitexa_1.Retrofit.RetrofitService;
import com.example.retrofitexa_1.model.Alumno;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         initializeComponents();

    }

    private void initializeComponents() {
        TextInputEditText inputEditTextName = findViewById(R.id.input_nombre);
        TextInputEditText inputEditApellido = findViewById(R.id.input_apellido);
        TextInputEditText inputEditTelefono = findViewById(R.id.input_telefono);
        MaterialButton buttonSave = findViewById(R.id.btn_save);

        RetrofitService retrofitService = new RetrofitService();
        AlumnoApi alumnoApi = retrofitService.getRetrofit().create(AlumnoApi.class);

        buttonSave.setOnClickListener(view -> {
          String name = String.valueOf(inputEditTextName.getText());
          String apellidos = String.valueOf(inputEditApellido.getText());
          String telefono = String.valueOf(inputEditTelefono.getText());

            Alumno alumno =  new Alumno();
            alumno.setNombre(name);
            alumno.setApellido(apellidos);
            alumno.setTelefono(telefono);

            alumnoApi.GuardarAlumno(alumno)
                    .enqueue(new Callback<Alumno>() {
                        @Override
                        public void onResponse(Call<Alumno> call, Response<Alumno> response) {
                            Toast.makeText(MainActivity.this, "Save successful", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onFailure(Call<Alumno> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Save failed!!", Toast.LENGTH_SHORT).show();
                            Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE,"Error ocurred",t);
                        }
                    });
        });
    }
}