package com.example.sqlitedatabase;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {

    private ArrayList<StudentData> studentDataList;
    private Context context;

    public DataAdapter(ArrayList<StudentData> studentDataList, Context context) {
        this.studentDataList = studentDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate your custom layout (card_view)
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        StudentData student = studentDataList.get(position);
        holder.name.setText(student.getName());
        holder.qualification.setText(student.getQualification());
        holder.dob.setText(student.getDob());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateRecord.class);

                // below we are passing all our values.
                i.putExtra("name", student.getName());
                i.putExtra("dob", student.getDob());
                i.putExtra("qualification",student.getQualification());


                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return studentDataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name, qualification, dob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.et1);           // Name
            qualification = itemView.findViewById(R.id.et2);  // Qualification
            dob = itemView.findViewById(R.id.et3);            // DOB
        }
    }
}
