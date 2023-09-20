package com.example.retrofitexa_1.Retrofit;

import com.example.retrofitexa_1.model.Alumno;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AlumnoApi {
    @GET("api/alumnos/listado")
    Call<List<Alumno>>findAll();
    @POST("api/alumnos/agregar")
    Call<Alumno> GuardarAlumno(@Body Alumno alumno);
    @PUT("api/alumnos/{id}")
    Call<Alumno> ActualizarAlumno(@Path("id") Long id, @Body Alumno alumno);
    @DELETE("api/alumnos/{id}")
    Call<Void> eliminarAlumno(@Path("id") Long id);

}
