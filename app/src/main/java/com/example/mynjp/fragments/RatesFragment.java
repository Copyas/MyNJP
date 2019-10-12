package com.example.mynjp.fragments;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mynjp.R;
import com.example.mynjp.util.RatesCalc;

/**
 * A simple {@link Fragment} subclass.
 */
public class RatesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    public RatesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_rates, container, false);
        // Bind
        final TextView berat = view.findViewById(R.id.weightInput);
        final TextView harga1 = view.findViewById(R.id.harga1text);
        final TextView harga2 = view.findViewById(R.id.harga2text);
        final TextView beratText = view.findViewById(R.id.weightText);

        //  Button Check
        Button checkButton = view.findViewById(R.id.checkButton);
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener != null){
                    int lokasi[] = {5,4,3,2,1};
                    Spinner origin = view.findViewById(R.id.originInput);
                    Spinner destination = view.findViewById(R.id.destinationInput);
                    int originIndex = lokasi[origin.getSelectedItemPosition()];
                    int destinationIndex = lokasi[destination.getSelectedItemPosition()];

                    //  Check dulu
                    if(!TextUtils.isEmpty(berat.getText().toString()) && Float.parseFloat(berat.getText().toString()) > 0) {
                        RatesCalc ratesCalc = new RatesCalc(originIndex, destinationIndex, Float.parseFloat(berat.getText().toString()));
                        harga1.setText("Rp. " + ratesCalc.getRatesYES() + " -- 1 Hari Sampai");
                        harga2.setText("Rp. " + ratesCalc.getRatesREG() + " -- 2-4 Hari Sampai");
                        beratText.setText(berat.getText().toString()+" KG");
                    }else if(!TextUtils.isEmpty(berat.getText().toString()) && Float.parseFloat(berat.getText().toString()) <= 0) {
                        Toast.makeText(getActivity(), "Berat harus lebih dari 0", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(getActivity(), "Masukkan berat barang", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        //  Button add
        ImageView addButton = view.findViewById(R.id.addbutton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                berat.setText(Float.parseFloat(berat.getText().toString())+0.5+"");
            }
        });

        //  Button Decrease
        ImageView decreaseButton = view.findViewById(R.id.decreaseButton);
        decreaseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Float.parseFloat(berat.getText().toString()) <= 0){
                    berat.setText("0.0");
                }else {
                    berat.setText(Float.parseFloat(berat.getText().toString()) - 0.5 + "");
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onCheckButtonClicked();
    }

}
