package com.example.mobilecontrol.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mobilecontrol.LogicaNegocio.Agencia;
import com.example.mobilecontrol.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class AgenciaAdapter extends RecyclerView.Adapter<AgenciaAdapter.MyViewHolder> implements Filterable {
    private List<Agencia> agenciaList;
    private List<Agencia> agenciaListFiltered;
    private AgenciaAdapterListener listener;
    private Agencia deletedItem;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView titulo1, titulo2, description;
        //two layers
        public RelativeLayout viewForeground, viewBackgroundDelete, viewBackgroundEdit;


        public MyViewHolder(View view) {
            super(view);
            titulo1 = view.findViewById(R.id.titleFirstLbl);
            titulo2 = view.findViewById(R.id.titleSecLbl);
            description = view.findViewById(R.id.descriptionLbl);
            viewBackgroundDelete = view.findViewById(R.id.view_background_delete);
            viewBackgroundEdit = view.findViewById(R.id.view_background_edit);
            viewForeground = view.findViewById(R.id.view_foreground);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected contact in callback
                    listener.onContactSelected(agenciaListFiltered.get(getAdapterPosition()));
                }
            });
        }
    }

    public AgenciaAdapter(List<Agencia> agenciaList, AgenciaAdapterListener listener) {
        this.agenciaList = agenciaList;
        this.listener = listener;
        //init filter
        this.agenciaListFiltered = agenciaList;
    }

    public void updateAgencias(List<Agencia> agencias) {
        this.agenciaList = agencias;
        notifyDataSetChanged();
    }

    @Override
    public AgenciaAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(AgenciaAdapter.MyViewHolder holder, int position) {
        // rendering view
        final Agencia cliente = agenciaListFiltered.get(position);
        holder.titulo1.setText(cliente.getCodigo());
        holder.titulo2.setText(cliente.getNombre());
        holder.description.setText(cliente.getEmail());
    }

    @Override
    public int getItemCount() {
        return agenciaListFiltered.size();
    }

    public void removeItem(int position) {
        deletedItem = agenciaListFiltered.remove(position);
        Iterator<Agencia> iter = agenciaList.iterator();
        while (iter.hasNext()) {
            Agencia aux = iter.next();
            if (deletedItem.equals(aux))
                iter.remove();
        }
        // notify item removed
        notifyItemRemoved(position);
    }

    public void restoreItem(int position) {

        if (agenciaListFiltered.size() == agenciaList.size()) {
            agenciaListFiltered.add(position, deletedItem);
        } else {
            agenciaListFiltered.add(position, deletedItem);
            agenciaList.add(deletedItem);
        }
        notifyDataSetChanged();
        // notify item added by position
        notifyItemInserted(position);
    }

    public Agencia getSwipedItem(int index) {
        if (this.agenciaList.size() == this.agenciaListFiltered.size()) { //not filtered yet
            return agenciaList.get(index);
        } else {
            return agenciaListFiltered.get(index);
        }
    }

    public void onItemMove(int fromPosition, int toPosition) {
        if (agenciaList.size() == agenciaListFiltered.size()) { // without filter
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(agenciaList, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(agenciaList, i, i - 1);
                }
            }
        } else {
            if (fromPosition < toPosition) {
                for (int i = fromPosition; i < toPosition; i++) {
                    Collections.swap(agenciaListFiltered, i, i + 1);
                }
            } else {
                for (int i = fromPosition; i > toPosition; i--) {
                    Collections.swap(agenciaListFiltered, i, i - 1);
                }
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    agenciaListFiltered = agenciaList;
                } else {
                    List<Agencia> filteredList = new ArrayList<>();
                    for (Agencia row : agenciaList) {
                        // filter use two parameters
                        if (row.getCodigo().toLowerCase().contains(charString.toLowerCase()) || row.getNombre().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }

                    agenciaListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = agenciaListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                agenciaListFiltered = (ArrayList<Agencia>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface AgenciaAdapterListener {
        void onContactSelected(Agencia agencia);
    }
}
