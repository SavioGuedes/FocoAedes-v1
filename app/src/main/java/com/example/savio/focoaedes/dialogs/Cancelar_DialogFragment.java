package com.example.savio.focoaedes.dialogs;


import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.savio.focoaedes.R;

public class Cancelar_DialogFragment extends DialogFragment {

    TextView descartar, manter;

    public Cancelar_DialogFragment() {}

    public static Cancelar_DialogFragment newInstance() {

        return new Cancelar_DialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cancelar_dialog, container, false);

        descartar = (TextView) view.findViewById(R.id.dialog_cancelar_descartar);
        manter = (TextView) view.findViewById(R.id.dialog_cancelar_manter);

//--------------Listener dos "botoes"---------------------------------------------------------------//


        descartar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
                getFragmentManager().popBackStack();

            }
        });

        manter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dismiss();
            }
        });


//--------------Fim de codigo-----------------------------------------------------------------------//


        return view;
    }

}
