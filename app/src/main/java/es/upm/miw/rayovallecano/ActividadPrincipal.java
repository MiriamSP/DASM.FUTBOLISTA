package es.upm.miw.rayovallecano;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import es.upm.miw.rayovallecano.models.Futbolista;
import es.upm.miw.rayovallecano.models.RepositorioFutbolistas;

public class ActividadPrincipal extends AppCompatActivity {

    ArrayList<Futbolista> futbolistas;
    ListView lvListadoFutbolistas;

    final static String URL_OBJETIVO = "https://avatars1.githubusercontent.com/u/5365410";
    ImageView ivContenido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        RepositorioFutbolistas repositorio = new RepositorioFutbolistas(this);

        int num = (int) (100 * Math.random());
        Log.i("Num", String.format("%d", repositorio.add(
                new Futbolista(num, "Jugador " + String.format("%d", num), num, num % 2 == 0, "Primera", null))));

        Futbolista newFutbolista = new Futbolista(++num,"Jugador " + String.format("%d", num), num, num % 2 == 0, "Primera", "https://www.google.es/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=0CAcQjRxqFQoTCLqY_ODM7MgCFQHtFAodWkIO5A&url=http%3A%2F%2Fsp.depositphotos.com%2F8532494%2Fstock-illustration-cartoon-footballer-or-soccer-player.html&bvm=bv.106379543,d.d24&psig=AFQjCNHg75RhM-2Py1VPEifycBpzTkG9mw&ust=1446377101470527");
        repositorio.add(newFutbolista);
        futbolistas = repositorio.getAll();
        ArrayAdapter<Futbolista> adaptador = new FutbolistaAdapter(this, futbolistas);
        lvListadoFutbolistas = (ListView) findViewById(R.id.lvListadoFutbolistas);
        lvListadoFutbolistas.setAdapter(adaptador);

        lvListadoFutbolistas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO editar futbolistas
                // Toast.makeText(contexto, futbolistas.get(position).toString(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(ActividadPrincipal.this, ActividadMostrarFutbolista.class);
                // TO DO Parcelable
                intent.putExtra("MOSTRAR_Futbolista", futbolistas.get(position));
                startActivity(intent);
            }
        });


        ivContenido = (ImageView) findViewById(R.id.ivMostrarFutbolistaImagen);

        // android-23: acceso contenido desde actividad principal -> NetworkOnMainThreadException
        // URL url = new URL(URL_OBJETIVO);
        // HttpURLConnection http = (HttpURLConnection) url.openConnection();

        // Mostrar contenido



        ;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_actividad_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                // TODO Ajustes
                break;
            case R.id.accionVaciar:
                // TODO dialog(confirmar) -> vaciar  tabla
                break;
        }

        return super.onOptionsItemSelected(item);
    }





}
