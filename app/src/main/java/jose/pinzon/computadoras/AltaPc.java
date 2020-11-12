package jose.pinzon.computadoras;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AltaPc extends AppCompatActivity {
    private EditText et1, et2, et3, et4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alta_pc);

        et1 = (EditText) findViewById(R.id.editText1);
        et2 = (EditText) findViewById(R.id.editText2);
        et3 = (EditText) findViewById(R.id.editText3);
        et4 = (EditText) findViewById(R.id.editText4);
    }
    //@Override
    //public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.alta_alumno, menu);
      //  return true;
   // }

    public void alta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        String marca = et2.getText().toString();
        String modelo = et3.getText().toString();
        String precio = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("id", id);
        registro.put("marca", marca);
        registro.put("modelo", modelo);
        registro.put("precio", precio);
        bd.insert("computadoras", null, registro);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        Toast.makeText(this, "Se guardaron los datos de la computadora",
                Toast.LENGTH_SHORT).show();
    }

    public void consulta(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        Cursor fila = bd.rawQuery(
                "select marca,modelo,precio from computadoras where id=" + id
                        + "", null);
        if (fila.moveToFirst()) {
            et2.setText(fila.getString(0));
            et3.setText(fila.getString(1));
            et4.setText(fila.getString(2));
        } else
            Toast.makeText(this, "No existe una Computadora con dicho id",
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void baja(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        int cant = bd.delete("computadoras", "id=" + id + "", null);
        bd.close();
        et1.setText("");
        et2.setText("");
        et3.setText("");
        et4.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se ha eliminado a la cp que seleccionaste con esa id",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe una Pc con esa id",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLite admin = new AdminSQLite(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String id = et1.getText().toString();
        String marca= et2.getText().toString();
        String modelo = et3.getText().toString();
        String precio = et4.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("marca", marca);
        registro.put("modelo", modelo);
        registro.put("precio", precio);
        int cant = bd.update("computadoras", registro, "id=" + id, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "Se modificaron los datos de la pc", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "No existe una Pc con esa id",
                    Toast.LENGTH_SHORT).show();
    }

}