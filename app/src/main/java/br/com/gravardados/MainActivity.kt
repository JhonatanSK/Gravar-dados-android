package br.com.gravardados

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    lateinit var sharedPref : SharedPreferences
    lateinit var editor : SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Instanciar o SharedPreferences
        sharedPref = getSharedPreferences("Dados", Context.MODE_PRIVATE)
        editor = sharedPref.edit()

        btn_gravar.setOnClickListener {
            if(edt_texto.editableText.isNotEmpty()){
                val dadoDigitado = edt_texto.editableText.toString().trim()

                editor.putString("dado", dadoDigitado)
                editor.apply()

                edt_texto.text.clear()
                Toast.makeText(this, "Dados gravados com sucesso!",Toast.LENGTH_SHORT).show()


                //txv_resultado.text = dadoDigitado
            }
            else{
                Toast.makeText(this, "Preencha o campo de dados", Toast.LENGTH_SHORT).show()
            }
        }

        fab.setOnClickListener { view ->
            txv_resultado.text = sharedPref.getString("dado", "Não informado")
            Snackbar.make(view, "Deu certo!!!", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
