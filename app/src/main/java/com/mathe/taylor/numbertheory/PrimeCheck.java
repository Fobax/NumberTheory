package com.mathe.taylor.numbertheory;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.text.TextWatcher;
import android.text.Editable;
import static java.lang.Math.*;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LucasLehmer.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LucasLehmer#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PrimeCheck extends Fragment {

    EditText chkNum;
    TextView dspResult;
    TextView dspResulttxt;
    Button btnChk;
    ArrayAdapter<String> adapter;
    EditText editText;
    ArrayList<String> itemList;

    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PrimeCheck() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LucasLehmer.
     */

    public static PrimeCheck newInstance(String param1, String param2) {
        PrimeCheck fragment = new PrimeCheck();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);


        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_prime_check, container, false);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] items={""};
        itemList = new ArrayList<>(Arrays.asList(items));
        adapter = new ArrayAdapter<>(getActivity(), R.layout.listitems, R.id.txtview, itemList);
        ListView listV=(ListView)getActivity().findViewById(R.id.listView);
        listV.setAdapter(adapter);



        chkNum = (EditText)getActivity().findViewById(R.id.checkNum);
        btnChk = (Button)getActivity().findViewById(R.id.btnCheck);
        dspResult = (TextView)getActivity().findViewById(R.id.dispResult);
        dspResulttxt = (TextView)getActivity().findViewById(R.id.dispResulttxt);

        // TODO Instead of using OnClickListener use the below code that will update as the user inters their text
      /*  chkNum.addTextChangedListener(new TextWatcher(){
            @Override
              public void onTextChanged(CharSequence s, int start, int before, int count) {
                  int length = chkNum.getText().length();
                    length = length / 2;
                  dspResult.setText(Double.toString(length));
               chkNum.setEms(length);
              }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void afterTextChanged(Editable s) {


            }


          });*/

        btnChk.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {



                long numb;
                String result;
                if (chkNum.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Enter a number, knucklehead.", Toast.LENGTH_SHORT).show();
                    return;
                }
                numb = Long.parseLong(chkNum.getText().toString());
               result = mListener.primecheck(numb);
                dspResulttxt.setText(result);

                dspResulttxt.setText("");

                String newItem = ( numb + " is " + result );
                // add new item to arraylist

                itemList.add(newItem);

                // notify listview of data changed
                adapter.notifyDataSetChanged();
                chkNum.setText("");
            }
            //TODO Remove items from list when it gets too full
        });
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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




    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
        String primecheck(long num);
    }
}

