package foodsqlitedemo.quocnguyen.com.foodsqlitedemo;

import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;

/**
 * Created by Astorga Dario on 26-May-17.
 */

public class ArticuloList extends AppCompatActivity {

    GridView gridView;
    ArrayList<Articulo> list;
    ArticuloListAdapter adapter = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_activity);

        gridView = (GridView) findViewById(R.id.gridView);
        list = new ArrayList<>();
        adapter = new ArticuloListAdapter(this, R.layout.food_items, list);
        gridView.setAdapter(adapter);


        // Obtener todos los datos de sqlite
        Cursor cursor = ArticuloMainActivity.sqLiteHelper.getData("SELECT * FROM ARTICULO");
        list.clear();
        while (cursor.moveToNext()){
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            byte[] image = cursor.getBlob(3);

            list.add(new Articulo(name, price, image, id));
        }
        adapter.notifyDataSetChanged();
    }
}
