package com.example.mynotesbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FourthFragment extends Fragment {
    Button delete;
    com.example.mynotesbook.DBHelper DB;
    EditText name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment4, container, false);
        return fragview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name4);
        delete = view.findViewById(R.id.btnDelete4);
        DB = new com.example.mynotesbook.DBHelper(getActivity());
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkudeletedata = DB.deletedata(nameTXT);
                if (checkudeletedata == true)
                    Toast.makeText(getActivity(), "Книга удалена", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Книга не удалена", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
