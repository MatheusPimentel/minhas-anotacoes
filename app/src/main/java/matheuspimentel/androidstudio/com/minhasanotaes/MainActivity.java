package matheuspimentel.androidstudio.com.minhasanotaes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    ImageButton button;

    private static final String ANOTACAO = "anotacao.txt";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.textoId);
        button = findViewById(R.id.salvarId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String textoDigitado = editText.getText().toString();
                gravarNoArquivo(textoDigitado);

                Toast.makeText(MainActivity.this, "Salvo com sucesso", Toast.LENGTH_SHORT).show();
            }
        });

        //recuperar arquivo que foi gravado

        if (lerArquivo() != null) {
            editText.setText(lerArquivo());
        }
    }

    private void gravarNoArquivo(String textoDigitado) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(ANOTACAO, Context.MODE_PRIVATE));
            outputStreamWriter.write(textoDigitado);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.v("MainActivity", e.toString());
        }
    }

    private String lerArquivo() {
        String resultado = "";
         try {
             //Abrir o arquivo
             InputStream arquivo = openFileInput(ANOTACAO);

             if (arquivo != null) {
                 //ler arquivo
                 InputStreamReader inputStreamReader = new InputStreamReader(arquivo);

                 //gerar buffer do arquivo lido
                 BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                 //recuperar os textos do arquivo
                 String linhaArquivo = "";

                 while ((linhaArquivo = bufferedReader.readLine()) != null) {
                     resultado += linhaArquivo;
                 }
                 arquivo.close();
             }
         } catch (IOException e) {
             Log.v("MainActivity", e.toString());
         }
        return resultado;
    }
}
