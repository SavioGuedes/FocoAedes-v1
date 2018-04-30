package com.example.savio.focoaedes.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.savio.focoaedes.Detalhe_Ocorrencia_Activity;
import com.example.savio.focoaedes.R;
import com.example.savio.focoaedes.fragments.Lista_OcorrenciasFragment;
import com.example.savio.focoaedes.model.Ocorrencia;

import java.util.Collections;
import java.util.List;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.Visao>{

    private LayoutInflater inflater;
    Lista_OcorrenciasFragment listaOcorrencias;
    Context context;

    List<Ocorrencia> ocorrencias = Collections.emptyList();

    public ListaAdapter(Context context, List<Ocorrencia> ocorrencias){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.ocorrencias = ocorrencias;
    }

    @Override
    public Visao onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.lista_layout, parent, false);
        Visao visao = new Visao(view);

        return visao;
    }

    @Override
    public void onBindViewHolder(Visao visao, int position) {

        Ocorrencia atual = ocorrencias.get(position);

        if (atual.getFoto() != null){

            //decodifica iamgem e manda pra lista
            byte[] decodedString = Base64.decode(atual.getFoto(), Base64.DEFAULT);
            visao.foto.setImageBitmap(BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length));
        }

        visao.id.setText(atual.getId());
        visao.titulo.setText(atual.getTitulo());
        visao.data.setText(atual.getData());
        visao.endereco.setText(atual.getRua() +" - "+ atual.getBairro());

    }

    @Override
    public int getItemCount() {

        return ocorrencias.size();
    }

    class Visao extends RecyclerView.ViewHolder{

        TextView id;
        ImageView foto;
        TextView titulo;
        TextView data;
        TextView endereco;

        public Visao(final View itemView) {
            super(itemView);

            id = (TextView) itemView.findViewById(R.id.lista_id);
            foto = (ImageView) itemView.findViewById(R.id.lista_foto);
            titulo = (TextView) itemView.findViewById(R.id.lista_titulo);
            data = (TextView) itemView.findViewById(R.id.lista_data);
            endereco = (TextView) itemView.findViewById(R.id.lista_endereco);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {

                    listaOcorrencias = new Lista_OcorrenciasFragment();
                    listaOcorrencias.deletarOcorrencia(id.getText().toString(), context);

                    ocorrencias.remove(getAdapterPosition());
                    notifyDataSetChanged();

                    return false;
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, Detalhe_Ocorrencia_Activity.class);
                    i.putExtra("id_ocorrencia", id.getText());
                    context.startActivity(i);
                }
            });

        }
    }
}
