package modelo

object Clientes
{
    var clientes = ArrayList<Cliente>()
    fun aniadirPersona(c:Cliente){
        clientes.add(c)
    }
}