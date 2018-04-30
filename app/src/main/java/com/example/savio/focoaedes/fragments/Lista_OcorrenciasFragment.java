package com.example.savio.focoaedes.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savio.focoaedes.MainActivity;
import com.example.savio.focoaedes.R;
import com.example.savio.focoaedes.adapters.ListaAdapter;
import com.example.savio.focoaedes.base.BaseLocal;
import com.example.savio.focoaedes.base.Service;
import com.example.savio.focoaedes.model.Ocorrencia;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Lista_OcorrenciasFragment extends Fragment {

    public MainActivity activity;

    BaseLocal baseLocal;

    public TextView qtd_ocorrencias;
    RecyclerView lista_ocorrencias;
    ListaAdapter adapter;

    public Lista_OcorrenciasFragment() {}


//---------------Ciclo de vida----------------------------------------------------------------------//


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        this.activity = (MainActivity) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_lista_ocorrencias, container, false);

        baseLocal = new BaseLocal(getContext());

        qtd_ocorrencias = (TextView) view.findViewById(R.id.lista_qtds);

        lista_ocorrencias = (RecyclerView) view.findViewById(R.id.lista_ocorrencias);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        listarTodasOcorrencias();

        ((MainActivity) getActivity()).mostraFloatingActionButton();
    }


//--------------Meus metodos para facilitar minha vida----------------------------------------------//


    void listarTodasOcorrencias(){

        final List<Ocorrencia> ocorrencia = new ArrayList<>();

        //implementa a interface Service e faz a requisição dos dados
        Service service = Service.retrofit.create(Service.class);

        //Call<Catalogos> requisicao = service.listaCatalogos();
        Call<List<Ocorrencia>> requisicao = service.getOcorrencias();
        
        //executar de forma assincrona
        requisicao.enqueue(new Callback<List<Ocorrencia>>() {

            //metodos de respostas
            @Override
            public void onResponse(Call<List<Ocorrencia>> call, Response<List<Ocorrencia>> response) {

                //condição se os dados foram capturados
                if(!response.isSuccessful()){

                    Log.i("LISTA", "Erro: " + "Erro: " + response.code());
                }
                else{

                    //captura o objeto JSON e converte
                    List<Ocorrencia> catalogo = response.body();

                    for(Ocorrencia oco : catalogo){

                        Log.i("LISTA", ""+oco.getTitulo() );

                        ocorrencia.add(oco);

                    }

                    adapter = new ListaAdapter(activity, ocorrencia);

                    lista_ocorrencias.setAdapter(adapter);
                    lista_ocorrencias.setLayoutManager(new LinearLayoutManager(getActivity()));

                    qtd_ocorrencias.setText(String.valueOf(ocorrencia.size()));

                }

            }

            @Override
            public void onFailure(Call<List<Ocorrencia>> call, Throwable t) {

                Log.i("LISTA", "Falha: " + t.getMessage());
            }

        });

    }

    //deleta a ocorrencia
    public static void deletarOcorrencia(String id, final Context context){

        //implementa a interface Service e faz a requisição dos dados
        Service service = Service.retrofit.create(Service.class);

        Call<Void> call = service.deleteOcorrencias(id);
        call.enqueue(new Callback<Void>() {

            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if(!response.isSuccessful()){

                    Log.i("RESULTADO", "Erro: " + response.code());
                }
                else{

                    Toast.makeText(context,"Deletado com sucesso", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("RESULTADO", "Falha: " + t.getMessage());
            }
        });
    }


//--------------Fim de codigo-----------------------------------------------------------------------//

}
