package com.example.mynotesbook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SecondFragment extends Fragment {
    Button insert;
    EditText name, author;
    Spinner style;
    com.example.mynotesbook.DBHelper DB;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment2, container, false);
        return fragview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = view.findViewById(R.id.name2);
        author = view.findViewById(R.id.author2);
        style = view.findViewById(R.id.style2);

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

        insert = view.findViewById(R.id.btnInsert2);
        insert.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String nameTXT = name.getText().toString();
            String authorTXT = author.getText().toString();
            String styleTXT = style.getSelectedItem().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT, authorTXT, styleTXT);
                if (checkinsertdata == true)
                    Toast.makeText(getActivity(), "Книга добавлена", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getActivity(), "Книга не добавлена", Toast.LENGTH_SHORT).show();
        }
    });

}}
