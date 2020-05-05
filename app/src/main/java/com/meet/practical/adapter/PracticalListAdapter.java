package com.meet.practical.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.meet.owlutilities.utils.GlideUtil;
import com.meet.practical.R;
import com.meet.practical.databinding.RowPracticalBinding;
import com.meet.practical.model.entity.response.PracticalInfo;

import java.io.File;
import java.util.List;

public class PracticalListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<PracticalInfo> list;
    File file = null;

    public PracticalListAdapter(Context context, List<PracticalInfo> list) {
        this.mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from (parent.getContext ( )).inflate (R.layout.row_practical, parent, false);
        return new ItemViewHolder (itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final PracticalInfo info = list.get (position);
        final ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
        itemViewHolder.binding.txtFirstName.setText(info.getFirst_name());
        itemViewHolder.binding.txtLastName.setText(info.getLast_name());
        itemViewHolder.binding.txtGender.setText(info.getGender());
        itemViewHolder.binding.txtStatus.setText(info.getStatus());
        itemViewHolder.getData (info);

        if (info.getGender().equals("male")){
            itemViewHolder.binding.txtGender.setTextColor(mContext.getResources().getColor(R.color.green));
        }else {
            itemViewHolder.binding.txtGender.setTextColor(mContext.getResources().getColor(R.color.red));
        }
        if (info.getStatus().equals("active")){
            itemViewHolder.binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.green));
        }else {
            itemViewHolder.binding.txtStatus.setTextColor(mContext.getResources().getColor(R.color.red));
        }
//        if ( info.getStatus() != null && info.getStatus().equals("Completed")){
//            itemViewHolder.binding.txtUserStatus.setTextColor(mContext.getResources().getColor(R.color.green));
//            itemViewHolder.binding.imgDesh.setTextColor(mContext.getResources().getColor(R.color.green));
//            itemViewHolder.binding.imgDownload.setVisibility(View.VISIBLE);
//        }else if (info.getStatus() != null && info.getStatus().equals("Assigned")){
//            itemViewHolder.binding.txtUserStatus.setTextColor(Color.RED);
//            itemViewHolder.binding.imgDesh.setTextColor(Color.RED);
//            itemViewHolder.binding.imgDownload.setVisibility(View.INVISIBLE);
//        }else{
//            itemViewHolder.binding.txtUserStatus.setTextColor(Color.GRAY);
//            itemViewHolder.binding.imgDesh.setTextColor(Color.GRAY);
//            itemViewHolder.binding.imgDownload.setVisibility(View.INVISIBLE);
//        }
    }

    @Override
    public int getItemCount() {
        return list.size ( );
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private RowPracticalBinding binding;

        public void getData(PracticalInfo info) {
            binding.setInfo (info);
        }

        public ItemViewHolder(View itemView) {
            super (itemView);
            binding = DataBindingUtil.bind (itemView);
        }
    }
}
