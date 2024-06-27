package campus.tech.kakao.contacts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

//contact.kt 파일이 contact 객체 정의 및 intent로 데이터 전달
//contactsadapter.kt 파일은 그 데이터를 recyclerview로 표시하는 역할 - 연락처 목록 조회
//RecyclerView를 사용해서 adapter패턴 필요
//각 항목 표시 및 효율적 ㅗ간리 위해 adapter 패턴 사용함 - 데이터 소스와 view 연결
class ContactsAdapter( //ReyclerView.Adapter 상속받아서 구현
    val contacts: List<Contact>, //연락처 목록 데이터 저장함
    val clickListener: (Contact) -> Unit //각 항목 클릭 시 실행되는 함수
    //즉 조회된 애들 하나하나 다 보여주는 거 얘가 하는거
) : RecyclerView.Adapter<ContactsAdapter.ContactViewHolder>() {

    //viewholder 객체 생성 - 목록 객체 하나
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        //layoutInflater - xml layout 파일 -> view 객체로 변환
        //parent.context로 layoutinflaer 객체 생성
        val view = LayoutInflater.from(parent.context).inflate(R.layout.contact_item, parent, false)
        //inflate는 지정된 xml레이아웃 (R.layout.contact_item)을 view 객체로 변환
        //parent - 새로운 view가 추가되길 기다리고 있는 viewgroup
        return ContactViewHolder(view) //생성자호출 -> 새로운 viewholder 객체 생성
    }

    //RecylerView의 각 항목, 즉 연락처 조회 시 나타나는 거 각 항목에 데이터 바인딩
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        holder.bind(contacts[position], clickListener) //position(위치)의 contact 데이터를 viewholder에 바인딩함
    }

    //RecyclerView 항목 개수 반환 - contacts 리스트 크기 반환
    override fun getItemCount(): Int = contacts.size

    //recylcerview.viewholder 상속 받아서 구현
    class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) { //뷰 초기화
        val contactInitial = itemView.findViewById<TextView>(R.id.contactInitial)
        val contactName = itemView.findViewById<TextView>(R.id.contactName)

        //연락처 데이터 view 바인딩
        fun bind(contact: Contact, clickListener: (Contact) -> Unit) {
            //연락처 초기 문자 설정
            val contactFirstChar = contact.name.first().toString()
            contactInitial.text = contactFirstChar

            //연락처 이름 설정
            val contactFullName = contact.name
            contactName.text = contactFullName

            // 클릭 리스너 설정
            itemView.setOnClickListener {
                clickListener(contact)
            }
        }
    }
}
