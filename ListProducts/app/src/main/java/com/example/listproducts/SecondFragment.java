package com.example.listproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.text.PrecomputedTextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.listproducts.databinding.FragmentSecondBinding;
import com.example.listproducts.entitys.Product;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private TableLayout tableProducts;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
        this.fillTable();
    }



    public void refreshTable(){
        Map<Integer, Product> listProducts= FirstFragment.getListProducts();
        if(listProducts.size() > 0){
            this.loadData(listProducts);
        }else{
            this.messajeTabla("Not data found");
        }
    }

    public void loadData(Map<Integer, Product> list){
       this.limpiarTabla();
       Integer keyProduct= Integer.valueOf(list.keySet().toArray()[0].toString());
       this.binding.tableProducts.setStretchAllColumns(true);

       for(Integer key: list.keySet()){
           //
           TableRow rowData= new TableRow(getContext());
           TextView iName= new TextView(getContext());
           TextView iPrecio= new TextView(getContext());
           TextView iCantidad= new TextView(getContext());
           Button iButtonDelete= new Button(getContext());

           //
           Product p= list.get(key);
           String name= p.getName();
           Integer precio= p.getPrecio();
           Integer cantidad= p.getCantidad();
           System.out.println("Cantidad es the ");
           System.out.println(cantidad);
           //
           iName.setText(name);
           iPrecio.setText(precio.toString());
           iCantidad.setText(cantidad.toString());

           iButtonDelete.setText("Delete product");
           iButtonDelete.setOnClickListener(new View.OnClickListener(){
               @Override
               public void onClick(View view){
                   Product pr= list.remove(key);
                   System.out.println("Deleted product: ".concat(pr.getName()));
                   refreshTable();
               }
           });

           rowData.addView(iName);
           rowData.addView(iCantidad);
           rowData.addView(iPrecio);
           rowData.addView(iButtonDelete);

           //rowData.setPadding(10, 0, 0,0);


           //this.tableProducts.setColumnStretchable(4, false);
           this.tableProducts.addView(rowData);
       }
       Integer sumaInt= list.values().stream().map(e -> e.getPrecio() * e.getCantidad()).mapToInt(i->i).sum();
       Integer cantProductos= list.values().stream().mapToInt(i -> i.getCantidad()).sum();
       Double sumaDouble= list.values().stream().map(e -> e.getCantidad() * e.getPrecio()).mapToDouble(i -> i).sum();
       binding.vBruto.setText(sumaInt.toString());
       binding.vCantidad.setText(cantProductos.toString());
    }
    public void limpiarTabla(){
       this.tableProducts.removeAllViews();
        this.binding.vBruto.setText("0");
        this.binding.vCantidad.setText("0");
    }
    public void messajeTabla(String mensaje){
        this.limpiarTabla();
        TextView t= new TextView(getContext());
        t.setText("Not data found");
        this.tableProducts.addView(t);
    }
    public synchronized void instanciaTabla(){
        this.tableProducts= this.binding.tableProducts;
    }

    public void fillTable(){
        this.instanciaTabla();
        this.refreshTable();
        System.out.println("finally");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}