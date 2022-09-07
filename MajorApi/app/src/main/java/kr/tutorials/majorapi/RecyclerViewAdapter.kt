package kr.tutorials.majorapi

import Var_Set.*
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import kr.tutorials.majorapi.databinding.FragmentMajorSummaryBinding


class RecyclerViewAdapter(private val majorNameList: List<String>, private val majorNumbList: List<String>) :
    RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {



    fun setSelection(position: Int) {
        majorName = majorNameList[position]
        majorSeqNumb = majorNumbList[position].toInt()
    }

    // 리사이클러뷰 라디오 버튼에 이벤트를 부여해주는 클래스
    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // 라디오 버튼 변수화
        private val radioButton: RadioButton = itemView.findViewById(R.id.MajorNameRow_radioButton)

        //라디오 버튼에 값, 이벤트를 부여해주는 함수
        fun bind(MajorName: String) = with(itemView) {

            // 입력받은 String을 라디오 버튼 텍스트에 대입
            radioButton.text = MajorName

            // 라디오 버튼 클릭시 발생 이벤트
            val clickListener = View.OnClickListener {
                selectedMajor = adapterPosition
                notifyDataSetChanged()
                setSelection(selectedMajor)

                // 대학 유형에 따라 불러오는 Api 변화 > 프래그먼트 호출
                if (UnivType)
                    getSpecificData(retrofitInstance().getUnivMajorSpecific(majorSeqNumb))
                else
                    getSpecificData(retrofitInstance().getCollegeMajorSpecific(majorSeqNumb))
            }
            radioButton.setOnClickListener(clickListener)
        }
    }




    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): RecyclerViewAdapter.MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.recycler_row, viewGroup, false)

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: RecyclerViewAdapter.MyViewHolder, position: Int) {
        viewHolder.bind(majorNameList[position])
        viewHolder.itemView.findViewById<RadioButton>(R.id.MajorNameRow_radioButton).isChecked =
            position == selectedMajor

        majorBinding.radiogroupUnivType.setOnClickListener{
            println("이거 실행되고있는거냐?")
            setSelection(-1)
        }
    }

    override fun getItemCount(): Int {
        return majorNameList.size
    }
}