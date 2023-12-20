package com.example.mynotesbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ThirdFragment extends Fragment {
    Button update;
    EditText name, author;
    Spinner style;
    com.example.mynotesbook.DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View frag = inflater.inflate(R.layout.fragment3, container, false);
        return frag;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name3);
        author = view.findViewById(R.id.author3);
        style = view.findViewById(R.id.style3);

        DB = new com.example.mynotesbook.DBHelper(getActivity());

        List<String> genres = new ArrayList<>();
        genres.add(getResources().getString(R.string.GenreHint));
        genres.add("Новелла");
        genres.add("Ода");
        genres.add("Поэма");
        genres.add("Роман");
        genres.add("Комедия");

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.custom_spinner_item, genres) {
            @Override
            public boolean isEnabled(int position) {
                // Запрещаем выбирать первый элемент
                return position != 0;
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                // Если это первый элемент, то скрываем его
                if (position == 0) {
                    view.setVisibility(View.GONE);
                } else {
                    view.setVisibility(View.VISIBLE);
                }
                return view;
            }
        };

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        style.setAdapter(adapter);

        update = view.findViewById(R.id.btnUpdate3);
                update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = author.getText().toString();
                String dobTXT = style.getSelectedItem().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if (checkupdatedata == true)
                    Toast.makeText(getActivity(), "Данные изменены", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Данные не изменены", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
