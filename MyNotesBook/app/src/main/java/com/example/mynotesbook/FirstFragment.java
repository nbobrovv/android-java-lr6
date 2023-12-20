package com.example.mynotesbook;

import android.database.Cursor;
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
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

public class FirstFragment extends Fragment {
    Button viewB;
    EditText name, author, style;
    com.example.mynotesbook.DBHelper DB;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragview = inflater.inflate(R.layout.fragment1, container, false);
        return fragview;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewB = view.findViewById(R.id.btnView1);
        DB = new com.example.mynotesbook.DBHelper(getActivity());
        viewB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if (res.getCount() == 0) {
                    Toast.makeText(getActivity(), "Книги отсутствуют", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()) {
                    buffer.append("Наименование: " + res.getString(0) + "\n");
                    buffer.append("Автор: " + res.getString(1) + "\n");
                    buffer.append("Жанр: " + res.getString(2) + "\n\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setCancelable(true);
                builder.setTitle("Библиотека");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

    }
}
