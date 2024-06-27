package campus.tech.kakao.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactsAdapter(
    private val contacts: List<Contact>,
    private val clickListener: (Contact) -> Unit
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position], clickListener)
    }

    override fun getItemCount(): Int = contacts.size

    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val contactInitial = itemView.findViewById<TextView>(R.id.contactInitial)
        private val contactName = itemView.findViewById<TextView>(R.id.contactName)

        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            contactInitial.text = contact.name.first().toString()
            contactName.text = contact.name
            itemView.setOnClickListener { clickListener(contact) }
        }
    }
}
