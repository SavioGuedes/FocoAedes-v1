package com.example.savio.focoaedes;

import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savio.focoaedes.base.Service;
import com.example.savio.focoaedes.model.Ocorrencia;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Detalhe_Ocorrencia_Activity extends AppCompatActivity {

    static ImageView foto_detalhe;
    static TextView titulo_detalhe;
    static TextView data_detalhe;
    static TextView endereco_detalhe;
    static TextView descricao_detalhe;

    String id_ocorrencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detalhe_ocorrencia_);

        foto_detalhe = (ImageView) findViewById(R.id.detalhe_imagem);
        titulo_detalhe = (TextView) findViewById(R.id.detalhe_titulo);
        data_detalhe = (TextView) findViewById(R.id.detalhe_data);
        endereco_detalhe = (TextView) findViewById(R.id.detalhe_endereco);
        descricao_detalhe = (TextView) findViewById(R.id.detalhe_descricao);

        Bundle extras = getIntent().getExtras();
        id_ocorrencia = extras.getString("id_ocorrencia");

        verOcorrencia(id_ocorrencia);


    }



//--------------Meus metodos para facilitar minha vida----------------------------------------------//


    //deleta a ocorrencia
    public static void verOcorrencia(String id){

        //implementa a interface Service e faz a requisição dos dados
        Service service = Service.retrofit.create(Service.class);

        Call<Ocorrencia> call = service.showOcorrencias(id);

        call.enqueue(new Callback<Ocorrencia>() {
            @Override
            public void onResponse(Call<Ocorrencia> call, Response<Ocorrencia> response) {

                if(!response.isSuccessful()){

                    Log.i("RESULTADO", "Erro: " + response.code());
                }
                else{

                    Ocorrencia oco = response.body();

                    //decodifica iamgem e manda pra lista
                    byte[] decodedString = Base64.decode(oco.getFoto(), Base64.DEFAULT);

                    foto_detalhe.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
                    titulo_detalhe.setText(oco.getTitulo());
                    data_detalhe.setText(oco.getData());
                    endereco_detalhe.setText(oco.getRua() + " - " + oco.getBairro());
                    descricao_detalhe.setText(oco.getDescricao());
                }
            }

            @Override
            public void onFailure(Call<Ocorrencia> call, Throwable t) {

                Log.i("RESULTADO", "Falha: " + t.getMessage());
            }
        });


    }
}
