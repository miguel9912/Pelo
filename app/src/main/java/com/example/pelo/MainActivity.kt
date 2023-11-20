package com.example.pelo
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pelo.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), AdapterClass.ClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var cliente: ArrayList<modelo.Cliente>
    private lateinit var imageIcons: Array<Int>
    private lateinit var images: Array<Int>
    private lateinit var titleList: Array<String>
    private val SECOND_ACTIVITY_REQUEST_CODE = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageIcons = arrayOf(R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara)
        images = arrayOf(R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara, R.drawable.cara)
        titleList = arrayOf("CLIENTE 1", "CLIENTE 2", "CLIENTE 3", "CLIENTE 4")

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        cliente = arrayListOf<modelo.Cliente>()
        getData()

        binding.anadir.setOnClickListener{
            irAVentanaAnadir()
        }
    }

    private fun getData() {
        var dataClass: modelo.Cliente
        for (i in imageIcons.indices) {
            dataClass=when(i){
                0 -> modelo.Cliente(
                    imageIcons[i],
                    titleList[i],
                    getString(R.string.indicaciones_cliente1),
                    images[2 * i],
                    images[2 * i + 1]
                )
                1 -> modelo.Cliente(
                    imageIcons[i],
                    titleList[i],
                    getString(R.string.indicaciones_cliente2),
                    images[2 * i],
                    images[2 * i + 1]
                )
                2 -> modelo.Cliente(
                    imageIcons[i],
                    titleList[i],
                    getString(R.string.indicaciones_cliente3),
                    images[2 * i],
                    images[2 * i + 1]
                )
                3 -> modelo.Cliente(
                    imageIcons[i],
                    titleList[i],
                    getString(R.string.indicaciones_cliente4),
                    images[2 * i],
                    images[2 * i + 1]
                )

                else ->
                    modelo.Cliente(0, "", "", 0, 0)
            }
            cliente.add(dataClass)

        }

        val adapter = AdapterClass(this, cliente, this)
        recyclerView.adapter = adapter
    }

    // Implementación del método de la interfaz ClickListener
    /*override fun onButtonClick(position: Int) {
        // Manejar la lógica de expansión y contracción aquí
        val item = dataList[position]
        // Puedes acceder al elemento específico y realizar acciones según sea necesario
    }*/
    override fun onButtonClick(position: Int) {
        val item = cliente[position]
        item.isExpanded = !item.isExpanded
        recyclerView.adapter?.notifyItemChanged(position)
    }
    private fun irAVentanaAnadir() {
        //Este método nos lleva a la ventana de manea normal, pasando datos pero no esperas que la segunda devuelva datos.
        //comentado está pasando los atributos individualmente. he optado por pasar un objeto Persona (debe ser serializable)
        var miIntent: Intent = Intent(this, Ventana2::class.java)
        /*miIntent.putExtra("nombre", binding.cajaNombre.text.toString())
        miIntent.putExtra("edad", binding.cajaEdad.text.toString())*/
        var dc = modelo.Cliente(
            R.drawable.cara,
            "CLIENTE X",
            getString(R.string.indicaciones_cliente1),
            R.drawable.cara,
            R.drawable.cara
        )
        //miIntent.putExtra("obj",dc)
        startActivity(miIntent)
    }
}
