package campus.tech.kakao.contacts

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView

class ContactAdapter(context: Context, contacts: List<Contact>) : ArrayAdapter<Contact>(context, 0, contacts) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contact = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.contact_item, parent, false)
        val nameTextView = view.findViewById<TextView>(R.id.contactName)
        val phoneTextView = view.findViewById<TextView>(R.id.contactPhone)

        nameTextView.text = contact?.name
        phoneTextView.text = contact?.phone

        return view
    }
}
