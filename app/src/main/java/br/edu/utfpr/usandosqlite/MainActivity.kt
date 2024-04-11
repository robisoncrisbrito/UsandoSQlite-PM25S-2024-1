package br.edu.utfpr.usandosqlite

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var etCod : EditText
    private lateinit var etNome : EditText
    private lateinit var etTelefone : EditText

    private lateinit var banco : SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCod = findViewById( R.id.etCod )
        etNome = findViewById( R.id.etNome )
        etTelefone = findViewById( R.id.etTelefone )

        banco = SQLiteDatabase.openOrCreateDatabase( this.getDatabasePath( "dbfile.sqlite" ), null )
        banco.execSQL( "CREATE TABLE IF NOT EXISTS cadastro( _id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, telefone TEXT)" )
    }

    fun btIncluirOnClick(view: View) {

        val registro = ContentValues()
        registro.put( "nome", etNome.text.toString() )
        registro.put( "telefone", etTelefone.text.toString() )

        banco.insert( "cadastro", null, registro )

        Toast.makeText( this, "Registro incluído com sucesso", Toast.LENGTH_LONG ).show()

    }

    fun btAlterarOnClick(view: View) {

    }

    fun btExcluirOnClick(view: View) {

    }

    fun btPesquisarOnClick(view: View) {

    }

    fun btListarOnClick(view: View) {
        var saida = ""

        val cursor =  banco.query( "cadastro", null, null, null, null, null, null )

        while ( cursor.moveToNext() ) {
            saida += cursor.getInt( 0 ).toString() + " - " +
                    cursor.getString( 1 ) + " - " +
                    cursor.getString( 2 ) + "\n"
        }

        Toast.makeText( this, saida, Toast.LENGTH_LONG ).show()
    }
}