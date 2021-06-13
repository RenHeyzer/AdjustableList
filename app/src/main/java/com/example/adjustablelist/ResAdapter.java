package com.example.adjustablelist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.util.ArrayList;
import java.util.List;

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ResViewHolder> {
    public List<ResModel> list = new ArrayList<>();
    LayoutInflater inflater;
    OnItemClickListener oniTemClisck;

//    private EditText etText;
//    private EditText etText2;
//    private ResModel resModel;

    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener onItemClickListener;

    public void updateData(ResModel resModel, int position){
        list.set(position,resModel);
        notifyDataSetChanged();
    }

    public void addData(ResModel resModel){
        list.add(resModel);
        notifyDataSetChanged();
    }
    public void deleteData(int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    public ResAdapter( Context context, MainActivity activity){
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ResViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_res, parent, false);
        return new ResViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResAdapter.ResViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ResViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;
      //  EditText etText, etText2;

        public ResViewHolder(View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_title);
            txtDescription = itemView.findViewById(R.id.txt_description);
           // etText = itemView.findViewById(R.id.et_text);
          //  etText2 = itemView.findViewById(R.id.et_text2);

        }

        public void bind(ResModel resModel) {
            txtTitle.setText(resModel.getTitle());
            txtDescription.setText(resModel.getDescription());
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    AlertDialog dialog = new AlertDialog.Builder(itemView.getContext()).create();
                    dialog.setTitle("ВНИМАНИЕ");
                    dialog.setMessage("Вы точно хотите удалить?");
                    dialog.setButton(AlertDialog.BUTTON_POSITIVE, "ДА", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.setButton(AlertDialog.BUTTON_NEGATIVE, "НЕТ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            deleteData(getAdapterPosition());

                        }
                    });
                    dialog.show();
                    return false;
                }
            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClickItem(getAdapterPosition());
                }
            });
        }
    }
}
