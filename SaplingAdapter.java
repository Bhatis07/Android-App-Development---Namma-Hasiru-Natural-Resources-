package com.internshipproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.button.MaterialButton;
import java.util.ArrayList;
import java.util.List;

public class SaplingAdapter extends RecyclerView.Adapter<SaplingAdapter.SaplingHolder> {
    private List<Sapling> saplings = new ArrayList<>();
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onUpdateClick(Sapling sapling);
        void onAlertClick(Sapling sapling);
        void onCaptureClick(Sapling sapling);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public SaplingHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_sapling, parent, false);
        return new SaplingHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SaplingHolder holder, int position) {
        Sapling currentSapling = saplings.get(position);
        holder.textViewName.setText(currentSapling.getName());
        holder.textViewLocation.setText(currentSapling.getLocation());
        holder.textViewStatus.setText(currentSapling.getStatus());
        
        // In a real app, you'd load the image using Glide or Picasso from currentSapling.getImagePath()
        // For now, we use a placeholder or the default
        holder.imageViewTree.setImageResource(R.drawable.hero_sapling);
    }

    @Override
    public int getItemCount() {
        return saplings.size();
    }

    public void setSaplings(List<Sapling> saplings) {
        this.saplings = saplings;
        notifyDataSetChanged();
    }

    class SaplingHolder extends RecyclerView.ViewHolder {
        private TextView textViewName;
        private TextView textViewLocation;
        private TextView textViewStatus;
        private ImageView imageViewTree;
        private MaterialButton btnUpdate;
        private MaterialButton btnAlert;
        private MaterialButton btnCapture;

        public SaplingHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewLocation = itemView.findViewById(R.id.text_view_location);
            textViewStatus = itemView.findViewById(R.id.text_view_status);
            imageViewTree = itemView.findViewById(R.id.iv_forest_tree_item);
            btnUpdate = itemView.findViewById(R.id.btn_growth_update_item);
            btnAlert = itemView.findViewById(R.id.btn_growth_alert_item);
            btnCapture = itemView.findViewById(R.id.btn_capture_tree_item);

            btnUpdate.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onUpdateClick(saplings.get(position));
                }
            });

            btnAlert.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onAlertClick(saplings.get(position));
                }
            });

            btnCapture.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (listener != null && position != RecyclerView.NO_POSITION) {
                    listener.onCaptureClick(saplings.get(position));
                }
            });
        }
    }
}
