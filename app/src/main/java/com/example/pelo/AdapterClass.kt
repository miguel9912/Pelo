package com.example.pelo
import android.content.Context
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView


class AdapterClass(
    private val context: Context,
    private val dataList: List<modelo.DataClass>,
    private val clickListener: ClickListener

) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {

    interface ClickListener {
        fun onButtonClick(position: Int)
    }

    class ViewHolderClass(itemView: View, clickListener: ClickListener) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.image)
        val image1: ImageView = itemView.findViewById(R.id.image1)
        val image2: ImageView = itemView.findViewById(R.id.image2)
        val title: TextView = itemView.findViewById(R.id.title)
        val myButton: Button = itemView.findViewById(R.id.myButton)
        val expandingLayout: LinearLayout = itemView.findViewById(R.id.expandingLayout)
        //val description: TextView = itemView.findViewById(R.id.description)
        val origin: TextView = itemView.findViewById(R.id.origin)
        fun Int.dpToPx(context: Context): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                this.toFloat(),
                context.resources.displayMetrics
            ).toInt()
        }
        init {
            myButton.setOnClickListener {
                clickListener.onButtonClick(adapterPosition)
            }
            fun bind(currentItem: modelo.DataClass, clickListener: ClickListener) {
                image.setImageResource(currentItem.dataImage)
                title.text = currentItem.dataTitle
                //description.text = currentItem.dataDescription
                //origin.text = currentItem.dataOrigin
                expandingLayout.visibility = if (currentItem.isExpanded) View.VISIBLE else View.GONE

                // Ajusta la altura del expandingLayout
                val params = expandingLayout.layoutParams as ConstraintLayout.LayoutParams
                params.height = if (currentItem.isExpanded) ViewGroup.LayoutParams.WRAP_CONTENT else 32.dpToPx(itemView.context)
                expandingLayout.layoutParams = params

                myButton.setOnClickListener {
                    clickListener.onButtonClick(adapterPosition)
                }
            }

            // Otras funciones auxiliares según sea necesario
        }

    }
    // Extensión para convertir dp a píxeles


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolderClass(itemView, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        /*holder.clickListener = clickListener*/
        holder.image.setImageResource(currentItem.dataImage)
        holder.title.text = currentItem.dataTitle
        //holder.description.text = currentItem.dataDescription
        //holder.origin.text = currentItem.dataOrigin
        holder.image1.setImageResource(currentItem.image1)
        holder.image2.setImageResource(currentItem.image2)
        holder.myButton.setOnClickListener {
            clickListener.onButtonClick(position)
        }

        if (currentItem.isExpanded) {
            holder.expandingLayout.visibility = View.VISIBLE

        } else {
            holder.expandingLayout.visibility = View.GONE
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}