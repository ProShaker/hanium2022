package kr.tutorials.majorapi

import DataPackage_Careernet_Specific.DatasetCareernetSpecific
import Var_Set.majorInterest
import Var_Set.majorName
import Var_Set.majorSeqNumb
import Var_Set.majorSummary

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import kr.tutorials.majorapi.databinding.ActivityMajorBinding
import kr.tutorials.majorapi.databinding.FragmentMajorSummaryBinding
import org.json.JSONObject
import org.w3c.dom.Text
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class MajorSummary : Fragment(R.layout.fragment_major_summary) {
    private var sBinding: FragmentMajorSummaryBinding? = null
    private val summaryBinding get() = sBinding!!

    // TODO: Rename and change types of parameters
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        sBinding = FragmentMajorSummaryBinding.inflate(inflater, container, false)
        return summaryBinding.root
    }

    // memory leaks 방지
   override fun onDestroyView() {
        super.onDestroyView()
        sBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("프래그먼트 텍스트에 값 대입")

        summaryBinding.MajorSummaryTV.text = majorName
        summaryBinding.MajorSummaryTV.text = majorSummary
//        summaryBinding.MajorInterestTV.text = majorInterest

    }

}





