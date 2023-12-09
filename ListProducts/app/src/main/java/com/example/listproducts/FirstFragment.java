package com.example.listproducts;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.listproducts.databinding.FragmentFirstBinding;
import com.example.listproducts.entitys.Product;

import java.util.HashMap;
import java.util.Map;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private static Map<Integer, Product> listProducts= new HashMap<>();
    private boolean modify= false;

    /*public FirstFragment(){
        this.listProducts.put(1, new Product(1, "Manzana", 3, 700));
        Product p= new Product(2, "Fresa", 2, 700);
        this.listProducts.put(p.getId(), p);
    }*/

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });
        binding.buttonSave.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String name= binding.editName.getText().toString();
                Integer cantidad= Integer.valueOf(binding.editCantidad.getText().toString());
                Integer precio= Integer.valueOf(binding.editPrecio.getText().toString());
                Integer newKey= getKey();
                //Integer newKey= 1;
                Product p= new Product(newKey, name, cantidad, precio);
                if(!modify){
                    saveProduct(p);
                    System.out.print("Guardando ");
                    System.out.println(listProducts.size());
                }else{
                    System.out.print("Modificando ");
                    System.out.println(listProducts.size());
                    editProduct(p);
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public Integer getKey(){
        Integer nkey= 0;
        Integer tamano= this.listProducts.size();
        System.out.println("Tamano");
        System.out.println(tamano);
        if(tamano > 0){
            nkey= Integer.valueOf(listProducts.keySet().toArray()[tamano - 1].toString()) + 1;
        }else{
            nkey= 1;
        }
        return nkey;
    }
    public Product saveProduct(Product product){
        Integer key= product.getId();
        this.listProducts.put(key, product);
        limpiarInputs();
        return product;
    }
    public Product editProduct(Product product){
        Integer key= product.getId();
        Product modifyedProduct= this.listProducts.get(key);
        this.listProducts.replace(key, product);
        limpiarInputs();
        modify= false;
        return modifyedProduct;
    }
    public void limpiarInputs(){
        this.binding.editName.setText("");
        this.binding.editCantidad.setText("");
        this.binding.editPrecio.setText("");
    }
    public static void setListProducts(Map<Integer, Product> lProducts){
        listProducts= lProducts;
    }

    public static Map<Integer, Product> getListProducts(){
        return listProducts;
    }
}